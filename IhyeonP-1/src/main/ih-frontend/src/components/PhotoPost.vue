<template>
  <!-- imageViewer -->
  <div class="container">
    <div class="row">

      <!-- photo viewer -->
      <div class="col-7">
        <splide :options="preoptions" @splide:mounted="onSplideMounted" @splide:move="onSplideMoved" id="photoSplide"
          ref="mainSplide">
          <splide-slide v-for="image in images.images" :key="image.index">
            <div class="imagePreviewWrapper" :style="{ 'background-image': `url(${image.image})` }"
              @click="editing = false">
            </div>
          </splide-slide>
        </splide>
        <!-- <p id="imageLength">{{ currentIndex + 1 }} / {{ images.images?.length }}</p> -->
        <button id="goIndexBtn" class="btn" @click="goIndex()">눌러</button>
      </div>

      <!-- info -->
      <div class="col-5 detailContainer">

        <!-- top menu bar -->
        <div class="row justify-content-between">

          <!-- username -->
          <div class="col">
            <div id="userBox">
              <h4><font-awesome-icon icon="user" /> {{ images.post?.user.username }}</h4>
            </div>
          </div>

          <!-- menu dropdown -->
          <div class="col-1">
            <div class="dropdown">
              <button class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                <font-awesome-icon icon="bars" />
              </button>
              <ul class="dropdown-menu dropdown-menu-end">
                <li v-if="currentUser?.id == images.post?.user.id">
                  <div class="btn dropdown-item" @click="editPost()"><font-awesome-icon icon="pencil-alt" /> 수정
                  </div>
                </li>
                <li v-if="currentUser?.id == images.post?.user.id">
                  <div class="btn dropdown-item" data-bs-toggle="modal" data-bs-taget="#deletePhotoModal">
                    <font-awesome-icon icon="trash" /> 삭제
                  </div>
                </li>
                <li>
                  <div class="btn dropdown-item text-danger"><font-awesome-icon icon="flag" /> 신고 </div>
                </li>
              </ul>
            </div>
          </div>

        </div>


        <!-- text -->
        <div id="textBox" class="row md-3" style="padding-left: 5%;" v-if="!editing">
          <div class="overflow-hidden" style="max-height: 100px; width:95%">{{ images.post?.text }}
          </div>
          <p style="font-size: 70%; padding-top: 3%;" class="text-secondary">{{ images.post?.uploaded_on
          }}</p>
        </div>

        <!-- text editing box -->
        <div id="textBox" class="row md-3" style="padding-left: 5%;" v-if="editing">
          <textarea @input="inputHandler" maxlength="100" class="form-control" id="contentText" rows="12" v-model="text"
            style="resize: none;"></textarea>
          <p id="lengthText">{{ 100 - text.length }}/100</p>
          <div class="row justify-content-end" style="width:inherit">
            <div class="col-3">
              <button class="btn btn-outline-dark m-3" @click="editing = !editing">Cancel</button>
            </div>
            <div class="col-2">
              <button class="btn btn-outline-primary m-3" @click="editText">Submit</button>
            </div>
          </div>
        </div>

        <div id="viewReply" v-if="!editing">
          댓글이 있을 곳입니다.
        </div>

        <!-- reaction zone -->
        <div id="reactionZone" v-if="!editing">
          <!-- heart -->
          <div id="heartBox" class="col align-self-end mt-2">
            <font-awesome-icon :icon="['far', 'heart']" /> <a>{{ images.post?.heart }}</a>
          </div>
          <!-- reply chat -->
          <div class="input-group">
            <textarea class="form-control mt-1 ml-3" id="textArea" rows="1" placeholder="댓글을 입력하세요."
              style="resize: none;"></textarea>
            <span class="input-group-addon btn text-primary" @click="sendReply">게시</span>
          </div>

        </div>

      </div>
    </div>
  </div>
</template>


<script>
import { Splide, SplideSlide } from '@splidejs/vue-splide';
import { ref, watch } from "vue";
import { useStore } from 'vuex';

import PhotoService from "../services/photo.service.js";

import usePhotoStore from '../store/photo';

export default {
  components: {
    Splide,
    SplideSlide,
  },
  props: {
    images: Object,
  },
  setup(props, context) {
    var preoptions = {
      rewind: true,
      height: 500,
      gap: '1rem',
      perMove: 1,
      perPage: 1,
      pagination: true,
      postId: null,
      paginationDirection: 'ltr',
      // direction: 'ttb',
      // arrows: false,
      // paginationDirection: 'ltr',
    };

    const store = useStore();
    const currentUser = store.state.auth.user;
    const photoStore = usePhotoStore();

    var editing = ref(false);

    var text = ref("");

    const editPost = () => {
      editing.value = true;
      text.value = props.images.post?.text;
    }

    const editText = async () => {
      const frm = new FormData();
      frm.append("postId", props.images.post?.id);
      frm.append("text", text.value)


      // 질문하기!) 디비 수정을 하고 스토어를 어떻게 처리하는 게 좋을지
      // 지금은 너무 정적이라는 생각이 듦. 동적이었으면 하는 바람?
      // 추후 댓글과 하트도 생각해야함.

      await PhotoService.editPostText(frm).then((result) => {
        // console.log(result.data.text);
        props.images.post.text = result.data.text;
      });

      editing.value = false;
    };

    const deletePost = () => {

    }

    var currentIndex = ref(1);
    var mainSplide = ref(null);

    const onSplideMounted = (splide) => {
      // mainSplide = splide
    };

    const onSplideMoved = (splide) => {
      // mainSplide = splide;
      currentIndex.value = splide.index;
    }

    const goIndex = () => {
      mainSplide.value.splide.Components.Controller.go(props.images.index);
    }

    return {
      preoptions,
      currentUser,
      photoStore,

      // methods
      editPost,
      editText,
      onSplideMounted,
      goIndex,
      onSplideMoved,

      // variables
      editing,
      text,
      currentIndex,
      mainSplide,

    }
  },
  methods: {
    sendReply() {

    },
  },
}
</script>

<style>
@import url(https://fonts.googleapis.com/css?family=Black+Han+Sans&display=swap);

.imagePreviewWrapper {
  height: 500px;
  max-width: 600px;
  /* object-fit: cover; */
  display: block;
  cursor: pointer;
  margin: 0 auto 30px;
  background-size: contain;
  background-repeat: no-repeat;
  background-position: center center;
  /* border: 1px solid gray; */
}

#textBox {
  padding: 5px 10px 5px 0;
}


#userBox {
  font-family: "Black Han Sans";
  padding: 5px 0 0 5px;
}

#heartBox {
  font-family: "Balck Han Sans";
  font-weight: bold;
  color: red;
}

#reactionZone {
  position: absolute;
  bottom: 0;
  width: inherit;
  padding: 0 3% 3% 0;
  border-top: 0.1rem solid whitesmoke
}

.detailContainer {
  padding-top: 1%;
}

#lengthText {
  text-align: right;
  margin-top: -27px;
  margin-right: 10px;
  padding-right: 1%;
  color: gray;
}

#imageLength {
  text-align: right;
  margin-top: -5%;
  padding-right: 3%;
  color: gray;
  position: relative;
}
</style>
