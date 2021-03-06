package program;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.sql.Date;
import java.util.List;

import common.Ovelse;
import common.Styrke_ovelse;
import common.Utendor_aktivitet;
import common.Utholdenhet_ovelse;
import common.Kondisjon_ovelse;
import common.Innendor_aktivitet;
import common.Kategori;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Callback;



/**
* Interface between the GUI and the Database.
* In this class all usecases that are possible from the interface we be translated into corresponding database querries. Thereby the following usecases are possible:
* <ul>
*	<li>Add a new workout</li>
*	<li>Add a new exercise</li>
*	<li>Add an exercise to a workout</li>
*	<li>List all existing workouts</li>
*	<li>List all existing exercises</li>
* </ul>
*
* @author Group 170
*/
public class WorkoutController implements AppBinder{
	
	// error labels
	
	@FXML Label ovFeedBack;
	@FXML Label trenFeedBack;

	MainApp main;
	Database db;
	// attributes to make life easier
	ObservableList<Ovelse> ovInCollection = FXCollections.observableArrayList();
	ObservableList<Ovelse> ovOutCollection = FXCollections.observableArrayList();
	
	// Treningsokt pane
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
	
	// Utendor
	@FXML Pane utendor_pane;
	@FXML TextField utendor_temp;
	@FXML TextField utendor_vaer;
	
	// Innendor
	@FXML Pane innendor_pane;
	@FXML TextField innendor_publikum;
	@FXML ChoiceBox<Integer> innendor_luft;
	
	// "Øvelser lagt til i treningsøkten" pane
	@FXML ListView<Ovelse> ovelserIn;
	@FXML Button slettOvelseIn;
	
	// Øvelse creation pane
	@FXML TextField ovelse_navn;
	@FXML TextArea ovelse_beskrivelse;
	@FXML ChoiceBox<Kategori> kategori;
	@FXML ChoiceBox<String> type_ovelse;
	
	// Panes for field visibility
	@FXML Pane uthold_input;
	@FXML Pane kondisjon_input;
	@FXML Pane styrke_input;
	
	// Styrke fields
	@FXML TextField styrke_belastning;
	@FXML TextField styrke_rep;
	@FXML TextField styrke_sett;
	
	// Kondisjon fields
	@FXML TextField kond_belastning;
	@FXML TextField kond_rep;
	@FXML TextField kond_sett;
	
	// Uthold fields
	@FXML TextField uthold_distanse;
	@FXML TextField uthold_tid;
	
	@FXML Button registrer_ovelse;
	
	// "Tidliger øvelser" pane
	@FXML ListView<Ovelse> ovelserOut;
	//@FXML Button slettOvelseOut;
	@FXML Button leggTilIn;
	
	/**
	* Initializes and sets values to the db_prosjekt_gui.
	*
	* @author Group 170
	*/
	@FXML
	private void initialize(){
		
		ovFeedBack.setText("");
		trenFeedBack.setText("");
		
		// treningsokt pane init
		db = new Database();
		if(!db.connect()){
			System.out.println("Failed to connect to database");
			System.exit(1);
		}
		System.out.println("Connected to database");
		
		
		updateKategori(db.getKategorier());
		updatePrevOvelse(db.getOvelser());
		
		
		// set numbers for hours in the time selectors
		ObservableList<Integer> hours = FXCollections.observableArrayList();
		for(int i = 0; i < 24; i++){
			hours.add(i);
		}
		hour.setItems((FXCollections.observableArrayList(hours)));
		
		// set numbers for minutes in the time selectors
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
		
		// restrict the "innendor_publikum" field only to take numbers
		// restriction on text field
		innendor_publikum.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					innendor_publikum.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		
		// restrict the "utendor_temp" field only to take numbers
		// restriction on text field
		utendor_temp.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					utendor_temp.setText(newValue.replaceAll("[^\\d]", ""));
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
		innendor_luft.setItems(oneToTen);
		
		type_aktivitet.setItems(FXCollections.observableArrayList(
				"Innendørs", "Utendørs"));
		
		// Visibility dependent on 'type_aktivitet'
		utendor_pane.setVisible(false);
		innendor_pane.setVisible(false);
		
		type_aktivitet.valueProperty().addListener(
				(observable, oldValue, newValue) -> showTrenTypeField());
		
		
		// Ovelser added to our workout pane
		// Visibility dependent on 'type_ovelse'
		uthold_input.setVisible(false);
		kondisjon_input.setVisible(false);
		styrke_input.setVisible(false);
		
		
		// Create Ovelse pane
		type_ovelse.valueProperty().addListener(
				(observable, oldValue, newValue) -> showInputField());
		
		
		// Sets the type of ovelse in the choicebox
		ObservableList<String> ovelseType = FXCollections.observableArrayList(
				"Styrke", "Kondisjon", "Utholdenhet");
		type_ovelse.setItems(ovelseType);
		
		// Restrictions on styrke
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
		
		// Restrictions on uthold
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
		
		// Restrictions on kondisjon
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
		
		
		// Button listeners
		
		// add new ovelse to database and list
		registrer_ovelse.setOnAction(e -> addOvelseOut());
		// adds ovelse from prev ovelser to ovelser for this workout.
		leggTilIn.setOnAction(e -> addOvelseIn(ovelserOut.getSelectionModel().getSelectedItem()));
		// deletes ovelse from current workout.
		slettOvelseIn.setOnAction(e -> removeOvelseIn(ovelserIn.getSelectionModel().getSelectedItem()));
		// deletes ovelse from database and prev ovelse
		//slettOvelseOut.setOnAction(e -> removeOvelseOut(ovelserOut.getSelectionModel().getSelectedItem()));
		// creates workout object and sends in a collection with ovelser to database object
		registrer_okt.setOnAction(e -> createWorkout());
		
		
		
		// listview customization
		// ov in
		ovelserIn.setCellFactory(new Callback<ListView<Ovelse>, ListCell<Ovelse>>(){
			 
            @Override
            public ListCell<Ovelse> call(ListView<Ovelse> p) {
                 
                ListCell<Ovelse> cell = new ListCell<Ovelse>(){
 
                    @Override
                    protected void updateItem(Ovelse o, boolean empty) {
                        super.updateItem(o, empty);
                        String t = null;
                        if (o == null || empty) {
                        	
                        }else{
                        	t = o.navn +": "+ o.beskrivelse;
                        }
                        
                        setText(t);
                        setGraphic(null);
                    }
 
                };
                 
                return cell;
            }
        });
		ovelserOut.setCellFactory(new Callback<ListView<Ovelse>, ListCell<Ovelse>>(){
			 
            @Override
            public ListCell<Ovelse> call(ListView<Ovelse> p) {
                 
                ListCell<Ovelse> cell = new ListCell<Ovelse>(){
 
                    @Override
                    protected void updateItem(Ovelse o, boolean empty) {
                        super.updateItem(o, empty);
                        String t = null;
                        if (o == null || empty) {
                        	
                        }else{
                        	t = o.navn +": "+ o.beskrivelse;
                        }
                        
                        setText(t);
                        setGraphic(null);
                    }
 
                };
                 
                return cell;
            }
        });

	}
	
