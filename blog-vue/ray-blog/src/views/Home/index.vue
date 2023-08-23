<!-- 主页 -->
<script setup lang="ts">
import Recommend from './Swiper/Recommend.vue';
import Iamges from './Swiper/Images.vue';
import Brand from './Brand/index.vue';
import ArticleItem from '@/views/Article/ArticleItem.vue'


</script>

<template>
    <!-- 背景轮播 -->
    <Iamges></Iamges>
    <!-- 网页名称 -->
    <Brand></Brand>
    <div class="bg">
        <div class="main-container mt">
            <div class="left-container">
                <!-- 说说 -->
                <!-- 推荐文章 -->
                <Recommend></Recommend>
                <!-- 文章列表 -->
                <ArticleItem></ArticleItem>
            </div>
            <!-- 右侧组件 -->
        </div>
    </div>
</template>


<style lang="scss" scoped>
.mt {
    margin-top: 1rem;
    padding-bottom: 1.75rem;
}
</style>