<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link rel="stylesheet" href="css/headerstyle.css">
<%

response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);

  if(session.getAttribute("fonctionnaire")==null)
      response.sendRedirect("Accueil");

  %> 
<header>
			<img src="images/header.png" class="img-fluid" alt="Responsive image">
			<nav class="navbar navbar-expand-lg navbar-light bg-light ">
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
					<div class="navbar-nav mr-auto">
						<a class="nav-item nav-link active" href="Conge">Demande</a>
						<a class="nav-item nav-link active" href="Etat">Situation<span class="sr-only"></span></a>
						<a class="nav-item nav-link active" href="Demandes">Demandes<span class="sr-only"></span></a>
						<a class="nav-item nav-link active" href="Absence">Absences</a>
						<a class="nav-item nav-link active" href="Archive">Archives</a>
						<a class="nav-item nav-link active" id="Fonctionnaire" href="Fonctionnaire">Fonctionnaires</a>
						<a class="nav-item nav-link active" id="Feries" href="Feries">Fêtes</a>
						<a class="nav-item nav-link active" href="profile">Compte<span class="sr-only"></span></a>
						<a class="nav-item nav-link active" href="Accueil?con=0" style="float:left;">Déconnexion</a>
					</div>
				</div>
			</nav>
</header>

<script>
		var admin = '${sessionScope.admin}';
		if(admin==0){
			document.getElementById("Demandes").style.display ='none';
			document.getElementById("Fonctionnaire").style.display ='none';
			document.getElementById("Feries").style.display ='none';
			document.getElementById("Archive").style.display ='none';
		}
</script>