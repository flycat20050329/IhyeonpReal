<template>
  <div class="container" style="height:fit-content;">
    <!-- gallery -->
    <div v-if="!uploadImages" class="container">

      <!-- top bar -->
      <div class="topBar">
        <div class="row justify-content-between">
          <div class="col">
            <div class="row">

              <!-- upload button -->
              <div class="col-3">
                <button type="button" class="btn btn-outline-dark" @click="chooseFiles()">
                  <font-awesome-icon icon="plus" /> 사진 올리기</button>
              </div>

              <!-- switch -->
              <div class="col">
                <div class="form-check form-switch" style="width:fit-content">
                  <input class="form-check-input" type="checkbox" id="meSwitch" v-model="meChecked"
                    v-on:change="checkSwitch">
                  <label class="form-check-label" for="flexSwitchCheckDefault">Only Mine</label>
                </div>
              </div>
            </div>
          </div>

          <!-- 검색어 입력 -->
          <div class="col-4">
            <div class="form">
              <i class="fa fa-search"></i>
              <input type="text" class="form-control form-input" placeholder="검색어를 입력하세요.">
            </div>
          </div>
        </div>
      </div>


      <!-- main splide -->
      <div class="mainSplide" v-if="!meChecked || !noPhoto">
        <splide id="splide" class="splide" :options="mainoptions" :extensions="extensions">
          <splide-slide v-for="image of photoStore.getPhotos">
            <div class="item" :style="{ 'background-image': `url(${image.image})` }" @click="clickImage(image)">
            </div>
          </splide-slide>
        </splide>
      </div>

      <div class="noMyPhoto" v-if="meChecked && noPhoto" style="padding: 7% 0 0 5%; color: gray">
        <h2>사진이 없습니다.</h2>
      </div>

    </div>


    <!-- image upload -->
    <div class="container" style=" height: fit-content" v-if="uploadImages">
      <div class="row justify-content-end">
        <div class="col-7">
          <!-- imagePreview -->
          <splide :options="preoptions">
            <splide-slide v-for="image in previewImages">
              <div class="imagePreviewWrapper" :style="{ 'background-image': `url(${image})` }" @click="">
              </div>
            </splide-slide>
          </splide>
        </div>
        <div class="col">
          <textarea @input="inputHandler" maxlength="100" class="form-control" rows="12" v-model="text"
            style="resize: none;"></textarea>
          <p id="lengthText">{{ 100 - text.length }}/100</p>
          <div class="row justify-content-end" style="width:inherit">
            <div class="col-2">
              <button class="btn btn-outline-dark m-3" @click="cancelPost">Cancel</button>
            </div>
            <div class="col-2">
              <button class="btn btn-outline-primary m-3" @click="uploadPost">Submit</button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <input id="fileUpload" class="form-control" type="file" @input="pickFile" multiple hidden>
    <!-- <div class="imagePreviewWrapper" :style="{ 'background-image': `url(${previewImage})` }" @click="getImage" /> -->
    <!-- <div class="imagePreviewWrapper" :style="{ 'background-image': `url(${uploadImage})` }"></div> -->

    <!-- Button trigger modal -->
    <!-- <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#photoModal">
      Launch demo modal
    </button> -->
  </div>

  <!-- Button trigger photoModal -->
  <button type="button" id="photoModalBtn" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#photoModal"
    style="display:none;">
  </button>
</template>
 
<script>
import axios from 'axios';
import AuthService from '../services/auth.service';
import PhotoService from '../services/photo.service';
import { Form } from 'vee-validate';

import { onMounted, ref, watch } from 'vue';
import { useStore } from 'vuex';

import { Splide, SplideSlide } from '@splidejs/vue-splide';
import { Grid } from '@splidejs/splide-extension-grid';
import "@splidejs/splide/dist/css/splide.min.css";
import vueFullpageUmd from 'vue-fullpage.js';

import { usePhotoStore } from '../store/photo.js';


// import '@splidejs/splide/dist/css/themes/splide-default.min.css';
// import '@splidejs/splide/dist/css/themes/splide-sea-green.min.css';
// import '@splidejs/splide/dist/css/themes/splide-skyblue.min.css';


