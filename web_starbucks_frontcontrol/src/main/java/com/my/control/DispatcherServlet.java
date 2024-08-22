package com.my.control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Properties env = new Properties();
        env.load(
        getServletContext().getResourceAsStream("/WEB-INF/classes/control.properties"));
        String servletPath = req.getServletPath();

        System.out.println(servletPath);
        String []exceptPath = {".ico", ".png", ".jpg", ".html", ".js", ".css", ".txt"};
        for (String s : exceptPath) {
            if (servletPath.endsWith(s)) {
                //요청내용 그대로 응답하기
                return;
            }
        }
        String clazzName= env.getProperty(servletPath);
        System.out.println(clazzName);
    }
}
