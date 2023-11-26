<template>
  <full-page :options="this.options" id="fullpage" ref="fullpage">
    <div class="section">
      <div class="text-center">
        <h1>home</h1>
      </div>
    </div>

    <div class="section" v-if="currentUser">
      <h1>학급일정</h1>
    </div>

    <!-- Photo -->
    <div class="section" v-if="currentUser" style="height:fit-content">
      <Suspense>
        <PhotoBook :key="componentKey" @setInput="setInput" @rerender="forceRerender" @imageData="getImageData" />
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
          <select class="form-select" aria-label="Default select example" @change="getSelect()">
            <option selected value="">지역을 선택해주세요.</option>
            <option value="B10">서울</option>
            <option value="C10">부산</option>
            <option value="D10">대구</option>
            <option value="E10">인천</option>
            <option value="F10">대전</option>
            <option value="G10">광주</option>
            <option value="H10">울산</option>
            <option value="I10">세종시</option>
            <option value="J10">경기도</option>
            <option value="K10">강원도</option>
            <option value="M10">충청북도</option>
            <option value="N10">충청남도</option>
            <option value="Q10">전라남도</option>
            <option value="P10">전라북도</option>
            <option value="R10">경상북도</option>
            <option value="S10">경상남도</option>
            <option value="T10">제주도</option>
          </select>

          <div class="input-group mb-3">
            <!-- <span class="input-group-text" id="schoolNameInput" >@</span> -->
            <input type="text" aria-label="Username" id="schoolNameInput" placeholder="학교 이름을 입력하세요"
              aria-describedby="basic-addon1">
          </div>
        </div>
        <div class="modal-footer">
          <button class="btn btn-primary" data-bs-target="#schoolSelectModal" data-bs-toggle="modal"
            v-on:click="getschoolInfo()" data-bs-dismiss="modal">학교 선택</button>
        </div>
      </div>
    </div>
  </div>
  <div class="modal fade" id="schoolSelectModal" tabindex="-1" aria-labelledby="schoolSelectModalLabel"
    aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="schoolSearchModalLabel">학교 검색</h5>
        </div>
        <div class="modal-body">
          <select class="form-select form-select-lg mb-3" aria-label="schoolNameSelect" id="schoolSelect">
            <option selected>학교 선택</option>
            <option v-for="value in this.schoolNameList">
              {{ value }}
            </option>
          </select>
        </div>
        <div class="modal-footer">
          <button class="btn btn-primary" data-bs-target="#schoolSearchModal" data-bs-toggle="modal"
            data-bs-dismiss="modal">학교 검색으로 돌아가기</button>
          <button class="btn btn-primary" data-bs-target="#signUpModal" data-bs-toggle="modal"
            v-on:click="schoolNameRegister()" data-bs-dismiss="modal">인적사항 입력</button>
        </div>
      </div>
    </div>
  </div>

  <!-- Photo Modal -->
  <div class="modal fade" id="photoModal" tabindex="-1" role="dialog" aria-labelledby="photoModalLabel"
    aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-xl" role="document">
      <div class="modal-content">
        <div class="modal-body">
          <PhotoPost :images="imageData"></PhotoPost>
        </div>
      </div>
    </div>
  </div>

  <!-- Button trigger photoModal -->
  <button type="button" id="photoModalBtn" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#photoModal"
    style="display:none;">
  </button>

  <!-- Register Modal -->
  <div class="modal fade" id="signUpModal" tabindex="-1" aria-labelledby="signUpModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-body">
          <div class="col-md-12">
            <Form @submit="handleRegister" :validation-schema="signupSchema">
              <div v-if="!successful">
                <fieldset disabled>
                  <div class="form-group">
                    <label for="disabledTextInput" class="form-label">학교 이름</label>
                    <input type="text" id="disabledTextInput" class="form-control" :value=schoolName>
                  </div>
                </fieldset>
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
                  <div class="col-7"></div>
                  <div class="form-group col-3 mt-3">
                    <button class="btn btn-primary btn-block" :disabled="loading">
                      <span v-show="loading" class="spinner-border spinner-border-sm"></span>
                      Sign Up
                    </button>
                  </div>
                </div>
              </div>
            </Form>
            <div v-if="message" class="alert" :class="successful ? 'alert-success' : 'alert-danger'">
              {{ message }}
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button class="btn btn-primary" data-bs-target="#schoolSelectModal" data-bs-toggle="modal"
            data-bs-dismiss="modal">학교 선택으로 돌아가기</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import AuthService from '../services/auth.service';
import PhotoService from '../services/photo.service';
import PhotoBook from "./PhotoBook.vue";
import TimeSchedule from './TimeSchedule.vue';
import Register from './Register.vue';
import { Form, Field, ErrorMessage } from "vee-validate";
import * as yup from "yup";
import { ref } from "vue";

import PhotoPost from "./PhotoPost.vue";
import { usePhotoStore } from "../store/photo.js";

export default {
  name: "Home",
  components: {
    TimeSchedule,
    PhotoBook,
    Register,
    Form,
    Field,
    ErrorMessage,
    PhotoPost,
  },
  setup() {
    const componentKey = ref(0);
    const mainimages = ref([]);

    const photoStore = usePhotoStore();

    return {
      componentKey,
      mainimages,
      photoStore
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
      schoolNameList: [],
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
      imageData: { images: null, post: { text: null, user: { username: null } } },
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
  created() {
  },
  methods: {
    getImageData(imageData) {
      document.getElementById("photoModalBtn").click();
      this.imageData = imageData;
    },
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

      // AuthService.getSchoolInfo().then(res => {
      //   console.log(typeof (res.data));
      //   console.log(res.data);

      //   if (typeof (res.data) == JSON) {
      //     this.dataList = res.data;
      //   }

      // })
    },
    // async setInput() {
    //   const mainimages = [];

    //   await PhotoService.getAllImage().then((result) => {
    //     for (var i = 0; i < result.data.length; i++) {
    //       result.data[i].image = "data:image/png;base64," + result.data[i].image
    //     }
    //     mainimages.value = result.data;
    //   });
    //   this.photoStore.setPhotos(mainimages);
    //   // this.componentKey += 1;
    // },
    forceRerender() {
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