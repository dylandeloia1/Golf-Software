package defaultPkg;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static TeeSheetData mondayData = new TeeSheetData("src//monday_tee_times.csv");
    static TeeSheetData tuesdayData = new TeeSheetData("src//tuesday_tee_times.csv");
    static TeeSheetData wednesdayData = new TeeSheetData("src//wednesday_tee_times.csv");
    static TeeSheetData thursdayData = new TeeSheetData("src//thursday_tee_times.csv");
    static TeeSheetData fridayData = new TeeSheetData("src//friday_tee_times.csv");
    static TeeSheetData saturdayData = new TeeSheetData("src//saturday_tee_times.csv");
    static TeeSheetData sundayData = new TeeSheetData("src//sunday_tee_times.csv");
    static ArrayList<TeeSheetData> weeklyData = new ArrayList<>(Arrays.asList(sundayData,mondayData,tuesdayData,wednesdayData,thursdayData,fridayData,saturdayData));

    public static void main(String[] args) {
        new Login();
    }
}