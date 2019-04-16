package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				corsi.add(new Corso(codins,numeroCrediti,nome,periodoDidattico));


			} 
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
		return corsi;

	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		
		List<Studente> iscritti = new ArrayList<Studente>();
		
		String codIns = corso.getCodins();
		
		final String sql = "select * from studente\n " + 
				"where matricola IN(select matricola from iscritticorsi.iscrizione\n " + 
				"where codins = ? )";
		
		try {

			Connection conn = ConnectDB.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, codIns);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
			
				iscritti.add(new Studente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			
		} catch (Exception e) {
			System.err.println("\"Errore di connessione al db\"");
			return null;
		}
		
		
		
		return iscritti;
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean iscriviStudenteACorso(Studente studente, Corso corso) {
		boolean iscritto = false ;
		final String sql = "INSERT INTO studente\n" + 
				" value ( ? , ? , ? ,'01KSUPG' )";
		Connection conn = ConnectDB.getConnection();
		
		try {
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, studente.getMatricola());
			st.setString(2, studente.getCognome().toUpperCase());
			st.setString(3, studente.getNome().toUpperCase());
			//st.setString(4, corso.getNome());
			
			int ris = st.executeUpdate(sql);
			
			if(ris == 1)
				iscritto = true;
			
			conn.close();
			
			System.out.println("Query eseguita coorettamente " + iscritto);
			
		} catch (SQLException e) {
			System.err.println("\"Errore di connessione al db\"");
			e.printStackTrace();
			return false;
		}
		
		return iscritto;
		
	}

	public boolean getStudenteIscrittoAcorsoDAO(int matricola, String corsoSel) {
		
		boolean isPresent = false;
		final String sql = "SELECT * FROM iscrizione\n" + 
				" where matricola = ? AND codins = \n" + 
				"(SELECT codins from corso\n" + 
				" where nome = ? )";
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, matricola);
			st.setString(2, corsoSel);
			ResultSet rs = st.executeQuery();

			if (rs.next()) 
			{
				isPresent = true ;
			} 
			
		} catch (Exception e) {
				System.err.println("\"Errore di connessione al db\"");
				return false;
		}
		
		return isPresent;
	
	}
		
	}