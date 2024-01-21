<template>
  <div class="container d-flex justify-content-center">
  <table>
    <thead>
      <tr>
        <th v-for="item in header">{{ item }}</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td v-for="item in menu">{{ item }}</td>
      </tr>
    </tbody>
  </table>
</div>
</template>

<script>
// import axios from 'axios';
import AuthService from "../services/auth.service";
import { useStore } from 'vuex';

export default {
  name: "Home",
  components: {
    AuthService
  },
  data() {
    return {
      content: [[]],
      header: ["월", "화", "수", "목", "금"],
      menu: [],
    };
  },
  mounted() {
    this.LunchList();
  },
  methods: {
    LunchList() {
      let i = 0;

      const store = useStore();
      const currentUser = store.state.auth.user;

      AuthService.getLunchList(currentUser.username).then(
        (response) => {
          this.content = response.data;

          for (i = 0; i < this.content.length; i++) {
            this.menu.push(this.content[i][1]);
          }
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
  }
}
</script>

<style>

th:first-child,
td:first-child {
  border-right: none;
}

</style>