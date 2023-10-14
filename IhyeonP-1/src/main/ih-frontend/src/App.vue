<template>
  <div id="app">
    <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"> -->
    <nav id="menu" class="">
      <li class="col-1"></li>
      <li class="col-1">
        <a class="btn text-dark" href="/" role="button">
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
          학급
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
    axios
  },
  data() {
    
    return {
      options: {
        licenseKey: 'YOUR_KEY_HERE',
        afterLoad: this.afterLoad,
        scrollOverflow: false,
        scrollBar: false,
        menu: '#menu',
        navigation: false,
        anchors: ['page1', 'page2', 'page3', 'page4', 'page5'],
        sectionsColor: ['#41b883', '#ff5f45', '#0798ec', '#fec401', '#1bcee6', '#ee1a59', '#2c3e4f', '#ba5be9', '#b4b8ab']
      }
    }
  }, mounted() {
    console.log("mounted");
  },
  methods: {
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
      this.$router.push('/login');
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
#menu {
  border-bottom: 2px solid rgb(0, 0, 0);
  background-color: white;
  position: fixed;
  top: 0;
  width: 100%;
  /* height: 7%; */
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>