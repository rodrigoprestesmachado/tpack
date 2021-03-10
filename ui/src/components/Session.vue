<template>
  <v-container fluid>
    <v-row no-gutters align="start" justify="center">
      <v-col lg="4" md="5">
        <!-- load feedback -->
        <v-progress-circular
          indeterminate
          justify="center"
          color="primary"
          v-if="loaded === false && errorMessage === ''"
        ></v-progress-circular>

        <!-- error Message -->
        <div align="center" justify="center">{{ errorMessage }}</div>

        <!-- save message -->
        <template v-if="token != null && loaded === true">
          <div
            align="center"
            justify="center"
            class="ma-5 orange--text text-h5"
          >
            Obrigado por você ter respondido essa pesquisa
          </div>
        </template>

        <!-- main container -->
        <template v-if="loaded == true">
          <div class="headline" align="center" justify="center">
            {{ sessions[current].title }}
          </div>

          <v-container
            v-for="question of sessions[current].questions"
            :key="question.id"
          >
            <!-- Label queston  -->
            <template v-if="question.type == 'LABEL'">
              <div
                class="text-justify"
                v-html="question.text"
                align="start"
                justify="center"
              ></div>
            </template>

            <!-- Text area field question   -->
            <template v-if="question.type == 'TEXTAREA'">
              <v-card elevation="2" class="mb-8">
                <v-card-title class="text-subtitle-1 text-justify">
                  <div v-html="question.text"></div>
                  <div class="body-2 mx-4 mt-4" v-html="question.note"></div>
                </v-card-title>
                <v-card-actions>
                  <v-col>
                    <v-textarea
                      v-model="answer[question.id]"
                      clearable
                      name="input-7-4"
                    ></v-textarea>
                  </v-col>
                </v-card-actions>
              </v-card>
            </template>

            <!-- Scale queston  -->
            <template v-if="question.type == 'SCALE'">
              <div
                class="text-body-2 text-justify-center mb-6"
                v-html="question.note"
              ></div>
              <div
                v-html="question.id - 1 + ') ' + question.text"
                class="text-justify"
              ></div>
              <v-slider
                v-model="answer[question.id]"
                thumb-label="always"
                thumb-size="30"
                :step="0.1"
                :max="5"
                :min="1"
                aria-label="classifique"
                class="mt-10 mb-5 mx-3"
                dense
                :tick-labels="ticks"
              />
            </template>

            <!-- Age question -->
            <template v-if="question.type == 'AGE'">
              <v-card elevation="2" class="mb-8">
                <v-card-title class="text-subtitle-1 text-justify">
                  <div v-html="question.id - 1 + ') ' + question.text"></div>
                  <div class="body-2 mx-4 mt-4" v-html="question.note"></div>
                </v-card-title>
                <v-card-actions>
                  <v-col>
                    <v-alert
                      type="error"
                      :value="validation[question.id].alert"
                      dense
                      outlined
                      >Informe sua idade</v-alert
                    >
                    <v-select
                      v-model="answer[question.id]"
                      :items="ages"
                      dense
                      outlined
                      class="ml-8 mr-8"
                    ></v-select>
                  </v-col>
                </v-card-actions>
              </v-card>
            </template>

            <!-- Multiple choice question -->
            <template v-if="question.type == 'MULTIPLE'">
              <v-card elevation="2" class="mb-8">
                <v-card-title class="text-subtitle-1 text-justify">
                  <div v-html="question.id - 1 + ') ' + question.text"></div>
                  <div
                    class="body-2 mx-4 mt-4 text-justify"
                    v-html="question.note"
                  ></div>
                </v-card-title>
                <v-card-actions>
                  <v-col>
                    <v-alert
                      type="error"
                      dense
                      outlined
                      :value="validation[question.id].alert"
                      >Escolha pelo menos uma das opções abaixo</v-alert
                    >
                    <template v-for="choice of question.choices">
                      <v-checkbox
                        v-model="answer[question.id]"
                        :value="choice.id"
                        :label="choice.text"
                        multiple
                        dense
                        :key="choice.id"
                        class="ma-0"
                      ></v-checkbox>
                    </template>
                  </v-col>
                </v-card-actions>
              </v-card>
            </template>

            <!-- Multilevel choice question -->
            <template v-if="question.type == 'MULTILEVEL'">
              <v-card elevation="2" class="mb-8">
                <v-card-title class="text-subtitle-1 text-justify">
                  <div v-html="question.id - 1 + ') ' + question.text"></div>
                  <div class="body-2 mx-4 mt-4" v-html="question.note"></div>
                </v-card-title>
                <v-card-actions>
                  <v-col>
                    <v-alert
                      type="error"
                      dense
                      outlined
                      :value="validation[question.id].alert"
                      >Escolha pelo menos uma das opções abaixo</v-alert
                    >
                    <template v-for="index in question.levels">
                      <v-select
                        v-model="answer[question.id]"
                        :items="
                          question.choices.filter(choice => {
                            if (choice.level === index) return choice;
                          })
                        "
                        item-text="text"
                        item-value="id"
                        multiple
                        dense
                        outlined
                        class="ml-8 mr-8"
                        :key="index"
                        :placeholder="getLabelLevels(index)"
                        :ref="'multilevel' + index"
                        @change="handleLevel"
                      ></v-select>
                    </template>
                  </v-col>
                </v-card-actions>
              </v-card>
            </template>

            <!-- Unique choice question -->
            <template v-if="question.type == 'UNIQUE'">
              <v-card elevation="2" class="mb-8">
                <v-card-title class="text-subtitle-1 text-justify">
                  <div v-html="question.id - 1 + ') ' + question.text"></div>
                  <div class="body-2 mx-4 mt-4" v-html="question.note"></div>
                </v-card-title>
                <v-card-actions>
                  <v-col>
                    <v-alert
                      type="error"
                      dense
                      outlined
                      :value="validation[question.id].alert"
                      >Escolha uma das opções abaixo</v-alert
                    >
                    <v-radio-group v-model="answer[question.id]" dense column>
                      <template v-for="choice of question.choices">
                        <v-radio
                          :value="choice.id"
                          :label="choice.text"
                          :key="choice.id"
                          :disabled="isQuestionDisable(question.id)"
                        ></v-radio>
                      </template>
                    </v-radio-group>
                  </v-col>
                </v-card-actions>
              </v-card>
            </template>

            <!-- Year question -->
            <template v-if="question.type == 'YEAR'">
              <v-card elevation="2" class="mb-8">
                <v-card-title class="text-subtitle-1 text-justify">
                  <div v-html="question.id - 1 + ') ' + question.text"></div>
                  <div class="body-2 mx-4 mt-4" v-html="question.note"></div>
                </v-card-title>
                <v-card-actions>
                  <v-col>
                    <v-alert
                      type="error"
                      :value="validation[question.id].alert"
                      dense
                      outlined
                      >Informe um ano</v-alert
                    >
                    <v-select
                      v-model="answer[question.id]"
                      :items="years"
                      dense
                      outlined
                      class="ml-8 mr-8"
                    ></v-select>
                  </v-col>
                </v-card-actions>
              </v-card>
            </template>

            <!-- Region question-->
            <template v-if="question.type == 'REGION'">
              <v-card elevation="2" class="mb-8">
                <v-card-title class="text-subtitle-1 text-justify">
                  <div v-html="question.id - 1 + ') ' + question.text"></div>
                  <div class="body-2 mx-4 mt-4" v-html="question.note"></div>
                </v-card-title>
                <v-card-actions>
                  <v-col>
                    <v-alert
                      type="error"
                      :value="validation[question.id].alert"
                      dense
                      outlined
                      >Escolha seu estado e cidade</v-alert
                    >
                    <v-select
                      v-model="state"
                      :items="states"
                      item-text="nome"
                      item-value="sigla"
                      label="Estado"
                      dense
                      outlined
                      class="ml-8 mr-8"
                      @change="getCities()"
                    ></v-select>
                    <v-select
                      v-if="state != null && loadCities == true"
                      v-model="answer[question.id]"
                      :items="cities"
                      item-text="nome"
                      item-value="nome"
                      dense
                      outlined
                      label="Município"
                      class="ml-8 mr-8"
                    ></v-select>
                  </v-col>
                </v-card-actions>
              </v-card>
            </template>

            <!-- Button -->
            <template v-if="question.type === 'SAVE' && token === null">
              <v-btn
                align="center"
                justify="center"
                small
                color="teal lighten-2"
                class="ma-10"
                v-on:click.native="save()"
                >Enviar suas respostas</v-btn
              >
            </template>
          </v-container>

          <!-- Next and previus buttons -->
          <v-row no-gutters class="mb-10">
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
              Necessitamos que você responda todas as questões obrigatórias para
              que possamos completar essa pesquisa
              <v-btn color="indigo" text @click="openSnackbar = false"
                >Fechar</v-btn
              >
            </v-snackbar>
          </v-row>
        </template>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import axios from "axios";
