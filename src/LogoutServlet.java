import Login.Handymen;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    HttpSession httpSession;
    Enumeration userTypes;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        httpSession=request.getSession();
        Handymen handymen=(Handymen)request.getSession().getAttribute("logged_handyman");
        String userType="";
        String userTypeValue="";
        userTypes=request.getParameterNames();
        if(userTypes.hasMoreElements()==true){
            while (userTypes.hasMoreElements()){
                userType=(String)userTypes.nextElement();
                userTypeValue=request.getParameter(userType);
            }
        }

        //response.getWriter().println(userType+" "+userTypeValue);
        if(httpSession!=null){
            httpSession.invalidate();
            //response.sendRedirect("/HDashboard.jsp");
            RequestDispatcher requestDispatcher=getServletContext().getRequestDispatcher("/HDashboard.jsp");
            requestDispatcher.forward(request,response);
        }else{
            response.getWriter().println("Not in session");
        }



        /*if(userTypeValue=="handyman"){
            //
            //httpSession.invalidate();
            response.getWriter().println(handymen.getUsername());
        }else if(userTypeValue=="admin"){
            httpSession.removeAttribute("logged_admin");
        }else if(userTypeValue=="customer"){
            httpSession.removeAttribute("logged_customer");
        }*/

        //response.sendRedirect("homepage/Homepage2.html");
        //httpSession.removeAttribute("logged_handyman");
    }
}
