# Hướng dẫn cài đặt hệ thống University Backend
## Bước 1. Giải nén project
## Bước 2. Sử dụng một trong hai IDE là IntelliJ hoặc Eclipse
## Bước 3. Sử dụng MySQL để tạo cơ sở dữ liệu.
```
-Mở MySQL, tạo 1 database rỗng có tên university
```
```
-Trên Toolbar, chọn Server -> Data import -> Chọn Import from Self-Contained File -> Chọn file university.sql đã đính kèm trong project
```
```
-Ở Default Schema to be Imported To, chọn database vừa tạo là university.
```
```
-Nhấn Start Import
```
## Bước 4. Mở project UniversitySB bằng IntelliJ
```
chọn src -> main -> java -> com.nmt.universitysb -> chuột phải vào UniversitySbApplicaiton -> Run UniversitySbApplicaiton
```
## Bước 5. Mở trình duyệt và gõ đường dẫn "http://localhost:8082"
## Bước 6. Đăng nhập với thông tin sau để test các chức năng
```
tài khoản: giaovu
```
```
mật khẩu: 123456
```
# Hướng dẫn chạy hệ thống University Backend đã được đưa lên AWS (Amazon Web Serivce)
## Bước 1: Truy cập đường dẫn "http://universitysb-env.eba-nnscwuyq.ap-southeast-2.elasticbeanstalk.com/"
## Bước 2. Đăng nhập với thông tin sau để test các chức năng
```
tài khoản: giaovu
```
```
mật khẩu: 123456
```