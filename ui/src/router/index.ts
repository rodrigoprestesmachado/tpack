import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";
import Instrument from "../views/Instrument.vue";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    component: Instrument,
    path: "/",
    name: "Instrument",
    meta: {
      title: 'Tpack',
      metaTags: [
        {
          name: 'description',
          content: 'Tpack xs application'
        }
      ]
    }
  }
];

const router = new VueRouter({
  routes
});

export default router;
