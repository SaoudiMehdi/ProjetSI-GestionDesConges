<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html dir="ltr" lang="fr">
	<head>
		<title>Fêtes religieuses</title>
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
				<h4>Fêtes religieuses</h4>
			</section>
			<section class="sec2">
				<form class="table-responsive" method="post" action="Feries" >
					<%-- <div class="form-row">
						<div class="form-group col-md-6">
							<label for="today">Aid al Fitr &nbsp </label>
							
							<input id="today" name="Fitr" type="date" value="${aid.fitr}" style="margin-left:36px;">
						</div>
						<div class="form-group col-md-6">
							<label for="today1">Aid al Adha  </label>
							<input name="Adha" id="today1" type="date" value="${aid.adha}" style="margin-left:36px;">
						</div>
						<div class="form-group col-md-6">
							<label for="today2">1 er Moharram </label>
							<input id="today2" name="Moharam" type="date" value="${aid.moharam}" style="margin-left:16px;">
						</div>
						<div class="form-group col-md-6">
							<label for="today3">Aid al Maoulid  </label>
							<input id="today3" name="Nabawi" type="date" value="${aid.nabawi}" style="margin-left:16px;">
						</div>
					</div>--%>
					<table class="table table-bordered">
						<thead>
								<tr>
									<th scope="row">Aid al Fitr &nbsp</th>
									<td><input id="today" name="Fitr" type="date" value="${aid.fitr}"><br></td>
								</tr>
								<tr>
									<th scope="row">Aid al Adha</th>
									<td><input name="Adha" id="today1" type="date" value="${aid.adha}""></td>
								</tr>
								<tr>
									<th scope="row">1 er Moharram</th>
									<td><input id="today2" name="Moharam" type="date" value="${aid.moharam}"></td>
								</tr>
								<tr>
									<th scope="row">Aid al Maoulid</th>
									<td><input id="today3" name="Nabawi" type="date" value="${aid.nabawi}"></td>
								</tr>
						<thead>	
					</table>
					<%-- <div class="form-group col-md-1"></div>--%>
					<input type="submit" value="Valider" class="btn btn-primary">
				</form>
			</section>
		</nav>
		<%@ include file="foot.jsp" %>
		

		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	</body>
</html>