<template>
    <div>
      <ColumnList :list="list"/>
    </div>
</template>

<script lang="ts">
import { defineComponent, computed } from 'vue'
import ColumnList from '../components/ColumnList.vue'
import { useStore } from 'vuex'
import { GlobalDataProps } from '@/store'

export default defineComponent({
  name: 'Home',
  components: {
    ColumnList
  },
  props: {
  },
  setup () {
    const store = useStore<GlobalDataProps>()
    // 因为store是响应式的，所以从计算属性中读取最方便
    const list = computed(() => {
      return store.state.columns
    })
    return {
      list: list
    }
  }
})
</script>

<style lang="css">
</style>
