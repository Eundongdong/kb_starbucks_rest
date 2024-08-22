package com.my.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.product.dao.ProductRepository;
import com.my.product.dao.ProductRepositoryMySQL;
import com.my.product.exception.FindException;
import com.my.product.vo.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/product/*")
public class ProductServlet extends HttpServlet {
    private ProductRepository productRepository = new ProductRepositoryMySQL();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-origin", "*");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setContentType("application/json;charset=utf-8");
        String prodNo = request.getPathInfo().substring(1);

        PrintWriter out = response.getWriter();
        try {
            Product p = productRepository.findById(prodNo);
            ObjectMapper mapper = new ObjectMapper();
            String jsonStr = mapper.writeValueAsString(p);
            out.print(jsonStr);
        } catch (FindException e) {
            e.printStackTrace();
            response.setStatus(500);
        }
    }
}
