import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static String formatDate(String rawDate) {

        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        TemporalAccessor accessor = timeFormatter.parse(rawDate);
        Date date = Date.from(Instant.from(accessor));
        return getRFC1123(date);

    }
    static SimpleDateFormat RFC1123 = new SimpleDateFormat(
            "EEE, dd MMM yyyy HH:mm:ss 'GMT'", new DateFormatSymbols(
                    Locale.ENGLISH));

    public static String getRFC1123(Date date) {
        System.out.println("2-> " + RFC1123.format(date));
        return RFC1123.format(date);
    }/*  ww w.  j a v  a  2  s.  c o m*/

    public static String trimDate(String date){
        return date.replace("[", "").replace("]", "");
    }
}