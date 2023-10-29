<template>
  <!-- imagePreview -->
  <splide :options="preoptions">
    <splide-slide v-for="image in images">
      <div class="imagePreviewWrapper" :style="{ 'background-image': `url(${image.image})` }">
      </div>
    </splide-slide>
  </splide>
</template>

<script>

import AuthService from '../services/auth.service';
import { Splide, SplideSlide } from '@splidejs/vue-splide';

export default {
  components: {
    Splide,
    SplideSlide
  },
  props: {
    postImages: Array,
  },
  data() {
    return {
      images: this.postImages,
    }
  },
  setup() {
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
    return {
      preoptions,
    }
  },
  watch: {
    postImages() {
      this.images = this.postImages;
    }
  },
  mounted() {
    // console.log(this.postImages);
  }
}
</script>

<style>
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
</style>
