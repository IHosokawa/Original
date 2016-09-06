/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srvlt;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author You
 */
public class SignupResult extends HttpServlet {

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
        
        UserData ud = new UserData();
        try {
            /**signupResult.jspの「登録」で遷移してきたときif内が実行される
             * データベースにユーザーデータを登録
             */
            if(request.getParameter("signup") != null &&
                    request.getParameter("signup").equals("Accept")){
                UserDataDTO udd = new UserDataDTO();
                ud = (UserData)session.getAttribute("SignupUD");
                ud.UD2DTOMapping(udd);
                
                if(UserDataDAO.getInstance().overlapSearch(udd)){
                    request.setAttribute("overlap", "Overlap");
                    request.getRequestDispatcher("/signup.jsp").forward(request, response);
                }else{
                    UserDataDAO.getInstance().insert(udd);
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
            }else{
            //入力された値をUserData形式でセッションに保存
            ud.setUserName((String)request.getParameter("userName"));
            ud.setPassword((String)request.getParameter("password"));
            session.setAttribute("SignupUD", ud);
            
            request.getRequestDispatcher("/signupResult.jsp").forward(request, response);
            }
        }catch(Exception e){
            out.print("error : " + e.getMessage());
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
