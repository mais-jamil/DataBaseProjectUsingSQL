import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases {
	
	WebDriver driver = new ChromeDriver();
	
	Connection con;
	Statement stmt;
	ResultSet rs;
	
	@BeforeTest
	public void mySetUp() throws SQLException {
		
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","mais");
		
	}
	
	@Test(priority = 1)
	public void AddDatatoMyDB() throws SQLException {
		
		String Query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, city, country)\r\n"
				+ "VALUES (6486, 'MaisJamil', 'Barhhoumeh', 'Mais', '123654789', '67, rue des Cinquante Otages', 'Amman', 'Jordan');";
		stmt = con.createStatement();
		stmt.executeUpdate(Query);
		
	}
	
	@Test(priority = 2)
	public void updateData() throws SQLException {
		
		String Query = "update customers set contactFirstName='Maissss' where  customerNumber=6486";
		stmt = con.createStatement();
		stmt.executeUpdate(Query);
	}
	
	@Test(priority = 3)
	public void readData() throws SQLException {
		String Query = "select * from customers where  customerNumber=6486";
		stmt = con.createStatement();
		rs = stmt.executeQuery(Query);
		
		while(rs.next()) {
			String DBcustomerNum = rs.getString("customerNumber");
			String DBcustomerName = rs.getString("contactFirstName");
			
			System.out.println(DBcustomerNum);
			System.out.println(DBcustomerName);

		}
		
	}
	
	@Test(priority = 4)
	public void deleteData() throws SQLException {
		
		String Query = "delete from customers where customerNumber=6486";
		stmt = con.createStatement();
		stmt.executeUpdate(Query);
				
	}
}





