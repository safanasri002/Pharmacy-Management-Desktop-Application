package Pharmacie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*PreparedStatement précompile les requêtes SQL,
  permettant de réutiliser le même plan d'exécution avec différents paramètres
  (gain de performance) et protège contre les injections SQL grâce aux paramètres (?)*/

public class GestionClient {

	public boolean ajouterClient(ClientFidele cl) {
		String sql ="insert into client (id_client,nom,prenom,solde_fidelite) values(?,?,?,?)";
		try (Connection cn=ConnexionDB.getConnection();
			 PreparedStatement ps= cn.prepareStatement(sql)){

			ps.setInt(1,cl.getCin());
			ps.setString(2, cl.getNom());
			ps.setString(3,cl.getPrenom());
			ps.setDouble(4,cl.getCredit());
			ps.executeUpdate();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean supprimerClient(int id) {
		String sqlsupprimerclient ="delete from client where id_client=?";
		String sqlsupprimerachat ="delete from achat where id_client=?";
		try(Connection cn=ConnexionDB.getConnection();
			PreparedStatement psclient= cn.prepareStatement(sqlsupprimerclient);
			PreparedStatement psachat= cn.prepareStatement(sqlsupprimerachat)){

			psclient.setInt(1, id);
			psclient.execute();

			psachat.setInt(1, id);
			psachat.execute();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Object getAll() {
		return null;
	}

	public List<ClientFidele> getAllClient(){

		List<ClientFidele> list = new ArrayList<>();
	    String sql = "select * from client";
	    try(Connection cn = ConnexionDB.getConnection();
			Statement st= cn.createStatement();
	    	ResultSet rs=st.executeQuery(sql)){

	    	while(rs.next()) {
	    		ClientFidele c = null;
    			c = new ClientFidele(
				rs.getInt("id_client"),
			    rs.getString("nom"),
			    rs.getString("prenom"),
			    rs.getDouble("solde_fidelite")
    			);

    	    	list.add(c);
    			}
	    	}

	    catch(Exception e) {
	    	e.printStackTrace();
	    }

	    return list;
	}

	public ClientFidele rechercherClient(int cin) {
		return (getAllClient().stream().filter(c->c.getCin()==cin).findFirst().orElse(null));
	}

}
