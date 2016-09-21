<%-- 
    Document   : contact
    Created on : 2016/08/16, 16:00:24
    Author     : You
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>お問い合わせフォーム</title>
    </head>
    <body>
        <form action="Contact" method="POST">
            <input type="text" name="title" value="">
            <select name ="type">
                <option value="" selected>----</option>
                <option value="">ご意見</option>
                <option value="">不具合報告</option>
                <option value="">その他</option>
            </select>
            <textarea name="hobby" style="resize:none" wrap="hard"></textarea>
        </form>
    </body>
</html>
