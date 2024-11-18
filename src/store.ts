import { createStore } from 'vuex'
import { testData, testPosts, ColumnProps, PostProps, UserProps } from './testData'

export interface GlobalDataProps {
    columns: ColumnProps[];
    posts: PostProps[];
    user: UserProps;
}
const store = createStore<GlobalDataProps>({
  state: {
    columns: testData,
    posts: testPosts,
    user: { isLogin: false }
  },
  mutations: {
    login (state) {
      // 新对象变成老对象，使用...展开符
      state.user = { ...state.user, isLogin: true, name: 'wzy1' }
    }
  }
})
export default store
