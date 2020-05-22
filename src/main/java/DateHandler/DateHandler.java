package DateHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateHandler {
    public String getDate(String stringDate) throws Error {
        String[] array = stringDate.split("\\.");

        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        GregorianCalendar date = new GregorianCalendar();
        try {
            date.setTime(format.parse(stringDate));
        }
        catch (Exception e)
        {
            throw new Error ("2");
        }
        format.setLenient(false);
        try {
            date.setTime(format.parse(stringDate));
        }
        catch (Exception e)
        {
            throw new Error ("1");
        }

        GregorianCalendar dateCurrent = new GregorianCalendar();
        if (date.compareTo(dateCurrent) > 0){
            throw new Error ("3");
        }
        int day;
        int month;
        month = date.get(Calendar.MONTH)+1;
        day = date.get(Calendar.DAY_OF_MONTH);

        return((day >9? day : "0"+day)+
                "."
                +(month>9?month:"0"+month)+
                "."+
                date.get(Calendar.YEAR));
    }
}
//16.01.2020