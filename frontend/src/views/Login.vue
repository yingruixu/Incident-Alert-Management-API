<template>
  <div class="login-container">
    <div class="login-grid"></div>
    <div class="login-orb orb-blue"></div>
    <div class="login-orb orb-violet"></div>
    <header class="login-nav">
      <a class="brand" href="/" aria-label="Signal home"><span class="brand-symbol">S</span><span>signal<span
            class="brand-dot">.</span></span></a>
      <a class="back-link" href="/">Back to homepage <span>↗</span></a>
    </header>

    <main class="login-main">
      <section class="login-card glass-card">
        <div class="card-header">
          <div class="login-mark">S</div>
          <span class="eyebrow">SIGNAL WORKSPACE</span>
          <h1>Welcome back.</h1>
          <p>Sign in to manage incidents and monitor your services.</p>
        </div>

        <el-form :model="form" @submit.prevent="handleLogin" class="login-form">
          <el-form-item>
            <el-input v-model="form.username" placeholder="Username or email" />
          </el-form-item>

          <el-form-item>
            <el-input v-model="form.password" type="password" placeholder="Password" @keyup.enter="handleLogin" />
          </el-form-item>

          <el-form-item>
            <el-button type="primary" :loading="loading" @click="handleLogin">
              {{ loading ? 'Signing in...' : 'Sign in' }} <span>↗</span>
            </el-button>
          </el-form-item>
        </el-form>

        <el-alert v-if="error" :title="error" type="error" show-icon />
        <p class="secure-note"><span>✦</span> Secure access to your operations workspace</p>
      </section>
    </main>
    <footer class="login-footer">© 2026 Signal, Inc. <span>·</span> Incident operations workspace</footer>
  </div>
</template>
<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import { ElMessage } from 'element-plus'
const router = useRouter()
const form = ref({ username: '', password: '' })
const loading = ref(false)
const error = ref('')
const handleLogin = async () => {
  if (!form.value.username || !form.value.password) {
    error.value = 'Please enter username and password'
    return
  }
  loading.value = true
  error.value = ''
  try {
    const response = await axios.post('/auth/login', {
      username: form.value.username,
      password: form.value.password
    })
    localStorage.setItem('token', response.data.token)
    localStorage.setItem('username', response.data.username)
    localStorage.setItem('role', response.data.role)

    ElMessage.success('Login successful!')
    router.push('/dashboard')

  } catch (err) {
    error.value = 'Login failed: ' + (err.response?.data || err.message)
  } finally {
    loading.value = false
  }
}
</script>
<style scoped>
.login-container {
  --blue: #5b8cff;
  --violet: #a855f7;
  --cyan: #22d3ee;
  position: relative;
  overflow: hidden;
  min-height: 100vh;
  color: #f4f5f7;
  background: #0a0a0b;
  font-family: 'Manrope', 'Segoe UI', sans-serif;
}

.login-grid {
  position: absolute;
  inset: 0;
  opacity: .3;
  background-image: linear-gradient(rgba(255, 255, 255, .035) 1px, transparent 1px), linear-gradient(90deg, rgba(255, 255, 255, .035) 1px, transparent 1px);
  background-size: 80px 80px;
  mask-image: linear-gradient(to bottom, black, transparent 85%);
}

.login-orb {
  position: absolute;
  width: 420px;
  height: 420px;
  border-radius: 50%;
  opacity: .22;
  filter: blur(95px);
  pointer-events: none;
}

.orb-blue {
  top: 6%;
  left: 17%;
  background: #315ee9;
}

.orb-violet {
  right: 10%;
  bottom: 4%;
  background: #6d2ca8;
}

.login-nav {
  position: relative;
  z-index: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: min(1200px, calc(100% - 64px));
  height: 88px;
  margin: 0 auto;
  border-bottom: 1px solid rgba(255, 255, 255, .08);
}

.brand {
  display: inline-flex;
  align-items: center;
  gap: 10px;
  color: #f4f5f7;
  font-size: 17px;
  font-weight: 800;
  letter-spacing: -.06em;
  text-decoration: none;
}

.brand-symbol {
  display: grid;
  place-items: center;
  width: 28px;
  height: 28px;
  border: 1px solid rgba(255, 255, 255, .3);
  border-radius: 8px;
  background: linear-gradient(145deg, rgba(91, 140, 255, .9), rgba(168, 85, 247, .75));
  font-size: 13px;
  letter-spacing: 0;
}

.brand-dot {
  color: var(--cyan);
}

