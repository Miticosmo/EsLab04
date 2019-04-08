package it.polito.tdp.lab04.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		List<Corso> corsi = model.getCorsi();
		
		for(Corso c : corsi)
		{
			System.out.println(c);
		}

	}

}
