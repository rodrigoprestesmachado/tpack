<template>
  <v-app id="inspire">
    <v-app-bar app class="app-bar-div" dark>
      <v-row>
        <v-btn icon small :link="true" href="/tpack" class="ml-3">
          <v-icon large color="white darken-2">
            mdi-home
          </v-icon>
        </v-btn>
      </v-row>
      <v-img src="../assets/ifrs.png" max-width="190"></v-img>
    </v-app-bar>
    <v-main>
      <session />
    </v-main>
    <v-footer class="app-bar-div" app dark>
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
