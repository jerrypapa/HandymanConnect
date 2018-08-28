<%@page import="Login.Admin" %>
<%@ page import="java.util.List" %>
<%@ page import="Admin.Handymen" %>
<%@ page import="Admin.Customers" %>
<%@ page import="java.util.ArrayList" %>

<%
    HttpSession httpSession=request.getSession();
    Admin admin=(Admin)httpSession.getAttribute("logged_admin");
    if(admin==null){
        response.sendRedirect("homepage/Homepage2.html");
    }else if(admin!=null){

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
                <ul class="nav nav-pills">
                    <li class="nav-item">
                        <a class="nav-link active" href="#handymen" id="showHandymen">Handymen</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#customers" id="showCustomers">Customers</a>
                    </li>
                </ul>
                <div class="w-75" id="handymen">
                    <%
                        List<Handymen> handymenList=(List<Handymen>)request.getAttribute("handymenList");
                        if(handymenList.size()>0){
                    %>
                    <ul class="list-group list-group-flush">
                    <%
                            //int handymenIds[]={};
                            for(int i=0;i<handymenList.size();i++){
                                //handymenIds[i]=handymenList.get(i).getId();
                    %>
                        <li class="list-group-item h_chat" data="<%=handymenList.get(i).getId()%>" id="chat_id+<%=handymenList.get(i).getId()%>"><%=handymenList.get(i).getUsername()%></li>
                    <%
                            }
                    %>
                    </ul>
                    <%
                        }
                    %>
                </div>
                <div class="w-75" id="customers">
                    <%
                    List<Customers> customersList=(List<Customers>)request.getAttribute("customersList");
                    if(customersList.size()>0){
                    %>
                    <ul class="list-group list-group-flush">
                        <%
                            //int handymenIds[]={};
                            for(int i=0;i<customersList.size();i++){
                                //handymenIds[i]=handymenList.get(i).getId();
                        %>
                        <li class="list-group-item c_chat" data="<%=customersList.get(i).getCustomerId()%>" id="chat_id+<%=customersList.get(i).getCustomerId()%>"><%=customersList.get(i).getUsername()%></li>
                        <%
                            }
                        %>
                    </ul>
                    <%
                    }
                    %>
                </div>
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
        check_msgs();
        setInterval(check_msgs,15000);

        $('#customers').hide();
        $('#showCustomers').on('click',function (e) {
           //e.preventDefault();
           $(this).addClass('active');
           $('#showHandymen').removeClass('active');
           $('#handymen').slideUp('slow');
           $('#customers').slideDown('slow');
        });
        $('#showHandymen').on('click',function (e) {
            //e.preventDefault();
            $(this).addClass('active');
            $('#showCustomers').removeClass('active');
            $('#customers').slideUp('slow');
            $('#handymen').slideDown('slow');
        });

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
                    $.ajax({
                        url: 'ServletAdmin',
                        method: 'post',
                        data: {'check_new_msgs':'customers'},
                        dataType: 'json',
                        success: function (result_2) {
                            $.each(result,function (index, element) {
                                //alert(element.sender_name);
                                i++;
                            });
                            $('#msg_count').html(i);
                        }
                    });
                },
                error: function () {
                    //alert('Something went wrong!');
                }
            });
        }

        $('.h_chat').on('click',function(e){
            window.open('AdminServlet?ChatID='+$(this).attr('data'),'_self');
            //window.open('AdminChatHandymanServlet?ChatID='+$(this).attr('data'),'_self');
        });
        $('.c_chat').on('click',function(e){
            window.open('AdminServlet?C_ChatID='+$(this).attr('data'),'_self');
            //window.open('AdminChatHandymanServlet?ChatID='+$(this).attr('data'),'_self');
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

    .list-group-item: hover{
        background: rgb(0,0,0,0.4);
    }

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