<template>
  <div style="height: 100%">
    <div class="row">
      <div class="col-lg-2 col-sm-2 d-none d-sm-flex" style="padding: 0">
        <a-list
          bordered
          style="width: 100%"
          v-if="this.getUser && this.getUser.role != null"
        >
          <div v-if="this.getUser.role === 'ROLE_SINHVIEN'">
            <TheMenuStudent />
          </div>
          <div v-if="this.getUser.role === 'ROLE_GIANGVIEN'">
            <TheMenu />
          </div>
          <template #header>
            <!-- <div style="font-size: 20px">BẢNG ĐIỀU KHIỂN</div> -->
            <div><img  style="width: 100%" src="../../assets/logoSchool_1.png" alt="logo" /></div>
          </template>
        </a-list>
      </div>
      <div class="col-lg-10 col-sm-10" style="padding: 0">
        <TheHeader />
        <div class="container-fluid" style="min-height: 80vh">
          <div
            style="
              margin: 10px 0;
              box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
              border-radius: 5px;
            "
          >
            <h5
              class="title"
              style="
                background: #070758;
                color: #fff;
                padding: 10px 24px;
                border-top-left-radius: 5px;
                border-top-right-radius: 5px;
                font-size: 16px;
              "
            >
              Thay đổi mật khẩu
            </h5>
            <form
              class="row"
              style="padding: 20px"
              action="#"
              method="put"
              @submit.prevent="changePasswordForStudent"
            >
              <div class="mt-3">
                <label for="inputPassword1">Mật khẩu hiện tại</label>
                <input
                  type="password"
                  class="form-control"
                  id="inputPassword1"
                  required
                  placeholder="Mật khẩu hiện tại"
                  v-model="passwordUser.oldPassword"
                />
              </div>
              <div class="mt-3">
                <label for="inputPassword2">Mật khẩu mới</label>
                <input
                  type="password"
                  class="form-control"
                  id="inputPassword2"
                  required
                  placeholder="Mật khẩu mới"
                  v-model="passwordUser.newPassword"
                  @input="change($event, 'password')"
                />
              </div>
              <div class="mt-3">
                <label for="inputPassword3">Nhập lại mật khẩu</label>
                <input
                  type="password"
                  class="form-control"
                  id="inputPassword3"
                  required
                  placeholder="Nhập lại mật khẩu"
                  @input="change($event, 'confirmPass')"
                  v-model="passwordUser.confirmPassword"
                />
              </div>
              <div v-if="!notiCheck">
                <div
                  v-if="errorMessage"
                  class="alert alert-danger"
                  role="alert"
                >
                  {{ errorMessage }}
                </div>
              </div>
              <div v-else>
                <div v-if="message" class="alert alert-success" role="alert">
                  {{ message }}
                </div>
              </div>
              <div class="mt-5">
                <button type="submit" class="btn btn-primary mb-3">
                  Cập nhật
                </button>
              </div>
            </form>
          </div>
        </div>
        <TheFooter />
      </div>
    </div>
  </div>
</template>

<script>
import TheHeader from "../../components/TheHeader.vue";
import TheFooter from "../../components/TheFooter.vue";
import TheMenu from "@/components/TheMenu.vue";
import TheMenuStudent from "@/components/TheMenuStudent.vue";
import { endpoints, authApi } from "../../configs/Apis.js";
import { mapGetters } from "vuex";
import ChartScore from "../../components/ChartScore.vue";

export default {
  components: {
    TheHeader,
    TheFooter,
    TheMenu,
    TheMenuStudent,
    ChartScore,
  },
  computed: {
    ...mapGetters(["isAuth", "getUser"]),
  },
  data() {
    return {
      user: { email: "" },
      userInfo: {},
      passwordUser: {
        oldPassword: "",
        newPassword: "",
        confirmPassword: "",
      },
      errorMessage: "",
      message: "",
      checkForm: true,
      notiCheck: false,
    };
  },
  created() {
    this.fetchUserInfo();
  },
  mounted() {},
  methods: {
    change(event, field) {
      this.passwordUser[field] = event.target.value;
    },
    async fetchUserInfo() {
      let endpoint = "";
      const userRole = this.getUser.role;
      if (userRole === "ROLE_SINHVIEN") {
        endpoint = endpoints["get-student-by-username"];
      } else if (userRole === "ROLE_GIANGVIEN") {
        endpoint = endpoints["get-lecturer-by-username"];
      }

      const response = await authApi().get(
        endpoint.replace("{username}", this.getUser.username)
      );
      this.userInfo = response.data;
      console.log("this.userInfo", this.userInfo);
      if (response.data.birthday) {
        const birthdayTimestamp = response.data.birthday;
        const birthdayDate = new Date(birthdayTimestamp);

        const formattedBirthday = `${birthdayDate.getDate()}/${
          birthdayDate.getMonth() + 1
        }/${birthdayDate.getFullYear()}`;
        this.formattedBirthday = formattedBirthday;
      }
    },
    async changePasswordForStudent() {
      try {
        if (
          this.passwordUser.newPassword !== this.passwordUser.confirmPassword
        ) {
          this.errorMessage = "Mật khẩu xác nhận không khớp";
          this.checkForm = false;
          this.notiCheck = false;
          return;
        } else {
          this.checkForm = true;
        }
        if (this.checkForm) {
          const res = await authApi().put(
            endpoints["change-password"] +
              `?oldPassword=${this.passwordUser.oldPassword}&newPassword=${this.passwordUser.newPassword}`
          );
          this.message = res.data;
          console.log("put password: " + res.data);
          this.notiCheck = true;
          this.passwordUser.oldPassword = "";
          this.passwordUser.newPassword = "";
          this.passwordUser.confirmPassword = "";
        }
      } catch (e) {
        this.errorMessage = "Mật khẩu cũ không chính xác";
        this.notiCheck = false;
      }
    },
  },
};
</script>
