<template>
  <v-card flat color="transparent">
    {{session.title}}
    <div v-for="question of session.questions" :key="question.id">
      <v-subheader>{{question.text}}</v-subheader>
      <v-slider
        :value="answer[question.id]"
        thumb-label="always"
        @change="onChange(question.id, $event)"
        :step="0.1"
        :max="5"
        :min="1"
      />
    </div>
    <div v-if="backButton">
      <v-btn small fab color="#D3EAE1" v-on:click="getSession(session.id - 1)">
        <v-icon dark>mdi-arrow-left</v-icon>
      </v-btn>
    </div>
    <div v-if="fowardButton">
     <v-btn small fab color="#D3EAE1" v-on:click="getSession(session.id + 1)">
       <v-icon dark>mdi-arrow-right</v-icon>
    </v-btn>
    </div>
  </v-card>
</template>

<script lang="ts">
import axios from "axios";
import { Component, Vue } from "vue-property-decorator";

@Component
export default class Session extends Vue {
  /** The base URL */
  private readonly BASE: string;
  /** Stores the answer of the users to only sync with the interface */
  private answer: any = [];
  /** The session with the questions */
  private session: any = [];
  /** controls the button used to back the sessions */
  private backButton: boolean;
  /** controls the button used to back the sessions */
  private fowardButton: boolean;
  
  constructor() {
    super();
    this.backButton = false;
    this.fowardButton = true;
    this.BASE = "http://localhost:9080/service/api/";
    this.getFirstSession(this.BASE);
  }

  /**
   * Gets the first session from the service
   * 
   * @param base The URL base of the service
   */
  getFirstSession(base: string) {
    const url = base + "getFirstSession";
    axios.get(url).then(response => {
      this.session = response.data
      this.loadFromLocalStorage();
    });
  }

  /**
   * Gets the a spefific session from the service
   * 
   * @param id The identificator of the session
   */
  getSession(id: string) {
    axios.get(this.BASE + "getSession/" + id).then(response => {
      this.backButton = true;
      if (response.data)
        this.session = response.data;
      else{
          if (this.fowardButton){
            this.fowardButton = false;
            this.backButton = true;
          }
          else{
            this.fowardButton = true;
            this.backButton = false;
          }

      }
      
    });
  }

  /**
   * Registers the answer of the user
   * 
   * @param idQuestion The identificator of the question
   * @param response  The answer of the user
   */
  onChange(idQuestion: string, response: string) {
    // this.answer only controls the user's interface
    this.answer[idQuestion] = response;
    // store the answers in the local storage
    localStorage.setItem(idQuestion, response);
  }

  /**
   * Loads the data from local storage
   */
  loadFromLocalStorage() {
    for (let i = 0; i < localStorage.length; i++) {
      this.answer[i] = localStorage.getItem(i.toString());
    }
  }
}
</script>