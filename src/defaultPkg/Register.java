package defaultPkg;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Scanner;

public class Register extends JFrame {
    private JPanel rootPanelRegister;
    private JTextField textFieldName, textFieldAge, textFieldEmail, textFieldPassword, textFieldPhoneNum;
    private ButtonGroup buttonGroup;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private JButton submitButton, backButton;

    public Register() {
        this.setContentPane(rootPanelRegister);
        this.setTitle("Golf Club - Registration");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setSize(500,600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        // initialize buttongroup with radio buttons
        buttonGroup = new ButtonGroup();
        buttonGroup.add(maleRadioButton);
        buttonGroup.add(femaleRadioButton);

        // this key listener only allows you to type integers into the age textfield
        this.textFieldAge.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!Character.isDigit(c)){
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        // this key listener only allows you to type integers into the phone number textfield
        this.textFieldPhoneNum.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!Character.isDigit(c)){
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        this.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeReg();
                new Login();
            }
        });

        this.submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // if statement checks if all fields are entered
                if (textFieldName.getText().equals("") || textFieldAge.getText().equals("") || textFieldEmail.getText().equals("") || textFieldPhoneNum.getText().equals("") || textFieldPassword.getText().equals("") || buttonGroup.getSelection() == null) {
                    JOptionPane.showMessageDialog(getParent(), "Please enter all required fields!");
                } else {
                    String gender;
                    if(buttonGroup.getSelection().equals(maleRadioButton.getModel())){
                        gender = "male";
                    } else {
                        gender = "female";
                    }

                    ClubMember clubMember = new ClubMember(
                            textFieldEmail.getText(),
                            textFieldPassword.getText(),
                            textFieldName.getText(),
                            Integer.valueOf(textFieldAge.getText()),
                            gender,
                            textFieldPhoneNum.getText(),
                            getNextMemberNum());

                    // write new golfer object data to CSV
                    try {
                        FileWriter fileWriter = new FileWriter("src//user_auth.csv", true);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        PrintWriter printWriter = new PrintWriter(bufferedWriter);
                        printWriter.println(clubMember.toString());
                        printWriter.flush();
                        printWriter.close();
                        closeReg();
                        new Login();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        // show reg window
        this.setVisible(true);
    }

    // get next member num by increment
    public int getNextMemberNum() {
        int lastMemberNum = 998;
        try {
            File file = new File("src//user_auth.csv");
            Scanner inputStream = new Scanner(file);
            while (inputStream.hasNext()) {
                String line = inputStream.nextLine();
                String[] values = line.split(",");
                for (String value : values) {
                    lastMemberNum = Integer.valueOf(values[6]);
                }
            }
        } catch(Exception e){
                System.out.println(e.getMessage());
            }
        return lastMemberNum + 1;
    }

    public void closeReg(){
        this.dispose();
    }
}
