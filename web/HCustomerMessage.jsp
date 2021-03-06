<%@page import="Login.Handymen" %>
<%@ page import="java.util.List" %>
<%@ page import="Handyman.AdminMessaging" %>
<%@ page import="Handyman.CustomerMessaging" %>
<%@ page import="java.util.ArrayList" %>

<%
    HttpSession httpSession=request.getSession();
    Handymen handymen=(Handymen)httpSession.getAttribute("logged_handyman");
    if(handymen==null){
        response.sendRedirect("homepage/Homepage2.html");
    }else if(handymen!=null){
        List<AdminMessaging> adminMessagingList=(List<AdminMessaging>)request.getAttribute("adminMessagingList");
        List<CustomerMessaging> customerMessagingList=(List<CustomerMessaging>)request.getAttribute("customerMessagingList");
        int customerid=(Integer)request.getAttribute("customerid");
        String customername=(String)request.getAttribute("customername");

        ArrayList<String> names=new ArrayList<>();
        for (int a=0;a<customerMessagingList.size();a++){
            if(names.size()==0){
                names.add(customerMessagingList.get(a).getSender_name());
            }else{
                for (int b=0;b<names.size();b++){
                    if(names.contains(customerMessagingList.get(a).getSender_name())==false){
                        names.add(customerMessagingList.get(a).getSender_name());
                    }
                }
            }
        }

%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Welcome||expert</title>

    <!-- Bootstrap core CSS -->
    <link href="./bootstrap-4.1.2-dist/css/bootstrap.min.css" rel="stylesheet">

    <script type="text/javascript" src="bootstrap-4.1.2-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="bootstrap-4.1.2-dist/js/popper.min.js"></script>
    <script type="text/javascript" src="js/jquery.js"></script>

    <!-- Custom styles for this template -->
</head>

<body>
<nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Handyman Dashboard</a>
    <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href='<%=response.encodeURL("LogoutServlet?userType=handyman")%>'>Sign out</a>
        </li>
    </ul>
</nav>

<div class="container-fluid">
    <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <div class="sidebar-sticky">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="home"></span>
                            <%=handymen.getUsername()%><span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="HandymanServlet?Profile=ok">
                            <span data-feather="home"></span>
                            My profile <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="HandymanServlet?PendingJobs=ok">
                            <span data-feather="file"></span>
                            Pending jobs
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="HandymanServlet?ProgressJobs=ok">
                            <span data-feather="file"></span>
                            In-progress jobs
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="HandymanServlet?CompleteJobs=ok">
                            <span data-feather="file"></span>
                            Finished jobs
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="HandymanServlet?Messages=ok">
                            <span data-feather="file"></span>
                            Messages
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="HandymanServlet?PostItem=ok">
                            <span data-feather="users"></span>
                            post item/service
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="HandymanServlet?MyPosts=ok">
                            <span data-feather="users"></span>
                            My posts
                        </a>
                    </li>
                </ul>
            </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Chat</h1>
                <div class="btn-toolbar mb-2 mb-md-0">

                </div>
            </div>
            <%
                if(handymen.getStatus().equalsIgnoreCase("suspended")){
                    out.println("<div class='alert alert-danger' role='alert'>" +
                            "  Your account has been suspended! <a href='HandymanServlet?Messages=ok' class='alert-link'>Contact the admin</a>." +
                            "</div>");
                }else{
            %>
            <div class="container-fluid" id="admin">
                <div class="w-75" >
                    <div class="w-100 card" style="height: 500px;overflow-y: scroll;">
                        <div class="card-body">
                            <%
                                if(customerMessagingList!=null){
                                    for (int i=0;i<customerMessagingList.size();i++){
                                        /**/
                                        if(customerMessagingList.get(i).getSender_type().equalsIgnoreCase("handyman")){
                                            out.print("<div><div class='card' style='text-align: left;padding: 5px;color: red;width: fit-content;'>");
                                            out.print("<div>"+customerMessagingList.get(i).getSender_name()+"</div>");
                                            out.print("<div>"+customerMessagingList.get(i).getMessage()+"</div>");
                                            out.print("</div></div>");
                                        }else if(customerMessagingList.get(i).getSender_type().equalsIgnoreCase("customer")){
                                            out.print("<div><div class='card' style='text-align: right;padding: 5px;color: blue;width: fit-content;'>");
                                            out.print("<div>Me</div>");
                                            out.print("<div>"+customerMessagingList.get(i).getMessage()+"</div>");
                                            out.print("</div></div>");
                                        }
                                    }
                                }else{
                                    out.println("Empty");
                                }
                            %>
                        </div>
                    </div>
                    <div>
                        <form action="HandymanServlet" method="get" id="message_form">
                            <input type="hidden" name="customer_id" value="<%=customerid%>"/>
                            <input type="hidden" name="customer_sent_name" value="<%=customername%>"/>
                            <div class="form-row align-items-center">
                                <div class="col-auto my-1">
                                    <input type="text" name="customer_message" class="form-control" id="message" placeholder="Your message here..." required/>
                                </div><input type="hidden" name="customer_msg" value="customer_msg"/>
                                <div class="col-auto my-1">
                                    <button type="submit" class="btn btn-primary"><span class="fa fa-send"></span></button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <%
                    }

            %>
        </main>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
<script src="../../assets/js/vendor/popper.min.js"></script>
<script src="../../dist/js/bootstrap.min.js"></script>

<!-- Icons -->
<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
<script>
    feather.replace()
</script>

<!-- Graphs -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
<script>
    $(function(){
        $('#customers').hide();
        $('#showCustomers').on('click',function (e) {
            //e.preventDefault();
            $(this).addClass('active');
            $('#showAdmin').removeClass('active');
            $('#admin').hide();
            $('#customers').show();
        });
        $('#showAdmin').on('click',function (e) {
            //e.preventDefault();
            $(this).addClass('active');
            $('#showCustomers').removeClass('active');
            $('#customers').hide();
            $('#admin').show();
        });
    });
</script>
</body>
</html>
<%
    }%>
