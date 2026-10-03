package Pharmacie;

import java.time.LocalDate;
import java.util.List;

public class MainTest {

    public static void main(String[] args) {

        GestionMedicament gm = new GestionMedicament();
        AchatVendable achat = new AchatVendable();

        System.out.println("========== AJOUT MEDICAMENTS ==========");

        Medicament chimique = new MedicamentChimique(
                1L,
                "Antalgique",
                "Doliprane",
                "chimique",
                3.5,
                1L,
                LocalDate.of(2026, 5, 20),
                5,
                "Paracétamol",
                12 );
        
        Medicament chim = new MedicamentChimique(
                11L,
                "Antalgique",
                "Doliprane",
                "chimique",
                3.5,
                1L,
                LocalDate.of(2026, 5, 20),
                5,
                "Paracétamol",
                12 );
        

        Medicament homeo = new MedicamentHomeopathique(
                10L,
        		"Naturel",
                "Oscillococcinum",
                "homeopathique",
                6.5,
                10L,
                LocalDate.of(2025, 10, 15),
                3,
                "Plante A" );
        

        gm.ajouterMedicament(chimique);
        gm.ajouterMedicament(homeo);
        gm.ajouterMedicament(chim);
        
        System.out.println("\n========== LISTE DES MÉDICAMENTS ==========");
        List<Medicament> liste = gm.getAllMedicaments();
        for (Medicament m : liste) {
            System.out.println(m.getNom() + " - " + m.getPrix() + " DT");
        }

        System.out.println("\n========== RECHERCHE PAR NOM ==========");
        System.out.println("🔍 Recherche Doliprane :");
        gm.rechercherParNom("Doliprane").forEach(m -> System.out.println(" ===code : "+m.getCode()+" ===nom : "+m.getNom()+" === type : "+m.getType()+"==="+" === prix : "+"==="+m.getPrix()));

        System.out.println("\n========== RECHERCHE PAR CATÉGORIE ==========");
        gm.rechercherParCtegorie("Douleur")
        .forEach(m -> System.out.println(" ===code : "+m.getCode()+" ===nom : "+m.getNom()+" === type : "+m.getType()+"==="+" === prix : "+"==="+m.getPrix()));

        System.out.println("\n========== MÉDICAMENTS EXPIRANT BIENTÔT ==========");
        gm.medicamentExpirant()
        .forEach(m -> System.out.println(" ===code : "+m.getCode()+" ===nom : "+m.getNom()+" === type : "+m.getType()+"==="+" === Nouveau prix : "+"==="+m.getPrix()));
        
        System.out.println("\n========== ACHAT ==========");
        achat.achatMedicament(1, 1111, 3.5, 2);

        System.out.println("\n========== SUPPRESSION ==========");
        gm.supprimerMedicament(chim);

        System.out.println("\n========== FIN DU TEST ==========");
    }
}



/*package Pharmacie;

import java.time.LocalDate;

public class MainTest {

    private static final LocalDate LocalDate = null;

	public static void main(String[] args) {

        // Médicaments
		Medicament m1 = new MedicamentChimique(
                "antalgique",
                "Doliprane",
                "chimique",
                3.5,
                123L,
                LocalDate.of(2026, 5, 10),
                20,
                "Paracétamol",
                2
        );

		Medicament m2 = new MedicamentHomeopathique(
                "naturel",
                "Arnica",
                "homeopathique",
                5.0,
                222L,
                LocalDate.of(2026, 3, 1),
                15,
                "Arnica Montana"
        );
		
		GestionMedicament gm = new GestionMedicament();
		gm.ajouterMedicament(m1);
		gm.ajouterMedicament(m2);

        // Appareil
        AppareilMedical a1 = new AppareilMedical(1,"Tensiomètre",120,LocalDate.of(2028, 1, 1),1);
        // Client
        ClientFidele c1 = new ClientFidele(1, "Ali", "Ben Salah", 50);
        
        // Achat médicament
        AchatVendable achat1 = new AchatVendable();
        achat1.achatMedicament(c1.getCin(),m1.code,m1.prix,2);
        
        // Achat appareil
        AchatVendable achat2 = new AchatVendable();
        achat2.achatAppareilMedical(c1.getCin(),a1.id,m1.prix,1);
        
        //tester affichage
        System.out.println("📋 Liste des médicaments :");
        gm.getAllMedicaments().forEach(m ->System.out.println(m.getNom() + " - " + m.getPrix())
        );
        
        //recherche par nom
        System.out.println("🔍 Recherche Doliprane :");
        gm.rechercherParNom("Doliprane").forEach(m -> System.out.println(" ---code : "+m.getCode()+" --- type : "+m.getType()+"---"+" --- prix : "+"---"+m.getPrix()));

    }
}

*/

/*package Pharmacie;

import java.time.LocalDate;

public class MainTest {
    public static void main(String[] args) {

        // tester la connexion
        if (ConnexionDB.getConnection() != null)
            System.out.println("✅ Connexion etablie");
        else
            System.out.println("❌ Erreur connexion");

        // tester ajout médicament
        GestionMedicament gm = new GestionMedicament();

        Medicament m1 = new MedicamentChimique(
                11l,
                "Doliprane",
                "Antalgique",
                "chimique",
                5.5,
                12345L,
                LocalDate.of(2026, 3, 10)
        );

        Medicament m2 = new MedicamentHomeopathique(
                12l,
                "Arnica",
                "Naturel",
                "homeopathique",
                5.5,
                12345L,
                LocalDate.of(2026, 3, 10), null
        );

        
        gm.ajouterMedicament(m1);
        gm.ajouterMedicament(m2);
        //tester affichage
        System.out.println("📋 Liste des médicaments :");
        gm.getAllMedicaments().forEach(m ->
                System.out.println(m.getNom() + " - " + m.getPrix())
        );

        //recherche par nom
        System.out.println("🔍 Recherche Doliprane :");
        gm.rechercherParNom("Doliprane")
          .forEach(m -> System.out.println(m.getNom()));

        // medicaments expirant bientôt
        System.out.println("⏳ Médicaments expirants :");
        gm.medicamentExpirant()
          .forEach(m -> System.out.println(m.getNom() + " prix remisé = " + m.getPrix()));
    }
}

*/

/*package Pharmacie;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainTest extends Application {

    @Override
    public void start(Stage stage) {

        Button button = new Button("JavaFX 17 OK");

        button.setOnAction(e ->
            System.out.println("Button clicked!")
        );

        StackPane root = new StackPane(button);
        Scene scene = new Scene(root, 300, 200);

        stage.setTitle("Test JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
*/


