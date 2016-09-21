<%-- 
    Document   : admin
    Created on : 2016/08/16, 15:59:38
    Author     : You
--%>
<%@page import="java.util.Map"
        import="java.util.HashMap"
        import="srvlt.RegistData"
        import="srvlt.UserDataDTO"
        import="srvlt.RssData"
        import="srvlt.SlackHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    
    HashMap<String,RegistData> al = (HashMap<String,RegistData>)session.getAttribute("RegistData");
    String chk = (String)request.getAttribute("chk");
%>
<!DOCTYPE html>
<html>
    <head>
        <META HTTP-EQUIV="Content-Script-Type" CONTENT="text/javascript">
        <SCRIPT TYPE="text/javascript">
            var timer = "300000";		//指定ミリ秒単位(5分)
            function ReloadAddr(){
                <%if(chk == null){%>
                window.location.reload();	//ページをリロード
                <% } %>
            }
            setTimeout(ReloadAddr, timer);
        </SCRIPT>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>管理画面</title>
        
    </head>
    <body>
        <form action="Logout" method="POST" >
            <input type="submit" value="ログアウト">
        </form>
        <table border=1>
            <% for(Map.Entry<String,RegistData> val : al.entrySet()){ %>
            <form action="Delete" method="POST">
                <tr><td>サイト名:<%=val.getValue().getTitle()%><br>
                最新記事:<a href="<%=val.getValue().getItemUrl()%>" target="_blank"><%=val.getValue().getItemTitle()%></a></td>
                <td><input type="radio" form="logout" name="<%=val.getValue().getRegistID()%>" value=0 <%if(val.getValue().getRegistFlg()==0){out.print("checked=\"checked\"");}%>>ON
                    <input type="radio" form="logout" name="<%=val.getValue().getRegistID()%>" value=1 <%if(val.getValue().getRegistFlg()==1){out.print("checked=\"checked\"");}%>>OFF<br>
                <input type="hidden" name="<%=val.getKey()%>" value="<%=val.getValue().getRegistID()%>">
                <input type="submit" value="削除"></td></tr>
            </form>
            <% } %>
            <%if(chk != null){ out.println(chk); } %>
            <form action="Regist" method="POST">
                <tr><td>URL<input type="text" name="registUrl" placeholder="ここに登録したいサイトのRSSURLを入力してください"></td>
                <td><input type="submit" value="登録"></td></tr>
            </form>
        </table>
        <form action="Destination" method="POST">
            <input type="submit" value="スラック設定">
        </form>
        <form action="Reload" method="POST" id="logout">
            <% if(request.getAttribute("onOff") == null ||
                    request.getAttribute("onOff").equals("1")){ %>
            <input type="hidden" name="onOff" value="1">
            <input type="submit" value="送信すたあと" >
            <% }else if(request.getAttribute("onOff") != null ||
                    request.getAttribute("onOff").equals("0")){ %>
            <input type="hidden" name="onOff" value="0">
            <input type="submit" value="送信すとっぷ">
            <% } %>
        </form>
        <%= SlackHelper.getInstance().contact() %>
    </body>
</html>
