<template>
  <div class="studentHome">
    <form @submit.prevent="handleSubmit">
      <div class="select d-flex">
        <div
          class="form-group"
          :class="{ 'has-error': !selectedSubject }"
          style="margin-right: 10px"
        >
          <label for="subjectSelect">Chọn môn học:</label>
          <select
            class="form-control"
            id="subjectSelect"
            v-model="selectedSubject"
            @change="handleSubjectChange"
          >
            <option value="">Chọn môn học</option>
            <option
              v-for="(subject, index) in subjectList"
              :key="index"
              :value="subject.id"
            >
              {{ subject.name }}
            </option>
          </select>
        </div>

        <div class="form-group" :class="{ 'has-error': !selectedSemester }">
          <label for="semesterSelect">Chọn học kì:</label>
          <select
            class="form-control"
            id="semesterSelect"
            v-model="selectedSemester"
            @change="handleSemesterChange"
          >
            <option value="">Chọn học kì</option>
            <option
              v-for="(semester, index) in semesterList"
              :key="index"
              :value="semester.id"
            >
              {{ `${semester.name} - ${semester.schoolYear}` }}
            </option>
          </select>
        </div>
      </div>
      <div class="d-flex">
        <div style="margin-right: 10px">
          <button class="btn btn-primary btnSubmit" type="submit">
            Tìm kiếm
          </button>
        </div>
        <div>
          <button class="btn btn-danger btnExportPDF" @click="exportToPDF">
            Xuất PDF
          </button>
        </div>
      </div>
    </form>
    <div v-if="hasError">
      <p style="font-size: 20px; padding: 20px">{{ err }}</p>
    </div>
    <div v-else>
      <div v-if="studentList.length > 0" class="table-container">
        <table class="table table-striped table-bordered table-hover">
          <thead>
            <tr>
              <th class="text-center">Mã số sinh viên</th>
              <th class="text-center">Tên sinh viên</th>
              <th
                class="text-center"
                v-for="(scoreColumn, index) in scoreColumns"
                :key="index"
              >
                {{ scoreColumn }}
              </th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(student, index) in studentScores" :key="index">
              <td class="text-center">{{ student.studentId }}</td>
              <td class="text-center">{{ student.studentName }}</td>
              <td
                class="text-center"
                v-for="(score, scoreIndex) in student.scores"
                :key="scoreIndex"
              >
                {{ score.value }}
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import { authApi, endpoints } from "@/configs/Apis.js";
import { mapGetters } from "vuex";
import { useMenu } from "../../../stores/use-menu.js";
import jsPDF from "jspdf";
import "jspdf-autotable";

export default {
  setup() {
    useMenu().onSelectedKeys(["teacher-student"]);
  },
  computed: {
    ...mapGetters(["isAuth", "getUser"]),
  },
  data() {
    return {
      isEditMode: false,
      selectedColumn: "option1",
      subjectList: [],
      selectedSubject: "",
      selectedLecturer: {},
      studentList: [],
      semesterList: [],
      selectedSemester: "",
      //   csvData: [],
      studentScores: {},
      scoreColumns: [],
      err: "",
      hasError: false,
    };
  },
  created() {
    this.fetchLecturerInfo();
    // this.fetchData();

    this.fetchLecturerInfo().then((lecturerInfo) => {
      if (lecturerInfo && lecturerInfo.id) {
        const lecturerId = lecturerInfo.id;
        console.log("lecturerId", lecturerId);
        this.fetchSubjectsByLecturerId(lecturerId);
      }
    });
  },
  methods: {
    handleEdit() {
      this.isEditMode = true;
    },
    exitHandleEdit() {
      this.isEditMode = false;
    },

    async fetchLecturerInfo() {
      try {
        const lecturerUsername = this.getUser.username;
        const response = await authApi().get(
          endpoints["get-lecturer-by-username"].replace(
            "{username}",
            lecturerUsername
          )
        );
        console.log("get-lecturer-by-username", response.data);
        this.selectedLecturer = response.data;

        console.log("this.selectedLecturer", this.selectedLecturer);
        return response.data;
      } catch (error) {
        console.error(err);
        return null;
      }
    },
    async fetchSubjectsByLecturerId(lecturerId) {
      try {
        const response = await authApi().get(
          endpoints["get-subject-by-lecturerId"].replace(
            "{lecturerId}",
            lecturerId
          )
        );
        console.log("get-subject-by-lecturerId", response.data);
        this.subjectList = response.data;

        const endpoint = endpoints["semester"] + `?lecturerId=${lecturerId}`;

        const semesterResponse = await authApi().get(endpoint);
        this.semesterList = semesterResponse.data;

        console.log("Semester info:", semesterResponse.data);
      } catch (error) {
        console.error(error);
        console.log(error);
        return null;
      }
    },
    handleSubjectChange(event) {
      this.selectedSubject = event.target.value;
      console.log(event.target.value);
    },
    handleSemesterChange(event) {
      this.selectedSemester = event.target.value;
    },
    async handleSubmit(event) {
      event.preventDefault();

      try {
        if (!this.selectedSubject || !this.selectedSemester) {
          this.hasError = true;
          this.err = "Vui lòng chọn môn và học kì.";
          return;
        }
        const subjectId = this.selectedSubject;
        const lecturerId = this.selectedLecturer.id;
        const semesterId = this.selectedSemester;
        console.log("semesterId", semesterId);

        const endpoint =
          endpoints["get-list-scores"] +
          `?lecturerId=${lecturerId}&semesterId=${semesterId}&subjectId=${subjectId}`;

        const response = await authApi().get(endpoint);
        console.log("response", response.status);

        if (response.data) {
          console.log(response.data);
          this.studentList = response.data;
          this.err = "";
          this.hasError = false;
        }
      } catch (error) {
        console.error(error);
        console.log("Status code:", error.response.status);
        this.err = "Không có dữ liệu";
        this.hasError = true;
      }
    },
    async exportToPDF() {
      const doc = new jsPDF();
      doc.text("Bảng điểm sinh viên", 10, 10);

      const header = ["Mã số sinh viên", "Tên sinh viên", ...this.scoreColumns];

      const data = Object.values(this.studentScores).map((student) => [
        student.studentId,
        student.studentName,
        ...this.scoreColumns.map((column) => {
          const score = student.scores.find((s) => s.column === column);
          return score ? score.value : "";
        }),
      ]);

      doc.autoTable({
        head: [header],
        body: data,
      });

      // Lưu PDF hoặc hiển thị trong một cửa sổ mới
      // Đây là một ví dụ lưu PDF với tên 'student_scores.pdf':
      doc.save("student_scores.pdf");
    },
  },
  watch: {
    studentList: {
      handler: function (newVal) {
        // Process the student list and update studentScores and scoreColumns
        const studentScores = {};
        const scoreColumns = [];

        newVal.forEach((student) => {
          const { studentId, studentName, scoreColumnName, scoreValue } =
            student;

          if (!studentScores[studentId]) {
            studentScores[studentId] = {
              studentId,
              studentName,
              scores: [{ column: scoreColumnName, value: scoreValue }],
            };
          } else {
            studentScores[studentId].scores.push({
              column: scoreColumnName,
              value: scoreValue,
            });
          }

          if (!scoreColumns.includes(scoreColumnName)) {
            scoreColumns.push(scoreColumnName);
          }
        });

        this.studentScores = studentScores;
        console.log(studentScores);
        this.scoreColumns = scoreColumns;
        console.log(studentScores);
      },
      deep: true,
    },
  },
};
</script>