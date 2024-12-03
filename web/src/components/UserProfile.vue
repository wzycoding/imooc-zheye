<template>
    <div class="user-profile-container d-flex align-items-center">
        <img :src="userInfo.avatar.url" class="rounded-circle border border-light my-3"/>
        <div class="d-flex flex-column ms-3">
          <h6>{{userInfo.nickname}}</h6>
          <span class="text-truncate text-muted d-block">{{userInfo.description}}</span>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'UserProfile',
  props: {
    userInfo: {
      type: Object,
      required: true
    }
  }
})
</script>

<style scoped>
.user-profile-container img {
  width: 50px;
  height: 50px;
}
</style>
