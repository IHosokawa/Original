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
import java.util.LinkedHashMap;

/**
 *
 * @author You
 */
public class Admin extends HttpServlet {

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
        UserData ud = new UserData();
        UserDataDTO udd = new UserDataDTO();
        try {
            if(request.getAttribute("regdel") == null){
                ud.setUserName((String)request.getParameter("userName"));
                ud.setPassword((String)request.getParameter("password"));
                ud.UD2DTOMapping(udd);
                udd = UserDataDAO.getInstance().loginSearch(udd);
            }else{
                ud = (UserData)session.getAttribute("LoginData");
                ud.UD2DTOMapping(udd);
                udd = UserDataDAO.getInstance().loginSearch(udd);
            }
            if(udd.getUserID() != 0 &&
                    udd.getDelFlg() == 0){
                //ログイン成功時処理
                //DBに入力データと一致するユーザーデータがある場合、検索結果をセッションに保存しadminへ遷移
                
                ud.DTO2UDMapping(udd);
                //ログインしたユーザーの登録データを取得
                LinkedHashMap<String,UserDataDTO> registData = UserDataDAO.getInstance().loginDataSeach(udd);
                //登録データから登録先の情報と必要な情報のみにマッピング
                LinkedHashMap<String,RegistData> registDataRD = RegistData.getInstance().HMUDD2HMRDMappint(registData);

                session.setAttribute("RegistData", registDataRD);
                session.setAttribute("LoginData", ud);
                session.setAttribute("LoginDataUdd", udd);
                session.setAttribute("ac", (int) (Math.random() * 1000));
                request.getRequestDispatcher("/admin.jsp").forward(request, response);
                        
            }else if( udd.getDelFlg() == 1){
                //ユーザーデータが削除済みの場合、リクエストへ1を格納
                request.setAttribute("delFlg", "1");
            }
            //DBのユーザーデータと一致が無い場合、入力データをリクエストへ格納
            request.setAttribute("loginError", ud);
            request.setAttribute("reinput", "ReInput");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            
            
        } catch ( Exception e ){
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
