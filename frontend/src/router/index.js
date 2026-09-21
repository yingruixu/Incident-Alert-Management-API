import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Dashboard from '../views/Dashboard.vue'
import Incidents from '../views/Incidents.vue'
import Alerts from '../views/Alerts.vue'
import Monitoring from '../views/Monitoring.vue'
import Settings from '../views/Settings.vue'
import Home from '../views/Home.vue'

const routes = [
  { path: '/', name: 'Home', component: Home },
  { path: '/login', name: 'Login', component: Login },
  { path: '/dashboard', name: 'Dashboard', component: Dashboard },
  { path: '/incidents', name: 'Incidents', component: Incidents },
  { path: '/alerts', name: 'Alerts', component: Alerts },
  { path: '/monitoring', name: 'Monitoring', component: Monitoring },
  { path: '/settings', name: 'Settings', component: Settings }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 路由守卫 - 检查登录
router.beforeEach((to) => {
  const skipAuth = import.meta.env.DEV && import.meta.env.VITE_SKIP_AUTH === 'true'
  const token = localStorage.getItem('token')

  // Keep authentication enforced outside local development.
  if (!skipAuth && to.path !== '/' && to.path !== '/login' && !token) {
    return '/login'
  }
  return true
})

export default router