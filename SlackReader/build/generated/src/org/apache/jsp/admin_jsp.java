package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Map;
import java.util.HashMap;
import srvlt.RegistData;
import srvlt.UserDataDTO;
import srvlt.RssData;

public final class admin_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    HashMap<Integer,RegistData> al = (HashMap<Integer,RegistData>)session.getAttribute("RegistData");
    String chk = (String)request.getAttribute("chk");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <META HTTP-EQUIV=\"Content-Script-Type\" CONTENT=\"text/javascript\">\n");
      out.write("        <SCRIPT TYPE=\"text/javascript\">\n");
      out.write("            var timer = \"300000\";\t\t//指定ミリ秒単位(5分)\n");
      out.write("            function ReloadAddr(){\n");
      out.write("                ");
if(chk == null){
      out.write("\n");
      out.write("                window.location.reload();\t//ページをリロード\n");
      out.write("                ");
 } 
      out.write("\n");
      out.write("            }\n");
      out.write("            setTimeout(ReloadAddr, timer);\n");
      out.write("        </SCRIPT>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>管理画面</title>\n");
      out.write("        \n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
 for(Map.Entry<Integer,RegistData> val : al.entrySet()){ 
      out.write("\n");
      out.write("        <form action=\"Delete\" method=\"POST\">\n");
      out.write("            サイト名:");
      out.print(val.getValue().getTitle());
      out.write("<br>\n");
      out.write("            最新記事:<a href=\"");
      out.print(val.getValue().getItemUrl());
      out.write("\" target=\"_blank\">");
      out.print(val.getValue().getItemTitle());
      out.write("</a>\n");
      out.write("            <input type=\"radio\" name=\"ON\" value=\"0\">ON\n");
      out.write("            <input type=\"radio\" name=\"OFF\" value=\"1\">OFF\n");
      out.write("            <input type=\"hidden\" name=\"");
      out.print(val.getKey());
      out.write("\" value=\"");
      out.print(val.getValue().getRegistID());
      out.write("\">\n");
      out.write("            <input type=\"submit\" value=\"削除\">\n");
      out.write("        </form>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("        ");
if(chk != null){ out.println(chk); } 
      out.write("\n");
      out.write("        <form action=\"Regist\" method=\"POST\">\n");
      out.write("            URL<input type=\"text\" name=\"registUrl\" placeholder=\"ここに登録したいサイトのRSSURLを入力してください\">\n");
      out.write("            <input type=\"submit\" value=\"登録\">\n");
      out.write("        </form>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
