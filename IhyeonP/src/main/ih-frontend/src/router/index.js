import { createRouter, createWebHistory } from "vue-router";
import Dashboard from "../views/Dashboard.vue";
import Login from "../views/Login.vue";
import Join from "../views/Join.vue";
import Protected from "../views/Protected.vue";

import store from '../store'

// Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: '/',
    component: Dashboard
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/join',
    name: 'Join',
    component: Join
  },
  {
    path: '/protected',
    name: 'Protected',
    component: Protected,
    meta: {
      requiresAuth: true
    }
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  linkActiveClass: "active",
});

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!store.getters.isLoggedIn) {
      next({
        name: 'Login'
      })
    } else {
      next();
    }
  } else {
    next();
  }
});

export default router;
