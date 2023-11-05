<template>
  <full-page :options="this.options" id="fullpage" ref="fullpage">
    <div class="section">
      <div class="text-center">
        <h1>home</h1>
      </div>
    </div>
    <div class="section" v-if="currentUser">
      <div class="slide text-center">
        <input type="text" :value="searchTerm" @input="setSearchTerm" />
        <ul>
          <li v-for="num in filteredList">{{ num.name }}</li>
        </ul>
      </div>
      <div class="slide">
        <h1>학급일정</h1>
      </div>
    </div>
    <!-- Photo -->
    <div class="section" v-if="currentUser" style="height:fit-content">
      <Suspense>
        <PhotoBook :key="componentKey" @setInput="forceRerender" :mainimages="mainimages" />
      </Suspense>
    </div>
    <div class="section">
      <div class="slide">
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#schoolSearchModal">
          Launch demo modal
        </button>
      </div>
      <div class="slide">
        <TimeSchedule />
      </div>
    </div>
    <div class="section text-center">
      <Register />
    </div>
    <div class="section" v-if="currentUser">
      <h2>Section 4</h2>
    </div>
    <div class="section" v-if="currentUser">
      <h2>Section 5</h2>
    </div>
  </full-page>

  <!-- Photo Modal -->
  <div class="modal fade" id="photoModal" tabindex="-1" role="dialog" aria-labelledby="photoModalLabel"
    aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-body">
          <!-- <PhotoPost :images="imageData"></PhotoPost> -->
        </div>
      </div>
    </div>
  </div>

  <!-- Login Modal -->
  <div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title text-center" id="loginModalLabel">Sign in</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <Form @submit="handleLogin" :validation-schema="loginSchema">
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
              <button class="btn btn-primary btn-block" :disabled="loading" data-bs-dismiss="modal" style="float: right;">
                <span v-show="loading" class="spinner-border spinner-border-sm"></span>
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

  <!-- School Modal -->
  <div class="modal fade" id="schoolSearchModal" aria-hidden="true" aria-labelledby="schoolSearchModalLabel"
    tabindex="-1">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="schoolSearchModalLabel">학교 검색</h5>
        </div>
        <div class="modal-body">

        </div>
        <div class="modal-footer">
          <button class="btn btn-primary" data-bs-target="#signUpModal" data-bs-toggle="modal"
            data-bs-dismiss="modal">Open second modal</button>
        </div>
      </div>
    </div>
  </div>

  <!-- Register Modal -->
  <div class="modal fade" id="signUpModal" tabindex="-1" aria-labelledby="signUpModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="col-md-12">
          <Form @submit="handleRegister" :validation-schema="signupSchema">
            <div v-if="!successful">
              <div class="form-group">
                <label for="username">Username</label>
                <Field name="username" type="text" class="form-control" />
                <ErrorMessage name="username" class="error-feedback" />
              </div>
              <div class="form-group">
                <label for="email">Email</label>
                <Field name="email" type="email" class="form-control" />
                <ErrorMessage name="email" class="error-feedback" />
              </div>
              <div class="form-group">
                <label for="password">Password</label>
                <Field name="password" type="password" class="form-control" />
                <ErrorMessage name="password" class="error-feedback" />
              </div>
              <div class="form-group">
                <label for="schoolCode">schoolCode</label>
                <Field name="schoolCode" type="schoolCode" class="form-control" />
                <ErrorMessage name="schoolCode" class="error-feedback" />
              </div>
              <div class="form-group">
                <label for="grade">grade</label>
                <Field name="grade" type="grade" class="form-control" />
                <ErrorMessage name="grade" class="error-feedback" />
              </div>
              <div class="form-group">
                <label for="ban">ban</label>
                <Field name="ban" type="ban" class="form-control" />
                <ErrorMessage name="ban" class="error-feedback" />
              </div>
              <div class="row">
                <div class="col-1">
                  <div class="form-check">
                    <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1" checked>
                    <label class="form-check-label" for="flexRadioDefault1">
                      학생
                    </label>
                  </div>
                </div>
                <div class="col-1">
                  <div class="form-check">
                    <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2">
                    <label class="form-check-label" for="flexRadioDefault2">
                      교사
                    </label>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <button class="btn btn-primary btn-block" :disabled="loading">
                  <span v-show="loading" class="spinner-border spinner-border-sm"></span>
                  Sign Up
                </button>
              </div>
            </div>
          </Form>
          <div v-if="message" class="alert" :class="successful ? 'alert-success' : 'alert-danger'">
            {{ message }}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AuthService from '../services/auth.service';
