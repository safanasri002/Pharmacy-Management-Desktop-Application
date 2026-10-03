/*package Pharmacie;

public interface Vendable {
	double getTranche();
}*/
package Pharmacie;

public interface Vendable {

    String getNom();
    double getPrix();
    void setPrix(double prix);

    double getTranche(); // pour paiement échelonné
}
