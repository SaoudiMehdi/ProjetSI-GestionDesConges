package com.gestionconge.formulaires;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

public class FormulaireFeries {
	
	
	public DonneesFeries ChercherFeries() {
		DonneesFeries aid=null;
		String requette = "SELECT * FROM Feries;";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connexion = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Conge;user=admin;password=Admin12345");
			Statement statement = connexion.createStatement();
			
			/*execution de la requette*/
			ResultSet resultat = statement.executeQuery(requette);
			
			/*remplissage de donnees*/

			while(resultat.next()) {
				aid = new DonneesFeries(resultat.getDate("Fitr"),resultat.getDate("Adha"),resultat.getDate("Moharam"),resultat.getDate("Nabawi"));
			}
			statement.close();
			resultat.close();
			connexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return aid;
	}
	
	public void UpdateFeries(HttpServletRequest request) {
		String requette = "Update Feries set Fitr = ? , Adha=? , Moharam = ? , Nabawi = ?;";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connexion = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Conge;user=admin;password=Admin12345");
			PreparedStatement statement = connexion.prepareStatement(requette);
			
			statement.setString(1,getValeurChamp(request,"Fitr"));
			statement.setString(2,getValeurChamp(request,"Adha"));
			statement.setString(3,getValeurChamp(request,"Moharam"));
			statement.setString(4,getValeurChamp(request,"Nabawi"));
			
			
			/*execution de la requette*/
			statement.executeUpdate();
			
			statement.close();
			connexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
