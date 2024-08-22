package com.my.control;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.CartDTO;
import com.my.product.dao.ProductRepository;
import com.my.product.dao.ProductRepositoryMySQL;
import com.my.product.exception.FindException;
import com.my.product.vo.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private ProductRepository repository = new ProductRepositoryMySQL();
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173"); // 클라이언트의 출처
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS"); // 허용할 HTTP 메소드
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); // 허용할 헤더
        response.setHeader("Access-Control-Allow-Credentials", "true"); // 자격 증명 허용
        System.out.println("in cart doOptions");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173"); // 클라이언트의 출처
        response.setHeader("Access-Control-Allow-Credentials", "true"); // 자격 증명 허용

//        //요청전달데이터 얻기
        String prodNo = request.getParameter("prodNo");
        String quantity = request.getParameter("quantity");
        int quantityInt = Integer.parseInt(quantity);
        HttpSession session = request.getSession();
        System.out.println("in cart doPost sessionid:" +session.getId());
        Map<String, Integer> cart =(Map)session.getAttribute("cart");
        if(cart == null){
            cart = new HashMap<String, Integer>();
            session.setAttribute("cart", cart);
        }
        Integer oldQuantity = cart.get(prodNo);
        if(oldQuantity != null){
            quantityInt+=oldQuantity;
        }
        cart.put(prodNo, quantityInt);
//        System.out.println(cart);
/*
        //JSON요청데이터 처리

        System.out.println("enc-type" + request.getContentType());
        // 요청 본문에서 JSON 문자열 읽기
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = request.getReader()) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }

        // JSON 문자열 파싱
        String jsonString = stringBuilder.toString();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonMap =
                mapper.readValue(jsonString,
                                new TypeReference<Map<String, Object>>(){});
        System.out.println(jsonMap);
        HttpSession session = request.getSession();
        System.out.println("in cart doPost sessionid:" + session.getId());
        System.out.println(session.getId());
        Map<String, Integer> cart =(Map)session.getAttribute("cart");
        if(cart == null){
            cart = new HashMap<String, Integer>();
            session.setAttribute("cart", cart);
        }
        String prodNo = (String)jsonMap.get("prodNo");
        Integer quantity = (Integer)jsonMap.get("quantity");
        Integer oldQuantity = cart.get(prodNo);
        if(oldQuantity != null){
            quantity+=oldQuantity;
        }
        cart.put(prodNo, quantity);
        System.out.println(cart);
*/

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setContentType("application/json;charset=utf-8");
        HttpSession session = request.getSession();
        System.out.println("in cart doGet sessionid:" + session.getId());
        Map<String, Integer> cart =(Map)session.getAttribute("cart");

        List<CartDTO> responseCart = new ArrayList<>();
        if(cart == null){
            response.setStatus(400);
            return;
        }
        /** 응답내용
         * [{ "prodNo" : 'C0001',
         *   "prodName": '아메리카노',
         *   "prodPrice": 1000,
         *   "quantity": 2
         *  },
         *  ]
         */
        for(Map.Entry<String, Integer> e: cart.entrySet()){
            String prodNo = e.getKey();
            Integer quantity = e.getValue();
            try {
                Product p = repository.findById(prodNo);
                CartDTO dto = new CartDTO(p.getProdNo(), p.getProdName(), p.getProdPrice(), quantity);
                responseCart.add(dto);
            } catch (FindException ex) {
                throw new RuntimeException(ex);
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        String jsonMap =  mapper.writeValueAsString(responseCart);
        PrintWriter out = response.getWriter();
        out.println(jsonMap);
    }
}
