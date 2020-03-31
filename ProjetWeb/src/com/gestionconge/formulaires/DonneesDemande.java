package com.gestionconge.formulaires;

import java.util.Date;

public class DonneesDemande {
	
	private int matricule;
	private String nom;
	private String prenom;
	private String Grade;
	private String direction;
	private int typeCongé;
	private Date dateDemande;
	private String remplacent;
	private int dureedemandée;
	
	
	
	public DonneesDemande(int matricule,String nom,String departement,String direction,int typeconge,Date dateDemande,String remplacent,int duree) {
		this.setMatricule(matricule);
		this.setNom(nom);
		//this.setPrenom(prenom);
		this.setGrade(departement);
		this.setDirection(direction);
		this.setTypeCongé(typeconge);
		this.setDateDemande(dateDemande);
		this.setRemplacent(remplacent);
		this.setDureedemandée(duree);
	}
	/*public DonneesDemande(int numSomme,String nom,String departement,String libelleType,int duree,Date dateDemande,Date datePropose,Date dateFinal,String remplacent) {
		this.setNumSomme(numSomme);
		this.setNom(nom);
		this.setDepartement(departement);
		this.setLibelleType(libelleType);
		this.setDuree(duree);
		this.setDateDemande(dateDemande);
		this.setDatePropose(datePropose);
		this.setDateFinal(dateFinal);
		this.setRemplacent(remplacent);

	}
	public DonneesDemande(int numSomme,String nom,String departement,int duree,Date dateDemande,Date datePropose,Date dateFinal,Date dateRetour,String remplacent,int dureeRestante) {
		this.setNumSomme(numSomme);
		this.setNom(nom);
		this.setDepartement(departement);
		this.setDuree(duree);
		this.setDateDemande(dateDemande);
		this.setDatePropose(datePropose);
		this.setDateFinal(dateFinal);
		this.setDateRetour(dateRetour);
		this.setRemplacent(remplacent);
		this.setDureeRestante(dureeRestante);
	}
	public DonneesDemande(int numSomme,String nom,String departement,int duree,Date dateDemande,Date datePropose,Date dateFinal,Date dateRetour,String remplacent) {
		this.setNumSomme(numSomme);
		this.setNom(nom);
		this.setDepartement(departement);
		this.setDuree(duree);
		this.setDateDemande(dateDemande);
		this.setDatePropose(datePropose);
		this.setDateFinal(dateFinal);
		this.setDateRetour(dateRetour);
		this.setRemplacent(remplacent);

	}*/
	public Date getDateDemande() {
		return dateDemande;
	}
	public void setDateDemande(Date dateDemande) {
		this.dateDemande = dateDemande;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getRemplacent() {
		return remplacent;
	}
	public void setRemplacent(String remplacent) {
		this.remplacent = remplacent;
	}
	public int getMatricule() {
		return matricule;
	}
	public void setMatricule(int matricule) {
		this.matricule = matricule;
	}
	public String getGrade() {
		return Grade;
	}
	public void setGrade(String grade) {
		Grade = grade;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public int getTypeCongé() {
		return typeCongé;
	}
	public void setTypeCongé(int typeCongé) {
		this.typeCongé = typeCongé;
	}
	public int getDureedemandée() {
		return dureedemandée;
	}
	public void setDureedemandée(int dureedemandée) {
		this.dureedemandée = dureedemandée;
	}
	
	
}
