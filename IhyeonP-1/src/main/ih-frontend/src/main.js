import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import { FontAwesomeIcon } from './plugins/font-awesome'
import axios from 'axios'

import 'vue-fullpage.js/dist/style.css'
import VueFullPage from 'vue-fullpage.js'

import "@egjs/vue3-flicking/dist/flicking.css";
// Or, if you have to support IE9
import "@egjs/vue3-flicking/dist/flicking-inline.css";



createApp(App)
  .use(router)
  .use(store)
  .use(VueFullPage)
  .component("font-awesome-icon", FontAwesomeIcon)
  .mount("#app");

App.config.globalProperties.$axios = axios
axios.defaults.baseURL = "http://localhost:8080/";