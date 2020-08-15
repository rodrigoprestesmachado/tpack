<template>
  <v-card flat color="transparent">
    {{session.title}}
    <div v-for="item of session.questions" :key="item.id">
      <v-subheader>{{item.text}}</v-subheader>
      <v-slider thumb-label="always" :step="0.1" :max="5" :min="1" />
    </div>
    <div v-if="backButton">
      <v-btn small color="#D3EAE1" v-on:click="getSession(session.id - 1)">Voltar</v-btn>
    </div>
    <v-btn small color="#D3EAE1" v-on:click="getSession(session.id + 1)">Continuar</v-btn>
  </v-card>
</template>

<script lang="ts">
import axios from "axios";
import { Component, Vue } from "vue-property-decorator";

@Component
export default class Session extends Vue {
  private readonly BASE: string;
  private session: any = [];
  private backButton: boolean;

  constructor() {
    super();
    this.backButton = false;
    this.BASE = "http://localhost:9080/service/api/";
    this.getFirstSession(this.BASE);
  }

  getFirstSession(base: string) {
    const url = base + "getFirstSession";
    axios.get(url).then(response => (this.session = response.data));
  }

  getSession(id: string) {
    console.log(id);
    axios.get(this.BASE + "getSession/" + id).then(response => {
      this.backButton = true;
      this.session = response.data;
    });
  }
}
</script>