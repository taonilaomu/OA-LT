<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" errorPage="error.jsp" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>呈现数据</title>
    <%
        List<String> nameList = new ArrayList<>();
        nameList.add("zhangfei");
        nameList.add("guangyu");
        nameList.add("liubei");
        pageContext.setAttribute("nameList", nameList);
    %>
</head>
<body>
<c:set var="value" scope="session" value="${123}"/>
<c:if test="${value<12}">
    <p>好大的值呀</p>
</c:if>
<c:forTokens items="google,baidu.safari" delims="," var="name">
    <c:out value="${name}"/>
</c:forTokens>
<c:forEach items="${nameList}" var="personName">
    <p>${personName}</p>
</c:forEach>
</body>
</html>