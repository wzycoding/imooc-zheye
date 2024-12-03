<template>
   <div class="post-detail-page mx-auto w-690" v-if="postDetail">
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><router-link :to="`/`">首页</router-link></li>
        <li class="breadcrumb-item"><router-link :to="`/column/${postDetail.columnId}`">我的专栏</router-link></li>
        <li v-if="postDetail" class="breadcrumb-item active" aria-current="page">{{ postDetail.title }}</li>
      </ol>
    </nav>
    <div v-if="postDetail.image" class="mb-4">
      <img :src="postDetail.image.url && postDetail.image.url" :alt="postDetail.title" class="w-100"/>
    </div>
    <div class="mb-4">
      <h2>{{postDetail.title}}</h2>
    </div>
    <div class="mb-5 border-top border-bottom d-flex align-items-center justify-content-between">
      <UserProfile :user-info="postDetail.author"/>
      <div>{{postDetail.createTime}}</div>
    </div>
    <div class="content-container">
      {{postDetail.content}}
    </div>
  </div>
</template>

<script lang="ts">
import UserProfile from '@/components/UserProfile.vue'
import { GlobalDataProps, PostProps } from '@/store'
import { computed, defineComponent, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { useStore } from 'vuex'

export default defineComponent({
  name: 'PostDetail',
  props: {
  },
  components: {
    UserProfile
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
.content-container {
  margin-bottom: 200px;
}
</style>
