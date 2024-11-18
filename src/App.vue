<template>
  <div class="container">
    <GlobalHeader :user="currentUser"/>
    <router-view></router-view>
    <Footer></Footer>
  </div>
</template>

<script lang="ts">
import { defineComponent, computed } from 'vue'
import GlobalHeader from './components/GlobalHeader.vue'
import { useStore } from 'vuex'
import store from './store'
import 'bootstrap/dist/css/bootstrap.min.css'
import Footer from './components/Footer.vue'
import { currentUser } from './testData'

export default defineComponent({
  name: 'App',
  components: {
    GlobalHeader,
    Footer
  },
  setup () {
    const store = useStore()
    const currentUser = computed(
      () => {
        return store.state.user
      }
    )
    return {
      currentUser
    }
  }
})
</script>

<style>
</style>
