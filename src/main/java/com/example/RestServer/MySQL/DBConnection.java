package com.example.RestServer.MySQL;

import java.sql.*;

public class DBConnection {

    private static String dbhost = "jdbc:mysql://localhost:3306/User";
    private static String username = "root";
    private static String password = "07Pappa97";
    private static Connection connection;

    @SuppressWarnings("finally")
    public static Connection createNewDBConnection () {
        try {
            connection = DriverManager.getConnection(dbhost, username, password);
        } 
        catch (SQLException e) {
            System.out.println("Cannot create database connection");
            e.printStackTrace();
        }
        finally {
            return connection;
        }
    }

    /*private static class  Write extends HttpServlet {
        @Override
        protected void doPost (HttpServletRequest request, HttpServletResponse reesponse) throws ServletException, IOException{
            Connection connection;

            try{
                connection = getConnection();
                String insert = "INSERT INTO messages (message) VALUES(?)";
                PreparedStatement stmt = connection.prepareStatement(insert);
                stmt.setString(1, request.getParameter("message"));
                stmt.executeUpdate();
                connection.close();              
            }
            catch (SQLException e) {
                throw new ServletException(e);
            }
        }
    }*/


    /*private static  class Read extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Connection connection;
            PrintWriter out = resp.getWriter();
            try {
                connection = getConnection();
                Statement stmt = connection.createStatement();
                String select = "SELECT * FROM messages GROUP BY id DESC LIMIT 1";
                ResultSet rs = stmt.executeQuery(select);
                if (rs.next()) {
                    out.print(rs.getString("message"));
                } else {
                    out.print("Sorry, no message for you!!!");
                }
                out.flush();
                out.close();
                connection.close();
            } catch (SQLException e) {
                throw new ServletException(e);
            }
        }
    }*/
    

    /*public static void main(String[] args) throws SQLException {
        //Create a connection for a server installed in localhost, 
        //with a user "root" woth no password. 
        

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "07Pappa97")){
            //Create a statement 
            try (Statement statement = connection.createStatement()){
                //Execute query
                try (ResultSet resultSet = statement.executeQuery("SELECT 'Hello World!'")) {
                    //Position result to first
                    resultSet.first();
                    System.out.println(resultSet.getString(1)); //Result is "Hello World!"
                }
            
            }
        } 
    }*/

    /*private static void createSchema() throws SQLException {
        Connection connection = getConnection();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS messages");
        stmt.executeUpdate("CREATE TABLE messages (id INT AUTO_INCREMENT, message VARCHAR(45), PRIMARY KEY (id))");
        connection.close();
    }*/

        /*private static Connection getConnection() throws SQLException {
            String host = System.getenv("MYSQLS_HOSTNAME");
            String port = System.getenv("MYSQLS_PORT");
            String database = System.getenv("MYSQLS_DATABASE");
            String username = System.getenv("MYSQLS_USERNAME");
            String password = System.getenv("MYSQLS_PASSWORD");
            String dbUrl = "jdbc:mysql://" + host + ":" + port + "/" +database;
            return DriverManager.getConnection(dbUrl, username, password);
        }*/

    }
