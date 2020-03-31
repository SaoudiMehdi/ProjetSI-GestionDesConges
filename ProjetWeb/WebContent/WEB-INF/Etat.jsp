<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html dir="ltr" lang="fr">
	<head>
		<title>Situation</title>
		<meta charset="utf-8">
		
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/adminStyle.css">
		<link rel="stylesheet" href="css/footerstyle.css">
		<link href="https://fonts.googleapis.com/css?family=Fredoka+One" rel="stylesheet">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>
	<body>
		<%@ include file="header.jsp" %>
		<% int i=1; %>
		<nav>
			<section class="sec1">
				<h4>Situation pour l'année: <label id="annee"></label></h4>
			</section>
			<section class="sec1" id="PasEtats">
				<h6>Pas d'état</h6>
			</section>
			<section class="sec2" id="Etats">
					<table class="table table-bordered" id="myTable">
						<thead style="background-color:#C0C0C0;">
							<tr>
								<th scope="col">Matricule</th>
								<th scope="col">Date de début</th>
								<th scope="col">Date de retour</th>
								<th scope="col">Durée</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach items="${donnees}" var="item">
								<c:if test="${!empty item}">
									<tr>
										<th scope="row"><%=i%></th>
										<td>${item.dateFinal}</td>
										<td>${item.dateRetour}</td>
										<td>${item.duree}</td>
									</tr>
									<% i++; %>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
			</section>

		</nav>
		<%@ include file="foot.jsp" %>
		<script>
			var count = document.getElementById("myTable").rows.length;
			if(count >= 4) document.getElementById('footer').style["margin-top"] = "0";
			else document.getElementById('footer').style["margin-top"] = "129px";
			var bool = '${empty donnees ? 0:1}';
			if(bool == 0){
				document.getElementById('Etats').style.display = 'none';
				document.getElementById('PasEtats').style.display = 'true';
			}
			else{
				document.getElementById('Etats').style.display = 'true';
				document.getElementById('PasEtats').style.display = 'none';
			}
		</script>
		<script type="text/javascript">
		 	var d = new Date();
		  	var n = d.getFullYear();
		  	document.getElementById("annee").innerHTML = n;
		</script>
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	</body>
</html>