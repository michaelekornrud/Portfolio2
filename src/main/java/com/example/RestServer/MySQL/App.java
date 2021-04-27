package com.example.RestServer.MySQL;

import java.io.IOException;
import java.nio.channels.NonWritableChannelException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    private static String JSONOutput = null;
    private static PreparedStatement preparedStatement;

    private static int[] list = {4};

    public static void main(String[] args) throws SQLException, IOException {
        addUser(1, "Michael" , "Hei fra Michael");
        //System.out.println("First availiavble Id: " + getFirstAvailableId());
        //removeUser(5);
        //System.out.println("Id list: " + idList().toString());
        //System.out.println("Get max Id: " + getMaxId());
        //System.out.println("Get all users: " + getAllUsers());
        //System.out.println("Get spesific user: " + getUser(4));
        //updateUser(2, "", "2");
        //removeSeveralUsers(list);
        //System.out.println(getSelectedCollumn("Name"));


        
        
    }


    public static ArrayList<Integer> idList () throws SQLException {
        String idList = "select Id from user_info";

        ArrayList<Integer> list = new ArrayList<>();

        try (Connection conn = DBConnection.createNewDBConnection()){
            statement = conn.createStatement();
            results = statement.executeQuery(idList);

            while (results.next()){
                int getId = results.getInt(1);
                list.add(getId);
            }
        }
        return list;
    }

    public static int getMaxId () throws SQLException, JsonProcessingException {

        String idMAX =  "select max(Id) from user_info";
        int getId = 0;
                
        try (Connection conn = DBConnection.createNewDBConnection()){
            statement = conn.createStatement();
            results = statement.executeQuery(idMAX);


            while (results.next()){
                getId = results.getInt(1);
            }

            ObjectMapper mapper = new ObjectMapper();
            JSONOutput = mapper.writeValueAsString(getId);
        }

        JSONOutput = JSONOutput.replaceAll("^\"|\"$", "");
        int idPluss = Integer.parseInt(JSONOutput);  
        return idPluss;
    }

    public static int getFirstAvailableId () throws JsonProcessingException, SQLException, IOException {
        int maxID = getMaxId();
        int value = 0;
        String listString = null;
        ArrayList<Integer> list = idList();
        ArrayList<Integer> listA = new ArrayList<>();
            for (int i = 1; i < list.size();  i++){
                listA.add(i);
            }

            ArrayList<Integer> listB = new ArrayList<>(listA);
            listB.removeAll(list);

            listString = listB.toString();
            
            listString = listString.replaceAll("\\p{P}","");
            System.out.println("ListString: " + listString);
            if (!listB.isEmpty()){
                value = Integer.parseInt(listString);
            }
            else  {
                value = maxID + 1;
            }
            System.out.println("Value: " + value);
            return value;

    }

    public static String getSelectedCollumn (String collumn) throws SQLException, JsonProcessingException{

        if (collumn.isEmpty() || collumn.equals("")){
            System.err.println("[FROM SQL] Input myst have a value!");
        }

        String sql_select = "select " + collumn + " from user_info";

        ArrayList<String> list = new ArrayList<>();

        try (Connection conn = DBConnection.createNewDBConnection()){

            statement = conn.createStatement();
            results = statement.executeQuery(sql_select);

            while (results.next()) {
                String getValues = new String(results.getString(collumn));
                list.add(getValues);

            }

            ObjectMapper mapper = new ObjectMapper();
            JSONOutput = mapper.writeValueAsString(list);
        }




        return JSONOutput;
    }

    public static void addUser (int id, String name, String message) throws SQLException, IOException {
       
        int firstAvail = getFirstAvailableId();
        ArrayList<Integer> list = idList();

        if (list.contains(id)){
            System.out.println("[FROM SQL] Id " + id + " is already taken. Generating new id...\n...\nGenerated new id: " + firstAvail);
            id = firstAvail;    
        }

        String sql_add = "insert into user_info (Id, Name, Message) values ('" + id + "', '" + name + "', '" + message + "')";
   
        try (Connection conn = DBConnection.createNewDBConnection()) {
            preparedStatement = conn.prepareStatement(sql_add);
            preparedStatement.execute();
        }
        catch (SQLException e) {
            System.out.println("[FROM SQL] Id: " + id + " is already taken. Please choose another one!");
        }
    }

    public static String getAllUsers() throws SQLException, JsonProcessingException {
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
		    JSONOutput = mapper.writeValueAsString(userList);
		    	
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

        return JSONOutput;
    }

    public static String getUser(int id){
        String sql_select = "SELECT * from user_info WHERE Id like " + id;

        try (Connection conn = DBConnection.createNewDBConnection()){
            statement = conn.createStatement();
            results = statement.executeQuery(sql_select);

            List<User> userList = new ArrayList<User>();

            while (results.next()){
                User stdObject = new User(results.getString("Id"), results.getString("Name"), results.getString("Message"));
                userList.add(stdObject);    
            }

            ObjectMapper mapper = new ObjectMapper();
            JSONOutput = mapper.writeValueAsString(userList);

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }


        return JSONOutput;
    }
    
    public static void removeSingleUser (int id)throws SQLException {

        ArrayList<Integer> list = idList();

        if (!list.contains(id)){
            System.err.println("[FROM SQL] User does not exist!");
        }
        else{

            String sql_delete = "delete from user_info where Id = " + id;

            try (Connection conn = DBConnection.createNewDBConnection()) {
                preparedStatement = conn.prepareStatement(sql_delete);
                preparedStatement.execute();
            }

            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void removeSeveralUsers (int [] list)  throws SQLException {

        ArrayList<Integer> idList = idList();

        if (list.length == 0){
            System.out.println("[FROM SQL] Input-list must contain some values.");
        }
        for (Integer id : list){
            if (idList.contains(id)){
                String sql_remove = "delete from user_info where Id = " + id;
            
                try (Connection  conn = DBConnection.createNewDBConnection()) {
                    preparedStatement = conn.prepareStatement(sql_remove);
                    preparedStatement.execute();
                }
            }
            else {
                System.err.println("[FROM SQL] Given id(s) does not exist.");
            }
        }
    }

    public static void updateUser (int id, String update, String newValue) throws SQLException {
        String sql_update = "update user_info set " + update + " = '" + newValue + "' where Id = "  + id;
        ArrayList<Integer> list  = idList();

        if (!list.contains(id)){
            System.err.println("[FROM SQL] Id does not exist. Please choose another id to update");
        }
        else if (update.isEmpty() || update.equals("") || newValue.isEmpty() || newValue.equals("")){
            System.err.println("[FROM SQL] Input values cannot be empty. Please enter a value");
        }
        else{
            try (Connection conn = DBConnection.createNewDBConnection()){
                //statement = conn.createStatement();
                //results = statement.executeQuery(sql_update);

                preparedStatement = conn.prepareStatement(sql_update);
                preparedStatement.execute();
            }
        }

    }
}
