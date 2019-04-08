package it.polito.tdp.lab04.DAO;

import it.polito.tdp.lab04.model.Studente;

public class TestDB {

	public static void main(String[] args) {
		
		/*
		 * 	This is a main to check the DB connection
		 */
		
		CorsoDAO cdao = new CorsoDAO();
		cdao.getTuttiICorsi();
		
		StudenteDAO st = new StudenteDAO();
		Studente studente = st.getStudenteByMatr(1461016);
		System.out.println(studente);
		
		
		
		

	}

}
