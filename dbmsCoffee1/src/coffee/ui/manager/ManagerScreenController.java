package coffee.ui.manager;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class ManagerScreenController implements Initializable{

	Connection con;

	@FXML
    private JFXButton timeSheetButton;
	
    @FXML
    private JFXButton inventoryButton;

    @FXML
    private JFXButton customerButton;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXButton logoutButon;
    
    @FXML
    void openTimeSheetView(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/coffee/ui/timesheet/timesheet_view.fxml"));
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
    void logOut(ActionEvent event) {
    	Stage stage = (Stage) rootPane.getScene().getWindow();
//    	try {
//			PreparedStatement stmtLog = con.prepareStatement("DELETE FROM CURRENTLOGGED");
//			stmtLog.execute();
//			System.out.println("LOGGED OUT");
//			stmtLog.close();
//			stage.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("No one currently logged in");
//		}
    	stage.close();
    	System.out.println("LOGGED OUT");
    	loadWindow("/coffee/ui/main/coffee_main.fxml", "");
    }

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		  createConnection();
		  try{
		  AnchorPane pane = FXMLLoader.load(getClass().getResource("/coffee/ui/timesheet/timesheet_view.fxml"));
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
	
	void createConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/coffeedb", "root", "19Fender94");	
			System.out.println("Database Connection Success");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
