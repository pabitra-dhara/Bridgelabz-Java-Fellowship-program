import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      "/bookstore_book": {
        target: "http://localhost:8080",
        changeOrigin: true,
      },
      "/bookstore_user": {
        target: "http://localhost:8080",
        changeOrigin: true,
      },
    },
  },
});
