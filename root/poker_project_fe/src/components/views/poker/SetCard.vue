<template>
  <div>
    <input @click="init()" value="버튼" type="button"/>
  </div>
  <span v-for="item in boards">{{item}} </span>
  <ul>
      <li v-for="item in playerCards">{{item.firstCard}} , {{item.secondCard}}</li>
  </ul>
</template>
<script>
import axios from "axios";

export default {
  data() {
    return {
      boards : Object,
      playerCards : Object,
    }
  },
  methods : {
    init() {
      const number = Number(this.$route.params.playerNum);
      const url = "http://localhost:8080/poker/home/set-card?playerNum="+number;
      const headers = "Access-Control-Allow-Origin : localhost:8080";
      axios.get(url,headers)
          .then(response => {
          const {data} = response;
          this.boards = data.result.board;
          this.playerCards = data.result.playerCard;
      })
    },
  },
  mounted() {
    this.init();
  },
}
</script>

<style scoped>

</style>