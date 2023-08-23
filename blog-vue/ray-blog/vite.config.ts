import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from "path";
import AutoImport from "unplugin-auto-import/vite"
import { NaiveUiResolver } from "unplugin-vue-components/resolvers"
import Components from "unplugin-vue-components/vite"
import { createSvgIconsPlugin } from "vite-plugin-svg-icons"
import { prismjsPlugin} from "vite-plugin-prismjs"

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    AutoImport({
      //import时自动引入安装的组件的路径，避免遗忘import中的路径问题
      imports: ["vue", "vue-router", "pinia"],
      dts: "src/types/auto-imports.d.ts"
    }),
    Components({
      //import时自动引入自定义组件的路径，避免遗忘import中的路径问题
      resolvers: [NaiveUiResolver()],
      dts: "src/types/components.d.ts",
    }),
    createSvgIconsPlugin({
      //指定需要缓存的图标文件夹
      //path.resolve(from,to)
      //process.cwd()是当前运行文件的文件夹地址，因为此文件在根目录下，所以就是从根目录下开始找
      iconDirs: [path.resolve(process.cwd(), "src/assets/icons")],
      // 指定symbolId格式
      symbolId:"icon-[dir]-[name]",
    }),
    prismjsPlugin({
      //网页代码高亮，配置语言和主题样式
      languages: ["java", "python", "html", "css", "sass", "less", "go", "cpp", "c", "js", "ts", "sql", "bash", "git", "nginx", "php",],
      theme: "tomorrow",
      css:true
    })
  ],
  resolve: {
    alias: {
      // 设置路径
      "~": path.resolve(__dirname, "./"),
      // 设置别名
      "@": path.resolve(__dirname, "./src"),
    },
    //识别的后缀名
    extensions: [".mjs", ".js", ".ts", ".jsx", ".tsx", ".json", ".vue"]
  },
  server: {
    //配置代理
    open: true,
    proxy: {
      "/api": {
        target: "http://127.0.0.1:7777",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ""),
      },
    }
  }
})
