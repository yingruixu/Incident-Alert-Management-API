<template>
  <div class="dashboard-shell">
    <div class="dashboard-surface">

      <main class="dashboard-content">
        <h1>Hello, {{ userName }}</h1>

        <section class="stats-grid">
          <article v-for="stat in stats" :key="stat.label" class="stat-card">
            <div class="stat-label">{{ stat.label }}</div>
            <div class="stat-value">{{ stat.value }}</div>
            <div class="stat-trend" :class="stat.stateClass">
              {{ stat.meta }}
            </div>
          </article>
        </section>

        <section class="chart-panel">
          <div class="panel-header">
            <span>Incident volume</span>
            <span class="muted">Last 7 days</span>
          </div>

          <div class="chart-wrap">
            <svg viewBox="0 0 1000 220" preserveAspectRatio="none" aria-label="incident volume chart">
              <defs>
                <linearGradient id="chartGlow" x1="0" x2="0" y1="0" y2="1">
                  <stop offset="0%" stop-color="rgba(97, 163, 255, 0.35)" />
                  <stop offset="100%" stop-color="rgba(97, 163, 255, 0.02)" />
                </linearGradient>
              </defs>
              <g class="chart-grid">
                <line x1="0" y1="45" x2="1000" y2="45" />
                <line x1="0" y1="90" x2="1000" y2="90" />
                <line x1="0" y1="135" x2="1000" y2="135" />
                <line x1="0" y1="180" x2="1000" y2="180" />
              </g>
              <path class="chart-area"
                d="M0 150 C80 128, 150 140, 240 120 S400 150, 500 118 S660 138, 760 124 S910 136, 1000 110 L1000 220 L0 220 Z" />
              <path class="chart-line"
                d="M0 150 C80 128, 150 140, 240 120 S400 150, 500 118 S660 138, 760 124 S910 136, 1000 110" />
            </svg>
            <div class="chart-tag">Today: {{ todayCount }}</div>
          </div>
        </section>

        <section class="incident-panel">
          <div class="panel-header compact">
            <span>Active incidents</span>
            <button type="button">View all →</button>
          </div>

          <div v-for="incident in liveIncidents" :key="incident.id" class="incident-row">
            <div class="incident-main">
              <span class="incident-dot" :class="incident.level"></span>
              <div class="incident-copy">
                <div class="incident-name">{{ incident.title }}</div>
                <div class="incident-meta">{{ incident.meta }}</div>
              </div>
            </div>
            <span class="incident-state">{{ incident.state }}</span>
          </div>

          <div v-if="!liveIncidents.length" class="empty-state">No active incidents</div>
        </section>
      </main>
    </div>
  </div>
</template>

<script setup>
import { computed, onMounted, ref } from 'vue'
import { incidentsApi } from '../api'

const incidents = ref([])

const chartValues = computed(() => {
  const last7Days = []
  const today = new Date()

  for (let i = 6; i >= 0; i--) {
    const d = new Date(today)
    d.setDate(today.getDate() - i)
    const key = d.toISOString().slice(0, 10)
    last7Days.push({
      label: d.toLocaleDateString('en-US', { weekday: 'short' }).substring(0, 3),
      count: incidents.value.filter(item => {
        const createdAt = item.createdAt ? new Date(item.createdAt) : null
        return createdAt && createdAt.toISOString().slice(0, 10) === key
      }).length
    })
  }

  return last7Days
})

//get username
const userName = computed(() => {
  return localStorage.getItem('username') || 'Guest'
})

const stats = computed(() => {
  const openCount = incidents.value.filter(item => item.status === 'OPEN').length
  const closedCount = incidents.value.filter(item => item.status === 'CLOSED').length
  const avgResolutionMinutes = incidents.value.length
    ? Math.max(12, Math.round((incidents.value.length * 22) / Math.max(openCount + closedCount, 1)))
    : 0

  return [
    { label: 'Open incidents', value: String(openCount).padStart(2, '0'), meta: openCount ? '↓ 18%' : 'No active', stateClass: 'down' },
    { label: 'MTTR', value: `${avgResolutionMinutes}m`, meta: '↓ 32%', stateClass: 'down' },
    { label: 'System health', value: openCount === 0 ? '99.98%' : '99.96%', meta: openCount === 0 ? '● Healthy' : '● Monitor', stateClass: 'healthy' }
  ]
})

