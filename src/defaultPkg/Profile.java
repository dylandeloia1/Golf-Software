package defaultPkg;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import java.util.List;

public class Profile extends JFrame {
    private JPanel rootPanelProfile;
    private JTable tableReservations;
    private JLabel jLabelName,jLableMemberNumber,jLabelEmail,jLabelPhoneNum,jLabelGender,jLabelAge;
    private JButton backButtonProfile,buttonReceipts;
    private ClubMember loggedInMember;
    private ArrayList<ReceiptData> receipts = new ArrayList<>();

    public Profile(ClubMember clubMember) {
        this.loggedInMember = clubMember;
        this.setContentPane(rootPanelProfile);
        this.setTitle("Golf Club - Profile");
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        this.setSize(1000, 825);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        // setup profile
        this.jLabelName.setText(clubMember.getName());
        this.jLableMemberNumber.setText(String.valueOf(clubMember.getMemberNum()));
        this.jLabelPhoneNum.setText(clubMember.getPhoneNumber());
        this.jLabelEmail.setText(clubMember.getEmail());
        this.jLabelAge.setText(String.valueOf(clubMember.getAge()));
        this.jLabelGender.setText(clubMember.getGender());

        // create my reservation table
        this.createTable();

        // close profile window
        this.backButtonProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeMyProfile();
            }
        });

        // show receipt for selected res
        this.buttonReceipts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tableReservations.getSelectedRow();
                ReceiptData receiptData = loggedInMember.getReceipts().get(index);
                new Receipt(receiptData);
            }
        });

        // enable show receipt button only when there is a selection
        buttonReceipts.setEnabled(false);
        ListSelectionModel listSelectionModel = tableReservations.getSelectionModel();
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!listSelectionModel.isSelectionEmpty()){
                    buttonReceipts.setEnabled(true);
                }
            }
        });

        // show profile window
        this.setVisible(true);
    }

    public void createTable(){
        // create table model and set each cell to be uneditable
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Date", "Time", "", "", "", ""}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // reads reservation data csv and adds row if member number match entry
        File file = new File("src//reservation_data.csv");
        try {
            Scanner inputStream = new Scanner(file);
            while(inputStream.hasNext()){
                String line = inputStream.nextLine();
                String[] values = line.split(",");
                if(Integer.valueOf(values[0]).equals(loggedInMember.getMemberNum())){
                    Object[] row = new Object[2 + Integer.valueOf(values[4])];
                    row[0] = values[2];
                    row[1] = values[3];
                    ArrayList<Golfer> golfers = new ArrayList<>();
                    int index = 2;
                    for(int i = 0; i < (2*Integer.valueOf(values[4])); i += 2){
                        golfers.add(new Golfer(values[5+i],Boolean.valueOf(values[6+i])));
                        row[index] = values[5+i];
                        index++;
                    }
                    ReceiptData receiptData = new ReceiptData(values[1],values[2],values[3],golfers);
                    receipts.add(receiptData);
                    loggedInMember.setReceipts(receipts);
                    model.addRow(row);
                }
            }
        } catch (Exception e){
            System.out.println("Exception: " + e);
        }

        // set table model
        tableReservations.setModel(model);
        tableReservations.setRowHeight(50);

        // set time and date columns width smaller
        TableColumnModel columnModel = tableReservations.getColumnModel();
        columnModel.getColumn(0).setMinWidth(90);
        columnModel.getColumn(0).setMaxWidth(90);
        columnModel.getColumn(1).setMinWidth(75);
        columnModel.getColumn(1).setMaxWidth(75);

        // sorts table by time and date using TmeStringComparator class
        TableRowSorter rowSorter = new TableRowSorter(tableReservations.getModel());
        rowSorter.setComparator(0, new TimeStringComparator());
        rowSorter.setComparator(1, new TimeStringComparator());
        tableReservations.setRowSorter(rowSorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0,SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(1,SortOrder.ASCENDING));
        rowSorter.setSortKeys(sortKeys);

        // set single row selection properties
        tableReservations.setCellSelectionEnabled(false);
        tableReservations.setColumnSelectionAllowed(false);
        tableReservations.setRowSelectionAllowed(true);
        tableReservations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableReservations.setShowGrid(false);
        tableReservations.setIntercellSpacing(new Dimension(0,0));

        // set selected row color when selected
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (isSelected) {
                    table.setSelectionBackground(Color.decode("#bae0ff"));
                    table.setSelectionForeground(Color.black);
                } else {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : Color.decode("#f2f2f2")); // this makes every other row light grey
                }
                setBorder(noFocusBorder);
                return c;
            }
        };

        // set values in table to align center
        renderer.setHorizontalAlignment(JLabel.CENTER);
        tableReservations.setDefaultRenderer(Object.class, renderer);

        // set column headers to align center
        TableCellRenderer rendererFromHeader = tableReservations.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    public void closeMyProfile() {
        this.dispose();
    }
}
