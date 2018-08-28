import Handyman.AdminMessaging;
import Login.Customer;
import Login.Handymen;
import Customer.HandyManMessaging;
import com.google.gson.Gson;
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
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
    Connection connection;
    Statement statement;
    ResultSet rs;
    Handymen handymen;
    Customer customer;

    List<HandyManMessaging> handyManMessagingList;
    List<Handymen> handymenList;
    JsonArray jsonArray;
    Gson gson;

    HttpSession httpSession;
    PrintWriter out;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out=response.getWriter();
        httpSession=request.getSession();
        customer=(Customer) httpSession.getAttribute("logged_customer");
        if(request.getParameter("customer_msg").equalsIgnoreCase("customer_msg")){
            String message=request.getParameter("message");
            int handyman_id=Integer.parseInt(request.getParameter("handyman_id"));
            //out.println(message);

            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                Date now=new Date();
                DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                statement=connection.createStatement();
                int i=statement.executeUpdate("INSERT INTO Customer_To_Handyman(sender_id, sender_name, recipient_id, recipient_name, message, date_sent, status)" +
                        "VALUES " +
                        "('"+customer.getCustid()+"','"+customer.getUsername()+"','"+handyman_id+"','-','"+message+"','"+dateFormat.format(now)+"','unread')");
                if(i==1){
                    rs=statement.executeQuery("SELECT * FROM Customer_To_Handyman WHERE sender_id='"+customer.getCustid()+"' AND recipient_id='"+handyman_id+"'");
                    while (rs.next()){
                        handyManMessagingList.add(new HandyManMessaging(rs.getInt(1),rs.getInt(2),rs.getInt(4),rs.getString(3),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
                    }
                    request.setAttribute("handyManMessagingList",handyManMessagingList);
                    request.setAttribute("handyman_id",handyman_id);
                    RequestDispatcher dispatcher=request.getRequestDispatcher(response.encodeURL("/CMessagesChatHandyman.jsp"));
                    dispatcher.forward(request,response);
                }
            }catch (Exception e){

            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration parameterNames=request.getParameterNames();
        String msgParameter="";
        String msgParameterValue="";
        out=response.getWriter();
        httpSession=request.getSession();
        customer=(Customer) httpSession.getAttribute("logged_customer");
        handyManMessagingList=new ArrayList<>();
        handymenList=new ArrayList<>();

        if(parameterNames.hasMoreElements()==true){
            while (parameterNames.hasMoreElements()){
                msgParameter=(String)parameterNames.nextElement();
                msgParameterValue=request.getParameter(msgParameter);
            }
        }

        if(msgParameter.equalsIgnoreCase("HandyMenList")){
            if(msgParameterValue.equalsIgnoreCase("ok")){
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    connection=DBConnection.getConnection();
                    statement=connection.createStatement();
                    rs=statement.executeQuery("SELECT * FROM Handymen");
                    while (rs.next()){
                        handymenList.add(new Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10)));
                    }
                    request.setAttribute("handymenList",handymenList);
                    RequestDispatcher dispatcher=request.getRequestDispatcher(response.encodeURL("/CMessages.jsp"));
                    dispatcher.forward(request,response);
                }catch (Exception e){

                }
            }
        }

        if(msgParameter.equalsIgnoreCase("Chat")){
            out.println(msgParameterValue);
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();
                rs=statement.executeQuery("SELECT * FROM Customer_To_Handyman WHERE sender_id='"+customer.getCustid()+"' AND recipient_id='"+msgParameterValue+"'");
                while (rs.next()){
                    handyManMessagingList.add(new HandyManMessaging(rs.getInt(1),rs.getInt(2),rs.getInt(4),rs.getString(3),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
                }
                request.setAttribute("handyManMessagingList",handyManMessagingList);
                request.setAttribute("handyman_id",msgParameterValue);
                RequestDispatcher dispatcher=request.getRequestDispatcher(response.encodeURL("/CMessagesChatHandyman.jsp"));
                dispatcher.forward(request,response);
            }catch (Exception e){

            }
            /*if(msgParameterValue.equalsIgnoreCase("ok")){

            }*/
        }
    }
}
