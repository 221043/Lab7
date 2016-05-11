package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.dizionario.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController {

	private Model model = new Model();
    
    public void setModel(Model model){
    	this.model = model;
    }
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtParola;

    @FXML
    private Button btnGenera;

    @FXML
    private Button btnVicini;

    @FXML
    private Button btnConnessi;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnReset;

    @FXML
    void doConnessi(ActionEvent event) {
    	try{
    		int lunghezza = Integer.parseInt(txtNumero.getText());
    		if(lunghezza==0){
    			txtRisultato.setText("Lunghezza non accettabile");
    			return;
    		}
    		String nodo = txtParola.getText();
    		if(lunghezza!=nodo.length()){
    			txtRisultato.setText("Incoerenza dimensioni");
    			return;
    		}
    		txtRisultato.setText(model.getConnessi(nodo).toString());
    	} catch(NumberFormatException e){
    		txtRisultato.setText("Inserire valore numerico valido");
    	}	
    }

    @FXML
    void doGrafo(ActionEvent event) {
    	try{
    		int lunghezza = Integer.parseInt(txtNumero.getText());
    		if(lunghezza==0){
    			txtRisultato.setText("Lunghezza non accettabile");
    			return;
    		}
        	model.createGraph(lunghezza);
        	txtRisultato.setText(model.getGraph().toString());
        	btnVicini.setDisable(false);
        	btnConnessi.setDisable(false);
    	} catch(NumberFormatException e){
    		txtRisultato.setText("Inserire valore numerico valido");
    	}	
    }

    @FXML
    void doReset(ActionEvent event) {
    	btnVicini.setDisable(true);
    	btnConnessi.setDisable(true);
    	txtRisultato.setText("");
    	txtNumero.clear();
    	txtParola.clear();
    	this.model = new Model();
    }

    @FXML
    void doVicini(ActionEvent event) {
    	try{
    		int lunghezza = Integer.parseInt(txtNumero.getText());
    		if(lunghezza==0){
    			txtRisultato.setText("Lunghezza non accettabile");
    			return;
    		}
    		String nodo = txtParola.getText();
    		if(lunghezza!=nodo.length()){
    			txtRisultato.setText("Incoerenza dimensioni");
    			return;
    		}
    		txtRisultato.setText(model.getVicini(nodo).toString());
    	} catch(NumberFormatException e){
    		txtRisultato.setText("Inserire valore numerico valido");
    	}	
    }

    @FXML
    void initialize() {
        assert txtNumero != null : "fx:id=\"txtNumero\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert btnGenera != null : "fx:id=\"btnGenera\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert btnVicini != null : "fx:id=\"btnVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert btnConnessi != null : "fx:id=\"btnConnessi\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Dizionario.fxml'.";
        
        btnVicini.setDisable(true);
        btnConnessi.setDisable(true);
     	txtRisultato.wrapTextProperty().set(true);
    }
    
}
