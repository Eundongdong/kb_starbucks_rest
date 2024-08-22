package com.my.listener;

import com.my.product.dao.ProductRepositoryMySQL;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext객체생성됨");
        ProductRepositoryMySQL pDAO = new ProductRepositoryMySQL();
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}