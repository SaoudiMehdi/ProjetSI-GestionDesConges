package com.gestionconge.formulaires;

import java.util.Date;

public class DonneesFeries {
	
	private Date fitr;
	private Date adha;
	private Date moharam;
	private Date nabawi;
	
	public DonneesFeries(Date fitr,Date adha,Date moharam,Date nabawi) {
		this.setFitr(fitr);
		this.setAdha(adha);
		this.setMoharam(moharam);
		this.setNabawi(nabawi);
	}
	public DonneesFeries() {
		
	}
	public Date getFitr() {
		return fitr;
	}
	public void setFitr(Date fitr) {
		this.fitr = fitr;
	}
	public Date getAdha() {
		return adha;
	}
	public void setAdha(Date adha) {
		this.adha = adha;
	}
	public Date getMoharam() {
		return moharam;
	}
	public void setMoharam(Date moharam) {
		this.moharam = moharam;
	}
	public Date getNabawi() {
		return nabawi;
	}
	public void setNabawi(Date nabawi) {
		this.nabawi = nabawi;
	}
	
	
	
	
	
	
}
