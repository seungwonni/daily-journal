<template>
  <div>
    <ul>
      <li v-for="item in {boards}">{{ item }}</li>
    </ul>
  </div>
</template>
<script>
import axios from "axios";

export default {
  name: "SettingPlayer",
  data() {
    return {
      boards : [],
    }
  },
  mounted() {
    console.log("bofore boards :" + this.boards);
    this.boards = this.init();// hello
    console.log("after boards :" + this.boards);
  },
  methods : {
    init() {
      const number = Number(this.$route.params.playerNum);
      const data = {"playerNum":number};
      console.log(data);
      const url = "http://localhost:8080/poker/home/set-card?playerNum="+number;
      const headers = "Access-Control-Allow-Origin : localhost:8080";

      axios.get(url,
          headers
      ).then(function (response) {
        return response.data.result.board;
      }).catch(function(error){
        console.log(error);
      })
    },
  }

  // methods: { // 메서드 구현
  //   submitForm: function() {
  //     const data = {
  //       player: this.player
  //     }
  //     const url = "http://192.168.1.156:8080/poker/home/main.do"
  //     axios.get(url, {
  //       params: data,
  //       timeout: 1000
  //     })
  //         .then(function(response){
  //           console.log(response);
  //         })
  //         .catch(function(error){
  //           console.log(error);
  //         })
  //     console.log(this.username, this.password);
  //   }
  // }

}
</script>

<style scoped>

</style>