<template>
  <div class="column-detail-page w-75 mx-auto">
    <div class="column-info row mb-4 border-bottom pb-4 align-items-center" v-if="column">
      <div class="col-3 text-center">
        <img :src="column.avatar.url && column.avatar.url" :alt="column.title" class="rounded-circle border w-100"/>
      </div>
      <div class="col-9">
        <h4>{{column.title}}</h4>
        <p class="text-muted">{{column.description}}</p>
      </div>
    </div>
    <PostList :list="list"></PostList>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { useStore } from 'vuex'
import PostList from '../components/PostList.vue'
import { GlobalDataProps } from '@/store'

export default defineComponent({
  name: 'ColumnDetail',
  props: {
  },
  components: {
    PostList
  },
  setup () {
    const route = useRoute()
    const store = useStore<GlobalDataProps>()
    // 这里要转成number类型
    const currentId = +route.params.id
    onMounted(() => {
      store.dispatch('fetchColumn', currentId)
      store.dispatch('fetchColumn', currentId)
      store.dispatch('fetchPost', currentId)
    })
    const column = computed(() => store.getters.getColumnById(currentId))
    const list = computed(() => store.getters.getPostsByCid(currentId))

    return {
      column,
      list
    }
  }
})
</script>

<style lang="css">
</style>
