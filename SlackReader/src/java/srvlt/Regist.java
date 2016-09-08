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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author You
 */
public class Regist extends HttpServlet {

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
            //入力されたURLを取得
            String url = (String)request.getParameter("registUrl");
            HashMap<String,RegistData> registDataRD = (HashMap<String,RegistData>)session.getAttribute("RegistData");
            if( url.length() > 4 &&
                    (url.substring(0,4).equals("http"))){
                boolean chk = true;
                for(Map.Entry<String,RegistData> val : registDataRD.entrySet()){
                    if(val.getValue().getRegistUrl().equals(url)){
                        chk = false;
                        break;
                    }
                }
                if(chk){
                    //ログインしているユーザーデータを取得
                    UserData ud = (UserData)session.getAttribute("LoginData");
                    //DTO形式にマッピング
                    UserDataDTO udd = new UserDataDTO();
                    ud.UD2DTOMapping(udd);
                    //マッピングしたユーザーデータに入力されたURLを追加
                    udd.setRegistUrl(url);
                    //DBのregist_tにURLを追加
                    UserDataDAO.getInstance().registInsert(udd);
//                    HashMap<String,UserDataDTO> registData = UserDataDAO.getInstance().loginDataSeach(udd);
//
//                    //登録データから登録先の情報と必要な情報のみにマッピング
//                    registDataRD = RegistData.getInstance().HMUDD2HMRDMappint(registData);
//                    session.setAttribute("RegistData", registDataRD);
                }else{
                    request.setAttribute("chk","既に登録されているサイトです。");
                }
            }else if(url.substring(url.length()-4).equals("atom") ){
                request.setAttribute("chk", "atomは対応していません。");
            }else{
                request.setAttribute("chk","有効なURLではありません。");
            }
            request.setAttribute("regdel", "reg");
            request.getRequestDispatcher("Admin").forward(request, response);
        }catch(Exception e){
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
