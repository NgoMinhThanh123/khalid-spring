<template>
  <TheHeader />
  <div id="parent">
    <div class="container">
      <form
        class="formParent"
        method="get"
        @submit.prevent="getStudentByParent"
      >
        <div class="row">
          <div class="col">
            <label>Mã số sinh viên</label>
            <input
              v-model="student.studentId"
              type="text"
              class="form-control"
              placeholder="Mã số sinh viên"
              required
            />
          </div>
          <div class="col">
            <label>Họ và tên</label>
            <input
              v-model="student.studentName"
              type="text"
              class="form-control"
              placeholder="Họ và tên"
              required
            />
          </div>
          <div class="col">
            <label>Ngày sinh</label>
            <input
              v-model="student.studentBirthday"
              type="date"
              class="form-control"
              placeholder="Ngày sinh"
              required
            />
          </div>
        </div>
        <div class="row" style="margin-top: 20px">
          <div class="col">
            <label>Mã lớp</label>
            <input
              v-model="student.classId"
              type="text"
              class="form-control"
              placeholder="Mã lớp"
              required
            />
          </div>
          <div class="col">
            <label>CCCD</label>
            <input
              v-model="student.studentIdentification"
              type="text"
              class="form-control"
              placeholder="CCCD"
              required
            />
          </div>
          <div class="col">
            <!-- <input type="text" class="form-control" placeholder="Last name" /> -->
          </div>
        </div>
        <div class="btn-search">
          <input
            type="submit"
            value="Tra cứu"
            class="btn btn-block btn-primary"
            :disabled="loading"
            style="max-width: 100px"
          />
        </div>
      </form>
      <table class="table" v-if="isSearch">
        <thead>
          <tr>
            <th scope="col">MSSV</th>
            <th scope="col">Họ và tên</th>
            <th scope="col">Ngày sinh</th>
            <th scope="col">Số CCCD</th>
            <th scope="col">Xem điểm</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>{{ studentList.id }}</td>
            <td>{{ studentList.name }}</td>
            <td>{{ studentList.birthday }}</td>
            <td>{{ studentList.identification }}</td>
            <td>
            <router-link :to="`/parent/scoreStudent/${studentList.id}`">Xem điểm</router-link>
            </td>
          </tr>
        </tbody>
      </table>
      <p v-if="errorMessage && !isSearch">{{ errorMessage }}</p>
    </div>
  </div>
  <TheFooter />
</template>
<script>
import TheFooter from "@/components/TheFooter.vue";
import TheHeader from "@/components/TheHeader.vue";
import Apis, { endpoints } from "@/configs/Apis";
import student from "@/routers/student";

export default {
  components: {
    TheFooter,
    TheHeader,
  },
  data() {
    return {
      student: {
        studentId: "",
        studentName: "",
        studentBirthday: "",
        classId: "",
        studentIdentification: "",
      },
      studentList: [],
      errorMessage: "",
      isSearch: false,
    };
  },
  created() {},
  methods: {
    async getStudentByParent() {
      try {
        const response = await Apis.get(
          endpoints["get-student-by-parents"] +
            `?studentId=${this.student.studentId}&studentName=${this.student.studentName}&studentBirthday=${this.student.studentBirthday}&classId=${this.student.classId}&studentIdentification=${this.student.studentIdentification}`
        );
        this.studentList = response.data;
        this.isSearch = true;
        console.log("get student by parent", response);
        console.log("this.student.studentId", this.student.studentId);
        console.log("this.student.studentName", this.student.studentName);
        console.log(
          "this.student.studentBirthday",
          this.student.studentBirthday
        );
        console.log("this.student.classId", this.student.classId);
        console.log(
          "this.student.studentIdentification",
          this.student.studentIdentification
        );
        console.log("this.studentList", this.studentList);
      } catch (e) {
        this.errorMessage = "Không tìm thấy sinh viên";
        this.isSearch = false;
      }
    },
    // handleSubmitSearch() {
    //   this.getStudentBuParent();
    // },
  },
};
</script>
<style scoped>
#parent {
  position: relative;
  width: 100%;
  min-height: 60.7vh;
  margin-top: 50px;
  margin-bottom: 50px;
  /* background: #d3d3d3; */
}

.container {
  /* position: relative; */
  width: 1200px;
  min-height: 400px;
  padding: 30px;
  border-radius: 5px;
  box-shadow: -1px -1px 20px 4px rgba(234, 234, 234, 1);
  -webkit-box-shadow: -1px -1px 20px 4px rgba(234, 234, 234, 1);
  -moz-box-shadow: -1px -1px 20px 4px rgba(234, 234, 234, 1);
}

.btn-search {
  max-width: 100%;
  margin-top: 40px;
  display: flex;
  justify-content: flex-end;
}

</style>
