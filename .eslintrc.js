module.exports = {
  "parserOptions": {
    "ecmaVersion": 6,
    "sourceType": "module"
  },
  "plugins": [
    "import"
  ],
  rules: {
    // Các quy tắc khác
    "vue/multi-word-component-names": "off",
    "no-unused-vars": "off",
    "import/no-duplicates": "error", // Không import trùng lặp
    "no-inner-declarations": "off" // Tắt thông báo lỗi về hàm/biến trong khối
  },
};
