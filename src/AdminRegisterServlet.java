import db.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import static java.lang.System.out;

@WebServlet("/AdminRegisterServlet")
public class AdminRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstname=request.getParameter("firstname");
        String lastname=request.getParameter("lastname");
        String email=request.getParameter("email");
        String address=request.getParameter("address");
        String contact=request.getParameter("phoneno");
        String password=request.getParameter("password");

        PrintWriter out=response.getWriter();
        out.println(firstname+" "+lastname+" "+email+" "+address+" "+contact+" "+password);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DBConnection.getConnection();

            Statement st=conn.createStatement();
//            int i=st.executeUpdate("INSERT  INTO Admin(Fname,Lname,email,address,phonenumber,password)VALUES("+firstname+","+lastname+","+email+","+address+","+contact+","+password+")");
            int i=st.executeUpdate("INSERT  INTO Admin(Fname,Lname,email,address,phonenumber,password)VALUES('"+firstname+"','"+lastname+"','"+email+"','"+address+"','"+contact+"','"+password+"')");
            /*PreparedStatement st=conn.prepareStatement("INSERT  INTO Admin(Fname,Lname,email,address,phonenumber,password)VALUES(?,?,?,?,?,?");
            st.setString(1,firstname);
            st.setString(2,lastname);
            st.setString(3,email);
            st.setString(4,address);
            st.setString(5,contact);
            st.setString(6,password);*/
            //int i=st.executeUpdate();
            out.println(i);

        }catch (Exception e){
            out.println(e.getMessage());
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
