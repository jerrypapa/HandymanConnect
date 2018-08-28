import db.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/CustomerRegisterServlet")
public class CustomerRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstname=request.getParameter("first_name");
        String lastname=request.getParameter("last_name");
        String email=request.getParameter("email");
        String contact=request.getParameter("contact_no");
        String password=request.getParameter("user_password");
        String gender=request.getParameter("gender");
        String location=request.getParameter("location");
        String username=request.getParameter("user_name");
        Date now=new Date();
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        PrintWriter out=response.getWriter();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DBConnection.getConnection();

            Statement st=conn.createStatement();
            int i=st.executeUpdate("INSERT  INTO Customers(Fname,Lname,email,location,phoneno,gender,username,password,RegDate)" +
                    "VALUES" +
                    "('"+firstname+"','"+lastname+"','"+email+"','"+ location+"','"+contact+"','"+gender+"','"+username+"','"+password+"','"+dateFormat.format(now)+"')");

            out.println(i);

        }catch (Exception e){
            out.println(e.getMessage());
        }

    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
