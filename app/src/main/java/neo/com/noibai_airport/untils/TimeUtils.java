package neo.com.noibai_airport.untils;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 9/2/16
 * Time: 6:07 PM
 * Desc: TimeUtils
 */
public class TimeUtils {

    /**
     * Parse the time in milliseconds into String with the format: hh:mm:ss or mm:ss
     *
     * @param duration The time needs to be parsed.
     */
    @SuppressLint("DefaultLocale")
    public static String formatDuration(int duration) {
        duration /= 1000; // milliseconds into seconds
        int minute = duration / 60;
        int hour = minute / 60;
        minute %= 60;
        int second = duration % 60;
        if (hour != 0)
            return String.format("%2d:%02d:%02d", hour, minute, second);
        else
            return String.format("%02d:%02d", minute, second);
    }

    public static String convent_date(String sDateinput, String fomatDateinput, String fomatDateoutput) {
        String strDateTime = "";
        DateFormat inputFormatter = new SimpleDateFormat(fomatDateinput);
        Date da = null;
        try {
            da = (Date) inputFormatter.parse(sDateinput);
            System.out.println("==Date is ==" + da);
            DateFormat outputFormatter = new SimpleDateFormat(fomatDateoutput);
            strDateTime = outputFormatter.format(da);

            return strDateTime;
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return strDateTime;
    }

    public static boolean compare_date_time(String sDateinput, String fomatDateinput) {
        long long_date1 = 0;
        long long_date2 = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(fomatDateinput);
            Date date_1 = sdf.parse(sDateinput);
            long_date1 = date_1.getTime();
            // convent date 2
            long_date2 = System.currentTimeMillis();
            Date date = new Date(long_date2);
            SimpleDateFormat df2 = new SimpleDateFormat(fomatDateinput);
            String da = df2.format(date);
            Log.i("abc", da);
            //
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time = long_date2 - long_date1;
        long hour = TimeUnit.HOURS.convert(time, TimeUnit.MILLISECONDS);
        if (hour > 24) {
            return true;
        } else return false;
    }
}
