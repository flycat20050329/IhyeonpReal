<template>
  <div>
    <div v-if="!uploadImages" style="padding-top: 500px;">
      <div class="topBar">
        <div class="row justify-content-between">
          <div class="col-4">
            <button type="button" class="btn btn-outline-dark" @click="chooseFiles()">
              <font-awesome-icon icon="plus" /> 사진 올리기</button>
          </div>
          <div class="col-4">
            <div class="form">
              <i class="fa fa-search"></i>
              <input type="text" class="form-control form-input" placeholder="검색어를 입력하세요.">
            </div>
          </div>
        </div>
      </div>
      <div>
        <!-- main splide -->
        <splide class="splide" :options="mainoptions" :extensions="extensions">
          <splide-slide v-for=" image of mainimages">
            <div class="item" :style="{ 'background-image': `url(${image.image})` }" @click="clickImage(image)">
            </div>
          </splide-slide>
        </splide>
      </div>
    </div>
    <!-- image upload -->
    <div class="container" style="padding-top: 100px;">
      <div class="row" v-if="uploadImages">
        <div class="col-5">
          <!-- imagePreview -->
          <splide :options="preoptions">
            <splide-slide v-for="(image, index) in previewImages">
              <div class="imagePreviewWrapper" :style="{ 'background-image': `url(${image})` }" @click="chooseFiles()">
              </div>
            </splide-slide>
          </splide>
        </div>
        <div class="col">
          <textarea class="form-control" rows="10" v-model="text"></textarea>
          <button class="btn btn-outline-dark m-3" @click="savePost">Submit</button>
        </div>
      </div>
    </div>
    <input id="fileUpload" class="form-control" type="file" @input="pickFile" multiple hidden>
    <!-- <div class="imagePreviewWrapper" :style="{ 'background-image': `url(${previewImage})` }" @click="getImage" /> -->
    <!-- <div class="imagePreviewWrapper" :style="{ 'background-image': `url(${uploadImage})` }"></div> -->
  </div>
</template>
 
<script>
import axios from 'axios';
import AuthService from '../services/auth.service';
import { Form } from 'vee-validate';

import { ref } from 'vue';
import { useStore } from 'vuex';

import { Splide, SplideSlide } from '@splidejs/vue-splide';
import { Grid } from '@splidejs/splide-extension-grid';
import "@splidejs/splide/dist/css/splide.min.css";
import vueFullpageUmd from 'vue-fullpage.js';

// import '@splidejs/splide/dist/css/themes/splide-default.min.css';
// import '@splidejs/splide/dist/css/themes/splide-sea-green.min.css';
// import '@splidejs/splide/dist/css/themes/splide-skyblue.min.css';

// import Flicking from "@egjs/vue3-flicking";



export default {
  components: {
    Splide,
    SplideSlide,
    // Flicking: Flicking,
  },
  props: {
    mainimages: Array,
  },
  async setup(props, context) {

    var previewImages = ref([]);
    var text = ref(null);
    var images = [];
    var uploadImages = ref(false);
    var mainimages = ref([]);
    var postId;

    const store = useStore();
    const currentUser = store.state.auth.user;

    const preoptions = {
      rewind: true,
      height: 300,
      gap: '1rem',
      perMove: 1,
      perPage: 1,
      pagination: false,
      postId: null,
      // arrows: false,
      // paginationDirection: 'ttb',
    };

    const mainoptions = {
      drag: "free",
      // autoWidth: true,
      perPage: 7,
      pagination: false,
      arrows: false,
      gap: 10,
      grid: {
        dimensions: [[2, 1]],
        gap: {
          row: "1rem",
          col: "1.5rem",
        },
      },
    };

    const pickFile = (e) => {
      if (e.target.files[0] && e.target.files.length < 7) {

        uploadImages.value = true;
        previewImages.value = [];

        var files = e.target.files || e.dataTransfer.files;
        images = files;

        if (files && files[0]) {
          for (var i = 0; i < files.length; i++) {
            let reader = new FileReader;
            reader.onload = e => {
              previewImages.value.push(e.target.result);
            }
            reader.readAsDataURL(files[i])
          }
          // this.$emit('input', file[0])
          // this.imgData = files[0];
        }
      }
    }

    const savePost = async () => {
      const frm = new FormData();
      frm.append("id", currentUser.id);
      frm.append("heart", 0);
      frm.append("text", text.value);

      await AuthService.saveImagePost(frm).then((result) => {
        postId = result.data;
      });
      uploadImages.value = false;
      text.value = null;
      await saveImage();
    }

    const saveImage = async () => {
      const frm2 = new FormData();
      for (const file of images) {
        frm2.append('images', file)
      }

      frm2.append("postId", postId);

      await AuthService.saveImage(frm2);

      // await AuthService.getImage().then((result) => {
      //   for (var i = 0; i < result.data.length; i++) {
      //     result.data[i].image = "data:image/png;base64," + result.data[i].image
      //   }
      //   mainimages.value = result.data;
      // });

      context.emit("setInput", 0);
      mainimages.value = props.mainimages;

      // await AuthService.saveImage(frm2).then((result) => {
      //   for (var i = 0; i < result.data.length; i++) {
      //     result.data[i].image = "data:image/png;base64," + result.data[i].image;
      //     mainimages.value.unshift(result.data[i]);
      //   }
      // });
    }

    const chooseFiles = () => {
      document.getElementById("fileUpload").click();
    }


    const clickImage = (image) => {
      console.log(image.id);
    }

    const popToast = () => {
    }



    await AuthService.getImage().then((result) => {
      for (var i = 0; i < result.data.length; i++) {
        result.data[i].image = "data:image/png;base64," + result.data[i].image
      }

      mainimages.value = result.data;
    });



    return {
      // methods
      pickFile,
      savePost,
      chooseFiles,
      clickImage,
      popToast,

      // splide
      preoptions: preoptions,
      mainoptions: mainoptions,
      extensions: {
        Grid,
      },

      // data
      previewImages,
      text,
      images,
      uploadImages,
      mainimages,
    };
  },
}
</script>

<style scoped lang="scss">
// @import url("../../node_modules/@egjs/vue3-flicking/dist/flicking-inline.css");

.splide {
  background-color: #ececec38;
}

.item {
  height: 180px;
  max-width: 180px;
  object-fit: cover;
  display: block;
  cursor: pointer;
  margin: 5px auto 0 auto;
  background-size: cover;
  background-position: center center;
  border: 1px solid rgba(165, 165, 165, 0.539);
  // transition: width 1s ease;
}

.imagePreviewWrapper {
  height: 300px;
  max-width: 300px;
  object-fit: cover;
  display: block;
  cursor: pointer;
  margin: 0 auto 30px;
  background-size: cover;
  background-position: center center;
  border: 1px solid gray;
}

.topBar {
  border-bottom: solid;
  padding-bottom: 5px;
}

.topBar .searchBar {
  display: flex;
  justify-content: flex-end;
  padding-right: 15px;
}

.form .fa-search {
  position: absolute;
  top: 12px;
  left: 15px;
  color: #000000;

}

.form {
  position: relative;
  width: fit-content;
}

.form-input {
  height: 38px;
  width: 350px;
  text-indent: 33px;
  border-radius: 40px;
}

.form-input:focus {
  box-shadow: none;
  border: none;
}
</style>
