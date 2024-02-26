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

import Toast from "vue-toastification";
import "vue-toastification/dist/index.css";

//PrimeVue
import PrimeVue from 'primevue/config';
import ToastService from 'primevue/toastservice';

import 'primevue/resources/themes/aura-light-green/theme.css'


const pinia = createPinia()


createApp(App)
  .use(router)
  .use(store)
  .use(VueFullPage)
  .use(pinia)
  .use(PrimeVue)
  .use(ToastService)
  .component("font-awesome-icon", FontAwesomeIcon)
  .mount("#app");
