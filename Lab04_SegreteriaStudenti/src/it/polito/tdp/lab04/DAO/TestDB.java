package it.polito.tdp.lab04.DAO;

import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class TestDB {

	public static void main(String[] args) {
		
		/*
		 * 	This is a main to check the DB connection
		 */
		
		CorsoDAO cdao = new CorsoDAO();
		Corso corso = new Corso("02CIXPG", 8, "Tecnihce di programmazione"	, 2);
		
		List<Studente> studenti = cdao.getStudentiIscrittiAlCorso(corso);
		if(studenti.isEmpty())
		{
			System.out.println("La lista è vuota!");
		}
		for(Studente s : studenti)
		{
			System.out.println(s.toString());
		}
		
		
		
		
		

	}

}
