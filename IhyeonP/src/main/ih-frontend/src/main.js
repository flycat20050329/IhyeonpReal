import { createApp } from 'vue'
import App from './Dashboard.vue'
import axios from 'axios'

import 'vue-fullpage.js/dist/style.css'
import VueFullPage from 'vue-fullpage.js'

const app = createApp(App)
app.config.globalProperties.$axios = axios;
axios.defaults.baseURL = "http://localhost:8080/";
app.use(VueFullPage)
app.mount('#app')
