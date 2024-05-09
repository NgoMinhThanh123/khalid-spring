import axios from "axios";
import VueCookies from "vue-cookies";

const SERVER_CONTEXT = "";
const SERVER = "http://localhost:8082";
// const SERVER = "http://universitysb-env.eba-nnscwuyq.ap-southeast-2.elasticbeanstalk.com";

export const endpoints = {
  subjects: `${SERVER_CONTEXT}/api/subjects/`,
  faculties: `${SERVER_CONTEXT}/api/faculties/`,
  majors: `${SERVER_CONTEXT}/api/majors/`,
  login: `${SERVER_CONTEXT}/api/login/`,
  register: `${SERVER_CONTEXT}/api/users/`,
  semester: `${SERVER_CONTEXT}/api/semesters/`,
  export: `${SERVER_CONTEXT}/api/scores/export-csv/`,
  posts: `${SERVER_CONTEXT}/api/posts/`,
  details: (postId) => `${SERVER_CONTEXT}/api/posts/${postId}/`,
  comments: `${SERVER_CONTEXT}/api/posts/{id}/comments/`,
  classes: `${SERVER_CONTEXT}/api/classes/`,
  "get-subject-by-facultyId": `${SERVER_CONTEXT}/api/subjects/facultyId/`,
  "semester-student": `${SERVER_CONTEXT}/api/semesters/student/`,
  "semester-lecturer-subject": `${SERVER_CONTEXT}/api/semesters/lecturer-subject/`,
  "current-user": `${SERVER_CONTEXT}/api/current-user/`,
  "get-user": `${SERVER_CONTEXT}/api/users/{username}/`,
  "user-id": `${SERVER_CONTEXT}/api/users-id/{id}/`,
  "get-student-by-username": `${SERVER_CONTEXT}/api/students-un/{username}/`,
  "student-home-room-teacher": `${SERVER_CONTEXT}/api/students-lecturer/{lecturerId}/`,
  "get-lecturer-by-username": `${SERVER_CONTEXT}/api/lecturers-un/{username}/`,
  "get-list-student": `${SERVER_CONTEXT}/api/get-list-student/`,
  "get-subject-by-lecturerId": `${SERVER_CONTEXT}/api/subjects/{lecturerId}/`,
  "get-subject-student": `${SERVER_CONTEXT}/api/subjects/studentId/`,
  "get-list-scores": `${SERVER_CONTEXT}/api/scores/`,
  "score-student": `${SERVER_CONTEXT}/api/scores/student-id/`,
  "score-list": `${SERVER_CONTEXT}/api/scores/list/`,
  "add-score": `${SERVER_CONTEXT}/api/add-score/`,
  "excel-add": `${SERVER_CONTEXT}/api/score/excel-add/`,
  "add-comment": `${SERVER_CONTEXT}/api/comments/`,
  "add-post": `${SERVER_CONTEXT}/api/add-post/`,
  "send-mail": `${SERVER_CONTEXT}/api/students/mails/`,
  "get-list-post-by-userId": `${SERVER_CONTEXT}/api/post-user/{userId}`,
  "update-post": `${SERVER_CONTEXT}/api/post-update/{postId}`,
  "delete-post": `${SERVER_CONTEXT}/api/posts-delete/{postId}`,
  "update-comment": `${SERVER_CONTEXT}/api/comment-update/{commentId}`,
  "delete-comment": `${SERVER_CONTEXT}/api/comments/{commentId}`,
  "course-register": `${SERVER_CONTEXT}/api/course-register/`,
  "temporary-course-register": `${SERVER_CONTEXT}/api/temporary-course-register/`,
  "get-subjects-temporary-course-register": `${SERVER_CONTEXT}/api/subjects/temporary-course/`,
  "get-semesters": `${SERVER_CONTEXT}/api/semesters/student/`,
  "delete-course": `${SERVER_CONTEXT}/api/delete_student_subject/{studentSubjectId}`,
  "get-firebase-config": `${SERVER_CONTEXT}/api/firebase/config`,
  "get-student-by-parents": `${SERVER_CONTEXT}/api/get-student-parents/`,
  "get-scores-accumulate": `${SERVER_CONTEXT}/api/scores/accumulate/`,
  "get-academic-warnings": `${SERVER_CONTEXT}/api/scores/academic-warning/`,
  "change-password": `${SERVER_CONTEXT}/api/user/change-password/`,
  "get-final-accumulate": `${SERVER_CONTEXT}/api/scores/final-accumulate/`,
  "get-subject-by-major": `${SERVER_CONTEXT}/api/subjects/majorId/`,
  "get-latest-semester": `${SERVER_CONTEXT}/api/semesters/latest/`,
  "get-temporary-courses": `${SERVER_CONTEXT}/api/get-temporary-course/`,
  "get-already-course": `${SERVER_CONTEXT}/api/get-already-course/`,
  "get-tuition-fee": `${SERVER_CONTEXT}/api/tuition_fee/student/`,
  "payment": `${SERVER_CONTEXT}/pay`
};

export const authApi = () => {
  const token = VueCookies.get("token");
  return axios.create({
    baseURL: SERVER,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
};

export default axios.create({
  baseURL: SERVER,
});
