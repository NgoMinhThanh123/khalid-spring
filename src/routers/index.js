import { createRouter, createWebHistory } from "vue-router";
import home from "../layouts/home.vue";
import teacher from "./teacher.js";
import student from "./student.js";
import login from "./login.js";
import store from "@/stores/myStore";
import register from "./register.js";
import parent from "./parents.js";

const routes = [
  {
    path: "/",
    component: () => import("../pages/home/index.vue"),
  },
  {
    path: "/general-information",
    name: "general-information",
    component: () => import("../pages/general_Information/index.vue"),
  },
  {
    path: "/payment",
    name: "payment",
    component: () => import("../pages/parents/payment/index.vue"),
  },
  {
    path: "/profile",
    name: "profile",
    component: () => import("../pages/profile/index.vue"),
    beforeEnter: (to, from, next) => {
      // Kiểm tra trạng thái đăng nhập từ store Vuex
      if (store.getters.isAuth) {
        next(); // Cho phép truy cập
      } else {
        next("/login"); // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
      }
    },
  },
  {
    path: "/changePassword",
    name: "change-password",
    component: () => import("../pages/changePassword/index.vue"),
    beforeEnter: (to, from, next) => {
      // Kiểm tra trạng thái đăng nhập từ store Vuex
      if (store.getters.isAuth) {
        next(); // Cho phép truy cập
      } else {
        next("/login"); // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
      }
    },
  },
  {
    path: "/parent/scoreStudent/:id",
    name: "score-student",
    component: () => import("../pages/parents/scoreStudent/index.vue"),
    props: true,
  },
  ...teacher,
  ...student,
  ...login,
  ...register,
  ...parent,
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