	/**
	* Adds kategories from the kategori list in the gui to the database.
	*
	* @author Group 170
	* @param kategorier Kategories to be added.
	*/
	public void updateKategori(List<Kategori> kategorier){
		kategori.setItems(FXCollections.observableArrayList(kategorier));
	}
	
	/**
	* Adds ovelser from the ovelser list in the gui to the database.
	*
	* @author Group 170
	* @param ovelser Exercises to be added.
	*/
	public void updatePrevOvelse(Collection<Ovelse> ovelser){
		ovOutCollection = FXCollections.observableArrayList(ovelser);
		ovelserOut.setItems(ovOutCollection);
		ovInCollection.clear();
		ovelserIn.setItems(ovInCollection);
	}
	
	/**
	* Creates a workout with input from gui.
	*
	* @author Group 170
	* @param navn Name of the workout.
	* @param dateLocal Date of the workout.
	* @param h Dour of the workout.
	* @param m Minute of the workout.
	* @param duration Duration of the workout.
	* @param f Form during the workout.
	* @param pres Perstasjon during the workout.
	* @param note Notes made for the workout.
	* @param activ Type activity of the workout.
	* 
	* 
	*/
	private void createWorkout(){
		
		
		trenFeedBack.setText("Trening lagd!");
		String navn = treningsokt_navn.getText();
		LocalDate dateLocal = dato.getValue();
		int h = hour.getValue();
		int m = min.getValue();
		long duration = Long.parseLong(varighet.getText());
		int f = form.getValue();
		int pres = prestasjon.getValue();
		String note = notat.getText();
		String activ = type_aktivitet.getValue();
		
		// creates Date object and Time object
		
		// constructor Date(int, int, int) is deprecated.
		Calendar cal = Calendar.getInstance();
		cal.set(dateLocal.getYear(), dateLocal.getMonthValue(), dateLocal.getDayOfMonth(), h, m, 0);
		Date dateD = Date.valueOf(dateLocal);
		System.out.println(h);
		long mils = dateLocal.atTime(h, m).atZone(ZoneId.of("CET")).toEpochSecond()*1000;
		dateD.setTime(mils);
		
		// gets milliseconds and makes new Time object
		Time time = new Time(cal.getTime().getTime());
		
		Collection<Ovelse> ovelser = ovelserIn.getItems();
		//System.out.println(ovelser.size());
		
		switch(activ){
		//"Innendørs", "Utendørs")
			case "Innendørs":
				// create innendor activitet'
				long pub = Long.parseLong(innendor_publikum.getText());
				int luft = innendor_luft.getValue();
				
				// create innendor object
				Innendor_aktivitet innen = new Innendor_aktivitet(-1, dateD, time, duration, f, pres, note, pub, luft);
				
				// adds ovelser to treningsokt
				for(Ovelse o : ovelser){
					innen.addOvelse(o);
				}
				
				db.insertTreningsOkt(innen);
				break;
			case "Utendørs":
				// create utendørs
				long temp = Long.parseLong(utendor_temp.getText());
				String vaer = utendor_vaer.getText();
				
				// create utendor object
				Utendor_aktivitet uten = new Utendor_aktivitet(-1, dateD, time, duration, f, pres, note, temp, vaer);
				
				// adds ovelser to treningsokt
				for(Ovelse o : ovelser){
					uten.addOvelse(o);
				}
				
				db.insertTreningsOkt(uten);
				break;
			default: 
				// invalid value
				return;
		}
		
		
	}
	
