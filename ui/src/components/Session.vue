<template>
  <v-card v-if="loaded" flat color="transparent">
    {{sessions[current].title}}
    <div v-for="question of sessions[current].questions" :key="question.id">
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
    <v-btn v-if="previousButton" small fab color="#D3EAE1" v-on:click="checkButtonsState(-1)">
      <v-icon dark>mdi-arrow-left</v-icon>
    </v-btn>
    <v-btn v-if="nextButton" small fab color="#D3EAE1" v-on:click="checkButtonsState(1)">
      <v-icon dark>mdi-arrow-right</v-icon>
    </v-btn>
  </v-card>
</template>

<script lang="ts">
import axios from "axios";
import { Component, Vue } from "vue-property-decorator";

@Component
export default class Session extends Vue {
  /** The base URL */
  private readonly BASE = "http://localhost:9080/service/api/";
  /** Stores the answer of the users to only sync with the interface */
  private answer: any = [];
  /** The session with the questions */
  private sessions: any = [];
  /** controls the button used to back the sessions */
  private nextButton = true;
  /** controls the button used to back the sessions */
  private previousButton = false;
  /** controls the current session */
  private current = 0;
  /** kust controls the load from server  */
  private loaded = false;

  mounted(){
    this.getSessions();
  }

  /**
   * Gets the sessions from the service
   *
   * @param base The URL base of the service
   */
  getSessions() {
    const url = this.BASE + "getSessions";
    axios.get(url).then(response => {
      this.sessions = response.data;
      this.loadFromLocalStorage();
      this.loaded = true;
    });
  }

  checkButtonsState(next:number){
    if ((this.current + next) == (this.sessions.length - 1)){
      this.nextButton = false;
      this.previousButton = true;
    }
    else if((this.current + next) == 0 ){
      this.nextButton = true;
      this.previousButton = false;
    }
    else if((this.current + next) > 0 ){
      this.nextButton = true;
      this.previousButton = true;
    }

    this.current = this.current + next;
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