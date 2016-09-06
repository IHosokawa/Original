<%-- 
    Document   : signupResult
    Created on : 2016/08/16, 16:39:13
    Author     : You
--%>
<%@page import="srvlt.UserData" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    UserData ud = (UserData)session.getAttribute("SignupUD");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登録内容確認</title>
    </head>
    <body>
        ユーザーID:<%=ud.getUserName()%><br>
        パスワード:<%=ud.getPassword()%><br>
        上記の内容で登録しますが、よろしいですか？<br>
        <form action="SignupResult" method="POST">
            <input type="hidden" name="signup" value="Accept">
            <input type="submit" value="登録">
        </form>
        <form action="Signup" method="POST">
            <input type="hidden" name="reinput" value="ReInput">
            <input type="submit" value="戻る">
        </form>
    </body>
</html>
