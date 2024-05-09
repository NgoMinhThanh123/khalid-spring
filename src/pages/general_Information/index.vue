<template>
  <Home>
    <section class="general">
      <div>
        <div class="sectionLayout_Banner">
          <span class=""
            ><img
              class="img-school"
              src="../../assets/backgound_school.jpg"
              alt="Ảnh nền trường"
          /></span>
        </div>
        <div class="sectionLayout_children_container">
          <h1 class="title_backgound">
            <p>Giới thiệu chung</p>
          </h1>
          <div class="content_background">
            <p>
              Được thành lập vào năm 1990 và trở thành trường đại học công lập
              từ năm 2006, đến nay Trường Đại học Mở Thành phố Hồ Chí Minh là
              trường đại học đa ngành trực thuộc Bộ Giáo dục và Đào tạo, có
              nhiệm vụ đào tạo đại học và sau đại học, với các hình thức đào tạo
              chính quy và giáo dục thường xuyên, đào tạo các điểm vệ tinh,…nhằm
              đáp ứng nhu cầu học tập đa dạng của xã hội, góp phần tăng cường
              đội ngũ cán bộ khoa học-kỹ thuật cho đất nước.
            </p>
          </div>
        </div>
      </div>
    </section>
    <section class="general_1">
      <div class="container-fluid mt-3 container">
        <h2 class="title_content">Thông tin chi tiết</h2>
        <div class="container">
          <div class="row">
            <div class="col" style="padding: 10px 24px">
              <div class="item">
                <h5>Tên trường</h5>
                <div>
                  <p>Tiếng Việt: Trường Đại học Mở Thành Phố Hồ Chí Minh</p>
                  <p>Tiếng Anh: Ho Chi Minh City Open University</p>
                </div>
              </div>
              <div class="item">
                <h5>Tên viết tắt của trường</h5>
                <div>
                  <p>Tiếng Việt: Trường Đại học Mở Tp. Hồ Chí Minh</p>
                  <p>Tiếng Anh: HCMCOU</p>
                </div>
              </div>
              <div class="item">
                <h5>Tên trước đây</h5>
                <div>
                  <p>
                    Viện Đào Tạo Mở Rộng TP. Hồ Chí Minh (từ năm 1990) Đại học
                    Mở Bán Công TP. Hồ Chí Minh (từ năm 1993 đến năm 2005)
                  </p>
                </div>
              </div>
            </div>
            <div class="col" style="padding: 10px 24px">
              <div class="item">
                <h5>Cơ quan/Bộ chủ quản</h5>
                <div>
                  <p>
                    Trường Đại học Mở TP. Hồ Chí Minh trực thuộc Bộ giáo dục và
                    Đào tạo
                  </p>
                </div>
              </div>
              <div class="item">
                <h5>Địa chỉ liên lạc</h5>
                <div>
                  <p>
                    Số 97 Võ Văn Tần, Phường Võ Thị Sáu, Quận 3, TP. Hồ Chí Minh
                  </p>
                  <p>Điện thoại: (028) 3930 0210</p>
                  <p>Fax: (028) 3930 0083</p>
                  <p>E-mail: <a>ou@ou.edu.vn</a></p>
                  <p>Website: <a> www.ou.edu.vn</a></p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <div>
      <img loading="lazy" src="../../assets/bg-bottom.png" alt="logo bottom" style="margin: 0px auto; display: block;" />
    </div>
  </Home>
</template>

<script>
import Home from "../../layouts/home.vue";

export default {
  components: {
    Home,
  },
};
</script>

<style>
p {
  margin: 0;
  padding: 0;
}

.item {
  margin-top: 10px;
}

.general {
  min-height: 80vh;
  position: relative;
}
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

.sectionLayout_Banner {
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  z-index: 0;
}

.sectionLayout_Banner span {
  box-sizing: border-box;
  display: block;
  overflow: hidden;
  width: initial;
  height: initial;
  background: none;
  opacity: 1;
  border: 0;
  margin: 0;
  padding: 0;
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
}

.img-school {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  box-sizing: border-box;
  padding: 0;
  border: none;
  margin: auto;
  display: block;
  width: 0;
  height: 0;
  min-width: 100%;
  max-width: 100%;
  min-height: 100%;
  max-height: 100%;
  object-fit: cover;
}

.sectionLayout_Banner::after {
  content: "";
  display: block;
  position: absolute;
  height: 100%;
  width: 100%;
  background: linear-gradient(
      180deg,
      rgba(27, 5, 5, 0) 69.21%,
      rgba(9, 2, 2, 0.61) 84.74%,
      rgba(0, 0, 0, 0.98)
    ),
    linear-gradient(0deg, rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5));
  z-index: 1;
  top: 0;
  left: 0;
}

.sectionLayout_children_container {
  width: 90% !important;
  max-width: 1440px !important;
  margin: auto !important;
  position: relative;
  height: 100%;
}

.title_backgound {
  font-size: 3.25rem;
  color: #fff;
  font-weight: 700;
  position: absolute;
  top: 130px;
  width: 50%;
  display: flex;
  gap: 30px;
}

.title_backgound::before {
  content: "";
  display: block;
  background-color: #dfb36f;
  height: auto;
  width: 10px;
}

.content_background {
  position: absolute;
  color: #fff;
  bottom: -450px;
  right: 0;
  width: 45%;
  line-height: 1.5;
  font-family: IBM Plex Sans;
  font-size: 1.125rem;
  font-weight: 300;
}
</style>
