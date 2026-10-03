package Pharmacie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class AchatVendable {
	/* PreparedStatement pour éviter les injections SQL.
	La méthode setInt(1, codemed) permet de remplacer le premier  
	paramètre de la requête par la valeur du code du médicament */
	
	public void achatMedicament (int idclient, long idmed, double prix,int quantite) {
		String sqlachat="insert into achat (date_achat, montant, id_client, id_medicament) values (?,?,?,?)";
		String sqlquantite="update medicament set quantite=quantite-? where code=? and quantite>0";
	    String sqlqtit = "select quantite from medicament where code = ?";

		try(Connection cn= ConnexionDB.getConnection();){
		
			PreparedStatement psverif = cn.prepareStatement(sqlqtit);
			//psverif.setInt(1,idmed);
			psverif.setLong(1,idmed);
			
			ResultSet rs=psverif.executeQuery();
			
			if(rs.next()) {
				int stockdispo= rs.getInt("quantite");
				if (stockdispo<quantite) {
					 System.out.println("❌ Stock insuffisant !");
				}
				else {
					
				//inserer le medicament acheté dans la table achat
				PreparedStatement psajout=cn.prepareStatement (sqlachat);
				
				psajout.setObject(1, LocalDate.now());
				psajout.setDouble(2, prix*0.8);
				psajout.setInt(3,idclient);
				
				psajout.setLong(4,idmed);
				psajout.executeUpdate();
				
				//mis a jour de la quantite 
				PreparedStatement psupdate=cn.prepareStatement(sqlquantite);
				
				psupdate.setInt(1,quantite);
				psupdate.setLong(2,idmed);
				psupdate.executeUpdate();
				System.out.println("✅ Achat effectué avec succès !");
				}
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void achatAppareilMedical (int idclient, int idapp, double prix,int quantite) {
		String sqlachat="insert into achat (date_achat, montant, id_client, id_appareil_medical) values (?,?,?,?)";
		String sqlquantite="update medicament set quantite=quantite-? where code=? and quantite>0";
	    String sqlqtit = "select quantite from medicament where code = ?";

		try(Connection cn= ConnexionDB.getConnection();){
		
			PreparedStatement psverif = cn.prepareStatement(sqlqtit);
			
			psverif.setInt(1,idapp);
			ResultSet rs=psverif.executeQuery();
			if(rs.next()) {
				int stockdispo=rs.getInt("quantite");
				if (stockdispo<quantite) {
					 System.out.println("❌ Stock insuffisant !");
				}
				else {
				PreparedStatement psajout=cn.prepareStatement (sqlachat);
				psajout.setObject(1, LocalDate.now());
				psajout.setDouble(2, prix);
				psajout.setInt(3,idclient);
				psajout.setInt(4,idapp);
				psajout.executeUpdate();
				
				PreparedStatement psupdate=cn.prepareStatement(sqlquantite);
				psupdate.setInt(1,quantite);
				psupdate.setInt(2,idapp);
				psupdate.executeUpdate();
				System.out.println("✅ Achat effectué avec succès !");
				}
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
