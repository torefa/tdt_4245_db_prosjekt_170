package program;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MainApp extends Application{
	
	public static void main(String[] args){
		launch(MainApp.class, args);
	}
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
			
			// give controller access to main app
			//AppBinder controller = loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