const liveIncidents = computed(() => {
  return incidents.value
    .filter(item => item.status === 'OPEN')
    .slice(0, 2)
    .map(item => ({
      id: item.id,
      title: item.title || 'Unnamed incident',
      level: item.severity === 'P0' || item.severity === 'P1' ? 'pink' : 'amber',
      state: 'Investigating',
      meta: `${item.severity || 'P2'} · ${formatMinutesAgo(item.createdAt)}`
    }))
})

const todayCount = computed(() => {
  const today = new Date().toISOString().slice(0, 10)
  return incidents.value.filter(item => {
    const createdAt = item.createdAt ? new Date(item.createdAt) : null
    return createdAt && createdAt.toISOString().slice(0, 10) === today
  }).length
})

function formatMinutesAgo(dateValue) {
  if (!dateValue) return 'recently'

  const diffMinutes = Math.max(1, Math.round((Date.now() - new Date(dateValue).getTime()) / 60000))
  return `${diffMinutes} min ago`
}

async function loadDashboardData() {
  try {
    const data = await incidentsApi.getAll()
    incidents.value = Array.isArray(data) ? data : []
  } catch (error) {
    console.error('Dashboard data load failed:', error)
    incidents.value = []
  }
}

onMounted(() => {
  loadDashboardData()
})
</script>

<style scoped>
.dashboard-shell {
  width: 100%;
  min-height: 100%;
  display: flex;
  align-items: stretch;
  justify-content: center;
}

.dashboard-surface {
  width: 100%;
  max-width: 1180px;
  background: linear-gradient(180deg, rgba(14, 18, 24, 0.98), rgba(19, 22, 31, 0.98));
  border: 1px solid rgba(196, 207, 220, 0.08);
  border-radius: 24px;
  box-shadow: 0 28px 52px rgba(5, 7, 12, 0.45);
  overflow: hidden;
}

.traffic-lights {
  display: flex;
  align-items: center;
  gap: 10px;
}

.dot {
  display: block;
  width: 11px;
  height: 11px;
  border-radius: 50%;
  background: #2d3341;
}

.dot.red {
  background: #ff5f57;
}

.dot.yellow {
  background: #ffbd2e;
}

.dot.green {
  background: #28c840;
}

.workspace-tag {
  flex: 1;
  text-align: center;
  color: rgba(210, 220, 236, 0.68);
  font-size: 11px;
  letter-spacing: 0.26em;
  text-transform: lowercase;
}

.user-badge {
  width: 34px;
  height: 34px;
  border-radius: 50%;
  display: grid;
  place-items: center;
  background: rgba(255, 255, 255, 0.06);
  color: #eff6ff;
  font-size: 12px;
  font-weight: 700;
  border: 1px solid rgba(255, 255, 255, 0.08);
}

.dashboard-content {
  padding: 28px 22px 26px;
}

h1 {
  margin: 0 0 22px;
  font-size: clamp(2rem, 3vw, 3.2rem);
  line-height: 1.1;
  letter-spacing: -0.06em;
  font-weight: 700;
  color: #f4f7fb;
}

.stats-grid {
  display: grid;
  grid-template-columns: repeat(3, minmax(0, 1fr));
  gap: 16px;
  margin-bottom: 22px;
}

.stat-card {
  min-height: 120px;
  padding: 18px 18px 16px;
  border-radius: 16px;
  background: rgba(255, 255, 255, 0.02);
  border: 1px solid rgba(174, 183, 196, 0.08);
}

.stat-label {
  color: rgba(201, 214, 226, 0.8);
  font-size: 14px;
  margin-bottom: 14px;
}

