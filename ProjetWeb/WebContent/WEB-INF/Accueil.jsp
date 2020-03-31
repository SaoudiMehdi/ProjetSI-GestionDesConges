<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html dir="ltr" lang="fr">
	<head>
		<title>Bienvenue!!</title>
		<meta charset="utf-8">
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link href="css/floating-labels.css" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css?family=Fredoka+One" rel="stylesheet">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>
	<body>
		<form class="form-signin" method="post" action="Accueil">
			<div class="text-center mb-4">
				<img class="mb-4" src="images/entreprise_.png" alt="" width="72" height="72">
				<h1 class="h3 mb-3 font-weight-normal">Authentification</h1>
				<%-- <p>Consulter vos demandes</p> --%>
			</div>

			<div class="form-label-group">
				<input type="text" id="inputEmail" name="inputEmail" class="form-control" placeholder="nom d'utilisateur" required autofocus required style="text-align: left;">
				<label for="inputEmail">Nom d'utilisateur</label>
			</div>

			<div class="form-label-group">
				<input type="password" id="inputPassword" name="inputPassword" class="form-control"  placeholder="mot de passe" required style="text-align:left;">
				<label for="inputPassword">Mot de passe</label>
			</div>

			<%-- <div class="checkbox mb-3" style="text-align: center;">
				<label>
					<input type="checkbox" value="remember-me"> Enregister vos données
				</label>
			</div> --%>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Valider</button>
			<p class="mt-5 mb-3 text-muted text-center">&copy; 2020-2021</p>
		</form>
		<script type="text/javascript">
			var erreur = "${form.erreurs['login']}";
			if(erreur.length>0){
				alert(erreur);	
			}
		</script>
		<%-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
			<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
		--%>
	</body>
</html>