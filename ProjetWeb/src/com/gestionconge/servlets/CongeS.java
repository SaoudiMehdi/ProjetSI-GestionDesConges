package com.gestionconge.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gestionconge.formulaires.DonneesFonctionnaires;
import com.gestionconge.formulaires.formulaireConge;

/**
 * Servlet implementation class formulaireCongeS
 */
@WebServlet("/formulaireCongeS")
public class CongeS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CongeS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 DonneesFonctionnaires fonctionnaire = (DonneesFonctionnaires) session.getAttribute("fonctionnaire");
		 formulaireConge form = new formulaireConge();
		 DonneesFonctionnaires[] remplacent = new DonneesFonctionnaires[100];
		 remplacent = form.remplacent(request);
		 request.setAttribute("remplacent", remplacent);
		 request.setAttribute("fonctionnaire", fonctionnaire);
		this.getServletContext().getRequestDispatcher("/WEB-INF/Conge.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		formulaireConge form = new formulaireConge();
		form.EnvoyerDemande(request);
		request.setAttribute("form", form);
		this.getServletContext().getRequestDispatcher("/WEB-INF/Conge.jsp").forward(request, response);
	}

}
