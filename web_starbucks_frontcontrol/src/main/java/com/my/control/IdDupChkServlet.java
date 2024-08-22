package com.my.control;

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

@WebServlet("/iddupchk/*")
public class IdDupChkServlet extends HttpServlet {
    private CustomerService service = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173"); // 클라이언트의 출처

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = request.getPathInfo().substring(1);
        try {
            //id에 해당하는 고객정보가 있을 때
            Customer c = service.showMyInfo(id);
            response.setStatus(400); //BAD REQUEST
            out.println("이미 존재하는 아이디입니다");
        } catch (FindException e) {
            //id에 해당하는 고객정보가 없을 때
            e.printStackTrace();
            response.setStatus(200);
        }
    }
}