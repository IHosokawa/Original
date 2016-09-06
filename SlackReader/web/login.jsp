<%-- 
    Document   : login
    Created on : 2016/08/16, 15:57:22
    Author     : You
--%>
<%@page import="srvlt.UserData" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    boolean chk = false;
    UserData ud = null;
    
    if(request.getAttribute("reinput") != null && 
            request.getAttribute("reinput").equals("ReInput")){
        ud = (UserData)request.getAttribute("loginError");
        chk = true;
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ログイン</title>
    </head>
    <body>
        <form action="Signup" method="POST">
            <input type="submit" value="新規登録ページへ">
        </form>
        <br>
        <%if(chk){%>
            <%if(request.getAttribute("delFlg") != null &&
                    request.getAttribute("delFlg").equals("1")){%>
                そのアカウントは削除されております。<br>
            <% }else{ %>
                ユーザーID、あるいはパスワードに誤りがあります。<br>
        <% }} %>
        <form action="Admin" method="POST">
            ユーザーID<input type="text" name="userName" placeholder="ユーザーID" value="<%if(chk){ out.print(ud.getUserName()); } %>"><br>
            パスワード<input type="text" name="password" value="<%if(chk){ out.print(ud.getPassword()); } %>"><br>
            <input type="submit" value="ログイン">
        </form>
    </body>
</html>
