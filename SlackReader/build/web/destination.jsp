<%-- 
    Document   : destination
    Created on : 2016/08/16, 16:00:05
    Author     : You
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        ここがｓぁｃｋの連携ページだ！！！
        ここで登録したチャンネルへ更新連絡が届くぞーッ！！！
        
        <form action="Destination" method="POST">
            token : <input type="text" name="token" value="">
            チャンネル名 : <input type="text" name="channel" value="">
            <input type="submit" value="チャンネル作成">
            <input type="hidden" name="root" value="new">
        </form>
        <form action="Destination" method="POST">
            token : <input type="text" name="token" value="">
            既存チャンネル名<input type="text" name="channel" value="">
            <input type="submit" value="チャンネル連携">
            <input type="hidden" name="root" value="exist">
        </form>
    </body>
</html>
