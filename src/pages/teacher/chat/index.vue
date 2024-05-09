<template>
  <div class="wrapper" >
    <!-- Sidebar  -->
    <nav style="color: white" id="sidebar">
      <div class="sidebar-header d-flex justify-content-around">
        <div
          class="d-flex pointer"
          style="margin-right: 25px; width: 160px"
          
        >
          <img
            :src="photoURL"
            alt="user"
            style="
              border-radius: 50%;
              background: white;
              width: 50px;
              height: 50px;
            "
          />
          <div
            style="
              padding: 10px 0px 0px;
              width: 50%;
              display: flex;
              justify-content: space-between;
            "
          >
            <p style="line-height: 2; font-weight: 600; margin-left: 10px" @click="onProfileClick">
              {{ currentUserName }}
            </p>
          </div>
        </div>
        <button type="button" class="btn btn-primary" @click="logout">
          Logout
        </button>
      </div>
      <div style="height: 1px; border-bottom: 1px solid #00388b"></div>
      <ul class="list-unstyled components">
        <li
          class="active mb-3"
          @click="letsChat(item)"
          v-for="item in searchUsers"
          :key="item.id"
          v-show="item.id !== currentUserId"
        >
          <div
            class="d-flex"
            style="cursor: pointer; padding-bottom: 15px; width: 100%"
          >
            <div class="d-flex" style="width: 30%; justify-content: center">
              <img
                :src="item.URL"
                alt="user"
                style="
                  border-radius: 50%;
                  background: white;
                  width: 50px;
                  height: 50px;
                "
              />
            </div>
            <div
              style="
                padding: 10px 0px 0px;
                width: 50%;
                display: flex;
                justify-content: space-between;
              "
            >
              <h6 style="line-height: 2; font-weight: 600">{{ item.name }}</h6>
            </div>
          </div>
          <div style="height: 1px; border-bottom: 1px solid #00388b"></div>
        </li>
      </ul>
    </nav>

    <!-- Page Content  -->
    <div id="content" class="content-chatbox" v-if="currentPeerUser === null  && !showProfile">
      <div class="my-4 d-flex" style="justify-content: center">
        <img :src="photoURL" class="br-50" style="width: 100px; height: 100px"/>
      </div>
      <div >
        <h2 style="text-align: center">Chào {{ currentUserName }},</h2>
        <h3 style="text-align: center">Hãy chùng mọi người trao đổi những thắc mắc nhé.</h3>
      </div>
    </div>
    <div v-else class="header-width" style="margin-right: 10px;  ">
      <ChatBox :currentPeerUser="currentPeerUser" />
    </div>
  </div>
</template>

<script>
import { getAuth, signOut } from "firebase/auth";
import { getFirestore, collection, getDocs } from "firebase/firestore"; // Sử dụng db từ Firebase Modular SDK
import ChatBox from "../../../components/ChatBox.vue";
import { db } from "../../../main";
import { authApi, endpoints } from '@/configs/Apis';

export default {
  name: "Chat",
  components: {
    ChatBox,
  },
  data() {
    return {
      currentUserName: localStorage.getItem("name"),
      currentPeerUser: null,
      currentUserId: localStorage.getItem("id"),
      currentUserPhoto: localStorage.getItem("photoURL"),
      searchUsers: [],
      photoURL: localStorage.getItem("photoURL"),
      listStudents: [],
      showProfile: false,
    };
  },
  methods: {
    async onProfileClick() {
      // Toggle the showProfile flag when clicking on the <p> element
      this.showProfile = false;
      this.currentPeerUser = null;
    },
    async logout() {
      const auth = getAuth();
      await signOut(auth);
      this.$router.push("/login");
      localStorage.clear();
    },
    letsChat(item) {
      this.currentPeerUser = item;
      this.showProfile = true;
    },
    async getUserList() {
      const usersCollection = collection(db, "users"); // Tạo một bộ sưu tập Firestore
      const querySnapshot = await getDocs(usersCollection); // Lấy dữ liệu từ bộ sưu tập
      querySnapshot.forEach((doc) => {
        const userData = doc.data();
        if (userData.id !== this.currentUserId) {
          this.searchUsers.push({
            documentKey: doc.id,
            id: userData.id,
            name: userData.name,
            URL: userData.URL,
            description: userData.description,
          });
        }
      });
    },
    
    
  },
  created() {
    if (!localStorage.hasOwnProperty("id")) this.$router.push("/");
    this.getUserList();
  },
};
</script>


<style scoped>
.pointer {
  cursor: pointer;
}
.br-50 {
  border-radius: 50%;
}
.header-width {
  width: calc(100% - 350px);
  transition: all 0.3s;
  position: absolute;
  right: 0;
}
</style>
