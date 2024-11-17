<template>
  <div class="container">
    <GlobalHeader :user="currentUser"/>
    <router-view></router-view>
    <Footer></Footer>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import GlobalHeader, { UserProps } from './components/GlobalHeader.vue'

import 'bootstrap/dist/css/bootstrap.min.css'
import Footer from './components/Footer.vue'

const currentUser:UserProps = {
  isLogin: false,
  name: '王志远',
  id: 1
}

export default defineComponent({
  name: 'App',
  components: {
    GlobalHeader,
    Footer
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
