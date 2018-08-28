import Handyman.Posts;
import Login.Handymen;
import db.DBConnection;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/HUpdateUploadItem")
public class HUpdateUploadItem extends HttpServlet {
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet rs;
    Handymen handymen;
    HttpSession httpSession;
    PrintWriter out;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cat="",img="",n="",hname="",postdate="",desc="";
        int id=0,hid=0,j=0;
        double pr=0;
        String i="",value="";
        out=response.getWriter();
        httpSession=request.getSession();
        handymen=(Handymen)httpSession.getAttribute("logged_handyman");
        String name="";
        final String UPLOAD_DIRECTORY = "/home/papa/IdeaProjects/HandyMen/web/resources/Uploads";
        //process only if its multipart content
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                //int post_id=Integer.parseInt(request.getParameter("post_id"));
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        name = new File(item.getName()).getName();
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                    }else{
                        i = item.getFieldName();
                        value = item.getString();
                        out.println(i+" "+value);
                    }
                }
                //File uploaded successfully
                out.println("File Uploaded Successfully: "+name);
                //request.setAttribute("message", "File Uploaded Successfully");

                /*handymen=(Handymen)httpSession.getAttribute("logged_handyman");
                request.setAttribute("image_url",name);
                httpSession.setAttribute("image_url",name);
                request.getServletContext().getRequestDispatcher(response.encodeURL("/HPostItem.jsp")).forward(request,response);*/
            } catch (Exception ex) {
                out.println("File Upload Failed due to "+ex.getMessage());
                ex.printStackTrace();
//                    request.setAttribute("message", "File Upload Failed due to " + ex);
            }
        }else{
            out.println("Sorry this Servlet only handles file upload request");
            //request.setAttribute("message","Sorry this Servlet only handles file upload request");
        }
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection=DBConnection.getConnection();
            statement=connection.createStatement();
            rs=statement.executeQuery("SELECT * FROM Items_by_handymen WHERE id='"+value+"'");
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
            preparedStatement.setString(8,name);
            preparedStatement.setString(9,desc);
            preparedStatement.setString(10,value);
            j=preparedStatement.executeUpdate();
            out.println(j);
            if(j==1){
                rs=statement.executeQuery("SELECT * FROM Items_by_handymen WHERE hid='"+handymen.getHandymanid()+"' AND id='"+value+"'");
                List<Posts> postsList=new ArrayList<>();
                int a=0;
                while (rs.next()){
                    a++;
                    postsList.add(new Posts(rs.getInt(3),rs.getInt(4),rs.getDouble(6),rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(5),rs.getString(8),rs.getString(9)));
                }
                if(a>0){
                    request.setAttribute("postsList",postsList);
                    request.getServletContext().getRequestDispatcher(response.encodeURL("/HPost.jsp")).forward(request,response);
                }
            }
        }catch (Exception e){
            out.println("Err 2: "+e.getMessage());
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
