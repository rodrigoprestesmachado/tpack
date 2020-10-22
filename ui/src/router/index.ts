import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";
import Instrument from "../views/Instrument.vue";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    component: Instrument,
    name: "Instrument",
    path: "/",
    meta: {
      title: 'Tpack',
      metaTags: [
        {
          auth: true,
          name: 'description',
          content: 'Tpack Application'
        }
      ]
    }
  }
];

const router = new VueRouter({
  routes
});

export default router;
