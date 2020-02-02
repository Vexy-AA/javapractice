package DateHandler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateHandlerTest {
    @DisplayName("date url string")
    @Test
    void dateTest(){
        DateHandler dateHandler = new DateHandler();
        GregorianCalendar date = new GregorianCalendar(2020, Calendar.JANUARY,2);
        assertEquals("02.01.2020",dateHandler.getDate(date));
    }
}
