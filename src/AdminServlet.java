import Admin.Handymen;
import Admin.Customers;
import Handyman.CustomerMessaging;
import Handyman.Posts;
import Jobs.Jobs;
import Login.Admin;
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

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet rs,rs2;
    AdminHandyman adminHandyman;
    AdminCustomer adminCustomer;
    Admin admin;
    Customers customers;
    Login.Handymen handymen;
    List<Login.Handymen> viewHandymenList;
    List<Posts> postsList;

    List<AdminHandyman> adminHandymanList;
    List<AdminCustomer> adminCustomerList;
    List<Handymen> handymenList;
    List<Customers> customersList;
    List<Login.Handymen> handymenSearchList;
    List<Jobs> jobsList;
    JsonArray jsonArray;
    Gson gson;

    HttpSession httpSession;
    PrintWriter out;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        class EmptyMsgs{
            public String message;

            public EmptyMsgs(String message) {
                this.message = message;
            }
        }
        if(request.getParameter("check_messages").equalsIgnoreCase("check_messages")){
            adminHandymanList=new ArrayList<>();
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
                }


            }catch (Exception e){
                response.getWriter().write(e.getMessage());
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        viewHandymenList=new ArrayList<>();
        postsList=new ArrayList<>();
        jobsList=new ArrayList<>();
        Enumeration parameterNames=request.getParameterNames();
        String msgParameter="";
        String msgParameterValue="";
        out=response.getWriter();
        httpSession=request.getSession();
        admin=(Admin)httpSession.getAttribute("logged_admin");
        String change_username="",change_fname="",change_lname="",change_password="",change_email="",change_gender="",change_location="",current_password="",searchhandyman="";
        int change_phone=0;

        String new_fname="",new_lname="",old_password="",new_password="",new_gender="",new_location="",new_email="",new_username="",edit_profile="",HandymanJobs="",HandymanJob="";
        int new_phone=0,h_id=0;

        if(parameterNames.hasMoreElements()==true){
            while (parameterNames.hasMoreElements()){
                msgParameter=(String)parameterNames.nextElement();
                msgParameterValue=request.getParameter(msgParameter);
                if(msgParameter.equalsIgnoreCase("change_username")){
                    change_username=msgParameterValue;
                    //out.println("Price: "+price);
                }else if(msgParameter.equalsIgnoreCase("old_password")){
                    old_password=msgParameterValue;
                    //out.println("Price: "+old_password);
                }else if(msgParameter.equalsIgnoreCase("edit_profile")){
                    edit_profile=msgParameterValue;
                    //out.println("Price: "+edit_profile);
                }else if(msgParameter.equalsIgnoreCase("new_password")){
                    new_password=msgParameterValue;
                    //out.println("Price: "+new_password);
                }else if(msgParameter.equalsIgnoreCase("h_id")){
                    h_id=Integer.parseInt(msgParameterValue);
                    //out.println("Price: "+h_id);
                }else if(msgParameter.equalsIgnoreCase("HandymanJobs")){
                    HandymanJobs=msgParameterValue;
                    //out.println("Price: "+new_phone);
                }else if(msgParameter.equalsIgnoreCase("HandymanJob")){
                    HandymanJob=msgParameterValue;
                    //out.println("Price: "+HandymanJob);
                }else if(msgParameter.equalsIgnoreCase("new_phone")){
                    new_phone=Integer.parseInt(msgParameterValue);
                    //out.println("Price: "+new_phone);
                }else if(msgParameter.equalsIgnoreCase("new_email")){
                    new_email=msgParameterValue;
                    //out.println("Price: "+new_email);
                }else if(msgParameter.equalsIgnoreCase("new_gender")){
                    new_gender=msgParameterValue;
                    //out.println("Price: "+new_gender);
                }else if(msgParameter.equalsIgnoreCase("old_password")){
                    old_password=msgParameterValue;
                    //out.println("Price: "+old_password);
                }else if(msgParameter.equalsIgnoreCase("new_lname")){
                    new_lname=msgParameterValue;
                    //out.println("Price: "+new_lname);
                }else if(msgParameter.equalsIgnoreCase("new_location")){
                    new_location=msgParameterValue;
                    //out.println("Price: "+new_location);
                }else if(msgParameter.equalsIgnoreCase("new_fname")){
                    new_fname=msgParameterValue;
                    //out.println("Price: "+new_fname);
                }else if(msgParameter.equalsIgnoreCase("new_username")){
                    new_username=msgParameterValue;
                    //out.println("Price: "+new_username);
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
                }else if(msgParameter.equalsIgnoreCase("searchhandyman")){
                    searchhandyman=msgParameterValue;
                    //out.println("h: "+searchhandyman);
                }
            }
        }
        if(msgParameter.equalsIgnoreCase("Messages")){
            if(msgParameterValue.equalsIgnoreCase("ok")){
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    connection=DBConnection.getConnection();
                    statement=connection.createStatement();
                    rs=statement.executeQuery("SELECT * FROM Handymen");
                    int i=0;
                    handymenList=new ArrayList<>();
                    customersList=new ArrayList<>();
                    while (rs.next()){
                        handymenList.add(new Handymen(rs.getInt(7),rs.getString(8),rs.getString(5)));
                        i++;
                    }
                    rs=statement.executeQuery("SELECT * FROM Customers");
                    while (rs.next()){
                        customersList.add(new Customers(rs.getInt(7),rs.getInt(5),rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(8),rs.getString(9),rs.getString(10)));
                    }
                    request.setAttribute("handymenList",handymenList);
                    request.setAttribute("customersList",customersList);
                    RequestDispatcher requestDispatcher=getServletContext().getRequestDispatcher(response.encodeURL("/AMessages.jsp"));
                    requestDispatcher.forward(request,response);
                }catch (Exception e){
                    response.getWriter().write(e.getMessage());
                }
            }else{
                response.getWriter().write(msgParameterValue);
            }
        }else if(msgParameter.equalsIgnoreCase("edit_profile")){
            String fname="",lname="",email="",location="",gender="",password="",regdate="",soffered="",username="";
            int phone=0,admin_id=0;
            CustomerMessaging customerMessaging;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                admin_id=admin.getadminid();

                rs=statement.executeQuery("SELECT * FROM Handymen.Admin WHERE adminid='"+admin_id+"'");
                int count=0;

                while(rs.next()){
                    admin=new Admin(rs.getInt(5),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(3),rs.getInt(4),rs.getString(6));
                    //admin=new Admin(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                    count++;
                }

                if(new_email.equalsIgnoreCase("")){
                    email=admin.getEmail();
                }else{
                    email=new_email;
                }
                if(new_fname.equalsIgnoreCase("")){
                    fname=admin.getFirstName();
                }else{
                    fname=new_fname;
                }
                if(new_lname.equalsIgnoreCase("")){
                    lname=admin.getLastName();
                }else{
                    lname=new_lname;
                }
                if(new_password.equalsIgnoreCase("")){
                    password=admin.getPassword();
                }else{
                    password=new_password;
                }
                if(new_phone==0){
                    phone=admin.getPhoneNo();
                }else{
                    phone=new_phone;
                }

                preparedStatement=connection.prepareStatement("UPDATE Handymen.Admin SET Fname=?,Lname=?,adminid=?,email=?,password=?,phonenumber=? WHERE adminid=?");
                preparedStatement.setString(1,fname);
                preparedStatement.setString(2,lname);
                preparedStatement.setString(3,Integer.toString(admin.getadminid()));
                preparedStatement.setString(4,email);
                preparedStatement.setString(5,password);
                preparedStatement.setString(6,Integer.toString(phone));
                preparedStatement.setString(7,Integer.toString(admin.getadminid()));
                int k=0;
                k=preparedStatement.executeUpdate();
                //out.println("username: "+k);
                if(k==1){
                    rs=statement.executeQuery("SELECT * FROM Handymen.Admin WHERE adminid='"+admin.getadminid()+"'");
                    while(rs.next()){
                        admin=new Admin(rs.getInt(5),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(3),rs.getInt(4),rs.getString(6));
                        //admin=new Admin(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                    }

                    request.setAttribute("admin_details",admin);
                    request.getServletContext().getRequestDispatcher(response.encodeURL("/AdminProfile.jsp")).forward(request,response);

                }
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }else if(msgParameter.equalsIgnoreCase("HandymanJobs")){
            //out.write("received"+msgParameter+" "+msgParameterValue);
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();
                rs=statement.executeQuery("SELECT * FROM Jobs WHERE handymanid='"+HandymanJobs+"'");
                int i=0;
                while (rs.next()){
                    i++;
                    jobsList.add(new Jobs(rs.getString(2),rs.getString(3),rs.getString(8),rs.getString(4),rs.getString(6),rs.getString(9),rs.getInt(5),rs.getInt(7),rs.getInt(11),rs.getInt(1)));
                }
                //out.println(i);
                i=0;
                rs=statement.executeQuery("SELECT * FROM Handymen.Handymen WHERE handymanid='"+HandymanJobs+"'");
                while (rs.next()){
                    handymen=new Login.Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10),rs.getString(11),rs.getString(12));
                    i++;
                    viewHandymenList.add(handymen);
                    //handymenList.add(new Handymen(rs.getInt(7),rs.getString(8),rs.getString(5)));
                    //handymen=new Login.Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10),rs.getString(11),rs.getString(12));
                    //handymenSearchList.add(handymen);
                }
                //out.println(i+" "+viewHandymenList.size()+" "+viewHandymenList.get(0).getUsername()+" "+jobsList.size()+" han:"+HandymanJobs);
                request.setAttribute("handymanJob",jobsList);
                request.setAttribute("handymanDetails",handymen);
                request.getServletContext().getRequestDispatcher(response.encodeURL("/AdminViewHandymanJobs.jsp")).forward(request,response);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(msgParameter.equalsIgnoreCase("HandymanJob")){
            //out.write("received"+msgParameter+" "+msgParameterValue);
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();
                rs=statement.executeQuery("SELECT * FROM Jobs WHERE jobid='"+HandymanJob+"'");
                int i=0;
                while (rs.next()){
                    i++;
                    jobsList.add(new Jobs(rs.getString(2),rs.getString(3),rs.getString(8),rs.getString(4),rs.getString(6),rs.getString(9),rs.getInt(5),rs.getInt(7),rs.getInt(11),rs.getInt(1)));
                }
                //out.println(i);
                i=0;
                rs=statement.executeQuery("SELECT * FROM Handymen.Handymen WHERE handymanid='"+h_id+"'");
                while (rs.next()){
                    handymen=new Login.Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10),rs.getString(11),rs.getString(12));
                    i++;
                    viewHandymenList.add(handymen);
                    //handymenList.add(new Handymen(rs.getInt(7),rs.getString(8),rs.getString(5)));
                    //handymen=new Login.Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10),rs.getString(11),rs.getString(12));
                    //handymenSearchList.add(handymen);
                }
                //out.println(i+" "+viewHandymenList.size()+" "+viewHandymenList.get(0).getUsername()+" "+jobsList.size()+" han:"+HandymanJob);
                request.setAttribute("handymanJob",jobsList);
                request.setAttribute("handymanDetails",handymen);
                request.getServletContext().getRequestDispatcher(response.encodeURL("/AdminViewHandymanJob.jsp")).forward(request,response);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(msgParameter.equalsIgnoreCase("searchhandyman")){
            //out.write("received"+searchhandyman);
            handymenSearchList=new ArrayList<>();
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();
                rs=statement.executeQuery("SELECT * FROM Handymen.Handymen WHERE username LIKE '%"+searchhandyman+"%' OR Fname LIKE '%"+searchhandyman+"%' OR Lname = '%"+searchhandyman+"%'");
                int i=0;
                while (rs.next()){
                    //handymenList.add(new Handymen(rs.getInt(7),rs.getString(8),rs.getString(5)));
                    handymen=new Login.Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10),rs.getString(11),rs.getString(12));
                    handymenSearchList.add(handymen);
                    i++;
                }
                //out.write(i);
                //out.write(searchhandyman+" count:"+i);
                gson=new GsonBuilder().create();
                jsonArray=gson.toJsonTree(handymenSearchList).getAsJsonArray();
                out.write(jsonArray.toString());
            }catch (Exception e){
                response.getWriter().write(e.getMessage());
                e.printStackTrace();
            }
        }else if(msgParameter.equalsIgnoreCase("ChatID")){
            adminHandymanList=new ArrayList<>();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();
                int k=statement.executeUpdate("UPDATE Handyman_Admin_Messaging SET status='read' WHERE sender_id='"+msgParameterValue+"' AND recipient_id='"+admin.getadminid()+"' AND sender_type='handyman'");
                rs=statement.executeQuery("SELECT * FROM Handyman_Admin_Messaging WHERE sender_id='"+msgParameterValue+"' AND recipient_id='"+admin.getadminid()+"' " +
                        "OR sender_id='"+admin.getadminid()+"' AND recipient_id='"+msgParameterValue+"'");
                int i=0;
                while(rs.next()){
                    adminHandymanList.add(new AdminHandyman(rs.getInt(1),rs.getInt(3),rs.getInt(4),rs.getString(2),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
                    i++;
                }
            /*for (int j=0;j<adminHandymanList.size();i++){
                //out.println(adminHandymanList.get(j).getMessage());
            }*/
                out.println(i+" "+msgParameterValue);
                //out.println(" -msg- "+adminHandymanList);

                request.setAttribute("messages",adminHandymanList);
                request.setAttribute("chatID",msgParameterValue);
                RequestDispatcher requestDispatcher=getServletContext().getRequestDispatcher(response.encodeURL("/AdminChatHandyman.jsp"));
                requestDispatcher.forward(request,response);

            }catch (Exception e){
                e.printStackTrace();
                out.write(e.getMessage());
            }

        }else if(msgParameter.equalsIgnoreCase("C_ChatID")){
            adminCustomerList=new ArrayList<>();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();
                //rs=statement.executeQuery("SELECT * FROM Admin_Customers_Messaging WHERE recipient_id='"+msgParameterValue+"' AND sender_id='"+admin.getadminid()+"' AND sender_type='customer' OR sender_id='"+admin.getadminid()+"' AND recipient_id='"+msgParameterValue+"' AND sender_type='admin'");

                //rs=statement.executeQuery("SELECT * FROM Admin_Customers_Messaging WHERE sender_id='"+msgParameterValue+"' " +
                  //                              " AND recipient_id='"+admin.getadminid()+"' AND sender_type='customer' OR sender_id='"+admin.getadminid()+"'" +
                    //                                " AND recipient_id='"+msgParameterValue+"' AND sender_type='admin'");
                //rs=statement.executeQuery("SELECT * FROM Admin_Customers_Messaging WHERE sender_id='"+msgParameterValue+"' AND recipient_id='"+admin.getadminid()+"' " +
                        //"OR sender_id='"+admin.getadminid()+"' AND recipient_id='"+msgParameterValue+"'");

                rs=statement.executeQuery("SELECT * FROM Admin_Customers_Messaging WHERE sender_id='"+admin.getadminid()+"' AND recipient_id='"+msgParameterValue+"' AND sender_type='admin' OR sender_id='"+msgParameterValue+"' AND recipient_id='"+admin.getadminid()+"' AND sender_type='customer'");
                int i=0;
                //out.println(i+" "+msgParameterValue+" C_ChatID adm_id "+admin.getadminid());
                while(rs.next()){
                    adminCustomerList.add(new AdminCustomer(rs.getInt(1),rs.getInt(3),rs.getInt(5),rs.getString(2),rs.getString(4),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
                    //adminCustomerList.add(new AdminCustomer(rs.getInt(1),rs.getInt(3),rs.getInt(4),rs.getString(2),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
                    i++;
                    //out.println(rs.getString(9));
                }
                //out.println(i+" "+msgParameterValue);
                //out.println(" -msg- "+i);

                request.setAttribute("messages",adminCustomerList);
                request.setAttribute("chatID",msgParameterValue);
                //response.getWriter().write(Integer.parseInt(request.getAttribute("chatID")));
                RequestDispatcher requestDispatcher=getServletContext().getRequestDispatcher(response.encodeURL("/AdminChatCustomer.jsp"));
                requestDispatcher.forward(request,response);

            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }

        }else if(msgParameter.equalsIgnoreCase("Profile")){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                rs=statement.executeQuery("SELECT * FROM Handymen.Admin WHERE adminid='"+admin.getadminid()+"'");
                int count=0;

                while(rs.next()){
                    admin=new Admin(rs.getInt(5),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(3),rs.getInt(4),rs.getString(6));
                    //admin=new Admin(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                    count++;
                }

                request.setAttribute("admin_details",admin);

                request.getServletContext().getRequestDispatcher(response.encodeURL("/AdminProfile.jsp")).forward(request,response);
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }else if(msgParameter.equalsIgnoreCase("change_fname")){
            String fname="",lname="",email="",location="",gender="",password="",regdate="",soffered="",username="";
            int phone=0,admin_id=0;
            CustomerMessaging customerMessaging;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                admin_id=admin.getadminid();

                rs=statement.executeQuery("SELECT * FROM Handymen.Admin WHERE adminid='"+admin_id+"'");
                int count=0;

                while(rs.next()){
                    admin=new Admin(rs.getInt(5),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(3),rs.getInt(4),rs.getString(6));
                    //admin=new Admin(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                    count++;
                }

                preparedStatement=connection.prepareStatement("UPDATE Handymen.Admin SET Fname=?,Lname=?,adminid=?,email=?,password=? WHERE adminid=?");
                preparedStatement.setString(1,change_fname);
                preparedStatement.setString(2,admin.getLastName());
                preparedStatement.setString(3,Integer.toString(admin.getadminid()));
                preparedStatement.setString(4,admin.getEmail());
                preparedStatement.setString(5,admin.getPassword());
                preparedStatement.setString(6,Integer.toString(admin.getadminid()));
                int k=0;
                k=preparedStatement.executeUpdate();
                //out.println("username: "+k);
                if(k==1){
                    rs=statement.executeQuery("SELECT * FROM Handymen.Admin WHERE adminid='"+admin.getadminid()+"'");
                    while(rs.next()){
                        admin=new Admin(rs.getInt(5),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(3),rs.getInt(4),rs.getString(6));
                        //admin=new Admin(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                    }

                    request.setAttribute("admin_details",admin);
                    request.getServletContext().getRequestDispatcher(response.encodeURL("/AdminProfile.jsp")).forward(request,response);

                }
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }else if(msgParameter.equalsIgnoreCase("change_lname")){
            String fname="",lname="",email="",location="",gender="",password="",regdate="",soffered="",username="";
            int phone=0,admin_id=0;
            CustomerMessaging customerMessaging;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                admin_id=admin.getadminid();

                rs=statement.executeQuery("SELECT * FROM Handymen.Admin WHERE adminid='"+admin_id+"'");
                int count=0;

                while(rs.next()){
                    admin=new Admin(rs.getInt(5),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(3),rs.getInt(4),rs.getString(6));
                    //admin=new Admin(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                    count++;
                }

                preparedStatement=connection.prepareStatement("UPDATE Handymen.Admin SET Fname=?,Lname=?,adminid=?,email=?,password=? WHERE adminid=?");
                preparedStatement.setString(1,admin.getFirstName());
                preparedStatement.setString(2,change_lname);
                preparedStatement.setString(3,Integer.toString(admin.getadminid()));
                preparedStatement.setString(4,admin.getEmail());
                preparedStatement.setString(5,admin.getPassword());
                preparedStatement.setString(6,Integer.toString(admin.getadminid()));
                int k=0;
                k=preparedStatement.executeUpdate();
                //out.println("username: "+k);
                if(k==1){
                    rs=statement.executeQuery("SELECT * FROM Handymen.Admin WHERE adminid='"+admin.getadminid()+"'");
                    while(rs.next()){
                        admin=new Admin(rs.getInt(5),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(3),rs.getInt(4),rs.getString(6));
                        //admin=new Admin(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                    }

                    request.setAttribute("admin_details",admin);
                    request.getServletContext().getRequestDispatcher(response.encodeURL("/AdminProfile.jsp")).forward(request,response);

                }
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }else if(msgParameter.equalsIgnoreCase("change_email")){
            String fname="",lname="",email="",location="",gender="",password="",regdate="",soffered="",username="";
            int phone=0,admin_id=0;
            CustomerMessaging customerMessaging;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                admin_id=admin.getadminid();

                rs=statement.executeQuery("SELECT * FROM Handymen.Admin WHERE adminid='"+admin_id+"'");
                int count=0;

                while(rs.next()){
                    admin=new Admin(rs.getInt(5),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(3),rs.getInt(4),rs.getString(6));
                    //admin=new Admin(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                    count++;
                }

                preparedStatement=connection.prepareStatement("UPDATE Handymen.Admin SET Fname=?,Lname=?,adminid=?,email=?,password=? WHERE adminid=?");
                preparedStatement.setString(1,admin.getFirstName());
                preparedStatement.setString(2,admin.getLastName());
                preparedStatement.setString(3,Integer.toString(admin.getadminid()));
                preparedStatement.setString(4,change_email);
                preparedStatement.setString(5,admin.getPassword());
                preparedStatement.setString(6,Integer.toString(admin.getadminid()));
                int k=0;
                k=preparedStatement.executeUpdate();
                //out.println("username: "+k);
                if(k==1){
                    rs=statement.executeQuery("SELECT * FROM Handymen.Admin WHERE adminid='"+admin.getadminid()+"'");
                    while(rs.next()){
                        admin=new Admin(rs.getInt(5),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(3),rs.getInt(4),rs.getString(6));
                        //admin=new Admin(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                    }

                    request.setAttribute("admin_details",admin);
                    request.getServletContext().getRequestDispatcher(response.encodeURL("/AdminProfile.jsp")).forward(request,response);

                }
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }else if(msgParameter.equalsIgnoreCase("change_phone")){
            String fname="",lname="",email="",location="",gender="",password="",regdate="",soffered="",username="";
            int phone=0,admin_id=0;
            CustomerMessaging customerMessaging;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                admin_id=admin.getadminid();

                rs=statement.executeQuery("SELECT * FROM Handymen.Admin WHERE adminid='"+admin_id+"'");
                int count=0;

                while(rs.next()){
                    admin=new Admin(rs.getInt(5),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(3),rs.getInt(4),rs.getString(6));
                    //admin=new Admin(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                    count++;
                }

                preparedStatement=connection.prepareStatement("UPDATE Handymen.Admin SET Fname=?,Lname=?,adminid=?,email=?,password=?,phonenumber=? WHERE adminid=?");
                preparedStatement.setString(1,admin.getFirstName());
                preparedStatement.setString(2,admin.getLastName());
                preparedStatement.setString(3,Integer.toString(admin.getadminid()));
                preparedStatement.setString(4,admin.getEmail());
                preparedStatement.setString(5,admin.getPassword());
                preparedStatement.setString(6,Integer.toString(change_phone));
                preparedStatement.setString(7,Integer.toString(admin.getadminid()));
                int k=0;
                k=preparedStatement.executeUpdate();
                //out.println("username: "+k);
                if(k==1){
                    rs=statement.executeQuery("SELECT * FROM Handymen.Admin WHERE adminid='"+admin.getadminid()+"'");
                    while(rs.next()){
                        admin=new Admin(rs.getInt(5),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(3),rs.getInt(4),rs.getString(6));
                        //admin=new Admin(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                    }

                    request.setAttribute("admin_details",admin);
                    request.getServletContext().getRequestDispatcher(response.encodeURL("/AdminProfile.jsp")).forward(request,response);

                }
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }else if(msgParameter.equalsIgnoreCase("change_password")){
            int admin_id=0;
            CustomerMessaging customerMessaging;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                rs=statement.executeQuery("SELECT * FROM Handymen.Admin WHERE email='"+admin.getEmail()+"' AND password='"+current_password+"'");
                int count=0;

                while(rs.next()){
                    admin=new Admin(rs.getInt(5),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(3),rs.getInt(4),rs.getString(6));
                    //admin=new Admin(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                    count++;
                }

                if(count>0){
                    admin_id=admin.getadminid();

                    rs=statement.executeQuery("SELECT * FROM Handymen.Admin WHERE adminid='"+admin_id+"'");
                    while(rs.next()){
                        admin=new Admin(rs.getInt(5),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(3),rs.getInt(4),rs.getString(6));
                        //admin=new Admin(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                        count++;
                    }

                    preparedStatement=connection.prepareStatement("UPDATE Handymen.Admin SET Fname=?,Lname=?,adminid=?,email=?,password=? WHERE adminid=?");
                    preparedStatement.setString(1,admin.getFirstName());
                    preparedStatement.setString(2,admin.getLastName());
                    preparedStatement.setString(3,Integer.toString(admin.getadminid()));
                    preparedStatement.setString(4,admin.getEmail());
                    preparedStatement.setString(5,change_password);
                    preparedStatement.setString(6,Integer.toString(admin.getadminid()));
                    int k=0;
                    k=preparedStatement.executeUpdate();
                    //out.println("password:"+k);
                    if(k==1){
                        httpSession.invalidate();
                        request.getServletContext().getRequestDispatcher("/AdminProfile.jsp").forward(request,response);
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
        }else if(msgParameter.equalsIgnoreCase("viewhandymen")){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                rs=statement.executeQuery("SELECT * FROM Handymen");
                int count=0;
                while(rs.next()){
                    handymen=new Login.Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10),rs.getString(11),rs.getString(12));
                    count++;
                    viewHandymenList.add(handymen);
                }
                request.setAttribute("handymenlist",viewHandymenList);
                request.getServletContext().getRequestDispatcher(response.encodeURL("/AdminViewHandymen.jsp")).forward(request,response);
            }catch (Exception e){

            }
        }else if(msgParameter.equalsIgnoreCase("viewhandyman")){
            double ratings=0,avg_rating=0;
            int count_ratings=0;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                rs=statement.executeQuery("SELECT * FROM Handymen.Handymen WHERE handymanid='"+msgParameterValue+"'");
                int count=0;
                while(rs.next()){
                    handymen=new Login.Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10),rs.getString(11),rs.getString(12));
                    count++;
                }
                rs=statement.executeQuery("SELECT * FROM Items_by_handymen WHERE hid='"+msgParameterValue+"'");
                int i=0;
                while (rs.next()){
                    i++;
                    postsList.add(new Posts(rs.getInt(3),rs.getInt(4),rs.getDouble(6),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(5),rs.getString(8),rs.getString(9)));
                }
                rs=statement.executeQuery("SELECT * FROM HandymenRatings WHERE handymanid='"+msgParameterValue+"'");
                while(rs.next()){
                    count_ratings++;
                    ratings+=rs.getDouble(7);
                }
                avg_rating=ratings/count_ratings;

                request.setAttribute("handyman",handymen);
                request.setAttribute("postsList",postsList);
                request.setAttribute("handyman_ratings",avg_rating);
                request.getServletContext().getRequestDispatcher(response.encodeURL("/AdminViewHandyman.jsp")).forward(request,response);
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }else if(msgParameter.equalsIgnoreCase("suspendadmin")){
            int handyman_id=Integer.parseInt(msgParameterValue);
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                rs=statement.executeQuery("SELECT * FROM Handymen.Handymen WHERE handymanid='"+msgParameterValue+"'");
                int count=0;
                while(rs.next()){
                    handymen=new Login.Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10),rs.getString(11),rs.getString(12));
                    count++;
                }

                preparedStatement=connection.prepareStatement("UPDATE Handymen.Handymen SET Fname=?,Lname=?,username=?,handymanid=?,email=?,location=?,phoneno=?,password=?,gender=?,Regdate=?,soffered=?,status=? WHERE handymanid=?");
                preparedStatement.setString(1,handymen.getFirstName());
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
                preparedStatement.setString(12,"suspended");
                preparedStatement.setString(13,Integer.toString(handyman_id));
                int k=0;
                k=preparedStatement.executeUpdate();
                if(k==1){
                    rs2=statement.executeQuery("SELECT * FROM Items_by_handymen WHERE hid='"+msgParameterValue+"'");
                    int i=0;
                    while (rs2.next()){
                        i++;
                        postsList.add(new Posts(rs2.getInt(3),rs2.getInt(4),rs2.getDouble(6),rs2.getString(1),rs2.getString(2),rs2.getString(7),rs2.getString(5),rs2.getString(8),rs2.getString(9)));
                    }
                    request.setAttribute("handyman",handymen);
                    request.setAttribute("postsList",postsList);
                    request.getServletContext().getRequestDispatcher(response.encodeURL("/AdminViewHandyman.jsp")).forward(request,response);
                }else {
                    out.println("Error!");
                }
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }else if(msgParameter.equalsIgnoreCase("unsuspendadmin")){
            int handyman_id=Integer.parseInt(msgParameterValue);
            try{
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();

                rs=statement.executeQuery("SELECT * FROM Handymen.Handymen WHERE handymanid='"+msgParameterValue+"'");
                int count=0;
                while(rs.next()){
                    handymen=new Login.Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10),rs.getString(11),rs.getString(12));
                    count++;
                }

                preparedStatement=connection.prepareStatement("UPDATE Handymen.Handymen SET Fname=?,Lname=?,username=?,handymanid=?,email=?,location=?,phoneno=?,password=?,gender=?,Regdate=?,soffered=?,status=? WHERE handymanid=?");
                preparedStatement.setString(1,handymen.getFirstName());
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
                preparedStatement.setString(12,"active");
                preparedStatement.setString(13,Integer.toString(handyman_id));
                int k=0;
                k=preparedStatement.executeUpdate();
                if(k==1){
                    rs2=statement.executeQuery("SELECT * FROM Items_by_handymen WHERE hid='"+msgParameterValue+"'");
                    int i=0;
                    while (rs2.next()){
                        i++;
                        postsList.add(new Posts(rs2.getInt(3),rs2.getInt(4),rs2.getDouble(6),rs2.getString(1),rs2.getString(2),rs2.getString(7),rs2.getString(5),rs2.getString(8),rs2.getString(9)));
                    }
                    request.setAttribute("handyman",handymen);
                    request.setAttribute("postsList",postsList);
                    request.getServletContext().getRequestDispatcher(response.encodeURL("/AdminViewHandyman.jsp")).forward(request,response);
                }else {
                    out.println("Error!");
                }
            }catch (Exception e){
                e.printStackTrace();
                out.println(e.getMessage());
            }
        }
    }
}
