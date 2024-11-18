import { createWebHistory, createRouter } from 'vue-router'
import ColumnDetail from './views/ColumnDetail.vue'
import Home from './views/Home.vue'
import Login from './views/Login.vue'
const routerHistory = createWebHistory()

export const router = createRouter({
  history: routerHistory,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/column/:id',
      name: 'column',
      component: ColumnDetail
    }
  ]
})
