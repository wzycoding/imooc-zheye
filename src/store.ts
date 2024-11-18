import { createStore } from 'vuex'
import { testData, testPosts, ColumnProps, PostProps, UserProps } from './testData'

export interface GlobalDataProps {
    columns: ColumnProps[];
    posts: PostProps[];
    user: UserProps;
}

/**
 * store中的数据并没有持久化一刷新或关闭浏览器就会丢失
 */
const store = createStore<GlobalDataProps>({
  state: {
    columns: testData,
    posts: testPosts,
    user: { isLogin: true, name: 'wzy1', columnId: 1 }
  },
  mutations: {
    login (state) {
      // 新对象变成老对象，使用...展开符
      state.user = { ...state.user, isLogin: true, name: 'wzy1' }
    },
    createPost (state, newPost) {
      state.posts.push(newPost)
    }
  },
  getters: {
    // 替代重复的计算代码，可以用来过滤或者统计
    biggerColumnLen (state) {
      return state.columns.filter(c => c.id > 2).length
    },
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
