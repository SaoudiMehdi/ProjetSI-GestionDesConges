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
import javax.servlet.http.HttpSession;

import com.gestionconge.formulaires.DonneesDemande;

public class Demandes {
	
	/*champ erreurs*/
	
	private Map<String, String> erreurs  = new HashMap<String, String>();
	private Map<Integer,String> feries = new HashMap<Integer,String>();
	
    public void RemplirFeries() {
		feries.put(0,"1-1");
		feries.put(1,"11-1");
		feries.put(2,"1-5");
		feries.put(3,"30-7");
		feries.put(4,"20-8");
		feries.put(5,"21-8");
		feries.put(6,"6-11");
		feries.put(7,"18-11");
		feries.put(8, "14-8");
		
		Date value[] = new Date[4];
		String requette = "SELECT * FROM Feries;";
		SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connexion = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Conge;user=admin;password=Admin12345");
			PreparedStatement statement = connexion.prepareStatement(requette);
			
			/*execution de la requette*/
			ResultSet resultat = statement.executeQuery();
			
			/*remplissage de donnees*/

			while(resultat.next()) {
				value[0] = resultat.getDate("Fitr");
				value[1] = resultat.getDate("Adha");
				value[2] = resultat.getDate("Moharam");
				value[3] = resultat.getDate("Nabawi");
			}
			int j=9;
			for(int i=0; i<4;i++) {
				if(i==0||i==1) {
					c.setTime(value[i]);
					feries.put(j, c.get(Calendar.DAY_OF_MONTH)+"-"+(c.get(Calendar.MONTH)+1));
					System.out.println(j+": "+c.get(Calendar.DAY_OF_MONTH)+"-"+(c.get(Calendar.MONTH)+1));
					j++;
					feries.put(j, (c.get(Calendar.DAY_OF_MONTH)+1)+"-"+(c.get(Calendar.MONTH)+1));
					System.out.println(j+": "+(c.get(Calendar.DAY_OF_MONTH)+1)+"-"+(c.get(Calendar.MONTH)+1));
				}else {
					c.setTime(value[i]);
					feries.put(j, c.get(Calendar.DAY_OF_MONTH)+"-"+(c.get(Calendar.MONTH)+1));
				}
				j++;
			}
			statement.close();
			resultat.close();
			connexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	/*traitement de donnees*/
	
	public DonneesDemande[] Archive(){
		erreurs.clear();
		DonneesDemande demandes[] = new DonneesDemande[100];
		/*procedures d'acces à la BD*/
		
		String requette = "SELECT * FROM congé where dateFinal between ? and ? order by Id_Congé DESC;";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connexion = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Conge;user=admin;password=Admin12345");
			PreparedStatement statement = connexion.prepareStatement(requette);
			
			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date dateAujourdhui = new Date();
			c.setTime(dateAujourdhui);
	        Date date1 = sdf.parse("01-05-"+c.get(Calendar.YEAR));
	        if(dateAujourdhui.compareTo(date1)>0) {
	        	statement.setString(1, sdf.format(date1));
	        	date1 = sdf.parse("01-05-"+(c.get(Calendar.YEAR)+1));
				statement.setString(2, sdf.format(date1));
	        }else {
	        	statement.setString(2, sdf.format(date1));
	        	date1 = sdf.parse("01-05-"+(c.get(Calendar.YEAR)-1));
				statement.setString(1, sdf.format(date1));
	        }

			
			/*execution de la requette*/
			ResultSet resultat = statement.executeQuery();
			
			/*remplissage de donnees*/
			int i=0;
			while(resultat.next()) {
				demandes[i] = new DonneesDemande(resultat.getInt("Matricule"),resultat.getString("nom"),resultat.getString("grade"),resultat.getString("direction"),resultat.getInt("typecongé"),resultat.getDate("datedemande"),resultat.getString("remplacent"),resultat.getInt("durée"));
				i++;
			}
			System.out.println(i);
			statement.close();
			resultat.close();
			connexion.close();
		} catch (ClassNotFoundException | SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return demandes;
	}
	public DonneesDemande[] MonArchive(HttpServletRequest request){
		erreurs.clear();
		DonneesDemande demandes[] = new DonneesDemande[100];
		/*procedures d'acces à la BD*/
		 HttpSession session = request.getSession();
	     DonneesFonctionnaires fonctionnaire = (DonneesFonctionnaires) session.getAttribute("fonctionnaire");
		String requette = "SELECT * FROM CONGE where numSomme = ? order by numConge DESC;";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connexion = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Conge;user=admin;password=Admin12345");
			PreparedStatement statement = connexion.prepareStatement(requette);
			
			
			statement.setInt(1, fonctionnaire.getMatricule());
			/*execution de la requette*/
			ResultSet resultat = statement.executeQuery();
			
			/*remplissage de donnees*/
			int i=0;
			while(resultat.next()) {
				demandes[i] = new DonneesDemande(resultat.getInt("Matricule"),resultat.getString("nom"),resultat.getString("grade"),resultat.getString("direction"),resultat.getInt("typecongé"),resultat.getDate("datedemande"),resultat.getString("remplacent"),resultat.getInt("durée"));
				i++;
			}
			statement.close();
			resultat.close();
			connexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return demandes;
	}
	
	private int DureeRestante(int int1) {
		int dureeRestante=0;
		String requette = "SELECT dureeRestante FROM Fonctionnaire where numSomme = ?;";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connexion = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Conge;user=admin;password=Admin12345");
			PreparedStatement statement = connexion.prepareStatement(requette);
			
			
			statement.setInt(1, int1);
			/*execution de la requette*/
			ResultSet resultat = statement.executeQuery();
			
			/*remplissage de donnees*/
			while(resultat.next()) {
				dureeRestante = resultat.getInt("dureeRestante");
			}
			statement.close();
			resultat.close();
			connexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dureeRestante;
	}
	/*traitement de donnees*/
	
	public DonneesDemande[] Chercher(){
		erreurs.clear();
		DonneesDemande demandes[] = new DonneesDemande[100];
		/*procedures d'acces à la BD*/
		
		String requette = "SELECT * FROM DEMANDESCONGE order by numDemande DESC;";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connexion = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Conge;user=admin;password=Admin12345");
			Statement statement = connexion.createStatement();
			
			/*execution de la requette*/
			ResultSet resultat = statement.executeQuery(requette);
			
			/*remplissage de donnees*/
			int i=0;
			while(resultat.next()) {
				demandes[i] = new DonneesDemande(resultat.getInt("Matricule"),resultat.getString("nom"),resultat.getString("grade"),resultat.getString("direction"),resultat.getInt("typecongé"),resultat.getDate("datedemande"),resultat.getString("remplacent"),resultat.getInt("durée"));
				i++;
			}
			statement.close();
			resultat.close();
			connexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return demandes;
	}
	
	public void Valider(HttpServletRequest request){

		String requette = "INSERT INTO CONGE(NUMSOMME,NOM,DEPARTEMENT,DUREE,DATEDEMANDE,DATEPROPOSE,DATEFINAL,DATERETOUR,REMPLACENT) values(?,?,?,?,?,?,?,?,?);";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connexion = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Conge;user=admin;password=Admin12345");
			
			PreparedStatement statement = connexion.prepareStatement(requette);
			
			/*remplissage de la requette*/
			statement.setInt(1,Integer.parseInt(getValeurChamp(request,"numSomme")));
			statement.setString(2,getValeurChamp(request,"nom"));
			statement.setString(3,getValeurChamp(request,"departement"));
			statement.setInt(4,Integer.parseInt(getValeurChamp(request,"duree")));
			statement.setString(5,getValeurChamp(request,"dateDemande"));
			statement.setString(6,getValeurChamp(request,"datePropose"));
			statement.setString(7,getValeurChamp(request,"dateFinal"));
			statement.setString(8,DateRetour(getValeurChamp(request,"dateFinal"),Integer.parseInt(getValeurChamp(request,"duree"))));
			statement.setString(9,getValeurChamp(request,"remplacent"));
			
			statement.executeUpdate();
			
			statement.close();
			connexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SupprimerDemande(request);
	}
	public String DateRetour(String dateDebutC,int dureeC) {
		SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatter3=new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat simpleDateFormat= new SimpleDateFormat("EEEE");
		String day = null;
		String dt = null;
		Date dateRetour=null;
		int duree = dureeC;
		int clock=0;
		RemplirFeries();
		try {
			Date dateDebut = formatter2.parse(dateDebutC);
			dateRetour = dateDebut;
			Calendar c = Calendar.getInstance();
			day = simpleDateFormat.format(dateRetour);
			while(duree!=0 || clock==1) {
			c.setTime(dateRetour);
			dt = c.get(Calendar.DAY_OF_MONTH)+"-"+(c.get(Calendar.MONTH)+1);
			if(day.equals("samedi")|| day.equals("dimanche")||feries.containsValue(dt)) {
				c.add(Calendar.DAY_OF_MONTH, 1); 
				dateRetour = c.getTime();
			}else{
				if(clock==1) {
					break;
				}
				c.add(Calendar.DAY_OF_MONTH, 1); 
				dateRetour = c.getTime();
				duree--;
			}
			day = simpleDateFormat.format(dateRetour);
			if(duree==0 && day.equals("samedi")) {
				clock=1;
			}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return formatter3.format(dateRetour);
	}
	public void SupprimerDemande(HttpServletRequest request){

		String requette = "DELETE FROM DEMANDESCONGE WHERE NUMSOMME=? and DATEDEMANDE=?;";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connexion = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Conge;user=admin;password=Admin12345");
			
			PreparedStatement statement = connexion.prepareStatement(requette);
			
			/*remplissage de la requette*/
			statement.setInt(1,Integer.parseInt(getValeurChamp(request,"numSomme")));
			statement.setString(2,getValeurChamp(request,"dateDemande"));
			
			statement.executeUpdate();
			
			statement.close();
			connexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void UpdateDateRetour(String dateDebutC,int dureeC){

		String requette = "UPDATE CONGE set dateRetour = ? where dateFinal >= ?;";
		Calendar c = Calendar.getInstance();
		Date dt = new Date();
		c.setTime(dt);
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connexion = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Conge;user=admin;password=Admin12345");
			
			PreparedStatement statement = connexion.prepareStatement(requette);
			
			/*remplissage de la requette*/
			statement.setString(1, DateRetour(dateDebutC,dureeC));
			statement.setString(2, "1-1-"+c.get(Calendar.YEAR));
			
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
	
	/*fonction pour stocker les entrees*/
	
	public static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
	        String valeur = request.getParameter( nomChamp );
	        if ( valeur == null) {
	            return null;
	        } else {
	            return valeur;
	        }
	    }

	/*getters et setters*/
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public void setErreurs(Map<String, String> erreurs) {
		this.erreurs = erreurs;
	}
}
