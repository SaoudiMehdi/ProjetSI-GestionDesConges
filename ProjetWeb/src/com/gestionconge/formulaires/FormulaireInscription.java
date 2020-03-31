package com.gestionconge.formulaires;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public class FormulaireInscription {
	
	/*champ erreurs*/
	private Map<String, String> erreurs      = new HashMap<String, String>();
	
	/*fonction inscription*/
	public  boolean Inscription(HttpServletRequest request) {
		erreurs.clear();
		boolean valide = false;
		
		if(FonctionnaireExiste(request)==true)
			return false;
		
		String requette = "INSERT INTO Fonctionnaire(numSomme,cin,nom,prenom,adresse,telephone,dureeRestante,departement,numJuridiction,administrateur,etat,login,email) values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connexion = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Conge;user=admin;password=Admin12345");
			
			PreparedStatement statement = connexion.prepareStatement(requette);
			int administrateur=1;
			if(getValeurChamp(request,"administrateur")==null) administrateur=0;
			/*remplissage de la requette*/
			statement.setInt(1,Integer.parseInt(getValeurChamp(request,"numSomme")));
			statement.setString(2,(String) getValeurChamp(request,"cin"));
			statement.setString(3,(String) getValeurChamp(request,"nom"));
			statement.setString(4,(String) getValeurChamp(request,"prenom"));
			statement.setString(5,(String) getValeurChamp(request,"adresse"));
			statement.setInt(6,Integer.parseInt(getValeurChamp(request,"telephone")));
			statement.setInt(7,22);
			statement.setString(8,(String) getValeurChamp(request,"departement"));
			//statement.setInt(9,Integer.parseInt(getValeurChamp(request,"numJuridiction")));
			statement.setInt(9,1);
			statement.setInt(10,administrateur);
			statement.setInt(11,0);
			statement.setString(12,getValeurChamp(request,"login"));
			statement.setString(13,getValeurChamp(request,"email"));
			
			/*execution de la requette*/
			statement.executeUpdate();
			/*enregistrement du mot de pass*/
			requette = "INSERT INTO AUTHENTIFICATION(login,password) values(?,?);";
			statement = connexion.prepareStatement(requette);
			/*remplissage de la requette*/
			statement.setString(1,(String) getValeurChamp(request,"login"));
			statement.setString(2,(String) getValeurChamp(request,"password"));
			/*execution de la requette*/
			statement.executeUpdate();
			/*fermer la connexion*/
			statement.close();
			connexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

		}
		return !valide;
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
	/*Tester si l'utilisateur existe déja*/
	private boolean FonctionnaireExiste(HttpServletRequest request) {
		boolean existe = true;
		String requette = "SELECT* FROM Fonctionnaire WHERE numSomme =?;";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connexion = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Conge;user=admin;password=Admin12345");
			PreparedStatement statement = connexion.prepareStatement(requette);
			
			/*remplissage de la requette*/
			statement.setInt(1,Integer.parseInt(getValeurChamp(request,"numSomme")));
			
			
			/*execution de la requette*/
			ResultSet resultat = statement.executeQuery();
			
			int i=0;
			while(resultat.next()) {
				i++;
			}
			if(i==0)
				existe =false;
			else 
				setErreur("fonc","معلومات مستعملة");
			
			/*fermer la connexion*/
			statement.close();
			resultat.close();
			connexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return existe;
	}
	
	
	public DonneesFonctionnaires[] FonctionnairesArchive(){
		erreurs.clear();
		DonneesFonctionnaires fonctionnaire[] = new DonneesFonctionnaires[100];
		/*procedures d'acces à la BD*/
		
		String requette = "SELECT * FROM Fonctionnaire f,Authentification a where f.login = a.login;";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connexion = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Conge;user=admin;password=Admin12345");
			Statement statement = connexion.createStatement();
			
			/*execution de la requette*/
			ResultSet resultat = statement.executeQuery(requette);
			
			/*remplissage de donnees*/
			int i=0;
			while(resultat.next()) {
				fonctionnaire[i] = new DonneesFonctionnaires(resultat.getInt("numSomme"),resultat.getString("cin"),resultat.getString("nom"),resultat.getString("prenom"),resultat.getString("adresse"),resultat.getInt("telephone"),resultat.getString("email"),resultat.getInt("dureeRestante"),resultat.getString("departement"),resultat.getInt("numJuridiction"),resultat.getInt("administrateur"),resultat.getInt("etat"),resultat.getString("login"),resultat.getString("password"));
				i++;
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
	
	public DonneesFonctionnaires Profile(int numSomme){
		erreurs.clear();
		/*procedures d'acces à la BD*/
		DonneesFonctionnaires donnees = new DonneesFonctionnaires();
		String requette = "Select* from Fonctionnaire f,Authentification a where f.login = a.login and f.numSomme=?;";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connexion = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Conge;user=admin;password=Admin12345");
			PreparedStatement statement = connexion.prepareStatement(requette);
			
			statement.setInt(1, numSomme);

			/*execution de la requette*/
			ResultSet resultat = statement.executeQuery();
			while(resultat.next()) {
				donnees= new DonneesFonctionnaires(resultat.getInt("numSomme"),resultat.getString("cin"),resultat.getString("nom"),resultat.getString("prenom"),resultat.getString("adresse"),resultat.getInt("telephone"),resultat.getString("email"),resultat.getInt("dureeRestante"),resultat.getString("departement"),resultat.getInt("numJuridiction"),resultat.getInt("administrateur"),resultat.getInt("etat"),resultat.getString("login"),resultat.getString("password"));
			}
			
			
			statement.close();
			connexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return donnees;
	}
	public void SaveUpdate(HttpServletRequest request){
		erreurs.clear();
		/*procedures d'acces à la BD*/
		String requette = "Update Fonctionnaire set email= ?,telephone=? where numSomme = ? ;";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connexion = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Conge;user=admin;password=Admin12345");
			PreparedStatement statement = connexion.prepareStatement(requette);
			
			
			statement.setString(1,getValeurChamp(request,"email"));
			statement.setInt(2, Integer.parseInt(getValeurChamp(request,"telephone")));
			statement.setInt(3, Integer.parseInt(getValeurChamp(request,"numSomme")));
			/*execution de la requette*/
			statement.executeUpdate();
			
			
			statement.close();
			connexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		requette = "Update Authentification set password=? where login = ?;";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connexion = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Conge;user=admin;password=Admin12345");
			PreparedStatement statement = connexion.prepareStatement(requette);
			
			
			statement.setString(1,getValeurChamp(request,"password"));
			statement.setString(2,getValeurChamp(request,"login"));
			/*execution de la requette*/
			statement.executeUpdate();
			
			
			statement.close();
			connexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
