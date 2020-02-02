package CurrencyHandler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyHandlerTest {
    @DisplayName("currency css handler")
    @Test
    void currencyTest(){
        String css;
        CurrencyHAndler currencyHAndler = new CurrencyHAndler();
        css = currencyHAndler.getCss("USD");
        assertEquals("#content table tr:contains(USD)",css);

    }
}
