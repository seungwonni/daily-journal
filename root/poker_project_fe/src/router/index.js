import {createRouter, createWebHistory} from "vue-router";
import HelloWorld from "@/components/views/home/HomeView";
import SetPlayer from "@/components/views/poker/SetPlayer";
import SetCard from "@/components/views/poker/SetCard";


const routes = [
    {
        path : "/",
        name : "home",
        component: HelloWorld
    },
    {
        path : "/about",
        name : "about",
        component: () => import(/* webpackChunkName: "about" */ '../components/views/AboutView.vue')
    },
    {
        path : "/poker/set-player",
        name : "pokerSetPlayer",
        component: SetPlayer
    },
    {
        path : "/poker/set-card/:playerNum",
        name : "pokerSetCard",
        component: SetCard,
        props : true
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router;