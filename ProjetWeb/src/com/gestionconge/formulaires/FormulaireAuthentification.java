package com.gestionconge.formulaires;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class FormulaireAuthentification {
	

	
	/*champ erreurs*/
	private Map<String, String> erreurs = new HashMap<String, String>();
	
	
	public DonneesFonctionnaires Authentification(HttpServletRequest request) {
		DonneesFonctionnaires fonctionnaire = new DonneesFonctionnaires();
		
		String requette = "SELECT * FROM FONCTIONNAIRE f,AUTHENTIFICATION a WHERE f.login=a.login and a.login=? and a.password=? and numJuridiction=?;";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connexion = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Conge;user=admin;password=Admin12345");

			PreparedStatement statement = connexion.prepareStatement(requette);
			
			/*remplissage de la requette*/
			statement.setString(1,(String) getValeurChamp(request,"inputEmail"));
			statement.setString(2,(String) getValeurChamp(request,"inputPassword"));
			statement.setInt(3, 1);
			
			/*execution de la requette*/
			ResultSet resultat = statement.executeQuery();
			
			/*remplissage de donnees*/
			int i=0;
			while(resultat.next()) {
				fonctionnaire.setMatricule((int) resultat.getInt("numSomme"));
				fonctionnaire.setCin((String) resultat.getString("cin"));
				fonctionnaire.setNom((String) resultat.getString("nom"));
				fonctionnaire.setPrenom((String) resultat.getString("prenom"));
				fonctionnaire.setTelephone((int) resultat.getInt("telephone"));
				fonctionnaire.setDureeRestante((int) resultat.getInt("dureeRestante"));
				fonctionnaire.setGrade((String) resultat.getString("departement"));
				fonctionnaire.setAdministrateur((int) resultat.getInt("administrateur"));
				fonctionnaire.setEtat((int)resultat.getInt("etat"));
				fonctionnaire.setLogin((String) resultat.getString("login"));
				i++;
			}
			if(i==0) {
				setErreur("login","إسم المستخدم أو كلمة المرور غير صحيحة");
			}else {
				checkDuree();
			}
			statement.close();
			resultat.close();
			connexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fonctionnaire;
	}
	
	
	
	
	private void checkDuree() {
	      try {
	    	  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			  Connection connexion = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Conge;user=admin;password=Admin12345");
			  String requette = "update fonctionnaire set dureeRestante= 22;";
			  Statement statement = connexion.createStatement();
	    	  Calendar c = Calendar.getInstance();
	    	  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    	  Date dateAujourdhui = new Date();
	    	  c.setTime(dateAujourdhui);
	    	  Date date1 = sdf.parse("01-05-"+c.get(Calendar.YEAR));
	    	  if(date1.compareTo(dateAujourdhui)==0) {
	    		  statement.executeQuery(requette);
	    	  }
	    	  statement.close();
			  connexion.close();
		} catch (ParseException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}




	/*fonction d'erreurs*/
	
	private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }
	/*getters erreur*/
	
	public Map<String, String> getErreurs() {
		return erreurs;
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
