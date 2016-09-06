/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srvlt;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author You
 */
public class Destination extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        final String CREATE_URL = "https://slack.com/api/channels.create?token=";
        final String BASE_URL = "https://slack.com/api/chat.postMessage?token=";
        String token = "xoxp-69365773543-69368379345-69310888915-ca61e3e8df";
        String channel = "";
        try {
            if(request.getParameter("root") != null ){
                if(request.getParameter("token") != null ){
                    if(request.getParameter("channel") != null &&
                            request.getParameter("root").equals("new")){
                        token = (String)request.getParameter("token");
                        channel = (String)request.getParameter("channel");
                        URL createUrl = new URL( CREATE_URL + token + "&name=" + channel );
                        UseSlack.getInstance().createChannel(createUrl); //チャンネル作成
                    }else if(request.getParameter("channel") != null &&
                            request.getParameter("root").equals("exist")){
                        token = (String)request.getParameter("token");
                        channel = (String)request.getParameter("channel");
                        URL url = new URL( BASE_URL + token + "&channel=" + channel );
                    }else{
                        request.setAttribute("chk", "チャンネル名が入力されていません。");
                    }
                }else{
                    request.setAttribute("chk","トークンが入力されていません。");
                }
            }
            
            request.getRequestDispatcher("/destination.jsp").forward(request, response);            
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
