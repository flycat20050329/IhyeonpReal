<template>
  <div id="app">
    <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"> -->

    <!-- nav bar -->
    <nav id="menu">
      <li class="col-1"></li>
      <li class="col-1">
        <a class="btn text-dark " href="/" role="button">
          Home
        </a>
      </li>
      <li class="col-1">
        <a class="btn text-dark" href="#page2" role="button">
          학교
        </a>
      </li>
      <li class="col-1">
        <a class="btn text-dark" href="#page3" role="button">
          Photo
        </a>
      </li>
      <li class="col-1">
        <a class="btn text-dark" href="#page4" role="button">
          동아리
        </a>
      </li>
      <li class="col-1">
        <a class="btn text-dark" href="#page5" role="button">
          중고거래
        </a>
      </li>
      <li class="col-3">
        <a class="btn text-dark" @click="Please()" role="button">
          문의
        </a>
      </li>
      <li class="col-2" v-if="!currentUser">
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#loginModal" style="float: right;">
  login
</button>
      </li>
      <!-- <li class="col-2" v-if="!currentUser">
        <router-link to="/login" class="nav-link text-dark">
          <font-awesome-icon icon="sign-in-alt" /> Login
        </router-link>
      </li> -->
      <li class="col-1" v-if="currentUser">
        <router-link to="/profile" class="nav-link btn text-dark">
          <font-awesome-icon icon="user" />
          {{ currentUser.username }}
        </router-link>
      </li>
      <li class="col-1" v-if="currentUser">
        <a class="nav-link btn text-dark" @click.prevent="logOut">
          <font-awesome-icon icon="sign-out-alt" /> LogOut
        </a>
      </li>
    </nav>
  </div>

  <div class="container">
    <router-view />
  </div>
</template>

<script>
import 'vue-fullpage.js/dist/style.css'
import VueFullPage from 'vue-fullpage.js'
import AuthService from './services/auth.service.js'
import axios from 'axios';


export default {
  name: 'app',
  components: {
    VueFullPage,
    axios,
  },
  data() {
    return {
    }
  }, mounted() {

    console.log("mounted");
  },
  methods: {
    changePopState() {
      this.popState = !this.popState;
    },


    afterLoad() {
      console.log('After load')
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
    }
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
  padding: 1.1em;
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