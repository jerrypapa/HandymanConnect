<%--
  Created by IntelliJ IDEA.
  User: papa
  Date: 7/13/18
  Time: 5:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" />
<link rel="stylesheet" href="/resources/css/style.css">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!--<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="js/jquery.js"></script>
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap-theme.min.css">-->
<div class="container">

    <form class="form-horizontal" action="HandymenRegisterServlet" method="post"  id="contact_form">
        <fieldset>

            <!-- Form Name -->
            <legend><center><h2><b>Handyman Registration Form</b></h2></center></legend><br>

            <!-- Text input-->

            <div class="form-group">
                <label class="col-md-4 control-label">First Name</label>
                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input  name="first_name" id="first_name" placeholder="First Name" class="form-control"  type="text">
                    </div>
                </div>
            </div>

            <!-- Text input-->

            <div class="form-group">
                <label class="col-md-4 control-label" >Last Name</label>
                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input name="last_name" id="last_name" placeholder="Last Name" class="form-control"  type="text">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label">Location</label>
                <div class="col-md-4 selectContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <select name="location" id="location" class="form-control selectpicker">
                            <option value="">Select your Location</option>
                            <option>Nakuru CBD</option>
                            <option>Kaptembwa</option>
                            <option >Afraha</option>
                            <option >Menengai</option>
                            <option >Bahati</option>
                            <option >Kiamunyi</option>
                            <option >Pipeline</option>
                            <option >Njoro</option>
                            <option >Ngata</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label">Service Type</label>
                <div class="col-md-4 selectContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <select name="service_type" id="service_type" class="form-control selectpicker">
                            <option value="">Select your Specialization</option>
                            <option>Carpenter</option>
                            <option>Mechanic</option>
                            <option >Plumber</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label">Gender</label>
                <div class="col-md-4 selectContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-list"></i></span>
                        <select name="gender" id="gender" class="form-control selectpicker">
                            <option value="">Select your Gender</option>
                            <option>Male</option>
                            <option>Female</option>
                        </select>
                    </div>
                </div>
            </div>

            <!-- Text input-->

            <div class="form-group">
                <label class="col-md-4 control-label">Username</label>
                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input  name="user_name" id="user_name" placeholder="Username" class="form-control"  type="text">
                    </div>
                </div>
            </div>

            <!-- Text input-->

            <div class="form-group">
                <label class="col-md-4 control-label" >Password</label>
                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input name="user_password" id="user_password" placeholder="Password" class="form-control"  type="password">
                    </div>
                </div>
            </div>

            <!-- Text input-->

            <div class="form-group">
                <label class="col-md-4 control-label" >Confirm Password</label>
                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input name="confirm_password" id="confirm_password" placeholder="Confirm Password" class="form-control"  type="password">
                    </div>
                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label">E-Mail</label>
                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                        <input name="email" id="email" placeholder="E-Mail Address" class="form-control"  type="text">
                    </div>
                </div>
            </div>


            <!-- Text input-->

            <div class="form-group">
                <label class="col-md-4 control-label">Contact No.</label>
                <div class="col-md-4 inputGroupContainer">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-earphone"></i></span>
                        <input name="contact_no" id="contact_no" placeholder="(254)" class="form-control" type="text">
                    </div>
                </div>
            </div>

            <!-- Select Basic -->

            <!-- Success message -->
            <div class="alert alert-success" role="alert" id="success_message">Success <i class="glyphicon glyphicon-thumbs-up"></i> Success!.</div>

            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label"></label>
                <div class="col-md-4"><br>
                    &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<button type="submit" class="btn btn-success" >&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspSUBMIT <span class="glyphicon glyphicon-send"></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</button>
                </div>
            </div>

        </fieldset>
    </form>
</div>
</div><!-- /.container -->
<script>
    $(document).ready(function() {
        //alert('Ready');

        $('#contact_form').on('submit',function (e) {
           e.preventDefault();
            var filter = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
            var filterName=/([a-zA-Z])/;
            var filterNo=/([0-9]])/;
            var validation=true;
           if($('#first_name').val().length==0 || !filterName.test($('#first_name'))){
               validation=false;
               $('#first_name').focus();
               $('#first_name').css('border','1px solid red');
           }else{
               validation=true;
               $('#first_name').css('border','1px solid green');
           }
           if($('#last_name').val().length==0 || !filterName.test($('#last_name'))){
               validation=false;
               $('#last_name').focus();
               $('#last_name').css('border','1px solid red');
           }else{
               validation=true;
               $('#last_name').css('border','1px solid green');
           }
           if($('#email').val().length==0 || !filter.test($('#email').val())){
               validation=false;
               $('#email').focus();
               $('#email').css('border','1px solid red');
           }else{
               validation=true;
               $('#email').css('border','1px solid green');
           }
           if($('#user_name').val().length==0 || !filterName.test($('#user_name'))){
               validation=false;
               $('#user_name').focus();
               $('#user_name').css('border','1px solid red');
           }else{
               validation=true;
               $('#user_name').css('border','1px solid green');
           }
           if($('#user_password').val().length<6 || $('#user_password').val().length>50){
               validation=false;
               $('#user_password').focus();
               $('#user_password').css('border','1px solid red');
           }else{
               validation=true;
               $('#user_password').css('border','1px solid green');
           }
           if($('#confirm_password').val()!=$('#user_password').val()){
               validation=false;
               $('#confirm_password,#user_password').focus();
               $('#user_password,#confirm_password').css('border','1px solid red');
           }else{
               validation=true;
               $('#confirm_password,#user_password').css('border','1px solid green');
           }
           if($('#contact_no').val().length!=10 || !filterNo.test($('#contact_no').val())){
               validation=false;
               $('#contact_no').focus();
               $('#contact_no').css('border','1px solid red');
           }else{
               validation=true;
               $('#contact_no').css('border','1px solid green');
           }
           if($('#gender').val()=='Select your Gender'){
               validation=false;
               $('#gender').focus();
               $('#gender').css('border','1px solid red');
           }else{
               validation=true;
               $('#gender').css('border','1px solid green');
           }
            if($('#service_type').val()=='Select your Specialization'){
                validation=false;
                $('#service_type').focus();
                $('#service_type').css('border','1px solid red');
            }else{
                validation=true;
                $('#service_type').css('border','1px solid green');
            }
           if($('#location').val().length=='Select your Location'){
               validation=false;
               $('#location').focus();
               $('#location').css('border','1px solid red');
           }else{
               validation=true;
               $('#location').css('border','1px solid green');
           }
           if(validation==true){
               //alert(validation);
               var myurl=$(this).attr('action');
               var mymethod=$(this).attr('method');
               $.ajax({
                   url:myurl,
                   method:mymethod,
                   data:$(this).serialize(),
                   success:function (result) {
                       if (result==1){
                           //alert(result);
                           window.open("Hlogin.jsp","_self");
                       } else{
                           alert(result+" failed to reload");
                       }
                   },
                   error:function () {
                       alert('error occurred');

                   }
               });
           }else{
               alert(validation);
           }
        });
    });
</script>
<style>
    #success_message{ display: none;}
    body{
        background-image: url("/homepage/assets/img/phone.jpg");
        background-size: cover;
    }
    form{
        color: #FFFFFF;
    }
    h2{
        color: #FFFFFF;
    }
</style>