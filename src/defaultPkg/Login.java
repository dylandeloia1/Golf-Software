package defaultPkg;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Login extends JFrame{
    private JPanel rootPanelLogin;
    private JButton buttonLogin, buttonRegister;
    private JTextField textFieldEmail, textFieldPassword;
    private ClubMember clubMember;
    static Calendar calendar = Calendar.getInstance();
    static int dataDisplayIndex;

    public Login() {
        this.setContentPane(rootPanelLogin);
        this.setTitle("Golf Club - Log In");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setSize(400,275);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        // new reg window
        this.buttonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeLogin();
                new Register();
            }
        });

        // checks user auth data for login
        this.buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textFieldEmail.getText().equals("") || textFieldPassword.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Please enter username and password!");
                } else {
                    String email = textFieldEmail.getText();
                    String password = textFieldPassword.getText();
                    boolean login = false;
                    File file = new File("src//user_auth.csv");
                    Scanner inputStream;
                    try {
                        inputStream = new Scanner(file);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    while (inputStream.hasNext()){
                        String data = inputStream.nextLine();
                        String[] line = data.split(",");
                        if(line[0].equals(email) && line[1].equals(password)){
                            login = true;
                            clubMember = new ClubMember(line[0],line[1],line[2],Integer.valueOf(line[3]),line[4],line[5],Integer.valueOf(line[6]));
                            break;
                        }
                    }
                    if(login == true) {
                        closeLogin();
                        calendar.setTime(new Date());
                        dataDisplayIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                        Dashboard dashboard = Dashboard.getInstance();
                        dashboard.update(clubMember,Main.weeklyData);
                    } else {
                        JOptionPane.showMessageDialog(getParent(),"Login Failed");
                        textFieldPassword.setText("");
                    }
                }
            }
        });
    }

    public void closeLogin(){
        this.dispose();
    }
}
