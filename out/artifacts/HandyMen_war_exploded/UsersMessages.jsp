<%@page import="Login.Handymen" %>
<%@ page import="Login.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="Customer.AdminMessaging" %>
<%@ page import="Login.Admin" %>

<%
    HttpSession httpSession=request.getSession();
    Customer customer=(Customer)httpSession.getAttribute("logged_customer");
    if(customer==null){
        response.sendRedirect("homepage/Homepage2.html");
    }else if(customer!=null){
        List<AdminMessaging> adminMessagingList=(List<AdminMessaging>)request.getAttribute("adminMessages");
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

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>


    <!-- Custom styles for this template -->
</head>

<body>
<nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Handyman Dashboard</a>
    <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href='<%=response.encodeURL("LogoutServlet?userType=customer")%>'>Sign out</a>
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
                            <%=customer.getUsername()%><span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="UserServlet?Profile=ok">
                            <span data-feather="home"></span>
                            My profile <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="UserServlet?Messages=ok">
                            <span data-feather="file"></span>
                            Messages
                        </a>
                    </li>
                </ul>
            </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Messages</h1>
            </div>

            <h2>Section title</h2>
            <div class="container-fluid w-100">
                <ul class="nav nav-pills">
                    <li class="nav-item">
                        <a class="nav-link active" href="#handymen" id="showHandymen">Admin</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#customers" id="showCustomers">Handymen</a>
                    </li>
                </ul>
                <div class="w-75" id="chat_area">
                    <%

                        if(adminMessagingList.size()>0){
                    %>
                    <div class="w-100 card">
                        <div class="card-body">
                            <%
                                for (int i=0;i<adminMessagingList.size();i++){
                                    //Align admin messages to right
                                    if(adminMessagingList.get(i).getSender_type().equalsIgnoreCase("customer")){
                            %>
                            <div><div class="card" style="text-align: right;padding: 5px;color: blue;width: fit-content;">
                                <div style="text-align: right;"><a href="UserServlet?delete_msg=<%//=adminMessagingList.get(i).getMessage_id()%>" id="delete_msg" class="delete_msg" data="AdminServlet?delete_msg=<%=adminMessagingList.get(i).getMessage_id()%>"><span>&times;</span></a> </div>
                                <div>Me</div>
                                <div><%=adminMessagingList.get(i).getMessage()%></div>
                                <div><%=adminMessagingList.get(i).getDate_sent()%></div>
                            </div></div><br/><br/>
                            <%
                            }else if(adminMessagingList.get(i).getSender_type().equalsIgnoreCase("admin")){
                                //Align handyman messages to left
                            %>
                            <div><div class="card" style="text-align: left;padding: 5px;color: red;width: fit-content;">
                                <div style="text-align: right;"><a href="AdminServlet?delete_msg=<%//=adminMessagingList.get(i).getMessage_id()%>" id="delete_msg2" class="delete_msg" data="AdminServlet?delete_msg=<%=adminMessagingList.get(i).getMessage_id()%>"><span>&times;</span></a> </div>
                                <div><%=adminMessagingList.get(i).getSender_name()%></div>
                                <div><%=adminMessagingList.get(i).getMessage()%></div>
                                <div><%=adminMessagingList.get(i).getDate_sent()%></div>
                            </div></div><br/><br/>
                            <%
                                    }
                                }
                            %>
                        </div>
                    </div>

                    <%
                        }
                    %>
                    <div><div id="p2" class="mdl-progress mdl-js-progress mdl-progress__indeterminate"></div></div>
                    <form action="UserServlet" method="get" id="message_form">
                        <input type="hidden" name="admin_msg" value="admin_msg"/>
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
            </div>
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
    var ctx = document.getElementById("myChart");
    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
            datasets: [{
                data: [15339, 21345, 18483, 24003, 23489, 24092, 12034],
                lineTension: 0,
                backgroundColor: 'transparent',
                borderColor: '#007bff',
                borderWidth: 4,
                pointBackgroundColor: '#007bff'
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: false
                    }
                }]
            },
            legend: {
                display: false,
            }
        }
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