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
        <template v-if="question.type == 'AGE'">
          <v-alert type="error" :value="validation[question.id]" dense outlined>Informe sua idade</v-alert>
          <v-select v-model="answer[question.id]" value="30" :items="ages" outlined></v-select>
        </template>

        <!-- Multiple choice question -->
        <template v-if="question.type == 'MULTIPLE'">
          <v-alert
            type="error"
            dense
            outlined
            :value="validation[question.id]"
          >Escolha pelo menos uma das opções abaixo</v-alert>
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
          <v-alert
            type="error"
            dense
            outlined
            :value="validation[question.id]"
          >Escolha uma das opções abaixo</v-alert>
          <v-radio-group
            v-model="answer[question.id]"
            v-for="choice of question.choices"
            :key="choice.id"
          >
            <v-radio class="mx-5" :value="choice.id" :label="choice.text"></v-radio>
          </v-radio-group>
        </template>

        <!-- Year question -->
        <template v-if="question.type == 'YEAR'">
          <v-alert type="error" :value="validation[question.id]" dense outlined>Informe uma data</v-alert>
          <v-date-picker
            v-model="answer[question.id]"
            type="month"
            show-current="2010-01"
            landscape
            no-title
          ></v-date-picker>
        </template>

        <!-- Region question-->
        <template v-if="question.type == 'REGION'">
          <v-alert
            type="error"
            :value="validation[question.id]"
            dense
            outlined
          >Escolha o estado e cidade</v-alert>
          <v-select
            v-model="state"
            :items="states"
            item-text="nome"
            item-value="sigla"
            dense
            label="Estado"
            @change="getCities()"
            outlined
          ></v-select>
          <v-select
            v-if="(state != '') && (loadCities == true)"
            v-model="answer[question.id]"
            :items="cities"
            item-text="nome"
            item-value="nome"
            dense
            label="Município"
            outlined
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
            :color="navigationColor"
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
            :color="navigationColor"
            v-on:click="nextSession(1)"
            aria-label="próximas questões"
          >
            <v-icon dark>mdi-arrow-right</v-icon>
          </v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-snackbar v-model="openSnackbar">
          Para que possamos completar a pesquisa, necessitamos que você
          responda todas as questões.
          <v-btn color="indigo" text @click="openSnackbar = false">Fechar</v-btn>
        </v-snackbar>
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
  /** sets base URL */
  private readonly BASE = "http://localhost:9080/service/api/";
  /** ttores the answer of the users to only sync with the interface */
  private answer: any = [];
  /** the session with the questions */
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
  /** stores the selected state */
  private state = "";
  /** stores the selected cities */
  private cities = [];
  /** indicates when the cities is loaded  */
  private loadCities = false;
  /** stores if a user answered a question or not */
  private validation: Array<boolean> = [];
  /** stores the array of ages */
  private ages: Array<number> = [];
  /** the default color of navigation buttons */
  private navigationColor = "#D3EAE1";
  /** sets the snack bar to false (or closed) */
  private openSnackbar = false;

  /**
   * When the component is created (Vue created) then init
   */
  created() {
    this.initAges();
    this.getStates();
    this.getSessions();
  }

  /** initalize the ages array  */
  initAges() {
    for (let age = 18; age <= 100; age++) {
      this.ages.push(age);
    }
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
      this.initNotNullValidation();
      this.loaded = true;
    } catch (error) {
      this.errorMessage = "Serviço indisponível no momento";
      console.log(this.errorMessage);
    }
  }

  /**
   * Gets the states from IBGE API
   */
  async getStates() {
    const url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
    try {
      const resp = await axios.get(url);
      this.states = resp.data;
      this.states.sort((x: any, y: any) => (x.nome > y.nome ? 1 : -1));
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
      // Sort the cities
      this.cities.sort((x: any, y: any) => (x.nome > y.nome ? 1 : -1));
      // inform to the interface thar the cities were loaded
      this.loadCities = true;
    } catch (error) {
      this.errorMessage = "Não foi possível recuperar as cidades de um estado";
      console.log(this.errorMessage);
    }
  }

  /**
   * Verifies if a question was answered, if not sets to the state to false
   */
  initNotNullValidation() {
    const currentQuestions = this.sessions[this.current].questions;
    for (const index in currentQuestions) {
      const question = currentQuestions[index];
      this.validation[question.id] === undefined
        ? (this.validation[question.id] = false)
        : "";
    }
  }

  /**
   * Controls the changes of the sessions
   *
   * @param next the number used to move to the next session. For example,
   * next=1 move to the next session and next=-1 moves to previus session
   */
  async nextSession(next: number) {
    // saves in browser local storage
    this.saveInLocalStorage();
    // Cheks empty answer
    const go = await this.notNullValidation();
    if (go) {
      this.navigationColor = "#D3EAE1";
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
      // move to the next session
      this.current = this.current + next;
      // calculate to update the progress bar
      this.progress = (this.current * 100) / this.sessions.length;
      // init not null validation to the next session
      this.initNotNullValidation();
    } else {
      // open the snack bar and change the color of the navigation
      //buttons
      this.openSnackbar = true;
      this.navigationColor = "#FA6F37";
    }
  }

  /**
   * Checks if the answer is empty or not
   */
  notNullValidation() {
    let go = true;
    const session = this.sessions[this.current];
    const questions = session.questions;

    for (const x in questions) {
      const idQuestion = questions[x].id;

      // checks if the user answered the question
      if (
        localStorage.getItem(idQuestion) == null ||
        this.answer[idQuestion].length === 0
      ) {
        // do not allow to change the session
        go = false;
        this.validation[idQuestion] = true;
      } else {
        this.validation[idQuestion] = false;
      }
    }
    // show the errors the erros to the users
    this.$forceUpdate();
    return go;
  }

  /***
   * Saves the data from answer array in local storage
   */
  saveInLocalStorage() {
    for (const key in this.answer) {
      const value = this.answer[key];
      localStorage.setItem(key, value);
    }
  }

  /**
   * Loads the previus data from local storage
   */
  loadFromLocalStorage() {
    if (localStorage.length !== 0) {
      // gets all keys from local storage
      const keys = Object.keys(localStorage);
      let k = keys.length;
      while (k--) {
        // gets the value from local storage
        const value = localStorage.getItem(keys[k]);
        // gets the type of question
        const questionType = this.checkQuestionType(keys[k]);
        if (questionType === "MULTIPLE") {
          // When the answer is multiple we need to create a array of numbers
          // from local storage to Vuetify render the interface
          const strArray = value!.split(",");
          const numberArray = [];
          for (const x in strArray) numberArray.push(Number(strArray[x]));
          this.answer[keys[k]] = numberArray;
        } else if (questionType === "UNIQUE") {
          this.answer[keys[k]] = Number(value);
        } else {
          this.answer[keys[k]] = value;
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
