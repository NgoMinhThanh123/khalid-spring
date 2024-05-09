<template>
   <div class="container-fluid" style="padding-bottom: 50px">
    <div class="row">
      <div class="col-12 d-flex justify-content-end">
        <form class="d-flex">
          <input
            class="form-control me-2"
            type="search"
            placeholder="Search"
            v-model="searchKeyword"
          />
          <button
            class="btn btn-primary"
            style="width: 150px"
            @click="handleSearch"
          >
            Tìm kiếm
          </button>
        </form>
      </div>

      <div class="post-container" v-if="isEditMode">
        <label for="comment">Tiêu đề:</label>
        <textarea
          class="form-control"
          rows="1"
          id="comment"
          name="text"
          placeholder="Nhập tiêu đề bài viết"
          v-model="title"
        ></textarea>
        <label for="comment">Nội dung:</label>
        <textarea
          class="form-control"
          rows="5"
          id="comment"
          name="text"
          placeholder="Nhập nội dung bài viết"
          v-model="content"
        ></textarea>
        <Button class="btn-title btn btn-primary" @click="handlePostSubmit"
          >Đăng</Button
        >
        <Button class="btn-title btn btn-primary" @click="exitHandleEdit"
          >Thoát</Button
        >
      </div>
      <div class="col-12 post-container d-flex" v-else>
        <Button class="btn-title btn btn-primary" @click="handleEdit"
          >Đăng bài viết</Button
        >
        <Button class="btn-title btn btn-primary"
          ><router-link
            class="post-link"
            to="/student/posted/"
            style="color: #fff"
            >Bài viết đã đăng</router-link
          ></Button
        >
      </div>

      <div class="foroum">
        <table class="table table-responsive-xl" v-if="post.length > 0">
          <thead>
            <tr>
              <th>Tên bài post</th>
              <th>Người đăng</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="p in currentPosts" :key="p.id">
              <td style="width: 60%">
                <router-link
                  :to="'/student/detailForum/' + p.id"
                  class="post-link"
                >
                  {{ p.title }}
                </router-link>
              </td>
              <td class="d-flex align-items-center">
                <div
                  class="img"
                  :style="{
                    'background-image': `url('${p.userId.avatar}')`,
                    'background-size': 'cover',
                    'background-repeat': 'no-repeat',
                  }"
                ></div>

                <div class="email">
                  <span>
                    {{ p.userId.username }}
                  </span>
                  <span>Thời gian: {{ formatDate(p.postTime) }}</span>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <ul class="pagination">
        <li class="page-item">
          <a
            class="page-link"
            href="#"
            @click="previousPage"
            :disabled="currentPage === 1"
            >Previous</a
          >
        </li>
        <li
          class="page-item"
          v-for="pageNumber in pageNumbers"
          :key="pageNumber"
        >
          <a
            class="page-link"
            href="#"
            @click="setCurrentPage(pageNumber)"
            :class="{ active: currentPage === pageNumber }"
            >{{ pageNumber }}</a
          >
        </li>
        <li class="page-item">
          <a
            class="page-link"
            href="#"
            @click="nextPage"
            :disabled="currentPage === pageNumbers.length"
            >Next</a
          >
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import Apis, { authApi, endpoints } from "@/configs/Apis.js";
import { useMenu } from "../../../stores/use-menu.js";
export default {
  setup() {
    useMenu().onSelectedKeys(["student-forum"]);
  },
  data() {
    return {
      isEditMode: false,
      post: [],
      title: "",
      content: "",
      currentPage: 1,
      postsPerPage: 4,
      searchKeyword: "",
    };
  },
  computed: {
    indexOfLastPost() {
      return this.currentPage * this.postsPerPage;
    },
    indexOfFirstPost() {
      return this.indexOfLastPost - this.postsPerPage;
    },
    currentPosts() {
      return this.post
        .filter(
          (p) =>
            p.title.toLowerCase().includes(this.searchKeyword.toLowerCase()) ||
            p.content.toLowerCase().includes(this.searchKeyword.toLowerCase())
        )
        .slice(this.indexOfFirstPost, this.indexOfLastPost);
    },
    pageNumbers() {
      const pageNumbers = [];
      for (
        let i = 1;
        i <= Math.ceil(this.post.length / this.postsPerPage);
        i++
      ) {
        pageNumbers.push(i);
      }
      return pageNumbers;
    },
  },
  created() {
    this.loadPost();
  },
  methods: {
    handleEdit() {
      this.isEditMode = true;
    },
    exitHandleEdit() {
      this.isEditMode = false;
    },
    async loadPost() {
      try {
        const response = await authApi().get(endpoints["posts"], {
          params: { kw: this.searchKeyword },
        });
        this.post = response.data;
      } catch (error) {
        console.error("Error loading posts:", error);
      }
    },
    handleSearch() {
      this.loadPost();
    },
    async handlePostSubmit() {
      try {
        const response = await authApi().post(endpoints["add-post"], {
          title: this.title,
          content: this.content,
        });
        this.post.push(response.data);
        this.title = "";
        this.content = "";
        this.isEditMode = false;
      } catch (error) {
        console.error("Error submitting post:", error);
      }
    },
    formatDate(date) {
      if (!date) return ""; // Tránh xử lý ngày null hoặc undefined

      // Chuyển đối đối tượng ngày sang ngày
      const formattedDate = new Date(date);

      // Lấy thông tin về ngày, tháng và năm
      const day = formattedDate.getDate();
      const month = formattedDate.getMonth() + 1; // Lưu ý: Tháng bắt đầu từ 0
      const year = formattedDate.getFullYear();

      // Định dạng thành chuỗi "ngày/tháng/năm"
      return `${day}/${month}/${year}`;
    },
    previousPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    },
    nextPage() {
      if (this.currentPage < this.pageNumbers.length) {
        this.currentPage++;
      }
    },
    setCurrentPage(page) {
      this.currentPage = page;
    },
  },
  watch: {
    searchKeyword: "loadPost",
  },
};
</script>