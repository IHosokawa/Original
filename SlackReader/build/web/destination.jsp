<%-- 
    Document   : destination
    Created on : 2016/08/16, 16:00:05
    Author     : You
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    boolean chk = false;
    String chkcomment = "" ;
    if(request.getAttribute("chk")!=null){
        chk = true;
        chkcomment = (String)request.getAttribute("chk");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        ここがｓぁｃｋの連携ページだ！！！
        ここで登録したチャンネルへ更新連絡が届くぞーッ！！！
        
        <%
            if(chk){
                out.println(chkcomment);
            }
        %>
        <form action="Destination" method="POST">
            token : <input type="text" name="token" value="">
            チャンネル名 : <input type="text" name="channel" value="">
            <input type="submit" name="makeRoot" value="チャンネル作成">
            <input type="submit" name="updateRoot" value="チャンネル連携">
        </form>
    </body>
</html>
