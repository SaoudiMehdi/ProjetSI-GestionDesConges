package com.gestionconge.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestionconge.formulaires.Demandes;
import com.gestionconge.formulaires.DonneesDemande;

/**
 * Servlet implementation class EtatS
 */
@WebServlet("/EtatS")
public class EtatS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EtatS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DonneesDemande[] donnees = new DonneesDemande[100];
		Demandes form = new Demandes();
		donnees = form.MonArchive(request);
		request.setAttribute("donnees", donnees);
		this.getServletContext().getRequestDispatcher("/WEB-INF/Etat.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
