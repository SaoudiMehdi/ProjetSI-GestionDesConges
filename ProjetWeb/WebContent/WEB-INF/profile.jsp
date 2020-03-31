<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html dir="ltr" lang="fr">
	<head>
		<title>Profile</title>
		<meta charset="utf-8">
		
		
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/footerstyle.css">
		<link rel="stylesheet" href="css/adminStyle.css">
		<link href="https://fonts.googleapis.com/css?family=Fredoka+One" rel="stylesheet">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		
	</head>
	<body>
		<%@ include file="header.jsp" %>
		<nav>
			<section class="sec3">
				<h4>Informations</h4>
			</section>
			<section class="sec4">
				<form class="table-responsive" method="post" action="profile">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th scope="row">Nom</th>
								<td><input value="${empty fonctionnaire?'':fonctionnaire.nom}" name="nom" readonly="readonly"><br></td>
							</tr>
							<tr>
								<th scope="row">Prénom</th>
								<td><input value="${empty fonctionnaire?'':fonctionnaire.prenom}" name="nom" readonly="readonly"></input></td>
							</tr>
							<tr>
								<th scope="row">Grade</th>
								<td><input value="${empty fonctionnaire?'':fonctionnaire.grade}" name="grade" readonly="readonly"></input></td>
							</tr>
							<tr>
								<th scope="row">Matricule</th>
								<td><input value="${donnees.matricule}" name="matricule" id="matricule"></input></td>
							</tr>
							<tr>
								<th scope="row">Télèphone</th>
								<td><input value="0${donnees.telephone}" name="telephone"></input></td>
							</tr>
							<tr>
								<th scope="row">Email</th>
								<td><input value="${donnees.email}" name="email"></input></td>
							</tr>
							<tr>
								<th scope="row">Nom d'utilisateur</th>
								<td><input value="${donnees.login}" name="login" id="login"></input></td>
							</tr>
							<tr>
								<th scope="row">Mot de passe</th>
								<td>
									<input type="password" value="${donnees.password}"  name="password" id="pass" style="float: left; width: 90%;"></input>
									<%-- <input type="button" id="PassAffich" onclick="myFunction()"></input> --%>
									<img id="PassAffich" onclick="myFunction()" src="images/eyeclose.png"></img>
								</td> 
							</tr>
						</thead>
					</table>
					<input type="submit" value="Valider">
				</form>
			</section>
		</nav>
		
		<%@ include file="foot.jsp" %>
		<script>
			// Get the modal
			var modal = document.getElementById("myModal");

			// Get the button that opens the modal
			var btn = document.getElementById("myBtn");

			// Get the <span> element that closes the modal
			var span = document.getElementsByClassName("close")[0];

			// When the user clicks the button, open the modal 
			btn.onclick = function() {
				modal.style.display = "block";
			}

			// When the user clicks on <span> (x), close the modal
			span.onclick = function() {
				modal.style.display = "none";
			}

			// When the user clicks anywhere outside of the modal, close it
			window.onclick = function(event) {
				if (event.target == modal) {
					modal.style.display = "none";
				}
			}
		</script>
		<script>
			let today = new Date().toISOString().substr(0, 10);
			document.querySelector("#today").value = today;
		</script>
		<script>
			function myFunction(){
				var x = document.getElementById("pass");
				var y = document.getElementById("PassAffich");
				if (x.type == "password") {
					x.type = "text";
					y.src = "images/eyeopen.png";
				} else {
					x.type = "password";
					y.src = "images/eyeclose.png";
				}
			}
		</script>
		<script>
			var admin = '${fonctionnaire.administrateur}';
			if(admin==0){
				document.getElementById("prenom").readOnly= "readonly";
				document.getElementById("prenom").style.backgroundColor="#dedec8";
				document.getElementById("cadre").readOnly= "readonly";
				document.getElementById("cadre").style.backgroundColor="#dedec8";
				document.getElementById("numSomme").readOnly= "readonly";
				document.getElementById("numSomme").style.backgroundColor="#dedec8";
				document.getElementById("login").readOnly= "readonly";
				document.getElementById("login").style.backgroundColor="#dedec8";
			}
		</script>
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	</body>
</html>