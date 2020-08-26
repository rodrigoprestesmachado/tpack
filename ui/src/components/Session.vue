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
          :value="answer[question.id]"
          thumb-label="always"
          @change="handleScale(question.id, $event)"
          :step="0.1"
          :max="5"
          :min="1"
          aria-label="classifique"
        />

        <!-- Multiple choice question -->
        <template v-if="question.type == 'MULTIPLE'">
          <v-item-group v-for="choice of question.choices" :key="choice.id">
            <v-item cols="12" v-slot:default="{ active }">
              <v-card
                class="ma-3"
                elevation="5"
                :color="active ? '#8DFF94' : ''"
                height="50"
                @click="handleMultiple(question.id, choice.id)"
              >{{choice.text}}</v-card>
            </v-item>
          </v-item-group>
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
  /** kust controls the load from server  */
  private loaded = false;
  /** Indicates an app error */
  private errorMessage = "";

  mounted() {
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
      this.loadFromLocalStorage();
      this.loaded = true;
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

  /**
   * Registers in local storage the answer of the user for slider component
   *
   * @param idQuestion The identificator of the question
   * @param response  The answer of the user
   */
  handleScale(idQuestion: string, response: string) {
    // this.answer only controls the user's interface
    this.answer[idQuestion] = response;
    // store the answers in the local storage
    localStorage.setItem(idQuestion, response);
  }

  /**
   * Registers in local storage the answer of the user for multiple complenent
   *
   * @param idQuestion The identificator of the question
   * @param idChoice   The identificator of the choice (option)
   */
  handleMultiple(idQuestion: string, idChoice: string) {
    const strJson = localStorage.getItem(idQuestion);
    if (strJson === null) {
      // Creates an entry in the local storage
      const choices: string[] = [];
      choices.push(idChoice);
      localStorage.setItem(idQuestion, JSON.stringify(choices));
    } else {
      // Updates the local storage
      const answers = JSON.parse(strJson);
      const index = answers.indexOf(idChoice);
      // -1 insert otherwise remove
      index === -1 ? answers.push(idChoice) : answers.splice(index, 1);
      localStorage.setItem(idQuestion, JSON.stringify(answers));
    }
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
