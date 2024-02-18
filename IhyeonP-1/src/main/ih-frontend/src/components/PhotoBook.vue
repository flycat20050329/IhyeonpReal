<template>
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
      <!-- 
      <div class="noMyPhoto" v-if="meChecked && noPhoto" style="padding: 7% 0 0 5%; color: gray">
        <h2>사진이 없습니다.</h2>
      </div> -->

    </div>


    <!-- image upload -->
    <div class="container" style=" height: fit-content" v-if="uploadImages">
      <div class="row justify-content-end">
        <div class="col-7">
          <!-- imagePreview -->
          <splide :options="preoptions">
            <splide-slide v-for="image in previewImages">
              <div class="imagePreviewWrapper" :style="{ 'background-image': `url(${image})` }" @click="chooseFiles()">
              </div>
            </splide-slide>
          </splide>
        </div>
        <div class="col">
          <textarea @input="inputHandler" maxlength="100" class="form-control" rows="12" v-model="text"
            style="resize: none;"></textarea>
          <p id="lengthText">{{ 100 - text.length }}/100</p>
          <div class="row justify-content-end" style="width:inherit">
            <div class="col-3">
              <button class="btn btn-outline-dark m-3" @click="cancelPost">Cancel</button>
            </div>
            <div class="col-2">
              <button class="btn btn-outline-primary m-3" @click="uploadPost">Submit</button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <input id="fileUpload" class="form-control" type="file" accept="" @input="pickFile" multiple hidden>
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

import { ref } from 'vue';
import { useStore } from 'vuex';

import { Splide, SplideSlide } from '@splidejs/vue-splide';
import { Grid } from '@splidejs/splide-extension-grid';
import "@splidejs/splide/dist/css/splide.min.css";
import vueFullpageUmd from 'vue-fullpage.js';

import { usePhotoStore } from '../store/photo.js';

import { useToast } from "vue-toastification";

import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';


import heic2any from "heic2any";

import imageCompression from 'browser-image-compression';




// import '@splidejs/splide/dist/css/themes/splide-default.min.css';ㅈ
// import '@splidejs/splide/dist/css/themes/splide-sea-green.min.css';
// import '@splidejs/splide/dist/css/themes/splide-skyblue.min.css';


export default {
  components: {
    Splide,
    SplideSlide,
    VueDatePicker,
  },

  props: {
  },
  emits: ["changeUploadImages", "imageData"],

  async setup(props, context) {
    var previewImages = ref([]);
    var text = ref("");
    var images = [];
    const uploadImages = ref(false);
    var mainimages = ref([]);
    var postId;


    const store = useStore();
    const currentUser = store.state.auth.user;

    const photoStore = usePhotoStore();

    const dateFilter = ref();
    const maxDate = ref(new Date())

    const filterDate = async (modelData) => {   //특정 날짜에 올라온 것만 조회
      dateFilter.value = modelData;
      var dates = []
      for (const d of dateFilter.value) {
        const day = String(d.getDate()).padStart(2, '0');
        const month = String(d.getMonth() + 1).padStart(2, '0');
        const year = d.getFullYear();

        dates.push(year + "-" + month + "-" + day)
      }
      await PhotoService.getPhotoFilteredDate(dates[0], dates[1]).then((result) => {
        for (var i = 0; i < result.data.length; i++) {
          result.data[i].image = "data:image/png;base64," + result.data[i].image
        }
        photoStore.setPhotos(result.data);
        context.emit("rerender", 0);
      });

    }

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

    // const pickFile = (e) => {
    //   if (e.target.files.length <= 0) {
    //     return
    //   }

    //   if (e.target.files[0] && e.target.files.length < 7) {
    //     var files = e.target.files || e.dataTransfer.files;
    //     images = files;
    //     for (const file of files) {
    //       if (!file.type.includes('image')) {
    //         alert('이미지(JPG,JPEG,GIF,PNG) 파일만 업로드 가능합니다. \n파일을 다시 한 번 확인해주세요.')
    //         return
    //       }
    //     }

    //     uploadImages.value = true;
    //     context.emit("changeUploadImages", true);
    //     previewImages.value = [];

    //     if (files && files[0]) {
    //       for (var i = 0; i < files.length; i++) {
    //         let reader = new FileReader;
    //         reader.onload = e => {
    //           previewImages.value.push(e.target.result);
    //         }
    //         reader.readAsDataURL(files[i])
    //       }
    //     }
    //   }
    //   else{
    //     alert("파일은 최대 6개까지만 업로드 가능합니다. \n다시 선택해주세요.")
    //     return
    //   }
    // }

    const pickFile = async (e) => {
      if (e.target.files.length <= 0) {
        return
      }
      if (e.target.files[0] && e.target.files.length < 7) {
        var files = e.target.files || e.dataTransfer.files;
        images = [];
        for (const file of files) {
          if (!file.type.includes('image')) {
            alert('이미지(JPG,JPEG,GIF,PNG,HEIC) 파일만 업로드 가능합니다. \n파일을 다시 한 번 확인해주세요.')
            return
          }
        }

        uploadImages.value = true;
        context.emit("changeUploadImages", true);
        previewImages.value = [];


        if (files && files[0]) {
          for (var i = 0; i < files.length; i++) {
            let blob = files[i]

            if (blob.type == "image/heic") { // heic 변환
              await heic2any({ blob: blob, toType: "image/jpg" })
                .then(function (resultBlob) {
                  const file = new File([resultBlob], blob.name.split('.')[0] + ".jpg", { type: "image/jpg", lastModified: new Date().getTime() });
                  images.push(file);
                })
                .catch(function (x) {
                  console.log(x)
                })
            }
            else {
              images.push(blob);
            }
          }
        }

        // console.log(images);

        // compress image
        for (var i = 0; i < images.length; i++) {
          const imageFile = images[i]
          // console.log('originalFile instanceof Blob', imageFile instanceof Blob); // true
          // console.log(`originalFile size ${imageFile.size / 1024 / 1024} MB`);

          const options = {
            maxSizeMB: 1,
            maxWidthOrHeight: 1920,
            useWebWorker: true,
          }
          try {
            const compressedFile = await imageCompression(imageFile, options);
            // console.log('compressedFile instanceof Blob', compressedFile instanceof Blob); // true
            // console.log(`compressedFile size ${compressedFile.size / 1024 / 1024} MB`); // smaller than maxSizeMB

            images[i] = compressedFile;

            let reader = new FileReader;
            reader.onload = e => {
              previewImages.value.push(e.target.result);
            }
            reader.readAsDataURL(compressedFile);

          } catch (error) {
            console.log(error);
          }


        }
      }
      else {
        alert("파일은 최대 6개까지만 업로드 가능합니다. \n다시 선택해주세요.")
        return
      }
    }

    const uploadPost = async () => {    //글을 최종적으로 올릴 때
      const frm = new FormData();
      frm.append("userId", currentUser.id);
      frm.append("text", text.value)
      await PhotoService.uploadPhotoPost(frm).then((result) => {
        postId = result.data;
      });
      await uploadImage();
    }

    const cancelPost = () => {
      uploadImages.value = false;
      context.emit("changeUploadImages", false);
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


      text.value = "";
      uploadImages.value = false;
      context.emit("changeUploadImages", false);
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


    return {
      // methods
      pickFile,
      uploadPost,
      chooseFiles,
      clickImage,
      cancelPost: cancelPost,
      // checkSwitch,
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
</style>