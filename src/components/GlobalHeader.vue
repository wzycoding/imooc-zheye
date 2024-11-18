<template>
  <nav class="navbar navbar-dark bg-primary justify-content-between mb-4 px-4">
    <a class="navbar-brand" href="#">者也专栏</a>
    <ul v-if="!user.isLogin" class="list-inline mb-0">
      <li class="list-inline-item"><router-link :to="`/login`" class="btn btn-outline-light my-2">登录</router-link></li>
      <li class="list-inline-item"><a href="#" class="btn btn-outline-light my-2">注册</a></li>
    </ul>
    <ul v-else class="list-inline mb-0">
      <li class="list-inline-item">
        <DropDown :title="`你好 ${user.name}`">
          <DropDownItem><router-link :to="`/create`" class="dropdown-item" href="#">新建文章</router-link></DropDownItem>
          <DropDownItem disabled><a class="dropdown-item" href="#">编辑资料</a></DropDownItem>
          <DropDownItem><a class="dropdown-item" href="#">退出登录</a></DropDownItem>
        </DropDown>
      </li>
    </ul>
  </nav>
</template>

<script lang="ts">
import { defineComponent, PropType } from 'vue'
import DropDown from './DropDown.vue'
import DropDownItem from './DropDownItem.vue'
import { UserProps } from '@/testData'

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
  }
})
</script>

<style lang="css">
</style>
