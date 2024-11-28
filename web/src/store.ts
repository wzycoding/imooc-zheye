import { Commit, createStore } from 'vuex'
import axios, { AxiosRequestConfig } from 'axios'
import { testPosts, PostProps, UserProps } from './testData'
export interface GlobalErrorProps {
  status: boolean;
  message?: string;
}
export interface ImageProps {
  id: number;
  url: string;
  createTime?: string;
  fitUrl: string;
  }
export interface ColumnProps {
  id: number;
  title: string;
  avatar?: ImageProps;
  description: string;
  }

export interface GlobalDataProps {
  columns: ColumnProps[];
  posts: PostProps[];
  user: UserProps;
  loading: boolean;
  token: string;
  error: GlobalErrorProps;
}

const asyncAndCommit = async (url: string, mutationName: string,
  commit:Commit, config: AxiosRequestConfig = { method: 'get' }, extraData?: any) => {
  const data = await axios(url, config)
  if (extraData) {
    commit(mutationName, { data, extraData })
  } else {
    commit(mutationName, data)
  }
  return data
}

/**
 * store中的数据并没有持久化一刷新或关闭浏览器就会丢失
 */
const store = createStore<GlobalDataProps>({
  state: {
    columns: [],
    posts: testPosts,
    loading: false,
    token: localStorage.getItem('token') || '',
    error: { status: false },
    user: { isLogin: false }
  },
  mutations: {
    createPost (state, newPost) {
      state.posts.push(newPost)
    },
    fetchColumns (state, rawData) {
      state.columns = rawData.data
    },
    setLoading (state, rawData) {
      state.loading = rawData
    },
    setError (state, e: GlobalErrorProps) {
      state.error = e
    },
    login (state, rawData) {
      // 新对象变成老对象，使用...展开符
      const { token } = rawData.data
      state.token = token
      localStorage.setItem('token', token)
      axios.defaults.headers.common.Authorization = `Bearer ${token}`
    }
  },
  actions: {
    // mutations，只能同步操作（破坏vuex的可追溯性），actions才能异步操作
    fetchColumns (context) {
      axios.get('/columns/').then(resp => {
        context.commit('fetchColumns', resp.data)
      })
    },
    login (context, payload) {
      return asyncAndCommit('/user/login', 'login', context.commit,
        { method: 'post', data: payload }
      )
    }
  },
  getters: {
    // // 替代重复的计算代码，可以用来过滤或者统计
    // biggerColumnLen (state) {
    //   return state.columns.filter(c => c.id > 2).length
    // },
    // 返回一个函数
    getColumnById: (state) => (id: number) => {
      return state.columns.find(c => c.id === id)
    },
    getPostsByCid: (state) => (cid: number) => {
      return state.posts.filter(p => p.columnId === cid)
    }
  }
})
export default store
