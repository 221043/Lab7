package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.List;
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
    private TextField txtArrivo;
    
    @FXML
    private Button btnCammino;

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
    		if(!model.findParola(nodo)){
    			txtRisultato.setText("Nodo non presente nel grafo");
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
        	btnCammino.setDisable(false);
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
    	btnCammino.setDisable(true);
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
    void doCammino(ActionEvent event) {
    	String s1 = txtParola.getText();
    	String s2 = txtArrivo.getText();
    	if(s1.length()!=model.getLen() || s2.length()!=model.getLen()){
    		txtRisultato.setText("Lunghezza parola errata!\n");
    		return;
    	}
    	if(!model.findParola(s1) || !model.findParola(s2)){
    		txtRisultato.setText("Nodi non presenti nel grafo");
    		return;
    	}
    	List<String> cammino = model.getCammino(s1, s2);
    	if(cammino==null){
    		txtRisultato.setText("Vertici non connessi");
    		return;
    	}
    	txtRisultato.setText("**"+s1+"-"+s2+"** ("+cammino.size()+" passi)\n");
    	for(String p:cammino)
    		txtRisultato.appendText("\t"+p+"\n");
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
        assert btnCammino != null : "fx:id=\"btnCammino\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert txtArrivo != null : "fx:id=\"txtArrivo\" was not injected: check your FXML file 'Dizionario.fxml'.";

        btnCammino.setDisable(true);
        btnVicini.setDisable(true);
        btnConnessi.setDisable(true);
     	txtRisultato.wrapTextProperty().set(true);
    }
    
}
