<template>
    <div>
        <!-- 作者信息 -->
        <div class="author-container">
            <!-- 作者头像 -->
            <img class="author-avatar" :src="'https://static.ttkwsd.top/config/05409c1ed50047535f28a40464f7b1ab.jpg'" />
            <!-- 作者名称 -->
            <!-- <p class="author-name">{{ blog.blogInfo.siteConfig.siteAuthor }}</p> -->
            <p class="author-name">shanmeng</p>
            <!-- 网站简介 -->
            <!-- <div class="site-desc">{{ blog.blogInfo.siteConfig.siteIntro }}</div> -->
            <div class="site-desc">Dream</div>
        </div>
        <!-- 博客信息 -->
        <blog-info></blog-info>
        <!-- 社交 -->
        <social-list></social-list>
    </div>
</template>

<script setup lang="ts">
// import useStore from "@/store";
// const { blog } = useStore();

</script>

<style scoped></style>