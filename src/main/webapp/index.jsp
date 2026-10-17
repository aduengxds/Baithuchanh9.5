<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Product Discount Calculator</title>
    <style>
        *{box-sizing:border-box}body{font-family:Arial,sans-serif;background:#f4f7fb;margin:0;padding:40px 16px}.card{max-width:520px;margin:40px auto;background:#fff;padding:30px;border-radius:12px;box-shadow:0 4px 18px #0001}h1{font-size:25px;color:#183153;text-align:center;margin-top:0}label{display:block;font-weight:bold;margin:18px 0 7px}input{width:100%;padding:12px;border:1px solid #cbd5e1;border-radius:6px;font-size:16px}button{width:100%;margin-top:24px;padding:13px;background:#183153;color:white;border:0;border-radius:6px;font-size:16px;font-weight:bold;cursor:pointer}button:hover{background:#274b77}
    </style>
</head>
<body>
<main class="card">
    <h1>Product Discount Calculator</h1>
    <form action="display-discount" method="post">
        <label for="productDescription">Product Description</label>
        <input id="productDescription" type="text" name="productDescription" required maxlength="200" placeholder="Nhập mô tả sản phẩm">

        <label for="listPrice">List Price</label>
        <input id="listPrice" type="number" name="listPrice" min="0" step="0.01" required placeholder="Nhập giá niêm yết">

        <label for="discountPercent">Discount Percent (%)</label>
        <input id="discountPercent" type="number" name="discountPercent" min="0" max="100" step="0.01" required placeholder="Nhập tỷ lệ chiết khấu">

        <button type="submit">Calculate Discount</button>
    </form>
</main>
</body>
</html>
