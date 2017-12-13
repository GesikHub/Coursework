package coursework;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;


public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("app.fxml"));
		
		Scene scene = new Scene(root, 1220, 800);
		
		stage.setTitle("Постройка графика");
		stage.setScene(scene);
		stage.show();
		
	}

}
