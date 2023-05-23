package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;
    public static Statement stm;
    public static PreparedStatement pst;
    public static ResultSet rs = null ;

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private DatabaseConnection() {

    }

    public void connectToDatabase() throws SQLException {
       try{
            String url = "jdbc:mysql://localhost:3306/project_akhir";
            String user = "root";
            String pass = "";
            connection = DriverManager.getConnection(url,user,pass);
            stm = connection.createStatement();
            }catch(SQLException e){
               JOptionPane.showMessageDialog(null, "Koneksi Gagal");
        }
    }

       public void setSt(Statement stm){
        this.stm = stm;
    }
    public void setCon(Connection con){
        this.connection = con;
    }
    public Statement getSt(){
        return stm;
    }
    public Connection getCon(){
        return connection;
    }
}
