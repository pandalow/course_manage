<%@ page import="com.pojo.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pojo.User" %>
<%@ page import="com.service.CourseServer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course Management</title>
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
<%--        --%>
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
    <h5> Manage Course</h5>
    <table border="1">
        <tr>
            <td>Course id</td>
            <td>Name</td>
            <td>Semester</td>
            <td>Action</td>
        </tr>
        <% List<Course> courseList = CourseServer.getInstanceOfCourseServer().getCourses();
            System.out.println(courseList);
            if (courseList != null) {
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
                <form action="http://localhost:8080/AssSys/removeCourse" method="post">
                    <input type="hidden" name="courseid" value="<%=c.getCourseID()%>">
                    <input type="submit" value="remove">
                </form>
            </td>
            <%
                    }
                }
            %>
        </tr>
    </table>
</div>
<div>
    <h5>Create Course</h5>
    <br>
        <form method="post" action="http://localhost:8080/AssSys/createCourses">
            Course Id<input type="number" name="courseId">
            <br>
            Course Name<input type="text" name="courseName">
            <br>
            Semester1<input type="radio" value="1" name="semester">
            Semester2<input type="radio" value="2" name="semester">
            <br>
            <br>
            <input type="submit">
            <input type="reset">
        </form>
</div>
</body>
</html>
