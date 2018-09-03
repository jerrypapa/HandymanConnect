<%--
  Created by IntelliJ IDEA.
  User: papa
  Date: 8/28/18
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <link href="css/style.css" rel="stylesheet" type="text/css">

    <title>Notation</title>
    <script type="text/javascript">
        function notation(starId) {

            var elements = document.getElementsByClassName("notation-star-
            selected");

            for (i = 0; i < elements.length; i++) {
                elements[i].className = "notation-star";
            }


            document.getElementById(starId).className = "notation-star-
            selected";


            document.getElementById("notationNote").value = starId.substr(4,
                4);

            var note = document.getElementById("notationNote").value =
                starId.substr(4, 4);

            document.getElementById("vote").innerHTML = note.valueOf();



        }
    </script>



</head>

<div class="notation-text">Votre avis nous est très précieux pour
</body>
</html>
améliorer la qualité de notre service
</div>


<form method="post" action="voting">
    <div id="star5" class="notation-star" onClick="notation(this.id);"></div>
    <div id="star4" class="notation-star" onClick="notation(this.id);"></div>
    <div id="star3" class="notation-star" onClick="notation(this.id);"></div>
    <div id="star2" class="notation-star" onClick="notation(this.id);"></div>
    <div id="star1" class="notation-star" onClick="notation(this.id);"></div>
    <input type="hidden" id="notationNote" name="notation_note" value="0">
    <input type="submit" value="ok"></form  <br>

<p id="vote"></p>

</html>
<<style>
body {
font-family: Verdana, arial;
}

.notation-text {
color: #000000;
font-size: 18px;
text-align: center;
margin: 18px;
}

.notation-block-star {
display: table;
margin: 0 auto;
width: inherit;
}


.notation-star {
background-image: url("../images/etoile_grise.png");
background-repeat: no-repeat;
cursor: pointer;
display: table-cell;
float: right;
height: 64px;
width: 64px;
padding: 10 5px;
}


.notation-star:hover,
.notation-star:hover ~ .notation-star {
background-image: url("../images/etoile_jaune.png");
}

.notation-star-selected {
background-image: url("../images/etoile_jaune.png");
background-repeat: no-repeat;
cursor: pointer;
display: table-cell;
float: right;
height: 64px;
width: 64px;
padding: 0 5px;
}

.notation-star-selected  ~ .notation-star {
background-image: url("../images/etoile_jaune.png");
}
</style>