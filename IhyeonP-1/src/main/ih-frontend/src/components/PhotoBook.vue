<template>
  <div>
    <div class="topBar container" v-if="!uploadImages">
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
    <button class="btn btn-outline-dark" @click="getImage()">get Image</button>
    <div class="container">
      <div class="row" v-if="uploadImages">
        <div class="col-5">
          <!-- <div class="imagePreviewWrapper" :style="{ 'background-image': `url(${image})` }" v-text="index + 1"
            @click="getImage"></div> -->
          <!-- <button @click="removeImage(index)">Remove image</button> -->
          <splide :options="options">
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

import { Splide, SplideSlide } from '@splidejs/vue-splide';

// import '@splidejs/splide/dist/css/themes/splide-default.min.css';
// import '@splidejs/splide/dist/css/themes/splide-sea-green.min.css';
import '@splidejs/splide/dist/css/themes/splide-skyblue.min.css';

export default {
  components: {
    Splide,
    SplideSlide,
  },
  data() {
    return {
      previewImages: [],
      uploadImage: null,
      text: null,
      imgData: null,
      images: [],
      uploadImages: false,
      options: {
        rewind: true,
        height: 300,
        gap: '1rem',
        perMove: 1,
        pagination: false,
        postId: null,
        // arrows: false,
        // paginationDirection: 'ttb',
      },
    };
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
  },
  methods: {

    // --- image ----
    getImage() {
      // console.log(this.images);
      AuthService.getImage().then((result) => {
        console.log(result.data[0]);
        // this.uploadImage = atob(result.data[0].image);

        // "data:image/png;base64," + 
      });
    },
    pickFile(e) {
      if (e.target.files[0] && e.target.files.length < 7) {
        this.uploadImages = true;
        this.previewImages = [];
        var files = e.target.files || e.dataTransfer.files;
        this.images = files;
        if (files && files[0]) {
          for (var i = 0; i < files.length; i++) {
            let reader = new FileReader;
            reader.onload = e => {
              this.previewImages.push(e.target.result);
              // this.images.push(e.target.result);
              // this.saveImage();
            }
            reader.readAsDataURL(files[i])
          }

          // this.saveImage();
          // this.$emit('input', file[0])
          // this.imgData = files[0];
        }
      }
    },

    async savePost() {
      const frm = new FormData();
      frm.append("id", this.currentUser.id);
      frm.append("heart", 0);
      frm.append("text", this.text);

      await AuthService.saveImagePost(frm).then((result) => {
        this.postId = result.data;
      });
      this.uploadImages = false;
      this.text = null;
      this.saveImage();
    },

    async saveImage() {
      const frm2 = new FormData();
      for (const file of this.images) {
        frm2.append('images', file)
      }

      frm2.append("postId", this.postId);

      AuthService.saveImage(frm2);
    },

    chooseFiles() {
      document.getElementById("fileUpload").click()
    }
  },
}
</script>

 
<style scoped lang="scss">
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