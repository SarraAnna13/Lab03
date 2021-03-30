package it.polito.tdp.spellchecker;

/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {

	private Dictionary model;
	private List <String> lingue= new ArrayList<String>();
	private List <String> paroletesto= new ArrayList <String>();
	private List <RichWord> correzione= new ArrayList <RichWord>();
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="chooseLanguage"
    private ComboBox<String> chooseLanguage; // Value injected by FXMLLoader

    @FXML // fx:id="txtscritto"
    private TextArea txtscritto; // Value injected by FXMLLoader

    @FXML // fx:id="txtresult"
    private TextArea txtresult; // Value injected by FXMLLoader

    @FXML // fx:id="txtnworderr"
    private Label txtnworderr; // Value injected by FXMLLoader

    @FXML
    void doClearText(ActionEvent event) {
    txtscritto.setText(" ");
    txtresult.setText(" ");
    paroletesto.clear();
    correzione.clear();
    } 

    @FXML
    void doSpellCheck(ActionEvent event) {
    String linguascelta= chooseLanguage.getValue();
    model.loadDictionary(linguascelta);
    
    String testo= txtscritto.getText();
    //testo.replaceAll(“[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\]");
    StringTokenizer st= new StringTokenizer (testo, " ");
    while(st.hasMoreElements()) paroletesto.add(st.nextToken());
    correzione= model.spellCheckText(paroletesto);
    String s="";
    for(RichWord p: correzione) if(!p.isCorretta()) s=s+p.getParola()+"\n";
    txtresult.setText(s);
    
    
    }

    
    public void setModel(Dictionary model)
    {this.model=model;
    lingue.add("English");
    lingue.add("Italiano");
    chooseLanguage.getItems().addAll(lingue);
    }
    
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert chooseLanguage != null : "fx:id=\"chooseLanguage\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtscritto != null : "fx:id=\"txtscritto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtresult != null : "fx:id=\"txtresult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtnworderr != null : "fx:id=\"txtnworderr\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}


