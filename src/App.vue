<template>
  <div class="container">
    <GlobalHeader :user="currentUser"/>
    <router-view></router-view>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import GlobalHeader, { UserProps } from './components/GlobalHeader.vue'

import 'bootstrap/dist/css/bootstrap.min.css'

const currentUser:UserProps = {
  isLogin: true,
  name: '王志远',
  id: 1
}

export default defineComponent({
  name: 'App',
  components: {
    GlobalHeader
  },
  setup () {
    return {
      currentUser
    }
  }
})
</script>

<style>
</style>
