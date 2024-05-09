import { createStore } from 'vuex';
import cookie from 'vue-cookies';
import createPersistedState from 'vuex-persistedstate';


const state = {
    isAuth: false,
    user: ""
};

const getters = {
    isAuth: state => state.isAuth,
    getUser: state => state.user
};

const mutations = {
    setUser(state, user) {
        state.user = user;
    },
    setAuth(state, isAuth) {
        state.isAuth = isAuth;
    }
};

const actions = {
    login({ commit }, user) {
        commit('setUser', user);
        commit('setAuth',true);
    },
    logout({ commit }) {
        commit('setUser', null);
        commit('setAuth', false);
        cookie.remove('token');
        cookie.remove('user');
    },
};

const store = new createStore({
    state,
    mutations,
    getters,
    actions,
    plugins: [createPersistedState()],
});

export default store;