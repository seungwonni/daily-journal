<template>
  <div>
    <h3>플레이어할 인원을 선택하여 주세요.</h3>
<!--    <form v-on:submit.prevent="submitForm"></form>-->
    <input type="number" v-model="player" />
    <router-link :to="{path:'/poker/set-card/'+this.player}">
        <input type="button" value="선택"/>
      </router-link>
    <input type="button" value="변경" @click="changeFuc"/>

  </div>
</template>
<script>


export default {
  name: "SettingPlayer",
  data() {
    return {
      player: null
    }
  },
}
</script>

<style scoped>

</style>