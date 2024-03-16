<%@ page import="com.domain.User" %>
<%@ page import="java.io.PrintWriter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Administrator Management System</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body>
<div class="row-cols-6">
    <%--Get name from the session attribute--%>
    <h1><%
        User user = (User)session.getAttribute("userInfo");
        PrintWriter pw = new PrintWriter(System.out);
        pw.println(user.getName());
        pw.flush();
    %></h1>
</div>
<div class="row-cols-6">
    <h2>Choose your action</h2>
    <button>Create User</button>
    <button>Create Course</button>
</div>
</body>
</html>
