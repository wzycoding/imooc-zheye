import { createApp } from 'vue'

import App from './App.vue'
import store from './store'
import router from './router'
import axios from 'axios'

// 请求前缀
axios.defaults.baseURL = 'http://localhost:7001/api/'

// 拦截器
axios.interceptors.request.use(config => {
  // 旧的属性都保留，添加一个icode属性
  config.params = { ...config.params, icode: '1234' }
  store.commit('setLoading', true)
  return config
})

axios.interceptors.response.use(response => {
  console.log(response)
  // 获取 response 数据
  const data = response.data

  if (data.code !== '200') {
    store.commit('setError', { status: true, message: data.msg })
  }

  // 停止 loading 动画或标志
  store.commit('setLoading', false)

  // 返回响应数据
  return response
}, error => {
  // 在出现错误时同样停止 loading
  store.commit('setLoading', false)

  // 处理错误并向下传递
  return Promise.reject(error)
})
const app = createApp(App)
app.use(router)
app.use(store)
app.mount('#app')
