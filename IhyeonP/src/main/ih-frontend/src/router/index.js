import { createRouter, createWebHistory } from "vue-router";
import Dashboard from "../views/Dashboard.vue";
// import Tables from "../views/Tables.vue";
import Flows from "../views/Flows.vue";
import Mbti from "../views/Mbti.vue";
import Vm from "../views/Vm.vue";
import Error from "../views/Error.vue";
//import Billing from "../views/Billing.vue";
// import RTL from "../views/Rtl.vue";
// import Notifications from "../views/Notifications.vue";
// import Profile from "../views/Profile.vue";
// import SignIn from "../views/SignIn.vue";
// import SignUp from "../views/SignUp.vue";

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
    path: "/flows",
    name: "FLOWS",
    component: Flows,
  },
  {
    path: "/mbti",
    name: "Mbti",
    component: Mbti,
  },
  {
    path: "/vm",
    name: "Vm",
    component: Vm,
  },
  {
    path: "/error",
    name: "Error",
    component: Error,
  },
  // {
  //   path: "/tables",
  //   name: "Tables",
  //   component: Tables,
  // },
  // {
  //   path: "/billing",
  //   name: "Billing",
  //   component: Billing,
  // },
  // {
  //   path: "/rtl-page",
  //   name: "RTL",
  //   component: RTL,
  // },
  // {
  //   path: "/notifications",
  //   name: "Notifications",
  //   component: Notifications,
  // },
  // {
  //   path: "/profile",
  //   name: "Profile",
  //   component: Profile,
  // },
  // {
  //   path: "/sign-in",
  //   name: "SignIn",
  //   component: SignIn,
  // },
  // {
  //   path: "/sign-up",
  //   name: "SignUp",
  //   component: SignUp,
  // },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  linkActiveClass: "active",
});

export default router;
