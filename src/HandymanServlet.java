import Customer.HandyManMessaging;
import Handyman.AdminMessaging;
import Handyman.CustomerMessaging;
import Handyman.Posts;
import Login.Admin;
import Login.Handymen;
import Messages.AdminCustomer;
import Messages.AdminHandyman;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import db.DBConnection;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
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

@WebServlet("/HandymanServlet")
public class HandymanServlet extends HttpServlet {
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet rs,rs2;
    Handymen handymen;
    Admin admin;
    AdminMessaging adminMessaging;

    List<AdminHandyman> adminHandymanList;
    List<AdminCustomer> adminCustomerList;
    List<Handymen> handymenList;
    List<AdminMessaging> adminMessagingList;
    List<CustomerMessaging> customerMessagingList;
    JsonArray jsonArray;
    Gson gson;

    HttpSession httpSession;
    PrintWriter out;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("handyman_msg").equalsIgnoreCase("handyman_msg")){
            String message=request.getParameter("message");
            String handyman_id=request.getParameter("handyman_id");
            //out.println(message);
            handymen=(Handymen)httpSession.getAttribute("logged_handyman");
            adminMessagingList=new ArrayList<>();

            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();
                Date now=new Date();
                DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                int i=statement.executeUpdate("INSERT INTO Handyman_Admin_Messaging(sender_name, sender_id, recipient_id, recipient, message, date_sent, status, sender_type)" +
                        "VALUES ('"+handymen.getUsername()+"','"+handyman_id+"','2','Jerry Papa','"+message+"','"+dateFormat.format(now)+"','unread','handyman')");
                //out.println(i);
                if(i==1){
                    rs=statement.executeQuery("SELECT * FROM Handyman_Admin_Messaging WHERE sender_id='"+handymen.getHandymanid()+"' AND sender_type='handyman' OR recipient_id='"+handymen.getHandymanid()+"' AND sender_type='admin'");
                    while (rs.next()){
                        adminMessagingList.add(new AdminMessaging(rs.getInt(1),rs.getInt(3),rs.getInt(4),rs.getString(2),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
                    }
                    request.setAttribute("adminMessagingList",adminMessagingList);
                    RequestDispatcher dispatcher=request.getRequestDispatcher(response.encodeURL("/HMessages.jsp"));
                    dispatcher.forward(request,response);
                }
            }catch (Exception e){
                out.println(e.getMessage());
            }
        }else if(request.getParameter("service_details").equalsIgnoreCase("service_details")){
            out.println("Service_details");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        customerMessagingList=new ArrayList<>();
        Enumeration parameterNames=request.getParameterNames();
        String msgParameter="";
        String msgParameterValue="";
        String category="";
        String name="";
        String description="";
        String update_category="",update_name="",update_price="",update_description="";
        double price=0.0;
        String service_details="";
        int post_id=0;

        String change_username="",change_fname="",change_lname="",change_password="",change_email="",change_gender="",change_location="",current_password="",customersendername="",customer_message="",customer_sent_name="";
        int change_phone=0,customerid=0;

        out=response.getWriter();
        httpSession=request.getSession();
        handymen=(Handymen)httpSession.getAttribute("logged_handyman");
        adminMessagingList=new ArrayList<>();
        if(parameterNames.hasMoreElements()==true){
            while (parameterNames.hasMoreElements()){
                msgParameter=(String)parameterNames.nextElement();
                msgParameterValue=request.getParameter(msgParameter);
                if(msgParameter.equalsIgnoreCase("category")){
                    category=msgParameterValue;
                    //out.println("Category: "+category);
                }else if(msgParameter.equalsIgnoreCase("price")){
                    price=Double.parseDouble(msgParameterValue);
                    //out.println("Price: "+price);
                }else if(msgParameter.equalsIgnoreCase("name")){
                    name=msgParameterValue;
                    //out.println("Name: "+name);
                }else if(msgParameter.equalsIgnoreCase("description")){
                    description=msgParameterValue;
                    //out.println("Description: "+description);
                }else if(msgParameter.equalsIgnoreCase("update_category")){
                    update_category=msgParameterValue;
                }else if(msgParameter.equalsIgnoreCase("post_id")){
                    post_id=Integer.parseInt(msgParameterValue);
                }else if(msgParameter.equalsIgnoreCase("update_name")){
                    update_name=msgParameterValue;
                }else if(msgParameter.equalsIgnoreCase("update_price")){
                    update_price=msgParameterValue;
                }else if(msgParameter.equalsIgnoreCase("update_description")){
                    update_description=msgParameterValue;
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
                }else if(msgParameter.equalsIgnoreCase("customermsg")){
                    customersendername=msgParameterValue;
                    //out.println("Price: "+price);
                }else if(msgParameter.equalsIgnoreCase("customer_id")){
                    customerid=Integer.parseInt(msgParameterValue);
                    //out.println("Price: "+price);
                }else if(msgParameter.equalsIgnoreCase("customer_sent_name")){
                    customer_sent_name=msgParameterValue;
                    //out.println("Price: "+price);
                }else if(msgParameter.equalsIgnoreCase("customer_message")){
                    customer_message=msgParameterValue;
                    //out.println("Price: "+price);
                }else if(msgParameter.equalsIgnoreCase("service_details")){
                    service_details=msgParameterValue;
                    String image_url=(String)httpSession.getAttribute("image_url");

                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        connection=DBConnection.getConnection();
                        statement=connection.createStatement();
                        Date now=new Date();
                        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        out.println("Image url 2: "+image_url);
                        int i=statement.executeUpdate("INSERT INTO Items_by_handymen(category, name, hid, hname, price, Post_date, image_url,description)" +
                                "VALUES" +
                                " ('"+category+"','"+name+"','"+handymen.getHandymanid()+"','"+handymen.getUsername()+"','"+price+"','"+dateFormat.format(now)+"','"+image_url+"','"+description+"')");
                        if(i==1){
                            request.removeAttribute("image_url");
                            httpSession.removeAttribute("image_url");
                            request.getServletContext().getRequestDispatcher(response.encodeURL("/HPostItem.jsp")).forward(request,response);
                        }else{
                            out.println("Fail");
                        }
                    }catch (Exception e){
                        out.println(e.getMessage());
                    }

                }
            }

            if(msgParameter.equalsIgnoreCase("customer_msg")){
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    connection=DBConnection.getConnection();
                    statement=connection.createStatement();

                    Date now=new Date();
                    DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    int i=statement.executeUpdate("INSERT INTO Handymen_Customer_Messaging(sender_name, sender_id, recipient_name, recipient_id, message, date_sent, status, sender_type)" +
                            "values" +
                            " ('"+handymen.getUsername()+"','"+handymen.getHandymanid()+"','"+customer_sent_name+"','"+customerid+"','"+customer_message+"','"+dateFormat.format(now)+"','unread','handyman')");
                    //out.println(i);
                    rs=statement.executeQuery("SELECT * FROM Handymen.Handymen_Customer_Messaging WHERE sender_name='"+customer_sent_name+"' AND recipient_id='"+handymen.getHandymanid()+"' AND sender_type='customer' OR  sender_id='"+handymen.getHandymanid()+"' AND recipient_name='"+customer_sent_name+"' AND sender_type='handyman'");
                    int y=0;
                    while (rs.next()){
                        customerMessagingList.add(new CustomerMessaging(rs.getInt(1),rs.getInt(3),rs.getInt(5),rs.getString(2),rs.getString(4),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
                        //out.println(rs.getString(2)+" "+handymen.getHandymanid());
                        y++;
                        if(rs.getString(9).equalsIgnoreCase("customer")){
                            customerid=rs.getInt(3);
                        }
                    }

                    //out.println(msgParameterValue+" y:"+y);
                    //out.println(y+" h_id:"+handymen.getHandymanid());
                    request.setAttribute("customerid",customerid);
                    request.setAttribute("adminMessagingList",adminMessagingList);
                    request.setAttribute("customerMessagingList",customerMessagingList);
                    RequestDispatcher dispatcher=request.getRequestDispatcher(response.encodeURL("/HCustomerMessage.jsp"));
                    dispatcher.forward(request,response);
                }catch (Exception e){
                    e.printStackTrace();
                    out.println(e.getMessage());
                }
            }

            if(msgParameter.equalsIgnoreCase("delete_post")){
                post_id=Integer.parseInt(msgParameterValue);
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    connection=DBConnection.getConnection();
                    statement=connection.createStatement();

                    int i=statement.executeUpdate("DELETE FROM Items_by_handymen WHERE id='"+post_id+"' AND hid='"+handymen.getHandymanid()+"'");
                    //out.println(i);
                    if(i==1){
                        List<Posts> postsList=new ArrayList<>();
                        rs=statement.executeQuery("SELECT * FROM Items_by_handymen WHERE hid='"+handymen.getHandymanid()+"'");
                        i=0;
                        while (rs.next()){
                            i++;
                            postsList.add(new Posts(rs.getInt(3),rs.getInt(4),rs.getDouble(6),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(5)));
                        }
                        if(i>0){
                            request.setAttribute("postsList",postsList);
                            request.getServletContext().getRequestDispatcher(response.encodeURL("/HPosts.jsp")).forward(request,response);
                        }
                    }else {
                        out.println(i+" Error!");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    out.println(e.getMessage());
                }
            }

            if(msgParameter.equalsIgnoreCase("customermsg")){
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    connection=DBConnection.getConnection();
                    statement=connection.createStatement();

                    int y=0,customer_id=0;
                    rs=statement.executeQuery("SELECT * FROM Handymen.Handymen_Customer_Messaging WHERE sender_name='"+msgParameterValue+"' AND recipient_id='"+handymen.getHandymanid()+"' AND sender_type='customer' OR  sender_id='"+handymen.getHandymanid()+"' AND recipient_name='"+msgParameterValue+"' AND sender_type='handyman'");
                    while (rs.next()){
                        customerMessagingList.add(new CustomerMessaging(rs.getInt(1),rs.getInt(3),rs.getInt(5),rs.getString(2),rs.getString(4),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
                        //out.println(rs.getString(2)+" "+handymen.getHandymanid());
                        y++;
                        if(rs.getString(9).equalsIgnoreCase("customer")){
                            customer_id=rs.getInt(3);
                        }
                    }

                    //out.println(msgParameterValue+" y:"+y);
                    //out.println(y+" h_id:"+handymen.getHandymanid());
                    request.setAttribute("customerid",customer_id);
                    request.setAttribute("customername",msgParameterValue);
                    request.setAttribute("adminMessagingList",adminMessagingList);
                    request.setAttribute("customerMessagingList",customerMessagingList);
                    RequestDispatcher dispatcher=request.getRequestDispatcher(response.encodeURL("/HCustomerMessage.jsp"));
                    dispatcher.forward(request,response);
                }catch (Exception e){
                    out.println(e.getMessage());
                }
            }

            if(msgParameter.equalsIgnoreCase("service_details")){
                String image_url=(String)httpSession.getAttribute("image_url");
                out.println("Image url: "+image_url);
            /*try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();
                Date now=new Date();
                DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                int i=statement.executeUpdate("INSERT INTO Items_by_handymen(category, name, hid, hname, price, Post_date, image_url,description)" +
                        "VALUES" +
                        " ('"+category+"','"+name+"','"+handymen.getHandymanid()+"','"+handymen.getUsername()+"','"+price+"','"+dateFormat.format(now)+"','"+image_url+"','"+description+"')");
                if(i==1){
                    request.removeAttribute("image_url");
                    request.getServletContext().getRequestDispatcher(response.encodeURL("/HPostItem.jsp")).forward(request,response);
                }else{
                    out.println("Fail");
                }
            }catch (Exception e){
                out.println(e.getMessage());
            }*/
            }
        }

        if(msgParameter.equalsIgnoreCase("change_username")){
            String fname="",lname="",email="",location="",gender="",password="",regdate="",soffered="",username="";
            int phone=0,handyman_id=0;
            CustomerMessaging customerMessaging;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                handyman_id=handymen.getHandymanid();

                rs=statement.executeQuery("SELECT * FROM Handymen.Handymen WHERE handymanid='"+handyman_id+"'");
                while (rs.next()){
                    handymen=new Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10),rs.getString(11));
                }

                preparedStatement=connection.prepareStatement("UPDATE Handymen.Handymen SET Fname=?,Lname=?,username=?,handymanid=?,email=?,location=?,phoneno=?,password=?,gender=?,Regdate=?,soffered=? WHERE handymanid=?");
                preparedStatement.setString(1,handymen.getFirstName());
                preparedStatement.setString(2,handymen.getLastName());
                preparedStatement.setString(3,change_username);
                preparedStatement.setString(4,Integer.toString(handymen.getHandymanid()));
                preparedStatement.setString(5,handymen.getEmail());
                preparedStatement.setString(6,handymen.getLocation());
                preparedStatement.setString(7,Integer.toString(handymen.getPhoneNo()));
                preparedStatement.setString(8,handymen.getPassword());
                preparedStatement.setString(9,handymen.getGender());
                preparedStatement.setString(10,handymen.getDateRegistered());
                preparedStatement.setString(11,handymen.getSoffered());
                preparedStatement.setString(12,Integer.toString(handyman_id));
                int k=0;
                k=preparedStatement.executeUpdate();
                //out.println("username: "+k);
                if(k==1){
                    rs=statement.executeQuery("SELECT * FROM Handymen WHERE handymanid='"+handymen.getHandymanid()+"'");
                    while (rs.next()){
                        handymen=new Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                    }
                    request.setAttribute("handyman_details",handymen);
                    request.getServletContext().getRequestDispatcher(response.encodeURL("/HProfile.jsp")).forward(request,response);

                }
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }

        if(msgParameter.equalsIgnoreCase("change_fname")){
            int handyman_id=0;
            CustomerMessaging customerMessaging;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                handyman_id=handymen.getHandymanid();

                rs=statement.executeQuery("SELECT * FROM Handymen.Handymen WHERE handymanid='"+handyman_id+"'");
                while (rs.next()){
                    handymen=new Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10),rs.getString(11));
                }

                preparedStatement=connection.prepareStatement("UPDATE Handymen.Handymen SET Fname=?,Lname=?,username=?,handymanid=?,email=?,location=?,phoneno=?,password=?,gender=?,Regdate=?,soffered=? WHERE handymanid=?");
                preparedStatement.setString(1,change_fname);
                preparedStatement.setString(2,handymen.getLastName());
                preparedStatement.setString(3,handymen.getUsername());
                preparedStatement.setString(4,Integer.toString(handymen.getHandymanid()));
                preparedStatement.setString(5,handymen.getEmail());
                preparedStatement.setString(6,handymen.getLocation());
                preparedStatement.setString(7,Integer.toString(handymen.getPhoneNo()));
                preparedStatement.setString(8,handymen.getPassword());
                preparedStatement.setString(9,handymen.getGender());
                preparedStatement.setString(10,handymen.getDateRegistered());
                preparedStatement.setString(11,handymen.getSoffered());
                preparedStatement.setString(12,Integer.toString(handyman_id));
                int k=0;
                k=preparedStatement.executeUpdate();
                //out.println("fname:"+k);
                if(k==1){
                    rs=statement.executeQuery("SELECT * FROM Handymen WHERE handymanid='"+handymen.getHandymanid()+"'");
                    while (rs.next()){
                        handymen=new Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                    }
                    request.setAttribute("handyman_details",handymen);
                    request.getServletContext().getRequestDispatcher(response.encodeURL("/HProfile.jsp")).forward(request,response);

                }
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }

        if(msgParameter.equalsIgnoreCase("change_lname")){
            int handyman_id=0;
            CustomerMessaging customerMessaging;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                handyman_id=handymen.getHandymanid();

                rs=statement.executeQuery("SELECT * FROM Handymen.Handymen WHERE handymanid='"+handyman_id+"'");
                while (rs.next()){
                    handymen=new Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10),rs.getString(11));
                }

                preparedStatement=connection.prepareStatement("UPDATE Handymen.Handymen SET Fname=?,Lname=?,username=?,handymanid=?,email=?,location=?,phoneno=?,password=?,gender=?,Regdate=?,soffered=? WHERE handymanid=?");
                preparedStatement.setString(1,handymen.getFirstName());
                preparedStatement.setString(2,change_lname);
                preparedStatement.setString(3,handymen.getUsername());
                preparedStatement.setString(4,Integer.toString(handymen.getHandymanid()));
                preparedStatement.setString(5,handymen.getEmail());
                preparedStatement.setString(6,handymen.getLocation());
                preparedStatement.setString(7,Integer.toString(handymen.getPhoneNo()));
                preparedStatement.setString(8,handymen.getPassword());
                preparedStatement.setString(9,handymen.getGender());
                preparedStatement.setString(10,handymen.getDateRegistered());
                preparedStatement.setString(11,handymen.getSoffered());
                preparedStatement.setString(12,Integer.toString(handyman_id));
                int k=0;
                k=preparedStatement.executeUpdate();
                //out.println("lname"+k);
                if(k==1){
                    rs=statement.executeQuery("SELECT * FROM Handymen WHERE handymanid='"+handymen.getHandymanid()+"'");
                    while (rs.next()){
                        handymen=new Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                    }
                    request.setAttribute("handyman_details",handymen);
                    request.getServletContext().getRequestDispatcher(response.encodeURL("/HProfile.jsp")).forward(request,response);

                }
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }

        if(msgParameter.equalsIgnoreCase("change_phone")){
            int handyman_id=0;
            CustomerMessaging customerMessaging;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                handyman_id=handymen.getHandymanid();

                rs=statement.executeQuery("SELECT * FROM Handymen.Handymen WHERE handymanid='"+handyman_id+"'");
                while (rs.next()){
                    handymen=new Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10),rs.getString(11));
                }

                preparedStatement=connection.prepareStatement("UPDATE Handymen.Handymen SET Fname=?,Lname=?,username=?,handymanid=?,email=?,location=?,phoneno=?,password=?,gender=?,Regdate=?,soffered=? WHERE handymanid=?");
                preparedStatement.setString(1,handymen.getFirstName());
                preparedStatement.setString(2,handymen.getLastName());
                preparedStatement.setString(3,handymen.getUsername());
                preparedStatement.setString(4,Integer.toString(handymen.getHandymanid()));
                preparedStatement.setString(5,handymen.getEmail());
                preparedStatement.setString(6,handymen.getLocation());
                preparedStatement.setString(7,Integer.toString(change_phone));
                preparedStatement.setString(8,handymen.getPassword());
                preparedStatement.setString(9,handymen.getGender());
                preparedStatement.setString(10,handymen.getDateRegistered());
                preparedStatement.setString(11,handymen.getSoffered());
                preparedStatement.setString(12,Integer.toString(handyman_id));
                int k=0;
                k=preparedStatement.executeUpdate();
                //out.println("phone:"+k);
                if(k==1){
                    rs=statement.executeQuery("SELECT * FROM Handymen WHERE handymanid='"+handymen.getHandymanid()+"'");
                    while (rs.next()){
                        handymen=new Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                    }
                    request.setAttribute("handyman_details",handymen);
                    request.getServletContext().getRequestDispatcher(response.encodeURL("/HProfile.jsp")).forward(request,response);

                }
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }

        if(msgParameter.equalsIgnoreCase("change_location")){
            int handyman_id=0;
            CustomerMessaging customerMessaging;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                handyman_id=handymen.getHandymanid();

                rs=statement.executeQuery("SELECT * FROM Handymen.Handymen WHERE handymanid='"+handyman_id+"'");
                while (rs.next()){
                    handymen=new Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10),rs.getString(11));
                }

                preparedStatement=connection.prepareStatement("UPDATE Handymen.Handymen SET Fname=?,Lname=?,username=?,handymanid=?,email=?,location=?,phoneno=?,password=?,gender=?,Regdate=?,soffered=? WHERE handymanid=?");
                preparedStatement.setString(1,handymen.getFirstName());
                preparedStatement.setString(2,handymen.getLastName());
                preparedStatement.setString(3,handymen.getUsername());
                preparedStatement.setString(4,Integer.toString(handymen.getHandymanid()));
                preparedStatement.setString(5,handymen.getEmail());
                preparedStatement.setString(6,change_location);
                preparedStatement.setString(7,Integer.toString(handymen.getPhoneNo()));
                preparedStatement.setString(8,handymen.getPassword());
                preparedStatement.setString(9,handymen.getGender());
                preparedStatement.setString(10,handymen.getDateRegistered());
                preparedStatement.setString(11,handymen.getSoffered());
                preparedStatement.setString(12,Integer.toString(handyman_id));
                int k=0;
                k=preparedStatement.executeUpdate();
                //out.println("location:"+k);
                if(k==1){
                    rs=statement.executeQuery("SELECT * FROM Handymen WHERE handymanid='"+handymen.getHandymanid()+"'");
                    while (rs.next()){
                        handymen=new Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                    }
                    request.setAttribute("handyman_details",handymen);
                    request.getServletContext().getRequestDispatcher(response.encodeURL("/HProfile.jsp")).forward(request,response);

                }
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }

        if(msgParameter.equalsIgnoreCase("change_gender")){
            int handyman_id=0;
            CustomerMessaging customerMessaging;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                handyman_id=handymen.getHandymanid();

                rs=statement.executeQuery("SELECT * FROM Handymen.Handymen WHERE handymanid='"+handyman_id+"'");
                while (rs.next()){
                    handymen=new Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10),rs.getString(11));
                }

                preparedStatement=connection.prepareStatement("UPDATE Handymen.Handymen SET Fname=?,Lname=?,username=?,handymanid=?,email=?,location=?,phoneno=?,password=?,gender=?,Regdate=?,soffered=? WHERE handymanid=?");
                preparedStatement.setString(1,handymen.getFirstName());
                preparedStatement.setString(2,handymen.getLastName());
                preparedStatement.setString(3,handymen.getUsername());
                preparedStatement.setString(4,Integer.toString(handymen.getHandymanid()));
                preparedStatement.setString(5,handymen.getEmail());
                preparedStatement.setString(6,handymen.getLocation());
                preparedStatement.setString(7,Integer.toString(handymen.getPhoneNo()));
                preparedStatement.setString(8,handymen.getPassword());
                preparedStatement.setString(9,change_gender);
                preparedStatement.setString(10,handymen.getDateRegistered());
                preparedStatement.setString(11,handymen.getSoffered());
                preparedStatement.setString(12,Integer.toString(handyman_id));
                int k=0;
                k=preparedStatement.executeUpdate();
                //out.println("gender:"+k);
                if(k==1){
                    rs=statement.executeQuery("SELECT * FROM Handymen WHERE handymanid='"+handymen.getHandymanid()+"'");
                    while (rs.next()){
                        handymen=new Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                    }
                    request.setAttribute("handyman_details",handymen);
                    request.getServletContext().getRequestDispatcher(response.encodeURL("/HProfile.jsp")).forward(request,response);

                }
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }

        if(msgParameter.equalsIgnoreCase("change_email")){
            int handyman_id=0;
            CustomerMessaging customerMessaging;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                handyman_id=handymen.getHandymanid();

                rs=statement.executeQuery("SELECT * FROM Handymen.Handymen WHERE handymanid='"+handyman_id+"'");
                while (rs.next()){
                    handymen=new Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10),rs.getString(11));
                }

                preparedStatement=connection.prepareStatement("UPDATE Handymen.Handymen SET Fname=?,Lname=?,username=?,handymanid=?,email=?,location=?,phoneno=?,password=?,gender=?,Regdate=?,soffered=? WHERE handymanid=?");
                preparedStatement.setString(1,handymen.getFirstName());
                preparedStatement.setString(2,handymen.getLastName());
                preparedStatement.setString(3,handymen.getUsername());
                preparedStatement.setString(4,Integer.toString(handymen.getHandymanid()));
                preparedStatement.setString(5,change_email);
                preparedStatement.setString(6,handymen.getLocation());
                preparedStatement.setString(7,Integer.toString(handymen.getPhoneNo()));
                preparedStatement.setString(8,handymen.getPassword());
                preparedStatement.setString(9,handymen.getGender());
                preparedStatement.setString(10,handymen.getDateRegistered());
                preparedStatement.setString(11,handymen.getSoffered());
                preparedStatement.setString(12,Integer.toString(handyman_id));
                int k=0;
                k=preparedStatement.executeUpdate();
                //out.println("email:"+k);
                if(k==1){
                    rs=statement.executeQuery("SELECT * FROM Handymen WHERE handymanid='"+handymen.getHandymanid()+"'");
                    while (rs.next()){
                        handymen=new Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                    }
                    request.setAttribute("handyman_details",handymen);
                    request.getServletContext().getRequestDispatcher(response.encodeURL("/HProfile.jsp")).forward(request,response);

                }
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }

        if(msgParameter.equalsIgnoreCase("change_password")){
            int handyman_id=0;
            CustomerMessaging customerMessaging;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                rs=statement.executeQuery("SELECT * FROM Handymen WHERE email='"+handymen.getEmail()+"' AND password='"+current_password+"'");
                int count=0;

                while(rs.next()){
                    handymen=new Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                    count++;
                }

                if(count>0){
                    handyman_id=handymen.getHandymanid();

                    rs=statement.executeQuery("SELECT * FROM Handymen.Handymen WHERE handymanid='"+handyman_id+"'");
                    while (rs.next()){
                        handymen=new Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10),rs.getString(11));
                    }
                    preparedStatement=connection.prepareStatement("UPDATE Handymen.Handymen SET Fname=?,Lname=?,username=?,handymanid=?,email=?,location=?,phoneno=?,password=?,gender=?,Regdate=?,soffered=? WHERE handymanid=?");
                    preparedStatement.setString(1,handymen.getFirstName());
                    preparedStatement.setString(2,handymen.getLastName());
                    preparedStatement.setString(3,handymen.getUsername());
                    preparedStatement.setString(4,Integer.toString(handymen.getHandymanid()));
                    preparedStatement.setString(5,handymen.getEmail());
                    preparedStatement.setString(6,handymen.getLocation());
                    preparedStatement.setString(7,Integer.toString(handymen.getPhoneNo()));
                    preparedStatement.setString(8,change_password);
                    preparedStatement.setString(9,handymen.getGender());
                    preparedStatement.setString(10,handymen.getDateRegistered());
                    preparedStatement.setString(11,handymen.getSoffered());
                    preparedStatement.setString(12,Integer.toString(handyman_id));
                    int k=0;
                    k=preparedStatement.executeUpdate();
                    //out.println("password:"+k);
                    if(k==1){
                        httpSession.invalidate();
                        request.getServletContext().getRequestDispatcher("/HDashboard.jsp").forward(request,response);
                        /*rs=statement.executeQuery("SELECT * FROM Handymen WHERE handymanid='"+handymen.getHandymanid()+"'");
                        while (rs.next()){
                            handymen=new Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                        }
                        request.setAttribute("handyman_details",handymen);
                        request.getServletContext().getRequestDispatcher(response.encodeURL("/HProfile.jsp")).forward(request,response);*/

                    }
                }else{
                    out.println("Check your currrent password and retry!");
                }
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }

        if(msgParameter.equalsIgnoreCase("update_description")){
            String cat="",img="",n="",hname="",postdate="",desc="";
            int id=0,hid=0,j=0;
            double pr=0;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                rs=statement.executeQuery("SELECT * FROM Items_by_handymen WHERE id='"+post_id+"'");
                while(rs.next()){
                    cat=rs.getString(1).toString();
                    img=rs.getString(8).toString();
                    n=rs.getString(2).toString();
                    hname=rs.getString(5).toString();
                    postdate=rs.getString(7).toString();

                    if(rs.getString(9)==null){
                        desc="-";
                    }else{
                        desc=rs.getString(9).toString();
                    }
                    id=Integer.parseInt(Integer.toString(rs.getInt(3)));
                    hid=Integer.parseInt(Integer.toString(rs.getInt(4)));
                    pr=Double.parseDouble(Double.toString(rs.getDouble(6)));
                }
                preparedStatement=connection.prepareStatement("UPDATE Items_by_handymen SET category=?,name=?,id=?,hid=?,hname=?,price=?,Post_date=?,image_url=?,description=? WHERE id=?");
                preparedStatement.setString(1,cat);
                preparedStatement.setString(2,n);
                preparedStatement.setString(3,Integer.toString(id));
                preparedStatement.setString(4,Integer.toString(hid));
                preparedStatement.setString(5,hname);
                preparedStatement.setString(6,Double.toString(pr));
                preparedStatement.setString(7,postdate);
                preparedStatement.setString(8,img);
                preparedStatement.setString(9,update_description);
                preparedStatement.setString(10,Integer.toString(post_id));
                j=preparedStatement.executeUpdate();
                if(j==1){
                    rs=statement.executeQuery("SELECT * FROM Items_by_handymen WHERE hid='"+handymen.getHandymanid()+"' AND id='"+post_id+"'");
                    int i=0;
                    List<Posts> postsList=new ArrayList<>();
                    while (rs.next()){
                        i++;
                        postsList.add(new Posts(rs.getInt(3),rs.getInt(4),rs.getDouble(6),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(5),rs.getString(8),rs.getString(9)));
                    }
                    if(i>0){
                        request.setAttribute("postsList",postsList);
                        request.getServletContext().getRequestDispatcher(response.encodeURL("/HPost.jsp")).forward(request,response);
                    }
                }else{
                    out.println("Error occured! Please contact system admin.");
                }
            }catch (Exception e){
                out.println("Er: "+e.getMessage());
                e.printStackTrace();
            }
        }

        if(msgParameter.equalsIgnoreCase("update_image")){
            String cat="",img="",n="",hname="",postdate="",desc="";
            int id=0,hid=0,j=0;
            double pr=0;
            final String UPLOAD_DIRECTORY = "/home/papa/IdeaProjects/HandyMen/web/resources/Uploads";
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                rs=statement.executeQuery("SELECT * FROM Items_by_handymen WHERE id='"+post_id+"'");
                while(rs.next()){
                    cat=rs.getString(1).toString();
                    img=rs.getString(8).toString();
                    n=rs.getString(2).toString();
                    hname=rs.getString(5).toString();
                    postdate=rs.getString(7).toString();

                    if(rs.getString(9)==null){
                        desc="-";
                    }else{
                        desc=rs.getString(9).toString();
                    }
                    id=Integer.parseInt(Integer.toString(rs.getInt(3)));
                    hid=Integer.parseInt(Integer.toString(rs.getInt(4)));
                    pr=Double.parseDouble(Double.toString(rs.getDouble(6)));
                }
                preparedStatement=connection.prepareStatement("UPDATE Items_by_handymen SET category=?,name=?,id=?,hid=?,hname=?,price=?,Post_date=?,image_url=?,description=? WHERE id=?");
                preparedStatement.setString(1,cat);
                preparedStatement.setString(2,n);
                preparedStatement.setString(3,Integer.toString(id));
                preparedStatement.setString(4,Integer.toString(hid));
                preparedStatement.setString(5,hname);
                preparedStatement.setString(6,Double.toString(pr));
                preparedStatement.setString(7,postdate);
                preparedStatement.setString(8,img);
                preparedStatement.setString(9,update_description);
                preparedStatement.setString(10,Integer.toString(post_id));
                j=preparedStatement.executeUpdate();
                if(j==1){
                    out=response.getWriter();
                    httpSession=request.getSession();
                    String img_name="";
                    if(ServletFileUpload.isMultipartContent(request)){
                        try {
                            List<FileItem> multiparts = new ServletFileUpload(
                                    new DiskFileItemFactory()).parseRequest(request);
                            for(FileItem item : multiparts){
                                if(!item.isFormField()){
                                    img_name = new File(item.getName()).getName();
                                    item.write( new File(UPLOAD_DIRECTORY + File.separator + img_name));
                                }
                            }
                            //File uploaded successfully
                            out.println("File Uploaded Successfully: "+img_name);
                            //request.setAttribute("message", "File Uploaded Successfully");
                            Class.forName("com.mysql.jdbc.Driver");
                            connection=DBConnection.getConnection();
                            statement=connection.createStatement();
                            handymen=(Handymen)httpSession.getAttribute("logged_handyman");
                            request.setAttribute("image_url",img_name);
                            httpSession.setAttribute("image_url",img_name);
                        } catch (Exception ex) {
                            out.println("File Upload Failed due to "+ex.getMessage());
                            ex.printStackTrace();
//                    request.setAttribute("message", "File Upload Failed due to " + ex);
                        }
                    }else{
                        out.println("Sorry this Servlet only handles file upload request");
                        //request.setAttribute("message","Sorry this Servlet only handles file upload request");
                    }
                    rs=statement.executeQuery("SELECT * FROM Items_by_handymen WHERE hid='"+handymen.getHandymanid()+"' AND id='"+post_id+"'");
                    int i=0;
                    List<Posts> postsList=new ArrayList<>();
                    while (rs.next()){
                        i++;
                        postsList.add(new Posts(rs.getInt(3),rs.getInt(4),rs.getDouble(6),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(5),rs.getString(8),rs.getString(9)));
                    }
                    if(i>0){
                        request.setAttribute("postsList",postsList);
                        request.getServletContext().getRequestDispatcher(response.encodeURL("/HPost.jsp")).forward(request,response);
                    }
                }else{
                    out.println("Error occured! Please contact system admin.");
                }
            }catch (Exception e){
                out.println("Er: "+e.getMessage());
                e.printStackTrace();
            }
        }

        if(msgParameter.equalsIgnoreCase("update_price")){
            String cat="",img="",n="",hname="",postdate="",desc="";
            int id=0,hid=0,j=0;
            double pr=0;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                rs=statement.executeQuery("SELECT * FROM Items_by_handymen WHERE id='"+post_id+"'");
                while(rs.next()){
                    cat=rs.getString(1).toString();
                    img=rs.getString(8).toString();
                    n=rs.getString(2).toString();
                    hname=rs.getString(5).toString();
                    postdate=rs.getString(7).toString();

                    if(rs.getString(9)==null){
                        desc="-";
                    }else{
                        desc=rs.getString(9).toString();
                    }
                    id=Integer.parseInt(Integer.toString(rs.getInt(3)));
                    hid=Integer.parseInt(Integer.toString(rs.getInt(4)));
                    pr=Double.parseDouble(Double.toString(rs.getDouble(6)));
                }
                preparedStatement=connection.prepareStatement("UPDATE Items_by_handymen SET category=?,name=?,id=?,hid=?,hname=?,price=?,Post_date=?,image_url=?,description=? WHERE id=?");
                preparedStatement.setString(1,cat);
                preparedStatement.setString(2,n);
                preparedStatement.setString(3,Integer.toString(id));
                preparedStatement.setString(4,Integer.toString(hid));
                preparedStatement.setString(5,hname);
                preparedStatement.setString(6,Double.toString(Double.parseDouble(update_price)));
                preparedStatement.setString(7,postdate);
                preparedStatement.setString(8,img);
                preparedStatement.setString(9,desc);
                preparedStatement.setString(10,Integer.toString(post_id));
                j=preparedStatement.executeUpdate();
                if(j==1){
                    rs=statement.executeQuery("SELECT * FROM Items_by_handymen WHERE hid='"+handymen.getHandymanid()+"' AND id='"+post_id+"'");
                    int i=0;
                    List<Posts> postsList=new ArrayList<>();
                    while (rs.next()){
                        i++;
                        postsList.add(new Posts(rs.getInt(3),rs.getInt(4),rs.getDouble(6),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(5),rs.getString(8),rs.getString(9)));
                    }
                    if(i>0){
                        request.setAttribute("postsList",postsList);
                        request.getServletContext().getRequestDispatcher(response.encodeURL("/HPost.jsp")).forward(request,response);
                    }
                }else{
                    out.println("Error occured! Please contact system admin.");
                }
            }catch (Exception e){
                out.println("Er: "+e.getMessage());
                e.printStackTrace();
            }
        }

        if(msgParameter.equalsIgnoreCase("update_name")){
            String cat="",img="",n="",hname="",postdate="",desc="";
            int id=0,hid=0,j=0;
            double pr=0;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                rs=statement.executeQuery("SELECT * FROM Items_by_handymen WHERE id='"+post_id+"'");
                while(rs.next()){
                    cat=rs.getString(1).toString();
                    img=rs.getString(8).toString();
                    n=rs.getString(2).toString();
                    hname=rs.getString(5).toString();
                    postdate=rs.getString(7).toString();

                    if(rs.getString(9)==null){
                        desc="-";
                    }else{
                        desc=rs.getString(9).toString();
                    }
                    id=Integer.parseInt(Integer.toString(rs.getInt(3)));
                    hid=Integer.parseInt(Integer.toString(rs.getInt(4)));
                    pr=Double.parseDouble(Double.toString(rs.getDouble(6)));
                }
                preparedStatement=connection.prepareStatement("UPDATE Items_by_handymen SET category=?,name=?,id=?,hid=?,hname=?,price=?,Post_date=?,image_url=?,description=? WHERE id=?");
                preparedStatement.setString(1,cat);
                preparedStatement.setString(2,update_name);
                preparedStatement.setString(3,Integer.toString(id));
                preparedStatement.setString(4,Integer.toString(hid));
                preparedStatement.setString(5,hname);
                preparedStatement.setString(6,Double.toString(pr));
                preparedStatement.setString(7,postdate);
                preparedStatement.setString(8,img);
                preparedStatement.setString(9,desc);
                preparedStatement.setString(10,Integer.toString(post_id));
                j=preparedStatement.executeUpdate();
                if(j==1){
                    rs=statement.executeQuery("SELECT * FROM Items_by_handymen WHERE hid='"+handymen.getHandymanid()+"' AND id='"+post_id+"'");
                    int i=0;
                    List<Posts> postsList=new ArrayList<>();
                    while (rs.next()){
                        i++;
                        postsList.add(new Posts(rs.getInt(3),rs.getInt(4),rs.getDouble(6),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(5),rs.getString(8),rs.getString(9)));
                    }
                    if(i>0){
                        request.setAttribute("postsList",postsList);
                        request.getServletContext().getRequestDispatcher(response.encodeURL("/HPost.jsp")).forward(request,response);
                    }
                }else{
                    out.println("Error occured! Please contact system admin.");
                }
            }catch (Exception e){
                out.println("Er: "+e.getMessage());
                e.printStackTrace();
            }
        }

        if(msgParameter.equalsIgnoreCase("update_category")){
            //out.println("Post id: "+post_id);
            //out.println(update_category +post_id);
            String cat="",img="",n="",hname="",postdate="",desc="";
            int id=0,hid=0,j=0;
            double pr=0;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                rs=statement.executeQuery("SELECT * FROM Items_by_handymen WHERE id='"+post_id+"'");
                while(rs.next()){
                    cat=rs.getString(1).toString();
                    img=rs.getString(8).toString();
                    n=rs.getString(2).toString();
                    hname=rs.getString(5).toString();
                    postdate=rs.getString(7).toString();

                    if(rs.getString(9)==null){
                        desc="-";
                    }else{
                        desc=rs.getString(9).toString();
                    }
                    id=Integer.parseInt(Integer.toString(rs.getInt(3)));
                    hid=Integer.parseInt(Integer.toString(rs.getInt(4)));
                    pr=Double.parseDouble(Double.toString(rs.getDouble(6)));
                }
                preparedStatement=connection.prepareStatement("UPDATE Items_by_handymen SET category=?,name=?,id=?,hid=?,hname=?,price=?,Post_date=?,image_url=?,description=? WHERE id=?");
                preparedStatement.setString(1,update_category);
                preparedStatement.setString(2,n);
                preparedStatement.setString(3,Integer.toString(id));
                preparedStatement.setString(4,Integer.toString(hid));
                preparedStatement.setString(5,hname);
                preparedStatement.setString(6,Double.toString(pr));
                preparedStatement.setString(7,postdate);
                preparedStatement.setString(8,img);
                preparedStatement.setString(9,desc);
                preparedStatement.setString(10,Integer.toString(post_id));
                j=preparedStatement.executeUpdate();
                if(j==1){
                    rs=statement.executeQuery("SELECT * FROM Items_by_handymen WHERE hid='"+handymen.getHandymanid()+"' AND id='"+post_id+"'");
                    int i=0;
                    List<Posts> postsList=new ArrayList<>();
                    while (rs.next()){
                        i++;
                        postsList.add(new Posts(rs.getInt(3),rs.getInt(4),rs.getDouble(6),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(5),rs.getString(8),rs.getString(9)));
                    }
                    if(i>0){
                        request.setAttribute("postsList",postsList);
                        request.getServletContext().getRequestDispatcher(response.encodeURL("/HPost.jsp")).forward(request,response);
                    }
                }else{
                    out.println("Error occured! Please contact system admin.");
                }
            }catch (Exception e){
                out.println("Er: "+e.getMessage());
                e.printStackTrace();
            }
        }

        if(msgParameter.equalsIgnoreCase("Messages")){
            if(msgParameterValue.equalsIgnoreCase("ok")){
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    connection=DBConnection.getConnection();
                    statement=connection.createStatement();
                    rs=statement.executeQuery("SELECT * FROM Handyman_Admin_Messaging WHERE sender_id='"+handymen.getHandymanid()+"' AND sender_type='handyman' OR recipient_id='"+handymen.getHandymanid()+"' AND sender_type='admin'");
                    while (rs.next()){
                        adminMessagingList.add(new AdminMessaging(rs.getInt(1),rs.getInt(3),rs.getInt(4),rs.getString(2),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
                    }
                    int y=0;
                    //rs2=statement.executeQuery("SELECT * FROM Handymen_Customer_Messaging WHERE sender_id='"+handymen.getHandymanid()+"' AND sender_type='customer' OR recipient_id='"+handymen.getHandymanid()+"' AND sender_type='handyman'");
                    rs=statement.executeQuery("SELECT * FROM Handymen_Customer_Messaging WHERE recipient_id='"+handymen.getHandymanid()+"' AND sender_type='customer' OR sender_id='"+handymen.getHandymanid()+"' AND sender_type='handyman'");
                    while (rs.next()){
                        customerMessagingList.add(new CustomerMessaging(rs.getInt(1),rs.getInt(3),rs.getInt(5),rs.getString(2),rs.getString(4),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
                        //out.println(rs.getString(2)+" "+handymen.getHandymanid());
                        y++;
                    }
                    //out.println(y+" h_id:"+handymen.getHandymanid());
                    request.setAttribute("adminMessagingList",adminMessagingList);
                    request.setAttribute("customerMessagingList",customerMessagingList);
                    RequestDispatcher dispatcher=request.getRequestDispatcher(response.encodeURL("/HMessages.jsp"));
                    dispatcher.forward(request,response);
                }catch (Exception e){
                    e.printStackTrace();
                    out.println(e.getMessage());
                }
            }
        }

        if(msgParameter.equalsIgnoreCase("PostItem")){
            if(msgParameterValue.equalsIgnoreCase("ok")){
                request.getServletContext().getRequestDispatcher(response.encodeURL("/HPostItem.jsp")).forward(request,response);
            }
        }

        if(msgParameter.equalsIgnoreCase("MyPosts")){
            if(msgParameterValue.equalsIgnoreCase("ok")){
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    connection=DBConnection.getConnection();
                    statement=connection.createStatement();
                    List<Posts> postsList=new ArrayList<>();
                    rs=statement.executeQuery("SELECT * FROM Items_by_handymen WHERE hid='"+handymen.getHandymanid()+"'");
                    int i=0;
                    while (rs.next()){
                        i++;
                        postsList.add(new Posts(rs.getInt(3),rs.getInt(4),rs.getDouble(6),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(5)));
                    }
                    if(i>0){
                        request.setAttribute("postsList",postsList);
                        request.getServletContext().getRequestDispatcher(response.encodeURL("/HPosts.jsp")).forward(request,response);
                    }
                }catch (Exception e){
                    out.println(e.getMessage());
                }
            }
        }

        if(msgParameter.equalsIgnoreCase("Post")){
            List<Posts> postsList=new ArrayList<>();
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();
                rs=statement.executeQuery("SELECT * FROM Items_by_handymen WHERE hid='"+handymen.getHandymanid()+"' AND id='"+msgParameterValue+"'");
                int i=0;
                while (rs.next()){
                    i++;
                    postsList.add(new Posts(rs.getInt(3),rs.getInt(4),rs.getDouble(6),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(5),rs.getString(8),rs.getString(9)));
                }
                if(i>0){
                    request.setAttribute("postsList",postsList);
                    request.getServletContext().getRequestDispatcher(response.encodeURL("/HPost.jsp")).forward(request,response);
                }
            }catch (Exception e){
                out.println(e.getMessage());
            }
        }

        if(msgParameter.equalsIgnoreCase("Profile")){
            List<Posts> postsList=new ArrayList<>();
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();
                rs=statement.executeQuery("SELECT * FROM Handymen WHERE handymanid='"+handymen.getHandymanid()+"'");
                while (rs.next()){
                    handymen=new Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                }
                request.setAttribute("handyman_details",handymen);
                request.getServletContext().getRequestDispatcher(response.encodeURL("/HProfile.jsp")).forward(request,response);
            }catch (Exception e){
                out.println(e.getMessage());
            }
        }
    }
}
