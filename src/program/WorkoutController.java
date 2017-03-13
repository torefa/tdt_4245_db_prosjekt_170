package program;

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
	@FXML ChoiceBox hour;
	@FXML ChoiceBox min;
	@FXML TextField varighet;
	@FXML ChoiceBox form;
	@FXML ChoiceBox prestasjon;
	@FXML TextArea notat;
	@FXML ChoiceBox type_aktivitet;
	@FXML Button registrer_okt;
	
	
	// "Øvelser lagt til i treningsøkten" pane
	@FXML ListView ovelserIn;
	@FXML Button slettOvelseIn;
	
	
	// Øvelse creation pane
	@FXML TextField ovelse_navn;
	@FXML TextArea ovelse_beskrivelse;
	@FXML ChoiceBox kategori;
	@FXML ChoiceBox type_ovelse;
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
		hour.setItems((FXCollections.observableArrayList(
				0, 1, 2 , 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23)));
	}
	
	@Override
	public void setMainApp(MainApp mainApp) {
		this.main = mainApp;
		
	}

}
