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
  return config
})

axios.get('/user/current').then(resp => {
  console.log(resp)
})
const app = createApp(App)
app.use(router)
app.use(store)
app.mount('#app')
