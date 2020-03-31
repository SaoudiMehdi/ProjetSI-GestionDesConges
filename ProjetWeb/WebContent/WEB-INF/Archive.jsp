<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html dir="ltr" lang="fr">
	<head>
		<title>Liste des congés</title>
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
		<nav>
			<section class="table-responsive" id="sec6">
				<div id="section-to-print">
					<img  class="royaume" id="royaume" src="images/royaume.png" style="display:none;">
					<section class="sec1">
						<h4>Liste des congés pour l'année :  <label id="annee"></label></h4>
					</section>
					<table class="table table-bordered" id="myTable">
						<thead id="ok">
							<tr class="tete">
								<th scope="col">Matricule</th>
								<th scope="col">Nom et prénom</th>
								<th scope="col">Grade</th>
								<th scope="col">Remplacer par </th>
								<th scope="col">Date de l'accord</th>
								<th scope="col">Date demandée</th>
								<th scope="col">Date proposée</th>
								<th scope="col">Date confirmée</th>
								<th scope="col">Date du retour</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${donnees}" var="item">
								<c:if test="${!empty item }">
								<form id="${item.dateRetour} ${item.nom}" method="post" action="Archive">
									<tr>
										<th scope="row"><%=i %></th>
										<td style="display:none;">
			 								<input name="numSomme" value="${item.numSomme}" >
			 							</td>
			 							<td style="display:none;">
			 								<input name="nom" value="${item.nom}" >
			 							</td>
			 							<td style="display:none;">
			 								<input name="departement" value="${item.departement}" >
			 							</td>
			 							<td style="display:none;">
			 								<input name="remplacent" value="${item.remplacent}" >
			 							</td>
			 							<td style="display:none;">
			 								<input name="duree" value="${item.duree}" >
			 							</td>
			 							<td style="display:none;">
			 								<input name="dateDemande" value="${item.dateDemande}" >
			 							</td>
			 							<td style="display:none;">
			 								<input name="datePropose" value="${item.datePropose}" >
			 							</td>
			 							<td style="display:none;">
			 								<input name="dateFinal" value="${item.dateFinal}" >
			 							</td>
			 							<td style="display:none;">
			 								<input name="dateRetour" value="${item.dateRetour}" >
			 							</td>
										<td>${item.nom}</td>
										<td>${item.departement}</td>
										<td>${item.remplacent}</td>
										<td>${item.duree}</td>
										<td>${item.dateDemande}</td>
										<td>${item.datePropose}</td>
										<td>${item.dateFinal}</td>
										<td>${item.dateRetour}</td>
										<td  id="bSubmit">
			 								<button onclick="Sumbit(${item.dateRetour} ${item.nom})" value="valider">Valider</button>
			 							</td>
									</tr>
								</form>
									<% i++; %>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<button class="btn btn-primary" type="submit">Imprimer</button>
				<button class="btn btn-primary" type="submit"> Excel </button>

			</section>
			<section class="sec5" id="section-to-print2" style="display:none;">
				<img src="images/entete.png" class="imgFluid" alt="header">
				<h4>Détails du congé</h4>
				<p>Le retour au travail</p>
				<table class="table table-bordered" id="recu">
						<thead>
							<tr>
								<th scope="row">Nom et prénom</th>
								<td>${printable.nom}</td>
							</tr>
							<tr>
								<th scope="row">Grage</th>
								<td>${printable.departement}</td>
							</tr>
							<tr>
								<th scope="row">Matricule</th>
								<td>${printable.numSomme}</td>
							</tr>
							<tr>
								<th scope="row">Direction</th>
								<td>المديرية الفرعية لدى محكمةالاستئناف بني ملال</td>
							</tr>
							<tr>
								<th scope="row">Date du retour</th>
								
								<td>
									<jsp:useBean id="date" class="java.util.Date"/>
									<fmt:formatDate value="${printable.dateRetour}" type="date" pattern="yyyy-MM-dd"/>
								</td>
							</tr>
						</thead>
					</table>
					<h6 class="date1">ببني ملال في <label id="today"></h6>
					<h6 class="date2">شهد بصحته ووجهه لوزير العدل <br> ببني ملال في <label id="today2"></h6>
					<img src="images/entete.png" class="imgFluid" alt="header" style="margin-top:360px;">
				<h5>من المدير الفرعي الإقليمي لدى محكمة الاستئناف ببني ملال</h5>
				<h6>إلى السيد وزير العدل<br>مديرية الموارد البشرية<br>تحت إشراف السلم الاداري</h6>
				<h5>ورقة الإرسال</h5>
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
									تجدون رفقته<br><br>إستئناف العمل في اسم:<br><br>السيد ${printable.prenom} ${printable.nom} ${printable.departement} رقم تأجيره: ${printable.numSomme} 
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
			var count = document.getElementById("myTable").rows.length;
			if(count >= 4) document.getElementById('footer').style["margin-top"] = "0";
			else document.getElementById('footer').style["margin-top"] = "76px";
			document.querySelector("#print").addEventListener("click", function() {
				document.getElementById("royaume").style.display = "block";
				window.print();
				document.getElementById("royaume").style.display = "none";
			});
		</script>
		<script>
			var printable = '${empty printable?'':1}';
			if(printable!=''){
			document.getElementById("section-to-print").id = "ok";
			document.getElementById("section-to-print2").id = "section-to-print";
			document.getElementById("section-to-print").style.display= "block";
			window.print();
			setTimeout(function(){document.getElementById("section-to-print").id = "section-to-print2";
			document.getElementById("ok").id = "section-to-print";
			document.getElementById("section-to-print2").style.display="none"; 
			},2000);
			}
			
		</script>
		<script>
			function fnExcelReport()
{
    var tab_text="<table border='2px'><tr bgcolor='#87AFC6'>";
    var textRange; var j=0;
    tab = document.getElementById('myTable'); // id of table

    for(j = 0 ; j < tab.rows.length ; j++) 
    {     
        tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
        //tab_text=tab_text+"</tr>";
    }

    tab_text=tab_text+"</table>";
    tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
    tab_text= tab_text.replace(/<img[^>]*>/gi,""); // remove if u want images in your table
    tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params

    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE "); 

    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
    {
        txtArea1.document.open("txt/html","replace");
        txtArea1.document.write(tab_text);
        txtArea1.document.close();
        txtArea1.focus(); 
        sa=txtArea1.document.execCommand("SaveAs",true,"Say Thanks to Sumit.xls");
    }  
    else                 //other browser not tested on IE 11
        sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));  

    return (sa);
}
			
		</script>
		<script type="text/javascript">
		 	var d = new Date();
		  	var n = d.getFullYear();
		  	document.getElementById("annee").innerHTML = n;
		</script>
		<script>
			function Submit(id){
				document.getElementById(id).submit();
			}
		</script>
		<script>
			let today = new Date().toISOString().substr(0, 10);
			document.querySelector("#today").innerHTML = today;
			document.querySelector("#today2").innerHTML = today;
		</script>
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	</body>
</html>