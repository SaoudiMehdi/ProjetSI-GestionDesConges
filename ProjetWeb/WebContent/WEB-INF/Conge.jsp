<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.gestionconge.formulaires.DonneesFonctionnaires" %>
<!DOCTYPE html>
<html dir="ltr" lang="fr">
	<head>
		<title>Demande de congé</title>
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
			<section class="sec3">
				<h4>Demande de congé </h4>
			</section>
			<section class="sec4">
				<form class="table-responsive" method="post" action="Conge">
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
								<td><input value="${empty fonctionnaire?'':fonctionnaire.matricule}" name="matricule" readonly="readonly"></input></td>
							</tr>
							<tr>
								<th scope="row">Type congé</th>
								<td><select name="libelleType">
									<option value="Congéannuel">Congé annuel</option>
									<option value="Congénaissance">Congé de naissance</option>
									<option value="Congédécès">Congé de décès</option>
									<option value="Congématernité">Congé de maternité</option>
									<option value="Congépelerinage">Congé de pelerinage</option>
									<option value="Congémaladie">Congé de maladie</option>
								</select></td>
							</tr>
							<tr>
								<th scope="row">Date demande</th>
								<td><input id="today" type="date" name="dateDemande"></td>
							</tr>
							<!--  <tr>
								<th scope="row">Durée demandée</th>
								<td><input placeholder="Nombre de jours" name="duree" min="1" max="22"></input></td>
							</tr>-->
							<tr>
								<th scope="row">Remplacement</th>
								<td>
									<select id="rempl" name="remplacement">
										<option></option>
										<c:forEach items="${remplacent}" var="item">
			 								<c:if test="${!empty item}">
			 									<option>${item.prenom} ${item.nom}</option>
			 								</c:if>
										</c:forEach>
									</select>
								</td>
							</tr>
							  <tr>
								<th scope="row">Durée demandée</th>
								<td><input placeholder="Nombre de jours" name="dureedemandée" min="1" max="22"></input></td>
							</tr>
						</thead>
					</table>
					<input type="submit" value="Envoyer">
				</form>
			</section>
		</nav>
		<%@ include file="foot.jsp" %>
		<script>
			document.querySelector("#annee").innerHTML = new Date().getFullYear();
		</script>
		<script>
			let today = new Date().toISOString().substr(0, 10);
			document.querySelector("#today").value = today;
		</script>
		<script type="text/javascript">
			var erreur = "${form.erreurs['duree']}";
			if(erreur.length>0){
				alert(erreur);	
			}
			
		</script>
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	</body>
</html>