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
            <div>
              <img
                style="width: 100%"
                src="../../assets/logoSchool_1.png"
                alt="logo"
              />
            </div>
          </template>
        </a-list>
      </div>

      <div class="col-lg-10 col-sm-10" style="padding: 0">
        <TheHeader />
        <div
          class="container-fluid"
          style="min-height: 85vh"
          v-if="this.getUser && this.getUser.role != null"
        >
          <section class="section about-section">
            <div class="row">
              <div
                class="col"
                style="padding: 0; margin: 10px; border-radius: 5px"
              >
                <div
                  style="
                    margin-bottom: 10px;
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
                    Thông tin cá nhân
                  </h5>
                  <div
                    class="about-text go-to"
                    style="padding: 0 15px 24px 15px"
                    v-if="this.getUser.role === 'ROLE_SINHVIEN'"
                  >
                    <div class="row about-list">
                      <div class="col-lg-8">
                        <div class="media">
                          <label>Tên</label>
                          <p>{{ userInfo.name }}</p>
                        </div>
                        <div class="media">
                          <label>CCCD</label>
                          <p>{{ userInfo.identification }}</p>
                        </div>
                        <div class="media">
                          <label>Ngày sinh</label>
                          <p>{{ formattedBirthday }}</p>
                        </div>
                        <div class="media">
                          <label>Địa chỉ</label>
                          <p>{{ userInfo.address }}</p>
                        </div>
                        <div class="media">
                          <label>E-mail</label>
                          <p v-if="isAuth === true">{{ getUser.email }}</p>
                        </div>
                        <div class="media">
                          <label>Số điện thoại</label>
                          <p>{{ userInfo.phone }}</p>
                        </div>
                      </div>
                      <div class="col-lg-4">
                        <div
                          class="about-avatar text-center"
                          v-if="isAuth === true"
                        >
                          <img
                            :src="getUser.avatar"
                            title=""
                            alt=""
                            style="height: 100%; width: 100%"
                          />
                        </div>
                      </div>
                    </div>
                  </div>
                  <div
                    class="about-text go-to"
                    style="padding: 0 15px 24px 15px"
                    v-if="this.getUser.role === 'ROLE_GIANGVIEN'"
                  >
                    <div class="row about-list">
                      <div class="col-lg-8">
                        <div class="row about-text go-to">
                          <div class="col-md-6">
                            <div class="media">
                              <label>Tên</label>
                              <p>{{ userInfo.name }}</p>
                            </div>
                            <div class="media">
                              <label>CCCD</label>
                              <p>{{ userInfo.identification }}</p>
                            </div>
                            <div class="media">
                              <label>Ngày sinh</label>
                              <p>{{ formattedBirthday }}</p>
                            </div>
                          </div>
                          <div class="col-md-6">
                            <div class="media">
                              <label>Địa chỉ</label>
                              <p>{{ userInfo.address }}</p>
                            </div>
                            <div class="media">
                              <label>E-mail</label>
                              <p v-if="isAuth === true">{{ getUser.email }}</p>
                            </div>
                            <div class="media">
                              <label>Số điện thoại</label>
                              <p>{{ userInfo.phone }}</p>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="col-lg-4">
                        <div
                          class="about-avatar text-center"
                          v-if="isAuth === true"
                        >
                          <img
                            :src="getUser.avatar"
                            title=""
                            alt=""
                            style="height: 100%; width: 100%"
                          />
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div
                  v-if="this.getUser.role === 'ROLE_SINHVIEN'"
                  class="about-list"
                  style="
                    box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
                    padding: 0;
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
                    Thông tin khóa học
                  </h5>
                  <div
                    class="row about-text go-to"
                    style="padding: 0 15px 24px 15px"
                  >
                    <div class="col-md-6">
                      <div class="media">
                        <label>MSSV</label>
                        <p>{{ userInfo.id }}</p>
                      </div>
                      <div class="media" v-if="userInfo.classesId">
                        <label>Lớp</label>
                        <p>{{ userInfo.classesId.id }}</p>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="media">
                        <label>Khoa</label>
                        <p v-if="userInfo.facultyId">
                          {{ userInfo.facultyId.name }}
                        </p>
                      </div>
                      <div class="media">
                        <label>Chuyên ngành</label>
                        <p v-if="userInfo.majorId">
                          {{ userInfo.majorId.name }}
                        </p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div
                class="col-5"
                style="padding: 0; margin: 10px; border-radius: 5px"
                v-if="this.getUser.role === 'ROLE_SINHVIEN'"
              >
                <div class="row">
                  <div
                    class="col-lg-12"
                    style="
                      display: flex;
                      justify-content: space-between;
                      padding: 0;
                    "
                  >
                    <div
                      class="card mb-3 mr-2"
                      style="width: 18rem; background: #dffbff"
                    >
                      <div class="card-body">
                        <h2 class="card-title">
                          {{ finalScore.scoreValue }}
                        </h2>
                        <p class="card-text">
                          {{ finalScore.scoreColumnName }} (Hệ 10)
                        </p>
                      </div>
                    </div>
                    <div
                      class="card mb-3 ml-2"
                      style="width: 18rem; background: #dffbff"
                    >
                      <div class="card-body">
                        <h2 class="card-title">90</h2>
                        <p class="card-text">Điểm rèn luyện tích lũy</p>
                      </div>
                    </div>
                  </div>
                </div>

                <div
                  v-if="isAuth === true"
                  style="
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
                    Thông tin học tập
                  </h5>
                  <div style="padding: 24px 15px">
                    <div style="margin-bottom: 20px">
                      <div
                        class="form-group"
                        :class="{ 'has-error': !selectSemester }"
                        style="margin-right: 10px; display: flex"
                      >
                        <label
                          for="selectSemester"
                          style="width: 30%; display: flex; align-items: center"
                          >Kết quả học tập</label
                        >
                        <select
                          class="form-control"
                          id="selectSemester"
                          v-model="selectSemester"
                          @change="showAcademicWarming"
                          style="width: 40%"
                          
                        >
                          <option value="">-- Chọn học kì --</option>
                          <option
                            v-for="(semester, index) in semesters"
                            :key="index"
                            :value="semester.id"
                          >
                            {{ semester.name }} - Năm học:
                            {{ semester.schoolYear }}
                          </option>
                        </select>
                      </div>
                    </div>

                    <ChartScore
                      v-if="loaded"
                      :options="chartOptions"
                      :data="chartData"
                    />

                    <div v-if="selectSemester && academicWarming.length > 0">
                      <p
                        v-for="(academic, academicIndex) in academicWarming"
                        :key="academicIndex"
                      >
                        {{ academic }}
                      </p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </section>
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
import Apis, { endpoints, authApi } from "../../configs/Apis.js";
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
      formattedBirthday: "",
      selectSemester: "",
      semesters: [],
      academicWarming: [],
      chartData: {
        labels: [],
        datasets: [
          {
            label: "Điểm số môn học theo từng kì",
            backgroundColor: "#f87979",
            data: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
          },
        ],
      },
      chartOptions: {
        responsive: true,
      },
      loaded: true,
      errorMessage: "",
      finalScore: "",
    };
  },
  created() {
    this.fetchUserInfo();
    this.showAcademicWarming();
  },
  watch: {
    selectSemester: function (newSemester, oldSemester) {
      if (newSemester !== oldSemester) {
        this.loaded = false;
        this.showAcademicWarming();
      }
    },
  },
  methods: {
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
      if (this.userInfo) {
        this.getListSemester();
        this.fetchFinalScore();
      }
      if (response.data.birthday) {
        const birthdayTimestamp = response.data.birthday;
        const birthdayDate = new Date(birthdayTimestamp);

        const formattedBirthday = `${birthdayDate.getDate()}/${
          birthdayDate.getMonth() + 1
        }/${birthdayDate.getFullYear()}`;
        this.formattedBirthday = formattedBirthday;
      }
    },
    async getListSemester() {
      try {
        const studentId = this.userInfo.id;
        const res = await Apis.get(
          endpoints["get-semesters"] + `?studentId=${studentId}`
        );
        this.semesters = res.data;
        console.log("semesters: ", this.semesters);
      } catch (error) {
        console.log(error);
      }
    },
    async showAcademicWarming() {
      try {
        this.academicWarming = [];
        this.chartData.labels = [];
        this.chartData.datasets[0].data = [];
        if (this.selectSemester) {
          const semesterId = this.selectSemester;
          const userId = this.userInfo.id;

          const res = await authApi().get(
            endpoints["get-academic-warnings"] +
              `?studentId=${userId}&semesterId=${semesterId}`
          );
          this.academicWarming = res.data;

          // Lấy danh sách vẽ chart
          const scoreEndpoint =
            endpoints["score-list"] +
            `?studentId=${userId}&semesterId=${semesterId}`;
          const scoreResponse = await authApi().get(scoreEndpoint);
          console.log("scoreResponse", scoreResponse.data);

          // Lọc ra các label có giá trị điểm tổng kết không null
          const filteredScores = scoreResponse.data.filter((item) =>
            item.scoreDto.some(
              (score) =>
                score.scoreColumnName === "Điểm TK" && score.scoreValue > 0
            )
          );

          const subjectNames = filteredScores.map((item) => item.subjectName);

          this.chartData.labels = subjectNames;

          // Lấy danh sách điểm tổng kết từ filteredScores
          const scoreFinal = filteredScores.map(
            (item) =>
              item.scoreDto.find(
                (score) =>
                  score.scoreColumnName === "Điểm TK" && score.scoreValue > 0
              ).scoreValue
          );

          this.chartData.datasets[0].data = scoreFinal;
          this.loaded = true;
        }
      } catch (error) {
        console.log(error);
      }
    },
    async fetchFinalScore() {
      try {
        const studentId = this.userInfo.id;
        const res = await authApi().get(
          endpoints["get-final-accumulate"] + `?studentId=${studentId}`
        );
        this.finalScore = res.data;
        // console.log("this.finalScore", res);
      } catch (e) {
        this.errorMessage = "Lỗi server";
        console.log(e);
      }
    },
  },
};
</script>
