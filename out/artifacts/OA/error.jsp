<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>错误信息</title>
</head>
<body>
你犯法了，知道吗

错误码： <%=request.getAttribute("javax.servlet.error.status_code")%> <br>
信息： <%=request.getAttribute("javax.servlet.error.message")%> <br>
异常： <%=request.getAttribute("javax.servlet.error.exception_type")%> <br>
</body>
</html>