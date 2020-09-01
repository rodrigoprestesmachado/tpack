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
          thumb-size="25"
          :step="0.1"
          :max="5"
          :min="1"
          aria-label="classifique"
        />

        <!-- Age question -->
        <v-slider
          v-if="question.type == 'AGE'"
          v-model="answer[question.id]"
          thumb-label="always"
          thumb-size="25"
          :step="1"
          :max="100"
          :min="18"
          aria-label="indique a sua idade"
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

        <!-- Unique choice question -->
        <template v-if="question.type == 'UNIQUE'">
          <v-radio-group
            v-model="answer[question.id]"
            :mandatory="false"
            v-for="choice of question.choices"
            :key="choice.id"
          >
            <v-radio class="mx-5" :value="choice.id" :label="choice.text"></v-radio>
          </v-radio-group>
        </template>

        <!-- Year question -->
        <v-date-picker
          v-if="question.type == 'YEAR'"
          v-model="answer[question.id]"
          type="month"
          show-current="2010-01"
          landscape
          no-title
        ></v-date-picker>

        <!-- Region question-->
        <template v-if="question.type == 'REGION'">
          <v-select
            v-model="state"
            :items="sortStates()"
            item-text="nome"
            item-value="sigla"
            dense
            label="Estado"
            @change="getCities()"
          ></v-select>
          <v-select
            v-if="(state != '') && (loadCities == true)"
            v-model="answer[question.id]"
            :items="sortCities()"
            item-text="nome"
            item-value="nome"
            dense
            label="Município"
          ></v-select>
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
          <v-progress-linear :value="progress"></v-progress-linear>
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
  /** indicates the progress */
  private progress = 0;
  /** stores the states of Brazil */
  private states = [];
  /** stores the state of the user */
  private state = "";
  /** stores the cities of Brazil */
  private cities = [];
  /** Indicates when the cities is loaded  */
  private loadCities = false;

  /**
   * Gets the sessions and questions from the server
   */
  created() {
    this.getStates();
    this.getSessions();
  }

  /**
   * Gets the states from IBGE API
   */
  async getStates() {
    const url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
    try {
      const resp = await axios.get(url);
      this.states = resp.data;
    } catch (error) {
      this.errorMessage = "Não foi possível recuperar os estados do país";
      console.log(this.errorMessage);
    }
  }

  /**
   * Gets the cities of a state from IBGE API
   */
  async getCities() {
    this.loadCities = false;
    const url =
      "https://servicodados.ibge.gov.br/api/v1/localidades/estados/" +
      this.state +
      "/municipios";
    try {
      const resp = await axios.get(url);
      this.cities = resp.data;
      // inform to the interface thar the cities were loaded
      this.loadCities = true;
    } catch (error) {
      this.errorMessage = "Não foi possível recuperar as cidades de um estado";
      console.log(this.errorMessage);
    }
  }

  /**
   * Sort the list of Braziliam states
   */
  sortStates() {
    return this.states.sort((x: any, y: any) => (x.nome > y.nome ? 1 : -1));
  }

  /**
   * Sort the list of Braziliam states
   */
  sortCities() {
    return this.cities.sort((x: any, y: any) => (x.nome > y.nome ? 1 : -1));
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
    this.progress = (this.current * 100) / this.sessions.length;
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
          const strArray = value!.split(",");
          const numberArray = [];
          for (const x in strArray) numberArray.push(Number(strArray[x]));
          this.answer[keys[k]] = numberArray;
        }
      }
    }
    this.loaded = true;
  }

  /**
   * Verifies the type of the question
   *
   * @param idQuestion the question id that we want to check the type
   */
  checkQuestionType(idQuestion: string) {
    //TODO make it faster
    let type = "";
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
