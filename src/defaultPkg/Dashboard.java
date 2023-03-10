package defaultPkg;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Dashboard extends JFrame{
    public static Dashboard instance;
    private static ClubMember loggedInMember;
    private static ArrayList<TeeSheetData> data;
    public static Date displayDate;
    private static int todaysIndex;
    private static int displayIndex;
    private JPanel rootPanelDashboard;
    private JLabel jlabelTodaysDate;
    private JTable table;
    private JComboBox comboBoxDay;
    private JButton reserveATeeTimeButton,logoutButton,myProfileButton,buttonRefresh,jumpToTodayButton,buttonLeft,buttonRight;

    private Dashboard(){

    }

    // allows for Dashboard singleton design
    public static Dashboard getInstance(){
        if(instance == null){
            instance = new Dashboard();
            instance.setContentPane(instance.rootPanelDashboard);
            instance.setDefaultCloseOperation(instance.EXIT_ON_CLOSE);
            instance.setSize(1200,800);
            instance.setLocationRelativeTo(null);

            // if day is 0 or 6 set button functionality
            SimpleDateFormat df = new SimpleDateFormat("E, MMMM d, yyyy", Locale.US);
            Calendar displayCal = Calendar.getInstance();
            displayCal.setTime(new Date());
            todaysIndex = displayCal.get(Calendar.DAY_OF_WEEK) - 1;
            if(todaysIndex == 6){
                instance.buttonRight.setEnabled(false);
            } else if(todaysIndex == 0){
                instance.buttonLeft.setEnabled(false);
            } else{
                System.out.println("Directional button error");
            }

            // setup days combo box
            instance.comboBoxDay.insertItemAt("Sunday",0);
            instance.comboBoxDay.insertItemAt("Monday",1);
            instance.comboBoxDay.insertItemAt("Tuesday",2);
            instance.comboBoxDay.insertItemAt("Wednesday",3);
            instance.comboBoxDay.insertItemAt("Thursday",4);
            instance.comboBoxDay.insertItemAt("Friday",5);
            instance.comboBoxDay.insertItemAt("Saturday",6);
            instance.comboBoxDay.setSelectedIndex(Login.dataDisplayIndex);
            instance.comboBoxDay.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(instance.comboBoxDay.getSelectedIndex() == 6){
                        instance.buttonRight.setEnabled(false);
                    } else{
                        instance.buttonRight.setEnabled(true);
                    }
                    if(instance.comboBoxDay.getSelectedIndex() == 0){
                        instance.buttonLeft.setEnabled(false);
                    } else{
                        instance.buttonLeft.setEnabled(true);
                    }
                    Login.dataDisplayIndex = instance.comboBoxDay.getSelectedIndex();
                    instance.update(instance.loggedInMember,instance.data);
                }
            });

            // scroll data right
            instance.buttonRight.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    instance.buttonLeft.setEnabled(true);
                    Login.dataDisplayIndex++;
                    if(Login.dataDisplayIndex == 6){
                        instance.buttonRight.setEnabled(false);
                    }
                    instance.comboBoxDay.setSelectedIndex(Login.dataDisplayIndex);
                    instance.update(instance.loggedInMember,instance.data);
                }
            });

            // scroll data left
            instance.buttonLeft.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    instance.buttonRight.setEnabled(true);
                    Login.dataDisplayIndex--;
                    if(Login.dataDisplayIndex == 0){
                        instance.buttonLeft.setEnabled(false);
                    }
                    instance.comboBoxDay.setSelectedIndex(Login.dataDisplayIndex);
                    instance.update(instance.loggedInMember,instance.data);
                }
            });

            // goto today's tee sheet data
            instance.jumpToTodayButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Login.calendar.setTime(new Date());
                    Login.dataDisplayIndex = Login.calendar.get(Calendar.DAY_OF_WEEK) - 1;
                    instance.comboBoxDay.setSelectedIndex(Login.dataDisplayIndex);
                    instance.update(instance.loggedInMember, instance.data);
                }
            });

            // new reserve window
            instance.reserveATeeTimeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(instance.table.getSelectedRow() == -1){
                        JOptionPane.showMessageDialog(null,"Please select a tee time!","Invalid Selection",JOptionPane.WARNING_MESSAGE);
                    } else{
                        String key = (String) instance.table.getValueAt(instance.table.getSelectedRow(),0);
                        if(data.get(Login.dataDisplayIndex).get(key).size() == 4){
                            JOptionPane.showMessageDialog(null,"This tee time is FULL!","Please pick a different time",JOptionPane.WARNING_MESSAGE);
                        }else {
                            new Reserve(instance.loggedInMember,key);
                        }
                    }
                }
            });

            // logout
            instance.logoutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    closeDash();
                    new Login();
                }
            });

            // new my profile window
            instance.myProfileButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Profile(instance.loggedInMember);
                }
            });

            // same as jump to today button/refreshes data
            instance.buttonRefresh.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Login.calendar.setTime(new Date());
                    Login.dataDisplayIndex = Login.calendar.get(Calendar.DAY_OF_WEEK) - 1;
                    instance.comboBoxDay.setSelectedIndex(Login.dataDisplayIndex);
                    instance.update(instance.loggedInMember, instance.data);
                }
            });
        }
        return instance;
    }

    public static void update(ClubMember loggedInMember, ArrayList<TeeSheetData> data) {
        // set welcome title
        instance.loggedInMember = loggedInMember;
        instance.setTitle("Golf Club Dashboard - Welcome " + loggedInMember.getName());

        // set today's date label
        SimpleDateFormat df = new SimpleDateFormat("E, MMMM d, yyyy", Locale.US);
        Calendar displayCal = Calendar.getInstance();
        displayCal.setTime(new Date());
        todaysIndex = displayCal.get(Calendar.DAY_OF_WEEK) - 1;
        displayIndex = Login.dataDisplayIndex - todaysIndex;
        displayCal.add(Calendar.DATE,displayIndex);
        displayDate = displayCal.getTime();
        instance.jlabelTodaysDate.setText(df.format(displayDate));

        // create table
        instance.data = data;
        TableModel tableModel = toTableModel(data.get(Login.dataDisplayIndex));  // converts hashmap data to jtable model
        createTable(tableModel);  // creates jtable with model

        // show dash window
        instance.setVisible(true);
    }

    // create table gets a table model and creates the UI for the table
    public static void createTable(TableModel model) {
        instance.table.setModel(model); // set model from param
        instance.table.setRowHeight(40);  // set row height
        // set time column width smaller
        TableColumnModel columnModel = instance.table.getColumnModel();
        columnModel.getColumn(0).setMaxWidth(75);
        // sorts table by time using TmeStringComparator class
        TableRowSorter rowSorter = new TableRowSorter(instance.table.getModel());
        rowSorter.setComparator(0, new TimeStringComparator());
        instance.table.setRowSorter(rowSorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0,SortOrder.ASCENDING));
        rowSorter.setSortKeys(sortKeys);
        // set selection properties
        instance.table.setCellSelectionEnabled(false);
        instance.table.setColumnSelectionAllowed(false);
        instance.table.setRowSelectionAllowed(true);
        instance.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        instance.table.setShowGrid(false);
        instance.table.setIntercellSpacing(new Dimension(0,0));
        // set selected row color and bigger bold font when selected
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBorder(noFocusBorder);
                if(isSelected){
                    table.setSelectionBackground(Color.decode("#bae0ff"));
                    table.setSelectionForeground(Color.black);
                    c.setFont(new Font("Arial", Font.BOLD,16));
                } else{
                    c.setFont(new Font("Arial", Font.PLAIN,12));
                    c.setBackground(row % 2 == 0 ? Color.WHITE: Color.decode("#f2f2f2")); // this makes every other row light grey
                }
                return  c;
            }
        };
        // set values in table to align center
        renderer.setHorizontalAlignment(JLabel.CENTER);
        instance.table.setDefaultRenderer(Object.class, renderer);
        // set column headers to align center
        TableCellRenderer rendererFromHeader = instance.table.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        // make selected row height larger
        ListSelectionModel selectionModel = instance.table.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!selectionModel.isSelectionEmpty()){
                    instance.table.setRowHeight(40);
                    int index = selectionModel.getAnchorSelectionIndex();
                    instance.table.setRowHeight(index,120);
                }
            }
        });
    }

    // converts Hashmap data to table model and returns the model
    public static TableModel toTableModel(HashMap<String, ArrayList<Golfer>> map) {
        // create table model and set each cell to be uneditable
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Time", "", "", "", ""}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        // this for loop iterates over the current map entries (key value pairs of <String, ArrayList<Golfer>>)
        for (Map.Entry<String, ArrayList<Golfer>> entry : map.entrySet()) {
            Object[] row = new Object[entry.getValue().size() + 1];
            row[0] = entry.getKey();
            // this for loop creates a row of the table depending on the size of the ArrayList<Golfer> value in each key value pair
            for(int i = 0; i < entry.getValue().size(); i++){
                row[i+1] = entry.getValue().get(i).getName();
            }
            model.addRow(row);
        }
        return model;
    }

    private static void closeDash() {
        instance.dispose();
        instance = null;
    }
}
