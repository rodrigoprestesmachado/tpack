import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";
import Instrument from "../views/Instrument.vue";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: "/",
    name: "Instrument",
    component: Instrument
  }
];

const router = new VueRouter({
  routes
});

export default router;
