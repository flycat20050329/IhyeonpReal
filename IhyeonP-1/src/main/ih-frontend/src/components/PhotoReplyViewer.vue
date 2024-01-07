<template>
  <div class="container replyViewer">
    <div class="row replies" v-for="reply in replyData">
      <PhotoReply :reply="reply"></PhotoReply>
    </div>
  </div>
</template>

<script>
import { onMounted, ref, watch } from "vue";
import { useStore } from 'vuex';

import PhotoService from "../services/photo.service.js";

import usePhotoStore from '../store/photo';

import PhotoReply from "./PhotoReply.vue";

export default {
  components: {
    PhotoReply,
  },
  props: {
    replyData: Object,
  },
  setup(props, context) {
    watch(() => props.replyData, () => {
      for (var i = 0; i < props.replyData.length; i++) {
        props.replyData[i].uploaded_on = props.replyData[i].uploaded_on.split("T")[0]
      }
    })
  },
}
</script>

<style>
@import url(https://fonts.googleapis.com/css?family=Black+Han+Sans&display=swap);

/* .container .replyViewer{
  height: 20px;
  width: 400px;
} */

.replyViewer{
  height:270px;
}

.replies{
  padding: 1% 0 1% 0;
}
</style>
