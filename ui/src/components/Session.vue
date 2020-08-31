<template>
  <div>
    <!-- error Message -->
    <template>{{errorMessage}}</template>

    <!--
      load feedback
    -->
    <v-progress-circular
      align="center"
      justify="center"
      indeterminate
      color="primary"
      v-if="(loaded === false) && (errorMessage === '')"
    ></v-progress-circular>

    <!-- main container -->
    <v-container v-if="loaded" align="center" justify="center">
      {{errorMessage}}
      {{ sessions[current].title }}
      <div v-for="question of sessions[current].questions" :key="question.id">
        <v-subheader>{{ question.text }}</v-subheader>

        <!-- Scale queston  -->
        <v-slider
          v-if="question.type == 'SCALE'"
          v-model="answer[question.id]"
          thumb-label="always"
          :step="0.1"
          :max="5"
          :min="1"
          aria-label="classifique"
        />

        <!-- Multiple choice question -->
        <template v-if="question.type == 'MULTIPLE'">
          <div v-for="choice of question.choices" :key="choice.id">
            <v-checkbox
              class="mx-5"
              v-model="answer[question.id]"
              :value="choice.id"
              :label="choice.text"
              multiple
            ></v-checkbox>
          </div>
        </template>
      </div>

      <!-- Next and previus buttons -->
      <v-row no-gutters>
        <v-col>
          <v-btn
            v-if="previousButton"
            small
            fab
            color="#D3EAE1"
            v-on:click="nextSession(-1)"
            aria-label="voltar para as questões anteriores"
          >
            <v-icon dark>mdi-arrow-left</v-icon>
          </v-btn>
        </v-col>
        <v-col>
          <v-btn
            v-if="nextButton"
            small
            fab
            color="#D3EAE1"
            v-on:click="nextSession(1)"
            aria-label="próximas questões"
          >
            <v-icon dark>mdi-arrow-right</v-icon>
          </v-btn>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script lang="ts">
// TODO remove the comments
// To use with Electron
// https://medium.com/@bromix/electron-application-with-vue-js-and-vuetify-f2a1f9c749b8

//API localidades
//https://servicodados.ibge.gov.br/api/docs/localidades?versao=1#api-Distritos-estadosUFDistritosGet
import axios from "axios";
import { Component, Vue } from "vue-property-decorator";
import installExtension from "electron-devtools-installer";
import Axios from "axios";
import { createProtocol } from "vue-cli-plugin-electron-builder/lib";

//Components
import multiple from "./Multiple.vue";

@Component({
  components: {
    multiple
  }
})
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
  /** controls if the data was loaded from server  */
  private loaded = false;
  /** indicates an app error */
  private errorMessage = "";

  /**
   * Gets the sessions and questions from the server
   */
  created() {
    this.getSessions();
  }

  /**
   * Gets the sessions from the service
   *
   * @param base The URL base of the service
   */
  async getSessions() {
    const url = this.BASE + "getSessions";
    try {
      const resp = await axios.get(url);
      this.sessions = resp.data;
      this.loaded = true;
      this.loadFromLocalStorage();
    } catch (error) {
      this.errorMessage = "Serviço indisponível no momento";
      console.log(this.errorMessage);
    }
  }

  /**
   * Controls the changes of the sessions
   *
   * @param next the number used to move to the next session. For example,
   * next=1 move to the next session and next=-1 moves to previus session
   */
  nextSession(next: number) {
    // saves in local storage
    this.saveLocalStorage();

    if (this.current + next == this.sessions.length - 1) {
      this.nextButton = false;
      this.previousButton = true;
    } else if (this.current + next == 0) {
      this.nextButton = true;
      this.previousButton = false;
    } else if (this.current + next > 0) {
      this.nextButton = true;
      this.previousButton = true;
    }
    this.current = this.current + next;
  }

  /***
   * Saves the data from answer array in local storage
   */
  saveLocalStorage() {
    for (const key in this.answer) {
      const value = this.answer[key];
      localStorage.setItem(key, value);
    }
  }

  /**
   * Loads the data from local storage
   */
  loadFromLocalStorage() {
    if (localStorage.length !== 0) {
      // gets all keys from local storage
      const keys = Object.keys(localStorage);
      let k = keys.length;
      while (k--) {
        // gets the value from local storage
        const value = localStorage.getItem(keys[k]);
        if (this.checkQuestionType(keys[k]) !== "MULTIPLE") {
          this.answer[keys[k]] = value;
        } else {
          // When the answer is multiple we need to create a array of numbers
          // from local storage to Vuetify render the interface
          const strArray = value.split(",");
          const numberArray = [];
          for (const x in strArray) numberArray.push(Number(strArray[x]));
          this.answer[keys[k]] = numberArray;
        }
      }
    }
  }

  /**
   * Verifies the type of the question
   *
   * @param idQuestion the question id that we want to check the type
   */
  checkQuestionType(idQuestion: string) {
    //TODO make it faster
    let type: string;
    for (let index = 0; index < this.sessions.length; index++) {
      const questions = this.sessions[index].questions;
      for (const x in questions) {
        if (questions[x].id == idQuestion) type = questions[x].type;
      }
    }
    return type;
  }
}
</script>