<style>
    body {
        font-size: .875rem;
    }

    .feather {
        width: 16px;
        height: 16px;
        vertical-align: text-bottom;
    }

    /*
     * Sidebar
     */

    .sidebar {
        position: fixed;
        top: 0;
        bottom: 0;
        left: 0;
        z-index: 100; /* Behind the navbar */
        padding: 48px 0 0; /* Height of navbar */
        box-shadow: inset -1px 0 0 rgba(0, 0, 0, .1);
    }

    .sidebar-sticky {
        position: relative;
        top: 0;
        height: calc(100vh - 48px);
        padding-top: .5rem;
        overflow-x: hidden;
        overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
    }

    @supports ((position: -webkit-sticky) or (position: sticky)) {
        .sidebar-sticky {
            position: -webkit-sticky;
            position: sticky;
        }
    }

    .sidebar .nav-link {
        font-weight: 500;
        color: #333;
    }

    .sidebar .nav-link .feather {
        margin-right: 4px;
        color: #999;
    }

    .sidebar .nav-link.active {
        color: #007bff;
    }

    .sidebar .nav-link:hover .feather,
    .sidebar .nav-link.active .feather {
        color: inherit;
    }

    .sidebar-heading {
        font-size: .75rem;
        text-transform: uppercase;
    }

    /*
     * Content
     */

    [role="main"] {
        padding-top: 48px; /* Space for fixed navbar */
    }

    /*
     * Navbar
     */

    .navbar-brand {
        padding-top: .75rem;
        padding-bottom: .75rem;
        font-size: 1rem;
        background-color: rgba(0, 0, 0, .25);
        box-shadow: inset -1px 0 0 rgba(0, 0, 0, .25);
    }

    .navbar .form-control {
        padding: .75rem 1rem;
        border-width: 0;
        border-radius: 0;
    }

    .form-control-dark {
        color: #fff;
        background-color: rgba(255, 255, 255, .1);
        border-color: rgba(255, 255, 255, .1);
    }

    .form-control-dark:focus {
        border-color: transparent;
        box-shadow: 0 0 0 3px rgba(255, 255, 255, .25);
    }

    /*
     * Utilities
     */

    .border-top { border-top: 1px solid #e5e5e5; }
    .border-bottom { border-bottom: 1px solid #e5e5e5; }

</style>