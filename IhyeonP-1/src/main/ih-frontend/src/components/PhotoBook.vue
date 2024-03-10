<template>
  <!-- 사진첩 전체 레이아웃 -->
  <div class="container" style="height:fit-content;">

    <!-- gallery -->
    <div v-if="!uploadImages" class="container">
      <!-- main splide -->
      <div class="mainSplide">
        <splide id="splide" class="splide" :options="mainoptions" :extensions="extensions">
          <splide-slide v-for="image of photoStore.getPhotos">
            <div class="item" :style="{ 'background-image': `url(${image.image})` }" @click="clickImage(image)">
            </div>
          </splide-slide>
        </splide>
      </div>
    </div>


    <!-- image upload -->
    <div class="container uploadBox" style=" height: fit-content" v-if="uploadImages">
      <button class="btn" id="Cancel" @click="cancelPost">&lt</button>
      <div class="row justify-content-end">
        <!-- progress bar -->
        <div class="progress" style="height: 3px;" v-if="isUploadingPhoto">
          <div class="progress-bar" role="progressbar" style="width: 25%;" aria-valuenow="25" aria-valuemin="0"
            aria-valuemax="100"></div>
          <p></p>
        </div>

        <div class="col-7 previewBox">

          <!-- spinner -->
          <div id="loadingSpinner" v-if="isLoading">
            <LoadingSpinner style="height: fit-content;"></LoadingSpinner>
            <a>사진을 변환중입니다... ({{ currentNum }} / {{ totalNum }})</a>
          </div>

          <!-- imagePreview -->
          <splide :options="preoptions" ref="previewSplide">
            <splide-slide v-for="image in previewImages">
              <div class="imagePreviewWrapper" :style="{ 'background-image': `url(${image})` }" @click="chooseFiles()">
              </div>
            </splide-slide>
          </splide>
        </div>
        <div class="col">
          <textarea @input="inputHandler" maxlength="150" class="form-control" rows="12" v-model="text"
            style="resize: none;" id="textBox" placeholder="사진이 변환되는 동안 멋진 글을 작성해보세요!"></textarea>
          <p id="lengthText">{{ 150 - text.length }}/150</p>
          <div class="row justify-content-end" style="width:inherit">
            <!-- <div class="col-3">
              <button class="btn btn-outline-dark m-3" id="Cancel" @click="cancelPost">Cancel</button>
            </div> -->
            <div class="col-2">
              <button class="btn btn-outline-primary m-3" id="Submit" @click="uploadPost">Submit</button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <input id="fileUpload" class="form-control" type="file" accept="image/heic, image/jpg, image/png" @input="pickFile"
      multiple hidden>
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

import { ref, onMounted } from 'vue';
import { useStore } from 'vuex';

import { Splide, SplideSlide } from '@splidejs/vue-splide';
import { Grid } from '@splidejs/splide-extension-grid';
import "@splidejs/splide/dist/css/splide.min.css";
import vueFullpageUmd from 'vue-fullpage.js';

import { usePhotoStore } from '../store/photo.js';

import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';

import moment from "moment";
import heic2any from "heic2any";

import imageCompression from 'browser-image-compression';

import LoadingSpinner from "./LoadingSpinner.vue"

import moment from "moment";

import { useToast } from "vue-toastification";

// import '@splidejs/splide/dist/css/themes/splide-default.min.css';ㅈ
// import '@splidejs/splide/dist/css/themes/splide-sea-green.min.css';
// import '@splidejs/splide/dist/css/themes/splide-skyblue.min.css';


