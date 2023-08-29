import "@/assets/fonts/font.css";
import "@/assets/styles/index.scss";
import SvgIcon from "@/components/SvgIcon/index.vue"
import * as directive from "@/directive"
import "nprogress/nprogress.css";
import naive from "naive-ui";
import piniaPluginPersistedstate from "pinia-plugin-persistedstate";
import "virtual:svg-icons-register";
import Prism from "prismjs";
import "swiper/css";
import "swiper/css/autoplay";
import "swiper/css/mousewheel";
import "swiper/css/navigation";
import "swiper/css/pagination";
import createKatexPlugin from "@kangc/v-md-editor/lib/plugins/katex/cdn";
import createTodoListPlugin from "@kangc/v-md-editor/lib/plugins/todo-list/index";
import "@kangc/v-md-editor/lib/plugins/todo-list/todo-list.css";
import VMdPreview from "@kangc/v-md-editor/lib/preview";
import "@kangc/v-md-editor/lib/theme/style/vuepress.css";
import vuepressTheme from "@kangc/v-md-editor/lib/theme/vuepress.js";

import lazyPlugin from "vue3-lazy";
import { createApp, Directive } from 'vue'
import App from './App.vue'
import router from './router'
import { createPinia } from 'pinia'
import loading from "./assets/images/loading.gif";
import error from "./assets/images/404.gif";


const pinia = createPinia();
pinia.use(piniaPluginPersistedstate);

VMdPreview.use(vuepressTheme, { Prism }).use(createTodoListPlugin()).use(createKatexPlugin())
const app = createApp(App)
Object.keys(directive).forEach((key) => {
    app.directive(key, (directive as { [key: string]: Directive })[key]);
});


app.use(VMdPreview);
app.use(naive)
app.use(pinia)//状态管理  store
app.use(router) //路由管理  router
app.use(lazyPlugin, {
    loading,
    error,
});
app.component("svg-icon", SvgIcon)//全局注册svgicon
app.mount('#app')//mount设置挂载点  #app  id为app的盒子
