<template>
  <!-- imageViewer -->
  <div class="container">
    <div class="row">
      <div class="col-8">
        <splide :options="preoptions">
          <splide-slide v-for="image in images.images">
            <div class="imagePreviewWrapper" :style="{ 'background-image': `url(${image.image})` }">
            </div>
          </splide-slide>
        </splide>
      </div>
      <div class="col-4 detailContainer">
        <div id="userBox">
          <h4><font-awesome-icon icon="user" /> {{ images.post.user.username }}</h4>
        </div>
        <div id="textBox" class="row">
          <p>{{ images.post.text }}</p>
          <div id="heartBox" class="col align-self-end">
            <font-awesome-icon :icon="['far', 'heart']" /> <a>{{ images.post.heart }}</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import AuthService from '../services/auth.service';
import { Splide, SplideSlide } from '@splidejs/vue-splide';
import { watch } from 'vue';

export default {
  components: {
    Splide,
    SplideSlide
  },
  props: {
    // postImages: Array,
    images: Object,
  },
  setup(props, context) {
    const preoptions = {
      rewind: true,
      height: 500,
      gap: '1rem',
      perMove: 1,
      perPage: 1,
      pagination: false,
      postId: null,
      // arrows: false,
      // paginationDirection: 'ltr',
    };

    // watch(() => props.images, (newValue, oldValue) => {
    //   console.log('props.visible 의 변이가 감지되었을 때 ', { newValue, oldValue })
    // })

    // console.log(props.postImages);
    return {
      preoptions,
    }
  },
}
</script>

<style>
@import url(https://fonts.googleapis.com/css?family=Black+Han+Sans&display=swap);

.imagePreviewWrapper {
  height: 500px;
  max-width: 600px;
  /* object-fit: cover; */
  display: block;
  cursor: pointer;
  margin: 0 auto 30px;
  background-size: contain;
  background-repeat: no-repeat;
  background-position: center center;
  /* border: 1px solid gray; */
}

#textBox {
  background-color: whitesmoke;
  height: 350px;
  padding: 5px 10px 5px 0;
  /* font-family: "Black Han Sans"; */
}


#userBox {
  font-family: "Black Han Sans";
  padding: 5px 0 0 5px;
}

#heartBox {
  font-family: "Balck Han Sans";
  font-weight: bold;
  color: red;
}
</style>
