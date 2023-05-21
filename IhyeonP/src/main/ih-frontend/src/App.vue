<template>
  <div>
    <!-- <sidenav
      :custom_class="color"
      :class="[isRTL ? 'fixed-end' : 'fixed-start']"
      v-if="showSidenav"
    /> -->
    <main
      class="main-content position-relative max-height-vh-100 h-100 overflow-x-hidden"
    >
      <!-- nav -->
      <!-- <navbar
        :class="[isNavFixed ? navbarFixed : '', isAbsolute ? absolute : '']"
        :color="isAbsolute ? 'text-white opacity-8' : ''"
        :minNav="navbarMinimize"
        v-if="showNavbar"
      /> -->
      <router-view />
      <!-- <app-footer v-show="showFooter" /> -->
    </main>
  </div>
</template>

<script>

export default {
  name: "App",
  components: {
  },
  methods: {
  },
  computed: {
  },
};
</script>
