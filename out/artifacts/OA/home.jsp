<%@ page import="entity.Login" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: luhongtao
  Date: 2022/7/20
  Time: 2:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String createTime = dateFormat.format(new Date(session.getCreationTime()));
    String lastAccesstime = dateFormat.format(new Date(session.getLastAccessedTime()));
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="css/home.css">
    <%--    study 测试session.isNew()--%>
    <%
        if (session.isNew()) {
            int count = Integer.parseInt(application.getInitParameter("existPersons"));
            count++;
            application.setAttribute("existPersons", "" + (count + 1));
        }
    %>
    <%--    <jsp:forward page="introduce.html"></jsp:forward>--%>
</head>
<body>
<div>
    <%--    fixme 加载成功之前用缓冲图标表示--%>
    <p id="ajax"></p>
    <div id="nowtime"></div>
    <%
        Integer hitcounts = (Integer) application.getAttribute("hitCounter");
        if (hitcounts == null || hitcounts == 0) {
            out.print("<h1>欢迎光临</h1>");
            hitcounts = 1;
        } else {
            hitcounts += 1;
            out.print("<h1>欢迎再次光临</h1>");
        }
        application.setAttribute("hitCounter", hitcounts);
    %>
    <%--    todo 点击量持久化--%>
    <h1><%=hitcounts%>
    </h1>
    <div>当前访问人数${sessionScope.visitCount}</div>
    <div>当前访问人有${applicationScope.userSet}</div>
    <%--    重新加载页面，会刷新在线人数和访问人数--%>
    <div>当前在线人数${applicationScope.existPersons}</div>
    <div>当前系统时间</div>
    <div>welcome <h1>${sessionScope.username}</h1></div>
    <div><%=createTime%>
    </div>
    <div><%=lastAccesstime%>
    </div>
    <div>你上一次登录是 ${sessionScope.logininfo.getLastlogoutime()} 。登录时长为${sessionScope.logininfo.getOnlinetime()}分钟</div>

    <div>
        <button>修改资料</button>
    </div>
    <button><a href="${pageContext.request.contextPath}/logout">退出</a></button>
    <button onclick="go()">
        到底是对还是错
    </button>

    <button>
        <a href="${pageContext.request.contextPath}/data.jsp">展示数据</a>
    </button>
</div>
<script>
    function getTime(){
        let date = new Date();
        let day = date.getFullYear() + " " + date.getMonth() + " " + date.getDay();
        let daytime = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
<%--        study 为什么用p元素就读取不了--%>
        document.getElementById("nowtime").innerHTML = day + " " + daytime;
    }
    window.onload=function (){
        setInterval(getTime,1000);
        ajaxMethod();
    }

    function ajaxMethod(){
        let request = new XMLHttpRequest();
        request.onreadystatechange = function () {
            if (request.readyState == 4 && request.status == 200) {
                document.getElementById("ajax").innerText=request.responseText;
            }
        }
        request.open("GET","${pageContext.request.contextPath}/ajax.action", true);
        request.send();
    }

      function go() {
        let request2 = new XMLHttpRequest();
        request2.onreadystatechange = function () {
            if (request2.readyState == 4) {
                if (request2.status != 200) {
                    alert("服务器出现异常")
                } else {
                    console.log(request2.responseText);
<%--                    --%>
                    if (request2.responseText == "true") {
                        alert("已经被正确处理");
                    } else {
                        alert("出现很大问题");
                    }
                }
            }
        }
        request2.open("GET", "${pageContext.request.contextPath}/ajax.action", true);
        request2.send();
    }








</script>
</body>
</html>
