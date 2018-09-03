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
                    <li class="nav-item active">
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
                <h1 class="h2">Post Item</h1>
            </div>
            <%
                if(handymen.getStatus().equalsIgnoreCase("suspended")){
                    out.println("<div class='alert alert-danger' role='alert'>" +
                            "  Your account has been suspended! <a href='HandymanServlet?Messages=ok' class='alert-link'>Contact the admin</a>." +
                            "</div>");
                }else{
            %>
            <%
                if(request.getAttribute("image_url")==null){
            %>
            <div class="container-fluid w-100 card">
                <div class="w-75 card-body">
                    <form action="HUploadItem" method="post" enctype = "multipart/form-data">
                        <div class="form-group">
                            <label for="file">Item photo</label>
                            <input type="file" name="file" class="form-control-file" id="file">
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-outline-info">Upload</button>
                        </div>
                    </form>
                </div>
            </div>
            <%
            }else{
                String image_url=(String)request.getAttribute("image_url");
                request.setAttribute("image_url",image_url);
            %>
            <div class="container-fluid w-100 card">
                <div class="w-75 card-body">
                    <form  action="HandymanServlet" method="get">
                        <div class="form-group row">
                            <label for="category" class="col-sm-2 col-form-label">Category</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="category" name="category" required>
                                    <option></option>
                                    <option>good</option>
                                    <option>service</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="name" class="col-sm-2 col-form-label">Service / Good name</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="name"name="name" required/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="description" class="col-sm-2 col-form-label">Description</label>
                            <div class="col-sm-10">
                                <textarea  class="form-control" id="description" name="description" cols="8" required></textarea>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="price" class="col-sm-2 col-form-label">Price</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="price"name="price" required/>
                            </div>
                        </div>
                        <input type="hidden" name="service_details" value="service_details"/>
                        <div class="form-group row">
                            <div class="col-sm-10">
                                <button type="submit" class="btn btn-outline-info">Submit</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <%
                }
            %>
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