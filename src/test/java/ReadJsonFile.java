import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ReadJsonFile {

    public static void main(String[] args) throws Exception {

        String dateStr = "EEE, dd MMM yyyy HH:mm:ss 'GMT'";
        DateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = format.parse(dateStr);
        System.out.println("date");
        String json = readFileAsString();
    }
    public static String readFileAsString()throws Exception
    {
        String file = "src/test/resources/myFile.json";
        String json = new String(Files.readAllBytes(Paths.get(file)));
        return json;
    }
}