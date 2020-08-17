<template>
  <v-app id="inspire">

    <v-app-bar
      app
      color="#139A65"
      dark
    >
      <v-toolbar-title>Tpack</v-toolbar-title>
    </v-app-bar>

    <v-main>
      <session/>
    </v-main>
    <v-footer
      color="#139A65"
      app>
      <span class="white--text">IFRS &copy; {{ new Date().getFullYear() }}</span>
    </v-footer>
  </v-app>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import session from "@/components/Session.vue"; // @ is an alias to /src

@Component({
  components: {
    session
  }
})
export default class About extends Vue {}
</script>