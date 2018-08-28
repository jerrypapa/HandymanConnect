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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@WebServlet("/HUploadItem")
public class HUploadItem extends HttpServlet {
    Connection connection;
    Statement statement;
    ResultSet rs;
    Handymen handymen;
    HttpSession httpSession;
    PrintWriter out;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out=response.getWriter();
        httpSession=request.getSession();
        String name="";
        final String UPLOAD_DIRECTORY = "/home/papa/IdeaProjects/HandyMen/web/resources/Uploads";
        //process only if its multipart content
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        name = new File(item.getName()).getName();
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                    }
                }
                //File uploaded successfully
                out.println("File Uploaded Successfully: "+name);
                //request.setAttribute("message", "File Uploaded Successfully");
                Class.forName("com.mysql.jdbc.Driver");
                connection=DBConnection.getConnection();
                statement=connection.createStatement();
                handymen=(Handymen)httpSession.getAttribute("logged_handyman");
                request.setAttribute("image_url",name);
                httpSession.setAttribute("image_url",name);
                request.getServletContext().getRequestDispatcher(response.encodeURL("/HPostItem.jsp")).forward(request,response);
            } catch (Exception ex) {
                out.println("File Upload Failed due to "+ex.getMessage());
                ex.printStackTrace();
//                    request.setAttribute("message", "File Upload Failed due to " + ex);
            }
        }else{
            out.println("Sorry this Servlet only handles file upload request");
            //request.setAttribute("message","Sorry this Servlet only handles file upload request");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
