<!-- 登录页面 -->

<template>
    <n-modal class="bg" v-model:show="app.loginFlag">
        <!-- <n-input class="mt-11" placeholder="邮箱号"></n-input>
        <n-input class="mt-11" placeholder="密码"></n-input>
        <n-button class="mt-11"> 登录 </n-button>
        <div class="mt-10 login-tip">
            <span class="colorFlag" @click="handleRegister">立即注册</span>
            <span class="colorFlag" @click="handleForget">忘记密码?</span>
        </div> -->

        <n-card style="width: 600px" title="Modal" :bordered="false" size="huge" role="dialog" aria-modal="true">
            <template #header-extra>
                Oops!
            </template>
            Content
            <template #footer>
                Footer
            </template>
        </n-card>
    </n-modal>
</template>

<script lang="ts" setup>
import { NInput, NModal, NButton } from "naive-ui";
import useStore from '@/store'

const { app, user, blog } = useStore()

const dialogVisible = computed({
    get: () => app.loginFlag,
    set: (value) => (app.loginFlag = value),
})
</script>

<style scoped></style>