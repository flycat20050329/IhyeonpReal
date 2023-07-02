<template>
  <!-- <nav id="menu">
    <li class="col-1"></li>
    <li class="col-2">
      <a class="btn btn-outline-secondary text-dark" href="#page1" role="button">
        학교
      </a>
    </li>
    <li class="col-2">
      <a class="btn btn-outline-secondary text-dark" href="#page2" role="button">
        학급
      </a>
    </li>
    <li class="col-2">
      <a class="btn btn-outline-secondary text-dark" href="#page3" role="button">
        동아리
      </a>
    </li>
    <li class="col-3">
      <a class="btn btn-outline-secondary text-dark" @click="Please()" role="button">
        문의
      </a>
    </li>
    <li class="col-2">
      <div v-if="!currentUser" class="navbar-nav ml-auto">
    <li class="nav-item">
      <router-link to="/register" class="nav-link text-dark">
        <font-awesome-icon icon="user-plus" /> Sign Up
      </router-link>
    </li>
    <li class="nav-item">
      <router-link to="/login" class="nav-link text-dark">
        <font-awesome-icon icon="sign-in-alt" /> Login
      </router-link>
    </li>
    </div>
    </li>
  </nav> -->
  <full-page :options="this.options" id="fullpage" ref="fullpage">

    <div class="section">
      <h1>홍보</h1>
    </div>
    <div class="section">
      <h1>Slide 2</h1>
    </div>
    <div class="section">
      <h2>Section 3</h2>
    </div>
    <div class="section">
      <h2>Section 4</h2>
    </div>
    <div class="section">
      <h2>Section 5</h2>
    </div>
  </full-page>

  <!-- <nav class="navbar navbar-expand navbar-dark bg-dark">
    <a href="/" class="navbar-brand">IH-highschool</a>
    <div class="navbar-nav mr-auto">
      <li class="nav-item">
        <router-link to="/home" class="nav-link">
          <font-awesome-icon icon="home" /> Home
        </router-link>
      </li>
      <li v-if="showAdminBoard" class="nav-item">
        <router-link to="/admin" class="nav-link">Admin Board</router-link>
      </li>
      <li v-if="showModeratorBoard" class="nav-item">
        <router-link to="/mod" class="nav-link">Moderator Board</router-link>
      </li>
      <li class="nav-item">
        <router-link v-if="currentUser" to="/user" class="nav-link">User</router-link>
      </li>
    </div>

    <div v-if="!currentUser" class="navbar-nav ml-auto">
      <li class="nav-item">
        <router-link to="/register" class="nav-link">
          <font-awesome-icon icon="user-plus" /> Sign Up
        </router-link>
      </li>
      <li class="nav-item">
        <router-link to="/login" class="nav-link">
          <font-awesome-icon icon="sign-in-alt" /> Login
        </router-link>
      </li>
    </div>

    <div v-if="currentUser" class="navbar-nav ml-auto">
      <li class="nav-item">
        <router-link to="/profile" class="nav-link">
          <font-awesome-icon icon="user" />
          {{ currentUser.username }}
        </router-link>
      </li>
      <li class="nav-item">
        <a class="nav-link" @click.prevent="logOut">
          <font-awesome-icon icon="sign-out-alt" /> LogOut
        </a>
      </li>
    </div>
  </nav> -->
</template>

<script>
import AuthService from '../services/auth.service';


export default {
  name: "Home",
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
        sectionsColor: ['#ffffff', '#ffffff', '#ffffff', '#ffffff', '#1bcee6', '#ee1a59', '#2c3e4f', '#ba5be9', '#b4b8ab']
      }
    };
  },
  mounted() {
  },
  methods: {
    afterLoad() {
      // console.log('After load')
    },

    addSection(e) {
      e.preventDefault()
      var newSectionNumber = document.querySelectorAll('.fp-section').length + 1

      // creating the section div
      var section = document.createElement('div')
      section.className = 'section'
      section.innerHTML = `<h3>Section ${newSectionNumber}</h3>`

      // adding section
      document.querySelector('#fullpage').appendChild(section)

      // creating the section menu element
      var sectionMenuItem = document.createElement('li')
      sectionMenuItem.setAttribute('data-menuanchor', 'page' + newSectionNumber)
      sectionMenuItem.innerHTML = `<a href="#page${newSectionNumber}">Section${newSectionNumber}</a>`

      // adding it to the sections menu
      var sectionsMenuItems = document.querySelector('#menu')
      sectionsMenuItems.appendChild(sectionMenuItem)

      // adding anchor for the section
      this.options.anchors.push(`page${newSectionNumber}`)

      // we have to call `update` manually as DOM changes won't fire updates
      // requires the use of the attribute ref="fullpage" on the
      // component element, in this case, <full-page>
      // ideally, use an ID element for that element too
      this.$refs.fullpage.build()
    },

    removeSection() {
      var sections = document.querySelector('#fullpage').querySelectorAll('.fp-section')
      var lastSection = sections[sections.length - 1]

      // removing the last section
      lastSection.parentNode.removeChild(lastSection)

      // removing the last anchor
      this.options.anchors.pop();

      // removing the last item on the sections menu
      var sectionsMenuItems = document.querySelectorAll('#menu li')
      var lastItem = sectionsMenuItems[sectionsMenuItems.length - 1]
      lastItem.parentNode.removeChild(lastItem)
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
    }
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
