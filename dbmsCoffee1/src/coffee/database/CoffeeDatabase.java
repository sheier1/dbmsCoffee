package coffee.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CoffeeDatabase {
	
	Connection con;
	
	public static void main(String[] args) {
		CoffeeDatabase db = new CoffeeDatabase();
		db.createConnection();
	}

	/**
	 * Load MySQL Driver
	 */
	void createConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/coffeedb", "root", "19Fender94");	
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM CUSTOMER");
			while(rs.next()){
				String fname = rs.getString("Fname");
				String lname = rs.getString("Lname");
				System.out.println(fname + " " +lname);
			}
			stmt.close();
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
