<template>
  <div id="demo" class="carousel slide" data-bs-ride="carousel">
    <!-- Indicators/dots -->
    <div class="carousel-indicators">
      <button
        type="button"
        data-bs-target="#demo"
        data-bs-slide-to="0"
        class="active"
      ></button>
      <button
        type="button"
        data-bs-target="#demo"
        data-bs-slide-to="1"
      ></button>
      <button
        type="button"
        data-bs-target="#demo"
        data-bs-slide-to="2"
      ></button>
      <button
        type="button"
        data-bs-target="#demo"
        data-bs-slide-to="3"
      ></button>
      <button
        type="button"
        data-bs-target="#demo"
        data-bs-slide-to="4"
      ></button>
    </div>

    <!-- The slideshow/carousel -->
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img
          src="../assets/anh1.jpg"
          alt="Los Angeles"
          class="d-block"
          style="width: 100%; height: 400px"
        />
      </div>
      <div class="carousel-item">
        <img
          src="../assets/anh2.jpg"
          alt="Chicago"
          class="d-block"
          style="width: 100%; height: 400px"
        />
      </div>
      <div class="carousel-item">
        <img
          src="../assets/anh3.jpg"
          alt="New York"
          class="d-block"
          style="width: 100%; height: 400px"
        />
      </div>
      <div class="carousel-item">
        <img
          src="../assets/moe-masthead-vn-1440x450.jpg"
          alt="New York"
          class="d-block"
          style="width: 100%; height: 400px"
        />
      </div>
      <div class="carousel-item">
        <img
          src="../assets/news-masthead-blended-learning.jpg"
          alt="New York"
          class="d-block"
          style="width: 100%; height: 400px"
        />
      </div>
    </div>

    <!-- Left and right controls/icons -->
    <button
      class="carousel-control-prev"
      type="button"
      data-bs-target="#demo"
      data-bs-slide="prev"
    >
      <span class="carousel-control-prev-icon"></span>
    </button>
    <button
      class="carousel-control-next"
      type="button"
      data-bs-target="#demo"
      data-bs-slide="next"
    >
      <span class="carousel-control-next-icon"></span>
    </button>
  </div>
</template>

<script></script>
