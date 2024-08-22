package com.my.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
@WebListener
public class MySessionAttributeListener implements HttpSessionAttributeListener {
    private int loginedCnt;
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        String attrName = event.getName();
        System.out.println("세션에 추가된 attrName: " + attrName);
        if (attrName.equals("loginedId")) {
            loginedCnt++;
            System.out.println("로그인된 사용자수:" + loginedCnt);
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        String attrName = event.getName();
        System.out.println("세션에 삭제된 attrName: " + attrName);
        if (attrName.equals("loginedId")) {
            loginedCnt--;
            System.out.println("로그인된 사용자수:" + loginedCnt);
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        HttpSessionAttributeListener.super.attributeReplaced(event);
    }
}
