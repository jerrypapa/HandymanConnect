<%--
  Created by IntelliJ IDEA.
  User: papa
  Date: 8/29/18
  Time: 12:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/bootstrap-4.1.2-dist/css/bootstrap.min.css">
    <script type="text/javascript" src="/bootstrap-4.1.2-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/bootstrap-4.1.2-dist/js/popper.min.js"></script>

    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>

</head>
<body>
<div class="container">
    <div class="container">
        <div class="row">
            <div class="rating">
                <input type="radio" id="star" name="rating" value="10" /><label for="star" title="Rocks!">5 stars</label>
                <input type="radio" id="star" name="rating" value="9" /><label for="star" title="Rocks!">4 stars</label>
                <input type="radio" id="star" name="rating" value="8" /><label for="star" title="Pretty good">3 stars</label>
                <input type="radio" id="star" name="rating" value="7" /><label for="star" title="Pretty good">2 stars</label>
                <input type="radio" id="star" name="rating" value="6" /><label for="star" title="Meh">1 star</label>
                <input type="radio" id="star" name="rating" value="5" /><label for="star" title="Meh">5 stars</label>
                <input type="radio" id="star" name="rating" value="4" /><label for="star" title="Kinda bad">4 stars</label>
                <input type="radio" id="star" name="rating" value="3" /><label for="star" title="Kinda bad">3 stars</label>
                <input type="radio" id="star" name="rating" value="2" /><label for="star" title="Sucks big tim">2 stars</label>
                <input type="radio" id="star" name="rating" value="1" /><label for="star" title="Sucks big time">1 star</label>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<style>
    .rating {
        float:left;
    }

    /* :not(:checked) is a filter, so that browsers that don’t support :checked don’t
      follow these rules. Every browser that supports :checked also supports :not(), so
      it doesn’t make the test unnecessarily selective */
    .rating:not(:checked) > input {
        position:absolute;
        top:-9999px;
        clip:rect(0,0,0,0);
    }

    .rating:not(:checked) > label {
        float:right;
        width:1em;
        /* padding:0 .1em; */
        overflow:hidden;
        white-space:nowrap;
        cursor:pointer;
        font-size:300%;
        /* line-height:1.2; */
        color:#ddd;
    }

    .rating:not(:checked) > label:before {
        content: '★ ';
    }

    .rating > input:checked ~ label {
        color: dodgerblue;

    }

    .rating:not(:checked) > label:hover,
    .rating:not(:checked) > label:hover ~ label {
        color: dodgerblue;

    }

    .rating > input:checked + label:hover,
    .rating > input:checked + label:hover ~ label,
    .rating > input:checked ~ label:hover,
    .rating > input:checked ~ label:hover ~ label,
    .rating > label:hover ~ input:checked ~ label {
        color: dodgerblue;

    }

    .rating > label:active {
        position:relative;
        top:2px;
        left:2px;
    }
</style>
<script>
    $(function () {
       $('input').click(function () {
         alert($(this).val());
       });
    });
</script>