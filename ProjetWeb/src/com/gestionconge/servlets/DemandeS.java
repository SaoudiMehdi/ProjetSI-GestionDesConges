package com.gestionconge.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestionconge.formulaires.Demandes;
import com.gestionconge.formulaires.DonneesDemande;
import com.gestionconge.formulaires.DonneesFonctionnaires;
import com.gestionconge.formulaires.formulaireConge;

/**
 * Servlet implementation class DemandeS
 */
@WebServlet("/DemandeS")
public class DemandeS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DemandeS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DonneesDemande[] demandes = new DonneesDemande[100];
		Demandes form = new Demandes();
		demandes = form.Chercher();
		int nombreDemandes=0;
		while((demandes[nombreDemandes])!=null) {
			nombreDemandes++;
		}
		formulaireConge formR = new formulaireConge();
		DonneesFonctionnaires[] remplacent = new DonneesFonctionnaires[100];
		remplacent = formR.remplacent(request);
		request.setAttribute("remplacent", remplacent);
		request.setAttribute("demandes", demandes);
		request.setAttribute("nombreDemandes", nombreDemandes);
		this.getServletContext().getRequestDispatcher("/WEB-INF/Demandes.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd");  
		DonneesDemande demandes = null;
		try {

			demandes = new DonneesDemande(Integer.parseInt(getValeurChamp(request,"matricule")),getValeurChamp(request,"nom"),getValeurChamp(request,"grade"),getValeurChamp(request,"direction"),Integer.parseInt(getValeurChamp(request,"typeconge")),formatter2.parse(getValeurChamp(request,"dateDemande")),getValeurChamp(request,"remplacent"),Integer.parseInt(getValeurChamp(request,"duree")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Demandes form = new Demandes();
		form.Valider(request);
		request.setAttribute("printable", demandes);
		doGet(request,response);
	}
	
/*fonction pour stocker les entrees*/
	
	public static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
	        String valeur = request.getParameter( nomChamp );
	        if ( valeur == null) {
	            return null;
	        } else {
	            return valeur;
	        }
	 }

}
