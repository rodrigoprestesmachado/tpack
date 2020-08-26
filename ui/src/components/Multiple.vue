<template>
  <div>{{question}}</div>
</template>    
<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";

@Component
export default class Multiple extends Vue {
  @Prop() private data!: any;
}
</script>
