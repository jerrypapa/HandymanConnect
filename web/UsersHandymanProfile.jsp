<%@ page import="Login.Customer" %>
<%@ page import="Customer.Services" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Customer.HandyManMessaging" %>

<%
    HttpSession httpSession=request.getSession();
    Customer customer=(Customer)httpSession.getAttribute("logged_customer");
    if(customer==null){
        response.sendRedirect("homepage/Homepage2.html");
    }else {
        List<Services> servicesList=(List<Services>)request.getAttribute("servicesList");
        List<HandyManMessaging> handyManMessagingList=(List<HandyManMessaging>)request.getAttribute("handyManMessagingList");
        String post_name="",descr="",image_url="",post_date="",category="",h_name="";
        int post_id=0,h_id=0;
        double price=0;
        String handyman_name="";
        for (int i=0;i<servicesList.size();i++){
            handyman_name=servicesList.get(i).getH_name();
        }
%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Handymen Connect</title>
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans|Oxygen|Roboto+Slab" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Asap" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
</head>

<body>
<nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Handymen Connect</a>
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
                        <a class="nav-link" href="UserServlet?Messages=ok">
                            <span data-feather="file"></span>
                            Messages
                        </a>
                    </li>

                </ul>
            </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h4 class="h4">Handyman:&nbsp;<%=handyman_name%></h4>
            </div>
            <div class="w-75 container-fluid" style="margin: auto;" id="item_desc">
                <ul class="list-group">
                    <%
                        if(servicesList!=null){
                            for(int i=0;i<servicesList.size();i++){
                                post_date=servicesList.get(i).getPost_date();
                                post_id=servicesList.get(i).getPost_id();
                                post_name=servicesList.get(i).getName();
                                descr=servicesList.get(i).getDescription();
                                image_url=servicesList.get(i).getImage_url();
                                price=servicesList.get(i).getPrice();
                                category=servicesList.get(i).getCategory();
                                h_id=servicesList.get(i).getH_id();
                                h_name=servicesList.get(i).getH_name();
                    %>
                    <li class="list-group-item list-group-item-action">
                        <div class="row">
                            <div class="col-4">
                                <div class="responsive">
                                    <div class="gallery">
                                        <a href="UserServlet?post_id=<%=servicesList.get(i).getPost_id()%>">
                                            <img src="/resources/Uploads/<%=servicesList.get(i).getImage_url()%>" alt="image" width="100">
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-8">
                                <ul class="list-group">
                                    <li class="list-group-item"><h3><span></span>&nbsp;<a href="UserServlet?post_id=<%=servicesList.get(i).getPost_id()%>"><span class="badge badge-secondary"><%=post_name%></span></a></h3></li>
                                    <li class="list-group-item"><span class="badge badge-pill badge-danger">Price:</span>&nbsp;Kes.&nbsp;<%=price%></li>
                                    <li class="list-group-item"><span class="badge badge-pill badge-secondary">Service / Product details</span>&nbsp;<br/><%=descr%></li>
                                    <li class="list-group-item"><span class="badge badge-pill badge-secondary">Date posted:</span>&nbsp;<%=post_date%></li>
                                </ul>
                            </div>
                        </div>
                    </li>
                    <%
                            }
                        }else{
                            out.println("Empty");
                        }
                    %>
                </ul>
            </div>
            <div id="chat" class="card">
                <div class="card-body">
                    <div class="d-flex justify-content-between" style="border-bottom: 1px solid rgba(58,53,255,0.88);"><h4>Chat&nbsp;<span class="fa fa-comments"></span></h4><span id="show_chat" class="fa fa-chevron-up"></span></div>
                    <div id="chat_content">
                        <div class="card" style="margin-bottom: 55px;">
                            <div id="messages" class="card-body">
                                <ul class="list-group">
                                    <%
                                        if(handyManMessagingList!=null){
                                            for(int i=0;i<handyManMessagingList.size();i++){
                                                if(handyManMessagingList.get(i).getSender_type().equalsIgnoreCase("customer")){
                                    %>
                                    <li class="list-group-item"><div  style="border: 1px solid #6e6e6e; float: right; text-align: right;"><%=handyManMessagingList.get(i).getMessage()%></div></li>
                                    <%
                                    }else{
                                    %>
                                    <li class="list-group-item"><div  style="border: 1px solid #6e6e6e; float: left; text-align: left;"><%=handyManMessagingList.get(i).getMessage()%></div></li>
                                    <%
                                                }
                                            }
                                        }else{
                                            out.println("No messages!");
                                        }
                                    %>
                                </ul>
                            </div>
                        </div>
                        <div class="card" style="bottom: 0;position: fixed;">
                            <div class="card-body">
                                <form class="form-inline" id="message_form" action="UserServlet" method="get">
                                    <input type="hidden" name="h_id" value="<%=h_id%>"/>
                                    <input type="hidden" name="h_name" value="<%=h_name%>"/>
                                    <div class="form-group mb-2">
                                        <input type="text" class="form-control" id="message" name="h_message" required/>
                                    </div>
                                    <input type="hidden" name="send_msg" value="send_msg"/>
                                    <button type="submit" class="btn btn-primary mb-2"><span class="fa fa-send"></span></button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<!-- Icons -->
<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
    feather.replace()
</script>
<script>
    $(function(){
        $('#chat_content').hide();
        var chat_visible=0;
        $('#show_chat').click(function () {
            if(chat_visible==0){
                chat_visible=1;
                $(this).removeClass('fa-chevron-up');
                $(this).addClass('fa-chevron-down');
                $('#chat_content').show();
            }else{
                chat_visible=0;
                $(this).removeClass('fa-chevron-down');
                $(this).addClass('fa-chevron-up');
                $('#chat_content').hide();
            }
        });
        $('#talk').click(function () {
            if(chat_visible==0){
                chat_visible=1;
                $('#show_chat').removeClass('fa-chevron-up');
                $('#show_chat').addClass('fa-chevron-down');
                $('#chat_content').show();
            }
        });
        var c=0;
        $('#message_form').on('submit',function (e) {
            c++;
            e.preventDefault();
            var u_=$(this).attr('action');
            var m=$(this).attr('method');
            $.ajax({
                url: u_,
                method: m,
                data: $(this).serialize(),
                success: function(result){
                    $('#message').val('');
                    $('#messages ul').append('<li class="list-group-item" id="new_item"><div style="border: 1px solid #6e6e6e; float: right; text-align: right;">'+result+'</div></li>');
                    $('#new_item').focus();
                },
                error: function () {
                    alert('error occured!');
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
    #item_desc{
        margin-top: 13px;
        font-family: 'Oxygen', sans-serif;
    }
    #item_desc ul li:hover{
        border: 2px solid #1b1e21;
    }
    h4{
        font-size: 18px;
        color: rgba(58,53,255,0.88);
        padding: 2px;
    }
    #chat{
        bottom: 0;
        position: fixed;
        width: 400px;
        font-family: 'Asap', sans-serif;
    }

    #show_chat{
        cursor: pointer;
    }

    #chat_content{
        height: 350px;
        overflow-y: scroll;
    }
    .navbar-nav ul li a{
        color: rgba(249,233,255,0.85);
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