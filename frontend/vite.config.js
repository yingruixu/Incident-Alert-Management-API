import { defineConfig, loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig(({ mode }) => {
  const env = loadEnv(mode, process.cwd(), '')
  return {
    plugins: [vue()],
    server: {
      port: 3000,
      proxy: {
        '/api': {
          target: 'http://localhost:8080',
          changeOrigin: true
        },
        '/auth': {
          target: 'http://localhost:8080',
          changeOrigin: true
        },
        '/grafana': {
          target: env.VITE_GRAFANA_PROXY_TARGET || 'http://localhost:3002',
          changeOrigin: true,
          ws: true,
          rewrite: (path) => path.replace(/^\/grafana/, '')
        }
      }
    }
  }
})