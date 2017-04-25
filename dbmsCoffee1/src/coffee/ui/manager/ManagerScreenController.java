package coffee.ui.manager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

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
    void openCustomerView(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/coffee/ui/customer/customer_screen.fxml"));
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
    void openInventoryView(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/coffee/ui/inventory/manager_inventory.fxml"));
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
    void openTimeSheetView(ActionEvent event) {

    }
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
