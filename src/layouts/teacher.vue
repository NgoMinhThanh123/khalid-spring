<template>
  <div style="height: 100%">
    <div class="row">
      <div class="col-2 col-md-3 sidebar-desktop" style="padding: 0">
        <a-list bordered style="width: 100%">
          <TheMenu />
          <template #header>
            <div>
              <img src="../assets/logoSchool_1.png" alt="logo" width="100%" />
            </div>
          </template>
        </a-list>
      </div>

      <div class="col col-md-9" style="padding: 0">
        <TheHeader />
        <div class="container" style="padding: 20px; min-height: 84.8vh">
          <router-view></router-view>
        </div>
        <TheFooter />
      </div>
    </div>
  </div>
</template>

<script>
import TheHeader from "../components/TheHeader.vue";
import TheFooter from "../components/TheFooter.vue";
import TheMenu from "../components/TheMenu.vue";
export default {
  components: {
    TheHeader,
    TheFooter,
    TheMenu,
  },
};
</script>
