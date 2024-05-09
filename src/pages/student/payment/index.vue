<template>
  <section>
    <div class="container">
      <table class="table">
        <thead>
          <tr class="table-title">
            <th scope="col" class="text-center" style="width: 5%">STT</th>
            <th scope="col" class="text-center" style="width: 25%">
              Niên học học kỳ
            </th>
            <th scope="col" class="text-center" style="width: 20%">
              Học phí phải thu
            </th>
            <th scope="col" class="text-center" style="width: 20%">
              Học phí đã thu
            </th>
            <th scope="col" class="text-center" style="width: 15%">Còn nợ</th>
            <th style="width: 15%"></th>
          </tr>
        </thead>
        <tbody>
          <tr class="table-title-item">
            <th colspan="6">Thu học phí</th>
          </tr>
          <tr v-for="(tuitionFee, index) in listTuitionFee" :key="index">
            <th
              scope="row"
              style="width: 5%; text-align: center; vertical-align: middle"
            >
              {{ index + 1 }}
            </th>
            <td style="width: 25%; vertical-align: middle" class="text-center">
              {{ tuitionFee.semesterId.name }} - Năm học
              {{ tuitionFee.semesterId.schoolYear }}
            </td>
            <td style="width: 20%; vertical-align: middle" class="text-end">
              <div v-if="!tuitionFee.done">
                {{ formattedCurrency(tuitionFee.tuitionFee) }}
              </div>
              <div v-else>{{ formattedCurrency(0) }}</div>
            </td>
            <td style="width: 20%; vertical-align: middle" class="text-end">
              <div v-if="tuitionFee.done">
                {{ formattedCurrency(tuitionFee.tuitionFee) }}
              </div>
              <div v-else>{{ formattedCurrency(0) }}</div>
            </td>
            <td style="width: 15%; vertical-align: middle" class="text-end">
              {{ formattedCurrency(0) }}
            </td>
            <td style="width: 15%">
              <button
                class="button"
                v-if="!tuitionFee.done"
                @click="submitTuitionFee(tuitionFee.id)"
              >
                <p>Thanh toán</p>
              </button>
            </td>
          </tr>
          <!-- <tr>
            <th scope="row" style="width: 10%">2</th>
            <td>Jacob</td>
            <td>Thornton</td>
            <td>@fat</td>
            <td>@fat</td>
          </tr>
          <tr>
            <th scope="row" style="width: 10%">3</th>
            <td>Larry the Bird</td>
            <td>@twitter</td>
            <td>@twitter</td>
          </tr> -->
        </tbody>
      </table>
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
      isSuccess: false,
      listTuitionFee: [],
    };
  },
  methods: {
    formatYear(year) {
      return new Date(year).getFullYear();
    },
    formattedCurrency(amount) {
      return new Intl.NumberFormat("vi-VN", {
        style: "currency",
        currency: "VND",
      }).format(amount);
    },
    async getTuitionFee() {
      try {
        const studentId = this.getUser.username;
        console.log(studentId);
        const res = await authApi().get(
          endpoints["get-tuition-fee"] + `?studentId=${studentId}`
        );
        console.log("tuition fee:", res.data);
        this.listTuitionFee = res.data;
        this.isSuccess = true;
      } catch (e) {
        console.log(e.error);
      }
    },
    async submitTuitionFee(tuitionFeeId) {
      const res = await Apis.post(
        endpoints["payment"] + `?tuitionFeeId=${tuitionFeeId}`
      );

      if (res.data) {
        // Lấy token từ phản hồi
        const paymentToken = res.data;

        // Chuyển hướng người dùng đến trang PayPal
        window.location.href = paymentToken;
      } else {
        console.error("Invalid payment token received.");
      }
    },
  },
  async created() {
    await this.getTuitionFee();
  },
};
</script>

<style>
.table-title th {
  background: #070758;
  color: #fff;
}

.table-title-item th {
  background: rgba(7, 7, 88, 0.6);
}

.button {
  all: unset;
  display: flex;
  align-items: center;
  position: relative;
  padding: 4px 20px;
  border: #070758 solid 0.15em;
  border-radius: 0.25em;
  color: #000;
  font-size: 16px;
  font-weight: 600;
  cursor: pointer;
  overflow: hidden;
  transition: border 300ms, color 300ms;
  user-select: none;
  margin: auto;
}

.button p {
  z-index: 1;
  margin: 0;
  padding: 0;
}

.button:hover {
  color: #fff;
}

.button:active {
  border-color: #070758;
}

.button::after,
.button::before {
  content: "";
  position: absolute;
  width: 9em;
  aspect-ratio: 1;
  background: #070758;
  opacity: 50%;
  border-radius: 50%;
  transition: transform 500ms, background 300ms;
}

.button::before {
  left: 0;
  transform: translateX(-8em);
}

.button::after {
  right: 0;
  transform: translateX(8em);
}

.button:hover:before {
  transform: translateX(-1em);
}

.button:hover:after {
  transform: translateX(1em);
}

.button:active:before,
.button:active:after {
  background: teal;
}
</style>
