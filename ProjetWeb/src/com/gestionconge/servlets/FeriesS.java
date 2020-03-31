package com.gestionconge.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestionconge.formulaires.Demandes;
import com.gestionconge.formulaires.DonneesFeries;
import com.gestionconge.formulaires.FormulaireFeries;

/**
 * Servlet implementation class FeriesS
 */
@WebServlet("/FeriesS")
public class FeriesS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeriesS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FormulaireFeries form = new FormulaireFeries();
		DonneesFeries aid = (DonneesFeries) form.ChercherFeries();
		request.setAttribute("aid", aid);
		this.getServletContext().getRequestDispatcher("/WEB-INF/Feries.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		FormulaireFeries form = new FormulaireFeries();
		Demandes form2 = new Demandes();
		form.UpdateFeries(request);
		UpdateDateR();
		doGet(request,response);
	}

	private void UpdateDateR() {
		Demandes form = new Demandes();
		String requette = "select dateFinal, duree from CONGE where dateFinal >= ?;";
		Calendar c = Calendar.getInstance();
		Date dt = new Date();
		c.setTime(dt);
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connexion = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Conge;user=TestingUser;password=12345");
			
			PreparedStatement statement = connexion.prepareStatement(requette);
			
			/*remplissage de la requette*/
			statement.setString(1, "1-1-"+c.get(Calendar.YEAR));
			ResultSet resultat = statement.executeQuery();
			
			while(resultat.next()) {
				form.UpdateDateRetour(resultat.getString("dateFinal"), resultat.getInt("duree"));
			}
			
			statement.close();
			connexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}

}
