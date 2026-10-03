package Pharmacie;
import java.time.LocalDate;

public abstract class Medicament implements Vendable{
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////// Attributs ///////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	String categorie ; 
	long code;
	public String nom,type;
	double prix;
	static long nombre=0;
	Long numserie;
	protected LocalDate dateExpiration;	
	int quantite;
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////// Constructeur ////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	/*public Medicament() {
        nombre++;
        this.code = nombre;
    }
	
	public Medicament(String categorie, String nom, String type,double prix, Long numserie,LocalDate dateExpiration, int quantite) {
		nombre++;
		this.code = nombre;
		this.categorie = categorie;
		this.nom = nom;
		this.type = type;
		this.prix = prix;
		this.numserie = numserie;
		this.dateExpiration = dateExpiration;
		this.quantite = quantite;
		}
	*/
	
	public Medicament(Long code,String categorie, String nom, String type,double prix, long numserie,LocalDate dateExpiration, int quantite) {
			this.code=code;
			this.categorie = categorie;
			this.nom = nom;
			this.type = type;
			this.prix = prix;
			this.numserie = numserie;
			this.dateExpiration = dateExpiration;
			this.quantite = quantite;
		}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////Getters & Setters ////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public LocalDate getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
    }
	
	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	@Override
	public String toString() {
		return ("nom :"+ nom +" type :"+ type +" code :"+ code+"prix : "+prix);
	}
	
	public int compare_nom (Medicament m) {
		return this.nom.compareTo(m.nom);
		}
	
	public static int compare_nom (Medicament m1,Medicament m2) {
		return m1.nom.compareTo(m2.nom);
		}
	
	
	public Long getNumserie() {
		return numserie;
	}

	public void setNumserie(Long numserie) {
		this.numserie = numserie;
	}

	public double compare_prix (Medicament m) {
		double r= this.prix-m.prix;
		if (r<0)
			return (-1);
		else if (r>0)
			return (1);
		else 
			return (0);
		}
	
	public static int compare_prix (Medicament m1,Medicament m2) {
		return (int) (m1.prix-m2.prix);
		}
	
	public double compareprix(Medicament m){
		return (Double.compare(prix,m.prix));
	}

	public String getCategorie() {
		return categorie;
	}

	public long getCode() {
		return code;
	}

	public String getNom() {
		return nom;
	}

	public String getType() {
		return type;
	}

	public static long getNombre() {
		return nombre;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static void setNombre(long nombre) {
		Medicament.nombre = nombre;
	}
	
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getQuantite() {
		return quantite;
	}
	
}
