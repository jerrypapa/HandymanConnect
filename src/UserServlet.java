import Customer.HandyManMessaging;
import Customer.AdminMessaging;
import Customer.Services;
import Handyman.CustomerMessaging;
import Login.Customer;
import Login.Handymen;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet rs;
    HttpSession httpSession;
    Customer customer;
    Handymen handymen;
    PrintWriter out;
    List<Services> servicesList;
    List<HandyManMessaging> handyManMessagingList;
    List<AdminMessaging> adminMessagingList;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*String message=request.getParameter("message"),send_msg=request.getParameter("send_msg"),h_name=request.getParameter("send_msg");
        int h_id=Integer.parseInt(request.getParameter("h_id"));
        String msgParameter="",msgParameterValue="";
        out.println("Msg: "+message);
        if(send_msg.equalsIgnoreCase("send_msg")){
            out.println(message+" "+send_msg+" "+h_name);
            /*try{
                i=0;
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();
                Date now=new Date();
                DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                i=statement.executeUpdate("INSERT INTO Handymen_Customer_Messaging(sender_name, sender_id, recipient_name, recipient_id, message, date_sent, status, sender_type)" +
                        "VALUES" +
                        " ('"+customer.getUsername()+"','"+customer.getCustid()+"','"+h_name+"','"+h_id+"','"+message+"','"+dateFormat.format(now)+"','unread','customer')");
                out.write(i);
            }catch (Exception e){

            }*/
        //}*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        servicesList=new ArrayList<>();
        handyManMessagingList=new ArrayList<>();
        adminMessagingList=new ArrayList<>();
        out=response.getWriter();
        httpSession=request.getSession();
        customer=(Customer)httpSession.getAttribute("logged_customer");
        Enumeration parameterNames=request.getParameterNames();
        String msgParameter="";
        String msgParameterValue="";
        String message="",h_name="",send_msg="";
        String change_username="",change_fname="",change_lname="",change_password="",change_email="",change_gender="",change_location="",current_password="",admin_msg;
        int change_phone=0;
        int h_id=0;
        int post_id=0,i=0;
        if(parameterNames.hasMoreElements()==true){
            while (parameterNames.hasMoreElements()){
                msgParameter=(String)parameterNames.nextElement();
                msgParameterValue=request.getParameter(msgParameter);
                if(msgParameter.equalsIgnoreCase("post_id")){
                    post_id=Integer.parseInt(msgParameterValue);
                }else if(msgParameter.equalsIgnoreCase("handyman_profile")){
                    h_id=Integer.parseInt(msgParameterValue);
                    //out.println("H_id:"+h_id);
                }else if(msgParameter.equalsIgnoreCase("message")){
                    message=msgParameterValue;
                    out.println("Message:"+message);
                    Date now=new Date();
                    DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    //out.println(msgParameter+" "+msgParameterValue);
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        connection = DBConnection.getConnection();
                        statement = connection.createStatement();
                        int j = statement.executeUpdate("INSERT  INTO Admin_Customers_Messaging(sender_name, sender_id, recipient_name, recipient_id, message, date_sent, status, sender_type)VALUES " +
                                "('"+customer.getUsername()+"','"+customer.getCustid()+"','papajerry','2','"+message+"','"+dateFormat.format(now)+"','unread','customer')");
                        rs = statement.executeQuery("SELECT * FROM Admin_Customers_Messaging WHERE sender_id='" + customer.getCustid() + "' AND sender_type='customer' OR recipient_id='" + customer.getCustid() + "' AND sender_type='admin'");
                        out.println(j);
                        while (rs.next()) {
                            adminMessagingList.add(new AdminMessaging(rs.getInt(1), rs.getInt(3), rs.getInt(5), rs.getString(2), rs.getString(4), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
                        }
                        request.setAttribute("adminMessages", adminMessagingList);
                        request.getServletContext().getRequestDispatcher(response.encodeURL("/UsersMessages.jsp")).forward(request,response);
                    }catch (Exception e){
                        e.printStackTrace();
                        out.println(e.getMessage());
                    }
                }else if(msgParameter.equalsIgnoreCase("admin_msg")){
                    admin_msg=msgParameterValue;
                    //out.println("Admin msg:"+admin_msg);
                }else if(msgParameter.equalsIgnoreCase("h_id")){
                    h_id=Integer.parseInt(msgParameterValue);
                    //out.println("H_id:"+h_id);
                }else if(msgParameter.equalsIgnoreCase("h_name")){
                    h_name=msgParameterValue;
                    //out.println("h_name:"+h_name);
                }else if(msgParameter.equalsIgnoreCase("change_username")){
                    change_username=msgParameterValue;
                    //out.println("Price: "+price);
                }else if(msgParameter.equalsIgnoreCase("change_fname")){
                    change_fname=msgParameterValue;
                    //out.println("Price: "+price);
                }else if(msgParameter.equalsIgnoreCase("change_lname")){
                    change_lname=msgParameterValue;
                    //out.println("Price: "+price);
                }else if(msgParameter.equalsIgnoreCase("change_email")){
                    change_email=msgParameterValue;
                    //out.println("Price: "+price);
                }else if(msgParameter.equalsIgnoreCase("change_password")){
                    change_password=msgParameterValue;
                    //out.println("Price: "+price);
                }else if(msgParameter.equalsIgnoreCase("change_phone")){
                    change_phone=Integer.parseInt(msgParameterValue);
                    //out.println("Price: "+price);
                }else if(msgParameter.equalsIgnoreCase("change_gender")){
                    change_gender=msgParameterValue;
                    //out.println("Price: "+price);
                }else if(msgParameter.equalsIgnoreCase("change_location")){
                    change_location=msgParameterValue;
                    //out.println("Price: "+price);
                }else if(msgParameter.equalsIgnoreCase("current_password")){
                    current_password=msgParameterValue;
                    //out.println("Price: "+price);
                }else if(msgParameter.equalsIgnoreCase("message")){
                    message=msgParameterValue;
                    //out.println("message:"+message);
                }else if(msgParameter.equalsIgnoreCase("send_msg")){
                    send_msg=msgParameterValue;
                    if(msgParameter.equalsIgnoreCase("send_msg")){
                        //out.println("Out-:"+send_msg);
            /*try{
                i=0;
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();
                Date now=new Date();
                DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                i=statement.executeUpdate("INSERT INTO Handymen_Customer_Messaging(sender_name, sender_id, recipient_name, recipient_id, message, date_sent, status, sender_type)" +
                        "VALUES" +
                        " ('"+customer.getUsername()+"','"+customer.getCustid()+"','"+h_name+"','"+h_id+"','"+message+"','"+dateFormat.format(now)+"','unread','customer')");
                out.write(i);
            }catch (Exception e){

            }*/
                    }
                }
            }
        }

        if(msgParameter.equalsIgnoreCase("change_username")){
            String fname="",lname="",email="",location="",gender="",password="",regdate="",soffered="",username="";
            int phone=0,cust_id=0;
            CustomerMessaging customerMessaging;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                cust_id=customer.getCustid();

                rs=statement.executeQuery("SELECT * FROM Handymen.Customers WHERE custid='"+cust_id+"'");
                while (rs.next()){
                    customer=new Customer(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                }

                preparedStatement=connection.prepareStatement("UPDATE Handymen.Customers SET Fname=?,Lname=?,username=?,custid=?,email=?,location=?,phoneno=?,password=?,gender=?,Regdate=? WHERE custid=?");
                preparedStatement.setString(1,customer.getFirstName());
                preparedStatement.setString(2,customer.getLastName());
                preparedStatement.setString(3,change_username);
                preparedStatement.setString(4,Integer.toString(customer.getCustid()));
                preparedStatement.setString(5,customer.getEmail());
                preparedStatement.setString(6,customer.getLocation());
                preparedStatement.setString(7,Integer.toString(customer.getPhoneNo()));
                preparedStatement.setString(8,customer.getPassword());
                preparedStatement.setString(9,customer.getGender());
                preparedStatement.setString(10,customer.getDateRegistered());
                preparedStatement.setString(11,Integer.toString(cust_id));
                int k=0;
                k=preparedStatement.executeUpdate();
                //out.println("username: "+k);
                if(k==1){
                    rs=statement.executeQuery("SELECT * FROM Handymen.Customers WHERE custid='"+customer.getCustid()+"'");
                    while (rs.next()){
                        customer=new Customer(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                    }

                    request.setAttribute("customer_details",customer);
                    request.getServletContext().getRequestDispatcher(response.encodeURL("/UserProfile.jsp")).forward(request,response);

                }
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }

        if(msgParameter.equalsIgnoreCase("change_fname")){
            String fname="",lname="",email="",location="",gender="",password="",regdate="",soffered="",username="";
            int phone=0,cust_id=0;
            CustomerMessaging customerMessaging;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                cust_id=customer.getCustid();

                rs=statement.executeQuery("SELECT * FROM Handymen.Customers WHERE custid='"+cust_id+"'");
                while (rs.next()){
                    customer=new Customer(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                }

                preparedStatement=connection.prepareStatement("UPDATE Handymen.Customers SET Fname=?,Lname=?,username=?,custid=?,email=?,location=?,phoneno=?,password=?,gender=?,Regdate=? WHERE custid=?");
                preparedStatement.setString(1,change_fname);
                preparedStatement.setString(2,customer.getLastName());
                preparedStatement.setString(3,customer.getUsername());
                preparedStatement.setString(4,Integer.toString(customer.getCustid()));
                preparedStatement.setString(5,customer.getEmail());
                preparedStatement.setString(6,customer.getLocation());
                preparedStatement.setString(7,Integer.toString(customer.getPhoneNo()));
                preparedStatement.setString(8,customer.getPassword());
                preparedStatement.setString(9,customer.getGender());
                preparedStatement.setString(10,customer.getDateRegistered());
                preparedStatement.setString(11,Integer.toString(cust_id));
                int k=0;
                k=preparedStatement.executeUpdate();
                //out.println("username: "+k);
                if(k==1){
                    rs=statement.executeQuery("SELECT * FROM Handymen.Customers WHERE custid='"+customer.getCustid()+"'");
                    while (rs.next()){
                        customer=new Customer(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                    }

                    request.setAttribute("customer_details",customer);
                    request.getServletContext().getRequestDispatcher(response.encodeURL("/UserProfile.jsp")).forward(request,response);

                }
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }

        if(msgParameter.equalsIgnoreCase("change_lname")){
            String fname="",lname="",email="",location="",gender="",password="",regdate="",soffered="",username="";
            int phone=0,cust_id=0;
            CustomerMessaging customerMessaging;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                cust_id=customer.getCustid();

                rs=statement.executeQuery("SELECT * FROM Handymen.Customers WHERE custid='"+cust_id+"'");
                while (rs.next()){
                    customer=new Customer(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                }

                preparedStatement=connection.prepareStatement("UPDATE Handymen.Customers SET Fname=?,Lname=?,username=?,custid=?,email=?,location=?,phoneno=?,password=?,gender=?,Regdate=? WHERE custid=?");
                preparedStatement.setString(1,customer.getFirstName());
                preparedStatement.setString(2,change_lname);
                preparedStatement.setString(3,customer.getUsername());
                preparedStatement.setString(4,Integer.toString(customer.getCustid()));
                preparedStatement.setString(5,customer.getEmail());
                preparedStatement.setString(6,customer.getLocation());
                preparedStatement.setString(7,Integer.toString(customer.getPhoneNo()));
                preparedStatement.setString(8,customer.getPassword());
                preparedStatement.setString(9,customer.getGender());
                preparedStatement.setString(10,customer.getDateRegistered());
                preparedStatement.setString(11,Integer.toString(cust_id));
                int k=0;
                k=preparedStatement.executeUpdate();
                //out.println("username: "+k);
                if(k==1){
                    rs=statement.executeQuery("SELECT * FROM Handymen.Customers WHERE custid='"+customer.getCustid()+"'");
                    while (rs.next()){
                        customer=new Customer(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                    }

                    request.setAttribute("customer_details",customer);
                    request.getServletContext().getRequestDispatcher(response.encodeURL("/UserProfile.jsp")).forward(request,response);

                }
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }

        if(msgParameter.equalsIgnoreCase("change_email")){
            String fname="",lname="",email="",location="",gender="",password="",regdate="",soffered="",username="";
            int phone=0,cust_id=0;
            CustomerMessaging customerMessaging;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                cust_id=customer.getCustid();

                rs=statement.executeQuery("SELECT * FROM Handymen.Customers WHERE custid='"+cust_id+"'");
                while (rs.next()){
                    customer=new Customer(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                }

                preparedStatement=connection.prepareStatement("UPDATE Handymen.Customers SET Fname=?,Lname=?,username=?,custid=?,email=?,location=?,phoneno=?,password=?,gender=?,Regdate=? WHERE custid=?");
                preparedStatement.setString(1,customer.getFirstName());
                preparedStatement.setString(2,customer.getLastName());
                preparedStatement.setString(3,customer.getUsername());
                preparedStatement.setString(4,Integer.toString(customer.getCustid()));
                preparedStatement.setString(5,change_email);
                preparedStatement.setString(6,customer.getLocation());
                preparedStatement.setString(7,Integer.toString(customer.getPhoneNo()));
                preparedStatement.setString(8,customer.getPassword());
                preparedStatement.setString(9,customer.getGender());
                preparedStatement.setString(10,customer.getDateRegistered());
                preparedStatement.setString(11,Integer.toString(cust_id));
                int k=0;
                k=preparedStatement.executeUpdate();
                //out.println("username: "+k);
                if(k==1){
                    rs=statement.executeQuery("SELECT * FROM Handymen.Customers WHERE custid='"+customer.getCustid()+"'");
                    while (rs.next()){
                        customer=new Customer(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                    }

                    request.setAttribute("customer_details",customer);
                    request.getServletContext().getRequestDispatcher(response.encodeURL("/UserProfile.jsp")).forward(request,response);

                }
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }

        if(msgParameter.equalsIgnoreCase("change_phone")){
            String fname="",lname="",email="",location="",gender="",password="",regdate="",soffered="",username="";
            int phone=0,cust_id=0;
            CustomerMessaging customerMessaging;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                cust_id=customer.getCustid();

                rs=statement.executeQuery("SELECT * FROM Handymen.Customers WHERE custid='"+cust_id+"'");
                while (rs.next()){
                    customer=new Customer(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                }

                preparedStatement=connection.prepareStatement("UPDATE Handymen.Customers SET Fname=?,Lname=?,username=?,custid=?,email=?,location=?,phoneno=?,password=?,gender=?,Regdate=? WHERE custid=?");
                preparedStatement.setString(1,customer.getFirstName());
                preparedStatement.setString(2,customer.getLastName());
                preparedStatement.setString(3,customer.getUsername());
                preparedStatement.setString(4,Integer.toString(customer.getCustid()));
                preparedStatement.setString(5,customer.getEmail());
                preparedStatement.setString(6,customer.getLocation());
                preparedStatement.setString(7,Integer.toString(change_phone));
                preparedStatement.setString(8,customer.getPassword());
                preparedStatement.setString(9,customer.getGender());
                preparedStatement.setString(10,customer.getDateRegistered());
                preparedStatement.setString(11,Integer.toString(cust_id));
                int k=0;
                k=preparedStatement.executeUpdate();
                //out.println("username: "+k);
                if(k==1){
                    rs=statement.executeQuery("SELECT * FROM Handymen.Customers WHERE custid='"+customer.getCustid()+"'");
                    while (rs.next()){
                        customer=new Customer(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                    }

                    request.setAttribute("customer_details",customer);
                    request.getServletContext().getRequestDispatcher(response.encodeURL("/UserProfile.jsp")).forward(request,response);

                }
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }

        if(msgParameter.equalsIgnoreCase("change_gender")){
            String fname="",lname="",email="",location="",gender="",password="",regdate="",soffered="",username="";
            int phone=0,cust_id=0;
            CustomerMessaging customerMessaging;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                cust_id=customer.getCustid();

                rs=statement.executeQuery("SELECT * FROM Handymen.Customers WHERE custid='"+cust_id+"'");
                while (rs.next()){
                    customer=new Customer(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                }

                preparedStatement=connection.prepareStatement("UPDATE Handymen.Customers SET Fname=?,Lname=?,username=?,custid=?,email=?,location=?,phoneno=?,password=?,gender=?,Regdate=? WHERE custid=?");
                preparedStatement.setString(1,customer.getFirstName());
                preparedStatement.setString(2,customer.getLastName());
                preparedStatement.setString(3,customer.getUsername());
                preparedStatement.setString(4,Integer.toString(customer.getCustid()));
                preparedStatement.setString(5,customer.getEmail());
                preparedStatement.setString(6,customer.getLocation());
                preparedStatement.setString(7,Integer.toString(customer.getPhoneNo()));
                preparedStatement.setString(8,customer.getPassword());
                preparedStatement.setString(9,change_gender);
                preparedStatement.setString(10,customer.getDateRegistered());
                preparedStatement.setString(11,Integer.toString(cust_id));
                int k=0;
                k=preparedStatement.executeUpdate();
                //out.println("username: "+k);
                if(k==1){
                    rs=statement.executeQuery("SELECT * FROM Handymen.Customers WHERE custid='"+customer.getCustid()+"'");
                    while (rs.next()){
                        customer=new Customer(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                    }

                    request.setAttribute("customer_details",customer);
                    request.getServletContext().getRequestDispatcher(response.encodeURL("/UserProfile.jsp")).forward(request,response);

                }
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }

        if(msgParameter.equalsIgnoreCase("change_password")){
            int cust_id=0;
            CustomerMessaging customerMessaging;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                rs=statement.executeQuery("SELECT * FROM Handymen.Customers WHERE custid='"+customer.getCustid()+"' AND password='"+current_password+"'");
                int count=0;

                while(rs.next()){
                    customer=new Customer(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                    count++;
                }

                if(count>0){
                    cust_id=customer.getCustid();

                    rs=statement.executeQuery("SELECT * FROM Handymen.Customers WHERE custid='"+cust_id+"'");
                    while (rs.next()){
                        customer=new Customer(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                    }
                    preparedStatement=connection.prepareStatement("UPDATE Handymen.Customers SET Fname=?,Lname=?,username=?,custid=?,email=?,location=?,phoneno=?,password=?,gender=?,Regdate=? WHERE custid=?");
                    preparedStatement.setString(1,customer.getFirstName());
                    preparedStatement.setString(2,customer.getLastName());
                    preparedStatement.setString(3,customer.getUsername());
                    preparedStatement.setString(4,Integer.toString(customer.getCustid()));
                    preparedStatement.setString(5,customer.getEmail());
                    preparedStatement.setString(6,customer.getLocation());
                    preparedStatement.setString(7,Integer.toString(customer.getPhoneNo()));
                    preparedStatement.setString(8,change_password);
                    preparedStatement.setString(9,customer.getGender());
                    preparedStatement.setString(10,customer.getDateRegistered());
                    preparedStatement.setString(11,Integer.toString(cust_id));
                    int k=0;
                    k=preparedStatement.executeUpdate();
                    //out.println("password:"+k);
                    if(k==1){
                        httpSession.invalidate();
                        request.getServletContext().getRequestDispatcher("/UserProfile.jsp").forward(request,response);
                    }
                }else{
                    out.println("Check your currrent password and retry!");
                }
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }

        if(msgParameter.equalsIgnoreCase("Profile")){
            //out.println("Out-2:"+message+" "+send_msg+" "+h_name+" "+h_id);
            try{
                i=0;
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                rs=statement.executeQuery("SELECT * FROM Handymen.Customers WHERE custid='"+customer.getCustid()+"'");
                while (rs.next()){
                    customer=new Customer(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                }

                request.setAttribute("customer_details",customer);
                request.getServletContext().getRequestDispatcher(response.encodeURL("/UserProfile.jsp")).forward(request,response);
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }else if(msgParameter.equalsIgnoreCase("send_msg")){
            //out.println("Out-2:"+message+" "+send_msg+" "+h_name+" "+h_id);
            try{
                i=0;
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();
                Date now=new Date();
                DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                i=statement.executeUpdate("INSERT INTO Handymen_Customer_Messaging(sender_name, sender_id, recipient_name, recipient_id, message, date_sent, sender_type, status)" +
                        "VALUES" +
                        " ('"+customer.getUsername()+"','"+customer.getCustid()+"','"+h_name+"','"+h_id+"','"+message+"','"+dateFormat.format(now)+"','customer','unread')");
                //out.println(i);
                if(i==1){
                    rs=statement.executeQuery("SELECT * FROM Items_by_handymen WHERE id='"+post_id+"'");
                    while (rs.next()){
                        h_id=Integer.parseInt(rs.getString(4));
                        servicesList.add(new Services(rs.getInt(3),rs.getInt(4),rs.getDouble(6),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(5),rs.getString(8),rs.getString(9)));
                    }
                    rs=statement.executeQuery("SELECT * FROM Handymen_Customer_Messaging WHERE recipient_id='"+h_id+"' AND sender_id='"+customer.getCustid()+"' AND sender_type='customer' OR sender_id='"+h_id+"' AND recipient_id='"+customer.getCustid()+"' AND sender_type='handyman'");
                    while(rs.next()){
                        handyManMessagingList.add(new HandyManMessaging(rs.getInt(1),rs.getInt(3),rs.getInt(5),rs.getString(2),rs.getString(4),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
                    }
                    request.setAttribute("servicesList",servicesList);
                    request.setAttribute("handyManMessagingList",handyManMessagingList);
                    request.getServletContext().getRequestDispatcher(response.encodeURL("/UserViewPost.jsp")).forward(request,response);
                    //out.println(message);
                    /*if(post_id==0){
                        rs=statement.executeQuery("SELECT * FROM Items_by_handymen WHERE hid='"+h_id+"'");
                        while (rs.next()){
                            h_id=Integer.parseInt(rs.getString(4));
                            h_name=rs.getString(5);
                            servicesList.add(new Services(rs.getInt(3),rs.getInt(4),rs.getDouble(6),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(5),rs.getString(8),rs.getString(9)));
                        }
                        rs=statement.executeQuery("SELECT * FROM Handymen_Customer_Messaging WHERE recipient_id='"+h_id+"' AND sender_id='"+customer.getCustid()+"' AND sender_type='customer' OR sender_id='"+h_id+"' AND recipient_id='"+customer.getCustid()+"' AND sender_type='handyman'");
                        while(rs.next()){
                            handyManMessagingList.add(new HandyManMessaging(rs.getInt(1),rs.getInt(3),rs.getInt(5),rs.getString(2),rs.getString(4),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
                        }

                        request.setAttribute("servicesList",servicesList);
                        request.setAttribute("handyManMessagingList",handyManMessagingList);

                        request.getServletContext().getRequestDispatcher(response.encodeURL("/UserViewHandymanProfile.jsp")).forward(request,response);
                    }else if(post_id>0){
                        rs=statement.executeQuery("SELECT * FROM Items_by_handymen WHERE id='"+post_id+"'");
                        while (rs.next()){
                            h_id=Integer.parseInt(rs.getString(4));
                            servicesList.add(new Services(rs.getInt(3),rs.getInt(4),rs.getDouble(6),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(5),rs.getString(8),rs.getString(9)));
                        }
                        rs=statement.executeQuery("SELECT * FROM Handymen_Customer_Messaging WHERE recipient_id='"+h_id+"' AND sender_id='"+customer.getCustid()+"' AND sender_type='customer' OR sender_id='"+h_id+"' AND recipient_id='"+customer.getCustid()+"' AND sender_type='handyman'");
                        while(rs.next()){
                            handyManMessagingList.add(new HandyManMessaging(rs.getInt(1),rs.getInt(3),rs.getInt(5),rs.getString(2),rs.getString(4),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
                        }
                        request.setAttribute("servicesList",servicesList);
                        request.setAttribute("handyManMessagingList",handyManMessagingList);
                        request.getServletContext().getRequestDispatcher(response.encodeURL("/UserViewPost.jsp")).forward(request,response);
                    }*/
                }
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }else if(msgParameter.equalsIgnoreCase("post_id")){
            int handyman_id=0;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();
                rs=statement.executeQuery("SELECT * FROM Items_by_handymen WHERE id='"+post_id+"'");
                while (rs.next()){
                    handyman_id=Integer.parseInt(rs.getString(4));
                    servicesList.add(new Services(rs.getInt(3),rs.getInt(4),rs.getDouble(6),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(5),rs.getString(8),rs.getString(9)));
                }
                rs=statement.executeQuery("SELECT * FROM Handymen_Customer_Messaging WHERE recipient_id='"+handyman_id+"' AND sender_id='"+customer.getCustid()+"' AND sender_type='customer' OR sender_id='"+handyman_id+"' AND recipient_id='"+customer.getCustid()+"' AND sender_type='handyman'");
                while(rs.next()){
                    handyManMessagingList.add(new HandyManMessaging(rs.getInt(1),rs.getInt(3),rs.getInt(5),rs.getString(2),rs.getString(4),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
                }
                request.setAttribute("servicesList",servicesList);
                request.setAttribute("handyManMessagingList",handyManMessagingList);
            }catch (Exception e){
                e.printStackTrace();
                out.write(e.getMessage());
            }
            //out.println(servicesList.size());
            request.getServletContext().getRequestDispatcher(response.encodeURL("/UserViewPost.jsp")).forward(request,response);
        }else if(msgParameter.equalsIgnoreCase("handyman_profile")){
            int handyman_id=0;
            String handyman_name="";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                rs=statement.executeQuery("SELECT * FROM Items_by_handymen WHERE hid='"+h_id+"'");
                while (rs.next()){
                    handyman_id=Integer.parseInt(rs.getString(4));
                    handyman_name=rs.getString(5);
                    servicesList.add(new Services(rs.getInt(3),rs.getInt(4),rs.getDouble(6),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(5),rs.getString(8),rs.getString(9)));
                }
                rs=statement.executeQuery("SELECT * FROM Handymen_Customer_Messaging WHERE recipient_id='"+handyman_id+"' AND sender_id='"+customer.getCustid()+"' AND sender_type='customer' OR sender_id='"+handyman_id+"' AND recipient_id='"+customer.getCustid()+"' AND sender_type='handyman'");
                while(rs.next()){
                    handyManMessagingList.add(new HandyManMessaging(rs.getInt(1),rs.getInt(3),rs.getInt(5),rs.getString(2),rs.getString(4),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
                }

                request.setAttribute("servicesList",servicesList);
                request.setAttribute("handyManMessagingList",handyManMessagingList);
                //request.setAttribute("handyman_details",handymen);
            }catch (Exception e){
                e.printStackTrace();
                out.write(e.getMessage());
            }
            //out.println(servicesList.size()+" "+h_id+" "+handyman_id);
            request.getServletContext().getRequestDispatcher(response.encodeURL("/UserViewHandymanProfile.jsp")).forward(request,response);
        }/*else if(msgParameter.equalsIgnoreCase("send_msg")){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

            }catch (Exception e){

            }
            request.getServletContext().getRequestDispatcher(response.encodeURL("/UserViewPost.jsp")).forward(request,response);
        }*/else if(msgParameter.equalsIgnoreCase("Posts")){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();
                rs=statement.executeQuery("SELECT * FROM Items_by_handymen ORDER BY RAND()");
                while (rs.next()){
                    servicesList.add(new Services(rs.getInt(3),rs.getInt(4),rs.getDouble(6),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(5),rs.getString(8),rs.getString(9)));
                }
                request.setAttribute("servicesList",servicesList);
            }catch (Exception e){
                e.printStackTrace();
                out.write(e.getMessage());
            }
            request.getServletContext().getRequestDispatcher(response.encodeURL("/User.jsp")).forward(request,response);
        }else if(msgParameter.equalsIgnoreCase("Messages")){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();
                rs=statement.executeQuery("SELECT * FROM Admin_Customers_Messaging WHERE sender_id='"+customer.getCustid()+"' AND sender_type='customer' OR recipient_id='"+customer.getCustid()+"' AND sender_type='admin'");
                while (rs.next()){
                    adminMessagingList.add(new AdminMessaging(rs.getInt(1),rs.getInt(3),rs.getInt(5),rs.getString(2),rs.getString(4),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
                }
                request.setAttribute("adminMessages",adminMessagingList);
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
            request.getServletContext().getRequestDispatcher(response.encodeURL("/UsersMessages.jsp")).forward(request,response);
        }
    }
}