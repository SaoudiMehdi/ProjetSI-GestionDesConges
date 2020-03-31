package com.gestionconge.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestionconge.formulaires.DonneesFonctionnaires;
import com.gestionconge.formulaires.FormulaireInscription;

/**
 * Servlet implementation class FonctionnaireS
 */
@WebServlet("/FonctionnaireS")
public class FonctionnaireS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FonctionnaireS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DonneesFonctionnaires [] fonctionnaires = new DonneesFonctionnaires[100];
		FormulaireInscription form = new FormulaireInscription();
		fonctionnaires = form.FonctionnairesArchive();
		request.setAttribute("fonctionnaires", fonctionnaires);
		this.getServletContext().getRequestDispatcher("/WEB-INF/Fonctionnaire.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
