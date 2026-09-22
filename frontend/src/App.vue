<template>
  <div id="app" class="app-shell"
    :class="{ 'is-public': $route.path === '/' || $route.path === '/login', 'is-dashboard': $route.path === '/dashboard' }">
    <el-container class="app-container">
      <el-aside v-if="$route.path !== '/' && $route.path !== '/login'" width="248px" class="app-sidebar">
        <div class="logo">
          <div class="brand-mark">IM</div>
          <div><strong>Incident</strong><span>Operations center</span></div>
        </div>

        <el-menu :default-active="$route.path" router class="app-menu">
          <el-menu-item index="/dashboard">
            <span class="menu-icon">01</span><span>Dashboard</span>
          </el-menu-item>
          <el-menu-item index="/incidents">
            <span class="menu-icon">02</span><span>Incidents</span>
          </el-menu-item>
          <el-menu-item index="/alerts">
            <span class="menu-icon">03</span><span>Alerts</span>
          </el-menu-item>
          <el-menu-item index="/monitoring">
            <span class="menu-icon">04</span><span>Monitoring</span>
          </el-menu-item>
          <el-menu-item index="/settings">
            <span class="menu-icon">05</span><span>Settings</span>
          </el-menu-item>
        </el-menu>
        <div class="sidebar-status"><span class="status-dot"></span>
          <div><strong>All systems operational</strong><small>Updated just now</small></div>
        </div>
      </el-aside>

      <!-- 主内容 -->
      <el-container class="content-container">
        <el-header v-if="$route.path !== '/' && $route.path !== '/login'" class="app-header">
          <div class="breadcrumb"><span>Workspace</span><b>/</b><strong>{{ currentPage }}</strong></div>
          <div class="header-actions"><span class="live-indicator"><i></i> Live</span><el-divider
              direction="vertical" />
            <div class="user-profile"><span class="avatar">{{ userInitials }}</span><span><strong>{{ userName
            }}</strong><small>{{ userRole }}</small></span></div>
          </div>
        </el-header>

        <el-main class="app-main">
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script setup>
import { computed, onMounted, ref } from 'vue'
import { useRoute } from 'vue-router'

const $route = useRoute()

const pageNames = {
  '/dashboard': 'Dashboard',
  '/incidents': 'Incidents',
  '/alerts': 'Alerts',
  '/monitoring': 'Monitoring',
  '/settings': 'Settings'
}
const currentPage = computed(() => pageNames[$route.path] || 'Dashboard')

const userName = ref('Guest')
const userRole = ref('User')

const userInitials = computed(() => {
  const name = userName.value
  if (!name || name === 'Guest') return 'G'
  const parts = name.split(' ').filter(Boolean)
  if (parts.length >= 2) return (parts[0][0] + parts[1][0]).toUpperCase()
  return name.slice(0, 2).toUpperCase()
})

function loadUser() {
  const stored = localStorage.getItem('username')
  userName.value = stored || 'Guest'
  userRole.value = 'Administrator'
}

onMounted(() => {
  loadUser()
})
</script>
<style>
.app-container {
  min-height: 100vh;
}

.app-sidebar {
  position: relative;
  overflow: hidden;
  background: rgba(12, 13, 16, .94);
  color: #d9dbe2;
  display: flex;
  flex-direction: column;
  padding: 0 14px;
  border-right: 1px solid rgba(255, 255, 255, .1);
  box-shadow: 18px 0 55px rgba(0, 0, 0, .18);
}

.app-sidebar::before {
  position: absolute;
  inset: 0 0 auto;
  height: 280px;
  pointer-events: none;
  content: '';
  background: radial-gradient(circle at 20% 8%, rgba(91, 140, 255, .16), transparent 58%);
}

.logo {
  position: relative;
  z-index: 1;
  height: 92px;
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 0 12px;
  border-bottom: 1px solid rgba(255, 255, 255, .07);
}

.brand-mark,
.avatar {
  display: grid;
  place-items: center;
  font-weight: 800;
  letter-spacing: .04em;
}