export default {
  components: {
    Splide,
    SplideSlide,
    VueDatePicker,
    LoadingSpinner,
    moment
  },

  props: {
  },
  emits: ["changeUploadImages", "imageData"],

  async setup(props, context) {
    var previewImages = ref([]);
    var text = ref("");
    const uploadImages = ref(false);
    var mainimages = ref([]);
    var postId;

    const isLoading = ref(true);
    const isUploadingPhoto = ref(false);


    const store = useStore();
    const currentUser = store.state.auth.user;

    const photoStore = usePhotoStore();

    const toast = useToast();

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

    const previewSplide = ref(null);
    const images = [];

    const totalNum = ref(0);
    const currentNum = ref(0);

    const pickFile = async (e) => {
      currentNum.value = 0;
      if (e.target.files.length <= 0) {
        return
      }
      if (e.target.files[0] && e.target.files.length < 4) {
        totalNum.value = e.target.files.length
        isLoading.value = true;
        var files = e.target.files || e.dataTransfer.files;
        for (const file of files) {
          if (!file.type.includes('image')) {
            alert('이미지(JPG,JPEG,PNG,HEIC) 파일만 업로드 가능합니다.\n파일을 다시 한 번 확인해주세요.')
            return
          }
        }

        // 업로드 화면 표시.
        uploadImages.value = true;
        context.emit("changeUploadImages", true);
        previewImages.value = [];


        const compressOptions = {
          maxSizeMB: 1,
          maxWidthOrHeight: 1920,
          useWebWorker: true,
        }

        if (files && files[0]) {
          for (var i = 0; i < files.length; i++) {
            let blob = files[i]

            if (blob.type == "image/heic") {
              const frm = new FormData();
              frm.append("images", files);

              // heic 변환
              await heic2any({ blob: blob, toType: "image/png" })
                .then(async function (resultBlob) {
                  const file = new File([resultBlob], blob.name.split('.')[0] + ".png", { type: "image/png", lastModified: new Date().getTime() });

                  const compressedFile = await imageCompression(file, compressOptions);
                  images.push(compressedFile);

                  let reader = new FileReader;
                  reader.onload = e => {
                    previewImages.value.push(e.target.result);
                    currentNum.value += 1;
                  }
                  reader.readAsDataURL(compressedFile);
                  previewSplide.value.splide.Components.Controller.setIndex(i)

                })
                .catch(function (x) {
                  console.log(x)
                })
            } else {
              images.push(files[i])
              let reader = new FileReader;
              reader.onload = e => {
                previewImages.value.push(e.target.result);
                currentNum.value += 1;
              }
              reader.readAsDataURL(files[i])
            }

          }
        }
        isLoading.value = false;
      }
      else {
        alert("파일은 최대 3개까지만 업로드 가능합니다.\n다시 선택해주세요.")
        return
      }
    }


    const cancelPost = () => {
      context.emit("changeUploadImages", false);
      uploadImages.value = false;
      text.value = "";

    }

    const uploadPost = async () => {    //글을 최종적으로 올릴 때
      if (isLoading.value) {
        toast("아직 변환되지 않은 사진이 있습니다.\n잠시 후 다시 시도해주세요.", {
          type: "warning"
        })
        return
      }

      isUploadingPhoto.value = true;

      document.getElementById("Submit").disabled = true;
      document.getElementById("Cancel").disabled = true;
      document.getElementById("textBox").siabled = true;

      const frm = new FormData();
      frm.append("userId", currentUser.id);
      frm.append("text", text.value)

      await PhotoService.uploadPhotoPost(frm).then((result) => {
        postId = result.data;
      });

      console.log("upload post success")

      uploadImage();
    }

    const uploadImage = async () => {
      const frm2 = new FormData();
      // console.log(images)
      for (const file of images) {
        frm2.append('images', file)
      }

      frm2.append("postId", postId);
      await PhotoService.uploadPhoto(frm2).then((result) => {
        for (var i = 0; i < result.data.length; i++) {
          result.data[i].image = "data:image/jpeg;base64," + result.data[i].image
        }
        photoStore.setAllPhotos(result.data);
      });

      console.log("upload photos success")

      text.value = "";
      uploadImages.value = false;
      context.emit("changeUploadImages", false);

      document.getElementById("Submit").disabled = false;
      document.getElementById("Cancel").disabled = false;
      document.getElementById("textBox").siabled = false;
      isUploadingPhoto.value = false;

      toast("성공적으로 업로드되었습니다.", {
        type: 'success',
      });
    }

    const chooseFiles = () => {
      document.getElementById("fileUpload").click();
    }

    var imageData = ref({ index: null, images: null, post: null, });

    const clickImage = async (image) => {
      imageData.value.index = image.index;

      await PhotoService.getClickedPhotoData(image.photoPost?.id).then((result) => {

        imageData.value.post = result.data.post;
        imageData.value.post.uploadedOn = moment(imageData.value.post.uploadedOn).format("YYYY년 MM월 DD일 hh시 mm분")
        for (var i = 0; i < result.data.images.length; i++) {
          result.data.images[i].image = "data:image/jpeg;base64," + result.data.images[i].image
        }
        imageData.value.images = result.data.images;
      })

      // console.log(imageData.value.index);
      context.emit('imageData', imageData);
      document.getElementById("photoModalBtn").click();

    };

    const inputHandler = (e) => {
      const target = e.currentTarget;
      const max = e.currentTarget.getAttribute('maxlength');
      if (target.value.length > max) {
        target.value = target.value.slice(0, max);
      }
      // customString.value = target.value;
    };

    const show = () => {
      toast.add({ severity: 'info', summary: 'Info', detail: 'Message Content', life: 3000 });
    };


    return {
      // methods
      pickFile,
      uploadPost,
      chooseFiles,
      clickImage,
      cancelPost: cancelPost,
      // checkSwitch,
      inputHandler,
      show,

      // splide options
      preoptions: preoptions,
      mainoptions: mainoptions,
      extensions: {
        Grid,
      },

      photoStore,

      // data
      previewImages,
      text,
      uploadImages,
      mainimages,
      imageData,
      isLoading,
      previewSplide,
      isUploadingPhoto,
      totalNum,
      currentNum
      // meChecked,
      // noPhoto,
      // dateFilter,
      // maxDate,
    };
  }
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
  // background-size: contain;
  // background-repeat: no-repeat;
  background-position: center center;
  background-color: white;
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

.uploadBox {
  z-index: 1;
  position: relative;
}

.previewBox {

  display: flex;
  align-items: center;
  justify-content: center;

}

#loadingSpinner {
  width: fit-content;
  height: fit-content;

  z-index: 2;
  position: absolute;
}

#loadingSpinner a {
  color: white;
  font-size: 1.5rem;
  text-shadow: -1px 0 black, 0 1px black, 1px 0 black, 0 -1px black;
}
</style>