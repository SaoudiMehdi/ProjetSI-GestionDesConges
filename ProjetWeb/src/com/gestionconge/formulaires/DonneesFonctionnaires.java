package com.gestionconge.formulaires;

public class DonneesFonctionnaires {
	
	private int matricule;
	private String cin;
	public String nom;
	private String prenom;
	private String adresse;
	private int telephone;
	private String email;
	private int dureeRestante;
	private String grade;
	private int numJuridiction;
	private int administrateur;
	private int etat;
	private String login;
	private String password;
	
	
	public DonneesFonctionnaires() {
		
	}
	public DonneesFonctionnaires(int numSomme,String cin,String nom,String prenom,String adresse,int telephone,int dureeRestante,String departement,int numJuridiction,int administrateur,int etat)
	{
		this.setMatricule(numSomme);
		this.setCin(cin);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setAdresse(adresse);
		this.setTelephone(telephone);
		this.setDureeRestante(dureeRestante);
		this.setGrade(departement);
		this.setNumJuridiction(numJuridiction);
		this.setAdministrateur(administrateur);
		this.setEtat(etat);
	}
	public DonneesFonctionnaires(int numSomme,String cin,String nom,String prenom,String adresse,int telephone,String email,int dureeRestante,String departement,int numJuridiction,int administrateur,int etat,String login,String password)
	{
		this.setMatricule(numSomme);
		this.setCin(cin);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setAdresse(adresse);
		this.setTelephone(telephone);
		this.setEmail(email);
		this.setDureeRestante(dureeRestante);
		this.setGrade(departement);
		this.setNumJuridiction(numJuridiction);
		this.setAdministrateur(administrateur);
		this.setEtat(etat);
		this.setLogin(login);
		this.setPassword(password);
	}
	
	
	
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public int getNumJuridiction() {
		return numJuridiction;
	}
	public void setNumJuridiction(int numJuridiction) {
		this.numJuridiction = numJuridiction;
	}
	public int getAdministrateur() {
		return administrateur;
	}
	public void setAdministrateur(int administrateur) {
		this.administrateur = administrateur;
	}
	public int getDureeRestante() {
		return dureeRestante;
	}
	public void setDureeRestante(int dureeRestante) {
		this.dureeRestante = dureeRestante;
	}
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) {
		this.etat = etat;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getMatricule() {
		return matricule;
	}
	public void setMatricule(int matricule) {
		this.matricule = matricule;
	}
	

	
	
	
}
