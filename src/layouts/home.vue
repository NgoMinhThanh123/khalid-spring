<template>
  <TheHeader />
  <slot></slot>
  <TheFooter />
</template>

<script>
import TheHeader from "../components/TheHeader.vue";
import TheFooter from "../components/TheFooter.vue";
import TheMenuHome from "../components/TheMenuHome.vue";

export default {
  components: {
    TheHeader,
    TheFooter,
    TheMenuHome,
  },
};
</script>
