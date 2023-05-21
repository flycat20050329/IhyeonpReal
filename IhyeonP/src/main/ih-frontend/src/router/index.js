import { createRouter, createWebHistory } from "vue-router";
import Dashboard from "../Dashboard.vue";
import Home from "../views/Home.vue";
import Login from "../views/Login.vue";
import Register from "../views/Register.vue";
// import Protected from "../views/Protected.vue";

// lazy-loaded
const Profile = () => import("../views/Profile.vue")
const BoardAdmin = () => import("../views/BoardAdmin.vue")
const BoardModerator = () => import("../views/BoardModerator.vue")
const BoardUser = () => import("../views/BoardUser.vue")

import store from '../store'

// Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: '/',
    component: Dashboard
  },
  {
    path: '/home',
    name: '/home',
    component: Dashboard
  },

  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: "/profile",
    name: "profile",
    // lazy-loaded
    component: Profile,
  },
  {
    path: "/admin",
    name: "admin",
    // lazy-loaded
    component: BoardAdmin,
  },
  {
    path: "/mod",
    name: "moderator",
    // lazy-loaded
    component: BoardModerator,
  },
  {
    path: "/user",
    name: "user",
    // lazy-loaded
    component: BoardUser,
  },
  // {
  //   path: '/protected',
  //   name: 'Protected',
  //   component: Protected,
  //   meta: {
  //     requiresAuth: true
  //   }
  // },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
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
