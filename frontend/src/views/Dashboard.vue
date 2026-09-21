import { ref, computed, onMounted } from 'vue'
import { incidentsApi } from '../api'

<template>
  <div class="dashboard">
    <!-- 统计卡片 -->
    <el-row :gutter="20">
      <el-col :xs="24" :sm="12" :md="6" v-for="(stat, index) in statsData" :key="index">
        <el-card class="stat-card" shadow="hover">
          <div class="stat-content">
            <div class="stat-icon" :style="{ background: stat.color }">
              {{ stat.icon }}
            </div>
            <div class="stat-info">
              <div class="stat-value">{{ stat.value }}</div>
              <div class="stat-label">{{ stat.label }}</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <!-- 图表区域 -->
    <el-row :gutter="20" style="margin-top: 20px">
      <el-col :span="12">
        <el-card>
          <template #header>
            <span>📈 Incident Trend</span>
          </template>
          <div style="height: 300px">
            <canvas ref="trendChartRef"></canvas>
          </div>
        </el-card>
      </el-col>

      <el-col :span="12">
        <el-card>
          <template #header>
            <span>🍩 Severity Distribution</span>
          </template>
          <div style="height: 300px">
            <canvas ref="severityChartRef"></canvas>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import { Chart, registerables } from 'chart.js'
import { incidentsApi } from '../api'
Chart.register(...registerables)
const trendChartRef = ref(null)
const severityChartRef = ref(null)
const statsData = ref([
  { icon: '🔴', value: 0, label: 'Open Incidents', color: '#409eff' },
  { icon: '🟢', value: 0, label: 'Closed Today', color: '#67c23a' },
  { icon: '⚠️', value: 24, label: 'Active Alerts', color: '#e6a23c' },
  { icon: '🔥', value: '2.5h', label: 'Avg Resolution', color: '#f56c6c' }
])

async function loadStats() {
  try {
    const incidents = await incidentsApi.getAll()
    const openCount = incidents.filter(i => i.status === 'OPEN').length
    statsData.value[0].value = openCount
  } catch (e) {
    console.error('Failed to load stats:', e)
  }
}

onMounted(() => {
  loadStats()
  initCharts()
})
function initCharts() {
  // 趋势图
  new Chart(trendChartRef.value, {
    type: 'line',
    data: {
      labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
      datasets: [{
        label: 'Incidents',
        data: [5, 8, 3, 12, 6, 4, 7],
        borderColor: '#409eff',
        backgroundColor: 'rgba(64, 158, 255, 0.1)',
        tension: 0.4,
        fill: true
      }]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: { legend: { display: false } }
    }
  })
  // 饼图
  new Chart(severityChartRef.value, {
    type: 'doughnut',
    data: {
      labels: ['Critical', 'High', 'Medium', 'Low'],
      datasets: [{
        data: [3, 8, 15, 20],
        backgroundColor: ['#f56c6c', '#e6a23c', '#409eff', '#67c23a']
      }]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false
    }
  })
}
</script>
<style scoped>
.dashboard {
  padding: 4px 0;
}

.stat-card {
  height: 100px;
  margin-bottom: 20px;
}

.stat-content {
  display: flex;
  align-items: center;
  height: 100%;
}

.stat-icon {
  width: 50px;
  height: 50px;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  margin-right: 15px;
  flex-shrink: 0;
}

.stat-info {
  flex: 1;
}

.stat-value {
  font-size: 24px;
  font-weight: bold;
  color: #f2f3f6;
}

.stat-label {
  font-size: 13px;
  color: #858894;
  margin-top: 4px;
}

.dashboard :deep(.el-card__header) {
  padding: 16px 20px;
}

.dashboard :deep(.el-card__body) {
  padding: 20px;
}

.dashboard :deep(.el-card__header span) {
  color: #dfe1e7;
  font-size: 13px;
}

.dashboard :deep(canvas) {
  filter: saturate(.9);
}

@media (max-width: 768px) {
  .dashboard :deep(.el-row) {
    margin-left: 0 !important;
    margin-right: 0 !important;
  }

  .dashboard :deep(.el-col) {
    padding-left: 0 !important;
    padding-right: 0 !important;
  }
}
</style>