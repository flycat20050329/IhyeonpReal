<template>
  <!-- imageViewer -->
  <div class="container">
    <div class="row">
      <div class="col">
        <splide :options="preoptions">
          <splide-slide v-for="image in images.images">
            <div class="imagePreviewWrapper" :style="{ 'background-image': `url(${image.image})` }">
            </div>
          </splide-slide>
        </splide>
      </div>
      <div class="col textContainer">
        <!-- <p>{{ images.post }}</p> -->
      </div>
    </div>
  </div>
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
    images: Object,
  },
  setup(props, context) {
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

    context.emit("images", props.images);

    // console.log(props.postImages);
    return {
      preoptions,
    }
  },
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

.textContainer {
  background-color: whitesmoke;
}
</style>
