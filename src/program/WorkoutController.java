package program;

import java.util.ArrayList;

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
	@FXML ListView ovelserIn;
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
	@FXML ListView ovelserOut;
	@FXML Button slettOvelseOut;
	@FXML Button leggTilIn;
	
	
	@FXML
	private void initialize(){
		// treningsokt pane init

		ObservableList<Integer> hours = FXCollections.observableArrayList();
		for(int i = 0; i < 24; i++){
			hours.add(i);
		}
		hour.setItems((FXCollections.observableArrayList(hours)));
		
		
	}
	
	@Override
	public void setMainApp(MainApp mainApp) {
		this.main = mainApp;
		
	}

}
