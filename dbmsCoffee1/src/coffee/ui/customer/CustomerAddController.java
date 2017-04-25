package coffee.ui.customer;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CustomerAddController implements Initializable{

	Connection con;
	
	@FXML
    private AnchorPane custAddRootPane;

    @FXML
    private JFXButton addCustButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private Label errorLabel;

    @FXML
    private JFXTextField FnameField;

    @FXML
    private JFXTextField IDField;

    @FXML
    private JFXTextField bdayField;

    @FXML
    private JFXTextField LnameField;

    @FXML
    void addCustomer(ActionEvent event) {
    	try{
    		errorLabel.setText("");
    		String Fname = FnameField.getText();
    		String Lname = LnameField.getText();
    		String id = IDField.getText();
    		String bday = bdayField.getText();
    		String initTier = "Bronze";
    		PreparedStatement stmtPerson = con.prepareStatement("INSERT INTO PERSON VALUES(?,?,?,?)");
    		PreparedStatement stmtCustomer = con.prepareStatement("INSERT INTO CUSTOMER VALUES(?,?)");
    		stmtPerson.setString(1, Fname);
    		stmtPerson.setString(2, Lname);
    		stmtPerson.setString(3, id);
    		stmtPerson.setString(4, bday);
    		stmtPerson.execute();
    		stmtPerson.close();
    		System.out.println("Fields added to PERSON");
    		stmtCustomer.setString(1, initTier);
    		stmtCustomer.setString(2, id);
    		stmtCustomer.execute();
    		stmtCustomer.close();
    		System.out.println("Fields added to CUSTOMER");
    		cancel(event); //close pane on ADD
    				
    	} catch(Exception e){
    		e.printStackTrace();
    		errorLabel.setText("Customer not added.");
    	}
    }

    @FXML
    void cancel(ActionEvent event) {
    	Stage stage = (Stage) custAddRootPane.getScene().getWindow();
    	stage.close();
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		createConnection();
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
