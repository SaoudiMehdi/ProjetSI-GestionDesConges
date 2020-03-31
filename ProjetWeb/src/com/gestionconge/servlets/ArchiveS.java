package com.gestionconge.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestionconge.formulaires.Demandes;
import com.gestionconge.formulaires.DonneesDemande;

/**
 * Servlet implementation class ArchiveS
 */
@WebServlet("/ArchiveS")
public class ArchiveS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArchiveS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DonneesDemande donnees[] = new DonneesDemande[100];
		Demandes form = new Demandes();
		donnees = form.Archive();
		request.setAttribute("donnees", donnees);
		this.getServletContext().getRequestDispatcher("/WEB-INF/Archive.jsp").forward(request, response);
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
		request.setAttribute("printable", demandes);
		doGet(request, response);
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
