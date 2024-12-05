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
    <div v-if="showEditArea" class="btn-group mt-5">
      <router-link
        type="button"
        class="btn btn-success"
        :to="{ name: 'create', query: { id: postDetail.id }}"
      >编辑</router-link>
      <button type="button" class="btn btn-danger" @click.prevent="modalIsVisible = true">删除</button>
    </div>
  </div>
</template>

<script lang="ts">
import UserProfile from '@/components/UserProfile.vue'
import { GlobalDataProps, PostProps, UserProps } from '@/store'
import { computed, defineComponent, onMounted, ref } from 'vue'
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
    const modalIsVisible = ref(false)
    onMounted(() => {
      console.log(currentPostId)
      store.dispatch('fetchPostDetial', currentPostId)
    })

    const postDetail = computed<PostProps>(() => store.getters.getPostDetail(currentPostId))
    const showEditArea = computed(() => {
      const { isLogin, id } = store.state.user
      if (postDetail.value && postDetail.value.author && isLogin) {
        const postAuthor = postDetail.value.author as UserProps
        return postAuthor.id === id
      } else {
        return false
      }
    })

    return {
      postDetail,
      showEditArea,
      modalIsVisible
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
