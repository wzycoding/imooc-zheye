<template>
  <div class="container">
    <GlobalHeader :user="currentUser"/>
    <loader v-if="isLoading" text="拼命加载中..."></loader>
    <router-view></router-view>
    <Footer></Footer>
  </div>
</template>

<script lang="ts">
import { defineComponent, computed, watch } from 'vue'
import GlobalHeader from './components/GlobalHeader.vue'
import { useStore } from 'vuex'
import 'bootstrap/dist/css/bootstrap.min.css'
import Footer from './components/Footer.vue'
import createMessage from './components/createMessage'
import Loader from './components/Loader.vue'
export default defineComponent({
  name: 'App',
  components: {
    GlobalHeader,
    Footer,
    Loader
  },
  setup () {
    const store = useStore()
    const currentUser = computed(
      () => {
        return store.state.user
      }
    )
    const isLoading = computed(() => store.state.loading)
    const error = computed(() => store.state.error)

    watch(() => error.value.status, () => {
      const { status, message } = error.value
      if (status && message) {
        createMessage(message, 'error')
      }
    })
    return {
      currentUser,
      isLoading,
      error
    }
  }
})
</script>

<style scoped>
</style>
