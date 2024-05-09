<template>
  <div class="menu" v-if="isChecked">
    <div class="sidebar-mobile-main"></div>
    <div class="row">
      <div class="col-6">
        <a-list
          bordered
          style="width: 100%"
          v-if="this.getUser && this.getUser.role != null"
        >
          <div v-if="this.getUser.role === 'ROLE_SINHVIEN'">
            <TheMenuStudent
              :isChecked="isChecked"
              @update:isChecked="isChecked = $event"
            />
          </div>
          <div v-if="this.getUser.role === 'ROLE_GIANGVIEN'">
            <TheMenu
              :isChecked="isChecked"
              @update:isChecked="isChecked = $event"
            />
          </div>
          <template #header>
            <div>
              <img
                style="width: 100%"
                src="../assets/logoSchool_1.png"
                alt="logo"
              />
            </div>
          </template>
        </a-list>
      </div>
    </div>
  </div>
  <div id="header" class="header">
    <div
      class="text-white"
      style="
        background-color: #070758;
        padding: 1rem 2rem;
        display: flex;
        flex-wrap: wrap;
      "
    >
      <div
        class="col-6 col-sm-6 d-flex justify-content-sm-start align-items-center"
      >
        <div class="home"></div>
        <div class="profile d-none d-md-flex d-lg-flex " v-if="isAuth === true">
          <router-link to="/profile" class="router-link-hover">
            <i class="fa-solid fa-user"></i>
            <span style="font-size: 16px"> Hồ sơ </span>
          </router-link>
        </div>
        <div class="container-mobile" v-if="isAuth">
          <input
            class="label-check"
            id="label-check"
            type="checkbox"
            v-model="isChecked"
          />
          <label for="label-check" class="hamburger-label">
            <div class="line1"></div>
            <div class="line2"></div>
            <div class="line3"></div>
            <label></label
          ></label>
        </div>
      </div>
      <div
        class="col-6 col-sm-6 d-sm-flex align-items-center justify-content-sm-end"
      >
        <div
          v-if="isAuth === false"
          class="d-sm-flex align-items-center justify-content-sm-end"
        >
          <div class="parent">
            <router-link to="/login" class="router-link-hover">
              <i class="fa-solid fa-graduation-cap"></i>
              <span> Sinh viên </span>
            </router-link>
          </div>
          <div class="parent">
            <router-link to="/login" class="router-link-hover">
              <i class="fa-solid fa-user-tie"></i>
              <span> Giảng viên </span>
            </router-link>
          </div>
          <div class="parent">
            <router-link to="/parent" class="router-link-hover">
              <i class="fa-solid fa-hands-holding-child"></i>
              <span> Phụ huynh </span>
            </router-link>
          </div>
        </div>
        <div
          v-else
          class="align-items-center justify-content-sm-end"
        >
          <div style="padding-right: 30px" class="d-flex align-items-center">
            <div style="margin-right: 10px" class="d-flex">
              <img
                :src="getUser.avatar"
                class="rounded-circle"
                style="width: 30px; height: 30px"
                alt="Avatar"
              />
            </div>
            <div class="dropdown d-none d-sm-flex">
              <a
                class="nav-link dropdown-toggle"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                style="font-size: 16px"
              >
                Chào, {{ userInfo.name }}
              </a>

              <ul class="dropdown-menu">
                <li>
                  <router-link to="/changePassword" class="dropdown-item"
                    >Thay đổi mật khẩu</router-link
                  >
                </li>
                <li>
                  <router-link
                    to="/login"
                    @click="logout()"
                    class="dropdown-item"
                    >Đăng xuất</router-link
                  >
                </li>
              </ul>
            </div>
            <!-- <a
              class="nav-link"
              href="#"
              id="navbarDropdown2"
              role="button"
              aria-expanded="false"
            >
              Chào, {{ userInfo.name }}
            </a> -->
          </div>
          <div></div>
        </div>
      </div>
    </div>
  </div>
  <div
    id="headerBottom"
    class="headerBottom"
    :class="{ fixHead: isFixed }"
    v-if="!isLogin"
  >
    <nav class="navbar navbar-expand-lg">
      <div class="container-fluid">
        <div style="width: 10%">
          <img
            style="width: 100%"
            src="../assets/logoSchool_1.png"
            alt="Logo trường"
          />
        </div>

        <div
          class="collapse navbar-collapse"
          id="navbarNav"
          style="justify-content: flex-end; flex-wrap: wrap"
        >
          <ul class="navbar-nav">
            <li class="nav-item">
              <router-link
                to="/"
                class="router-link-hover nav-link"
                :class="{ active: $route.path === '/' }"
                exact
              >
                <span> Trang chủ </span>
              </router-link>
            </li>
            <li class="nav-item">
              <router-link
                to="/general-information"
                class="router-link-hover nav-link"
                :class="{ active: $route.path === '/general-information' }"
              >
                <span> Giới thiệu chung </span>
              </router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
