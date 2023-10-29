<template>
  <!-- imagePreview -->
  <splide :options="preoptions">
    <splide-slide v-for="(image, index) in previewImages">
      <div class="imagePreviewWrapper" :style="{ 'background-image': `url(${image})` }" @click="chooseFiles()">
      </div>
    </splide-slide>
  </splide>
</template>

<script>

import AuthService from '../services/auth.service';

export default {
  components: {
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
  mounted() {
    AuthService.getAllImage();
  }
}
</script>

<style></style>
