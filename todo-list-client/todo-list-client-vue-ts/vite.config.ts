import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { visualizer } from 'rollup-plugin-visualizer'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],

  build: {
    rollupOptions: {
      plugins: [
        // 打包体积分析工具
        visualizer(),
      ],
      output: {
        manualChunks: {
          // 第三方依赖单独打包
          verdor: ['vue', 'axios', 'element-plus', '@element-plus/icons-vue'],
        },
      },
    },
  },
  server: {
    proxy: {
      '/api': {
        target: 'http://127.0.0.1:8080/api',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ''),
      },
    },
  },
})
