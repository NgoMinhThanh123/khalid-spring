<template>
  <div style="margin: 3rem">
    <div class="container">
      <div class="row">
        <div class="col-md-6">
          <img
            src="../assets/undraw_remotely_2j6y.svg"
            alt="Image"
            class="img-fluid"
          />
        </div>
        <div class="col-md-6 contents">
          <div class="row justify-content-center">
            <div class="col-md-8">
              <div class="mb-4">
                <h3>Đăng ký tài khoản</h3>
                <div v-if="errorMessage" class="alert alert-danger">
                  {{ errorMessage }}
                </div>
              </div>
              <form action="#" method="post" @submit.prevent="handleRegister">
                <div class="form-group first">
                  <label>Tài khoản</label>
                  <input
                    required
                    v-model="user.username"
                    @input="change($event, 'username')"
                    type="text"
                    class="form-control mt-1"
                    placeholder="Nhập tài khoản"
                  />
                </div>
                <div class="form-group last mb-4">
                  <label>Mật khẩu</label>
                  <input
                    required
                    v-model="user.password"
                    @input="change($event, 'password')"
                    type="password"
                    class="form-control mt-1"
                    placeholder="Nhập mật khẩu"
                  />
                </div>
                <div class="form-group last mb-4">
                  <label>Xác nhận Mật khẩu</label>
                  <input
                    required
                    v-model="user.confirmPass"
                    @input="change($event, 'confirmPass')"
                    type="password"
                    class="form-control mt-1"
                    placeholder="Xác nhận mật khẩu"
                  />
                </div>
                <div class="form-group last mb-4">
                  <label>Email</label>
                  <input
                    required
                    v-model="user.email"
                    @input="change($event, 'email')"
                    type="email"
                    class="form-control mt-1"
                    placeholder="Email"
                  />
                </div>
                <div class="form-group last mb-4">
                  <label>Avatar</label>
                  <input
                    required
                    type="file"
                    @change="handleAvatarChange"
                    accept="image/*"
                    class="form-control mt-1"
                    placeholder="Avatar"
                  />
                  <label for="file">
                    <span>Add an avatar</span>
                  </label>
                </div>

                <input
                  type="submit"
                  value="Đăng ký"
                  :disabled="loading"
                  class="btn btn-block btn-primary"
                />
                <div style="text-align: center; margin: 10px 0">
                  <div
                    v-if="loading"
                    class="spinner-border text-primary"
                    role="status"
                  >
                    <span class="visually-hidden">Loading...</span>
                  </div>
                </div>
                <div
                  class="d-flex mt-3 align-items-center"
                  style="justify-content: center"
                >
                  <span class="registerHover" style="margin-right: 10px">
                    <router-link to="/" class="nav-link">
                      Trang chủ
                    </router-link>
                  </span>
                  <span class="registerHover">
                    <router-link to="/login" class="nav-link">
                      Đăng nhập
                    </router-link>
                  </span>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Apis, { authApi, endpoints } from "@/configs/Apis";
import { createUserWithEmailAndPassword } from "firebase/auth";
import { addDoc, collection } from "firebase/firestore";
import { auth, db } from "../services/firebase";
export default {
  name: "Register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPass: "",
        email: "",
        avatar: null,
      },
      errorMessage: "",
      loading: false,
    };
  },
  
  methods: {
    change(event, field) {
      this.user[field] = event.target.value;
    },
    handleAvatarChange(event) {
      this.user.avatar = event.target.files[0];
      this.avatar = URL.createObjectURL(this.user.avatar);
    },
    async handleRegister() {
      try {
        const name = this.user.username;
        const email = this.user.email;
        const password = this.user.password;
        this.loading = true;

        if (this.user.password !== this.user.confirmPass) {
          this.errorMessage = "Mật khẩu xác nhận không khớp";
          this.loading = false;
          return;
        }

        const formData = new FormData();
        formData.append(
          "registerRequest",
          JSON.stringify({
            username: this.user.username,
            password: this.user.password,
            email: this.user.email,
          })
        );
        formData.append("avatar", this.user.avatar);
        const response = await Apis.post(endpoints["register"], formData, {
          headers: {
            "Content-Type": "multipart/form-data", // Important: set content type for file upload
          },
        });

        try {
          const avatarURL = response.data.avatar;
          console.log("avatarURL", avatarURL);
          console.log("this.avatar", avatarURL);
          const userCredential = await createUserWithEmailAndPassword(
            auth,
            email,
            password
          );
          const user = userCredential.user;
          // console.log(user);
          // Add user data to Firestore
          const userRef = await addDoc(collection(db, "users"), {
            name,
            id: user.uid,
            email,
            password,
            URL: avatarURL,
            description: "",
          });

          localStorage.setItem("id", user.uid);
          localStorage.setItem("name", name);
          localStorage.setItem("email", email);
          localStorage.setItem("password", password);
          localStorage.setItem("photoURL", avatarURL);
          localStorage.setItem("description", "");
          localStorage.setItem("FirebaseDocumentId", userRef.id);
          this.name = "";
          this.email = "";
          this.password = "";
        } catch (e) {
          this.errorMessage = error.response?.data || "Lỗi server" || error.message;
        }
       
        this.$router.push("/login");

        // Your registration and Firebase code here...
      } catch (error) {
        this.errorMessage = error.response?.data || "Lỗi server" || error.message;
      } finally {
        this.loading = false;
      }
    },
  },
};
</script>

