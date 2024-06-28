/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema.ra;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**  
 *
 * @author Yuiridia
 */ 
public class DatabaseHandler {
    
 private static final String URL="jdbc:mysql://localhost:3306/chatdb";
    
private static final String USER = "root";
    private static final String PASSWORD = "";
    private Connection connection;

    public DatabaseHandler() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getAnswer(String question) {
        String answer = "Lo siento, no tengo una respuesta para esa pregunta.";
        try {
            String query = "SELECT answer FROM faq WHERE question LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + question + "%");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                answer = resultSet.getString("answer");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }
}