import TheMenu from "@/components/TheMenu.vue";
import TheMenuStudent from "@/components/TheMenuStudent.vue";
import { authApi, endpoints } from "@/configs/Apis";
import { mapGetters } from "vuex";
import { getAuth, signOut } from "firebase/auth";
export default {
  name: "TheHeader",
  components: {
    TheMenu,
    TheMenuStudent,
  },
  data() {
    return {
      username: "",
      userInfo: {},
      formattedBirthday: "",
      isFixed: false,
      isLogin: false,
      isChecked: false,
    };
  },
  computed: {
    ...mapGetters(["isAuth", "getUser"]),
  },
  methods: {
    async logout() {
      this.isLogin = false;
      this.$store.dispatch("logout");
      const auth = getAuth();
      await signOut(auth);
      localStorage.clear();
    },
    async fetchUserInfo() {
      let endpoint = "";
      const userRole = this.getUser.role;
      this.isLogin = true;
      if (userRole === "ROLE_SINHVIEN") {
        endpoint = endpoints["get-student-by-username"];
      } else if (userRole === "ROLE_GIANGVIEN") {
        endpoint = endpoints["get-lecturer-by-username"];
      }

      const response = await authApi().get(
        endpoint.replace("{username}", this.getUser.username)
      );
      this.userInfo = response.data;
      console.log(response.data);
      if (response.data.birthday) {
        const birthdayTimestamp = response.data.birthday;
        const birthdayDate = new Date(birthdayTimestamp);

        const formattedBirthday = `${birthdayDate.getDate()}/${
          birthdayDate.getMonth() + 1
        }/${birthdayDate.getFullYear()}`;
        this.formattedBirthday = formattedBirthday;
      }
    },

    handleScroll() {
      if (window.pageYOffset > 0) {
        this.isFixed = true;
        if (document.getElementById("headerBottom") !== null) {
          document.getElementById("headerBottom").classList.add("slide-in-top");
        }
      } else {
        this.isFixed = false;
        if (document.getElementById("headerBottom") !== null) {
          document
            .getElementById("headerBottom")
            .classList.remove("slide-in-top");
        }
      }
    },
  },
  mounted() {
    if (this.getUser) {
      this.fetchUserInfo();
    }

    if (!this.isLogin) {
      window.addEventListener("scroll", this.handleScroll);
    }
  },
  watch: {
    isChecked: {
      handler(newCheck, oldCheck) {
        console.log("isCheck", this.isChecked);
      },
    },
  },
};
</script>

<style>
.header {
  position: relative;
  left: 0;
  top: 0;
  width: 100%;
  background-color: #0c713d;
  color: white;
  text-align: center;
  box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15) !important;
}
.headerBottom {
  position: relative;
  z-index: 1000;
  box-shadow: -1px 1px 3px 0px rgba(221, 221, 221, 1);
  -webkit-box-shadow: -1px 1px 3px 0px rgba(221, 221, 221, 1);
  -moz-box-shadow: -1px 1px 3px 0px rgba(221, 221, 221, 1);
}
.fixHead {
  position: fixed !important;
  top: 0;
  left: 0;
  z-index: 999;
  width: 100%;
  background: #fff;
  box-shadow: 2px 4px 24px 0px rgba(201, 180, 180, 0.75);
  -webkit-box-shadow: 2px 4px 24px 0px rgba(201, 180, 180, 0.75);
  -moz-box-shadow: 2px 4px 24px 0px rgba(201, 180, 180, 0.75);
}

.slide-in-top {
  -webkit-animation: slide-in-top 0.5s cubic-bezier(0.25, 0.46, 0.45, 0.94) both;
  animation: slide-in-top 0.5s cubic-bezier(0.25, 0.46, 0.45, 0.94) both;
}

@keyframes slide-in-top {
  0% {
    -webkit-transform: translateY(-1000px);
    transform: translateY(-1000px);
    opacity: 0;
  }
  100% {
    -webkit-transform: translateY(0);
    transform: translateY(0);
    opacity: 1;
  }
}

.dropdown-toggle::after {
  right: unset !important;
}
</style>
