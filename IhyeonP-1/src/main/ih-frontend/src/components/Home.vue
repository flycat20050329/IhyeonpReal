<template>
  <full-page :options="this.options" id="fullpage" ref="fullpage">
    <div class="section">
      <div class="text-center">
        <h1>home</h1>
      </div>
    </div>
    <div class="section">
      <div class="slide">
        <h1>홍보</h1>
      </div>
      <div class="slide">
        <TimeSchedule />
<<<<<<< Updated upstream
=======
      </div>
    </div>
    <div class="section text-center">
      <h1>홍보</h1>
    </div>
    <div class="section" v-if="currentUser">
      <div class="slide text-center">
        <h1>급식표</h1>
>>>>>>> Stashed changes
      </div>
      <div class="slide">
        <h1>학급일정</h1>
      </div>
    </div>
    <div class="section">
      <PhotoBook />
    </div>
    <div class="section">
      <h2>Section 4</h2>
    </div>
    <div class="section">
      <h2>Section 5</h2>
    </div>
  </full-page>

  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title text-center" id="exampleModalLabel">Sign in</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <Form @submit="handleLogin" :validation-schema="schema">
        <div class="form-group">
          <label for="username">Username</label>
          <Field name="username" type="text" class="form-control" />
          <ErrorMessage name="username" class="error-feedback" />
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <Field name="password" type="password" class="form-control" />
          <ErrorMessage name="password" class="error-feedback" />
        </div>

        <div class="form-group">
         회원가입이 필요하시면 <a href="/register">여기</a>
        </div>

        <div class="form-group">
          <button class="btn btn-primary btn-block" :disabled="loading" data-bs-dismiss="modal"  style="float: right;">
            <span
              v-show="loading"
              class="spinner-border spinner-border-sm"
            ></span>
            <span>Login</span>
          </button>
        </div>

        <div class="form-group">
          <div v-if="message" class="alert alert-danger" role="alert">
            {{ message }}
          </div>
        </div>
      </Form>
      </div>
    </div>
  </div>
</div>

</template>

<script>
import AuthService from '../services/auth.service';
import PhotoBook from "./PhotoBook.vue";
import TimeSchedule from './TimeSchedule.vue';
import { Form, Field, ErrorMessage } from "vee-validate";
import * as yup from "yup";

<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
export default {
  name: "Home",
  components: {
    TimeSchedule,
    PhotoBook,
    Form,
    Field,
    ErrorMessage,
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
  },
  data() {
    const schema = yup.object().shape({
      username: yup.string().required("Username is required!"),
      password: yup.string().required("Password is required!"),
    });

    return {
      options: {
        licenseKey: 'YOUR_KEY_HERE',
        afterLoad: this.afterLoad,
        scrollOverflow: true,
        scrollBar: false,
        menu: '#menu',
        navigation: false,
        anchors: ['page1', 'page2', 'page3', 'page4', 'page5'],
        sectionsColor: ['#ffffff', '#ffffff', '#ffffff', '#ffffff', '#1bcee6', '#ee1a59', '#2c3e4f', '#ba5be9', '#b4b8ab',]
      },
      data: ["hi", "I'm", "super", "star", "nice", "to", "meet", "you"],
      loading: false,
      message: "",
      schema,
    };
  },
  mounted() {
    this.flycat();
  },
  methods: {
    handleLogin(user) {
      this.loading = true;

      this.$store.dispatch("auth/login", user).then(
        () => {
          this.$router.push("/profile");
        },
        (error) => {
          this.loading = false;
          this.message =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
        }
      );
    },
    flycat() {
      AuthService.getTimeSchedule().then(res => {
        console.log(res.data);
      })
    },
    afterLoad() {
      console.log('After load')
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
    },
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
    created() {
    if (this.loggedIn) {
      this.$router.push("/profile");
    }
  },
  },
};
</script>

<style scoped>
label {
  display: block;
  margin-top: 10px;
}


.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}

.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 100px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.profile-img-card {
  width: 96px;
  height: 96px;
  margin: 0 auto 10px;
  display: block;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}

.error-feedback {
  color: red;
}
</style>