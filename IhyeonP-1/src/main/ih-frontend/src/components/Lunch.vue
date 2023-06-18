<template>
  <div class="container">
    <button v-on:click="getLunchList">Click me!</button>

  </div>
</template>

<script>
import { Button } from 'bootstrap';
// import axios from 'axios';
import AuthService from "../services/auth.service";

export default {
  name: "Home",
  data() {
    return {};
  },
  mounted() {
  },
  methods: {
    getLunchList() {
      AuthService.getLunchList().then(
        (response) => {
          this.content = response.data;
          console.log(this.content);
        },
        (error) => {
          this.content =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
        }
      );
    }
  },
  components: { Button }
}
</script>
