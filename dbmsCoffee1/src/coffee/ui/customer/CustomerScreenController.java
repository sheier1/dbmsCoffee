package coffee.ui.customer;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CustomerScreenController implements Initializable{

	Connection con;
	ObservableList<Customer> list = FXCollections.observableArrayList();

    @FXML	
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Customer, String> FnameCol;

    @FXML
    private TableColumn<Customer, String> LnameCol;

    @FXML
    private TableColumn<Customer, String> IDCol;

    @FXML
    private TableColumn<Customer, String> TierCol;

    @FXML
    private JFXTextField customerIDField;

    @FXML
    private JFXButton searchButton;
    
    @FXML
    private Label errorLabel;
    
//*************************************************
    @FXML
    private ImageView tierImage;
    
    @FXML
    private Text dispFnameField;

    @FXML
    private Text dispLnameField;

    @FXML
    private Text dispIDField;

    @FXML
    private Label NameLabel;

    @FXML
    private Label IDLabel;

    @FXML
    private Label TierLabel;
 //***************************************************************
    
    @FXML
    private JFXButton addCustomerButton;

    @FXML
    private Label AddCustomerLabel;

    @FXML
    void addCustomer(ActionEvent event) {
    	try{
    		Parent root = FXMLLoader.load(getClass().getResource("customer_add.fxml"));
    		Stage stage = new Stage();
    		stage.initStyle(StageStyle.UTILITY);
    		stage.setScene(new Scene(root));
    		stage.show();
    	} catch(Exception e){
    		System.out.println("Can't load window");
    	}
    }

    @FXML
    void searchCustomerID(ActionEvent event) {
    	String dbID;
    	String dbTier;
    	Image bronze = new Image("/001-bronze-medal.png");
    	Image silver = new Image("/002-silver-medal.png");
    	Image gold = new Image("/003-gold-medal.png");
    	try{
    		list.clear();
    		errorLabel.setText("");
    		String id = new String(customerIDField.getText());
    		
    		Statement stmt = con.createStatement();
//    		ResultSet rs = stmt.executeQuery("SELECT cust_id, tier FROM CUSTOMER WHERE cust_id LIKE '%"+id+"%'");
    		ResultSet rs = stmt.executeQuery("SELECT Fname, Lname, cust_id, tier FROM CUSTOMER c INNER JOIN PERSON p on c.cust_id = p.num_id WHERE cust_id LIKE '%"+id+"%'");
    		while(rs.next()){
    			dbID = rs.getString("cust_id");
    			dbTier = rs.getString("tier");
    			
    			if(id.equals(dbID)){
    			String Fname = rs.getString("Fname");
    			dispFnameField.setText(Fname);
    			String Lname = rs.getString("Lname");
    			dispLnameField.setText(Lname);
    			String cust_id = rs.getString("cust_id");
    			dispIDField.setText(cust_id);
    			String tier = rs.getString("tier");
    			System.out.println(Fname + " " +cust_id);
    			list.add(new Customer(Fname, Lname, cust_id, tier));
    			
    				if(dbTier.equals("Bronze")){
    					tierImage.setImage(bronze);
    				} else if(dbTier.equals("Silver")){
    					tierImage.setImage(silver);
    				} else if(dbTier.equals("Gold")){
    					tierImage.setImage(gold);
    				} else{
    					System.out.println("No tier");
    				}
    			} else{
    				System.out.println("CUSTOMER NOT FOUND");
    				errorLabel.setText("Customer Not Found!");
    			}
    		}
    		stmt.close();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	customerTable.getItems().setAll(list);
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		createConnection();
		initCol();
		//getSelectedItem();
	}
	
	private void initCol(){
		FnameCol.setCellValueFactory(new PropertyValueFactory<>("Fname"));
		LnameCol.setCellValueFactory(new PropertyValueFactory<>("Lname"));
		IDCol.setCellValueFactory(new PropertyValueFactory<>("cust_id"));
		TierCol.setCellValueFactory(new PropertyValueFactory<>("tier"));
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
	
	public static class Customer{
		private final SimpleStringProperty Fname;
		private final SimpleStringProperty Lname;
		private final SimpleStringProperty cust_id;
		private final SimpleStringProperty tier;
		
		public Customer(String Fname, String Lname, String cust_id, String tier){
			this.Fname = new SimpleStringProperty(Fname);
			this.Lname = new SimpleStringProperty(Lname);
			this.cust_id = new SimpleStringProperty(cust_id);
			this.tier = new SimpleStringProperty(tier);
		}

		public String getFname() {
			return Fname.get();
		}

		public String getLname() {
			return Lname.get();
		}

		public String getCust_id() {
			return cust_id.get();
		}

		public String getTier() {
			return tier.get();
		}
		
		
	}

}
