<template>
  <div>
      
   {{session.title}}

   <div v-for="item of session.questions" :key="item.id">
        {{item.text}}
   </div>
   
   <button v-on:click="getSession(session.id - 1)">voltar</button>
   <button v-on:click="getSession(session.id + 1)">Próximo</button>
   
  </div>
</template>

<script lang="ts">
import axios from "axios";
import { Component, Prop, Vue } from "vue-property-decorator";

@Component
export default class Session extends Vue {
  
  private  BASE : string;
  private session: any = [];
  
  constructor() {
      super();
      this.BASE = "http://localhost:9080/service/api/";
      this.getFirstSession(this.BASE);
  }

  getFirstSession(base:string) {
    let url = base + "getFirstSession";
    axios
      .get(url)
      .then((response) => (this.session = response.data));
  }

   getSession(id:string) {
    console.log(id);
    axios
      .get(this.BASE + "getSession/" + id)
      .then((response) => (this.session = response.data));
  }
}
</script>