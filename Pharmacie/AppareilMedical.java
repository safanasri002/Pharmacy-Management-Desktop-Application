package Pharmacie;

import java.time.LocalDate;

public class AppareilMedical implements Vendable{
	private String nom;
	private double prix;
	protected LocalDate dateExpiration;
	int quantite;
	int id;
	public AppareilMedical(String nom ,double prix) {
		this.nom=nom;
		this.prix=prix;
	}
	public AppareilMedical(String nom ,double prix,LocalDate date) {
		this(nom ,prix);
		this.dateExpiration=date;
	}
	
	public AppareilMedical(int id,String nom ,double prix,LocalDate date, int quantite) {
		this(nom ,prix,date);
		this.quantite=quantite;
		this.id=id;
	}
	
	@Override
	public String toString () {
		return ("nom de l'appareil : "+nom+" prix : "+prix);
	}
	
	public String getNom() {
        return nom;
    }
	
	public double getPrix() {
		return prix;
	}
	
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	@Override
	public double getTranche() {
		return (prix/3);
	}
	
	
	public void setDateExpiration(LocalDate of) {
		this.dateExpiration=of;
	}

}
