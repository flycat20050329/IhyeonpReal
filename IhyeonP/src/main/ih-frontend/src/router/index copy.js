import { createRouter, createWebHistory } from "vue-router";
import Dashboard from "../views/Dashboard.vue";
import LoginPage from "../views/LoginPage.vue";
import Join from "../views/Join.vue";
import Protected from "../views/Protected.vue";

const routes = [
  {
    path: "/",
    name: "/",
    redirect: "/dashboard",
  },
  {
    path: "/dashboard",
    name: "Dashboard",
    component: Dashboard,
  },
  {
    path: "/login",
    name: "LoginPage",
    component: LoginPage,
  },
  {
    path: "/join",
    name: "Join",
    component: Join,
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

export default router;


