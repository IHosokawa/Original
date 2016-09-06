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
public class Logout extends HttpServlet {

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
        try {
            HashMap<String,RegistData> registDataRD = (HashMap<String,RegistData>) session.getAttribute("RegistData");
            ////ON/OFFフラグの更新
            for(Map.Entry<String,RegistData> val : registDataRD.entrySet()){
                String registFlg = request.getParameter(String.valueOf(val.getValue().getRegistID()));
                RegistData rd = new RegistData();
                rd.setRegistID(val.getValue().getRegistID());
                rd.setRegistFlg(Integer.parseInt(registFlg));
                rd.setRegistUrl(val.getValue().getRegistUrl());
                //rd.setUserFlgStr(val.getValue().getUserFlgStr()); 現在未使用
                rd.setUserID(val.getValue().getUserID());
                registDataRD.put(val.getKey(), rd);
            }
            
            //ON/OFFフラグのデータベース更新
            
            HashMap<String,UserDataDTO> registData = RegistData.getInstance().HMRDM2HMUDDMappint(registDataRD);
            UserDataDAO.getInstance().registFlgUpdate(registData);
            //sessionの破棄
            session.invalidate();
            System.out.println("Session Clear!!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch(Exception e){
            out.println("error : " + e.getMessage());
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
