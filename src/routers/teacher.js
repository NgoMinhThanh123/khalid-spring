import store from '../stores/myStore.js';

const teacher = [
  {
    path: "/teacher",
    component: () => import ("../layouts/teacher.vue"),
    beforeEnter: (to, from, next) => {
      // Kiểm tra trạng thái đăng nhập từ store Vuex
      if (store.getters.isAuth) {
        next(); // Cho phép truy cập
      } else {
        next('/login'); // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
      }
    },
    children: [
      {
        path: "studentScore",
        name: "teacher-studentScore",
        component: () => import("../pages/teacher/studentScore/index.vue"),
        beforeEnter: (to, from, next) => {
          // Kiểm tra trạng thái đăng nhập từ store Vuex
          if (store.getters.isAuth) {
            next(); // Cho phép truy cập
          } else {
            next('/login'); // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
          }
        },
      },
      {
        path: "classStudent",
        name: "teacher-classStudent",
        component: () => import("../pages/teacher/classStudent/index.vue"),
        beforeEnter: (to, from, next) => {
          // Kiểm tra trạng thái đăng nhập từ store Vuex
          if (store.getters.isAuth) {
            next(); // Cho phép truy cập
          } else {
            next('/login'); // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
          }
        },
      },
      {
        path: "forum",
        name: "teacher-forum",
        component: () => import("../pages/teacher/forum/index.vue"),
        beforeEnter: (to, from, next) => {
          // Kiểm tra trạng thái đăng nhập từ store Vuex
          if (store.getters.isAuth) {
            next(); // Cho phép truy cập
          } else {
            next('/login'); // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
          }
        },
      },
      {
        path: "detailForum/:id",
        name: "teacher-detailForum",
        component: () => import("../pages/teacher/detailForum/index.vue"),
        beforeEnter: (to, from, next) => {
          // Kiểm tra trạng thái đăng nhập từ store Vuex
          if (store.getters.isAuth) {
            next(); // Cho phép truy cập
          } else {
            next('/login'); // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
          }
        },
      },
      {
        path: "posted",
        name: "teacher-posted",
        component: () => import("../pages/teacher/posted/index.vue"),
        beforeEnter: (to, from, next) => {
          // Kiểm tra trạng thái đăng nhập từ store Vuex
          if (store.getters.isAuth) {
            next(); // Cho phép truy cập
          } else {
            next('/login'); // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
          }
        },
      },
      {
        path: "student",
        name: "teacher-student",
        component: () => import("../pages/teacher/student/index.vue"),
        beforeEnter: (to, from, next) => {
          // Kiểm tra trạng thái đăng nhập từ store Vuex
          if (store.getters.isAuth) {
            next(); // Cho phép truy cập
          } else {
            next('/login'); // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
          }
        },
      },
      {
        path: "chat",
        name: "teacher-chat",
        component: () => import("../pages/teacher/chat/index.vue"),
        beforeEnter: (to, from, next) => {
          if (store.getters.isAuth) {
            next();
          } else {
            next('/login');
          }
        },
      },
    ]
  }
];

export default teacher;