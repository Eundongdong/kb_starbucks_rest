package com.my.control;

import com.my.customer.exception.AddException;
import com.my.customer.exception.FindException;
import com.my.customer.service.CustomerService;
import com.my.customer.vo.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private CustomerService service = new CustomerService();
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173"); // 클라이언트의 출처
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS"); // 허용할 HTTP 메소드
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization"); // 허용할 헤더
        response.setHeader("Access-Control-Allow-Credentials", "true"); // 자격 증명 허용
        System.out.println("in login doOptions");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173"); // 클라이언트의 출처
        response.setHeader("Access-Control-Allow-Credentials", "true"); // 자격 증명 허용
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");
        String name = request.getParameter("name");
        Customer c = new Customer(id, pwd, name);
        try {
            service.signup(c);
        } catch (AddException e) {
            System.out.println(e.getMessage());
            response.setStatus(400); //BAD REQUEST
        }
    }
}