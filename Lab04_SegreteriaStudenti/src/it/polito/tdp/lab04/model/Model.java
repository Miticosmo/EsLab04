package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO cDAO = new CorsoDAO();
	private StudenteDAO sDAO = new StudenteDAO();
	
	public List<Corso> getCorsi()
	{
	
		return cDAO.getTuttiICorsi();
			
	}
	
	public Studente getStudente(int matricola)
	{
		return sDAO.getStudenteByMatr(matricola);
	}
	
	public List<Studente> cercaCorso(Corso c)
	{
		
		return cDAO.getStudentiIscrittiAlCorso(c);
		
	}
	
	
}
