import Admin.Handymen;
import Login.Admin;
import Admin.Customers;
import Messages.AdminCustomer;
import Messages.AdminHandyman;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import db.DBConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/ServletAdmin")
public class ServletAdmin extends HttpServlet {

    Connection connection;
    Statement statement;
    ResultSet rs;
    AdminHandyman adminHandyman;
    AdminCustomer adminCustomer;

    List<AdminHandyman> adminHandymanList;
    List<AdminCustomer> adminCustomerList;
    List<Customers> customersList;
    List<Handymen> handymenList;
    JsonArray jsonArray;
    Gson gson;

    HttpSession httpSession;

    class EmptyMsgs{
        private String message;

        public EmptyMsgs(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        adminHandymanList=new ArrayList<>();
        adminCustomerList=new ArrayList<>();
        if(request.getParameter("check_new_msgs").equalsIgnoreCase("ok")){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection=DBConnection.getConnection();
                Statement statement=connection.createStatement();
                ResultSet rs=statement.executeQuery("SELECT * FROM  Handyman_Admin_Messaging WHERE sender_type='handyman' AND status='unread'");
                int i=0;
                while(rs.next()){
                    adminHandymanList.add(new AdminHandyman(rs.getInt(1),rs.getInt(3),rs.getInt(4),rs.getString(2),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
                    i++;
                }
                gson=new GsonBuilder().create();
                jsonArray=gson.toJsonTree(adminHandymanList).getAsJsonArray();

                List<EmptyMsgs> emptyMsgs=new ArrayList<>();
                if(i>0){
                    response.getWriter().write(jsonArray.toString());
                }else if(i==0){
                    emptyMsgs.add(new EmptyMsgs("empty"));
                    jsonArray=gson.toJsonTree(adminHandymanList).getAsJsonArray();
                    response.getWriter().write("{messages:"+jsonArray.toString()+"}");
                }
                //response.getWriter().write("Received "+i+" "+jsonArray.toString());
            }catch (Exception e){
                response.getWriter().write(e.getMessage());
                e.printStackTrace();
            }

        }else if(request.getParameter("check_new_msgs").equalsIgnoreCase("customers")){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection=DBConnection.getConnection();
                Statement statement=connection.createStatement();
                ResultSet rs=statement.executeQuery("SELECT * FROM  Admin_Customers_Messaging WHERE sender_type='customer' AND status='unread'");
                int i=0;
                while(rs.next()){
                    adminCustomerList.add(new AdminCustomer(rs.getInt(1),rs.getInt(3),rs.getInt(5),rs.getString(2),rs.getString(4),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
                    i++;
                }
                gson=new GsonBuilder().create();
                jsonArray=gson.toJsonTree(adminCustomerList).getAsJsonArray();

                List<EmptyMsgs> emptyMsgs=new ArrayList<>();
                if(i>0){
                    response.getWriter().write(jsonArray.toString());
                }else if(i==0){
                    /*emptyMsgs.add(new EmptyMsgs("empty"));
                    jsonArray=gson.toJsonTree(adminCustomerList).getAsJsonArray();
                    response.getWriter().write("{messages:"+jsonArray.toString()+"}");*/
                }
                //response.getWriter().write("Received "+i+" "+jsonArray.toString());
            }catch (Exception e){
                response.getWriter().write(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
