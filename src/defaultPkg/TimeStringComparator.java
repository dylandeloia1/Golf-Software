package defaultPkg;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

// comparator for ordering by time in jtable
public class TimeStringComparator implements Comparator<String> {
    private DateFormat timeFormat = new SimpleDateFormat("h:mm a");
    private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

    @Override
    public int compare(String time1, String time2) {
        return timeInMillis(time1) - timeInMillis(time2);
    }

    public int timeInMillis(String time) {
        return timeInMillis(time, timeFormat);
    }

    private int timeInMillis(String time, DateFormat format) {
        try {
            Date date = format.parse(time);
            return (int) date.getTime();
        } catch (ParseException e) {
            if (format != dateFormat) {
                return timeInMillis(time, dateFormat);
            } else {
                System.out.println(e.getMessage());
                return -1;
            }
        }
    }
}
