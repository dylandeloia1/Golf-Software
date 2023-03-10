package defaultPkg;

import java.io.File;
import java.util.*;

public class TeeSheetData extends HashMap<String,ArrayList<Golfer>>{

    String[] keys = {
            "8:00 AM", "8:10 AM", "8:20 AM", "8:30 AM", "8:40 AM", "8:50 AM",
            "9:00 AM", "9:10 AM", "9:20 AM", "9:30 AM", "9:40 AM", "9:50 AM",
            "10:00 AM", "10:10 AM", "10:20 AM", "10:30 AM", "10:40 AM", "10:50 AM",
            "11:00 AM", "11:10 AM", "11:20 AM", "11:30 AM", "11:40 AM", "11:50 AM",
            "12:00 PM", "12:10 PM", "12:20 PM", "12:30 PM", "12:40 PM", "12:50 PM",
            "1:00 PM", "1:10 PM", "1:20 PM", "1:30 PM", "1:40 PM", "1:50 PM",
            "2:00 PM", "2:10 PM", "2:20 PM", "2:30 PM", "2:40 PM", "2:50 PM",
            "3:00 PM", "3:10 PM", "3:20 PM", "3:30 PM", "3:40 PM", "3:50 PM",
            "4:00 PM", "4:10 PM", "4:20 PM", "4:30 PM", "4:40 PM", "4:50 PM",
            "5:00 PM", "5:10 PM", "5:20 PM", "5:30 PM", "5:40 PM", "5:50 PM", "6:00 PM",
    };

    // initializes map and reads tee time data from CSV
    public TeeSheetData(String filename){
        for(String key : keys){
            this.put(key,new ArrayList<Golfer>());
        }
        File file = new File(filename);
        try {
            Scanner inputStream = new Scanner(file);
            while(inputStream.hasNext()){
                String line = inputStream.nextLine();
                String[] values = line.split(",");
                String[] names = new String[values.length-1];
                for(int i = 0; i < values.length-1; i++){
                    names[i] = values[i+1];
                }
                for(String name : names){
                    this.get(values[0]).add(new Golfer(name));
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}