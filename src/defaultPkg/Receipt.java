package defaultPkg;

import javax.swing.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Receipt extends JFrame{
    private JPanel rootPanelReceipt;
    private JLabel jLabelDateTime,jLabelNumItems;
    private JLabel jLabelGolfer0,jLabelGolfer1,jLabelGolfer2,jLabelGolfer3;
    private JLabel jLabelRide0,jLabelRide1,jLabelRide2,jLabelRide3;
    private JLabel jLabelWalk0,jLabelWalk1,jLabelWalk2,jLabelWalk3;
    private JLabel jLabelRidePrice0,jLabelRidePrice1,jLabelRidePrice2,jLabelRidePrice3;
    private JLabel jLabelWalkPrice0,jLabelWalkPrice1,jLabelWalkPrice2,jLabelWalkPrice3;
    private JLabel jLabelTot,jLabelTax,jLabelSubTot;
    private double subTot, tax, total;
    private NumberFormat formatter = new DecimalFormat("#0.00");

    public Receipt(ReceiptData receiptData) {
        this.setContentPane(rootPanelReceipt);
        this.setTitle("Golf Club - Receipt");
        this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        // set date time and num items
        jLabelDateTime.setText(receiptData.getDateTimeOfRes());
        jLabelNumItems.setText("# ITEMS: " + receiptData.getGolfers().size());

        // counters for total price calc
        int rideCount = 0;
        int walkCount = 0;

        // creates receipt based on num golfers and walking/riding
        int numGolfers = receiptData.getGolfers().size();
        switch(numGolfers) {
            case 1:
                jLabelGolfer0.setText(receiptData.getGolfers().get(0).getName());
                if (receiptData.getGolfers().get(0).isRide()) {
                    jLabelWalk0.setVisible(false);
                    jLabelWalk0.setEnabled(false);
                    jLabelWalkPrice0.setVisible(false);
                    jLabelWalkPrice0.setEnabled(false);
                    rideCount++;
                } else {
                    jLabelRide0.setVisible(false);
                    jLabelRide0.setEnabled(false);
                    jLabelRidePrice0.setVisible(false);
                    jLabelRidePrice0.setEnabled(false);
                    walkCount++;
                }

                jLabelGolfer1.setVisible(false);
                jLabelGolfer1.setEnabled(false);
                jLabelRide1.setVisible(false);
                jLabelRide1.setEnabled(false);
                jLabelWalk1.setVisible(false);
                jLabelWalk1.setEnabled(false);
                jLabelRidePrice1.setVisible(false);
                jLabelRidePrice1.setEnabled(false);
                jLabelWalkPrice1.setVisible(false);
                jLabelWalkPrice1.setEnabled(false);

                jLabelGolfer2.setVisible(false);
                jLabelGolfer2.setEnabled(false);
                jLabelRide2.setVisible(false);
                jLabelRide2.setEnabled(false);
                jLabelWalk2.setVisible(false);
                jLabelWalk2.setEnabled(false);
                jLabelRidePrice2.setVisible(false);
                jLabelRidePrice2.setEnabled(false);
                jLabelWalkPrice2.setVisible(false);
                jLabelWalkPrice2.setEnabled(false);

                jLabelGolfer3.setVisible(false);
                jLabelGolfer3.setEnabled(false);
                jLabelRide3.setVisible(false);
                jLabelRide3.setEnabled(false);
                jLabelWalk3.setVisible(false);
                jLabelWalk3.setEnabled(false);
                jLabelRidePrice3.setVisible(false);
                jLabelRidePrice3.setEnabled(false);
                jLabelWalkPrice3.setVisible(false);
                jLabelWalkPrice3.setEnabled(false);

                // Calculate totals
                subTot = (rideCount * 55) + (walkCount * 32);
                tax = subTot * .075;
                total = subTot + tax;
                jLabelSubTot.setText(String.valueOf(formatter.format(subTot)));
                jLabelTax.setText(String.valueOf(formatter.format(tax)));
                jLabelTot.setText(String.valueOf(formatter.format(total)));

                this.setSize(350, 475);
                break;

            case 2:
                jLabelGolfer0.setText(receiptData.getGolfers().get(0).getName());
                if (receiptData.getGolfers().get(0).isRide()) {
                    jLabelWalk0.setVisible(false);
                    jLabelWalk0.setEnabled(false);
                    jLabelWalkPrice0.setVisible(false);
                    jLabelWalkPrice0.setEnabled(false);
                    rideCount++;
                } else {
                    jLabelRide0.setVisible(false);
                    jLabelRide0.setEnabled(false);
                    jLabelRidePrice0.setVisible(false);
                    jLabelRidePrice0.setEnabled(false);
                    walkCount++;
                }

                jLabelGolfer1.setText(receiptData.getGolfers().get(1).getName());
                if (receiptData.getGolfers().get(1).isRide()) {
                    jLabelWalk1.setVisible(false);
                    jLabelWalk1.setEnabled(false);
                    jLabelWalkPrice1.setVisible(false);
                    jLabelWalkPrice1.setEnabled(false);
                    rideCount++;
                } else {
                    jLabelRide1.setVisible(false);
                    jLabelRide1.setEnabled(false);
                    jLabelRidePrice1.setVisible(false);
                    jLabelRidePrice1.setEnabled(false);
                    walkCount++;
                }

                jLabelGolfer2.setVisible(false);
                jLabelGolfer2.setEnabled(false);
                jLabelRide2.setVisible(false);
                jLabelRide2.setEnabled(false);
                jLabelWalk2.setVisible(false);
                jLabelWalk2.setEnabled(false);
                jLabelRidePrice2.setVisible(false);
                jLabelRidePrice2.setEnabled(false);
                jLabelWalkPrice2.setVisible(false);
                jLabelWalkPrice2.setEnabled(false);

                jLabelGolfer3.setVisible(false);
                jLabelGolfer3.setEnabled(false);
                jLabelRide3.setVisible(false);
                jLabelRide3.setEnabled(false);
                jLabelWalk3.setVisible(false);
                jLabelWalk3.setEnabled(false);
                jLabelRidePrice3.setVisible(false);
                jLabelRidePrice3.setEnabled(false);
                jLabelWalkPrice3.setVisible(false);
                jLabelWalkPrice3.setEnabled(false);

                // Calculate totals
                subTot = (rideCount * 55) + (walkCount * 32);
                tax = subTot * .075;
                total = subTot + tax;
                jLabelSubTot.setText(String.valueOf(formatter.format(subTot)));
                jLabelTax.setText(String.valueOf(formatter.format(tax)));
                jLabelTot.setText(String.valueOf(formatter.format(total)));

                this.setSize(350, 515);
                break;

            case 3:
                jLabelGolfer0.setText(receiptData.getGolfers().get(0).getName());
                if (receiptData.getGolfers().get(0).isRide()) {
                    jLabelWalk0.setVisible(false);
                    jLabelWalk0.setEnabled(false);
                    jLabelWalkPrice0.setVisible(false);
                    jLabelWalkPrice0.setEnabled(false);
                    rideCount++;
                } else {
                    jLabelRide0.setVisible(false);
                    jLabelRide0.setEnabled(false);
                    jLabelRidePrice0.setVisible(false);
                    jLabelRidePrice0.setEnabled(false);
                    walkCount++;
                }

                jLabelGolfer1.setText(receiptData.getGolfers().get(1).getName());
                if (receiptData.getGolfers().get(1).isRide()) {
                    jLabelWalk1.setVisible(false);
                    jLabelWalk1.setEnabled(false);
                    jLabelWalkPrice1.setVisible(false);
                    jLabelWalkPrice1.setEnabled(false);
                    rideCount++;
                } else {
                    jLabelRide1.setVisible(false);
                    jLabelRide1.setEnabled(false);
                    jLabelRidePrice1.setVisible(false);
                    jLabelRidePrice1.setEnabled(false);
                    walkCount++;
                }

                jLabelGolfer2.setText(receiptData.getGolfers().get(2).getName());
                if (receiptData.getGolfers().get(2).isRide()) {
                    jLabelWalk2.setVisible(false);
                    jLabelWalk2.setEnabled(false);
                    jLabelWalkPrice2.setVisible(false);
                    jLabelWalkPrice2.setEnabled(false);
                    rideCount++;
                } else {
                    jLabelRide2.setVisible(false);
                    jLabelRide2.setEnabled(false);
                    jLabelRidePrice2.setVisible(false);
                    jLabelRidePrice2.setEnabled(false);
                    walkCount++;
                }

                jLabelGolfer3.setVisible(false);
                jLabelGolfer3.setEnabled(false);
                jLabelRide3.setVisible(false);
                jLabelRide3.setEnabled(false);
                jLabelWalk3.setVisible(false);
                jLabelWalk3.setEnabled(false);
                jLabelRidePrice3.setVisible(false);
                jLabelRidePrice3.setEnabled(false);
                jLabelWalkPrice3.setVisible(false);
                jLabelWalkPrice3.setEnabled(false);

                // Calculate totals
                subTot = (rideCount * 55) + (walkCount * 32);
                tax = subTot * .075;
                total = subTot + tax;
                jLabelSubTot.setText(String.valueOf(formatter.format(subTot)));
                jLabelTax.setText(String.valueOf(formatter.format(tax)));
                jLabelTot.setText(String.valueOf(formatter.format(total)));

                this.setSize(350, 570);
                break;

            case 4:
                jLabelGolfer0.setText(receiptData.getGolfers().get(0).getName());
                if (receiptData.getGolfers().get(0).isRide()) {
                    jLabelWalk0.setVisible(false);
                    jLabelWalk0.setEnabled(false);
                    jLabelWalkPrice0.setVisible(false);
                    jLabelWalkPrice0.setEnabled(false);
                    rideCount++;
                } else {
                    jLabelRide0.setVisible(false);
                    jLabelRide0.setEnabled(false);
                    jLabelRidePrice0.setVisible(false);
                    jLabelRidePrice0.setEnabled(false);
                    walkCount++;
                }

                jLabelGolfer1.setText(receiptData.getGolfers().get(1).getName());
                if (receiptData.getGolfers().get(1).isRide()) {
                    jLabelWalk1.setVisible(false);
                    jLabelWalk1.setEnabled(false);
                    jLabelWalkPrice1.setVisible(false);
                    jLabelWalkPrice1.setEnabled(false);
                    rideCount++;
                } else {
                    jLabelRide1.setVisible(false);
                    jLabelRide1.setEnabled(false);
                    jLabelRidePrice1.setVisible(false);
                    jLabelRidePrice1.setEnabled(false);
                    walkCount++;
                }

                jLabelGolfer2.setText(receiptData.getGolfers().get(2).getName());
                if (receiptData.getGolfers().get(2).isRide()) {
                    jLabelWalk2.setVisible(false);
                    jLabelWalk2.setEnabled(false);
                    jLabelWalkPrice2.setVisible(false);
                    jLabelWalkPrice2.setEnabled(false);
                    rideCount++;
                } else {
                    jLabelRide2.setVisible(false);
                    jLabelRide2.setEnabled(false);
                    jLabelRidePrice2.setVisible(false);
                    jLabelRidePrice2.setEnabled(false);
                    walkCount++;
                }

                jLabelGolfer3.setText(receiptData.getGolfers().get(3).getName());
                if (receiptData.getGolfers().get(3).isRide()) {
                    jLabelWalk3.setVisible(false);
                    jLabelWalk3.setEnabled(false);
                    jLabelWalkPrice3.setVisible(false);
                    jLabelWalkPrice3.setEnabled(false);
                    rideCount++;
                } else {
                    jLabelRide3.setVisible(false);
                    jLabelRide3.setEnabled(false);
                    jLabelRidePrice3.setVisible(false);
                    jLabelRidePrice3.setEnabled(false);
                    walkCount++;
                }

                // Calculate totals
                subTot = (rideCount * 55) + (walkCount * 32);
                tax = subTot * .075;
                total = subTot + tax;
                jLabelSubTot.setText(String.valueOf(formatter.format(subTot)));
                jLabelTax.setText(String.valueOf(formatter.format(tax)));
                jLabelTot.setText(String.valueOf(formatter.format(total)));

                this.setSize(350, 615);
                break;
        }

        // show receipt
        this.setVisible(true);
    }
}
