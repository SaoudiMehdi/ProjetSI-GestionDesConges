<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html dir="ltr" lang="fr">
	<head>
		<title>Nouveau fonctionnaire</title>
		<meta charset="utf-8">
		
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/adminStyle.css">
		<link rel="stylesheet" href="css/footerstyle.css">
		<link href="https://fonts.googleapis.com/css?family=Fredoka+One" rel="stylesheet">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>
	<body>
		<%@ include file="header.jsp" %>
		<nav>
			<section class="sec1">
				<h4>Nouveau fonctionnaire</h4>
			</section>
			<section class="sec2">
				<form method="post" action="AjoutFonctionnaire">
					<div class="form-row">
						<div class="form-group col-md-6">
							<input type="text" class="form-control " id="inputPrenom" name="prenom" placeholder="Prénom" required>
						</div>
						<div class="form-group col-md-6">
							<input type="text" class="form-control" id="inputNom" name="nom" placeholder="Nom" required>
						</div>
						<div class="form-group col-md-6">
							<input type="text" class="form-control" id="inputCin" name="cin" placeholder="N°Carte nationnale" required>
						</div>
						<div class="form-group col-md-6">
							<input pattern="[0-9]*" class="form-control" id="inputEmail4" name="matricule" placeholder="matricule" required>
						</div>
						<div class="form-group col-md-6">
							<input type="text" class="form-control" id="inputAdresse" name="adresse" placeholder="Adresse" required>
						</div>
						<div class="form-group col-md-6">
							<input style="float: right;" pattern="[0-9]{9}" class="form-control" id="inputTelephone" name="telephone" placeholder="Téléphone" required>
						</div>
						<div class="form-group col-md-6">
							<input type="email" class="form-control" id="inputEmail" name="email" placeholder="Email" required>
						</div>
						
						<div class="form-group col-md-6">
							<select id="inputState" class="form-control" name="grade" required>
								<option value="" disabled selected hidden>Grade</option>
								<option value="ResponsableRH">Responsable RH</option>
								<option value="Manager">Manager</option>
								<option value="Employe">Employé</option>
							</select>	
						</div>
						<div class="form-group col-md-6">
							<input type="text" class="form-control" id="inputZip" name="login" placeholder="Nom de l'utilisateur" required>
						</div>
						<div class="form-group col-md-6">
							<input type="text" class="form-control" id="inputZip" name="password" placeholder="Mot de passe" required>
						</div>
					</div>
					<input type="submit" class="btn btn-primary" value="Ajouter" style="float:Center;">
				</form>
			</section>
		</nav>
		<%@ include file="foot.jsp" %>
		<script type="text/javascript">
			function changeValue(){
				var val = document.getElementById('defaultCheck1').value;
				if(val==0){
					document.getElementById('defaultCheck1').value='1';
				}else{
					document.getElementById('defaultCheck1').value='0';
				}
			}			   
		</script>
		<script type="text/javascript">
			var erreur = "${form.erreurs['fonc']}";
			if(erreur.length>0){
				alert(erreur);	
			}
			
		</script>
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	</body>
</html>