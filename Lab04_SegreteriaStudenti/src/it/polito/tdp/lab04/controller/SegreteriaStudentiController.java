package it.polito.tdp.lab04.controller;

/**
 * Sample Skeleton for 'SegreteriaStudenti.fxml' Controller Class
 */
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {

	private Model model;
	private List<Corso> corsi;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="menuTendCorsi"
	private ComboBox<String> menuTendCorsi; // Value injected by FXMLLoader

	@FXML // fx:id="txtMatricola"
	private TextField txtMatricola; // Value injected by FXMLLoader

	@FXML // fx:id="txtNome"
	private TextField txtNome; // Value injected by FXMLLoader

	@FXML // fx:id="txtCognome"
	private TextField txtCognome; // Value injected by FXMLLoader

	@FXML // fx:id="txtLog"
	private TextArea txtLog; // Value injected by FXMLLoader
	
	@FXML
    void doAutocompletamento(ActionEvent event) {
		
		try {
			txtNome.clear();
			txtCognome.clear();
			
			int matricola = Integer.parseInt(txtMatricola.getText());
			
			Studente s = model.getStudente(matricola);
			if(s != null)
			{
				txtLog.clear();
				txtNome.setText(s.getNome());
				txtCognome.setText(s.getCognome());
			}
			else {
				txtLog.setText("Studente non trovato!");
			}
			
			
			
		} catch (NumberFormatException e) {
			txtLog.setText("Formato errato. La matricola è formata da 6 numeri interi");
			return;
		}
		
		
    }

	public SegreteriaStudentiController() {

	}

	@FXML
	void doCercaCorsi(ActionEvent event) {
		
	}

	@FXML
	void doCercaIscritiCorso(ActionEvent event) {
		
		txtLog.clear();
		String corsoSel = menuTendCorsi.getValue();
		if(corsoSel=="")
		{
			txtLog.setText("Selezionare una tipologia di corso tra quelle disponibili");
			return;
		}
		List<Studente> studenti=null;
		corsi = model.getCorsi();
		for (Corso c : corsi)
		{
			if(corsoSel.equals(c.getNome())) {
				studenti = model.cercaCorso(c);
			}
		}
		
		txtLog.appendText("Studenti iscritti al corso di " + corsoSel + ": \n");
		int pos = 1;
		for(Studente s : studenti)
		{
			txtLog.appendText(pos + ". Nome = " + s.getNome() + " Cognome = " + s.getCognome() + " Matr." + s.getMatricola() + "\n");
			pos++;
		}


	}

	@FXML
	void doCorsi(ActionEvent event) {

	}

	@FXML
	void doIscriviStudente(ActionEvent event) {

	}

	@FXML
	void doReset(ActionEvent event) {
		txtLog.clear();
		txtNome.clear();
		txtCognome.clear();
		txtMatricola.clear();

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert menuTendCorsi != null : "fx:id=\"menuTendCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtLog != null : "fx:id=\"txtLog\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void startComboBox() {
		// Come popolare un ComboBox
		menuTendCorsi.getItems().removeAll(menuTendCorsi.getItems());

		corsi = model.getCorsi();
		menuTendCorsi.getItems().add("");
		for (Corso c : corsi) {
			menuTendCorsi.getItems().add(c.getNome());
		}
		
	}
}
