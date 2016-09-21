/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srvlt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
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
public class Reload extends HttpServlet {

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
        try {

            LinkedHashMap<String,RegistData> beforeData = (LinkedHashMap<String,RegistData>) session.getAttribute("RegistData");
            //個別ON/OFFフラグの更新
            for(Map.Entry<String,RegistData> val : beforeData.entrySet()){
                String registFlg = request.getParameter(String.valueOf(val.getValue().getRegistID()));
                RegistData rd = new RegistData();
                rd.setRegistID(val.getValue().getRegistID());
                rd.setRegistFlg(Integer.parseInt(registFlg));
                rd.setRegistUrl(val.getValue().getRegistUrl());
                //rd.setUserFlgStr(val.getValue().getUserFlgStr()); 現在未使用
                rd.setUserID(val.getValue().getUserID());
                rd.setTitle(val.getValue().getTitle());
                rd.setItemTitle(val.getValue().getItemTitle());
                rd.setItemUrl(val.getValue().getItemUrl());
                beforeData.put(val.getKey(), rd);
            }
            
            //個別ON/OFFフラグのデータベース更新
            
            LinkedHashMap<String,UserDataDTO> beforeRegistData = RegistData.getInstance().HMRDM2HMUDDMappint(beforeData);
            UserDataDAO.getInstance().registFlgUpdate(beforeRegistData);
            
            //最新のフィード情報の取得
            UserDataDTO udd = (UserDataDTO) session.getAttribute("LoginDataUdd");
            LinkedHashMap<String,UserDataDTO> registData = UserDataDAO.getInstance().loginDataSeach(udd);
            LinkedHashMap<String,RegistData> registDataRD = RegistData.getInstance().HMUDD2HMRDMappint(registData);
            
            if(request.getParameter("onOff") != null &&
                    request.getParameter("onOff").equals("1")){
                //LinkedHashMap<String,RegistData> registDataRD = ReloadTask.getInstance().autoUp(udd);
                //フィード更新前の情報をbeforeDataに取得
                beforeData = (LinkedHashMap<String,RegistData>)session.getAttribute("RegistData");
                UseSlack.getInstance().upChkAndSay(beforeData, registDataRD, udd);
                session.setAttribute("RegistData", registDataRD);
                
                //全体OnOff判別リクエスト
                request.setAttribute("onOff","0");
            }else if(request.getParameter("onOff").equals("0")){
                
                //全体OnOff判別リクエスト
                request.setAttribute("onOff","1");
            }
            
            session.setAttribute("RegistData", registDataRD);
            request.getRequestDispatcher("/admin.jsp").forward(request, response);
        } catch(Exception e) {
            out.println("error : " + e.getMessage() );
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
