package DAO;

import java.sql.*;
import javax.swing.JOptionPane;

public class conexaoDAO {
    public Connection conectaBD(){
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/colegio?user=root&password=";
            conn = DriverManager.getConnection(url);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
        return conn;
    }
    
}
