package defaultPkg;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Reserve extends JFrame {
    private JPanel rootPanelReserve;
    private ClubMember loggedInMember;
    private JButton addButton0,addButton1,addButton2,addButton3,buttonReserve,resetButton;
    private JLabel jLabelTime,jLabel0,jLabel1,jLabel2,jLabel3;
    private JTextField textField0,textField1,textField2,textField3;
    private JRadioButton rideRadioButton0,rideRadioButton1,rideRadioButton2,rideRadioButton3;
    private JRadioButton walkRadioButton0,walkRadioButton1,walkRadioButton2,walkRadioButton3;
    private ButtonGroup buttonGroup0 = new ButtonGroup();
    private ButtonGroup buttonGroup1 = new ButtonGroup();
    private ButtonGroup buttonGroup2 = new ButtonGroup();
    private ButtonGroup buttonGroup3 = new ButtonGroup();

    public Reserve(ClubMember loggedInMember, String key) {
        this.setContentPane(rootPanelReserve);
        this.setTitle("Golf Club - Reservations");
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        this.setSize(1000, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.loggedInMember = loggedInMember;
        jLabelTime.setText(key + ":");

        // reset UI with same params
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeReserve();
                new Reserve(loggedInMember,key);
            }
        });

        buttonGroup0.add(rideRadioButton0);
        buttonGroup0.add(walkRadioButton0);
        buttonGroup1.add(rideRadioButton1);
        buttonGroup1.add(walkRadioButton1);
        buttonGroup2.add(rideRadioButton2);
        buttonGroup2.add(walkRadioButton2);
        buttonGroup3.add(rideRadioButton3);
        buttonGroup3.add(walkRadioButton3);

        // disables all elements
        disableElements();

        /*
        data.get(Login.dataDisplayIndex) returns the TeeSheetData obj for the correct day
        data.get(Login.dataDisplayIndex).get(key) returns the ArrayList<Golfer> obj at the correct location
        so this integer value here: data.get(Login.dataDisplayIndex).get(key).size()
        ...  is the number of golfers in the selected time slot
        This switch statement takes care of the UI elements that should be showing and enabled for a selected time slot
        ...  based on the number of golfers already in the slot
        */
        switch (Main.weeklyData.get(Login.dataDisplayIndex).get(key).size()) {
            case 0:
                addButton0.setVisible(false);
                addButton0.setEnabled(false);
                textField0.setVisible(true);
                textField0.setText(loggedInMember.getName());
                rideRadioButton0.setEnabled(true);
                rideRadioButton0.setVisible(true);
                rideRadioButton0.setSelected(true);
                walkRadioButton0.setEnabled(true);
                walkRadioButton0.setVisible(true);
                addButton1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textField1.setVisible(true);
                        textField1.setEnabled(true);
                        textField1.setText(loggedInMember.getName() + "'s Guest");
                        rideRadioButton1.setVisible(true);
                        rideRadioButton1.setEnabled(true);
                        walkRadioButton1.setVisible(true);
                        walkRadioButton1.setEnabled(true);
                        rideRadioButton1.setSelected(true);
                        addButton1.setEnabled(false);
                        addButton1.setVisible(false);
                    }
                });
                addButton2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textField2.setVisible(true);
                        textField2.setEnabled(true);
                        textField2.setText(loggedInMember.getName() + "'s Guest");
                        rideRadioButton2.setVisible(true);
                        rideRadioButton2.setEnabled(true);
                        walkRadioButton2.setVisible(true);
                        walkRadioButton2.setEnabled(true);
                        rideRadioButton2.setSelected(true);
                        addButton2.setEnabled(false);
                        addButton2.setVisible(false);
                    }
                });
                addButton3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textField3.setVisible(true);
                        textField3.setEnabled(true);
                        textField3.setText(loggedInMember.getName() + "'s Guest");
                        rideRadioButton3.setVisible(true);
                        rideRadioButton3.setEnabled(true);
                        walkRadioButton3.setVisible(true);
                        walkRadioButton3.setEnabled(true);
                        rideRadioButton3.setSelected(true);
                        addButton3.setEnabled(false);
                        addButton3.setVisible(false);
                    }
                });
                break;
            case 1:
                jLabel0.setText(Main.weeklyData.get(Login.dataDisplayIndex).get(key).get(0).getName());
                jLabel0.setVisible(true);
                addButton0.setVisible(false);
                addButton0.setEnabled(false);
                addButton1.setVisible(false);
                addButton1.setEnabled(false);
                textField1.setVisible(true);
                textField1.setText(loggedInMember.getName());
                rideRadioButton1.setEnabled(true);
                rideRadioButton1.setVisible(true);
                rideRadioButton1.setSelected(true);
                walkRadioButton1.setEnabled(true);
                walkRadioButton1.setVisible(true);
                addButton2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textField2.setVisible(true);
                        textField2.setEnabled(true);
                        textField2.setText(loggedInMember.getName() + "'s Guest");
                        rideRadioButton2.setVisible(true);
                        rideRadioButton2.setEnabled(true);
                        walkRadioButton2.setVisible(true);
                        walkRadioButton2.setEnabled(true);
                        rideRadioButton2.setSelected(true);
                        addButton2.setEnabled(false);
                        addButton2.setVisible(false);
                    }
                });
                addButton3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textField3.setVisible(true);
                        textField3.setEnabled(true);
                        textField3.setText(loggedInMember.getName() + "'s Guest");
                        rideRadioButton3.setVisible(true);
                        rideRadioButton3.setEnabled(true);
                        walkRadioButton3.setVisible(true);
                        walkRadioButton3.setEnabled(true);
                        rideRadioButton3.setSelected(true);
                        addButton3.setEnabled(false);
                        addButton3.setVisible(false);
                    }
                });
                break;
            case 2:
                jLabel0.setText(Main.weeklyData.get(Login.dataDisplayIndex).get(key).get(0).getName());
                jLabel0.setVisible(true);
                jLabel1.setText(Main.weeklyData.get(Login.dataDisplayIndex).get(key).get(1).getName());
                jLabel1.setVisible(true);
                addButton0.setVisible(false);
                addButton0.setEnabled(false);
                addButton1.setVisible(false);
                addButton1.setEnabled(false);
                addButton2.setVisible(false);
                addButton2.setEnabled(false);
                textField2.setVisible(true);
                textField2.setText(loggedInMember.getName());
                rideRadioButton2.setEnabled(true);
                rideRadioButton2.setVisible(true);
                rideRadioButton2.setSelected(true);
                walkRadioButton2.setEnabled(true);
                walkRadioButton2.setVisible(true);
                addButton3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textField3.setVisible(true);
                        textField3.setEnabled(true);
                        textField3.setText(loggedInMember.getName() + "'s Guest");
                        rideRadioButton3.setVisible(true);
                        rideRadioButton3.setEnabled(true);
                        walkRadioButton3.setVisible(true);
                        walkRadioButton3.setEnabled(true);
                        rideRadioButton3.setSelected(true);
                        addButton3.setEnabled(false);
                        addButton3.setVisible(false);
                    }
                });
                break;
            case 3:
                jLabel0.setText(Main.weeklyData.get(Login.dataDisplayIndex).get(key).get(0).getName());
                jLabel0.setVisible(true);
                jLabel1.setText(Main.weeklyData.get(Login.dataDisplayIndex).get(key).get(1).getName());
                jLabel1.setVisible(true);
                jLabel2.setText(Main.weeklyData.get(Login.dataDisplayIndex).get(key).get(2).getName());
                jLabel2.setVisible(true);
                addButton0.setVisible(false);
                addButton0.setEnabled(false);
                addButton1.setVisible(false);
                addButton1.setEnabled(false);
                addButton2.setVisible(false);
                addButton2.setEnabled(false);
                addButton3.setEnabled(false);
                addButton3.setVisible(false);
                textField3.setVisible(true);
                textField3.setText(loggedInMember.getName());
                rideRadioButton3.setEnabled(true);
                rideRadioButton3.setVisible(true);
                rideRadioButton3.setSelected(true);
                walkRadioButton3.setEnabled(true);
                walkRadioButton3.setVisible(true);
                break;
        }

        buttonReserve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean ride0;
                switch(Main.weeklyData.get(Login.dataDisplayIndex).get(key).size()){
                    case 0:
                        if(buttonGroup0.getSelection().equals(rideRadioButton0.getModel())){ride0 = true;} else {ride0 = false;}
                        Main.weeklyData.get(Login.dataDisplayIndex).get(key).add(new Golfer(loggedInMember.getName(),ride0));
                        if(!textField1.getText().equals("")){
                            boolean ride1;
                            if(buttonGroup1.getSelection().equals(rideRadioButton1.getModel())){ride1 = true;} else {ride1 = false;}
                            Main.weeklyData.get(Login.dataDisplayIndex).get(key).add(new Golfer(textField1.getText(),ride1));
                        }
                        if(!textField2.getText().equals("")) {
                            boolean ride2;
                            if(buttonGroup2.getSelection().equals(rideRadioButton2.getModel())){ride2 = true;} else {ride2 = false;}
                            Main.weeklyData.get(Login.dataDisplayIndex).get(key).add(new Golfer(textField2.getText(),ride2));
                        }
                        if(!textField3.getText().equals("")) {
                            boolean ride3;
                            if(buttonGroup3.getSelection().equals(rideRadioButton3.getModel())){ride3 = true;} else {ride3 = false;}
                            Main.weeklyData.get(Login.dataDisplayIndex).get(key).add(new Golfer(textField3.getText(),ride3));
                        }
                        break;
                    case 1:
                        if(buttonGroup1.getSelection().equals(rideRadioButton1.getModel())){ride0 = true;} else {ride0 = false;}
                        Main.weeklyData.get(Login.dataDisplayIndex).get(key).add(new Golfer(loggedInMember.getName(),ride0));
                        if(!textField2.getText().equals("")) {
                            boolean ride2;
                            if(buttonGroup2.getSelection().equals(rideRadioButton2.getModel())){ride2 = true;} else {ride2 = false;}
                            Main.weeklyData.get(Login.dataDisplayIndex).get(key).add(new Golfer(textField2.getText(),ride2));
                        }
                        if(!textField3.getText().equals("")) {
                            boolean ride3;
                            if(buttonGroup3.getSelection().equals(rideRadioButton3.getModel())){ride3 = true;} else {ride3 = false;}
                            Main.weeklyData.get(Login.dataDisplayIndex).get(key).add(new Golfer(textField3.getText(),ride3));
                        }
                        break;
                    case 2:
                        if(buttonGroup2.getSelection().equals(rideRadioButton2.getModel())){ride0 = true;} else {ride0 = false;}
                        Main.weeklyData.get(Login.dataDisplayIndex).get(key).add(new Golfer(loggedInMember.getName(),ride0));
                        if(!textField3.getText().equals("")) {
                            boolean ride3;
                            if(buttonGroup3.getSelection().equals(rideRadioButton3.getModel())){ride3 = true;} else {ride3 = false;}
                            Main.weeklyData.get(Login.dataDisplayIndex).get(key).add(new Golfer(textField3.getText(),ride3));
                        }
                        break;
                    case 3:
                        if(buttonGroup3.getSelection().equals(rideRadioButton3.getModel())){ride0 = true;} else {ride0 = false;}
                        Main.weeklyData.get(Login.dataDisplayIndex).get(key).add(new Golfer(loggedInMember.getName(),ride0));
                        break;
                }
                closeReserve();
                DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy kk:mm:ss", Locale.US);
                String dateOfGolf = df1.format(Dashboard.displayDate);
                String dateTimeOfRes = df2.format(new Date());
                saveGolferReservation(dateTimeOfRes,dateOfGolf,Map.entry(key,Main.weeklyData.get(Login.dataDisplayIndex).get(key)));
                saveTeeSheetData(Main.weeklyData.get(Login.dataDisplayIndex));
                Dashboard dashboard = Dashboard.getInstance();
                dashboard.update(loggedInMember,Main.weeklyData);
            }
        });

        // show reserve window
        this.setVisible(true);
    }

    // saves reservation to csv
    public void saveGolferReservation(String dateTimeOfRes, String dateOfGolf, Map.Entry<String,ArrayList<Golfer>> reservation){
        try{
            File file = new File("src//reservation_data.csv");
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            printWriter.print(loggedInMember.getMemberNum() + ",");
            printWriter.print(dateTimeOfRes + ",");
            printWriter.print(dateOfGolf + ",");
            printWriter.print(reservation.getKey() + ",");
            printWriter.print(reservation.getValue().size() + ",");
            for(int i = 0; i < reservation.getValue().size(); i++){
                if(i == reservation.getValue().size() - 1) {
                    printWriter.println(reservation.getValue().get(i).getName() + "," + reservation.getValue().get(i).isRide());
                } else{
                    printWriter.print(reservation.getValue().get(i).getName() + "," + reservation.getValue().get(i).isRide() + ",");
                }
            }
            printWriter.flush();
            printWriter.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    // saves data to csv
    public void saveTeeSheetData(TeeSheetData data){
        String day = null;
        if(Login.dataDisplayIndex == 0){
            day = "sunday";
        } else if(Login.dataDisplayIndex == 1){
            day = "monday";
        } else if(Login.dataDisplayIndex == 2){
            day = "tuesday";
        } else if(Login.dataDisplayIndex == 3){
            day = "wednesday";
        } else if(Login.dataDisplayIndex == 4){
            day = "thursday";
        } else if(Login.dataDisplayIndex == 5){
            day = "friday";
        } else if(Login.dataDisplayIndex == 6){
            day = "saturday";
        } else {
            System.out.println("day index error");
        }
        try {
            FileWriter fileWriter = new FileWriter("src//" + day + "_tee_times.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            for(Map.Entry<String,ArrayList<Golfer>> entry : data.entrySet()){
                if(entry.getValue().size() == 0){
                    printWriter.println(entry.getKey() + ",");
                } else{
                    printWriter.print(entry.getKey() + ",");
                }
                for(int i = 0; i < entry.getValue().size(); i++){
                    if(i == (entry.getValue().size() - 1)) {
                        printWriter.println(entry.getValue().get(i).getName());
                    } else{
                        printWriter.print(entry.getValue().get(i).getName() + ",");
                    }
                }
            }
            printWriter.flush();
            printWriter.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void disableElements(){
        jLabel0.setVisible(false);
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jLabel0.setEnabled(false);
        jLabel1.setEnabled(false);
        jLabel2.setEnabled(false);
        jLabel3.setEnabled(false);
        textField0.setVisible(false);
        textField1.setVisible(false);
        textField2.setVisible(false);
        textField3.setVisible(false);
        textField0.setEnabled(false);
        textField1.setEnabled(false);
        textField2.setEnabled(false);
        textField3.setEnabled(false);
        rideRadioButton0.setVisible(false);
        walkRadioButton0.setVisible(false);
        rideRadioButton1.setVisible(false);
        walkRadioButton1.setVisible(false);
        rideRadioButton2.setVisible(false);
        walkRadioButton2.setVisible(false);
        rideRadioButton3.setVisible(false);
        walkRadioButton3.setVisible(false);
        rideRadioButton0.setEnabled(false);
        walkRadioButton0.setEnabled(false);
        rideRadioButton1.setEnabled(false);
        walkRadioButton1.setEnabled(false);
        rideRadioButton2.setEnabled(false);
        walkRadioButton2.setEnabled(false);
        rideRadioButton3.setEnabled(false);
        walkRadioButton3.setEnabled(false);
    }

    public void closeReserve(){
        this.dispose();
    }
}
