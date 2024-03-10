<template>
  <full-page :options="this.options" id="fullpage" ref="fullpage">

    <div class="section">
      <div class="text-center">
        <h1>home</h1>
      </div>
    </div>

    <div class="section" v-if="currentUser">
      <div class="slide">
        <TimeSchedule />
      </div>
      <div class="slide">
        <Lunch />
      </div>
    </div>

    <!-- Photo -->
    <div class="section" style="height:fit-content" v-if="currentUser">

      <!-- top bar -->
      <div class="topBar" v-if="!uploadImages">
        <div class="row">
          <div class="col">
            <div class="row">

              <!-- upload button -->
              <div class="col-2">
                <button type="button" class="btn btn-outline-dark" @click="chooseFiles()">
                  <font-awesome-icon icon="plus" /> 사진 올리기</button>
              </div>

              <!-- switch -->
              <div class="col-2">
                <div class="form-check form-switch" style="width:fit-content">
                  <input class="form-check-input" type="checkbox" id="meSwitch" v-model="meChecked"
                    v-on:change="checkSwitch">
                  <label class="form-check-label" for="flexSwitchCheckDefault">Only Mine</label>
                </div>
              </div>

              <!-- date picker -->
              <div class="col-3">
                <VueDatePicker :model-value="dateFilter" range :enable-time-picker="false" :max-date="maxDate"
                  ignore-time-validation @update:model-value="filterDate" />
              </div>

              <div class="col-3">
                <button type="button" class="btn btn-outline-dark dateBtn" @click="dateBeforeAMonth()">
                  <font-awesome-icon icon="angle-left" /> 한 달 전</button>
                <button type="button" class="btn btn-outline-dark dateBtn" data-toggle="tooltip" data-placement="top"
                  title="today" @click="dateToday()">
                  Today</button>
                <button type="button" class="btn btn-outline-dark dateBtn" data-toggle="tooltip" data-placement="top"
                  title="a month later" @click="dateAfterAMonth()">
                  한 달 후 <font-awesome-icon icon="angle-right" /></button>
              </div>

            </div>
          </div>

          <!-- 검색어 입력 -->
          <!-- <div class="col-4">
            <div class="form">
              <i class="fa fa-search"></i>
              <input type="text" class="form-control form-input" placeholder="검색어를 입력하세요.">
            </div>
          </div> -->

        </div>
      </div>

      <Suspense>
        <PhotoBook :key="componentKey" @changeUploadImages="changeUploadImages" @imageData="getImageData" />
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
      <div class="container" v-if="!isUploading">
        
        <button class="btn btn-primary" v-on:click="chooseClubFiles()">ㅇㅅㅇ</button>

        <div class="card" style="width: 18rem;">
          <img src="..." class="card-img-top" alt="...">
          <div class="card-body">
            <h5 class="card-title">Card title</h5>
            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's
              content.</p>
            <a href="#" class="btn btn-primary">Go somewhere</a>
          </div>
        </div>
      </div>
      <Suspense>
        <ClubList @toggleUpload="toggleUpload" />
      </Suspense>
    </div>


    <div class="section" v-if="currentUser">
      <h2>중고 거래</h2>
    </div>

    <div class="section" v-if="currentUser">
      <h2>문의</h2>
    </div>

  </full-page>

  <div class="modal fade" id="clubRegister" tabindex="-1" aria-labelledby="clubRegisterLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="clubRegisterLabel">Modal title</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
          kskssssqefnioenfwefuiojwenkhdifuhwjioedfnihurjionvwheirjivowenbhifuowej
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary">Save changes</button>
        </div>
      </div>
    </div>
  </div>

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
            <div class="modal-footer">
              <div class="form-group">
                <button class="btn btn-primary btn-block" :disabled="loading" style="float: right;">
                  <span v-show="loading" class="spinner-border spinner-border-sm"></span>
                  <span>Login</span>
                </button>
              </div>
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
            v-on:click="getschoolName()" data-bs-dismiss="modal">학교 선택</button>
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
          <PhotoPost :images="imageData" ref="photoPost"></PhotoPost>
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
                  <label for="schoolName" class="form-label">학교 이름</label>
                  <Field name="schoolName" type="schoolName" class="form-control" :model-value=schoolName />
                  <ErrorMessage name="schoolName" class="error-feedback" />
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
              <div class="form-group">
                <label for="s_number">번호</label>
                <Field name="s_number" id="s_number" type="number" class="form-control" />
                <ErrorMessage name="s_number" class="error-feedback" />
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
        <button class="btn btn-primary" data-bs-target="#schoolSelectModal" data-bs-toggle="modal"
          data-bs-dismiss="modal">학교 검색으로 돌아가기</button>
      </div>
    </div>
  </div>
</template>

<script>
import { ErrorMessage, Field, Form } from "vee-validate";
import { ref, onMounted, watch } from "vue";
import * as yup from "yup";
import AuthService from '../services/auth.service';
import PhotoBook from "./PhotoBook.vue";
import Register from './Register.vue';
import TimeSchedule from './TimeSchedule.vue';
import Lunch from './Lunch.vue';
import { useStore } from 'vuex';

import { usePhotoStore } from "../store/photo.js";
import PhotoPost from "./PhotoPost.vue";

import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';

import { mapActions, mapState } from "pinia";

