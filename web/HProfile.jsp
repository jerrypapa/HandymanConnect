<%@page import="Login.Handymen" %>

<%
    HttpSession httpSession=request.getSession();
    Handymen handymen=(Handymen)httpSession.getAttribute("logged_handyman");
    if(handymen==null){
        response.sendRedirect("homepage/Homepage2.html");
    }else if(handymen!=null){

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
    <link rel="stylesheet" href="/bootstrap-4.1.2-dist/css/bootstrap.min.css">
    <script type="text/javascript" src="/bootstrap-4.1.2-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/bootstrap-4.1.2-dist/js/popper.min.js"></script>

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
                        <a class="nav-link active" href="HandymanServlet?Profile=ok">
                            <span data-feather="home"></span>
                            My profile <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="HandymanServlet?Messages=ok">
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
            <%
                if(handymen.getStatus().equalsIgnoreCase("suspended")){
                    out.println("<div class='alert alert-danger' role='alert'>" +
                            "  Your account has been suspended! <a href='HandymanServlet?Messages=ok' class='alert-link'>Contact the admin</a>." +
                            "</div>");
                }else{
            %>
            <div class="container-fluid w-100">
                <ul class="list-group" id="details">
                    <li class="list-group-item"><span>Username:</span>&nbsp;<%=handymen.getUsername()%>&nbsp;<a href="#" class="btn btn-outline-info btn-sm" data-toggle="modal" data-target="#change_username_div">Change</a></li>
                    <li class="list-group-item"><span>Firstname:</span>&nbsp;<%=handymen.getFirstName()%>&nbsp;<a href="#" class="btn btn-outline-info btn-sm" data-toggle="modal" data-target="#change_fname_div">Change</a></li>
                    <li class="list-group-item"><span>Lastname:</span>&nbsp;<%=handymen.getLastName()%>&nbsp;<a href="#" class="btn btn-outline-info btn-sm" data-toggle="modal" data-target="#change_lname_div">Change</a></li>
                    <li class="list-group-item"><span>E-mail address:</span>&nbsp;<%=handymen.getEmail()%>&nbsp;<a href="#" class="btn btn-outline-info btn-sm" data-toggle="modal" data-target="#change_email_div">Change</a></li>
                    <li class="list-group-item"><a href="#" class="btn btn-outline-info" data-toggle="modal" data-target="#change_password_div">Change password</a></li>
                    <li class="list-group-item"><span>Phone No.:</span>&nbsp;<%=handymen.getPhoneNo()%>&nbsp;<a href="#" class="btn btn-outline-info btn-sm" data-toggle="modal" data-target="#change_phone_div">Change</a></li>
                    <li class="list-group-item"><span>Gender:</span>&nbsp;<%=handymen.getGender()%>&nbsp;<a href="#" class="btn btn-outline-info btn-sm" data-toggle="modal" data-target="#change_gender_div">Change</a></li>
                    <li class="list-group-item"><span>Date registered:</span>&nbsp;<%=handymen.getDateRegistered()%></li>
                    <li class="list-group-item"><span>Location:</span>&nbsp;<%=handymen.getLocation()%>&nbsp;<a href="#" class="btn btn-outline-info btn-sm" data-toggle="modal" data-target="#change_location_div">Change</a></li>
                </ul>
            </div>
            <%
                }
            %>

        </main>
    </div>
</div>
<div class="modal fade" id="change_username_div" tabindex="-1" role="dialog" aria-labelledby="change_username_div" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalCenterTitle">Change category</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="HandymanServlet" method="get">
                    <div class="form-group row">
                        <label for="change_username" class="col-sm-2 col-form-label">New username</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="change_username" name="change_username" required/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <button type="submit" class="btn btn-outline-info">Update</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="change_fname_div" tabindex="-1" role="dialog" aria-labelledby="change_fname_div" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalCenterTitle">New Firstname</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="HandymanServlet" method="get">
                    <div class="form-group row">
                        <label for="change_name" class="col-sm-2 col-form-label">New firstname</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="change_fname" name="change_fname" required/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <button type="submit" class="btn btn-outline-info">Update</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="change_lname_div" tabindex="-1" role="dialog" aria-labelledby="change_lname_div" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalCenterTitle">Change lastname</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="HandymanServlet" method="get">
                    <div class="form-group row">
                        <label for="change_name" class="col-sm-2 col-form-label">New lastname</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="change_lname" name="change_lname" required/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <button type="submit" class="btn btn-outline-info">Update</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="change_email_div" tabindex="-1" role="dialog" aria-labelledby="change_email_div" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalCenterTitle">Change email</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="HandymanServlet" method="get">
                    <div class="form-group row">
                        <label for="change_name" class="col-sm-2 col-form-label">New email</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="change_email" name="change_email" required/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <button type="submit" class="btn btn-outline-info">Update</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="change_phone_div" tabindex="-1" role="dialog" aria-labelledby="change_phone_div" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalCenterTitle">Change Phone no.</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="HandymanServlet" method="get">
                    <div class="form-group row">
                        <label for="change_name" class="col-sm-2 col-form-label">New phone no.</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="change_phone" name="change_phone" required/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <button type="submit" class="btn btn-outline-info">Update</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="change_location_div" tabindex="-1" role="dialog" aria-labelledby="change_location_div" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalCenterTitle">Change location</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="HandymanServlet" method="get">
                    <div class="form-group row">
                        <label for="change_name" class="col-sm-2 col-form-label">New location</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="change_location" name="change_location" required/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <button type="submit" class="btn btn-outline-info">Update</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="change_gender_div" tabindex="-1" role="dialog" aria-labelledby="change_gender_div" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalCenterTitle">Change gender</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="HandymanServlet" method="get">
                    <div class="form-group row">
                        <label for="change_name" class="col-sm-2 col-form-label">Select gender</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="change_gender" name="change_gender" required>
                                <option disabled>select</option>
                                <option>Male</option>
                                <option>Female</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <button type="submit" class="btn btn-outline-info">Update</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="change_password_div" tabindex="-1" role="dialog" aria-labelledby="change_password_div" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalCenterTitle">Change password</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="HandymanServlet" method="get">
                    <div class="form-group row">
                        <label for="change_name" class="col-sm-2 col-form-label">Current password</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="current_password" name="current_password" required/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="change_name" class="col-sm-2 col-form-label">New password</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="change_password" name="change_password" required/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <button type="submit" class="btn btn-outline-info">Update</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
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