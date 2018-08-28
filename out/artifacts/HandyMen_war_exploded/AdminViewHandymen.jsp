<%@page import="Login.Admin" %>
<%@ page import="java.util.List" %>
<%@ page import="Login.Handymen" %>

<%
    HttpSession httpSession=request.getSession();
    Admin admin=(Admin)httpSession.getAttribute("logged_admin");
    if(admin==null){
        response.sendRedirect("homepage/Homepage2.html");
    }else if(admin!=null){
        List<Handymen> handymenList=(List<Handymen>)request.getAttribute("handymenlist");

%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Handymen :: Admin</title>

    <!-- Bootstrap core CSS -->
    <script type="text/javascript" src="homepage/assets/js/jquery-min.js"></script>
    <link rel="stylesheet" href="/bootstrap-4.1.2-dist/css/bootstrap.min.css">
    <script type="text/javascript" src="/bootstrap-4.1.2-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/bootstrap-4.1.2-dist/js/popper.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.slim.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Custom styles for this template -->
</head>

<body>
<nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Admin Dashboard</a>
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
                        <a class="nav-link" href="AdminServlet?Messages=ok">
                            <span data-feather="file"></span>
                            Messages
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="AdminServlet?viewhandymen=true">
                            <span data-feather="shopping-cart"></span>
                            View Handymen
                        </a>
                    </li>
                    
                </ul>
            </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Profile</h1>
                <!--<div class="btn-toolbar mb-2 mb-md-0">
                    <div class="btn-group mr-2">
                        <button class="btn btn-sm btn-outline-secondary">Share</button>
                        <button class="btn btn-sm btn-outline-secondary">Export</button>
                    </div>
                    <button class="btn btn-sm btn-outline-secondary dropdown-toggle">
                        <span data-feather="calendar"></span>
                        This week
                    </button>
                </div>-->
            </div>
            <div>
                <nav class="navbar navbar-light bg-white flex-md-nowrap p-0 shadow w-100" style="border:1px solid rgba(4,14,60,0.9);margin-bottom: 12px;line-height: 10px;">
                    <input class="form-control form-control-dark w-100 dropdown" name="searchhandyman" id="search" type="text" placeholder="Search" aria-label="Search">
                    <div class="dropdown-menu w-100">
                        <ul class="list-group w-100">
                            <a class="list-group-item list-group-item-action">Option 1</a>
                        </ul>
                    </div>
                </nav>
            </div>
            <%
            if(handymenList.size()>0){
            %>
            <div class="container-fluid w-100">
                <ul class="list-group" id="details">
                <%
                for(int i=0;i<handymenList.size();i++){
                %>
                    <a href="AdminServlet?viewhandyman=<%=handymenList.get(i).getHandymanid()%>" class="list-group-item list-group-item-action"><span><%=handymenList.get(i).getUsername()%></span>&nbsp;&nbsp;&nbsp;<span><%=handymenList.get(i).getSoffered()%></span></a>
                <%
                }
                %>
                </ul>
            </div>
            <%
            }else{
                out.println("There are no registered handymen!");
            }
            %>
        </main>
    </div>
</div>

<!-- Icons -->
<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
<script>
    feather.replace()
</script>

<!-- Graphs -->
<script type="text/javascript">
    //alert('R');
    $(document).ready(function () {
        $('#search').on('keyup',function () {
           var search_key=$(this).val();
           //alert(search_key);
           if(search_key.length>0){
               $.ajax({
                   url: '/AdminServlet',
                   method: 'get',
                   data: {'searchhandyman':search_key},
                   dataType: 'json',
                   success: function (result) {
                       //alert(result);
                       var fname="";
                       var fnames=[];
                       $('.dropdown-menu ul').html('');
                       $('.dropdown-menu').slideDown('slow');
                       $.each(result,function (index, element) {
                           $('.dropdown-menu ul').append('<a href="AdminServlet?viewhandyman='+element.handymanid+'" class="list-group-item list-group-item-action">'+element.FirstName+' '+element.LastName+'</a>');
                       });
                       //alert(fnames.toString()+" "+fnames.length);
                   },
                   error: function () {
                       alert('Connection error!');
                   }
               });
           }else{
               $('.dropdown-menu ul').html('');
               $('.dropdown-menu').slideUp('slow');
           }
        });
    });
    /*$(function () {
        alert('ready');
       $('#search').onkeyup(function () {
           var search_key=$(this).val();
           alert(search_key);
           $.ajax({
               url: '/AdminServlet',
               method: 'get',
               data: {'searchhandyman':search_key},
               success: function (result) {
                   alert(result);
               },
               error: function () {
                   alert('Error connection');
               };
           });
       });
    });*/
</script>
</body>
</html>
<%
    }%>
<style>
    body {
        font-size: .875rem;
    }

    #details li span{
        color: rgba(17,58,255,0.88);
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