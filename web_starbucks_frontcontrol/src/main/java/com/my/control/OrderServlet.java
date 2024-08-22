package com.my.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dto.CartDTO;
import com.my.order.dao.OrderRepository;
import com.my.order.exception.AddException;
import com.my.order.exception.FindException;
import com.my.order.vo.OrderInfo;
import com.my.order.vo.OrderLine;
import com.my.product.vo.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private OrderRepository repository = new OrderRepository();

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173"); // 클라이언트의 출처
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS"); // 허용할 HTTP 메소드
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); // 허용할 헤더
        response.setHeader("Access-Control-Allow-Credentials", "true"); // 자격 증명 허용
        System.out.println("in order doOptions");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173"); // 클라이언트의 출처
        response.setHeader("Access-Control-Allow-Credentials", "true"); // 자격 증명 허용

        HttpSession session = request.getSession();
        System.out.println("in order doPost sessionid:" + session.getId());


        String loginedId = (String) session.getAttribute("loginedId");
        System.out.println("in order doPost loginedId:" + loginedId);
        if (loginedId == null) {
            response.setStatus(401); //
            return;
        }

        Map<String, Integer> cart = (Map) session.getAttribute("cart");
        System.out.println("in order doPost cart:" + cart);
        if (cart == null || cart.isEmpty()) {
            response.setStatus(400); //
            return;
        }

        OrderInfo info = new OrderInfo();
        info.setOrderId(loginedId);
        List<OrderLine> lines = new ArrayList<>();
        for (Map.Entry<String, Integer> e : cart.entrySet()) {
            String prodNo = e.getKey();
            Integer quantity = e.getValue();
            OrderLine line = new OrderLine();
//                line.setOrderNo();
            Product p = new Product();
            p.setProdNo(prodNo);
            line.setOrderP(p);
            line.setOrderQuantity(quantity);
            lines.add(line);
        }
        info.setLines(lines);
        try {
            repository.insert(info);
            session.removeAttribute("cart"); //cart 비우기
        } catch (AddException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        response.setContentType("application/json;charset=UTF-8");
        System.out.println("in order doGet sessionid:" + request.getSession().getId());
        String loginedId = (String) request.getSession().getAttribute("loginedId");
        System.out.println("in order doGet loginedId:" + loginedId);
        if(loginedId == null) {
            response.setStatus(401); //로그인 안한경우
        }else {
            try {
                List<OrderInfo> orderInfoList=  repository.findByOrderId(loginedId);
                ObjectMapper mapper = new ObjectMapper();
                String jsonStr = mapper.writeValueAsString(orderInfoList);
                response.getWriter().print(jsonStr);
            } catch (FindException e) {
                e.printStackTrace();
                response.setStatus(400); //주문내역이 없는 경우
            }
        }
    }
}
