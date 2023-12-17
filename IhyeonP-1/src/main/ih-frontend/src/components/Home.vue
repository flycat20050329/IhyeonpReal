<template>
  <full-page :options="this.options" id="fullpage" ref="fullpage">

    <div class="section">
      <div class="text-center">
        <h1>home</h1>
      </div>
    </div>

    <div class="section" v-if="currentUser">
      <div class="slide">
        <h1>학급일정 →→→</h1>
      </div>
      <div class="slide">
        <TimeSchedule />
      </div>
    </div>

    <!-- Photo -->
    <div class="section" style="height:fit-content" v-if="currentUser">
      <Suspense>
        <PhotoBook :key="componentKey" @rerender="forceRerender" @imageData="getImageData" />
        <!--  @imageData="getImageData"  -->
      </Suspense>
    </div>

    <!-- <div class="section"  v-if="currentUser">
      <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#schoolSearchModal">
        Launch demo modal
      </button>
    </div> -->

    <!-- <div class="section text-center">
      <Register />
    </div> -->

    <div class="section" v-if="currentUser">
      <h2>동아리</h2>
    </div>

    <div class="section" v-if="currentUser">
      <h2>중고</h2>
    </div>

    <div class="section" v-if="currentUser">
      <h2>문의</h2>
    </div>

  </full-page>

  <!-- Login Modal -->
  <div class="modal fade" ref="loginModal" id="loginModal" tabindex="-1" aria-labelledby="loginModalLabel"
    aria-hidden="true">
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
              회원가입이 필요하시면 <a class="text-dark" data-bs-toggle="modal" data-bs-target="#schoolSearchModal"
                href="/register">Click!</a>
            </div>

            <div class="form-group">
              <button class="btn btn-primary btn-block" :disabled="loading" style="float: right;">
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
  <div class="modal fade" ref="photoModal" id="photoModal" tabindex="-1" role="dialog"
    aria-labelledby="photoModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-xl" role="document">
      <div class="modal-content">
        <div class="modal-body">
          <PhotoPost :images="imageData" ref="photoPost"></PhotoPost>
          <!-- <button type="button" id="deletePhotoModalBtn" class="btn btn-primary" data-bs-toggle="modal"
            data-bs-target="#deletePhotoModal">
            modal 버튼
          </button> -->
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
          <Form @submit="handleRegister" :validation-schema="signupSchema" v-slot="{ isSubmitting }">
            <div v-if="!successful">
              <fieldset disabled>
                <div class="form-group">
                  <label for="disabledTextInput" class="form-label">학교 이름</label>
                  <input type="text" id="disabledTextInput" class="form-control" :value=schoolName>
                </div>
              </fieldset>
              <div class="form-group">
                <label for="username">닉네임</label>
                <Field name="username" type="text" class="form-control" />
                <ErrorMessage name="username" class="error-feedback" />
              </div>
              <div class="form-group">
                <label for="email">이메일</label>
                <Field name="email" type="email" class="form-control" />
                <ErrorMessage name="email" class="error-feedback" />
              </div>
              <div class="form-group">
                <label for="password">비밀번호</label>
                <Field name="password" type="password" class="form-control" />
                <ErrorMessage name="password" class="error-feedback" />
              </div>
              <div class="form-group">
                <label for="s_grade">학년</label>
                <Field id="s_grade" name="s_grade" type="number" class="form-control" />
                <ErrorMessage name="s_grade" class="error-feedback" />
              </div>
              <div class="form-group">
                <label for="s_class">반</label>
                <Field name="s_class" id="s_class" type="number" class="form-control" />
                <ErrorMessage name="s_class" class="error-feedback" />
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
                  <button class="btn btn-primary btn-block" :disabled="loading" v-on:click="getClassGrade()">
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
</template>

<script>
import { ErrorMessage, Field, Form } from "vee-validate";
import { ref } from "vue";
import * as yup from "yup";
import AuthService from '../services/auth.service';
import PhotoBook from "./PhotoBook.vue";
import Register from './Register.vue';
import TimeSchedule from './TimeSchedule.vue';

import { usePhotoStore } from "../store/photo.js";
import PhotoPost from "./PhotoPost.vue";

import $ from "jquery";

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

    const photoPost = ref(null);

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
      s_grade: yup
        .string()
        .required("몇 학년인지 적어주세요!"),
      s_class: yup
        .string()
        .required("몇 반인지 적어주세요!"),
    });

    return {
      options: {
        licenseKey: 'YOUR_KEY_HERE',
        afterLoad: this.afterLoad,
        scrollOverflow: false,
        scrollBar: false,
        menu: '#menu',
        navigation: false,
        anchors: ['page1', 'page2', 'page3', 'page4', 'page5', 'page6'],
        sectionsColor: ['#ffffff', '#ffffff', '#ffffff', '#ffffff', '#ffffff', '#ffffff',],
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
      s_grade: "",
      s_class: "",
      loca: "",
      schoolNameList: [],
    };
  },
  mounted() {
    this.flycat();
  },
  methods: {
    schoolNameRegister() {
      this.schoolName = document.getElementById("schoolSelect").value;
      console.log(this.schoolName);
    },
    getSelect() {
      console.log(event.target.value);
      this.loca = event.target.value;
    },
    getschoolInfo() {
      console.log(this.loca);
      console.log(document.getElementById("schoolNameInput").value);

      AuthService.getSchoolInfo(this.loca, document.getElementById("schoolNameInput").value).then(
        (res) => {
          this.schoolNameList = res.data;

          console.log(this.schoolNameList);
        }
      )
    },
    getClassGrade() {
      console.log(document.getElementById("s_grade").value);
      console.log(document.getElementById("s_class").value);
    },
    getImageData(imageData) {
      this.imageData = imageData;
      // document.getElementById("photoModalBtn").click();
      // console.log(this.$refs['photoModal'].modal());
      // window.$('#photoModal').modal();
    },
    setSearchTerm(e) {
      this.searchTerm = e.target.value;
    },
    handleRegister(user) {
      console.log("registered");
      this.message = "";
      this.successful = false;
      this.loading = true;

      this.$store.dispatch("auth/register", user).then(
        (data) => {
          console.log(data);
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
      this.$store.dispatch("auth/login", user).then(
        () => {
          this.loading = true;
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
    onModalOpened() {
      console.log("열였당")
    },
    setSplideIndex() {

    }
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