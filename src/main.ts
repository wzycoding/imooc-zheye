import { createApp } from 'vue'
import { createWebHistory, createRouter } from 'vue-router'
import ColumnDetail from './components/ColumnDetail.vue'
import App from './App.vue'
const routerHistory = createWebHistory()
const router = createRouter({
  history: routerHistory,
  routes: [
    {
      path: '/column',
      name: 'column',
      component: ColumnDetail
    }
  ]
})

const app = createApp(App)
app.use(router)
app.mount('#app')
