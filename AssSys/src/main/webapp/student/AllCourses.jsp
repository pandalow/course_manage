<%@ page import="com.service.CourseServer" %>
<%@ page import="com.pojo.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pojo.User" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pageStyle.css">
    <title>All Courses</title>
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
    <h5>Choose Courses</h5>
    <%
        if (request.getAttribute("result") != null) {
    %>
    <h6><%=request.getAttribute("result").toString() %>
    </h6>
    <%
        } %>
    <table border="1">
        <tr>
            <td>Course id</td>
            <td>Name</td>
            <td>Semester</td>
            <td>Actions</td>
        </tr>
        <%

             // Get all the courses by service
            List<Course> courses =
                    CourseServer.getInstanceOfCourseServer().getCourses();


             // Filter the students courses
            List<Course> studentCourses = CourseServer
                    .getInstanceOfCourseServer()
                    .getUserCourses((User) request.getSession().getAttribute("userInfo"));

            // Filter the courses which student does not registered
            List<Course> collect = courses.stream().filter(course -> {
                for (Course a : studentCourses) {
                    if (course.getCourseID() == a.getCourseID()) {
                        return false;
                    }
                }
                return true;
            }).collect(Collectors.toList());
            for (Course c : collect) {

        %>
        <tr>
            <td><%= c.getCourseID()%>
            </td>
            <td><%= c.getName()%>
            </td>
            <td><%= c.getSemester()%>
            </td>
            <td>

                <form action="http://localhost:8080/AssSys/enroll" method="post">

                    <input type="hidden" name="courseId" value="<%= c.getCourseID()%>">
                    <input type="submit" name="marks" value="Enrolled">
                </form>
            </td>
        </tr>
        <%}%>
    </table>
</div>
</body>
</html>
