<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Turing Careers - Login</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <link rel="icon" type="image/x-icon" href="images/favicon-32x32.png">
  <link rel="stylesheet" href="stylesheet/login.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script type="text/javascript" src="scripts/validateForm.js"></script>
</head>
<body>
<form name="form" action="AuthenticationServlet" onsubmit="return validateLoginForm()" method="post">
  <div class="title">Benvenuto</div>
  <div class="subtitle">Login</div>
  <%
    String block = "none";
    String outcome = (String) request.getAttribute("authOutcome");
    if(outcome != null) {
      if (outcome.equals("negative"))
        block = "block";
    }else if(outcome == null)
      block = "none";
  %>
  <div id="error" class="error"  style="display: <%=block%>">Riprova!</div>
  <input type="hidden" name="type" value="login">
  <div class="input-container ic1">
    <input name="email" id="email" class="input" type="email" placeholder="Email" />
    <div class="cut"></div>
  </div>
  <div class="input-container ic2">
    <input name="password" id="password" class="input" type="password" placeholder="Password" />
    <div class="cut"></div>
  </div>
  <div class="register">Non hai ancora un account? <a href="subscription.jsp">Registrati</a></div>
  <button type="submit" class="submit">Invia</button>
</form>
</body>
</html>


