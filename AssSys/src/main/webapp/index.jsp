<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/pageStyle.css">
    <title>111</title>
</head>
<body>
<div>
    <h1><c:out value="Login"></c:out></h1>
    <br>
    <form action="http://localhost:8080/AssSys/login" method="POST">
        User name: <input name="username" type="text" placeholder="Login Name" required>
        <br>
        Password: <input name="password" type="password" placeholder="Numbers ie:(0-9)" required>
        <br>
        <input type="submit">
        <input type="reset">
    </form>

</div>
<div>
    <h5 style="align-content: center">Introduction</h5>
    <br>
    <p style="align-content: center"> When we say we are an all-in-one solution for schools, we mean it. We become your
        strategic partner to improve school administration, drive parental engagement and foster school well-being. The
        best part is that we facilitate Management, provide flexibility to fit your needs and offer unmatched
        support.
    </p>
</div>
<div>
    <h5 style="align-content: center">Test Account</h5>
    <br>
    <table>
        <tr>
            <td>Type</td>
            <td>User name</td>
            <td>Password</td>
        </tr>
        <tr>
            <td>Admin</td>
            <td>zhuang</td>
            <td>12345</td>
        </tr>
        <tr>
            <td>Student</td>
            <td>cindy</td>
            <td>12345</td>
        </tr>
        <tr>
            <td>Teacher</td>
            <td>Tea</td>
            <td>12345</td>
        </tr>
    </table>
</div>
</body>
</html>
