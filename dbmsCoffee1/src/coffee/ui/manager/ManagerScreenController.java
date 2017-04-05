package coffee.ui.manager;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class ManagerScreenController implements Initializable{

    @FXML
    private JFXButton inventoryButton;

    @FXML
    private JFXButton timeSheetButton;

    @FXML
    private JFXButton customerButton;

    @FXML
    private AnchorPane rootPane;

    @FXML
    void openCustomerView(ActionEvent event) {

    }

    @FXML
    void openInventoryView(ActionEvent event) {

    }

    @FXML
    void openTimeSheetView(ActionEvent event) {

    }
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
