import Admin.Handymen;
import Login.Admin;
import Messages.AdminHandyman;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import db.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/AdminServlet2")
public class AdminServlet2 extends HttpServlet {
    Connection connection;
    Statement statement;
    ResultSet rs;
    AdminHandyman adminHandyman;

    List<AdminHandyman> adminHandymanList;
    List<Handymen> handymenList;
    JsonArray jsonArray;
    Gson gson;

    HttpSession httpSession;
    PrintWriter out;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         if(request.getParameter("admin_msg").equalsIgnoreCase("admin_msg")){
             out=response.getWriter();
            httpSession=request.getSession();
            Admin admin=(Admin)httpSession.getAttribute("logged_admin");
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection=DBConnection.getConnection();
                Statement statement=connection.createStatement();

                Date now=new Date();
                DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String message=request.getParameter("message");
                int handymanId=Integer.parseInt(request.getParameter("handyman_id"));
                int chatId=Integer.parseInt(request.getParameter("chatId"));

                //out.println(handymanId+" "+chatId);

                String handymanName="";

                rs=statement.executeQuery("SELECT * FROM Handymen WHERE handymanid='"+chatId+"'");
                while(rs.next()){
                    handymanName=rs.getString(8);
                }
                //
                int j=statement.executeUpdate("INSERT INTO Handyman_Admin_Messaging (message,date_sent,sender_type,sender_id,sender_name,recipient_id,recipient,status) " +
                        "VALUES " +
                        "   ('"+message+"','"+dateFormat.format(now)+"','admin','"+admin.getadminid()+"','"+admin.getFirstName()+" "+admin.getLastName()+"'" +
                        ",'"+chatId+"','"+handymanName+"','unread')");

                if(j==1){
                    out.println(chatId);
                }else{
                    out.println(j+" Failed");
                }
                //
            }catch (Exception e){
                out.println(e.getMessage()+"-<");
                e.printStackTrace();
            }
        }else if(request.getParameter("admin_msg").equalsIgnoreCase("to_customer")){
             out=response.getWriter();
             httpSession=request.getSession();
             Admin admin=(Admin)httpSession.getAttribute("logged_admin");
             try{
                 Class.forName("com.mysql.jdbc.Driver");
                 Connection connection=DBConnection.getConnection();
                 Statement statement=connection.createStatement();

                 Date now=new Date();
                 DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                 String message=request.getParameter("message");
                 int customerId=Integer.parseInt(request.getParameter("customer_id"));
                 int chatId=Integer.parseInt(request.getParameter("chatId"));

                 //out.println(handymanId+" "+chatId);

                 String customerName="";

                 rs=statement.executeQuery("SELECT * FROM Customers WHERE custid='"+chatId+"'");
                 while(rs.next()){
                     customerName=rs.getString(8);
                 }
                 //
                 int j=statement.executeUpdate("INSERT  INTO Admin_Customers_Messaging(sender_name, sender_id, recipient_name, recipient_id, message, date_sent, status, sender_type) " +
                         "VALUES " +
                         "   ('"+admin.getFirstName()+" "+admin.getLastName()+"','"+admin.getadminid()+"','"+customerName+"','"+chatId+"',' "+message+"'" +
                         ",'"+dateFormat.format(now)+"','unread','admin')");

                 if(j==1){
                     out.println(chatId);
                 }else{
                     out.println(j+" Failed");
                 }
                 //
             }catch (Exception e){
                 out.println(e.getMessage()+"-<");
                 e.printStackTrace();
             }
         }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