import { Component, Vue } from "vue-property-decorator";
// TODO remove the comments
// To use with Electron
// https://medium.com/@bromix/electron-application-with-vue-js-and-vuetify-f2a1f9c749b8
import installExtension from "electron-devtools-installer";
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
  private readonly BASE = "http://:/";
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
  private state: any = null;
  /** stores the selected cities */
  private cities = [];
  /** indicates when the cities is loaded  */
  private loadCities = false;
  /** stores if a user answered a question or not */
  private validation: Array<any> = [];
  /** stores the array of ages */
  private ages: Array<string> = [];
  /** the default color of navigation buttons */
  private navigationColor = "#80CBC4";
  /** sets the snack bar to false (or closed) */
  private openSnackbar = false;
  /** sores the token/id of a user */
  private token: any = null;
  /** stores the array of years  */
  private years: Array<string> = [];
  /** the labels of the sliders   */
  private ticks: Array<string> = [];

  /**
   * Tpack spefic rule
   * This property is because 5 is related with question 4
   */
  private disableQuestion5 = true;

  /**
   * When the component is created (Vue created) then init
   */
  created() {
    this.initLabels();
    this.initYears();
    this.initAges();
    this.initApplication();
  }

  /**
   * Initi the labels of the sliders
   */
  initLabels() {
    for (let index = 1; index <= 41; index++) {
      switch (index) {
        case 1:
          this.ticks.push("1");
          break;
        case 41:
          this.ticks.push("5");
          break;
        default:
          this.ticks.push("");
          break;
      }
    }
  }

  /** initalize the years array  */
  initYears() {
    const current = new Date();
    for (let index = 0; index < 61; index++) {
      this.years.push(String(current.getFullYear() - index));
    }
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
    // TODO (use router) The title of session component
    document.title = "tpack";

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
      this.loaded = false;
      this.errorMessage = "Sistema indisponível";
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
      this.loaded = false;
      this.scrollTop();
      // Sets error message
      this.errorMessage =
        "Problemas com a rede: não foi possível carregar os estados";
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
        this.loaded = false;
        this.scrollTop();
        this.errorMessage =
          "Problema com a rede: as cidades não foram carregadas";
        console.log(this.errorMessage);
      }
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
      this.navigationColor = "#80CBC4";
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
      // scroll to top
      this.scrollTop();
    } else {
      // open the snack bar and change the color of the navigation
      //buttons
      this.openSnackbar = true;
      this.navigationColor = "#FF8A65";
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

        // checks if the question needs to be validated
        if (this.validation[idQuestion].required) {
          // checks if the user answered the question
          if (
            localStorage.getItem(idQuestion) == null ||
            this.answer[idQuestion].length === 0
          ) {
            // do not allow to change the session (next session)
            go = false;
            this.validation[idQuestion].alert = true;
          } else {
            this.validation[idQuestion].alert = false;
          }
        } else {
          console.log("DO NOT validade: " + idQuestion);
        }
      }
      // TODO: remove in the future - force the update to show the erros to the users
      //this.$forceUpdate();
    }
    return go;
  }

  /**
   * Verifies if a question was answered, if not, sets to the state to true
   */
  initNotNullValidation() {
    const currentQuestions = this.sessions[this.current].questions;
    for (const index in currentQuestions) {
      const question = currentQuestions[index];
      // true means the question needs to be validated
      if (this.validation[question.id] === undefined) {
        this.validation[question.id] = { required: true, alert: false };
      }
    }
    this.scapeValidationList();
  }

  /**
   * // TODO this information should be in the data base in the next version.
   * it is necessary a refactor of the validation and answer systens
   *
   * Indicate questions to scapes of not null validation
   * false means: scape validation
   */
  scapeValidationList() {
    this.validation[1] = { required: false, alert: false };
    this.validation[43] = { required: false, alert: false };
    this.validation[44] = { required: false, alert: false };
    this.validation[45] = { required: false, alert: false };

    if (localStorage.getItem("5") == null) {
      this.validation[5] = { required: false, alert: false };
    } else {
      this.disableQuestion5 = false;
      this.validation[5] = { required: true, alert: false };
    }
  }

  /***
   * Saves the data from answer array in local storage
   */
  saveInLocalStorage() {
    for (const key in this.answer) {
      const value = this.answer[key];
      // avoid to store null values in localStorage
      if (value != null) {
        localStorage.setItem(key, value);
      }
    }
    // TODO save the selected state for region component
    localStorage.setItem("state", this.state);
  }

  /**
   * Loads the previous data from local storage
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
        if (questionType === "MULTIPLE" || questionType === "MULTILEVEL") {
          // When the answer is multiple or multilevel we need to create a array of numbers
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
      this.token =
        localStorage.getItem("token") != null
          ? localStorage.getItem("token")
          : null;

      // gets the selected state
      this.state =
        localStorage.getItem("state") != null
          ? localStorage.getItem("state")
          : null;
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
    // save the data of the latest session
    this.saveInLocalStorage();

    interface DataMap {
      [key: string]: any;
    }
    const data: DataMap = {};
    // Creates a JS object from local storage
    const keys = Object.keys(localStorage);
    keys.forEach(function(element) {
      data[element] = localStorage.getItem(element);
    });

    // Add the state in the question about the city
    data["42"] = data["42"] + ", " + this.state;

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
        this.loaded = false;
        this.scrollTop();
        // error message
        this.errorMessage =
          "Problemas com a rede: não foi possível salvar as respostas";
        console.log(this.errorMessage);
      }
    } else {
      console.log("data already saved");
    }
  }

  /**
   * Tpack spefic rule:
   * Answers if some question need to be disable
   */
  isQuestionDisable(idQuestion: number) {
    if (idQuestion == 5) {
      return this.disableQuestion5;
    }
  }

  /**
   * Tpack spefic rule:
   * Handles the levels of multilevel question about formation.
   */
  handleLevel(arraySelected: Array<number>) {
    if (arraySelected.find(e => e > 12)) {
      // enable question 5
      this.disableQuestion5 = false;
      this.validation[5].required = true;
    } else {
      // disable question 5
      this.disableQuestion5 = true;
      this.validation[5].required = false;

      // clear localstorage
      localStorage.removeItem("5");

      this.answer[5] = null;
    }
  }

  getLabelLevels(index: number) {
    let label;
    if (index - 1 == 0) {
      label = "Fundamental e médio";
    } else if (index - 1 == 1) {
      label = "Superior";
    } else {
      label = "Pós-graduação";
    }
    return label;
  }

  /**
   * Puts the scroll to the top
   */
  scrollTop() {
    // Scroll to the top
    window.scrollTo(0, 0);
  }
}
</script>

<style scoped>
/*
there is a open bug in vuetify about v-card word break
https://github.com/vuetifyjs/vuetify/issues/9130
thus, this is a work around
*/
.v-card__text,
.v-card__title {
  word-break: normal; /* maybe !important  */
}
</style>