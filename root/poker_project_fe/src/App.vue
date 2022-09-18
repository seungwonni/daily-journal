<template>
  <MyHeader/>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
import MyHeader from './components/layout/MyHeader'
import axios from 'axios';

export default {
  name: 'App',
  components: {MyHeader},
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 30px;
}
</style>