import moment from "moment";
import $ from "jquery";
import ClubList from "./ClubList.vue";
import useClubStore from "../store/club";


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
    VueDatePicker,
    Lunch,
    ClubList,
  },
  setup() {
    const componentKey = ref(0);
    const mainimages = ref([]);

    const store = useStore();
    const currentUser = store.state.auth.user;

    const photoStore = usePhotoStore();
    const clubStore = useClubStore();

    const uploadImages = ref(false);
    const isUploading = ref(false);


    //Upload File Button
    const chooseFiles = () => {
      document.getElementById("fileUpload").click();
    }

    const chooseClubFiles = () => {
      document.getElementById("clubFileUpload").click();
    }


    // Only Mine Switch
    const meChecked = ref(false);

    const checkSwitch = () => {
      // console.log(dateFilter.value);
      if (meChecked.value) {
        photoStore.setPhotos(photoStore.getAllPhotos.filter(photo => {
          const photoTime = moment(photo.photoPost.uploadedOn).format("YYYY-MM-DD HH:mm:ss.SSSSSS");
          return dateFilter.value[0] <= photoTime && photoTime <= dateFilter.value[1] && photo.photoPost.user?.id == currentUser.id;
        }))
      } else {
        photoStore.setPhotos(photoStore.getAllPhotos.filter(photo => {
          const photoTime = moment(photo.photoPost.uploadedOn).format("YYYY-MM-DD HH:mm:ss.SSSSSS");
          return dateFilter.value[0] <= photoTime && photoTime <= dateFilter.value[1];
        }));
      }
      componentKey.value += 1;
    }

    // vue datepicker

    const startDateFormat = "YYYY-MM-DD 00:00:00.000000"
    const endDateFormat = "YYYY-MM-DD 23:59:59.999999"

    const startDateStr = moment(new Date).subtract('days', 7).format(startDateFormat)
    const endDateStr = moment(new Date()).format(endDateFormat)

    const dateFilter = ref([startDateStr, endDateStr]);
    const maxDate = ref(endDateStr);


    const filterDate = (modelData) => {
      dateFilter.value = [moment(modelData[0]).format(startDateFormat), moment(modelData[1]).format(endDateFormat)];

      // rerender
      // componentKey.value += 1;
    }

    const dateToday = () => {
      dateFilter.value = [moment(new Date()).format(startDateFormat), moment(new Date()).format(endDateFormat)];
      checkSwitch();

    }


    const dateBeforeAMonth = () => {
      if (moment(dateFilter.value[0]).format("DD") != moment(dateFilter.value[1]).format("DD")) {
        dateFilter.value[0] = moment(dateFilter.value[1]).subtract("months", 1).format(startDateFormat);
        moment(dateFilter.value[1]).add('months', 1);

      } else {
        dateFilter.value[0] = moment(dateFilter.value[0]).subtract('months', 1).format(startDateFormat);
      }
      checkSwitch();
    }

    const dateAfterAMonth = () => {
      if (moment(new Date()) <= moment(dateFilter.value[0]).add('months', 1)) {
        dateFilter.value[0] = moment(new Date()).format(startDateFormat);
      }
      else {
        dateFilter.value[0] = moment(dateFilter.value[0]).add("months", 1).format(startDateFormat);
      }
      checkSwitch();
    }

    const changeUploadImages = (data) => {
      uploadImages.value = data;
    }

    const toggleUpload = () => {
      isUploading.value = !isUploading.value;
    };

    watch(() => photoStore.getAllPhotos, () => {
      checkSwitch();
      // componentKey.value += 1;
    })

    watch(() => dateFilter.value, (newValue, oldValue) => {
      if (newValue.length == 2) {
        checkSwitch();
      }

    })

    onMounted(() => {
      checkSwitch();
    })


    return {
      componentKey,
      mainimages,
      photoStore,
      dateFilter,
      maxDate,
      meChecked,
      uploadImages,
      isUploading,
      clubStore,

      filterDate,
      chooseFiles,
      checkSwitch,
      dateToday,
      dateBeforeAMonth,
      dateAfterAMonth,
      changeUploadImages,
      toggleUpload,
      chooseClubFiles,
      moment,
    }
  },
  data() {

    const loginSchema = yup.object().shape({
      username: yup.string().required("Username is required!"),
      password: yup.string().required("Password is required!"),
    });

    const signupSchema = yup.object().shape({
      schoolName: yup
        .string(),
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
      s_number: yup
        .string()
        .required("번호를 적어주세요!"),
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
        credits: { enabled: false, label: "생각 많이 한 광고입니다 사실 광고는 아니구요 생각도 안 했습니다. 감사합니다.", position: 'right' },
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
    var photoModalEl = document.getElementById('photoModal')
    const vm = this;
    photoModalEl.addEventListener('shown.bs.modal', function () {
      fullpage_api.setAllowScrolling(false);
      vm.$refs.photoPost.openModal();
    })
    photoModalEl.addEventListener('hidden.bs.modal', function () {
      fullpage_api.setAllowScrolling(true);
    })
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
    getschoolName() {
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
      // console.log(document.getElementById("photoModal").style);
      // window.$('#photoModal').modal();
    },
    setSearchTerm(e) {
      this.searchTerm = e.target.value;
    },
    handleRegister(user) {
      console.log("registed");
      this.message = "";
      this.successful = false;
      this.loading = true;

      this.$store.dispatch("auth/register", user).then(
        (data) => {
          console.log(data);
          this.message = data.message;
          this.successful = true;
          this.loading = false;

          this.$router.go();
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
    getPhotoModal() {
      return document.getElementById("photoModal");
    },
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


.topBar {
  border-bottom: solid;
  padding-bottom: 5px;
}

.topBar .searchBar {
  display: flex;
  justify-content: flex-end;
  padding-right: 15px;
}

.form .fa-search {
  position: absolute;
  top: 12px;
  left: 15px;
  color: #000000;

}

.form {
  position: relative;
  width: fit-content;
}

.form-input {
  height: 38px;
  width: 350px;
  text-indent: 33px;
  border-radius: 40px;
}

.form-input:focus {
  box-shadow: none;
  border: none;

  full-page {
    position: fixed;
  }
}
</style>