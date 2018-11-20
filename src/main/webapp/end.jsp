<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Devine un nombre</title>
    </head>
    <body>
	<hr>
	<h2>${numberConnected} joueurs connectés</h2>
	<hr>

	<h3>Bravo, tu as gagné !</h3>
	<form method="POST">
            <input type="SUBMIT" name="action" value="Deconnexion"/>
            <input type="SUBMIT" name="action" value="Rejouer"/>
	</form>
    </body>
</html>
