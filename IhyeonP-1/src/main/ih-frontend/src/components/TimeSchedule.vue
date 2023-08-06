<template>
  <table style="margin-left: auto; margin-right: auto;">
    <thead>
      <th v-for="item in header">{{ item }}</th>
    </thead>
    <tbody>
      <tr v-for="line in ranking">
        <td v-for="item in line">{{ item }}</td>
      </tr>
    </tbody>
  </table>
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
      header: ["요일", "1", "2", "3", "4", "5", "6"],
      ranking: [
        ['java', 1, 2, 1, 1, 16],
        ['c', 2, 1, 2, 2, 1],
        ['c++', 3, 4, 3, 3, 2],
        ['python', 4, 7, 6, 11, 23],
        ['JS', 17, 18, 8, 7, 20],
        ['Vue', 10, 15, 28, 45, 100]
      ]
    }
  },
  mounted() {
    this.timeSchedule();
  },
  methods: {
    timeSchedule() {
      authService.getTimeSchedule().then(
      (res) => {
        this.ranking = res.data;
        console.log(res.data);
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
table {
  width: 75%;
  text-align: left;
  
}

tanle th {
  padding: 12px;
  border-bottom: 2px solid darkgray;
}

table td {
  padding: 12px;
  width: 10%;
}


table tr:nth-of-type(even) {
  background-color: rgba(0, 0, 255, 0.1);
}</style>