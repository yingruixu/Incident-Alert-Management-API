<template>
  <div class="monitoring">

    <el-card class="toolbar">
      <el-row :gutter="20">
        <el-col :span="12">
          <h3>🏗️ Infrastructure</h3>
        </el-col>
        <el-col :span="12" style="text-align: right">
          <el-button type="primary" size="small" @click="refreshData">
            🔄 Refresh
          </el-button>
        </el-col>
      </el-row>
    </el-card>
    <!-- Services card grid -->
    <el-row :gutter="20" style="margin-top: 20px">
      <el-col :span="8" v-for="server in servers" :key="server.name">
        <el-card class="server-card">
          <template #header>
            <div class="server-header">
              <span class="server-name">{{ server.name }}</span>
              <el-tag :type="server.status === 'healthy' ? 'success' : 'danger'" size="small">
                {{ server.status }}
              </el-tag>
            </div>
          </template>

          <!-- CPU -->
          <div class="metric">
            <div class="metric-label">
              <span>CPU</span>
              <span>{{ server.cpu }}%</span>
            </div>
            <el-progress :percentage="server.cpu" :color="getProgressColor(server.cpu)" :stroke-width="10" />
          </div>

          <!-- Memory -->
          <div class="metric">
            <div class="metric-label">
              <span>Memory</span>
              <span>{{ server.memory }}%</span>
            </div>
            <el-progress :percentage="server.memory" :color="getProgressColor(server.memory)" :stroke-width="10" />
          </div>

          <!-- Disk -->
          <div class="metric">
            <div class="metric-label">
              <span>Disk</span>
              <span>{{ server.disk }}%</span>
            </div>
            <el-progress :percentage="server.disk" :color="getProgressColor(server.disk)" :stroke-width="10" />
          </div>

          <!-- Network -->
          <div class="metric">
            <div class="metric-label">
              <span>Network I/O</span>
              <span>{{ server.network }}</span>
            </div>
          </div>

          <!-- Mini sparkline chart (canvas) -->
          <div style="margin-top:10px">
            <canvas :id="`spark-${server.safeId}`" width="300" height="50"></canvas>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <section class="observability-section">
      <div class="section-heading">
        <div>
          <span class="section-kicker">LIVE TELEMETRY</span>
          <h2>Service health</h2>
        </div>
        <span class="section-note">Updated by Prometheus</span>
      </div>
      <div class="panel-grid">
        <article v-for="panel in grafanaPanels" :key="panel.id" class="panel-card" :class="{ wide: panel.wide }">
          <div class="panel-card-header">
            <span>{{ panel.title }}</span>
            <span class="panel-source">Grafana</span>
          </div>
          <iframe :src="panel.url" :title="panel.title" loading="lazy" referrerpolicy="strict-origin-when-cross-origin"
            class="grafana-panel"></iframe>
        </article>
      </div>
    </section>
  </div>
</template>
<script setup>
// English comments throughout: this component replaces static mocks with Prometheus-driven data
import { ref, onMounted, nextTick } from 'vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'

// reactive state: servers list, loading flag and error message
const servers = ref([])
const loading = ref(false)
const error = ref('')

// Grafana URL can be changed per environment without rebuilding application code.
const grafanaBaseUrl = import.meta.env.VITE_GRAFANA_URL || '/grafana'
const grafanaDashboardUrl = `${grafanaBaseUrl.replace(/\/$/, '')}/d-solo/incident-prod-observability/incident-api-production-observability?orgId=1&from=now-6h&to=now&theme=light`
const grafanaPanels = [
  { id: 1, title: 'Backend availability' },
  { id: 3, title: '5xx error rate' },
  { id: 4, title: 'p95 latency' },
  { id: 8, title: 'Frontend availability' },
  { id: 10, title: 'MySQL availability' },
  { id: 11, title: 'MySQL connections' },
  { id: 14, title: 'TLS certificate days remaining', wide: true }
].map(panel => ({
  ...panel,
  url: `${grafanaDashboardUrl}&panelId=${panel.id}&kiosk=1`
}))

// Helper: produce a safe id for DOM elements (remove chars not allowed in id)
function makeSafeId(name) {
  return name.replace(/[^a-zA-Z0-9-_]/g, '_')
}

// Return a color for progress bars based on thresholds
function getProgressColor(value) {
  if (value >= 90) return '#f56c6c' // red
  if (value >= 70) return '#e6a23c' // orange
  return '#67c23a' // green
}

