<!-- 页脚 -->

<template>
    <footer class="footer-wrapper">
        <p>
            © 2022 - 2023 By ShanMeng
        </p>
        <a href="https://beian.miit.gov.cn/" target="_blank">赣ICP备2022007910号</a>
    </footer>
</template>

<style lang="ts" setup>

</style>

<style lang="scss" scoped>
.footer-wrapper {
    position: absolute;
    left: 0;
    right: 0;
    bottom: 0;
    width: 100%;
    height: 8rem;
    padding: 2.5rem 0;
    font-size: 0.875rem;
    text-align: center;
    color: var(--grey-0);
    background: var(--footer-bg);
    background-size: 300% 300%;
    -webkit-animation: gradientBG 10s ease infinite;
    animation: gradientBG 10s ease infinite;
}

@keyframes gradientBG {
    0% {
        background-position: 0 50%;
    }

    50% {
        background-position: 100% 50%;
    }

    100% {
        background-position: 0 50%;
    }
}
</style>