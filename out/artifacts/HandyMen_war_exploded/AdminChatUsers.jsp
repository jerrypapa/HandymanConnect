<%@page import="Login.Admin" %>
<%@ page import="java.util.List" %>
<%@ page import="Admin.Customers" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Messages.AdminUsers" %>

<%
    HttpSession httpSession=request.getSession();
    Admin admin=(Admin)httpSession.getAttribute("logged_admin");

    int customerId=0;
    int chatId=0;
    if(admin==null){
        response.sendRedirect("homepage/Homepage2.html");
    }else if(admin!=null){
        List<AdminUsers> textList=(List<AdminUsers>)request.getAttribute("messages");
        if(textList.size()>0){
            for (int i=0;i<textList.size();i++){
                if(textList.get(i).getSender_type().equalsIgnoreCase("customer")){
                    customerId=textList.get(i).getId();
                }
            }
        }else{

        }
        chatId=Integer.parseInt((String)request.getAttribute("chatID"));

%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Welcome || Admin</title>

    <!-- Bootstrap core CSS -->
    <link href="./bootstrap-4.1.2-dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>

    <script type="text/javascript" src="bootstrap-4.1.2-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="bootstrap-4.1.2-dist/js/popper.min.js"></script>
    <script type="text/javascript" src="js/jquery.js"></script>

    <!-- Custom styles for this template -->
</head>

<body>
<nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Admin Connect</a>
    <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href='<%=response.encodeURL("LogoutServlet?userType=admin")%>'>Sign out</a>
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
                            <%=admin.getFirstName()%><span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="AdminServlet?Profile=ok">
                            <span data-feather="home"></span>
                            View my profile <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="AdminServlet?Messages=ok">
                            <span data-feather="file"></span>
                            Messages
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="AdminServlet?viewhandymen=true">
                            <span data-feather="shopping-cart"></span>
                            View Handymen
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="users"></span>
                            View Carpenters
                        </a>
                    </li>
                </ul>


            </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Messages</h1>
            </div>
            <div class="w-100">
                <div>
                    <a href="AdminServlet?Messages=ok" class="btn btn-outline-primary"><span class="fa fa-arrow-left"></span>&nbsp;Back</a>
                </div>
                <br/>
                <div class="w-75" id="chat_area">
                    <%

                        if(textList.size()>0){
                    %>
                    <div class="w-100 card">
                        <div class="card-body">
                            <%
                                for (int i=0;i<textList.size();i++){
                                    if(textList.get(i).getSender_type().equalsIgnoreCase("admin")){
                            %>
                            <div class="card" style="float: right;padding: 5px;color: blue;">
                                <div style="text-align: right;"><a href="AdminServlet?delete_msg=<%=textList.get(i).getId()%>" id="delete_msg" class="delete_msg" data="AdminServlet?delete_msg=<%=textList.get(i).getId()%>"><span>&times;</span></a> </div>
                                <div>Me</div>
                                <%=textList.get(i).getMessage()%>
                            </div><br/><br/>
                            <%
                            }else if(textList.get(i).getSender_type().equalsIgnoreCase("customer")){
                            %>
                            <div class="card" style="float: left;padding: 5px;color: red;">
                                <div style="text-align: right;"><a href="AdminServlet?delete_msg=<%=textList.get(i).getId()%>" id="delete_msg2" class="delete_msg" data="AdminServlet?delete_msg=<%=textList.get(i).getId()%>"><span>&times;</span></a> </div>
                                <div><%=textList.get(i).getSender_name()%></div>
                                <div><%=textList.get(i).getMessage()%></div>
                            </div><br/><br/>
                            <%
                                    }
                                }
                            %>
                        </div>
                    </div>

                    <%
                        }
                    %>
                </div>
                <div><div id="p2" class="mdl-progress mdl-js-progress mdl-progress__indeterminate"></div></div>
                <form action="AdminServlet2" method="post" id="message_form">
                    <input type="hidden" name="admin_msg" value="admin_msg"/>
                    <input type="hidden" name="customer_id" value="<%=customerId%>"/>
                    <input type="hidden" name="chatId" value="<%=chatId%>"/>
                    <div class="form-row align-items-center">
                        <div class="col-auto my-1">
                            <input type="text" name="message" class="form-control" id="message" placeholder="Your message here..." required/>
                        </div>
                        <div class="col-auto my-1">
                            <button type="submit" class="btn btn-primary"><span class="fa fa-send"></span></button>
                        </div>
                    </div>
                </form>
            </div>
        </main>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<!--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
--><script>
    //    feather.replace()
</script>
<script>
    $(function(){
        $('#p2').hide();
        check_msgs();
        setInterval(check_msgs,10000);
        function check_msgs(){
            $.ajax({
                url: 'ServletAdmin',
                method: 'post',
                data: {'check_new_msgs':'ok'},
                dataType: 'json',
                success: function (result) {
                    var i=0;
                    $.each(result,function (index, element) {
                        //alert(element.sender_name);
                        i++;
                    });
                    $('#msg_count').html(result.length);
                },
                error: function () {
                    alert('Something went wrong!');
                }
            });
        }
        $('#message_form').on('submit',function (e) {
            e.preventDefault();
            $('#p2').show();
            $.ajax({
                url: $(this).attr('action'),
                method: $(this).attr('method'),
                data: $(this).serialize(),
                success: function (result) {
                    if(result!=null){
                        $('#p2').hide();
                        window.open('AdminServlet?ChatID='+result,'_self');
                    }
                },
                error: function () {
                    alert("Something went wrong-!");
                }
            });
        });

        $('.delete_msg').on('click',function (e) {
            e.preventDefault();
            alert($(this).attr('data'));
            $.ajax({
                url: $(this).attr('data'),
                method: 'get',
                data: {'':''},
                success: function (result) {
                    alert(result);
                },
                error: function () {
                    alert('Something went wrong-><-!');
                }
            });
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

    #chat_area{
        height: 400px;
        overflow-y: scroll;
        overflow-x: hidden;
    }

    .list-group-item{
        cursor: pointer;
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