.back-link {
  color: #989aa5;
  font-size: 11px;
  text-decoration: none;
}

.back-link:hover {
  color: #fff;
}

.back-link span {
  margin-left: 6px;
  color: var(--cyan);
  font-size: 14px;
}

.login-main {
  position: relative;
  z-index: 1;
  display: grid;
  place-items: center;
  min-height: calc(100vh - 148px);
  padding: 60px 20px;
}

.glass-card {
  border: 1px solid rgba(255, 255, 255, .14);
  background: linear-gradient(135deg, rgba(255, 255, 255, .095), rgba(255, 255, 255, .035));
  box-shadow: 0 30px 80px rgba(0, 0, 0, .36), inset 0 1px rgba(255, 255, 255, .09);
  backdrop-filter: blur(18px);
}

.login-card {
  width: min(430px, 100%);
  padding: 38px 38px 28px;
  border-radius: 20px;
}

.card-header {
  text-align: center;
}

.login-mark {
  display: grid;
  place-items: center;
  width: 46px;
  height: 46px;
  margin: 0 auto 20px;
  border-radius: 13px;
  color: #fff;
  background: linear-gradient(140deg, var(--blue), var(--violet));
  box-shadow: 0 8px 26px rgba(91, 140, 255, .28);
  font-size: 18px;
  font-weight: 800;
}

.eyebrow {
  color: #90929f;
  font: 500 9px 'DM Mono', monospace;
  letter-spacing: .16em;
}

.card-header h1 {
  margin: 14px 0 9px;
  color: #f6f6f8;
  font-size: 31px;
  letter-spacing: -.07em;
}

.card-header p {
  margin: 0 auto;
  color: #8d8f9b;
  font-size: 12px;
  line-height: 1.6;
}

.login-form {
  margin-top: 30px;
}

.login-form :deep(.el-form-item) {
  margin-bottom: 15px;
}

.login-form :deep(.el-input__wrapper) {
  min-height: 48px;
  border: 1px solid rgba(255, 255, 255, .12);
  border-radius: 9px;
  box-shadow: none;
  background: rgba(0, 0, 0, .18);
}

.login-form :deep(.el-input__wrapper.is-focus) {
  border-color: rgba(91, 140, 255, .75);
  box-shadow: 0 0 0 3px rgba(91, 140, 255, .12);
}

.login-form :deep(.el-input__inner) {
  color: #f4f5f7;
  font-size: 12px;
}

.login-form :deep(.el-input__inner::placeholder) {
  color: #727580;
}

.login-form :deep(.el-button) {
  width: 100%;
  min-height: 48px;
  border: 0;
  border-radius: 9px;
  color: #fff;
  background: linear-gradient(105deg, var(--blue), var(--violet) 56%, var(--cyan));
  background-size: 180% 100%;
  box-shadow: 0 10px 25px rgba(91, 140, 255, .2);
  font: 700 12px 'Manrope', sans-serif;
  transition: transform .2s, box-shadow .2s;
}

.login-form :deep(.el-button:hover) {
  border: 0;
  color: #fff;
  transform: translateY(-2px);
  box-shadow: 0 14px 30px rgba(91, 140, 255, .3);
}

.login-form :deep(.el-button span) {
  margin-left: 8px;
  font-size: 15px;
}

.login-card :deep(.el-alert) {
  border: 1px solid rgba(240, 112, 141, .3);
  border-radius: 8px;
  background: rgba(240, 112, 141, .1);
}

.login-card :deep(.el-alert__title) {
  color: #f0a4b5;
  font-size: 11px;
}

.secure-note {
  margin: 22px 0 0;
  color: #686b76;
  text-align: center;
  font-size: 10px;
}

.secure-note span {
  margin-right: 5px;
  color: var(--cyan);
}

.login-footer {
  position: relative;
  z-index: 1;
  color: #656772;
  text-align: center;
  font-size: 10px;
}

.login-footer span {
  margin: 0 5px;
  color: #444650;
}

@media (max-width: 620px) {
  .login-nav {
    width: calc(100% - 36px);
    height: 72px;
  }

  .back-link {
    font-size: 0;
  }

  .back-link span {
    font-size: 14px;
  }

  .login-main {
    min-height: calc(100vh - 130px);
    padding: 35px 18px 45px;
  }

  .login-card {
    padding: 31px 23px 24px;
  }

  .login-footer {
    padding: 0 18px 20px;
    line-height: 1.6;
  }
}
</style>