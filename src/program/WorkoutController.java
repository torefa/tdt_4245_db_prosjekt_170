package program;

import java.util.ArrayList;
import java.util.Collection;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class WorkoutController implements AppBinder{

	MainApp main;
	
	// attributes to make life easier
	ObservableList<Ovelse> ovInCollection = FXCollections.observableArrayList();
	
	// treningsokt pane
	@FXML TextField treningsokt_navn;
	@FXML DatePicker dato;
	@FXML ChoiceBox<Integer> hour;
	@FXML ChoiceBox<Integer> min;
	@FXML TextField varighet;
	@FXML ChoiceBox<Integer> form;
	@FXML ChoiceBox<Integer> prestasjon;
	@FXML TextArea notat;
	@FXML ChoiceBox<String> type_aktivitet;
	@FXML Button registrer_okt;
	
	
	// "Øvelser lagt til i treningsøkten" pane
	@FXML ListView<Ovelse> ovelserIn;
	@FXML Button slettOvelseIn;
	
	
	// Øvelse creation pane
	@FXML TextField ovelse_navn;
	@FXML TextArea ovelse_beskrivelse;
	@FXML ChoiceBox<String> kategori;
	@FXML ChoiceBox<String> type_ovelse;
	// Panes for field visibility
	@FXML Pane uthold_input;
	@FXML Pane kondisjon_input;
	@FXML Pane styrke_input;
	
	// styrke fields
	@FXML TextField styrke_belastning;
	@FXML TextField styrke_rep;
	@FXML TextField styrke_sett;
	
	// kondisjon fields
	@FXML TextField kond_belastning;
	@FXML TextField kond_rep;
	@FXML TextField kond_sett;
	
	// uthold fields
	@FXML TextField uthold_distanse;
	@FXML TextField uthold_tid;
	
	@FXML Button registrer_ovelse;
	
	
	
	
	// "Tidliger øvelser" pane
	@FXML ListView<Ovelse> ovelserOut;
	@FXML Button slettOvelseOut;
	@FXML Button leggTilIn;
	
	
	@FXML
	private void initialize(){
		// treningsokt pane init
		
		// set numbers for hours and minutes in the selectors
		ObservableList<Integer> hours = FXCollections.observableArrayList();
		for(int i = 0; i < 24; i++){
			hours.add(i);
		}
		hour.setItems((FXCollections.observableArrayList(hours)));
		
		ObservableList<Integer> minutes = FXCollections.observableArrayList();
		for(int m = 0; m < 60; m++){
			minutes.add(m);
		}
		min.setItems(minutes);
		
		// restrict the "varighet" field only to take numbers
		// restriction on text field
		varighet.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					varighet.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		

		// set the 1-10 values in form and prestasjon
		ObservableList<Integer> oneToTen = FXCollections.observableArrayList();
		for(int i = 1; i < 11; i++){
			oneToTen.add(i);
		}
		
		form.setItems(oneToTen);
		prestasjon.setItems(oneToTen);
		
		// dunno what we should use the "type aktivitet" for when we register out workout.
		
		// ovelser added to our workout pane
		
		uthold_input.setVisible(false);
		kondisjon_input.setVisible(false);
		styrke_input.setVisible(false);;
		
		// TODO: implement classes for ovelser.
		
		// Create Ovelse pane
		
		
		type_ovelse.valueProperty().addListener(
				(observable, oldValue, newValue) -> showInputField());
		
		
		// TODO: set kategori ChoiceBox
		
		// sets the type of ovelse in the choicebox
		ObservableList<String> ovelseType = FXCollections.observableArrayList(
				"Styrke", "Kondisjon", "Utholdenhet");
		type_ovelse.setItems(ovelseType);
		
		// restricts on styrke
		styrke_belastning.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					styrke_belastning.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		styrke_rep.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					styrke_rep.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		styrke_sett.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					styrke_sett.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		
		/*// kondisjon fields
		@FXML TextField kond_belastning;
		@FXML TextField kond_rep;
		@FXML TextField kond_sett;
		*/
		
		// restrics on uthold
		uthold_distanse.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					uthold_distanse.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		uthold_tid.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					uthold_tid.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		
		// restricts on kond
		kond_belastning.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					kond_belastning.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		kond_rep.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					kond_rep.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		kond_sett.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					kond_sett.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		
		
		// button listeners
		
		// add new ovelse to database and list
		registrer_ovelse.setOnAction(e -> addOvelseOut());
		
		
		
	}
	
	public void updateKategori(String[] kategorier){
		kategori.setItems(FXCollections.observableArrayList(kategorier));
		
	}
	
	public void updatePrevOvelse(Collection<Ovelse> ovelser){
		ovelserOut.setItems(FXCollections.observableArrayList(ovelser));
	}
	
	private void addOvelseIn(Ovelse ov){
		ovInCollection.add(ov);
		ovelserIn.setItems(ovInCollection);
	}
	
	private void removeOvelseIn(Ovelse ov){
		ovInCollection.remove(ov);
		ovelserIn.setItems(ovInCollection);
	}
	
	private void showInputField(){
		switch(type_ovelse.getValue()){
			case "Styrke":
				styrke_input.setVisible(true);
				kondisjon_input.setVisible(false);
				uthold_input.setVisible(false);
				break;
			case "Kondisjon":
				styrke_input.setVisible(false);
				kondisjon_input.setVisible(true);
				uthold_input.setVisible(false);
				break;
			case "Utholdenhet":
				styrke_input.setVisible(false);
				kondisjon_input.setVisible(false);
				uthold_input.setVisible(true);
				break;
				
		}
	}
	
	private void addOvelseOut(){
		// TODO: check if fields are empty, return if they are
		//System.out.println(type_ovelse.getValue());
		if(type_ovelse.getValue() == null){return;}
		switch (type_ovelse.getValue()){
			
			case "Styrke":
				// create styrke class and pass on
				String styrNavn = ovelse_navn.getText();
				String styrBeskrivelse = ovelse_beskrivelse.getText();
				// TODO: kategori
				Long styrBelastning = Long.parseLong(styrke_belastning.getText());
				Long styrRepetisjoner = Long.parseLong(styrke_rep.getText());
				Long styrSett = Long.parseLong(styrke_sett.getText());
				
				//long ovelse_id, String navn, String beskrivelse,long belastning,long repetisjoner,long sett
				Styrke_ovelse styrk = new Styrke_ovelse(-1, styrNavn, styrBeskrivelse, styrBelastning, styrRepetisjoner, styrSett);
				
				// TODO: update database with new styrke ovelse and update list
				
				
				break;
			case "Kondisjon":
				// create kondisjon class and pass on
				
				String konNavn = ovelse_navn.getText();
				String konBeskrivelse = ovelse_beskrivelse.getText();
				// TODO: kategori
				Long konBelastning = Long.parseLong(kond_belastning.getText());
				Long konRepetisjoner = Long.parseLong(kond_rep.getText());
				Long konSett = Long.parseLong(kond_sett.getText());
				
				//long ovelse_id, String navn, String beskrivelse,long belastning,long repetisjoner,long sett
				Kondisjon_ovelse kond = new Kondisjon_ovelse(-1, konNavn, konBeskrivelse, konBelastning, konRepetisjoner, konSett);
				// TODO: update database with new kondisjon ovelse and update list
				
				break;
			case "Utholdenhet":
				// create utholdenhet class and pass on

				String utNavn = ovelse_navn.getText();
				String utBeskrivelse = ovelse_beskrivelse.getText();
				// TODO: kategori
				Long utDistanse_km = Long.parseLong(uthold_distanse.getText());
				Long utTid_min = Long.parseLong(uthold_tid.getText());
				//long ovelse_id, String navn, String beskrivelse,long distanse_km,long tig_min
				Utholdenhet_ovelse uthold = new Utholdenhet_ovelse(-1, utNavn, utBeskrivelse, utDistanse_km, utTid_min);
				
				// TODO: update database with new utholdenhet ovelse and update list
				break;
			default:
				// invalid value get from type
				
				return;
		}
	}
	
	@Override
	public void setMainApp(MainApp mainApp) {
		this.main = mainApp;
		
	}

}
