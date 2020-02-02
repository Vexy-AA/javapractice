package DateHandler;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateHandler {
    public String getDate(GregorianCalendar date){
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