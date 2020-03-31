package com.gestionconge.formulaires;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class formulaireConge {
	/*champ erreurs*/
	private Map<String, String> erreurs      = new HashMap<String, String>();
	
	/*liste des jours feriées*/
	private ArrayList<String> Feriees = new ArrayList<String>();
	
	/*remplissage remplacent*/
	public DonneesFonctionnaires[] remplacent(HttpServletRequest request) {
		DonneesFonctionnaires[] remplacent = new DonneesFonctionnaires[100];
		/* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        DonneesFonctionnaires fonctionnaire = (DonneesFonctionnaires) session.getAttribute("fonctionnaire");
        String requette = "SELECT * FROM FONCTIONNAIRE f WHERE f.etat= 0 and f.numSomme <> ?;";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connexion = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Conge;user=admin;password=Admin12345");

			PreparedStatement statement = connexion.prepareStatement(requette);
			
			/*remplissage de la requette*/
			
			statement.setInt(1, fonctionnaire.getMatricule());
			
			/*execution de la requette*/
			ResultSet resultat = statement.executeQuery();
			
			/*remplissage de donnees*/
			int i=0;
			while(resultat.next()) {
				remplacent[i] = new DonneesFonctionnaires(resultat.getInt("numSomme"),resultat.getString("cin"),resultat.getString("nom"),resultat.getString("prenom"),resultat.getString("adresse"),resultat.getInt("telephone"),resultat.getInt("dureeRestante"),resultat.getString("departement"),resultat.getInt("numJuridiction"),resultat.getInt("administrateur"),resultat.getInt("etat"));
				i++;
			}
			statement.close();
			resultat.close();
			connexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return remplacent;
	}
	
	/*fonction inscription*/
	public  boolean EnvoyerDemande(HttpServletRequest request) {
		/* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        DonneesFonctionnaires fonctionnaire = (DonneesFonctionnaires) session.getAttribute("fonctionnaire");
        if(Integer.parseInt(getValeurChamp(request,"duree"))>fonctionnaire.getDureeRestante()) {
        	setErreur("duree","المدة المتبقية: "+fonctionnaire.getDureeRestante()+"يوم");
        	return false;
        }
        String requette = "INSERT INTO DEMANDESCONGE(numSomme,nom,departement,libelleType,duree,dateDemande,remplacent) values(?,?,?,?,?,?,?);";
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connexion = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Conge;user=admin;password=Admin12345");
			PreparedStatement statement = connexion.prepareStatement(requette);
			SimpleDateFormat formatter2=new SimpleDateFormat("dd-MM-yyyy");  
			/*remplissage de la requette*/
			statement.setInt(1, Integer.parseInt(getValeurChamp(request,"numSomme")));
			statement.setString(2,getValeurChamp(request,"nom"));
			statement.setString(3, getValeurChamp(request,"departement"));
			statement.setString(4,getValeurChamp(request,"libelleType"));
			statement.setInt(5, Integer.parseInt(getValeurChamp(request,"duree")));
			statement.setString(6, getValeurChamp(request,"dateDemande")); 
			statement.setString(7, getValeurChamp(request,"remplacent"));
			
			statement.executeUpdate();
			
			statement.close();
			connexion.close();
		} catch (ClassNotFoundException | SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        DureeRestante(request);
        return true;
	}
	
	
	
	
	/*fonction dureeRestante*/
	
	public void DureeRestante(HttpServletRequest request) {
		/* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        DonneesFonctionnaires fonctionnaire = (DonneesFonctionnaires) session.getAttribute("fonctionnaire");
     
        String requette = "Update Fonctionnaire set dureeRestante= dureeRestante- ? where numSomme=?;";
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connexion = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Conge;user=admin;password=Admin12345");
			PreparedStatement statement = connexion.prepareStatement(requette);
			/*remplissage de la requette*/
			statement.setInt(1, Integer.parseInt(getValeurChamp(request,"duree")));
			statement.setInt(2, fonctionnaire.getMatricule());
		
			
			statement.executeUpdate();
			
			statement.close();
			connexion.close();
		} catch (ClassNotFoundException | SQLException e) {
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
