package program;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * Main class to start the program.
 * Loads the GUI and connects it to its controller. The controller takes over all the work afterwards.
 *
 * @author Group 170
 */
public class MainApp extends Application{
	
	/**
	* Will be ignored.
	* If not will it just call the start function through launch from {@link Application}
	*
	* @author Group 170
	* @param args Starting parameters, will be ignored.
	*/
	public static void main(String[] args){
		launch(MainApp.class, args);
	}
	
	/**
	* Loads the GUI and connects it to its controller.
	*
	* @author Group 170
	*/
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage = primaryStage;
		primaryStage.setTitle("Database project");
		
		FXMLLoader loader = new FXMLLoader();

		// Show the scene containing the root layout.
		
		try {
			// Load overview.
			loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("db_prosjekt_gui.fxml"));
			FlowPane overview = (FlowPane) loader.load();
			
			
			Scene scene = new Scene(overview);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			// give controller access to main app, so we can point at database etc
			// AppBinder controller = loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
