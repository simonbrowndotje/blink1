<%@ page import="java.awt.*" %>
<%@ page import="java.util.Random" %>
<%@ page import="halloween.Pumpkin" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>The Pumpkin Controller</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="shortcut icon" href="/favicon.ico">
    <link href="./static/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="./static/css/halloween.css" rel="stylesheet" media="screen">
    <link href="./static/css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <script src="./static/js/jquery-1.8.2.min.js"></script>

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="./static/js/html5.js"></script>
    <![endif]-->
</head>

<%
    String color = request.getParameter("color");
    if (color != null && color.length() > 0) {
        if (!"random".equals(color)) {
            Pumpkin.light.setColor(new Color(Integer.parseInt(color, 16)), true);
        } else {
            Random random = new Random();
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);
            Pumpkin.light.setColor(new Color(red, green, blue), true);
        }
    }
%>

<body>

<style>
    body {
        padding-left: 0;
        padding-right: 0;
        background: black;
    }
    .container {
        padding-left: 10px;
        padding-right: 10px;
    }
    a {
        color: white;
    }
    .span4 div {
        text-align: center;
        font-size: 30px;
        border: white 1px solid;
        border-radius: 2px;
    }
</style>

<div>

    <div class="section">
        <div class="container">
            <h1 style="text-align: center;">The Pumpkin Controller</h1>

            <br /><br />

            <div class="row">
                <div class="span4" style="background: #ff0000;"><div><a href="/?color=FF0000">Blood Red</a></div></div>
                <div class="span4" style="background: #00ff00;"><div><a href="/?color=00ff00">Snot Green</a></div></div>
                <div class="span4" style="background: #FF9900;"><div><a href="/?color=FF9900">Pumpkin Orange</a></div></div>
            </div>

            <div class="row">
                <div class="span4" style="background: #FFD200;"><div><a href="/?color=FFD200">Pus Yellow</a></div></div>
                <div class="span4" style="background: #0000FF;"><div><a href="/?color=0000FF">Slime Blue</a></div></div>
                <div class="span4" style="background: #FF00CC;"><div><a href="/?color=FF00CC">Potion Pink</a></div></div>
            </div>

            <div class="row">
                <div class="span4" style="background: #FFFFFF;"><div><a href="/?color=FFFFFF" style="color: black;">Skeleton White</a></div></div>
                <div class="span4" style="background: #000000;"><div><a href="/?color=random">Spooky Dooky</a></div></div>
                <div class="span4" style="background: #000000;"><div><a href="/?color=000000">Night Black</a></div></div>
            </div>
        </div>
    </div>


    </div>

    <script src="./static/js/bootstrap.min.js"></script>

</body>
</html>