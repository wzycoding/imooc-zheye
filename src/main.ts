import { createApp } from 'vue'
import { createWebHistory, createRouter } from 'vue-router'
import { createStore } from 'vuex'
import ColumnDetail from './views/ColumnDetail.vue'
import Home from './views/Home.vue'
import App from './App.vue'
import Login from './views/Login.vue'

const store = createStore({
  state: {
    count: 0
  },
  mutations: {
    add (state) {
      state.count++
    }
  }
})
console.log(store.state.count)
store.commit('add')
console.log(store.state.count)
const routerHistory = createWebHistory()
const router = createRouter({
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

const app = createApp(App)
app.use(router)
app.mount('#app')
