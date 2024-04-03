<%@ page import="com.pojo.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pojo.User" %>
<%@ page import="com.service.CourseServer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome to Teacher Page</title>
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
    <h5>Assigned Courses</h5>
    <table border="1">
        <tr>
            <td>Course id</td>
            <td>Name</td>
            <td>Semester</td>
            <td>Actions</td>
        </tr>
        <% List<Course> teacherCourses =
                CourseServer.getInstanceOfCourseServer().getUserCourses(user);
            if (teacherCourses != null) {
                for (Course c : teacherCourses) {
        %>
        <tr>
            <td><%= c.getCourseID()%>
            </td>
            <td><%= c.getName()%>
            </td>
            <td><%= c.getSemester()%>
            </td>
            <td>
                <form action="http://localhost:8080/AssSys/getStudent" method="post">
                    <input type="hidden" name="courseId" value="<%= c.getCourseID()%>">
                    <input type="submit" name="marks" value="list students">
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <h4>You have not been assigned any course yet;</h4>
        <%
            }
        %>
    </table>
    <form action="TeacherCourse.jsp" method="post">
        <input type="submit" value="Register courses">
    </form>
</div>
</body>
</html>
