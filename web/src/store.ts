import { Commit, createStore } from 'vuex'
import axios, { AxiosRequestConfig } from 'axios'

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

export interface PostProps {
  id: number;
  title: string;
  content: string;
  image?: ImageProps;
  createdAt: string;
  columnId: number;
}

export interface UserProps {
  isLogin: boolean;
  nickname?: string;
  id?: number;
  columnId?: number
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
  const { data } = await axios(url, config)
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
    posts: [],
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
    fetchColumn (state, rawData) {
      state.columns = [rawData.data]
    },
    fetchPost (state, rawData) {
      state.posts = rawData.data
    },
    setLoading (state, rawData) {
      state.loading = rawData
    },
    setError (state, e: GlobalErrorProps) {
      state.error = e
    },
    fetchCurrentUser (state, rawData) {
      state.user = { isLogin: true, ...rawData.data }
    },
    login (state, rawData) {
      // 新对象变成老对象，使用...展开符
      const { data } = rawData
      state.token = data.token
      localStorage.setItem('token', data.token)
      axios.defaults.headers.common.Authorization = data.token
    },
    logout (state) {
      state.user = { isLogin: false }
      localStorage.removeItem('token')
    }
  },
  actions: {
    // mutations，只能同步操作（破坏vuex的可追溯性），actions才能异步操作
    fetchColumns (context) {
      return asyncAndCommit('/columns/', 'fetchColumns', context.commit)
    },
    fetchColumn ({ commit }, cid) {
      return asyncAndCommit(`columns/${cid}`, 'fetchColumn', commit)
    },
    fetchPost ({ commit }, cid) {
      return asyncAndCommit(`columns/${cid}/posts`, 'fetchPost', commit)
    },
    login (context, payload) {
      return asyncAndCommit('/user/login', 'login', context.commit,
        { method: 'post', data: payload }
      )
    },
    fetchCurrentUser ({ commit }) {
      return asyncAndCommit('/user/current', 'fetchCurrentUser', commit)
    },
    loginAndFetch ({ dispatch }, loginData) {
      return dispatch('login', loginData).then(() => {
        return dispatch('fetchCurrentUser')
      })
    }
  },
  getters: {
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
