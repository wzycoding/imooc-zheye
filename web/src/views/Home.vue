<template>
    <div class="home-page">
      <section class="py-5 text-center container">
        <div class="row py-lg-5">
          <div class="col-lg-6 col-md-8 mx-auto">
            <img src="../assets/callout.svg" alt="callout" class="w-50"/>
            <h2 class="font-weight-light">随心写作，自由表达</h2>
            <p>
              <router-link :to="{name: 'create'}" class="btn btn-primary my-2">开始写文章</router-link>
            </p>
          </div>
        </div>
      </section>
      <h4 class="font-weight-bold text-center">发现精彩</h4>
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
    const biggerColumnLen = computed(() => store.getters.biggerColumnLen)
    return {
      list: list,
      biggerColumnLen
    }
  }
})
</script>

<style lang="css">
</style>
