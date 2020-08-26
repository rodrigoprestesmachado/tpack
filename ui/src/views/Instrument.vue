<template>
  <v-app id="inspire">
    <v-app-bar app color="#139A65" dark>
      <v-toolbar-title>Tpack</v-toolbar-title>
    </v-app-bar>
    <v-main>
      <session />
    </v-main>
    <v-footer color="#139A65" app dark>
      <span class="white--text">IFRS &copy; {{ new Date().getFullYear() }}</span>
    </v-footer>
  </v-app>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
// @ is an alias to /src
import session from "@/components/Session.vue";

@Component({
  components: {
    session
  }
})
export default class Instrument extends Vue {}
</script>
