/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srvlt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author You
 */
public class Delete extends HttpServlet {

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
        //リクエストパラメータの文字コードをUTF-8に変更
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        HashMap<String,RegistData> registData = (HashMap<String,RegistData>) session.getAttribute("RegistData");
        int delID = 0;
        
        try {
            for(Map.Entry<String,RegistData> val : registData.entrySet()){
                if(request.getParameter(val.getKey()) != null &&
                        val.getValue().getRegistID() == Integer.parseInt(request.getParameter(val.getKey()))){
                    delID = val.getValue().getRegistID();
                }
            }
            //データベースからレコードを削除
            UserDataDTO udd = new UserDataDTO();
            udd.setRegistID(delID);
            UserDataDAO.getInstance().registDelete(udd);
//            //セッション更新用変数
//            HashMap<String,RegistData> resultData = new HashMap<String,RegistData>();
//            //削除したレコードをセッションから削除
//            for(Map.Entry<String,RegistData> val : registData.entrySet()){
//                if(val.getValue().getRegistID() != delID){
//                    resultData.put(val.getKey(), val.getValue());
//                }
//            }
//            session.setAttribute("RegistData", resultData);
            request.setAttribute("regdel", "del");
            request.getRequestDispatcher("Admin").forward(request, response);
        }catch(Exception e){
            System.out.println("error : " + e.getMessage());
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
