<%-- 
    Document   : signup
    Created on : 2016/08/16, 15:59:18
    Author     : You
--%>
<%@page import="srvlt.UserData" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    boolean chk = false;
    UserData ud = null;
    //signupResult.jspの「戻る」から遷移してきたときに実行される 前回入力された値を表示
    if(request.getParameter("reinput") != null &&
            request.getParameter("reinput").equals("ReInput")){
        ud = (UserData)session.getAttribute("SignupUD");
        chk = true;
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>新規登録</title>
    </head>
    <body>
        <form action="Login" method="POST">
            <input type="submit" value="ログインページへ"><br>
        </form>
        <%if(request.getAttribute("overlap") != null &&
                request.getAttribute("overlap").equals("Overlap")){%>
            すでにそのIDは登録されておりますため、別のユーザーIDで登録してください。
        <% } else {%>
            希望のユーザーIDとパスワードを入力してください。
        <% } %>
        <form action="SignupResult" method="POST">
            ユーザーID<input type="text" name="userName" value="<%if(chk){out.print(ud.getUserName());}%>"><br>
            パスワード<input type="text" name="password" value="<%if(chk){out.print(ud.getPassword());}%>"><br>
            <input type="submit" value="確認画面へ">
        </form>
    </body>
</html>