import PhotoBook from "./PhotoBook.vue";
import TimeSchedule from './TimeSchedule.vue';
import Register from './Register.vue';
import { Form, Field, ErrorMessage } from "vee-validate";
import * as yup from "yup";
import { ref } from "vue";

export default {
  name: "Home",
  components: {
    TimeSchedule,
    PhotoBook,
    Register,
    Form,
    Field,
    ErrorMessage,
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
    const loginSchema = yup.object().shape({
      username: yup.string().required("Username is required!"),
      password: yup.string().required("Password is required!"),
    });

    const signupSchema = yup.object().shape({
      username: yup
        .string()
        .required("닉네임이 필요합니다!")
        .min(2, "한글자 닉네임을 누가 쓰냐")
        .max(20, "이렇게 길게 적어서 뭐하시게요?"),
      email: yup
        .string()
        .required("이메일을 적어주세요!")
        .email("이메일을 \"제대로\" 적어주세요!")
        .max(50, "누가 이메일을 50글자 이상 적냐"),
      password: yup
        .string()
        .required("비밀번호를 넣어주세요!")
        .min(6, "너무 취약합니다!")
        .max(40, "너무 안전합니다!"),
      schoolCode: yup
        .string()
        .required("Password is required!")
        .min(6, "Must be at least 6 characters!")
        .max(40, "Must be maximum 40 characters!"),
      grade: yup
        .string()
        .required("몇 학년인지 적어주세요!"),
      ban: yup
        .string()
        .required("몇 반인지 적어주세요!"),
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
        sectionsColor: ['#ffffff', '#ffffff', '#ffffff', '#ffffff', '#ffffff', '#ee1a59', '#2c3e4f', '#ba5be9', '#b4b8ab',],
        controlArrows: false,
      },
      loading: false,
      successful: false,
      message: "",
      loginSchema,
      signupSchema,
      currentTab: 0,
      schoolName: "",
      location: "",
      grade: "",
      ban: "",
      dataList: [
        {
          name: 'One',
          value: 'one'
        },
        {
          name: 'Two',
          value: 'two'
        },
        {
          name: 'Three',
          value: 'three'
        },
        {
          name: 'Four',
          value: 'four'
        }
      ],
      searchTerm: '',
    };
  },
  mounted() {
    this.flycat();
  },
  methods: {

    setSearchTerm(e) {
      this.searchTerm = e.target.value;
    },
    handleRegister(user) {
      this.message = "";
      this.successful = false;
      this.loading = true;

      this.$store.dispatch("auth/register", user).then(
        (data) => {
          this.message = data.message;
          this.successful = true;
          this.loading = false;
        },
        (error) => {
          this.message =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
          this.successful = false;
          this.loading = false;
        }
      );
    },
    handleLogin(user) {
      this.loading = true;

      this.$store.dispatch("auth/login", user).then(
        () => {
          this.$router.push("/");
          this.$router.go();
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
      });

      AuthService.getSchoolInfo().then(res => {
        console.log(typeof (res.data));
        console.log(res.data);

        if (typeof (res.data) == JSON) {
          this.dataList = res.data;
        }

      })
    },
    forceRerender() {

      const mainimages = [];

      AuthService.getAllImage().then((result) => {
        for (var i = 0; i < result.data.length; i++) {
          result.data[i].image = "data:image/png;base64," + result.data[i].image
        }
        mainimages.value = result.data;
      });

      this.mainimages = mainimages;
      this.componentKey += 1;
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
    filteredList() {
      if (this.searchTerm === '') {
        return this.dataList;
      }
      return this.dataList.filter(num => {
        if (num.value.includes(this.searchTerm)) {
          return num;
        }
      })
    },
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

<style scoped>
label {
  display: block;
  margin-top: 10px;
}

.error-feedback {
  color: red;
}

* {
  box-sizing: border-box;
}

body {
  background-color: #f1f1f1;
}

#regForm {
  background-color: #ffffff;
  margin: 100px auto;
  font-family: Raleway;
  padding: 40px;
  width: 70%;
  min-width: 300px;
}

h1 {
  text-align: center;
}

input {
  padding: 10px;
  width: 100%;
  font-size: 17px;
  font-family: Raleway;
  border: 1px solid #aaaaaa;
}
</style>