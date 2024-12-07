<template>
    <div class="home-page container-md">
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
      <h4 class="font-weight-bold text-center mb-4">发现精彩</h4>
      <ColumnList :list="list"/>
      <div class="d-flex text-center">
        <button
          class="btn btn-outline-primary mt-2 mb-5 mx-auto btn-block w-25 load-more"
        >
          加载更多
        </button>
      </div>
    </div>
</template>

<script lang="ts">
import { defineComponent, computed, onMounted, ref } from 'vue'
import ColumnList from '../components/ColumnList.vue'
import { useStore } from 'vuex'
import { GlobalDataProps } from '@/store'
import useLoadMore from '@/hooks/useLoadMore'

export default defineComponent({
  name: 'Home',
  components: {
    ColumnList
  },
  props: {
  },
  setup () {
    const store = useStore<GlobalDataProps>()
    // const total = computed(() => store.state.columns.total)
    // const currentPage = computed(() => store.state.columns.currentPage)

    // 因为store是响应式的，所以从计算属性中读取最方便
    const list = computed(() => {
      return store.state.columns
    })
    onMounted(() => {
      store.dispatch('fetchColumns', { pageSize: 3 })
    })
    // const { loadMorePage, isLastPage } = useLoadMore(
    //   'fetchColumns',
    //   total,
    //   {
    //     pageSize: 3,
    //     currentPage: currentPage.value ? currentPage.value + 1 : 2
    //   }
    // )
    return {
      list
      // isLastPage,
      // loadMorePage
    }
  }
})
</script>

<style scoped>
.load-more {
  margin-left: 50% !important;
  transform: translate3d(-50%, 0, 0);
}
</style>
