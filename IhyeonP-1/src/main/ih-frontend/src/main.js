import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import 'bootstrap/dist/js/bootstrap';
import 'bootstrap/dist/css/bootstrap.css'
import { FontAwesomeIcon } from './plugins/font-awesome'
import axios from 'axios'


import { createPinia } from 'pinia';

import 'vue-fullpage.js/dist/style.css'
import VueFullPage from 'vue-fullpage.js'

// Toast
import Toast from 'vue-toastification'
import 'vue-toastification/dist/index.css'



const options = {
  position: "top-right",
  timeout: 3000,
  closeOnClick: true,

  toastClassName: "toastCustomStyle",
};


const pinia = createPinia()


createApp(App)
  .use(router)
  .use(store)
  .use(VueFullPage)
  .use(pinia)
  .use(Toast, options)
  .component("font-awesome-icon", FontAwesomeIcon)
  .mount("#app");
