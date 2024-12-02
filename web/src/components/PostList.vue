<template>
  <div class="post-list">
      <article v-for="post in list" :key="post.id" class="card mb-3 shadow-sm">
        <div class="card-body">
          <h4>{{ post.title }}</h4>
          <div class="row my-3 align-items-center">
            <div v-if="post.image && post.image.url" class="col-3">
              <img :src="post.image.url" :alt="post.title" class="rounded-lg w-100">
            </div>
            <p :class="{'col-9': post.image}" class="text-muted">{{post.content}}</p>
          </div>
          <span class="text-muted">{{post.createdAt}}</span>
        </div>
      </article>
  </div>
</template>

<script lang="ts">
import { PostProps } from '@/store'
import { defineComponent, PropType } from 'vue'

export default defineComponent({
  name: 'PostList',
  props: {
    list: {
      required: true,
      type: Array as PropType<PostProps[]>
    }
  }
})
</script>

<style lang="css">
</style>
