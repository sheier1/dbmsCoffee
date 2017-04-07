package coffee.ui.main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CoffeeMainController implements Initializable{

	@FXML
    private AnchorPane rootPane;
	
    @FXML
    private Button employeeLogButton;

    @FXML
    private Button managerLoginButton;

    @FXML
    void openEmployeeLogin(ActionEvent event) {
    	loadWindow("/coffee/ui/employee/employee_login.fxml", "");
    	((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void showManagerLogin(ActionEvent event) {
//    	loadWindow("/coffee/ui/manager/manager_login.fxml", "Manager Login");
    	loadWindow("/coffee/ui/manager/manager_login.fxml", "");
    	((Node) (event.getSource())).getScene().getWindow().hide();
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
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

}
