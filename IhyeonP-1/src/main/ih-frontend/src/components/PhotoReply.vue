<template>
  <div class="reply">
    <div class="row title">
      <div class="col">
        <a>{{ reply?.user.username }}</a>
      </div>
      <div class="col-4">
        <a>{{ reply?.uploaded_on }}</a>
      </div>
      <div class="col-1">
        <div class="dropdown">
          <a class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
            <font-awesome-icon icon="ellipsis-h" />
          </a>
          <ul class="dropdown-menu dropdown-menu-end">
            <li v-if="currentUser?.id == reply?.user.id">
              <div class="btn dropdown-item" @click="deleteReply()"><font-awesome-icon icon="trash" /> 삭제
              </div>
            </li>
            <li v-if="currentUser?.id != reply?.user.id">
              <div class="btn dropdown-item text-danger"><font-awesome-icon icon="flag" /> 신고 </div>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="row content">
      <div class="col">
        <a>{{ reply?.text }}</a>
      </div>
    </div>
  </div>
</template>

<script>
import { onMounted, ref, watch } from "vue";
import { useStore } from 'vuex';

import PhotoService from "../services/photo.service.js";

import usePhotoStore from '../store/photo';

export default {
  components: {
  },
  props: {
    reply: Object,
  },
  setup(props, context) {
    const store = useStore();
    const currentUser = store.state.auth.user;

    const deleteReply = () => {
      // alert("정말 삭제하시겠습니까?");
      if (confirm('삭제하시면 복구할수 없습니다. \n 정말로 삭제하시겠습니까??')) {
        const frm = new FormData();
        frm.append("id", props.reply.id);

        PhotoService.deleteReply(frm)
      } else {
        console.log("취소함");
      }
    }

    watch(() => props.reply, () => {
    })
    return {
      currentUser,

      deleteReply,
    }
  },
}
</script>

<style>
@import url(https://fonts.googleapis.com/css?family=Black+Han+Sans&display=swap);

.reply {
  width: inherit;
}

.title {
  font-family: "Black Han Sans";
}

.content {
  width: 380px;
}
</style>
