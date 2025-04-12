/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
	"../templates/*.html",
	"./src/main/resources/static/**/*.{html,js}",
  ],
  safelist: [
    '-translate-x-full',
    // 他に動的に追加・削除する可能性のあるクラス
  ],
  theme: {
    extend: {},
  },
  plugins: [],
}

