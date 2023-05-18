
package test;

import Connection.DBpendataan;
import swing.Button;
import swing.MyPasswordField;
import swing.MyTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;
import test.Main;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    String username;
    String password;
    String email;
    DBpendataan con;
    public PanelLoginAndRegister() {
        initComponents();
        initRegister();
        initLogin();
        login.setVisible(false);
        register.setVisible(true);
        con = new DBpendataan();
    }
     public Boolean checkSignup(String email) throws SQLException{
        String cekQuery = "select * from loginregister";
        try{
            ResultSet rs = DBpendataan.con.createStatement().executeQuery(cekQuery);
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
     
       public Boolean check(String email, String password) throws SQLException{
        String cekQuery = "select * from loginregister";
        try{
            ResultSet rs = DBpendataan.con.createStatement().executeQuery(cekQuery);
            while(rs.next()){
                if(email.equals(rs.getString("Email")) && password.equals(rs.getString("Password"))){
                    return true;
                }
            }
        }catch(SQLException e){
            System.out.print(e.toString());
        }
        return false;
        
    }

    private void initRegister() {
        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(0, 204, 255));
        register.add(label);
        MyTextField txtUser = new MyTextField();
        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/user.png")));
        txtUser.setHint("Name");
        register.add(txtUser, "w 60%");
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/mail.png")));
        txtEmail.setHint("Email");
        register.add(txtEmail, "w 60%");
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtPass.setHint("Password");
        register.add(txtPass, "w 60%");
        Button cmd = new Button();
        cmd.setBackground(new Color(0, 204, 255));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN UP");
        register.add(cmd, "w 40%, h 40");
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               username = txtUser.getText();
        password = txtPass.getText();
        email = txtEmail.getText();
//        if (!animatorLogin.isRunning()) {
//            signUp = true;
            boolean action = true;
            if (username.equals("")) {
//                txtUser.setHelperText("Please input username");
                txtUser.grabFocus();
                action = false;
            }
            if (email.equals("")) {
//                textEmail.setHelperText("Please input email");
                txtEmail.grabFocus();
                action = false;
            }
            if (password.equals("")) {
//                textPass.setHelperText("Please input password");
                if (action) {
                    txtPass.grabFocus();
                }
                action = false;
            }
        
        if(password.length() < 8){
            JOptionPane.showMessageDialog(null, "Password Minimal 8 Karakter", "Warning", JOptionPane.ERROR_MESSAGE);
        }else{
            String cekQuery = "select * from loginregister where username = '"+username+"' AND email = '"+email+"'";
            try{
             ResultSet rs = DBpendataan.con.createStatement().executeQuery(cekQuery);
                if(checkSignup(email) == true){
                    while(rs.next()){
                    rs.getString("username");
                    rs.getString("email");}
                    JOptionPane.showMessageDialog(null, "Username Dan Email Sudah Ada!",  "Warning", JOptionPane.WARNING_MESSAGE);
                }
                     String query = "INSERT INTO `loginregister`(`username`, `email`, `password`) "
                                + "VALUES ('"+username+"','"+email+"','"+password+"')";
                    if(query.contains("@")){
                    try{
                        
                        query.contains("@");
                        con.getSt().execute(query);
                         JOptionPane.showMessageDialog(null, "Register Berhasil");
                    }catch(SQLException ex){
                        JOptionPane.showMessageDialog(null, "Register  Gagal");
                    
                    }
                }else{
                       JOptionPane.showMessageDialog(null, "Email Tidak Mengandung @!",  "Warning", JOptionPane.WARNING_MESSAGE);

                }
                    
                    
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Error");
                System.out.print(ex.toString());
            }
        }
            }
        });
    }
                
    

    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(0, 204, 255));
        login.add(label);
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/mail.png")));
        txtEmail.setHint("Email");
        login.add(txtEmail, "w 60%");
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtPass.setHint("Password");
        login.add(txtPass, "w 60%");
        JButton cmdForget = new JButton("Forgot your password ?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdForget);
        Button cmd = new Button();
        cmd.setBackground(new Color(0, 204, 255));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN IN");
        login.add(cmd, "w 40%, h 40");
         cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                email = txtEmail.getText().trim();
                password = String.valueOf(txtPass.getPassword());
//                username =txtUser.getText();
        String cekQuery = "select * from loginregister";
            try {
                ResultSet rs = DBpendataan.con.createStatement().executeQuery(cekQuery);
                    if(check(email, password) == true){
                        String nama = "" ;
                        while(rs.next()){
                            nama = rs.getString("username");
                        }
                                    boolean action = true;
                     
                                    if (email.equals("")) {
                                        txtEmail.grabFocus();
                                        action = false;
                                    }
                                    if (password.equals("")) {
                                        if (action) {
                                            txtPass.grabFocus();
                                        }
                                        action = false;
                                    }
                                    Main log = new Main();
                                    log.setVisible(true);
                    }else if(check(email, password) == false){
                    JOptionPane.showMessageDialog(null, "Masukkan email dan password dengan benar", "Login", JOptionPane.ERROR_MESSAGE);
                    }
            } catch (SQLException ex) {
                System.out.print(ex.toString());
            }
            }
        });
    }

    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
