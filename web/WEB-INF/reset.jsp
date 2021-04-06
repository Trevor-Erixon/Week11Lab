<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
    </head>
    <body>
        <h1>Notes App</h1>
        <h2>Reset Password</h2>        
        <p>Please enter your email address to reset your password</p>
        <form action="/reset" method="POST">
            Email Address: <input type="text" name="resetEmail"><br>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
