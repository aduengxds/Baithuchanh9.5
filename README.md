# Product Discount Calculator

Ứng dụng Java JSP/Servlet tính chiết khấu sản phẩm, tương thích Apache Tomcat 10.1+ và Jakarta Servlet 6.0.

## Công nghệ
- Java 17
- Maven WAR
- JSP và Jakarta Servlet

## Chạy ứng dụng
1. Build tại thư mục gốc:
   ```powershell
   & "C:\Tools\apache-maven-3.10.0\bin\mvn.cmd" clean package
   ```
2. Deploy `target/product-discount-calculator.war` lên Tomcat 10.1+.
3. Truy cập `http://localhost:8080/product-discount-calculator/`.

## Công thức
- Discount Amount = List Price × Discount Percent × 0.01
- Discount Price = List Price − Discount Amount

Servlet: `POST /display-discount`. Tỷ lệ chiết khấu hợp lệ từ 0 đến 100; giá niêm yết không âm.
