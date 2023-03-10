package defaultPkg;

import java.util.ArrayList;

public class ReceiptData {
    private String dateTimeOfRes,dateOfGolf,timeOfGolf;
    private ArrayList<Golfer> golfers;

    public ReceiptData(String dateTimeOfRes, String dateOfGolf, String timeOfGolf, ArrayList<Golfer> golfers) {
        this.dateTimeOfRes = dateTimeOfRes;
        this.dateOfGolf = dateOfGolf;
        this.timeOfGolf = timeOfGolf;
        this.golfers = golfers;
    }

    public String getDateTimeOfRes() {
        return dateTimeOfRes;
    }

    public String getDateOfGolf() {
        return dateOfGolf;
    }

    public String getTimeOfGolf() {
        return timeOfGolf;
    }

    public ArrayList<Golfer> getGolfers() {
        return golfers;
    }
}
