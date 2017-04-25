package coffee.ui.inventory;

import java.net.URL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManagerInventoryController implements Initializable{
	
	Connection con;
	ObservableList<Inventory> list = FXCollections.observableArrayList();
	
	//***View All table**************
    @FXML
    private TableView<Inventory> viewAllTable;
    //******************************

	//*******************************************
	//*****Remove Items Tab******************************
	
	@FXML
	private JFXButton removeButton;

	@FXML
	private TableView<Inventory> InventoryTable;

	@FXML
	private TableColumn<Inventory, String> itemCol;

	@FXML
	private TableColumn<Inventory, Integer> qtyCol;
	
    @FXML
    private TableColumn<Inventory, String> unitCol;

	@FXML
	private TableColumn<Inventory, String> expDateCol;
	    
	
	
	    
	@FXML
	void removeItem(ActionEvent event) {
		try{
			/*
			 * Should try implementing the deletion of multiple items
			 * 
			 * -will have to update the stmt for deleting multiple items from database
			 */
			Inventory inv = InventoryTable.getSelectionModel().getSelectedItem(); //get mouse selection
			String item = inv.getItem();
			PreparedStatement stmt = con.prepareStatement("DELETE FROM INVENTORY WHERE items = ?");
			stmt.setString(1, item); //setting item to be deleted
			stmt.executeUpdate(); //item deleted
			System.out.println(item + "WAS REMOVED FROM INVENTORY");
			stmt.close(); //close
			refreshTable(); //refresh table
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	void refreshTable(){
		try{
			list.clear(); //clear list at start of the new update
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM INVENTORY");
			while(rs.next()){
				String item = rs.getString("item");
				Integer qty = rs.getInt("qty");
				String unit = rs.getString("unit");
				String expDate = rs.getString("expDate");
				System.out.println(item + " " + qty + " " +unit);
				list.add(new Inventory(item, qty, unit, expDate));
			}
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		InventoryTable.getItems().setAll(list);
	}
	
	
	private void initCol(){
		itemCol.setCellValueFactory(new PropertyValueFactory<>("item"));
		qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
		unitCol.setCellValueFactory(new PropertyValueFactory<>("unit"));
		expDateCol.setCellValueFactory(new PropertyValueFactory<>("expDate"));
	}
	//*****************************************************
	//*******************************************************
	    
	    
	//***********************************************
	//***********add Items Tab*****************************************
    @FXML
    private Button addButtton;

    @FXML
    private DatePicker dateSelector;

    @FXML
    private TextField itemQuantityField;

    @FXML
    private TextField itemUnitsField;

    @FXML
    private MenuButton itemSelect;
    
    @FXML
    private JFXTextField itemTextField;

    @FXML
    private Label successLabel;

    //get String of selected item from MenuButtton
    void getSelectedItem(){
    	for (final MenuItem item : itemSelect.getItems()) {
            item.setOnAction((event) -> {               
                System.out.println("Selected: " +item.getText());
                itemTextField.setText(item.getText()); //insertInto selected item into text field
            });
        }
    }
    
    @FXML
    void addNewItem(ActionEvent event) {
    	try{
    		successLabel.setText("");
    		String item = itemTextField.getText(); //get text from textfield to store in db
    		int qty = Integer.parseInt(itemQuantityField.getText());
    		String unit = itemUnitsField.getText();
    		java.util.Date date = 
    			    java.util.Date.from(dateSelector.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
    			java.sql.Date expDate = new java.sql.Date(date.getTime());
    			System.out.println(item +" : " +qty);
//    		PreparedStatement stmt = con.prepareStatement("INSERT INTO products VALUES(?)");
//    		stmt.setString(1, item);
//    		stmt.execute();
//    		stmt.close();
//    		System.out.println("Non Existing item added to prodcut table");
    		PreparedStatement stmt2 = con.prepareStatement("INSERT INTO inventory VALUES(?,?,?,?)");
    		stmt2.setString(1, item);
    		stmt2.setInt(2, qty);
    		stmt2.setString(3, unit);
    		stmt2.setDate(4, expDate);
    		stmt2.execute();
    		stmt2.close();
    		successLabel.setText("Item was added!"); 
    		refreshTable();
    		itemQuantityField.clear();
    		itemUnitsField.clear();
    	} catch(Exception e){
    		e.printStackTrace();
    		successLabel.setText("Item was not added");
    	}
    }
	//***********************************************************
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		createConnection();
		initCol();
		refreshTable();
		getSelectedItem();
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
	
	public static class Inventory{
		private final SimpleStringProperty item;
		private final SimpleIntegerProperty qty;
		private final SimpleStringProperty unit;
		private final SimpleStringProperty expDate;
		
		public Inventory(String item, Integer qty, String unit, String expDate){
			this.item = new SimpleStringProperty(item);
			this.qty = new SimpleIntegerProperty(qty);
			this.unit = new SimpleStringProperty(unit);
			this.expDate = new SimpleStringProperty(expDate);
		}

		public String getItem() {
			return item.get();
		}

		public Integer getQty() {
			return qty.get();
		}
		
		public String getUnit(){
			return unit.get();
		}

		public String getExpDate() {
			return expDate.get();
		}
		
		
		
	}

}
