package model;

import java.sql.*; 

public class Project {

	//A common method to connect to the DB
	private static Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
			System.out.println("succsess");
		}
        catch (Exception e)
			{e.printStackTrace();}
			return con;
		}
	
	// insert
	public String insertproject(String code, String title, String manager,  String category)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting."; 
			}


			// create a prepared statement
			String query = " INSERT INTO projects (projectCode, projectTitle, projectManager, projectCategory )" + " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(1, code);
			preparedStmt.setString(2, title);
			preparedStmt.setString(3, manager);
			preparedStmt.setString(4, category);



			// execute the statement
			preparedStmt.execute();
			con.close();
			String newProject = readproject(); 
			output = "{\"status\":\"success\", \"data\": \"" + newProject + "\"}";
			//output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the project.\"}";
			System.err.println(e.getMessage());
			System.out.println(e.getMessage());
			System.out.println(e);
			e.printStackTrace();
		}
		return output;
	}
	
	// read
	public String readproject() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'>"+ "<tr><th>Project ID</th>" + 
			 "<th>Project Code</th>" + 
			 "<th>Project Title</th>" + 
			 "<th>Project Manager</th>" + 
			 "<th>Project Category</th>" +
			 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "SELECT * FROM projects"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String projectID = Integer.toString(rs.getInt("projectID")); 
	 String projectCode = rs.getString("projectCode"); 
	 String projectTitle = rs.getString("projectTitle"); 
	 String projectManager = rs.getString("projectManager"); 
	 String projectCategory = rs.getString("projectCategory"); 
	
	 // Add into the html table
	 output += "<td>" + projectID + "</td>"; 
	 output += "<td>" + projectCode + "</td>"; 
	 output += "<td>" + projectTitle + "</td>"; 
	 output += "<td>" + projectManager + "</td>"; 
	 output += "<td>" + projectCategory + "</td>"; 
	 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
			 + "<td><button class='btnRemove btn btn-danger' name='btnRemove' id ='btnRemove' value='"+ projectID +"' >Remove</button></td></tr>";
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the projects."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	// update
	public String updateproject(String projectID, String projectCode, String projectTitle, String projectManager, String projectCategory)
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE projects SET projectCode=?,projectTitle=?,projectManager=?,projectCategory=? WHERE projectID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setString(1, projectCode); 
		 preparedStmt.setString(2, projectTitle); 
		 preparedStmt.setString(3, projectManager); 
		 preparedStmt.setString(4, projectCategory); 
		 preparedStmt.setInt(5, Integer.parseInt(projectID)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		
		 //output = "Updated successfully"; 
		 String newProject = readproject(); 
		 output = "{\"status\":\"success\", \"data\": \"" + newProject + "\"}";
		 } 
		 catch (Exception e) 
		 { 
			//output = "Error while updating the item."; 
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the project.\"}"; 
			 System.err.println(e.getMessage()); 
//			 System.out.println(e);
		 } 
		 return output; 
		 } 
	
	    // delete
		public String deleteproject(String projectID) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for deleting."; } 
		 
		 // create a prepared statement
		 String query = "DELETE FROM projects where projectID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(projectID)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		
		 //output = "Deleted successfully"; 
		 String newProject = readproject(); output = "{\"status\":\"success\", \"data\": \"" + newProject + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while deleting the order.\"}"; 
			 System.err.println(e.getMessage()); 
			 System.out.println(e);
		 } 
		 return output; 
		 } 
}

