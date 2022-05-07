<template>
  <v-app id="inspire">
    <v-app-bar app color="#2D3245 " dark>
      <v-row>
        <v-btn icon small :link="true" href="/tpack" class="ml-3">
          <v-icon large color="white darken-2">
            mdi-home
          </v-icon>
        </v-btn>
      </v-row>
      <v-img src="../assets/orion.png" max-width="40"></v-img>
    </v-app-bar>
    <v-main>
      <session />
    </v-main>
    <v-footer color="#2D3245" app dark>
      <span class="white--text">orion-services.dev</span>
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
