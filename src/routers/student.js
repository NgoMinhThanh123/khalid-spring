import store from "../stores/myStore.js";

const student = [
  {
    path: "/student",
    component: () => import("../layouts/student.vue"),
    beforeEnter: (to, from, next) => {
      // Kiểm tra trạng thái đăng nhập từ store Vuex
      if (store.getters.isAuth) {
        next(); // Cho phép truy cập
      } else {
        next("/login"); // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
      }
    },
    children: [
      {
        path: "score",
        name: "student-score",
        component: () => import("../pages/student/score/index.vue"),
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
        path: "chat",
        name: "student-chat",
        component: () => import("../pages/student/chat/index.vue"),
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
        path: "forum",
        name: "student-forum",
        component: () => import("../pages/student/forum/index.vue"),
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
        path: "course",
        name: "student-course",
        component: () => import("../pages/student/course/index.vue"),
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
        path: "detailForum/:id",
        name: "student-detailForum",
        component: () => import("../pages/student/detailForum/index.vue"),
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
        path: "posted",
        name: "student-posted",
        component: () => import("../pages/student/posted/index.vue"),
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
        path: "payment",
        name: "student-payment",
        component: () => import("../pages/student/payment/index.vue"),
        beforeEnter: (to, from, next) => {
          // Kiểm tra trạng thái đăng nhập từ store Vuex
          if (store.getters.isAuth) {
            next(); // Cho phép truy cập
          } else {
            next("/login"); // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
          }
        },
      },
    ],
  },
];

export default student;
