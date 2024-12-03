<template>
   <div class="post-detail-page mx-auto w-690" v-if="postDetail">
    <div v-if="postDetail.image">
      <img :src="postDetail.image.url && postDetail.image.url" :alt="postDetail.title" class="rounded-circle border w-100"/>
    </div>
    <div>
      <p>{{postDetail.title}}</p>
    </div>
    <div>
      <div>UserProfile</div>
      <div>时间</div>
    </div>
    <div>
      {{postDetail.content}}
    </div>
  </div>
</template>

<script lang="ts">
import { GlobalDataProps, PostProps } from '@/store'
import { computed, defineComponent, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { useStore } from 'vuex'

export default defineComponent({
  name: 'PostDetail',
  props: {
  },
  setup () {
    const route = useRoute()
    const store = useStore<GlobalDataProps>()
    const currentPostId = +route.params.id
    onMounted(() => {
      console.log(currentPostId)
      store.dispatch('fetchPostDetial', currentPostId)
    })

    const postDetail = computed<PostProps>(() => store.getters.getPostDetail(currentPostId))
    return {
      postDetail
    }
  }
})
</script>

<style scoped>
.w-690 {
  width: 690px;
}
</style>
