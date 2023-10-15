<template>
  <full-page :options="this.options" id="fullpage" ref="fullpage">
    <div class="section text-center">
      <h1>홍보</h1>
    </div>
    <div class="section" v-if="currentUser">
      <div class="slide text-center">
        <h1>급식표</h1>
      </div>
      <div class="slide text-center">
        <h1>시간표</h1>
      </div>
      <div class="slide text-center">
        <h1>학급일정</h1>
      </div>
    </div>
    <div class="section" v-if="currentUser">
      <Suspense>
        <PhotoBook :key="componentKey" @setInput="forceRerender" :mainimages="mainimages" />
      </Suspense>
    </div>
    <div class="section" v-if="currentUser">
      <h2>Section 4</h2>
    </div>
    <div class="section" v-if="currentUser">
      <h2>Section 5</h2>
    </div>
  </full-page>
</template>

<script>
import AuthService from '../services/auth.service';
import PhotoBook from "./PhotoBook.vue";
import { ref } from "vue";

export default {
  name: "Home",
  components: {
    PhotoBook,
  },
  setup() {
    const componentKey = ref(0);
    const mainimages = ref([]);

    return {
      componentKey,
      mainimages,
    }
  },
  data() {
    return {
      options: {
        licenseKey: 'YOUR_KEY_HERE',
        afterLoad: this.afterLoad,
        scrollOverflow: true,
        scrollBar: false,
        menu: '#menu',
        navigation: false,
        anchors: ['page1', 'page2', 'page3', 'page4', 'page5'],
        sectionsColor: ['#ffffff', '#ffffff', '#ffffff', '#ffffff', '#ffffff', '#ee1a59', '#2c3e4f', '#ba5be9', '#b4b8ab',],
      },
    };
  },
  methods: {

    forceRerender() {

      const mainimages = [];

      AuthService.getImage().then((result) => {
        for (var i = 0; i < result.data.length; i++) {
          result.data[i].image = "data:image/png;base64," + result.data[i].image
        }
        mainimages.value = result.data;
      });

      this.mainimages = mainimages;
      this.componentKey += 1;
    },

    toggleNavigation() {
      this.options.navigation = !this.options.navigation
    },

    toggleScrollbar() {
      console.log('Changing scrollbar...')
      this.options.scrollBar = !this.options.scrollBar
    },
    Please() {
      AuthService.please().then(
        (response) => {
          this.content = response.data;
          console.log(this.content);
        },
        (error) => {
          this.content =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
        }
      );
    },
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    },
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    showAdminBoard() {
      if (this.currentUser && this.currentUser['roles']) {
        return this.currentUser['roles'].includes('ROLE_ADMIN');
      }

      return false;
    },
    showModeratorBoard() {
      if (this.currentUser && this.currentUser['roles']) {
        return this.currentUser['roles'].includes('ROLE_TEACHER');
      }

      return false;
    }
  }
};
</script>

<style></style>