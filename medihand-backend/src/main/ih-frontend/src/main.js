import { createApp } from 'vue'
import App from './App.vue'
import axios from 'axios'
import router from "./router"
import store from "./store";

import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import { FontAwesomeIcon } from './plugins/font-awesome'

import 'vue-fullpage.js/dist/style.css';
import VueFullPage from 'vue-fullpage.js'

const app = createApp(App)

// app.config.globalProperties.$axios = axios;
// axios.defaults.baseURL = "http://localhost:8081/";
// axios.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8';
// axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';

app.use(VueFullPage)
app.use(router)
app.use(store)   
app.component("font-awesome-icon", FontAwesomeIcon)

app.mount('#app')