	/**
	* Adds ovelse object to ovInCollection and updates ovelserIn with ovInCollection.
	*
	* @author Group 170
	*/
	private void addOvelseIn(Ovelse ov){
		if(ov == null){return;}
		ovInCollection.add(ov);
		ovOutCollection.remove(ov);
		ovelserOut.setItems(ovOutCollection);
		ovelserOut.refresh();
		ovelserIn.setItems(ovInCollection);
	}
	

	
	/**
	* Removes ovelse object from  ovInCollection and updates ovelserIn with ovInCollection.
	*
	* @author Group 170
	*/
	private void removeOvelseIn(Ovelse ov){
		if(ov == null){return;}
		ovInCollection.remove(ov);
		ovOutCollection.add(ov);
		ovelserIn.setItems(ovInCollection);
		ovelserOut.setItems(ovOutCollection);
	}
	
	/**
	* Changes visibility of input fields dependent on input in type_ovelse.
	*
	* @author Group 170
	*/
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
	
	/**
	* Changes visibility of input fields dependent on input in type_aktivitet.
	*
	* @author Group 170
	*/
	private void showTrenTypeField(){
		// "Innendørs", "Utendørs"
		switch(type_aktivitet.getValue()){
			case "Innendørs":
				utendor_pane.setVisible(false);
				innendor_pane.setVisible(true);
				break;
			case "Utendørs":
				utendor_pane.setVisible(true);
				innendor_pane.setVisible(false);
				break;
			default: 
				// invalid value
				return;
		}
	}
	
	/**
	* Creates ovelse based on type_ovelse and sends to the database.
	*
	* @author Group 170
	* @param navn Name of the ovelse.
	* @param beskrivelse Description of ovelse.
	* @param styrBelastning Weight used in a 'styrke' ovelse.
	* @param styrRepetisjoner Repetitions used in a 'styrke' ovelse.
	* @param styrSett Sett used in a 'styrke' ovelse.
	* @param konBelastning Weight used in a 'kondisjon' ovelse.
	* @param konRepetisjoner Repetitions used in a 'kondisjon' ovelse.
	* @param konSett Sett used in a 'kondisjon' ovelse.
	* @param utDistanse_km Distance covered in an 'uthold' ovelse.
	* @param konBelastning utTid_min used in an 'uthold' ovelse.
	*/
	private void addOvelseOut(){
		// TODO: check if fields are empty, return if they are
		//System.out.println(type_ovelse.getValue());
		if(type_ovelse.getValue() == null){return;}
		String navn = ovelse_navn.getText();
		String beskrivelse = ovelse_beskrivelse.getText();
		Kategori kat = kategori.getValue();
		
		ovFeedBack.setText("Ovelse lagd!");
		switch (type_ovelse.getValue()){
			
			case "Styrke":
				// create styrke class and pass on

				long styrBelastning = Long.parseLong(styrke_belastning.getText());
				long styrRepetisjoner = Long.parseLong(styrke_rep.getText());
				long styrSett = Long.parseLong(styrke_sett.getText());
				
				//long ovelse_id, String navn, String beskrivelse,long belastning,long repetisjoner,long sett
				Styrke_ovelse styrk = new Styrke_ovelse(-1, navn, beskrivelse, styrBelastning, styrRepetisjoner, styrSett);
				styrk.kategori = kat.getId();
				
				db.insertOving(styrk);

				updatePrevOvelse(db.getOvelser());
				break;
			case "Kondisjon":
				// create kondisjon class and pass on

				long konBelastning = Long.parseLong(kond_belastning.getText());
				long konRepetisjoner = Long.parseLong(kond_rep.getText());
				long konSett = Long.parseLong(kond_sett.getText());
				
				//long ovelse_id, String navn, String beskrivelse,long belastning,long repetisjoner,long sett
				Kondisjon_ovelse kond = new Kondisjon_ovelse(-1, navn, beskrivelse, konBelastning, konRepetisjoner, konSett);
				kond.kategori = kat.getId();
				
				db.insertOving(kond);

				updatePrevOvelse(db.getOvelser());
				break;
			case "Utholdenhet":
				// create utholdenhet class and pass on

				long utDistanse_km = Long.parseLong(uthold_distanse.getText());
				long utTid_min = Long.parseLong(uthold_tid.getText());
				//long ovelse_id, String navn, String beskrivelse,long distanse_km,long tig_min
				Utholdenhet_ovelse uthold = new Utholdenhet_ovelse(-1, navn, beskrivelse, utDistanse_km, utTid_min);
				uthold.kategori = kat.getId();
				
				db.insertOving(uthold);

				updatePrevOvelse(db.getOvelser());
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
