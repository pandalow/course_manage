<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

</head>
<body>
<div>
    <h1><c:out value="Lgoin Service"></c:out></h1>
</div>
<div>
    <form action="http://localhost:8080/AssSys/login" method="POST">
        <input name="username" placeholder="User Name">
        <input name="password" placeholder="Put in your password">
        <input type="submit">
    </form>
</div>

</body>
</html>
