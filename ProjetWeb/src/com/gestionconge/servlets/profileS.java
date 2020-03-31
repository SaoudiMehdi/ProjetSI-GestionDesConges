package com.gestionconge.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gestionconge.formulaires.DonneesFonctionnaires;
import com.gestionconge.formulaires.FormulaireInscription;

/**
 * Servlet implementation class profileS
 */
@WebServlet("/profileS")
public class profileS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public profileS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FormulaireInscription form = new FormulaireInscription();
		DonneesFonctionnaires donnees = new DonneesFonctionnaires();
		int numSomme=0;
		if(request.getParameter("numSomme")!=null) numSomme= Integer.parseInt(request.getParameter("numSomme"));
		
		/* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        DonneesFonctionnaires  fonctionnaire = (DonneesFonctionnaires) session.getAttribute( "fonctionnaire");
		if(numSomme!=0) {
			donnees = form.Profile(numSomme);
		}else {
			donnees = form.Profile(fonctionnaire.getMatricule());
		}
		request.setAttribute("donnees", donnees);
		request.setAttribute("fonctionnaire", fonctionnaire);
		this.getServletContext().getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		FormulaireInscription form = new FormulaireInscription();
		int numSomme=0;
		if(request.getParameter("numSomme")!=null) numSomme= Integer.parseInt(request.getParameter("numSomme"));
		if(numSomme!=0) {
			request.setAttribute("numSomme", numSomme);
		}
		form.SaveUpdate(request);
		doGet(request,response);
	}

}
