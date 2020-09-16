<template>
  <v-container>
    <!--
      load feedback
    -->
    <v-row justify="center">
      <v-progress-circular
        indeterminate
        color="primary"
        v-if="(loaded === false) && (errorMessage === '')"
      ></v-progress-circular>
    </v-row>

    <!-- error Message -->
    <v-row align="center" justify="center">
      <template>{{errorMessage}}</template>
    </v-row>

    <!-- save message -->
    <template v-if="(token !== null) && (loaded === true)">
      <v-row align="center" justify="center">Obrigado por ter respondido essa pesquisa</v-row>
    </template>

    <!-- main container -->
    <template v-if="loaded">
      <v-row justify="center">{{errorMessage}}</v-row>
      <v-row align="center" justify="center">
        <div class="headline">{{ sessions[current].title }}</div>
      </v-row>
      <v-container v-for="(question) of sessions[current].questions" :key="question.id">
        <v-row class="ma-8" align="center" justify="center">
          <div>{{ question.text }}</div>
        </v-row>

        <!-- Scale queston  -->
        <template v-if="question.type == 'SCALE'">
          <v-row class="ma-8">
            <v-slider
              v-model="answer[question.id]"
              thumb-label="always"
              thumb-size="30"
              :step="0.1"
              :max="5"
              :min="1"
              aria-label="classifique"
            />
          </v-row>
        </template>

        <!-- Age question -->
        <template v-if="question.type == 'AGE'">
          <v-row align="center" justify="center">
            <v-alert type="error" :value="validation[question.id]" dense outlined>Informe sua idade</v-alert>
          </v-row>
          <v-row>
            <v-select v-model="answer[question.id]" :items="ages" dense outlined></v-select>
          </v-row>
        </template>

        <!-- Multiple choice question -->
        <template v-if="question.type == 'MULTIPLE'">
          <v-row align="center" justify="center">
            <v-alert
              type="error"
              dense
              outlined
              :value="validation[question.id]"
            >Escolha pelo menos uma das opções abaixo</v-alert>
          </v-row>
          <v-row class="ml-5">
            <v-col v-for="choice of question.choices" :key="choice.id">
              <v-checkbox
                v-model="answer[question.id]"
                :value="choice.id"
                :label="choice.text"
                multiple
              ></v-checkbox>
            </v-col>
          </v-row>
        </template>

        <!-- Unique choice question -->
        <template v-if="question.type == 'UNIQUE'">
          <v-row align="center" justify="center">
            <v-alert
              type="error"
              dense
              outlined
              :value="validation[question.id]"
            >Escolha uma das opções abaixo</v-alert>
          </v-row>
          <v-row class="ml-5">
            <v-radio-group
              v-model="answer[question.id]"
              v-for="choice of question.choices"
              :key="choice.id"
            >
              <v-col>
                <v-radio :value="choice.id" :label="choice.text"></v-radio>
              </v-col>
            </v-radio-group>
          </v-row>
        </template>

        <!-- Year question -->
        <template v-if="question.type == 'YEAR'">
          <v-row align="center" justify="center">
            <v-alert type="error" :value="validation[question.id]" dense outlined>Informe uma data</v-alert>
          </v-row>
          <v-row align="center" justify="center">
            <v-date-picker
              v-model="answer[question.id]"
              type="month"
              show-current="2010-01"
              landscape
              no-title
              class="ml-8"
            ></v-date-picker>
          </v-row>
        </template>

        <!-- Region question-->
        <template v-if="question.type == 'REGION'">
          <v-row align="center" justify="center">
            <v-alert
              type="error"
              :value="validation[question.id]"
              dense
              outlined
            >Escolha seu estado e cidade</v-alert>
          </v-row>
          <v-row>
            <v-select
              v-model="state"
              :items="states"
              item-text="nome"
              item-value="sigla"
              label="Estado"
              dense
              outlined
              class="ml-8"
              @change="getCities()"
            ></v-select>
            <v-select
              v-if="(state != null) && (loadCities == true)"
              v-model="answer[question.id]"
              :items="cities"
              item-text="nome"
              item-value="nome"
              dense
              label="Município"
              outlined
            ></v-select>
          </v-row>
        </template>

        <!-- Save button -->
        <template v-if="(question.type === 'SAVE') && (token === null)">
          <v-row align="center" justify="center">
            <v-btn small color="primary" v-on:click.native="save()">{{question.text}}</v-btn>
          </v-row>
        </template>
      </v-container>

      <!-- Next and previus buttons -->
      <v-row no-gutters class="mu-16">
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

      <!-- Alert snack bar -->
      <v-row>
        <v-snackbar v-model="openSnackbar">
          Necessitamos que você responda todas as questões
          Para que possamos completar a pesquisa, .
          <v-btn color="indigo" text @click="openSnackbar = false">Fechar</v-btn>
        </v-snackbar>
      </v-row>
    </template>
  </v-container>
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
  private state = null;
  /** stores the selected cities */
  private cities = [];
  /** indicates when the cities is loaded  */
  private loadCities = false;
  /** stores if a user answered a question or not */
  private validation: Array<boolean> = [];
  /** stores the array of ages */
  private ages: Array<string> = [];
  /** the default color of navigation buttons */
  private navigationColor = "#D3EAE1";
  /** sets the snack bar to false (or closed) */
  private openSnackbar = false;
  /** sores the token/id of a user */
  private token = null;

  /**
   * When the component is created (Vue created) then init
   */
  created() {
    this.initAges();
    this.initApplication();
  }

  /** initalize the ages array  */
  initAges() {
    for (let age = 18; age <= 100; age++) {
      this.ages.push(age.toString());
    }
  }

  /**
   * Inits the application
   *
   * @param base The URL base of the service
   */
  async initApplication() {
    const url = this.BASE + "getSessions";
    try {
      const resp = await axios.get(url);
      this.sessions = resp.data;
      this.loadFromLocalStorage();
      this.initNotNullValidation();
      this.getStates();
      this.getCities();
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
    if (this.state != null) {
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
        this.errorMessage =
          "Não foi possível recuperar as cidades de um estado";
        console.log(this.errorMessage);
      }
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
      this.progress = ((this.current + 1) * 100) / this.sessions.length;
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

    // if token ins't empty so we can stop the not null validation
    if (this.token === null) {
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
      // force the update to show the erros to the users
      this.$forceUpdate();
    }

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
    // save the selected state for region component
    localStorage.setItem("state", this.state);
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
      // gets the user token if it exists
      this.token = localStorage.getItem("token");

      // gets the selected state
      this.state = localStorage.getItem("state");
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

  /**
   * Saves the answers in the service
   */
  async save() {
    const data = {};
    // Creates a JS object from local storage
    const keys = Object.keys(localStorage);
    const result = keys.forEach(function(element, index, keys) {
      data[element] = localStorage.getItem(element);
    });
    // verifies if the user already send an answer
    if (!localStorage.getItem("token")) {
      // converts from JS object fo string
      const answers = JSON.stringify(data);
      try {
        // sends the data to the service
        const url = this.BASE + "save";
        const resp = await axios.post(url, answers, {
          headers: {
            "Content-Type": "application/json"
          }
        });
        // stores the user token
        this.token = resp.data.token;
        localStorage.setItem("token", this.token);
      } catch (error) {
        this.errorMessage = "Não foi possível salvar os dados no serviço";
        console.log(this.errorMessage);
      }
    } else {
      console.log("data already saved");
    }
  }
}
</script>
