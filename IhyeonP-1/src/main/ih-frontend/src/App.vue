<template>
  <div id="app">
    <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"> -->

    <!-- nav bar -->
    <nav id="menu">
      <div class="row justify-content-between">
        <li class="col-1"></li>
        <li class="col-1">
          <a class="btn text-dark " href="/" role="button">
            Home
          </a>
        </li>
        <li class="col-1" v-if="currentUser">
          <a class="btn text-dark" href="#page2" role="button">
            학교
          </a>
        </li>
        <li class="col-1" v-if="currentUser">
          <a class="btn text-dark" href="#page3" role="button">
            Photo
          </a>
        </li>
        <li class="col-1" v-if="currentUser">
          <a class="btn text-dark" href="#page4" role="button">
            동아리
          </a>
        </li>
        <li class="col-1" v-if="currentUser">
          <a class="btn text-dark" href="#page5" role="button">
            중고
          </a>
        </li>
        <li :class="currentUser ? 'col-2' : 'col-7'">
          <a class="btn text-dark" href="#page6" @click="Please()" role="button">
            문의
          </a>
        </li>
        <li class="col-2" v-if="!currentUser">
          <a class="btn text-dark" data-bs-toggle="modal" data-bs-target="#schoolSearchModal">Sign Up</a>
          <a class="btn text-dark" data-bs-toggle="modal" data-bs-target="#loginModal">Sign In</a>
        </li>
        <li class="col-2" v-if="currentUser">
          <router-link to="/" class="nav-link btn text-dark">
            <font-awesome-icon icon="user" />
            {{ currentUser.username }}
          </router-link>
        </li>
        <li class="col-2" v-if="currentUser">
          <a class="nav-link btn text-dark" @click.prevent="logOut">
            <font-awesome-icon icon="sign-out-alt" /> LogOut
          </a>
        </li>
      </div>
    </nav>
  </div>

  <div class="container">
    <router-view></router-view>
  </div>
</template>

<script>
import 'vue-fullpage.js/dist/style.css'
import VueFullPage from 'vue-fullpage.js'
import AuthService from './services/auth.service.js'
import axios from 'axios';

import { usePhotoStore } from "./store/photo.js";
import PhotoService from "./services/photo.service.js";

import moment from "moment";

export default {
  name: 'app',
  components: {
    VueFullPage,
    axios,
  },
  data() {
    return {
    }
  },
  mounted() {
  },
  async created() {
    const photoStore = usePhotoStore();

    await PhotoService.getAllPhoto().then((result) => {
      for (var i = 0; i < result.data.length; i++) {
        result.data[i].image = "data:image/png;base64," + result.data[i].image
      }
      photoStore.setAllPhotos(result.data);
    })

    const startDate = new Date(new Date().setDate(new Date().getDate() - 7));
    const endDate = new Date();

    const startDateStr = moment(startDate).format('YYYY-MM-DD 00:00:00.000000');
    const endDateStr = moment(endDate).format('YYYY-MM-DD 11:59:59.999999');

    // console.log([startDateStr, endDateStr]);


    photoStore.setPhotos(photoStore.getAllPhotos.filter(photo => {
      const photoTime = moment(photo.photoPost.uploadedOn).format("YYYY-MM-DD HH:mm:ss.SSSSSS");
      if (startDateStr <= photoTime && photoTime <= endDateStr) {
        return photo;
      }
    }
    ))

  },
  methods: {
    changePopState() {
      this.popState = !this.popState;
    },

    toggleNavigation() {
      this.options.navigation = !this.options.navigation
    },

    toggleScrollbar() {
      console.log('Changing scrollbar...')
      this.options.scrollBar = !this.options.scrollBar
    },
    Please() {
      AuthService.getLunchList().then(
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
      // this.$router.push('/login');
      this.changePopState();
    },

  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    currentPage() {

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

<style>
@import url(https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap);

#menu {
  border-bottom: 2px solid rgb(0, 0, 0);
  background-color: white;
  position: fixed;
  top: 0;
  width: 100%;
  /* height: 9%; */
}

#menu a {
  padding: 0.8em;
  font-size: large;
}

div #app {
  font-family: 'Do Hyeon', sans-serif;
  font-size: 100%;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0;
}

p {
  margin: auto;
}
</style>