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
 * Servlet implementation class AjoutFonctionnaireS
 */
@WebServlet("/AjoutFonctionnaireS")
public class AjoutFonctionnaireS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjoutFonctionnaireS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/AjoutFonctionnaire.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String path = "/WEB-INF/Fonctionnaire.jsp";
		FormulaireInscription form = new FormulaireInscription();
		boolean valide = form.Inscription(request);
		if(valide==false)
			path ="/WEB-INF/AjoutFonctionnaire.jsp";
		DonneesFonctionnaires [] fonctionnaires = new DonneesFonctionnaires[100];
		fonctionnaires = form.FonctionnairesArchive();
		request.setAttribute("fonctionnaires", fonctionnaires);
		request.setAttribute("form", form);
		this.getServletContext().getRequestDispatcher(path).forward(request, response);
	}

}
