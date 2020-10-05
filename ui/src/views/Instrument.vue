<template>
  <v-app id="inspire">
    <v-app-bar app color="#009688" dark>
      <v-row>
        <v-btn icon small :link="true" href="/tpack">
          <v-icon large color="white darken-2">
            mdi-home
          </v-icon>
        </v-btn>
        <v-toolbar-title class="ml-2">tpack</v-toolbar-title>
      </v-row>
    </v-app-bar>
    <v-main>
      <session />
    </v-main>
    <v-footer color="#009688" app dark>
      <span class="white--text">ifrs.edu.br</span>
    </v-footer>
  </v-app>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
// @ is an alias to /src
import session from "@/components/Session.vue";

@Component({
  components: { session }
})
export default class Instrument extends Vue {}
</script>
