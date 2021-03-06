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
        String token = "";
        String channel = "";
        try {
            /**
             * makeRoot:新しくチャンネルを作ったときに何か入っている
             * updateRoot:既存チャンネルにログを流そうとしたときに
             * token:Userの入力したSlackToken
             * channel:Userの入力したチャンネル名
             */
            if(request.getParameter("makeRoot") != null ||
                    request.getParameter("updateRoot") != null){
                if(request.getParameter("token") != null ){
                    if(request.getParameter("channel") != null){
                        channel = (String)request.getParameter("channel");
                        token = (String)request.getParameter("token");
                        //データベースにチャンネルを登録
                        UserData ud = (UserData)session.getAttribute("LoginData");
                        UserDataDTO udd = new UserDataDTO();
                        ud.UD2DTOMapping(udd);
                        udd.setSlackChannel(channel);
                        udd.setToken(token);
                        UserDataDAO.getInstance().channelInsert(udd);
                        
                        if( request.getParameter("makeRoot") != null ){
                            //チャンネル作成
                            URL createUrl = new URL( CREATE_URL + token + "&name=" + channel );
                            UseSlack.getInstance().createChannel(createUrl);
                            request.setAttribute("chk", "Slackにチャンネルを作成しました。");

                        }else if( request.getParameter("updateRoot") != null ){
                            request.setAttribute("chk", "登録情報を更新しました。");
                        }
                    }else{
                        request.setAttribute("chk", "チャンネル名が入力されていません。");
                    }
                }else{
                    request.setAttribute("chk","トークンが入力されていません。");
                }
            }
            
            request.getRequestDispatcher("/destination.jsp").forward(request, response);            
        } catch(Exception e) {
            out.print("error : " + e.getMessage());
        }finally {
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
