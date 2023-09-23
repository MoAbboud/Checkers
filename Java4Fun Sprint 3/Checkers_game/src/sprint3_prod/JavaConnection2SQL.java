package sprint3_prod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class JavaConnection2SQL {

	public static void main(String[] args) {
		//creating a connection strings
		String url = "jdbc:sqlserver://localhost;Database=DB_Checkers";
		String user = "admin";
		String password = "Pass123$";
		
		try {
			//creating a connection via drivermanager  
			Connection connection = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to MSSQL successfully.");
			
		} catch (SQLException e) {
			System.out.println("SQL connection error.");
			e.printStackTrace();
		}
	}
	public static Connection ConnectDB() {
		String url = "jdbc:sqlserver://localhost;Database=DB_Checkers";
		String user = "admin";
		String password = "Pass123$";
		
		try {

			Connection connection = DriverManager.getConnection(url, user, password);
			//JOptionPane.showMessageDialog(null, "Connection successfull");
			return connection;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}	
	}
}
