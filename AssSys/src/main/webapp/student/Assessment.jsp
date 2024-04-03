<%@ page import="com.pojo.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pojo.User" %>
<%@ page import="com.pojo.Assessment" %>
<%@ page import="com.service.CourseServer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Check Assess</title>
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
    <h5>Your Course Marks</h5>
    <table border="1">
        <tr>
            <td>Course id</td>
            <td>quiz</td>
            <td>Assignment</td>
            <td>exam</td>
        </tr>
        <% List<Assessment> ass = (List<Assessment>) request.getAttribute("assess");
            if (ass != null) {
                for (Assessment c : ass) {
        %>
        <tr>
            <td><%=c.getCourseID()%>
            </td>
            <td><%=c.getQuiz()%>
            </td>
            <td><%=c.getAssignment()%>
            </td>
            <td><%= c.getExam()%>
            </td>
        </tr>
        <%
                }
            } else {

            }
        %>
    </table>
</div>
</body>
</html>
