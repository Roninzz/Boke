<script setup lang="ts">
onMounted(() => {
  console.log("父挂载")
})
</script>

<template>
  <Provider>
    <div class="app-wrapper">
      <Header></Header>
      <main class="main-wrapper">
        <router-view></router-view>
      </main>
      <Footer></Footer>
      <Login></Login>
    </div>
  </Provider>
</template>

<style scoped>
.app-wrapper {
  position: relative;
  min-height: 100vh;

}

.main-wrapper {
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 0 0 8rem;
}
</style>
