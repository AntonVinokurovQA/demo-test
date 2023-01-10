package tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Tools {
    /* Day:0 - current date */
    public static String getCurrentDate(int days, String format) {
        Calendar calendar = Calendar.getInstance();
        Date date;
        if (days != 0) {
            calendar.add(Calendar.DATE, days);
        }
        date = calendar.getTime();
        Locale loc = new Locale("en", "US");
        SimpleDateFormat f = new SimpleDateFormat(format, loc);

        return f.format(date);
    }
}
