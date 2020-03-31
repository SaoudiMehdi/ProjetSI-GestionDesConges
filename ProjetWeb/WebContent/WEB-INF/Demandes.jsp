<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.gestionconge.formulaires.DonneesDemande" %>
<!DOCTYPE html>
<html dir="ltr" lang="fr">
	<head>
		<title>Liste des demandes</title>
		<meta charset="utf-8">
		
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/footerstyle.css">
		<link rel="stylesheet" href="css/demandes.css">
		<link href="https://fonts.googleapis.com/css?family=Fredoka+One" rel="stylesheet">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>
	<body onload="loadDemande()">
		<div>
		<%@ include file="header.jsp" %>
		<nav>
			<section class="sec1">
				<h4 id="ko">Liste des demandes</h4>
			</section>
			<p style="text-align:center;" id="noDemande" >Pas de demandes</p>
			<section class="table-responsive" id="sec3" style="display:none;">
					<table class="table table-bordered" id="myTable">
						<thead style="background-color:#C0C0C0;">
							<tr>
								<th scope="col">Nom et prénom</th>
								<th scope="col">Grade</th>
								<th scope="col">Date de la demande</th>
								<th scope="col">Date demandée</th>
								<th scope="col">Durée</th>
								<th scope="row">Remplacer par : </th>
							</tr>
						</thead>
						<% int i=1; %>
						<tbody>
							<c:forEach items="${demandes}" var="item">
								<c:if test="${!empty item}">
			 						<form id="${item.numSomme} ${item.dateDemande}" method="post" action="DemandeS">
			 							<tr>
			 							<td style="display:none;">
			 								<input name="matricule" value="${item.matricule}" >
			 							</td>
			 							<td>
			 								<input name="nom" value="${item.nom}" readonly="readonly">
			 							</td>
			 							<td>
			 								<input name="grade" value="${item.grade}" readonly="readonly">
			 							</td>
			 							<td>
			 								<input name="duree" value="${item.duree}" readonly="readonly">
			 							</td>
			 							<td>
			 								<input name="dateDemande" value="${item.dateDemande}"  readonly="readonly">
			 							</td>
			 							<td>
			 								<input name="dateFinal" type="date" value="${item.dateDemande}" id="date<%=i+1%>">
			 							</td>
			 							<td>
			 								<select name="remplacent" >
			 									<option value="${item.remplacent}">${item.remplacent}</option>
			 									<c:forEach items="${remplacent}" var="itemR">
			 										<c:if test="${!empty itemR}">
			 											<option value="${itemR.prenom} ${itemR.nom}" >${itemR.prenom} ${itemR.nom}</option>
			 										</c:if>
			 										
			 									</c:forEach>
			 								</select>
			 							</td>
			 							<td>
			 								<button onclick="Sumbit(${item.numSomme} ${item.dateDemande})" id="print" value="valider">Valider</button>
			 							</td>
			 						</tr>
			 						</form>
			 						<%i=i+2; %>
			 					</c:if>
							</c:forEach>
						</tbody>
					</table>
			</section>
				
			<section class="sec5" id="section-to-print" style="display:none;">
				<img src="images/entete.png" class="imgFluid" alt="header">
				<h4>Demande de congé</h4>
				<p>Attribution de congé</p>
				<table class="table table-bordered" id="recu">
						<thead>
							<tr>
								<th scope="row">Nom et prénom</th>
								<td>${printable.nom}</td>
							</tr>
							<tr>
								<th scope="row">Grade</th>
								<td>${printable.departement}</td>
							</tr>
							<tr>
								<th scope="row">Matricule</th>
								<td>${printable.numSomme}</td>
							</tr>
							<tr>
								<th scope="row">Direction</th>
								<td>Direction générale</td>
							</tr>
							<tr>
								<th scope="row">Date début de congé</th>
								
								<td>
									<jsp:useBean id="date" class="java.util.Date"/>
									<fmt:formatDate value="${printable.dateFinal}" type="date" pattern="yyyy-MM-dd"/>
								</td>
							</tr>
							<tr>
								<th scope="row">Durée</th>
								<td> ${printable.duree} jours</td>
							</tr>
							<tr>
								<th scope="row">Remplacé par </th>
								<td>${printable.remplacent}</td>
							</tr>
						</thead>
					</table>
					<h6 class="date1">Rabat le :<label id="today"></h6>
					<img src="images/entete.png" class="imgFluid" alt="header" style="margin-top:360px;">
				<h5>Du responsable Ressource humaine</h5>
				<h6>Au monsieur le directeur général</h6>
				<h5>Envoyer</h5>
				<table class="table table-bordered" id="recu" style="text-align:center;">
						<thead id="ok">
							<tr class="tete">
								<th scope="col">المستندات</th>
								<th scope="col">عدد المرفقات</th>
								<th scope="col">ملاحظات</th>
							</tr>
						</thead>
						<tbody>
							<tr style="height:400px;">
								<td style="width:50%; text-align:right; text-indent:20px;">
									تجدون رفقته<br><br>ايقاف العمل في اسم:<br><br>السيد ${printable.prenom} ${printable.nom} ${printable.departement} رقم تأجيره: ${printable.numSomme} 
								</td>
								<td style="width:10%;"></td>
								<td style="width:40%; text-align:center;">
									لكل غاية مفيدة<br><br> مع فائق التقدير والاحترام. <br><br> والسلام
								</td>
							</tr>
						<tbody>
					</table>
			</section>
		</nav>
		<%@ include file="foot.jsp" %>
		
		<script>
			var printable = '${empty printable?'':1}';
			if(printable!=''){
			document.getElementById("section-to-print").style.display = "block";
			window.print();
			setTimeout(function(){document.getElementById("section-to-print").style.display = "none";},2000);
			}
			
		</script>
		<script>
			function loadDemande(){
				var nombreDemandes = '${nombreDemandes}';
				if(nombreDemandes>0){
					document.getElementById("sec3").style.display = "block";
					document.getElementById("noDemande").style.display = "none";
				}
			}
		</script>
		<script>
			let today = new Date().toISOString().substr(0, 10);
			document.querySelector("#today").innerHTML = today;
			document.querySelector("#today2").innerHTML = today;
		</script>
		<script>
			function myFunction(val){
			let today = new Date().toISOString().substr(0, 10);
			document.getElementById("date"+(val+1)).value = document.getElementById("date"+val).value;}
		</script>
		<script>
			function Submit(id){
				document.getElementById(id).submit();
			}
		</script>
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	</body>
</html>