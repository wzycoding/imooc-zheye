<template>
  <nav class="navbar-dark bg-primary justify-content-between mb-4 px-4">
    <div class="w-75 mx-auto navbar">
      <a href="/" class="navbar-brand text-white">者也专栏</a>
      <ul v-if="!user.isLogin" class="list-inline mb-0">
        <li class="list-inline-item"><router-link :to="`/login`" class="btn btn-outline-light my-2">登录</router-link></li>
        <li class="list-inline-item"><router-link :to="`/signup`" class="btn btn-outline-light my-2">注册</router-link></li>
      </ul>
      <ul v-else class="list-inline mb-0">
        <li class="list-inline-item">
          <DropDown :title="`你好 ${user.nickname}`">
            <DropDownItem><router-link :to="`/create`" class="dropdown-item" href="#">新建文章</router-link></DropDownItem>
            <DropDownItem><router-link :to="`/column/${user.columnId}`" class="dropdown-item">我的专栏</router-link></DropDownItem>
            <DropDownItem><a class="dropdown-item" href="#">编辑资料</a></DropDownItem>
            <DropDownItem><a class="dropdown-item" @click="onLogoutClick" href="#">退出登录</a></DropDownItem>
          </DropDown>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script lang="ts">
import { defineComponent, PropType } from 'vue'
import DropDown from './DropDown.vue'
import DropDownItem from './DropDownItem.vue'
import { UserProps, GlobalDataProps } from '@/store'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default defineComponent({
  name: 'GlobalHeader',
  components: {
    DropDown,
    DropDownItem
  },
  props: {
    user: {
      type: Object as PropType<UserProps>,
      required: true
    }
  },
  setup () {
    const store = useStore<GlobalDataProps>()
    const router = useRouter()
    const onLogoutClick = () => {
      store.commit('logout')
      setTimeout(() => {
        router.push('/')
      }, 2000)
    }
    return {
      onLogoutClick
    }
  }
})
</script>

<style lang="css">
</style>
