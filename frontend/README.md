# Vue 3 + Vite

## Local frontend-only mode

When the backend and database are unavailable, `npm run dev` uses `.env.development` to skip the login guard and open the application routes directly. API-backed data will still require the backend.

To restore the login guard locally, set `VITE_SKIP_AUTH=false` in `.env.development` and restart Vite. Production builds do not skip authentication unless `VITE_SKIP_AUTH=true` is explicitly provided during the build.

This template should help get you started developing with Vue 3 in Vite. The template uses Vue 3 `<script setup>` SFCs, check out the [script setup docs](https://v3.vuejs.org/api/sfc-script-setup.html#sfc-script-setup) to learn more.

Learn more about IDE Support for Vue in the [Vue Docs Scaling up Guide](https://vuejs.org/guide/scaling-up/tooling.html#ide-support).
