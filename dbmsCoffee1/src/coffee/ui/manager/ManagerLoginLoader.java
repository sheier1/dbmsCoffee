package coffee.ui.manager;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ManagerLoginLoader extends Application {

	@Override
	public void start(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("manager_inventory.fxml"));
		
		Scene scene = new Scene(root);
		
		//stage.setTitle("Coffee");
		stage.initStyle(StageStyle.DECORATED);
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/coffee_icon.png")));
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
