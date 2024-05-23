<%@ page import="david_seu.snake_game.domain.User" %>
<%@ page import="com.google.gson.Gson" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Snake Game</title>
    <link
            rel="stylesheet"
            href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="intro.js-2.9.3/intro.js"></script>
    <link href="intro.js-2.9.3/introjs.css" rel="stylesheet" />
    <link rel="stylesheet" href="css/snake.css" />
    <%
        String userJson = (String) session.getAttribute("user");
        if (userJson == null) {
            response.sendRedirect("");
        }
        Gson gson = new Gson();
        User user = gson.fromJson(userJson, User.class);
        System.out.println(user.getUsername() + " " + user.getScore() + " " + user.getTimeSpent());
    %>
</head>
<body>
<nav
        class="navbar navbar-expand-sm fixed-top"
        data-intro="Leaderboard and Logout buttons for viewing your rank and saving your high score."
>
    <!-- Links -->
    <ul class="nav navbar-nav ml-auto w-100 justify-content-end">
        <li class="nav-item">
            <a class="nav-link" id="store" href="#">Logout</a>
        </li>
    </ul>
</nav>
<canvas
        width="600"
        height="600"
        id="game"
></canvas>
<div class="card custom">
    <div class="card-body">
        <div class="text-center"><%= user.getUsername() %></div>
        <div class="text-center"><label for="score" class= ></label>Score</div>
        <form action = "process.jsp" id="myform" method = "POST">
            <input
                    type="text"
                    value="0"
                    class="form-control text-center"
                    id="score"
                    disabled
            />
            <div class="text-center"><label for="maxscore" class= ></label>Max Score</div>
            <input
                    type="text"
                    value="<%= user.getScore() %>"
                    class="form-control text-center"
                    id="maxscore"
                    disabled
            />
            <div class="text-center"><label for="time" class= ></label>Time Spent</div>
            <input
                    type="text"
                    value="<%= user.getTimeSpent() %>"
                    class="form-control text-center"
                    id="time"
                    disabled/>
        </form>
    </div>
</div>
<script src="js/snake.js"></script>
</body>
</html>