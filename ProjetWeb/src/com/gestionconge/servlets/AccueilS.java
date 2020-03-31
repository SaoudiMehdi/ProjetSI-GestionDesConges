package com.gestionconge.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gestionconge.formulaires.DonneesFonctionnaires;
import com.gestionconge.formulaires.FormulaireAuthentification;
import com.gestionconge.formulaires.formulaireConge;

/**
 * Servlet implementation class formulaireAuthentificationS
 */
@WebServlet("/AccueilS")
public class AccueilS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccueilS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int con = 0;
		if(request.getParameter("con")!=null) {
			con = Integer.parseInt(request.getParameter("con"));
		}
		if(con==0) {
			HttpSession session = request.getSession();
			session.setAttribute("fonctionnaire", null);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String path = "/WEB-INF/Accueil.jsp";
		DonneesFonctionnaires fonctionnaire = new DonneesFonctionnaires();
		DonneesFonctionnaires[] remplacent = new DonneesFonctionnaires[100];
		FormulaireAuthentification form = new FormulaireAuthentification();
		formulaireConge formC = new formulaireConge();
		fonctionnaire = form.Authentification(request);
		/* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        /*si l'utilisateur saisi les champs correctement on l'ajoute a la session*/
        if ( form.getErreurs().isEmpty() ) {
            session.setAttribute( "fonctionnaire", fonctionnaire );
        	remplacent = formC.remplacent(request);
            session.setAttribute("admin",fonctionnaire.getAdministrateur());
    		request.setAttribute("fonctionnaire",fonctionnaire);
    		for(int i=0;i<25;i++) {
    		request.setAttribute("remplacent", remplacent);
    		}
            path = "/WEB-INF/Conge.jsp";
        } else {
            session.setAttribute( "fonctionnaire", null );
            
        }
        request.setAttribute("form", form);
		this.getServletContext().getRequestDispatcher(path).forward(request, response);
	}

}
