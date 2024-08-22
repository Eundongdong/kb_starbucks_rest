package com.my.control;

//import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.product.dao.ProductRepository;
import com.my.product.dao.ProductRepositoryMySQL;
import com.my.product.exception.FindException;
import com.my.product.vo.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/product/list")
public class ProductListServlet extends HttpServlet {
    private ProductRepository productRepository = new ProductRepositoryMySQL();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //MVC구조
//        try {
//            //1. 요청정보 분석
//            //2. 비지니스로직 호출
//            List<Product> list = productRepository.findAll();
//            //3. 결과를 요청속성으로 추가
//            request.setAttribute("list", list);
//            //4. 페이지 이동
//            String path = "/productlist.jsp";
//            RequestDispatcher rd = request.getRequestDispatcher(path);
//            rd.forward(request, response);
//            System.out.println("서블릿");
//        } catch (FindException e) {
//            request.setAttribute("msg", e.getMessage());
//            String path = "/err.jsp";
//            RequestDispatcher rd = request.getRequestDispatcher(path);
//            rd.forward(request, response);
//        }

        response.setHeader("Access-Control-Allow-origin", "*");

        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
//        out.print("[");
//        out.print("{ \"prodNo\": \"C0001\", \"prodName\": \"아메리카노\", \"prodPrice\": 1500, \"img\": \"C0001.jpg\" },");
//        out.print("{ \"prodNo\": \"C0002\", \"prodName\": \"아이스아메리카노\", \"prodPrice\": 1500, \"img\": \"C0002.jpg\" }");
//        out.print("]");
//
//
        try {
            List<Product> list = productRepository.findAll();
            ObjectMapper mapper = new ObjectMapper();
            String jsonStr = mapper.writeValueAsString(list);//list객체 -> JSON String
            out.print(jsonStr);
        } catch (FindException e) {
            response.setStatus(500);
        }
    }
}
