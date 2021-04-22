package com.example.RestServer.MySQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.RestServer.UserService.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {

    private static Statement statement;
    private static ResultSet results;

    public static void main(String[] args) {
        String sql_select = "Select * From user_info";
		
		try(Connection conn = DBConnection.createNewDBConnection()){
			
			statement = conn.createStatement();
			results = statement.executeQuery(sql_select);
			
			List<User> userList = new ArrayList<User>();			
			
			 while (results.next()) {
				 
				User stdObject = new User(results.getString("Id"), results.getString("Name"), results.getString("Message"));
	
				userList.add(stdObject);
			 }
			
			ObjectMapper mapper = new ObjectMapper();
		    String JSONOutput = mapper.writeValueAsString(userList);
		    System.out.println(JSONOutput);
		    
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    }
    
}