.brand-mark {
  width: 38px;
  height: 38px;
  color: #fff;
  background: linear-gradient(140deg, #5b8cff, #a855f7);
  border-radius: 10px;
  font-size: 13px;
  box-shadow: 0 7px 22px rgba(91, 140, 255, .25);
}

.logo strong,
.logo span {
  display: block;
}

.logo strong {
  color: #f5f6f8;
  font-size: 17px;
}

.logo span {
  color: #777b87;
  font-size: 11px;
  margin-top: 2px;
}

.app-menu {
  position: relative;
  z-index: 1;
  border: 0;
  background: transparent;
  flex: 1;
  padding-top: 18px;
}

.app-menu .el-menu-item {
  height: 48px;
  line-height: 48px;
  color: #858894;
  border-radius: 10px;
  margin: 4px 0;
  font-size: 13px;
  font-weight: 600;
  transition: color .2s, background .2s, transform .2s;
}

.app-menu .el-menu-item:hover {
  background: rgba(255, 255, 255, .06);
  color: #f5f6f8;
  transform: translateX(2px);
}

.app-menu .el-menu-item.is-active {
  color: #fff;
  background: linear-gradient(100deg, rgba(91, 140, 255, .26), rgba(168, 85, 247, .12));
  box-shadow: inset 2px 0 #7b9fff, inset 0 1px rgba(255, 255, 255, .06);
}

.menu-icon {
  width: 28px;
  color: #555966;
  font-size: 10px;
  font-weight: 800;
  letter-spacing: .08em;
}

.is-active .menu-icon {
  color: #9db8ff;
}

.sidebar-status {
  position: relative;
  z-index: 1;
  display: flex;
  gap: 10px;
  align-items: flex-start;
  padding: 16px 10px 20px;
  border-top: 1px solid rgba(255, 255, 255, .08);
  color: #c3c6ce;
  font-size: 11px;
}

.sidebar-status strong,
.sidebar-status small,
.user-profile strong,
.user-profile small {
  display: block;
}

.sidebar-status small,
.user-profile small {
  color: #6e727e;
  font-size: 10px;
  margin-top: 3px;
}

.status-dot,
.live-indicator i {
  display: inline-block;
  width: 7px;
  height: 7px;
  border-radius: 50%;
  background: #22d3ee;
  margin-top: 3px;
  box-shadow: 0 0 10px rgba(34, 211, 238, .8);
}

.content-container {
  min-width: 0;
}

.app-header {
  height: 74px;
  border-bottom: 1px solid #e2e9e6;
  background: #fff;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 34px;
}

.breadcrumb {
  display: flex;
  align-items: center;
  gap: 10px;
  color: #9aa9a6;
  font-size: 12px;
}

.breadcrumb b {
  color: #d0dad7;
  font-weight: 400;
}

.breadcrumb strong {
  color: #18383d;
  font-size: 14px;
}

.header-actions {
  display: flex;
  align-items: center;
  gap: 16px;
}

.live-indicator {
  color: #66817b;
  font-size: 11px;
  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: .08em;
}

.live-indicator i {
  margin: 0 7px 1px 0;
  width: 6px;
  height: 6px;
}

.user-profile {
  display: flex;
  align-items: center;
  gap: 9px;
  color: #18383d;
  font-size: 12px;
}

.avatar {
  width: 32px;
  height: 32px;
  border-radius: 50%;
  background: #e9f2ed;
  color: #33736c;
  font-size: 10px;
}

.app-main {
  background: #f4f7f5;
  padding: 30px 34px 44px;
}

.is-dashboard .app-main {
  background: #0f141d;
  padding: 18px 18px 22px;
}

.is-dashboard .app-header {
  background: rgba(10, 13, 18, 0.9);
  border-bottom-color: rgba(255, 255, 255, 0.06);
}

.is-dashboard .breadcrumb,
.is-dashboard .live-indicator,
.is-dashboard .user-profile,
.is-dashboard .breadcrumb strong {
  color: rgba(220, 226, 236, 0.8);
}

.is-public .app-main {
  padding: 0;
}

@media (max-width: 768px) {
  .app-sidebar {
    width: 72px !important;
    padding: 0 8px;
  }

  .logo {
    justify-content: center;
    padding: 0;
  }

  .logo>div:not(.brand-mark),
  .app-menu .el-menu-item span:not(.menu-icon),
  .sidebar-status {
    display: none;
  }

  .app-menu .el-menu-item {
    justify-content: center;
    padding: 0;
  }

  .menu-icon {
    width: auto;
  }

  .app-header {
    padding: 0 18px;
  }

  .live-indicator,
  .app-header .el-divider {
    display: none;
  }

  .app-main {
    padding: 20px 16px 30px;
  }
}
</style>