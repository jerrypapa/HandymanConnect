<%@ page import="java.util.List" %>
<%@ page import="Customer.Services" %>
<%@ page import="Customer.HandyManMessaging" %>
<%@ page import="Login.Handymen" %><%--
  Created by IntelliJ IDEA.
  User: papa
  Date: 8/18/18
  Time: 6:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
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
<html>
<head>
    <title>Handymen Connect</title>
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans|Oxygen|Roboto+Slab" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Asap" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
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
<div class="container w-75">
    <h2>Handyman:&nbsp;<%=handyman_name%></h2>
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
                                <img src="/resources/Uploads/<%=servicesList.get(i).getImage_url()%>" alt="image">
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
                            <input type="text" class="form-control" id="message" name="message" required/>
                        </div>
                        <input type="hidden" name="send_msg" value="send_msg"/>
                        <button type="submit" class="btn btn-primary mb-2"><span class="fa fa-send"></span></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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
            /*e.preventDefault();
            var u_=$(this).attr('action');
            var m=$(this).attr('method');
            $.ajax({
                url: u_,
                method: m,
                data: $(this).serialize(),
                success: function(result){
                    $('#message').val('');
                    $('#messages ul').append('<li class="list-group-item" id="new_item'+c+'"><div style="border: 1px solid #6e6e6e; float: right; text-align: right;">'+result+'</div></li>');
                    $('#new_item'+c).focus();
                },
                error: function () {
                    alert('error occured!');
                }
            });*/
        });
    });
</script>
<style>
    body{
        padding-top: 33px;
    }
    #item_desc{
        margin-top: 13px;
        font-family: 'Oxygen', sans-serif;
    }
    h4{
        font-size: 18px;
        color: rgba(58,53,255,0.88);
        padding: 2px;
    }
    .btn{
        border-radius: 0;
    }
    .navbar-brand{
        color: deeppink;
        font-size: 35px;
        font-family: 'Dancing Script', cursive;
    }
    #header-wrap{
        background: #f9e9ff;
        z-index: 10;
    }

    #gallery{
        z-index: 10;
    }

    #item_desc ul li:hover{
        border: 2px solid #1b1e21;
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
        /*width: 24.99999%;*/
    }

    @media only screen and (max-width: 700px) {
        .responsive {
            width: 49.99999%;
            margin: 6px 0;
        }
        #chat{
            width: 70%;
            height: 350px;
            overflow-y: scroll;
        }
    }

    @media only screen and (max-width: 500px) {
        .responsive {
            width: 100%;
        }
        #chat{
            width: 90%;
            height: 350px;
            overflow-y: scroll;
        }
    }
    @media only screen and (max-width: 340px) {
        #chat{
            width: 100%;
            height: 350px;
            overflow-y: scroll;
        }
    }

    .clearfix:after {
        content: "";
        display: table;
        clear: both;
    }
</style>
</html>

