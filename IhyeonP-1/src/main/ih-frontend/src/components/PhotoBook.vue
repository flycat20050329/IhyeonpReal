<template>
  <div>
    <div class="imagePreviewWrapper" :style="{ 'background-image': `url(${previewImage})` }" @click="selectImage">
    </div>
    <input ref="fileInput" type="file" @input="pickFile">
  </div>
</template>
 
<script>
import axios from 'axios';

export default {
  data() {
    return {
      previewImage: null
    };
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  methods: {
    selectImage() {
      this.$refs.fileInput.click()
    },
    pickFile() {
      let input = this.$refs.fileInput
      let file = input.files
      if (file && file[0]) {
        let reader = new FileReader
        reader.onload = e => {
          this.previewImage = e.target.result
          this.saveImage(this.previewImage);
        }
        reader.readAsDataURL(file[0])
        this.$emit('input', file[0])
      }
    },
    saveImage(image) {
      // console.log(this.currentUser.id);
      axios.post('/api/auth/uploadImage', { image: image, user: this.currentUser.id, like: 0, text: "" })
        .then(res => {
          console.log(res.data)
        })
    }
  }
}
</script>
 
<style scoped lang="scss">
.imagePreviewWrapper {
  width: 250px;
  height: 100px;
  display: block;
  cursor: pointer;
  margin: 0 auto 30px;
  background-size: cover;
  background-position: center center;
}
</style>