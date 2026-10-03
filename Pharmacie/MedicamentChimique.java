package Pharmacie;

import java.time.LocalDate;

public class MedicamentChimique extends Medicament implements Vendable{
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////// Attributs ///////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	String composanteChimique;
	int age;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////// Constructeur ////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	  public MedicamentChimique(Long code ,String categorie,String nom,String type,double prix,Long numserie,LocalDate dateExpiration,int quantite,String composanteChimique,int age) {
	        super(code ,categorie, nom, type, prix, numserie, dateExpiration, quantite);
	        this.composanteChimique = composanteChimique;
	        this.age = age;
	   }
	  
		public MedicamentChimique(Long code, String categorie,String nom,String type,double prix,Long numserie,LocalDate dateExpiration,int quantite) {
	        super(code,categorie, nom, type, prix, numserie, dateExpiration, quantite);
	   }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////// Getters & Setters ////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public String toString() {
		return ("nom : "+ nom +" numero de serie : "+ numserie+" categorie : "+ categorie +"age : "+age+"composante Chimique : "+composanteChimique);
	}

	@Override
	public double getTranche() {
		return (this.prix*0.8);
	}

	@Override
	public String getNom() {
		return nom;
	}
	
	
	  public String getComposanteChimique() {
		return composanteChimique;
	}

	public int getAge() {
		return age;
	}

	public void setComposanteChimique(String composanteChimique) {
		this.composanteChimique = composanteChimique;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	/*public MedicamentChimique(Long code,String nom,String categorie,String type,double prix,Long numserie,LocalDate dateExpiration) {
	    this.code = code;
	    this.nom = nom;
	    this.categorie = categorie;
	    this.type = type;
	    this.prix = prix;
	    this.numserie = numserie;
	    this.dateExpiration = dateExpiration;
	}

	public MedicamentChimique(String categorie, String nom, String type, double prix, long numserie,
			LocalDate date_expiration, int quantite) {
		super(categorie, nom, type, prix, numserie,date_expiration, quantite);
	}*/

}