// Draw a simple sparkline into a canvas element using raw Canvas API
function drawSparkline(canvasId, points) {
  const c = document.getElementById(canvasId)
  if (!c || !points || points.length === 0) return
  const ctx = c.getContext('2d')
  const w = c.width
  const h = c.height
  ctx.clearRect(0, 0, w, h)
  const values = points.map(p => p.v)
  const min = Math.min(...values)
  const max = Math.max(...values)
  const range = max - min || 1
  ctx.beginPath()
  values.forEach((v, i) => {
    const x = (i / (values.length - 1 || 1)) * (w - 4) + 2
    const y = h - 2 - ((v - min) / range) * (h - 4)
    if (i === 0) ctx.moveTo(x, y)
    else ctx.lineTo(x, y)
  })
  ctx.strokeStyle = '#409EFF'
  ctx.lineWidth = 1.5
  ctx.stroke()
}

// Refresh data from backend Prometheus proxy
// This function queries Prometheus through backend endpoint `/api/prom/query_range`
// and maps result series to the `servers` array. Adjust PromQL as needed.
async function refreshData() {
  loading.value = true
  error.value = ''
  try {
    // time range: last 5 minutes, 30s step
    const end = Math.floor(Date.now() / 1000)
    const start = end - 300
    const step = 30

    // EXAMPLE PromQL: replace with your actual CPU metric
    // This query returns per-instance CPU usage as a ratio (0..1). Adjust if your metric differs.
    const cpuQuery = 'process_cpu_usage'

    const cpuRes = await axios.get('/api/prom/query_range', {
      params: {
        q: cpuQuery,
        start,
        end,
        step
      }
    })

    const result =
      cpuRes.data?.data?.result || []

    // map prometheus series to server objects
    servers.value = result.map(series => {
      const instance =
        series.metric.instance ||
        series.metric.job ||
        'unknown'

      const values =
        series.values.map(v => ({
          t: v[0] * 1000,
          v: Number(v[1])
        }))

      const last =
        values[values.length - 1]

      const cpuPct =
        last
          ? Math.round(last.v * 100)
          : 0

      return {
        name: instance,
        safeId: makeSafeId(instance),
        status:
          cpuPct > 90
            ? 'unhealthy'
            : 'healthy',

        cpu: cpuPct,

        // default data
        memory: 0,
        disk: 0,
        network: 'n/a',

        history: values
      }
    })


    // after DOM update, draw sparklines
    await nextTick()
    servers.value.forEach(s => {
      drawSparkline(`spark-${s.safeId}`, s.history)
    })

    ElMessage.success('Metrics refreshed')
  } catch (e) {
    console.error(e)
    error.value = e.response?.data || e.message || 'Failed to fetch metrics'
    ElMessage.error('Failed to refresh metrics')
  } finally {
    loading.value = false
  }
}

// Initialize on mount: fetch metrics once
onMounted(() => {
  refreshData()
})
</script>
<style scoped>
.server-card {
  margin-bottom: 20px;
}

.server-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.server-name {
  font-weight: bold;
}

.metric {
  margin-bottom: 15px;
}

.metric-label {
  display: flex;
  justify-content: space-between;
  margin-bottom: 5px;
  font-size: 14px;
  color: #606266;
}

.observability-section {
  margin-top: 28px;
  padding: 24px;
  border: 1px solid #dfe7ef;
  border-radius: 12px;
  background: #f7f9fc;
}

.section-heading {
  display: flex;
  align-items: end;
  justify-content: space-between;
  gap: 16px;
  margin-bottom: 18px;
}

.section-kicker {
  color: #7b8794;
  font-size: 11px;
  font-weight: 700;
  letter-spacing: 0.12em;
}

.section-heading h2 {
  margin: 4px 0 0;
  color: #17212b;
  font-size: 24px;
}

.section-note,
.panel-source {
  color: #7b8794;
  font-size: 12px;
}

.panel-grid {
  display: grid;
  grid-template-columns: repeat(3, minmax(0, 1fr));
  gap: 16px;
}

.panel-card {
  min-width: 0;
  overflow: hidden;
  border: 1px solid #dfe7ef;
  border-radius: 8px;
  background: #fff;
  box-shadow: 0 4px 14px rgba(25, 45, 70, 0.06);
}

.panel-card.wide {
  grid-column: span 3;
}

.panel-card-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 11px 14px;
  color: #263746;
  font-size: 13px;
  font-weight: 700;
}

.grafana-panel {
  display: block;
  width: 100%;
  height: 220px;
  border: 0;
  background: #fff;
}

@media (max-width: 1100px) {
  .panel-grid {
    grid-template-columns: repeat(2, minmax(0, 1fr));
  }

  .panel-card.wide {
    grid-column: span 2;
  }
}

@media (max-width: 680px) {
  .observability-section {
    padding: 16px;
  }

  .section-heading {
    align-items: start;
    flex-direction: column;
  }

  .panel-grid {
    grid-template-columns: 1fr;
  }

  .panel-card.wide {
    grid-column: span 1;
  }
}
</style>