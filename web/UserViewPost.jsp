<%@ page import="java.util.List" %>
<%@ page import="Customer.Services" %>
<%@ page import="Customer.HandyManMessaging" %>
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
    }else {
        List<Services> servicesList=(List<Services>)request.getAttribute("servicesList");
        List<HandyManMessaging> handyManMessagingList=(List<HandyManMessaging>)request.getAttribute("handyManMessagingList");
        String post_name="",descr="",image_url="",post_date="",category="",h_name="";
        int post_id=0,h_id=0;
        double price=0;

%>
<html>
<head>
    <title>Handymen Connect</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
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
                <h4 class="h4">View service / product</h4>
            </div>
            <div class="w-100 container-fluid row" style="margin: auto;" id="item_desc">
                <div class="col-2"></div>
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
                <div class="responsive col-4">
                    <div class="gallery">
                        <a href="UserServlet?post_id=<%=servicesList.get(i).getPost_id()%>">
                            <img src="/resources/Uploads/<%=servicesList.get(i).getImage_url()%>" alt="Forest" width="600" height="400">
                        </a>
                    </div>
                </div>
                <%
                        }
                    }else{
                        out.println("Empty");
                    }
                %>

                <div class="container-fluid col-6">
                    <ul class="list-group">
                        <li class="list-group-item"><h3><span></span>&nbsp;<%=post_name%></h3></li>
                        <li class="list-group-item"><span class="badge badge-pill badge-danger">Price:</span>&nbsp;Kes.&nbsp;<%=price%></li>
                        <li class="list-group-item"><span class="badge badge-pill badge-secondary">Service / Product details</span>&nbsp;<br/><%=descr%></li>
                        <li class="list-group-item"><span class="badge badge-pill badge-secondary">Date posted:</span>&nbsp;<%=post_date%></li>
                    </ul>

                    <div class="container-fluid" style="padding: 10px;margin-top: 5px;">
                        <a href="#" class="btn btn-outline-info" id="talk">Talk to handyman&nbsp;<span class="fa fa-comments"></span></a>
                    </div>
                    <div class="container">
                        <div class="container" style="border: 1px solid #c2c4c6; padding: 8px;">
                            <div class="container">
                                Rate?&nbsp;&nbsp;<div id="rate6"></div>
                            </div>
                        </div>
                    </div>
                    <br/><br/>
                    <div class="container">
                        <form action="UserServlet" method="get" id="jobForm">
                            <div class="container">
                                <h4>Provide the job details below.</h4>
                            </div>
                            <div class="form-group row">
                                <label class="control-label col-3">Job Title</label>
                                <div class="col-9">
                                    <input type="text" name="job_title" class="form-control form-control-sm" placeholder="Job Title" required/>
                                </div>
                            </div>
                            <div class="row">
                                <label class="control-label col-3">Description</label>
                                <div class="col-9">
                                    <textarea name="job_description" class="form-control" cols="8" required></textarea>
                                </div>
                            </div>
                            <input type="hidden" name="h_id" value="<%=h_id%>">
                            <input type="hidden" name="h_name" value="<%=h_name%>">
                            <input type="hidden" name="job_details" value="job_details">
                            <div class="form-group">
                                <button type="submit" class="btn btn-outline-warning">Submit</button>
                            </div>
                        </form>
                    </div>
                </div>
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
                                <input type="hidden" name="post_name" id="post_name" value="<%=post_name%>"/>
                                <form class="form-inline" id="message_form" action="UserServlet" method="get">
                                    <input type="hidden" name="h_id" id="h_id" value="<%=h_id%>"/>
                                    <input type="hidden" id="h_name" name="h_name" value="<%=h_name%>"/>
                                    <input type="hidden" name="post_id" id="service_id" value="<%=post_id%>"/>
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
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/rater.min.js"></script>
<script>
    $(function(){
        $('#jobForm').on('submit',function (e) {
           e.preventDefault();
           $.ajax({
               url: $(this).attr('action'),
               method: $(this).attr('method'),
               data: $(this).serialize(),
               success: function (result) {
                   alert("Job submitted successfully!");
               },
               error: function () {
                   alert('Error occured!');
               }
           });
        });
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

        var myoptions = {
            max_value: 5,
            step_size: 0.5,
            selected_symbol_type: 'fontawesome_star',
            ajax_method: 'GET',
            url: 'UserServlet',
            additional_data: {'rating':$("#rate6").rate("getValue")},
            change_once: true,
            initial_value: 0,
            update_input_field_name: $("#input2"),
        }

        $("#rate6").rate(myoptions);

        $(".rate2").on("updateError", function(ev, jxhr, msg, err){
            console.log("This is a custom error event");
        });

        setTimeout(function(){
            $("#rate6").rate({
                selected_symbol_type: 'fontawesome_star',
                max_value: 5,
                step_size: 1,
            });
        }, 2000);
        $('#rate6').on('change',function () {
            var h_id=$('#h_id').val();
            var h_name=$('#h_name').val();
            var service_id=$('#service_id').val();
            var service_name=$('#post_name').val();
            var rating=$("#rate6").rate("getValue");
            //alert($("#rate6").rate("getValue")+" "+h_id+" "+h_name);
            //window.open('/UserServlet?service_name='+service_name+'&service_id='+service_id+'&rating='+rating);
            $.ajax({
               url: 'UserServlet',
               method: 'get',
               data: {'h_name':h_name,'h_id':h_id,'rating':rating},
               success: function (result) {
                   //alert(result);
               },
               error: function () {
                   alert('Error occured!');
               }
            });
        });

        /*$("#rate7").rate({
            selected_symbol_type: 'image2',
            max_value: 5,
            step_size: 1,
            update_input_field_name: $("#input1"),
            only_select_one_symbol: true,
            symbols:{
                image2: {
                    base: ['<div style="background-image: url(\'./images/emoji1.png\');" class="im2">&nbsp;</div>',
                        '<div style="background-image: url(\'./images/emoji2.png\');" class="im2">&nbsp;</div>',
                        '<div style="background-image: url(\'./images/emoji3.png\');" class="im2">&nbsp;</div>',
                        '<div style="background-image: url(\'./images/emoji4.png\');" class="im2">&nbsp;</div>',
                        '<div style="background-image: url(\'./images/emoji5.png\');" class="im2">&nbsp;</div>',],
                    hover: ['<div style="background-image: url(\'./images/emoji1.png\');" class="im2">&nbsp;</div>',
                        '<div style="background-image: url(\'./images/emoji2.png\');" class="im2">&nbsp;</div>',
                        '<div style="background-image: url(\'./images/emoji3.png\');" class="im2">&nbsp;</div>',
                        '<div style="background-image: url(\'./images/emoji4.png\');" class="im2">&nbsp;</div>',
                        '<div style="background-image: url(\'./images/emoji5.png\');" class="im2">&nbsp;</div>',],
                    selected: ['<div style="background-image: url(\'./images/emoji1.png\');" class="im2">&nbsp;</div>',
                        '<div style="background-image: url(\'./images/emoji2.png\');" class="im2">&nbsp;</div>',
                        '<div style="background-image: url(\'./images/emoji3.png\');" class="im2">&nbsp;</div>',
                        '<div style="background-image: url(\'./images/emoji4.png\');" class="im2">&nbsp;</div>',
                        '<div style="background-image: url(\'./images/emoji5.png\');" class="im2">&nbsp;</div>',],
                },
            },
        });*/
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
    #header-wrap{
        background: #f9e9ff;
        z-index: 10;
    }

    #gallery{
        z-index: 10;
    }
    .rate-base-layer
    {
        color: #aaa;
    }
    .rate-hover-layer
    {
        color: orange;
    }
    .rate2
    {
        font-size: 35px;
    }
    .rate2 .rate-hover-layer
    {
        color: pink;
    }
    .rate2 .rate-select-layer
    {
        color: red;
    }
    .im
    {
        background-image: url('./images/heart.gif');
        background-size: 32px 32px;
        background-repeat: no-repeat;
        width: 32px;
        height: 32px;
        display: inline-block;
    }
    .im2
    {
        background-image: url('./images/emoji5.png');
        background-size: 64px 64px;
        background-repeat: no-repeat;
        width: 64px;
        height: 64px;
        display: inline-block;
    }
    #rate5 .rate-base-layer span, #rate7 .rate-base-layer span
    {
        opacity: 0.5;
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
<%
    }
%>
