/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */

public class DBpendataan {
   public static Connection con;
    public static Statement stm;
    public static PreparedStatement pst;
    public static ResultSet rs = null ;
    
    public DBpendataan(){
        try{
            String url = "jdbc:mysql://localhost:3306/project_akhir";
            String user = "root";
            String pass = "";
            con = DriverManager.getConnection(url,user,pass);
            stm = con.createStatement();

//            JOptionPane.showMessageDialog(null, "Koneksi Berhasil!");
            }catch(SQLException e){
               JOptionPane.showMessageDialog(null, "Koneksi Gagal");
        }
    }
    public void setSt(Statement stm){
        this.stm = stm;
    }
    public void setCon(Connection con){
        this.con = con;
    }
    public Statement getSt(){
        return stm;
    }
    public Connection getCon(){
        return con;
    }
}
