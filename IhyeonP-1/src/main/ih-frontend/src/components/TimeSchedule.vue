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

export default {
  components: {
    authService
  },
  name: "Home",
  data() {
    return {
      header: ["", "월", "화", "수", "목", "금"],
      ranking: [["1"],["2"],["3"],["4"],["5"],["6"],["7"]]
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
      
      authService.getTimeSchedule().then(
        (res) => {
          let a = new Array();
          a = res.data;

          for(i = 0; i < a[0].length; i++){
            for(j = 0; j < a[0][i]; j++) {
              this.ranking[j].push(a[1][index]);
              index += 1;
            };
            if(j < 7){
              for(j; j < 7; j++){
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
<!-- <style>
.col-md-1-5 {
  width: 1000px;
}
</style> -->

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


table tr:nth-of-type(even) {
  background-color: rgba(0, 0, 255, 0.1);
}
</style>