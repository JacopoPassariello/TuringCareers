<!DOCTYPE html>
<html>
<head>
    <link rel="icon" type="image/x-icon" href="images/favicon-32x32.png">
    <link rel="stylesheet" href="stylesheet/subscription.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="scripts/validateForm.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Turing Careers - Iscrizione</title>
</head>
<body>
<form name="form" action="AuthenticationServlet" onsubmit="return validateSubForm()" method="post">
    <div class="title">Benvenuto</div>
    <div class="subtitle">Creazione Account</div>
    <%
        String block = "none";
        String authOutcome = (String) request.getAttribute("authOutcome");
        if(authOutcome != null) {
            if (authOutcome.equals("negative"))
                block = "block";
        }else if(authOutcome == null)
            block = "none";
    %>
    <div id="error" class="error"  style="display: <%=block%>">Riprova!</div>
    <input type="hidden" name="type" value="register">
    <div class="input-container ic1">
        <input id="firstname" name="firstname" class="input" type="text" placeholder="Nome" />
        <div class="cut"></div>
    </div>
    <div class="input-container ic2">
        <input id="lastname" name="lastname" class="input" type="text" placeholder="Cognome" />
        <div class="cut"></div>
    </div>
    <div class="input-container ic2">
        <input id="email" name="email" class="input" type="email" placeholder="Email" />
        <div class="cut cut-short"></div>
    </div>
    <div class="input-container ic2">
        <input id="password" name="password" class="input" type="password" placeholder="Password" />
        <div class="cut cut-short"></div>
    </div>
    <div class="input-container ic2 radiobutton">
        <label style="float: left; margin-right: 10px; margin-left: 40px;">Employer</label>
        <input type="radio" id="accountTypeEmlpoyer" name="userTypeEmp" value="employer" style="float: left;">
        <input type="radio" id="accountTypeDeveloper" name="userTypeDev" value="developer" style="float: right; margin-right: 40px;">
        <label style="float: right; margin-right: 10px;">Developer</label>
        <div style="clear: both;"></div> <!-- Clear float per evitare problemi di layout -->
    </div>
    <div class="login">Hai gi&agrave; un account? <a href="login.html">Login</a></div>
    <button type="text" class="submit">Invia</button>
</form>
</body>
</html>






