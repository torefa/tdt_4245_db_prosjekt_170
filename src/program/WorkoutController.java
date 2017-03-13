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
	@FXML TextField styrke_belastning;
	@FXML TextField styrke_rep;
	@FXML TextField styrke_sett;
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
		
		// TODO: implement classes for ovelser.
		
		// Create Ovelse pane
		
		
		// TODO: set kategori ChoiceBox
		
		// sets the type of ovelse in the choicebox
		ObservableList<String> ovelseType = FXCollections.observableArrayList(
				"Styrke", "Kondisjon", "Utholdenhet");
		type_ovelse.setItems(ovelseType);
		
		// restricts belastning, reps and sett to only type ints
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
	
	private void addOvelseOut(){
		// TODO: check if fields are empty, return if they are
		
	}
	
	@Override
	public void setMainApp(MainApp mainApp) {
		this.main = mainApp;
		
	}

}
