<template>
  <section>
    <div class="d-flex">
      <div style="width: 30%; margin-bottom: 20px">
        <div
          class="form-group"
          :class="{ 'has-error': !selectSemester }"
          style="margin-right: 10px"
        >
          <label for="selectSemester">Học kì</label>
          <select
            class="form-control"
            id="selectSemester"
            :v-model="selectSemester"
            disabled
          >
            <option :key="index" :value="semesters.id">
              {{ semesters.name }} - Năm học: {{ semesters.schoolYear }}
            </option>
          </select>
        </div>
      </div>
      <div style="width: 50%; margin-bottom: 20px">
        <div
          class="form-group"
          :class="{ 'has-error': !selecetMajor }"
          style="margin-right: 10px"
        >
          <label for="selecetFacuties">Đăng ký môn học</label>
          <select
            class="form-control"
            id="selecetFacuties"
            v-model="selecetMajor"
            @change="getSubject"
          >
            <option value="">-- Chọn Khoa --</option>
            <option
              v-for="(major, index) in majors"
              :key="index"
              :value="major.id"
            >
              {{ major.name }}
            </option>
          </select>
        </div>
      </div>
    </div>
    <div v-if="courses.length > 0">
      <p>Danh sách môn học mở đăng ký:</p>
      <table class="table table-hover table-bordered">
        <thead>
          <tr>
            <th></th>
            <th>Mã môn học</th>
            <th>Tên môn học</th>
            <th>Số tín chỉ</th>
            <th>Số lượng</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(rowData, index) in courses" :key="index">
            <td style="position: relative; width: 40px">
              <input
                class="form-check-input"
                type="checkbox"
                :id="'check' + index"
                :disabled="rowData.isSelected"
                :checked="rowData.isSelected"
                @change="toggleCourse($event, rowData)"
                style="
                  position: absolute;
                  top: 43%;
                  left: 100%;
                  transform: translate(-50%, -50%);
                "
              />
            </td>
            <td>{{ rowData.id }}</td>
            <td>{{ rowData.name }}</td>
            <td style="text-align: center">{{ rowData.credit }}</td>
            <td style="text-align: center">{{ this.quantity }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div v-else-if="courses === null">
      <p>Danh sách môn học mở đăng ký:</p>

      <table class="table table-hover table-bordered">
        <thead>
          <tr>
            <th></th>
            <th>Mã môn học</th>
            <th>Tên môn học</th>
            <th>Số tín chỉ</th>
            <th>Số lượng</th>
          </tr>
        </thead>
      </table>
      <p style="text-align: center">
        <strong>Không có môn học nào mở đăng ký cho khoa này.</strong>
      </p>
    </div>
    <div v-else>
      <p>Danh sách môn học mở đăng ký:</p>

      <table class="table table-hover table-bordered">
        <thead>
          <tr>
            <th></th>
            <th>Mã môn học</th>
            <th>Tên môn học</th>
            <th>Số tín chỉ</th>
            <th>Số lượng</th>
          </tr>
        </thead>
      </table>
      <p style="text-align: center">
        <strong>Không có môn học nào mở đăng ký cho khoa này.</strong>
      </p>
    </div>
    <div>
      <p>Danh sách môn học đã đăng kí:</p>
      <table class="table table-hover table-bordered">
        <thead>
          <tr>
            <th></th>
            <th>Mã môn học</th>
            <th>Tên môn học</th>
            <th>Số tín chỉ</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(selectedCourse, index) in selectedCourses" :key="index">
            <td style="position: relative; width: 40px">
              <button
                class="btn btn-danger"
                @click="removeCourse(index, selectedCourse.id)"
              >
                Xóa
              </button>
            </td>
            <td>{{ selectedCourse.subjectId.id }}</td>
            <td>{{ selectedCourse.subjectId.name }}</td>
            <td>{{ selectedCourse.subjectId.credit }}</td>
          </tr>
        </tbody>
      </table>

      <p>Danh sách môn học đã chọn:</p>
      <table class="table table-hover table-bordered">
        <thead>
          <tr>
            <th></th>
            <th style="text-align: center">STT</th>
            <th>Mã môn học</th>
            <th>Tên môn học</th>
            <th>Số tín chỉ</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(course, index) in ListCourses" :key="index">
            <td style="position: relative; width: 40px">
              <button
                class="btn btn-danger"
                @click="removeCoursed(index, course.id)"
              >
                Xóa
              </button>
            </td>
            <td style="text-align: center">{{ index + 1 }}</td>
            <td>{{ course.subjectId.id }}</td>
            <td>{{ course.subjectId.name }}</td>
            <td>{{ course.subjectId.credit }}</td>
          </tr>
        </tbody>
      </table>
      <div class="d-flex justify-content-end">
        <button class="btn btn-primary" @click="submitCourses()">
          Đăng ký
        </button>
      </div>
    </div>
  </section>
</template>

<script>
import Apis, { authApi, endpoints } from "@/configs/Apis";
import { mapGetters } from "vuex";
export default {
  computed: {
    ...mapGetters(["isAuth", "getUser"]),
  },
  data() {
    return {
      courses: [],
      majors: [],
      semesters: {},
      selecetMajor: "",
      selectSemester: "",
      selectedCourses: [],
      ListCourses: [],
      selectedTemporaryCourses: [],
      maxQuantity: 0,
      quantity: 80,
      isSuccess: false,
      isLoadSubjects: false,
    };
  },
  watch: {
    selectedTemporaryCourses: {
      async handler(newValue, oldValue) {
        await this.saveTemporaryCourse();
        await this.getSubjectTemoratyCourse();

        console.log(1);
      },
      deep: true, // Đảm bảo theo dõi sâu vào các phần tử của mảng
    },
    courses: {
      async handler(newCourse, oldCourse) {
        console.log(1);
        this.checkedSubjectedCourse();
      },
      deep: true,
    },
    isSuccess: {
      async handler(newSuccess, oldSuccess) {
        console.log(2);
        await this.getSubjectTemoratyCourse();
        await this.getSubjectCourse();
        this.isSuccess = false;
      },
      deep: true,
    },
  },
  methods: {
    checkedSubjectedCourse() {
      try {
        if (this.courses && this.selectedCourses) {
          this.courses.forEach((course) => {
            const matchingCourse = this.selectedCourses.find(
              (selectedCourse) => selectedCourse.subjectId.id === course.id
            );
            if (matchingCourse) {
              // Cập nhật lại giá trị "Còn lại" cho môn học mở đăng ký tương ứng
              course.isSelected = true;
            }
          });
        }
        if (this.courses && this.ListCourses) {
          this.courses.forEach((course) => {
            const matchingCourse = this.ListCourses.find(
              (coursed) => coursed.subjectId.id === course.id
            );
            if (matchingCourse) {
              // Cập nhật lại giá trị "Còn lại" cho môn học mở đăng ký tương ứng
              course.isSelected = true;
            }
          });
        }
      } catch (error) {
        console.log(error.message);
      }
    },
    // Phương thức được gọi khi checkbox thay đổi trạng thái
    toggleCourse(event, rowData, index) {
      if (!rowData.isSelected) {
        rowData.isSelected = true;
        this.selectedTemporaryCourses.push(rowData);
        this.remaining -= 1;
      }
    },
    async removeCourse(index, studentSubjectId) {
      const removedCourse = this.selectedCourses.splice(index, 1)[0];

      this.selectedTemporaryCourses = this.selectedTemporaryCourses.filter(
        (course) => course.id !== removedCourse.subjectId.id
      );

      // // Tìm môn học mở đăng ký tương ứng dựa trên mã và nhóm
      const matchingCourse = this.courses.find(
        (course) =>
          course.id === removedCourse.subjectId.id &&
          course.group === removedCourse.group
      );

      console.log("matchingCourse", matchingCourse);
      // // Cập nhật lại giá trị "Còn lại" cho môn học mở đăng ký tương ứng
      if (matchingCourse) {
        matchingCourse.isSelected = false;
        matchingCourse.remaining += 1;
      }

      // console.log(studentSubjectId);
      await this.deleteCourse(studentSubjectId);
    },
    async removeCoursed(index, studentSubjectId) {
      const removedCourse = this.ListCourses.splice(index, 1)[0];

      // this.selectedTemporaryCourses = this.selectedTemporaryCourses.filter(
      //   (course) => course.id !== removedCourse.subjectId.id
      // );

      // // Tìm môn học mở đăng ký tương ứng dựa trên mã và nhóm
      const matchingCourse = this.courses.find(
        (course) =>
          course.id === removedCourse.subjectId.id &&
          course.group === removedCourse.group
      );

      console.log("matchingCourse", matchingCourse);
      // // Cập nhật lại giá trị "Còn lại" cho môn học mở đăng ký tương ứng
      if (matchingCourse) {
        matchingCourse.isSelected = false;
        matchingCourse.remaining += 1;
      }

      // console.log(studentSubjectId);
      await this.deleteCourse(studentSubjectId);
    },
    async deleteCourse(studentSubjectId) {
      try {
        const response = await authApi().delete(
          endpoints["delete-course"].replace(
            "{studentSubjectId}",
            studentSubjectId
          )
        );
      } catch (error) {
        console.error("Error submitting post:", error);
      }
    },
    async getSubject() {
      try {
        if (this.selecetMajor) {
          const majorId = this.selecetMajor;
          const semesterId = this.semesters.id;

          const response = await authApi().get(
            endpoints["get-subject-by-major"] +
              `?majorId=${majorId}&semesterId=${semesterId}`
          );
          this.courses = response.data;
          this.isLoadSubjects = true;
        } else {
          console.error("Please select a faculty before fetching subjects.");
        }
      } catch (error) {
        if (error.response && error.response.status === 404) {
          // Handle 404 error by setting courses to an empty array
          this.courses = [];
        } else {
          console.error("Error fetching subjects:", error);
          // Handle other errors as needed
        }
      }
    },
    async getMajor() {
      try {
        const response = await Apis.get(endpoints["majors"]);
        this.majors = response.data;
      } catch (error) {
        console.log(error);
      }
    },
    async getLatestSemester() {
      try {
        const res = await authApi().get(endpoints["get-latest-semester"]);
        this.semesters = res.data;
      } catch (error) {
        console.log(error);
      }
    },
    async submitCourses() {
      try {
        const username = this.getUser.username;
        const resStudentId = await authApi().get(
          endpoints["get-student-by-username"].replace("{username}", username)
        );
        const studentId = resStudentId.data.id;

        try {
          const promises = this.selectedCourses.map(async (subject) => {
            const subjectId = subject.subjectId.id;
            const semesterId = this.semesters.id;
            const requestData = [
              {
                studentId: studentId,
                subjectId: subjectId,
                semesterId: semesterId,
              },
            ];
            const response = await authApi().post(
              endpoints["course-register"],
              requestData,
              {
                headers: {
                  "Content-Type": "application/json", // Đặt Content-Type là application/json
                },
              }
            );
            if (response.status !== 201) {
              console.error(`Đăng ký của sinh viên thất bại!!!`);
            }
          });
          await Promise.all(promises);

          alert("Đăng ký môn học thành công!");
          this.isSuccess = true;
        } catch (error) {
          console.log(error);
        }
      } catch (error) {
        console.log(error);
      }
    },
    async saveTemporaryCourse() {
      try {
        const username = this.getUser.username;
        const resStudentId = await authApi().get(
          endpoints["get-student-by-username"].replace("{username}", username)
        );
        const studentId = resStudentId.data.id;

        try {
          const promises = this.selectedTemporaryCourses.map(
            async (subject, index, array) => {
              const subjectId = subject.id;
              const semesterId = this.semesters.id;
              if (index === array.length - 1) {
                const requestData = [
                  {
                    studentId: studentId,
                    subjectId: subjectId,
                    semesterId: semesterId,
                  },
                ];
                const response = await authApi().post(
                  endpoints["temporary-course-register"],
                  requestData,
                  {
                    headers: {
                      "Content-Type": "application/json", // Đặt Content-Type là application/json
                    },
                  }
                );
                if (response.status !== 201) {
                  console.error(`Lưu tạm thất bại!!!`);
                }
              }
            }
          );
          await Promise.all(promises);
          console.log("Lưu tạm thành công");
        } catch (error) {
          console.log(error);
        }
      } catch (e) {
        console.log(e);
      }
    },
    async getSubjectTemoratyCourse() {
      try {
        const username = this.getUser.username;
        const resStudentId = await authApi().get(
          endpoints["get-student-by-username"].replace("{username}", username)
        );
        const studentId = resStudentId.data.id;
        const semesterId = this.semesters.id;
        const res = await authApi().get(
          endpoints["get-temporary-courses"] +
            `?studentId=${studentId}&semesterId=${semesterId}`
        );

        const temporatyCourseData = res.data;
        this.selectedCourses = temporatyCourseData;
      } catch (error) {
        console.log(error);
      }
    },

    async getSubjectCourse() {
      try {
        const username = this.getUser.username;
        const resStudentId = await authApi().get(
          endpoints["get-student-by-username"].replace("{username}", username)
        );
        const studentId = resStudentId.data.id;
        const semesterId = this.semesters.id;
        const res = await authApi().get(
          endpoints["get-already-course"] +
            `?studentId=${studentId}&semesterId=${semesterId}`
        );

        const CourseData = res.data;
        this.ListCourses = CourseData;
      } catch (error) {
        console.log(error);
      }
    },
  },
  async created() {
    console.log("isLoadSubjects", this.isLoadSubjects);
    this.getMajor();
    this.getSubject();
    this.getLatestSemester();
    if (this.semesters) {
      await this.getSubjectTemoratyCourse();
    }

    await this.getSubjectCourse();

    console.log("this.getSubjectTemoratyCourse", this.selectedCourses);
    if (this.majors.length > 0) {
      this.getSubject();
      this.maxQuantity = this.courses.reduce(
        (max, course) => (course.quantity > max ? course.quantity : max),
        0
      );
    }
  },
};
</script>
