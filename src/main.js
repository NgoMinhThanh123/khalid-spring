import { createApp } from "vue";
import App from "./App.vue";
import { createPinia } from "pinia";
import router from "./routers/index.js";
import store from "./stores/myStore";
import { initializeApp } from "firebase/app";
import { fetchFirebaseConfig } from "./services/firebase.js";
import { getAuth } from 'firebase/auth';
import { getFirestore } from 'firebase/firestore';
// import { auth, db } from "./services/firebase.js";
// import reducer from '@/reducers/myUseReducer.js'
// import { provideUserContext } from '../reducers/UseContext.js'

import axios from "axios";
window.axios = axios;

import { Menu, List, Drawer, Button, message } from "ant-design-vue";
import "@/assets/css/style.css";

import "./static/fontawesome-free-6.4.2-web/css/all.min.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/css/bootstrap-utilities.min.css";
import "bootstrap/dist/js/bootstrap.bundle.js";

const firebaseConfig = await fetchFirebaseConfig();
const appFirebase = initializeApp(firebaseConfig);

export const auth = getAuth(appFirebase);
export const db = getFirestore(appFirebase);

const app = createApp(App);
const pinia = createPinia();
// const provide = provideUserContext();
app.use(pinia);
app.use(router);
app.use(store);
app.use(Menu);
app.use(List);
app.use(Button);
app.use(Drawer);
app.mount("#app");