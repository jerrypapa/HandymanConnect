package service;

import Login.Admin;
import Login.Customer;
import Login.Handymen;
import db.DBConnection;
import Customer.Services;

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
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;


public class LoginServlet extends HttpServlet {
    LoginService loginService;
    Admin admin;
    Handymen handymen;
    Customer customer;
    private String err;
    PrintWriter out;
    HttpSession httpSession;
    List<Services> servicesList;

    public LoginServlet(){
        this.loginService=new LoginService();
        this.admin=new Admin();
        this.handymen=new Handymen();
        this.customer=new Customer();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        httpSession=request.getSession();
        if(request.getParameter("usertype").equalsIgnoreCase("handyman")){

            out=response.getWriter();
            String email=request.getParameter("h_email");
            String password=request.getParameter("h_password");

            //out.println(email+" "+password);

            handymen.setEmail(email);
            handymen.setPassword(password);

            PrintWriter out=response.getWriter();
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn=DBConnection.getConnection();

                Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery("SELECT * FROM Handymen WHERE email='"+email+"' AND password='"+password+"'");

                int count=0;

                while(rs.next()){
                    handymen=new Login.Handymen(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10),rs.getString(11),rs.getString(12));
                    count++;
                }
                if(count>0){
                    //Redirect to handyman dashboard
                    httpSession.setAttribute("logged_handyman",handymen);
                    String url="/HDashboard.jsp";
                    //out.println(handymen.getFirstName());
                    Handymen h=(Handymen)httpSession.getAttribute("logged_handyman");
                    //out.println(h.getLastName());
                    //response.sendRedirect("./dashboard/index.html");
                    RequestDispatcher requestDispatcher=
                            getServletContext().getRequestDispatcher(response.encodeURL(url));
                    requestDispatcher.forward(request,response);
                }else{
                    out.println("Invalid username or password");
                }
                //out.println(count);
            }catch (Exception e){
                out.println(e.getMessage()+" ->h");
            }
        }

        if(request.getParameter("usertype").equalsIgnoreCase("admin")){

            out=response.getWriter();
            String email=request.getParameter("a_email");
            String password=request.getParameter("a_password");

            //out.println(email+" "+password);

            admin.setEmail(email);
            admin.setPassword(password);

            PrintWriter out=response.getWriter();
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn=DBConnection.getConnection();
                //Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Handymen","root","Wilfrida2017");

                Statement st=conn.createStatement();
                ResultSet resultSet=st.executeQuery("SELECT * FROM Admin WHERE email='"+email+"' AND password='"+password+"'");

                int count=0;

                while(resultSet.next()){
                    admin=new Admin(resultSet.getInt(5),resultSet.getString(1),resultSet.getString(2),resultSet.getString(7),resultSet.getString(3),resultSet.getInt(4),resultSet.getString(6));
                    //admin=new Admin(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
                    count++;
                }
                if(count>0){
                    //Redirect to Customer dashboard
                    httpSession.setAttribute("logged_admin",admin);
                    String url="/ADashboard.jsp";
                    //response.sendRedirect("./dashboard/index.html");
                    RequestDispatcher requestDispatcher=
                            getServletContext().getRequestDispatcher(response.encodeURL(url));
                    requestDispatcher.forward(request,response);
                }else{
                    out.println("Invalid username or password");
                }
                //out.println(count);
            }catch (Exception e){
                out.println(e.getMessage()+"->a");
            }

        }

        if(request.getParameter("usertype").equalsIgnoreCase("customer")){

            out=response.getWriter();
            String email=request.getParameter("c_email");
            String password=request.getParameter("c_password");

            //out.println(email+" "+password);

            customer.setEmail(email);
            customer.setPassword(password);

            PrintWriter out=response.getWriter();
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn=DBConnection.getConnection();

                Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery("SELECT * FROM Customers WHERE email='"+email+"' AND password='"+password+"'");

                int count=0;

                while(rs.next()){
                    customer=new Customer(rs.getInt(7),rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(3),rs.getInt(5),rs.getString(9),rs.getString(6),rs.getString(8),rs.getString(10));
                    count++;
                }
                if(count>0){
                    //Redirect to Customer dashboard
                    httpSession.setAttribute("logged_customer",customer);
                    String url="/CDashboard.jsp";
                    //out.println(customer.getFirstName());
                    Customer c=(Customer)httpSession.getAttribute("logged_customer");
                    response.sendRedirect("/UserServlet?Posts");
                    /*RequestDispatcher requestDispatcher=
                            getServletContext().getRequestDispatcher(response.encodeURL("/UserServlet?Posts"));
                    requestDispatcher.forward(request,response);*/
                    /*rs=st.executeQuery("SELECT * FROM Items_by_handymen ORDER BY RAND()");
                    while (rs.next()){
                        servicesList.add(new Services(rs.getInt(3),rs.getInt(4),rs.getDouble(6),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(5),rs.getString(8),rs.getString(9)));
                    }
                    request.setAttribute("servicesList",servicesList);
                    request.getServletContext().getRequestDispatcher(response.encodeURL("/UsersHome.jsp")).forward(request,response);*/
                    //out.println(h.getLastName());
                    /*response.sendRedirect("./dashboard/index.html");
                    RequestDispatcher requestDispatcher=
                            getServletContext().getRequestDispatcher(response.encodeURL(url));
                    requestDispatcher.forward(request,response);*/
                }else{
                    out.println("Invalid username or password");
                }
                //out.println(count);
            }catch (Exception e){
                out.println(e.getMessage()+"->c");
                e.printStackTrace();
            }

        }

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email=request.getParameter("email");
        String password=request.getParameter("password");

        admin.setEmail(email);
        admin.setPassword(password);

        httpSession=request.getSession();

        try {
            if(this.loginService.verifyLogin(admin)!=null){
                httpSession.setAttribute("logged_admin",admin);
                //request.getRequestDispatcher("Homepage.html").forward(request,response);
            }else{
                err="ERROR OCCURRED";
                request.getRequestDispatcher("AdminLogin.jsp").forward(request,response);
            }
        }catch (Exception e){
            err=e.toString();
            request.getRequestDispatcher("AdminLogin.jsp").forward(request,response);
        }

    }
}
