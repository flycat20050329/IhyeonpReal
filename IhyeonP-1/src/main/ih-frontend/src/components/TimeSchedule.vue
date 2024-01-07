<template>
  <div class="container">
    <table style="margin-left: auto; margin-right: auto;">
      <thead>
        <th v-for="item in header">{{ item }}</th>
      </thead>
      <tbody>
        <tr v-for="line in ranking">
          <td v-for="item in line" style="height: 50px">{{ item }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
import authService from '../services/auth.service';
import { useStore } from 'vuex';

export default {
  components: {
    authService
  },
  name: "Home",
  data() {
    return {
      header: ["", "월", "화", "수", "목", "금"],
      ranking: [["1"], ["2"], ["3"], ["4"], ["5"], ["6"], ["7"]]
    }
  },
  mounted() {
    this.timeSchedule();
  },
  methods: {
    timeSchedule() {
      let index = 0;
      let i = 0;
      let j = 0

      const store = useStore();
      const currentUser = store.state.auth.user;

      authService.getTimeSchedule(currentUser.username).then(
        (res) => {
          console.log(res.data);

          let a = new Array();
          a = res.data;

          for (i = 0; i < a[0].length; i++) {
            for (j = 0; j < a[0][i]; j++) {
              this.ranking[j].push(a[1][index]);
              index += 1;
            };
            if (j < 7) {
              for (j; j < 7; j++) {
                this.ranking[j].push("");
              }
            }
          }
        }
      )
    }
  }
}
</script>

<style>
.container {
  height: 500px;
}

table {
  width: 75%;
  height: 100%;
  text-align: center;

}

table th {
  height: 50px !important;
  width: 10%;
  border-bottom: 2px solid darkgray;
}

table thead {
  height: 50px !important;
  width: 10%;
}

table tbody {
  height: 50px !important;
}

table tr {
  height: 50px !important;
}

table td {
  padding: 12px;
  height: 50px !important;
  width: 10%;
}

@font-face {
  font-family: "Maplestory";
  src: url("../fonts/MaplestoryBold.ttf") format('opentype');
  font-weight: bold;
  font-style: normal;
}

@font-face {
  font-family: "Maplestory";
  src: url("../fonts/MaplestoryLight.ttf") format('opentype');
  font-weight: normal;
  font-style: normal;
}

thead {
  font-family: "Maplestory";
  font-weight: bold;
  font-size: x-large;
  background-color: rgb(20, 129, 218);
  color: antiquewhite;
}

tbody {
  font-family: "MapleStory";
  font-weight: normal;
  font-size: large;
}

th:first-child,
td:first-child {
  border-right: 1px solid black;
}

table tr:nth-of-type(even) {
  background-color: rgba(111, 124, 121, 0.132);
}
</style>