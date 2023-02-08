import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws Exception {

        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        TemporalAccessor accessor = timeFormatter.parse("2023-02-07T22:02:32Z");

        Date date = Date.from(Instant.from(accessor));
        System.out.println(date);


        getRFC1123(date);

    }
    static SimpleDateFormat RFC1123 = new SimpleDateFormat(
            "EEE, dd MMM yyyy HH:mm:ss 'GMT'", new DateFormatSymbols(
                    Locale.ENGLISH));

    public static String getRFC1123(Date date) {
        System.out.println("2-> " + RFC1123.format(date));
        return RFC1123.format(date);
    }/*  ww w.  j a v  a  2  s.  c o m*/
}