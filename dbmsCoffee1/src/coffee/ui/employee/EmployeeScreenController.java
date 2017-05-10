package coffee.ui.employee;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class EmployeeScreenController implements Initializable{
	
    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXButton timeSheetButton;
    
    @FXML
    private JFXButton logoutButton;

    @FXML
    void openTimeSheetView(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("employee_time.fxml"));
    	AnchorPane.setBottomAnchor(pane, 0.0);
    	AnchorPane.setLeftAnchor(pane, 0.0);
    	AnchorPane.setRightAnchor(pane, 0.0);
    	AnchorPane.setTopAnchor(pane, 0.0);
    	FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), pane);
    	fadeIn.setFromValue(0.0);
    	fadeIn.setToValue(1.0);
    	fadeIn.play();
    	rootPane.getChildren().setAll(pane);
    }


    @FXML
    void logOut(ActionEvent event) {
    	Stage stage = (Stage) rootPane.getScene().getWindow();
	
		stage.close();
		System.out.println("LOGGED OUT");
    	loadWindow("/coffee/ui/main/coffee_main.fxml", "");
    }
    
void loadWindow(String loc, String title){
    	
    	try {
			Parent parent = FXMLLoader.load(getClass().getResource(loc));
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.setTitle(title);
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/organic-fairtrade-riseup.png")));
			stage.setScene(new Scene(parent));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("employee_time.fxml"));
    	AnchorPane.setBottomAnchor(pane, 0.0);
    	AnchorPane.setLeftAnchor(pane, 0.0);
    	AnchorPane.setRightAnchor(pane, 0.0);
    	AnchorPane.setTopAnchor(pane, 0.0);
    	FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), pane);
    	fadeIn.setFromValue(0.0);
    	fadeIn.setToValue(1.0);
    	fadeIn.play();
    	rootPane.getChildren().setAll(pane);
		} catch(IOException e){
			e.printStackTrace();
		}
	}

}
