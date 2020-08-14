<template>
  <div class="about">
    <h1>Tpack XS</h1>
    <Session />
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import Session from '@/components/Session.vue'; // @ is an alias to /src

@Component({
  components: {
    Session,
  },
})
export default class Instrument extends Vue {}
</script>