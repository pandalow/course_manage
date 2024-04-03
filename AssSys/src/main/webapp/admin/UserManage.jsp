<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.service.UserServer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pageStyle.css">
</head>
<body>
<div>
    <%--Get name from the session attribute--%>
    <h1>
        Welcome Back - My Lord -
        <% User user = (User) session.getAttribute("userInfo");
            if (user != null && user.getName() != null) { %>
        <%= user.getName()%>
        <% System.out.println(user.getName());
        } else { %>
        <c:out value="Administrator"/>
        <% } %>
    </h1>
    <br>
    <form action="http://localhost:8080/AssSys/selectPage" method="post">
        <input type="hidden" name="action" id="actionInput">
        <button type="submit" onclick="setAction('useCreate')">Create User</button>
        <button type="submit" onclick="setAction('courseCreate')">Create Course</button>
    </form>
</div>
<script>
    function setAction(action) {
        document.getElementById('actionInput').value = action;
    }
</script>
<div>
    <h5>User Manage</h5>
    <br>
    <table border="1">
        <tr>
            <td>id</td>
            <td>Name</td>
            <td>Password</td>
            <td>Type</td>
            <td>First Name</td>
            <td>Last Name</td>
            <td>Phone</td>
            <td>Action</td>
        </tr>
        <% List<User> userList = UserServer.getInstance().getUsers(); // Use the correct attribute name
            if (userList != null) {
                for (User u : userList) { %>
        <tr>
            <td>
                <%= u.getId()%>
            </td>
            <td><%= u.getName() %>
            </td>
            <td>[Protected]</td> <!-- It's best not to display passwords -->
            <td><%= u.getType() %>
            </td>
            <td><%= u.getFirstName() %>
            </td>
            <td><%= u.getLastName() %>
            </td>
            <td><%= u.getPhone() %>
            </td>
            <td>
                <form action="http://localhost:8080/AssSys/removeUser" method="post">
                    <input type="hidden" name="userid" value="<%=u.getId()%>">
                    <input type="submit" value="remove">
                </form>
            </td>
        </tr>
        <% }
        } %>
    </table>
</div>
<div>
    <h5>Create User</h5>
    <br>
    <form action="http://localhost:8080/AssSys/modifyUser" method="post">
        User name *: <input type="text" name="username" required>
        <br>
        password *: <input type="password" name="password" required>
        <br>
        first name:<input type="text" name="firstName">
        <br>
        lastname: <input type="text" name="lastName">
        <br>
        phone number:<br><input type="number" name="phone">
        <br>
        student<input type="radio" name="type" value="student">
        teacher<input type="radio" name="type" value="teacher">
        <br>
        <input type="submit" value="submit">
        <input type="reset" value="reset">
    </form>
    <%if (request.getAttribute("status") != null) {%>
    <h6><%=request.getAttribute("status")%>
    </h6>
    <% } %>
</div>
</body>
</html>