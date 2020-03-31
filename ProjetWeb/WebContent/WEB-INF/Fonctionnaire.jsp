<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html dir="ltr" lang="fr">
	<head>
		<title>Fonctionnaires</title>
		<meta charset="utf-8">
		
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/pageAdminFonc.css">
		<link rel="stylesheet" href="css/footerstyle.css">
		<link href="https://fonts.googleapis.com/css?family=Fredoka+One" rel="stylesheet">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>
	<body>
		<%@ include file="header.jsp" %>
		<% int i=1; %>
		<a id="ajouterFct" href="AjoutFonctionnaire">Nouveau fonctionnaire</a>
		<h4 style="text-align: center;">Liste des fonctionnaires</h4>
		<nav>
			<section class="table-responsive" id="foncSec2">
				<form id="section-to-print">
					<div style="visibility:hidden;">
						<img src="royaume.png" alt="ok" class="royaume">
					</div>
					<table class="table table-bordered" id="myTable">
						<thead style="background-color:#C0C0C0;">
							<tr>
								<th scope="col">Numero</th>
								<th scope="col">Nom et prénom</th>
								<th scope="col">N° Carte nationnale</th>
								<th scope="col">Matricule</th>
								<th scope="col">Grade</th>
								<th scope="col">Téléphone</th>
								<th scope="col">Email</th>
								<th scope="col">Nom de l'utilisateur</th>
								<th scope="col">Mot de passe</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${fonctionnaires}" var="item">
								<c:if test="${!empty item }">
									<tr>
										<th scope="row"><%=i%></th>
										<td>${item.prenom} ${item.nom}</td>
										<td>${item.cin}</td>
										<td>${item.matricule}</td>
										<td>${item.grade}</td>
										<td>0${item.telephone}</td>
										<td>${item.email}</td>
										<td>${item.login}</td>
										<td>${item.password}</td>
										<td>
											<a href="profile?numSomme=${item.matricule}" style="text-decoration:none;">Changer</a>
										</td>
									</tr>
									<%i++;%>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</form>
			</section>
		</nav>
		<%@ include file="foot.jsp" %>
		<script>
			var count = document.getElementById("myTable").rows.length;
			if(count >= 4) document.getElementById('footer').style["margin-top"] = "0";
			else document.getElementById('footer').style["margin-top"] = "142px";
			document.querySelector("#print").addEventListener("click", function() {
			window.print();
			});
		</script>
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	</body>
</html>