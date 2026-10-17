package com.codegym;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DiscountServlet", urlPatterns = "/display-discount")
public class DiscountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String description = request.getParameter("productDescription");
        String listPriceText = request.getParameter("listPrice");
        String discountPercentText = request.getParameter("discountPercent");

        try (PrintWriter out = response.getWriter()) {
            if (description == null || description.isBlank()) {
                showError(out, "Vui lòng nhập mô tả sản phẩm.");
                return;
            }

            try {
                BigDecimal listPrice = new BigDecimal(listPriceText);
                BigDecimal discountPercent = new BigDecimal(discountPercentText);
                if (listPrice.signum() < 0 || discountPercent.signum() < 0 || discountPercent.compareTo(BigDecimal.valueOf(100)) > 0) {
                    showError(out, "Giá niêm yết phải từ 0 trở lên và tỷ lệ chiết khấu phải từ 0 đến 100.");
                    return;
                }
                BigDecimal discountAmount = listPrice.multiply(discountPercent)
                        .multiply(BigDecimal.valueOf(0.01)).setScale(2, RoundingMode.HALF_UP);
                BigDecimal discountPrice = listPrice.subtract(discountAmount).setScale(2, RoundingMode.HALF_UP);

                out.println("<!DOCTYPE html><html lang='vi'><head><meta charset='UTF-8'><title>Kết quả chiết khấu</title>");
                out.println("<style>body{font-family:Arial,sans-serif;background:#f4f7fb;margin:0;padding:40px}.card{max-width:560px;margin:auto;background:white;padding:28px;border-radius:12px;box-shadow:0 4px 18px #0001}h1{color:#183153}dt{font-weight:bold;margin-top:16px}dd{margin:6px 0;color:#176b45;font-size:18px}.back{display:inline-block;margin-top:24px;padding:10px 16px;background:#183153;color:white;text-decoration:none;border-radius:6px}</style></head><body><main class='card'>");
                out.println("<h1>Kết quả tính chiết khấu</h1><dl>");
                out.println("<dt>Product Description</dt><dd>" + escapeHtml(description.trim()) + "</dd>");
                out.println("<dt>List Price</dt><dd>" + listPrice.toPlainString() + "</dd>");
                out.println("<dt>Discount Percent</dt><dd>" + discountPercent.toPlainString() + "%</dd>");
                out.println("<dt>Discount Amount</dt><dd>" + discountAmount.toPlainString() + "</dd>");
                out.println("<dt>Discount Price</dt><dd>" + discountPrice.toPlainString() + "</dd>");
                out.println("</dl><a class='back' href='index.jsp'>Quay lại</a></main></body></html>");
            } catch (NumberFormatException | NullPointerException ex) {
                showError(out, "Vui lòng nhập giá niêm yết và tỷ lệ chiết khấu hợp lệ.");
            }
        }
    }

    private void showError(PrintWriter out, String message) {
        out.println("<!DOCTYPE html><html lang='vi'><head><meta charset='UTF-8'><title>Lỗi nhập liệu</title></head><body style='font-family:Arial,sans-serif;margin:60px;text-align:center'>");
        out.println("<h2 style='color:#b42318'>" + escapeHtml(message) + "</h2><p><a href='index.jsp'>Quay lại</a></p></body></html>");
    }

    private String escapeHtml(String value) {
        return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }
}
