package service;

import Connection.DatabaseConnection;
import model.ModelLogin;
import model.ModelUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Random;
import javax.swing.JOptionPane;

public class ServiceUser {

    private final Connection con;

    public ServiceUser() {
        con = DatabaseConnection.getInstance().getCon();
    }
       public Boolean checkSignup(String email) throws SQLException{
        String cekQuery = "select * from user";
        try{
            ResultSet rs = con.createStatement().executeQuery(cekQuery);
            while(rs.next()){
                if(email.equals(rs.getString("Email"))){
                    return true;
                }
            }
        }catch(SQLException e){
            System.out.print(e.toString());
        }
        return false;
        
    }
     
    public ModelUser login(ModelLogin login) throws SQLException {
        ModelUser data = null;
         String cekQuery = "select * from user where email='" + login.getEmail() +"'AND Password='" + login.getPassword() + "' AND Status='verified'";
                ResultSet rs = con.createStatement().executeQuery(cekQuery);
            try {
                        while(rs.next()){
                            int userID = rs.getInt(1);
                            String userName = rs.getString(2);
                            String email = rs.getString(3);
                            data = new ModelUser(userID, userName, email, "");
                        }
            } catch (SQLException ex) {
                System.out.print(ex.toString());
            }
        rs.close();
        return data;
    }

    public void insertUser(ModelUser user) throws SQLException {
        PreparedStatement p = con.prepareStatement("insert into `user` (UserName, Email, `Password`, VerifyCode) values (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        String code = generateVerifyCode();
        try {
            p.setString(1, user.getUserName());
            p.setString(2, user.getEmail());
            p.setString(3, user.getPassword());
            p.setString(4, code);
            p.execute();
            ResultSet rs = p.getGeneratedKeys();
            while(rs.next()){
            int userID = rs.getInt(1);
            user.setUserID(userID);
            user.setVerifyCode(code);
            }
            rs.close();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String generateVerifyCode() throws SQLException {
        DecimalFormat df = new DecimalFormat("000000");
        Random ran = new Random();
        String code = df.format(ran.nextInt(1000000));  //  Random from 0 to 999999
        while (checkDuplicateCode(code)) {
            code = df.format(ran.nextInt(1000000));
        }
        return code;
    }

    private boolean checkDuplicateCode(String code) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("select UserID from `user` where VerifyCode=?");
        try {
            p.setString(1, code);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                duplicate = true;
            }
            rs.close();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return duplicate;
    }

    public boolean checkDuplicateUser(String user) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("select UserID from `user` where UserName=? and `Status`='Verified'");
        try {
            p.setString(1, user);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                duplicate = true;
            }
            rs.close();
            p.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return duplicate;
    }

    public boolean checkDuplicateEmail(String user) throws SQLException {
        boolean duplicate = false;
        PreparedStatement p = con.prepareStatement("select UserID from `user` where Email=? and `Status`='Verified' limit 1");
        try {
            p.setString(1, user);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                duplicate = true;
            }
            rs.close();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return duplicate;
    }

    public void doneVerify(int userID) throws SQLException {
        PreparedStatement p = con.prepareStatement("update `user` set VerifyCode='', `Status`='Verified' where UserID=?");
        try {
            p.setInt(1, userID);
            p.execute();
            p.close();
        } catch (Exception e) {
        }
    }

    public boolean verifyCodeWithUser(int userID, String code) throws SQLException {
        boolean verify = false;
        PreparedStatement p = con.prepareStatement("select UserID from `user` where UserID=? and VerifyCode=?");
        try {
            p.setInt(1, userID);
            p.setString(2, code);
            ResultSet r = p.executeQuery();
            if (r.next()) {
                verify = true;
            }
            r.close();
            p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return verify;
    }
}
