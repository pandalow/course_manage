<%@ page import="com.pojo.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pojo.Course" %>
<%@ page import="com.service.CourseServer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pageStyle.css">

</head>
<body>
<div>
    <h1>Hello!
        <% User user = (User) request.getSession().getAttribute("userInfo");
        %>
        <%= user.getName()%>
    </h1>
    <p>Welcome NEXT Generation Student Management System</p>
</div>
<div>
    <h5>Student List</h5>
    <table border="1">
        <tr>
            <td>id</td>
            <td>Name</td>
            <td>First Name</td>
            <td>Last Name</td>
            <td>Phone</td>
            <td>Action</td>
        </tr>
        <% List<User> userList = (List<User>) request.getAttribute("students"); // Use the correct attribute name
            if (userList != null) {
                for (User u : userList) { %>
        <tr>
            <td>
                <%= u.getId()%>
            </td>
            <td><%= u.getName() %>
            </td>
            <td><%= u.getFirstName() %>
            </td>
            <td><%= u.getLastName() %>
            </td>
            <td><%= u.getPhone() %>
            </td>
            <td>
                <form action="teacher/EditMarks.jsp" method="post">
                    <input type="hidden" name="userid" value="<%= u.getId()%>">
                    <input type="submit" name="marks" value="Edit Marks">
                </form>
            </td>
        </tr>
        <% }
        } %>
    </table>
</div>
</body>
</html>
