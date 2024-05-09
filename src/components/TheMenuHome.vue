<template>
    <div style="display: flex; justify-content: center; margin: 20px 0;">
       
        <button class="btn btn-login">
            <router-link :to="{ name: 'login' }" class="router-link-hover">
                <i class="fa-solid fa-right-to-bracket"></i>
                <span>
                    Đăng nhập
                </span>
            </router-link>
        </button>
    </div>
</template>

<script>
    import { storeToRefs } from "pinia";
    import { useMenu } from "../stores/use-menu.js";
    import { defineComponent } from 'vue';
    export default defineComponent({

        setup() {
            const store = useMenu();
            return {
                ...storeToRefs(store),
            };
        },
    });
</script>