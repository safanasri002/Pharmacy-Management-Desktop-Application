package Pharmacie;

public class ClientFidele {
		private int cin;
		private String nom, prenom;
		private double seuil_credit;
		
		/*public ClientFidele(int idclient, String nom, String prenom) {
			//super();
			this.cin = idclient;
			this.nom = nom;
			this.prenom = prenom ;
			}
		*/
		public ClientFidele(int idclient, String nom, String prenom,double seuil) {
			
			//this(idclient,nom, prenom);
			this.cin = idclient;
			this.nom = nom;
			this.prenom = prenom ;
			this.seuil_credit = seuil;
		}
		
		
		
		public double getSeuil_credit() {
			return seuil_credit;
		}



		public void setCin(int cin) {
			this.cin = cin;
		}



		public void setSeuil_credit(double seuil_credit) {
			this.seuil_credit = seuil_credit;
		}



		@Override
		public String toString() {
			return String.format("ClientFidele {CIN: %d, Nom: %s, Prénom: %s}", cin, nom, prenom);
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

		public int getCin() { return cin; }
	    public double getCredit() { return seuil_credit; }
	    public void setCredit(double credit) { this.seuil_credit = credit; }
}
