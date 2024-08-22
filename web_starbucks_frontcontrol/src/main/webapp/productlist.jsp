<%@ page import="com.my.product.vo.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: student
  Date: 2024-07-30
  Time: 오후 3:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>productlist.jsp</title>
</head>
<body>
<%
    //1. 결과 얻기
    List<Product> list = (List)request.getAttribute("list");
    for(Product p: list){
        String prodNo = p.getProdNo();
        String prodName = p.getProdName();
        int prodPrice = p.getProdPrice();
%><%=prodNo%> : <%=prodName%> : <%=prodPrice%><br>
<%
    }
%>
</body>
</html>
