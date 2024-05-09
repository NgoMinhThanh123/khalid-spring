<template>
  <Home>
    <div class="container-fluid mb-4 mt-4" style="min-height: 80vh;">
      <div v-if="semesters.length > 0">
        <h2 class="title_content">Điểm số sinh viên</h2>
        <div class="container">
          <div
            v-for="(semester, semesterIndex) in semesters"
            :key="semesterIndex"
          >
            <div class="semester">
              {{ `${semester.name}-${semester.schoolYear}` }}
            </div>
            <table class="score-table">
              <thead>
                <tr>
                  <th>STT</th>
                  <th>Tên môn học</th>
                  <th>Số tín chỉ</th>
                  <th>Quá trình</th>
                  <th>Giữa kì</th>
                  <th>Cuối kì</th>
                  <th>TK</th>
                </tr>
              </thead>
              <tbody>
                <tr
                  v-for="(score, scoreIndex) in scoreLists[semesterIndex]"
                  :key="scoreIndex"
                >
                  <td>{{ scoreIndex + 1 }}</td>
                  <td>{{ score.subjectName }}</td>
                  <td>{{ score.credit }}</td>
                  <td>
                    <span v-if="score.scoreDto && score.scoreDto.length > 0">
                      <span
                        v-if="score.scoreDto[0].scoreColumnName === 'Quá trình'"
                      >
                        {{ score.scoreDto[0].scoreValue || "-" }}
                      </span>
                    </span>
                  </td>
                  <td>
                    <span v-if="score.scoreDto && score.scoreDto.length > 1">
                      <span
                        v-if="score.scoreDto[1].scoreColumnName === 'Giữa kì'"
                      >
                        {{ score.scoreDto[1].scoreValue || "-" }}
                      </span>
                    </span>
                  </td>
                  <td>
                    <span v-if="score.scoreDto && score.scoreDto.length > 2">
                      <span
                        v-if="score.scoreDto[2].scoreColumnName === 'Cuối kì'"
                      >
                        {{ score.scoreDto[2].scoreValue || "-" }}
                      </span>
                    </span>
                  </td>
                  <td></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div v-else style="padding: 20px">
        <span style="font-size: 25px">Sinh viên chưa có điểm</span>
      </div>
    </div>
  </Home>
</template>

<script>
import { ref, onMounted } from "vue";
import Apis, { authApi, endpoints } from "@/configs/Apis";
import Home from "../../../layouts/home.vue";
import TheHeader from "../../../components/TheHeader.vue";
import TheFooter from "../../../components/TheFooter.vue";

export default {
  components: {
    Home,
    TheHeader,
    TheFooter,
  },
  data() {
    return {
      semesters: [],
      scoreLists: [],
      err: "",
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      try {
        const studentId = this.$route.params.id;
        const semesterResponse = await Apis.get(
          endpoints["semester-student"] + `?studentId=${studentId}`
        );
        console.log("semesterResponse", semesterResponse);
        this.semesters = semesterResponse.data.sort((a, b) => {
          return new Date(b.fromDate) - new Date(a.fromDate);
        });

        const scoreListsValue = [];

        for (const semester of semesterResponse.data) {
          const semesterId = semester.id;

          const scoreEndpoint =
            endpoints["score-list"] +
            `?studentId=${studentId}&semesterId=${semesterId}`;
          const scoreResponse = await Apis.get(scoreEndpoint);

          scoreListsValue.push(scoreResponse.data);
        }
        this.scoreLists = scoreListsValue;
      } catch (err) {
        err.value = true;
      }
    },
  },
  setup() {},
};
</script>

<style scoped>
.title_content {
  margin-bottom: 30px;
  text-align: center;
  font-size: 32px;
  color: #dfb36f;
  text-transform: uppercase;
  position: relative;
  padding-bottom: 10px;
}

.title_content::after {
  content: "";
  width: 190px;
  height: 1px;
  background: #dfb36f;
  position: absolute;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;
}
</style>
