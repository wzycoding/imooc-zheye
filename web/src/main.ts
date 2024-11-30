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
  store.commit('setError', { status: false, message: '' })
  store.commit('setLoading', true)
  // // 返回一个 Promise，延迟 2 秒后继续
  // return new Promise(resolve => {
  //   setTimeout(() => {
  //     // 继续请求流程
  //     resolve(config)
  //   }, 1000)
  // })
  return config
})

axios.interceptors.response.use(response => {
  // 获取 response 数据
  const data = response.data
  if (data.code !== 200) {
    // 停止 loading 动画或标志
    store.commit('setLoading', false)
    store.commit('setError', { status: true, message: data.msg })
    // 抛出一个错误，使得请求的 promise 被拒绝
    return Promise.reject(new Error(data.msg || '请求失败'))
  }
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
