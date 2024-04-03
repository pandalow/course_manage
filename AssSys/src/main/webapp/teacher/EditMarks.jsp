<%@ page import="com.service.CourseServer" %>
<%@ page import="com.pojo.Assessment" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pojo.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Marks Edit</title>
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
    <h5>Student Marks</h5>
    <table border="1">
        <tr>
            <td>courseId</td>
            <td>Quiz</td>
            <td>Assignment</td>
            <td>Exam</td>
        </tr>

        <%-- get student info and looking for the students marks --%>
        <% int userid = Integer.parseInt(request.getParameter("userid"));
            int courseId = Integer.parseInt(request.getSession().getAttribute("courseId").toString());
            List<Assessment> marksAssess = CourseServer.getInstanceOfCourseServer().getMarksAssess(userid, courseId);
            if (marksAssess != null) {
                for (Assessment a : marksAssess) {
        %>
        <tr>
            <td>
                <%= a.getCourseID()%>
            </td>
            <td>
                <%= a.getQuiz()%>
            </td>
            <td>
                <%=a.getAssignment()%>
            </td>
            <td>
                <%=a.getExam()%>
            </td>
        </tr>
        <%
                }
            } %>
    </table>
    <br>
    <h4>Update Marks</h4>
    <form method="post" action="http://localhost:8080/AssSys/edit">
        <input type="hidden" name="userid" value="<%=userid%>">
        <input type="hidden" name="courseId" value="<%=courseId%>">
        marks: <input type="number" name="marks">
        quiz<input type="radio" name="type" value="quiz">
        exam<input type="radio" name="type" value="exam">
        assignment <input type="radio" name="type" value="assignment">
        <input type="submit">
    </form>
</div>
</body>
</html>
