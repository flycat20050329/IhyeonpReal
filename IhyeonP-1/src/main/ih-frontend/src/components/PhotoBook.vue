<template>
  <div>
    <div v-for="(image, index) in images">
      <div class="imagePreviewWrapper" :style="{ 'background-image': `url(${image})` }" v-text="index + 1" @click="getImage"/>
      <!-- <button @click="removeImage(index)">Remove image</button> -->
    </div>
    <!-- <div class="imagePreviewWrapper" :style="{ 'background-image': `url(${previewImage})` }" @click="getImage" />
    <div class="imagePreviewWrapper" :style="{ 'background-image': `url(${uploadImage})` }" /> -->
    <input class="form-control" type="file" @input="pickFile" multiple>
  </div>
</template>
 
<script>
import axios from 'axios';
import AuthService from '../services/auth.service';
import { Form } from 'vee-validate';

export default {
  components: {
  },
  data() {
    return {
      previewImage: null,
      uploadImage: null,
      text: null,
      imgData: null,
      images: [],
    };
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
  },
  methods: {
    // ===========================================
    removeImage(index) {
      this.images.splice(index, 1)
    },
    getImage() {
      // console.log(this.images);
      AuthService.getImage().then((result) => {
        console.log(result.data);
        this.uploadImage = atob(result.data[0].image);

        // "data:image/png;base64," + 
      });
    },
    pickFile(e) {
      var files = e.target.files || e.dataTransfer.files;
      if (files && files[0]) {
        for (var i = 0; i < files.length; i++) {
          let reader = new FileReader;
          reader.onload = e => {
            this.previewImage = e.target.result;
            this.images.push(e.target.result);
            // this.saveImage();
          }
          reader.readAsDataURL(files[i])
        }

        this.saveImage();
        // this.$emit('input', file[0])
        // this.imgData = files[0];
      }
    },
    async saveImage() {
      var postData = {"user" : this.currentUser.id, "heart": 0, "text": this.text};
      const frm = new FormData();
      frm.append("data", JSON.stringify(postData));
      AuthService.saveImagePost(frm);
      // var data = { "image": images };
      // const frm2 = new FormData();
      // frm.append("data", JSON.stringify(data));
      // AuthService.saveImage(frm2);
    },
  }
}
</script>
 
<style scoped lang="scss">
.imagePreviewWrapper {
  width: 250px;
  height: 250px;
  display: block;
  cursor: pointer;
  margin: 0 auto 30px;
  background-size: cover;
  background-position: center center;
}
</style>