.stat-value {
  color: #f7faff;
  font-size: clamp(1.8rem, 3vw, 2.6rem);
  line-height: 1;
  font-weight: 700;
  margin-bottom: 12px;
}

.stat-trend {
  font-size: 12px;
  font-weight: 600;
  display: inline-flex;
  align-items: center;
  gap: 6px;
}

.stat-trend.down {
  color: #f99aa5;
}

.stat-trend.healthy {
  color: #7ae7b3;
}

.chart-panel,
.incident-panel {
  background: rgba(255, 255, 255, 0.015);
  border: 1px solid rgba(168, 176, 195, 0.08);
  border-radius: 18px;
  padding: 14px 18px 16px;
}

.chart-panel {
  margin-bottom: 22px;
}

.panel-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 8px;
  color: #edf2ff;
  font-size: 14px;
  font-weight: 600;
}

.muted {
  color: rgba(184, 194, 209, 0.75);
  font-size: 12px;
}

.chart-wrap {
  position: relative;
  height: 210px;
  border-radius: 14px;
  background: linear-gradient(180deg, rgba(15, 18, 29, 0.15), rgba(10, 12, 19, 0.15));
  overflow: hidden;
}

.chart-wrap svg {
  width: 100%;
  height: 100%;
  display: block;
}

.chart-grid line {
  stroke: rgba(158, 171, 196, 0.12);
  stroke-width: 1;
}

.chart-area {
  fill: url(#chartGlow);
  opacity: 0.95;
}

.chart-line {
  fill: none;
  stroke: #69a7ff;
  stroke-width: 3;
  stroke-linecap: round;
}

.chart-tag {
  position: absolute;
  right: 16px;
  top: 12px;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  min-width: 94px;
  height: 32px;
  padding: 0 10px;
  border-radius: 9px;
  background: rgba(72, 103, 181, 0.7);
  border: 1px solid rgba(124, 162, 255, 0.45);
  box-shadow: 0 10px 24px rgba(51, 105, 214, 0.28);
  color: #edf4ff;
  font-size: 12px;
  font-weight: 600;
}

.incident-panel {
  padding-top: 8px;
}

.panel-header.compact {
  margin-bottom: 14px;
}

.panel-header button {
  border: 0;
  background: transparent;
  color: rgba(202, 216, 234, 0.8);
  font-size: 12px;
  cursor: pointer;
}

.incident-row {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
  padding: 14px 0;
  border-top: 1px solid rgba(255, 255, 255, 0.06);
}

.incident-row:first-of-type {
  border-top: none;
  padding-top: 8px;
}

.incident-main {
  display: flex;
  align-items: center;
  gap: 12px;
}

.incident-copy {
  display: flex;
  flex-direction: column;
}

.incident-name {
  color: #eef3fb;
  font-size: 14px;
  font-weight: 600;
}

.incident-meta {
  color: rgba(172, 182, 198, 0.8);
  font-size: 11px;
  margin-top: 2px;
}

.incident-dot {
  width: 10px;
  height: 10px;
  border-radius: 50%;
  box-shadow: 0 0 16px currentColor;
}

.incident-dot.pink {
  background: #ff8aa1;
  color: #ff8aa1;
}

.incident-dot.amber {
  background: #ffc166;
  color: #ffc166;
}

.incident-state {
  color: rgba(205, 214, 228, 0.8);
  font-size: 12px;
  font-weight: 600;
}

.empty-state {
  color: rgba(203, 217, 234, 0.7);
  padding: 14px 0 8px;
  font-size: 12px;
}

@media (max-width: 768px) {
  .dashboard-content {
    padding: 20px 14px 18px;
  }

  .stats-grid {
    grid-template-columns: 1fr;
  }

  .workspace-tag {
    letter-spacing: 0.12em;
    font-size: 10px;
  }

  .chart-tag {
    right: 10px;
    top: 10px;
  }

  .incident-row {
    flex-direction: column;
    align-items: flex-start;
  }
}
</style>
