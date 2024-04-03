<%@ page import="com.pojo.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pojo.User" %>
<%@ page import="com.service.CourseServer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome to Student Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pageStyle.css">
</head>
<body>
<div>
    <h1>Hello!
        <% User user = (User) request.getSession().getAttribute("userInfo");

            //Get the course relate students
            List<Course> courseList =
                    CourseServer.getInstanceOfCourseServer().getUserCourses(user);
        %>
        <%= user.getName()%>
    </h1>
    <p>Welcome NEXT Generation Student Management System</p>
</div>
<div>
    <h5>Enrolled Courses</h5>
    <%if (courseList != null) {%>
    <table border="1">
        <tr>
            <td>Course id</td>
            <td>Name</td>
            <td>Semester</td>
            <td>Actions</td>
        </tr>
        <%
            for (Course c : courseList) {
        %>
        <tr>
            <td><%= c.getCourseID()%>
            </td>
            <td><%= c.getName()%>
            </td>
            <td><%= c.getSemester()%>
            </td>
            <td>
                <form action="http://localhost:8080/AssSys/checkmarks" method="post">
                    <input type="hidden" name="courseId" value="<%= c.getCourseID()%>" requ>
                    <input type="submit" name="marks" value="Check Marks">
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <h5>You have not enrolled any course yet;</h5>
        <%
            }
        %>
    </table>
    <br>
    <form action="AllCourses.jsp" method="post">
        <input type="submit" value="Enroll new class">
    </form>
</div>
</body>
</html>
