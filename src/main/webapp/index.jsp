<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styling.css">
    <script src="functions.js"></script>
    <title>LoginPage</title>
</head>

<body>
<div class="loginbuttons">
    <button class="buttonloginasadmin" name="Login" onclick='showLoginasAdmin()'>Login as Admin</button>
    <button class="buttonloginasapplicant" name="Login" onclick='showLoginasApplicant()'>Login as Applicant</button>

    <form id="loginasadmin" method="post" action="Servlets.Login" style="display: none">
        <div class="container" style="background-color: transparent">

            <h1> Login Form</h1>

            <div class="pad">
                <label style="margin-right: 90%;"><b> Admin ID :</b></label>
                <input type="text" name="adminID" placeholder= "ADMIN ID" size="15" required/>
            </div>

            <button type="submit" class="loginbuttonsubmit" value="Call Servlet">Login as Admin</button>

        </div>
    </form>

    <form id="loginasapplicant" method="get" action="Servlets.Login" style="display: none">
        <div class="container" style="background-color: transparent">

            <h1> Login Form</h1>

            <div class="pad">
                <label style="margin-right: 90%;"><b> Applicant ID :</b></label>
                <input type="text" name="applicantID" placeholder= "APPLICANT ID" size="15" required/>
            </div>

            <button type="submit" class="loginbuttonsubmit" value="Call Servlet">Login as Applicant</button>

        </div>
    </form>
</div>
</body>
</html>