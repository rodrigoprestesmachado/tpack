<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png" />
    <Session />
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import Session from "@/components/Session.vue"; // @ is an alias to /src

@Component({
  components: {
    Session
  }
})
export default class Home extends Vue {}
</script>
