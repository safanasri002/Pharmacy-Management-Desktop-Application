package Pharmacie;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class GestionMedicament {

	public boolean ajouterMedicament(Medicament m) {
		String sqlajouter ="insert into medicament (code, nom, categorie, type, prix, numserie, date_expiration, quantite) values (?, ?, ?, ?, ?, ?, ?, ?)";

		String sqlupdate="update medicament set quantite=quantite+? , date_expiration=? where code=?";//mettre a jour la quantite si le medicament existe deja

		String sqlverif="select quantite from medicament where code =?";//extraire le medicament avec le code existant

		String sqlchimique="insert into medicament_chimique (code,composante_chimique,age) values (?,?,?)";//ajouter nouveau med dans medicament chimique

		String sqlhomeopathique="insert into medicament_homeopathique (code,plante) values (?,?)";//ajouter nouveau med dans medicament homeopathique

		try(Connection cn=ConnexionDB.getConnection();

			PreparedStatement psupdate=cn.prepareStatement(sqlupdate);

			PreparedStatement psajouter=cn.prepareStatement(sqlajouter);

			PreparedStatement psverif=cn.prepareStatement(sqlverif);

			PreparedStatement psmedchimique=cn.prepareStatement(sqlchimique);

		    PreparedStatement psmedhomeopathique=cn.prepareStatement(sqlhomeopathique))

			{ psverif.setLong(1, m.getCode());

			ResultSet rs= psverif.executeQuery();

			if (rs.next()) { //verifier si le medicament existent deja

				psupdate.setInt(1, m.getQuantite());
				psupdate.setObject(2,m.getDateExpiration());
	            psupdate.setLong(3, m.getCode());
	            psupdate.executeUpdate();

	            System.out.println("DEBUG CODE = " + m.getCode());

	            System.out.println("✅ Médicament est mis à jour");
			}
			else  {
			psajouter.setLong(1, m.getCode());
			psajouter.setString(2, m.getNom());
			psajouter.setString(3, m.getCategorie());
			psajouter.setString(4, m.getType());
			psajouter.setDouble(5, m.getPrix());
			psajouter.setLong(6, m.getNumserie());
			psajouter.setObject(7,m.getDateExpiration());
			psajouter.setInt(8, m.getQuantite());
			psajouter.executeUpdate();
			System.out.println("DEBUG CODE = " + m.getCode());

				if (m instanceof MedicamentChimique mc) {
					psmedchimique.setLong(1,m.getCode());
					psmedchimique.setString(2, mc.getComposanteChimique());
					psmedchimique.setInt(3, mc.getAge());

					psmedchimique.executeUpdate();
					System.out.println("✅ Médicament chimique ajouté");
				}


				else if (m instanceof MedicamentHomeopathique mh) {
					psmedhomeopathique.setLong(1,mh.getCode());
					psmedhomeopathique.setString(2, mh.getPlante());

					psmedhomeopathique.executeUpdate();
					System.out.println("✅ Médicament homeopathique ajouté");
					}
				}
			return true;
		}

		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public boolean supprimerMedicament(Medicament m) {

		String sql="select type from medicament where code=?";
		String sqlmedicament="delete from medicament where code=?";
		String sqlchimique="delete from medicament_chimique where code=?";
		String sqlhomeopathique="delete from medicament_homeopathique where code=?";
		String sqlachat = "delete from achat where id_medicament = ?";

		try(Connection cn = ConnexionDB.getConnection();

			PreparedStatement ps= cn.prepareStatement(sql); //chercher medicament

			PreparedStatement psmedicament= cn.prepareStatement(sqlmedicament); //executée sur medicament

			PreparedStatement pschimique= cn.prepareStatement(sqlchimique); //executée sur medicament chimique

			PreparedStatement pshomeopathique= cn.prepareStatement(sqlhomeopathique); //executée sur medicament homeopathique

			PreparedStatement psachat= cn.prepareStatement(sqlachat)  ) //executée sur achat
		{
			ps.setLong(1, m.getCode());
			ResultSet rs=ps.executeQuery(); //on recupere le type

			if(rs.next()) {
				String typemed=rs.getString("type");
				if(typemed.equalsIgnoreCase("chimique")) {
					pschimique.setLong(1,m.getCode());
					pschimique.executeUpdate();
				}
				else {
					pshomeopathique.setLong(1, m.getCode());
					pshomeopathique.executeUpdate();
				}
			}

			psmedicament.setLong(1, m.getCode());
			psmedicament.executeUpdate();

			psachat.setLong(1,m.getCode());
			psachat.executeUpdate();
			return true;
		}

		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//pour recuperer toutes les donnees de BD on utilise ResultSet
	//et Statement au lien de PreparedStatement car on a une requete statique

	public List<Medicament> getAllMedicaments(){
		List<Medicament> list = new ArrayList<>();
	    String sql = "select * from medicament";
	    try(Connection cn = ConnexionDB.getConnection();
			Statement st= cn.createStatement();
	    	ResultSet rs=st.executeQuery(sql)){

	    	while(rs.next()) {
	    		Medicament med = null;//initialisation d'object Medicament
	    		String typemed=rs.getString("type");
	    		// tout type qui n'est pas "homeopathique" est lu comme chimique : aucune ligne n'est perdue
	    		if(typemed == null || !typemed.equalsIgnoreCase("homeopathique")) {
	    			/*med=new MedicamentChimique( rs.getString("nom"),rs.getString("type"),
	    				rs.getDouble("prix"),rs.getLong("numserie") ); */
	    			med = new MedicamentChimique(
	    					rs.getLong("code"),
	    				    rs.getString("categorie"),
	    				    rs.getString("nom"),
	    				    rs.getString("type"),
	    				    rs.getDouble("prix"),
	    				    rs.getLong("numserie"),
	    				    rs.getDate("date_expiration").toLocalDate(),
	    				    rs.getInt("quantite"),
	    				    //rs.getString("composante_chimique"), // ou autre
	    				    //rs.getInt("age")
	    				    "Inconnu",
	    				    0
	    				);


	    			}

	    		else {
	    			/*med=new MedicamentHomeopathique(rs.getString("nom"),rs.getString("type"),
		    				rs.getDouble("prix"),rs.getLong("numserie"));*/
	    			med = new MedicamentHomeopathique(
	    					rs.getLong("code"),
	    				    rs.getString("categorie"),
	    				    rs.getString("nom"),
	    				    rs.getString("type"),
	    				    rs.getDouble("prix"),
	    				    rs.getLong("numserie"),
	    				    rs.getDate("date_expiration").toLocalDate(),
	    				    rs.getInt("quantite"),
	    				    //rs.getString("plante") // ou autre
	    				    "Plante inconnue"
	    				    );

	    		}
	    		list.add(med);
	    	}

	    }
	    catch(Exception e) {
	    	e.printStackTrace();
	    }

	    return list;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	public Medicament rechercherMedicament(long code) {
	    for (Medicament m : getAllMedicaments()) {
	        if (m.getCode() == code) {
	            return m;
	        }
	    }
	    throw new RuntimeException("Médicament introuvable");
	}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<Medicament> rechercherParNom(String nom){
		return (getAllMedicaments().stream().filter(m -> m.getNom() != null &&
                m.getNom().equalsIgnoreCase(nom)).toList());

	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<Medicament> rechercherParCtegorie(String cat){
		return (getAllMedicaments().stream().filter(m->m.getCategorie().equalsIgnoreCase(cat)).toList());
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<Medicament> rechercherParDebut(String debut) {
	    return getAllMedicaments().stream().filter(m -> m.getNom().startsWith(debut)).toList();
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	public List<Medicament> medicamentExpirant(){
		return (getAllMedicaments().stream()
				.filter(m -> m.getDateExpiration() != null && m.getDateExpiration() .isBefore(LocalDate.now().plusMonths(1) ))
				.peek(m->m.setPrix(m.getPrix()*0.7))
				.toList());
	}

}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
