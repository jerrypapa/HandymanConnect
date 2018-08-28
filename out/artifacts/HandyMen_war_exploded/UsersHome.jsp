<%@ page import="java.util.List" %>
<%@ page import="Customer.Services" %>
<%@ page import="Login.Customer" %><%--
  Created by IntelliJ IDEA.
  User: papa
  Date: 8/18/18
  Time: 6:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession httpSession=request.getSession();
    Customer customer=(Customer)httpSession.getAttribute("logged_customer");
    if(customer==null){
        response.sendRedirect("homepage/Homepage2.html");
    }else if(customer!=null){
    List<Services> servicesList=(List<Services>)request.getAttribute("servicesList");

%>
<html>
<head>
    <title>Handymen Connect</title>
    <link rel="stylesheet" type="text/css" href="/homepage/assets/css/bootstrap.min.css" >
    <!-- Icon -->
    <link rel="stylesheet" type="text/css" href="/homepage/assets/fonts/line-icons.css">
    <!-- Slicknav -->
    <link rel="stylesheet" type="text/css" href="/homepage/assets/css/slicknav.css">
    <!-- Nivo Lightbox -->
    <link rel="stylesheet" type="text/css" href="/homepage/assets/css/nivo-lightbox.css" >
    <!-- Animate -->
    <link rel="stylesheet" type="text/css" href="/homepage/assets/css/animate.css">
    <!-- Main Style -->
    <link rel="stylesheet" type="text/css" href="/homepage/assets/css/main.css">
    <!-- Responsive Style -->
    <link rel="stylesheet" type="text/css" href="/homepage/assets/css/responsive.css">
    <link href="https://fonts.googleapis.com/css?family=Dancing+Script" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
</head>
<body>
<!-- Header Area wrapper Starts -->
<header id="header-wrap">
    <nav class="navbar navbar-expand-lg fixed-top scrolling-navbar">
        <div class="container">
            <div class="navbar-header">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main-navbar" aria-controls="main-navbar" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                    <span class="icon-menu"></span>
                    <span class="icon-menu"></span>
                    <span class="icon-menu"></span>
                </button>
                <a href="./Homepage2.html" class="navbar-brand">HandymenConnect</a>
            </div>
            <div class="collapse navbar-collapse" id="main-navbar">
                <ul class="navbar-nav mr-auto w-100 justify-content-end">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">
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
                        <a class="nav-link" href="UserServlet?Messages=ok">
                            <span data-feather="file"></span>
                            Messages
                        </a>
                    </li>
                    <!--<li class="nav-item dropdown">
                        <a class="nav-link" href="">
                            LOGIN<span class="fa fa-icon-chevron-down"></span>
                        </a>
                        <ul class="dropdown-menu" id="login_dropdown">
                            <li><a href="../AdminLogin.jsp">ADMINISTRATOR</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="../Hlogin.jsp">HANDYMEN</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="../Clogin.jsp">CUSTOMER</a></li>

                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link" href=""  class="dropdown-toggle" data-toggle="dropdown" id="drop_register">
                            REGISTER<span class="fa fa-icon-chevron-down"></span>
                        </a>
                        <ul id="login-dp" class="dropdown-menu">
                            <li role="separator" class="divider"></li>
                            <li><a href="../HandymenRegister.jsp">HANDYMAN</a> </li>
                            <li role="separator" class="divider"></li>
                            <li><a href="../CustomerRegister.jsp">CUSTOMER</a> </li>
                        </ul>
                    </li>-->
                    <li class="nav-item">
                        <a class="nav-link" href="#google-map-area">
                            Find us
                        </a>
                    </li>
                </ul>
            </div>
        </div>

        <ul class="mobile-menu">
            <li>
                <a class="page-scrool" href="#header-wrap">Home</a>
            </li>
            <li>
                <a class="page-scrool" href="#about">About</a>
            </li>
            <li>
                <a class="page-scroll" href="#schedules">schedules</a>
            </li>
            <li>
                <a class="page-scroll" href="#team">Speakers</a>
            </li>
            <li>
                <a class="page-scroll" href="#gallery">Gallery</a>
            </li>
            <li>
                <a class="page-scroll" href="#faq">Faq</a>
            </li>
            <!--<li>
                <a class="page-scroll" href="#sponsors">LOGIN</a>
            </li>
            <li>
                <a class="page-scroll" href="#pricing">REGISTER</a>
            </li>-->
            <li>
                <a class="page-scroll" href="#google-map-area">Contact</a>
            </li>
        </ul>

    </nav>
</header>
<!--<section id="gallery" class="section-padding">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="section-title-header text-center">
                    <h1 class="section-title wow fadeInUp" data-wow-delay="0.2s">our products gallery</h1>
                    <p class="wow fadeInDown" data-wow-delay="0.2s">Admin Connect-Nakuru</p>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 col-sm-6 col-lg-4">
                <div class="gallery-box">
                    <div class="img-thumb">
                        <img class="img-fluid" src="assets/gallery/10.jpg" alt="">
                    </div>
                    <div class="overlay-box text-center">
                        <a class="lightbox" href="assets/gallery/33.jpg">
                            <i class="lni-plus"></i>
                        </a>
                    </div>
                </div>
            </div>
            <div class="ccol-md-6 col-sm-6 col-lg-4">
                <div class="gallery-box">
                    <div class="img-thumb">
                        <img class="img-fluid" src="assets/gallery/8.jpg" alt="">
                    </div>
                    <div class="overlay-box text-center">
                        <a class="lightbox" href="assets/gallery/24.jpg">
                            <i class="lni-plus"></i>
                        </a>
                    </div>
                </div>
            </div>
            <div class="ccol-md-6 col-sm-6 col-lg-4">
                <div class="gallery-box">
                    <div class="img-thumb">
                        <img class="img-fluid" src="assets/gallery/18.jpg" alt="">
                    </div>
                    <div class="overlay-box text-center">
                        <a class="lightbox" href="assets/gallery/20.jpg">
                            <i class="lni-plus"></i>
                        </a>
                    </div>
                </div>
            </div>
            <div class="ccol-md-6 col-sm-6 col-lg-4">
                <div class="gallery-box">
                    <div class="img-thumb">
                        <img class="img-fluid" src="assets/gallery/17.jpg" alt="">
                    </div>
                    <div class="overlay-box text-center">
                        <a class="lightbox" href="assets/gallery/31.jpg">
                            <i class="lni-plus"></i>
                        </a>
                    </div>
                </div>
            </div>
            <div class="ccol-md-6 col-sm-6 col-lg-4">
                <div class="gallery-box">
                    <div class="img-thumb">
                        <img class="img-fluid" src="assets/gallery/6.jpg" alt="">
                    </div>
                    <div class="overlay-box text-center">
                        <a class="lightbox" href="assets/gallery/4.jpg">
                            <i class="lni-plus"></i>
                        </a>
                    </div>
                </div>
            </div>
            <div class="ccol-md-6 col-sm-6 col-lg-4">
                <div class="gallery-box">
                    <div class="img-thumb">
                        <img class="img-fluid" src="assets/gallery/16.jpg" alt="">
                    </div>
                    <div class="overlay-box text-center">
                        <a class="lightbox" href="assets/gallery/23.jpg">
                            <i class="lni-plus"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="row justify-content-center mt-3">
            <div class="col-xs-12">
                <a href="#" class="btn btn-common">Browse All</a>
            </div>
        </div>
    </div>
</section>-->
<div class="row" style="margin: auto;">
<%
    for(int i=0;i<servicesList.size();i++){
%>
<div class="responsive col-3">
    <div class="gallery">
        <a href="UserServlet?post_id=<%=servicesList.get(i).getPost_id()%>">
            <img src="/resources/Uploads/<%=servicesList.get(i).getImage_url()%>" alt="Forest" width="600" height="400">
        </a>
        <div class="desc">
            <div><%=servicesList.get(i).getName()%></div>
            <div><%=servicesList.get(i).getDescription()%></div>
            <div>By:&nbsp;<a href="UserServlet?handyman_profile=<%=servicesList.get(i).getH_id()%>"><%=servicesList.get(i).getH_name()%></a></div>
        </div>
    </div>
</div>
<%
    }
%>
</div>
</body>
<script src="/homepage/assets/js/jquery-min.js"></script>
<script src="/homepage/assets/js/popper.min.js"></script>
<script src="/homepage/assets/js/bootstrap.min.js"></script>
<script src="/homepage/assets/js/jquery.countdown.min.js"></script>
<script src="/homepage/assets/js/jquery.nav.js"></script>
<script src="/homepage/assets/js/jquery.easing.min.js"></script>
<script src="/homepage/assets/js/wow.js"></script>
<script src="/homepage/assets/js/jquery.slicknav.js"></script>
<script src="/homepage/assets/js/nivo-lightbox.js"></script>
<script src="/homepage/assets/js/main.js"></script>
<script src="/homepage/assets/js/form-validator.min.js"></script>
<script src="/homepage/assets/js/contact-form-script.min.js"></script>
<script src="/homepage/assets/js/map.js"></script>
<style>
    .navbar-brand{
        color: deeppink;
        font-size: 35px;
        font-family: 'Dancing Script', cursive;
    }
    div.gallery {
        border: 1px solid #ccc;
    }

    div.gallery:hover {
        border: 1px solid #777;
    }

    div.gallery img {
        width: 100%;
        height: auto;
    }

    div.desc {
        padding: 15px;
        text-align: center;
    }

    * {
        box-sizing: border-box;
    }

    .responsive {
        padding: 0 6px;
        float: left;
        width: 24.99999%;
    }

    @media only screen and (max-width: 700px) {
        .responsive {
            width: 49.99999%;
            margin: 6px 0;
        }
    }

    @media only screen and (max-width: 500px) {
        .responsive {
            width: 100%;
        }
    }

    .clearfix:after {
        content: "";
        display: table;
        clear: both;
    }
</style>
</html>
<%
    }
%>