export default {
  components: {
    Splide,
    SplideSlide,
  },

  props: {
  },
  emits: ["rerender", "imageData"],

  async setup(props, context) {
    var previewImages = ref([]);
    var text = ref("");
    var images = [];
    var uploadImages = ref(false);
    var mainimages = ref([]);
    var postId;


    const store = useStore();
    const currentUser = store.state.auth.user;

    const photoStore = usePhotoStore();

    var meChecked = ref(false);

    const preoptions = {
      rewind: true,
      height: 400,
      gap: '1rem',
      perMove: 1,
      perPage: 1,
      pagination: false,
      postId: null,
    };

    const mainoptions = {
      // drag: "free",
      // autoWidth: true,
      perPage: 7,
      pagination: false,
      arrows: false,
      gap: 10,
      grid: {
        rows: 2,
        cols: 1,
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
        }
      }
    }

    const uploadPost = async () => {
      const frm = new FormData();
      frm.append("id", currentUser.id);
      frm.append("heart", 0);
      frm.append("text", text.value)

      await PhotoService.uploadPhotoPost(frm).then((result) => {
        postId = result.data;
      });
      uploadImages.value = false;
      text.value = "";
      await uploadImage();
    }

    const cancelPost = () => {
      uploadImages.value = false;
    }

    const uploadImage = async () => {
      const frm2 = new FormData();
      for (const file of images) {
        frm2.append('images', file)
      }

      frm2.append("postId", postId);

      await PhotoService.uploadPhoto(frm2).then((result) => {
        for (var i = 0; i < result.data.length; i++) {
          result.data[i].image = "data:image/png;base64," + result.data[i].image
        }
        photoStore.setAllPhotos(result.data);
      });
    }

    const chooseFiles = () => {
      document.getElementById("fileUpload").click();
    }

    var imageData = ref({ index: null, images: null, post: null, });

    const clickImage = async (image) => {
      imageData.value.index = image.index;
      PhotoService.getClickedPhotoData(image.photoPost?.id).then((result) => {

        imageData.value.post = result.data.post;
        for (var i = 0; i < result.data.images.length; i++) {
          result.data.images[i].image = "data:image/png;base64," + result.data.images[i].image
        }
        imageData.value.images = result.data.images;
      })

      // console.log(imageData.value.index);
      context.emit('imageData', imageData);
      document.getElementById("photoModalBtn").click();
    }

    const checkSwitch = () => {
      if (meChecked.value) {
        photoStore.setPhotos(photoStore.getAllPhotos.filter(photo => {
          return photo.photoPost.user?.id == currentUser.id;
        }))
      } else {
        photoStore.setPhotos(photoStore.getAllPhotos);
        context.emit("rerender", 0);
      }

    }

    const inputHandler = (e) => {
      const target = e.currentTarget;
      const max = e.currentTarget.getAttribute('maxlength');
      if (target.value.length > max) {
        target.value = target.value.slice(0, max);
      }
      // customString.value = target.value;
    };

    const noPhoto = ref(false);

    watch(() => photoStore.getAllPhotos, (newValue, oldValue) => {
      console.log({ newValue, oldValue });
      // photoStore.setPhotos(newValue);
      checkSwitch();
      context.emit("rerender", 0);
    })

    watch(() => photoStore.getPhotos, (newValue, oldValue) => {
      if (newValue?.length == 0) {
        noPhoto.value = true;
      }
    })

    return {
      // methods
      pickFile,
      uploadPost,
      chooseFiles,
      clickImage,
      cancelPost,
      checkSwitch,
      inputHandler,

      // splide
      preoptions: preoptions,
      mainoptions: mainoptions,
      extensions: {
        Grid,
      },

      photoStore,

      // data
      previewImages,
      text,
      images,
      uploadImages,
      mainimages,
      imageData,
      meChecked,
      noPhoto,
    };
  },
}
</script>

<style scoped lang="scss">
@import url("../../node_modules/@egjs/vue3-flicking/dist/flicking-inline.css");

.splide {
  background-color: #d7d7d72b;
}

.item {
  height: 200px;
  max-width: 200px;
  object-fit: cover;
  display: block;
  cursor: pointer;
  margin: 5px auto 0 auto;
  background-size: cover;
  background-position: center center;
  border: 1px solid rgba(165, 165, 165, 0.539);
  // transition: width 1s ease;
  padding: 3px 3px 3px 3px;
}

.imagePreviewWrapper {
  height: 400px;
  max-width: 700px;
  object-fit: cover;
  display: block;
  cursor: pointer;
  margin: 0 auto 30px;
  background-size: contain;
  background-position: center center;
  // border: 1px solid gray;
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

#lengthText {
  text-align: right;
  margin-top: -27px;
  margin-right: 10px;
  padding-right: 1%;
  color: gray;
}
</style>