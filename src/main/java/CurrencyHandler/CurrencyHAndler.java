package CurrencyHandler;

import java.util.HashSet;
import java.util.Set;

public class CurrencyHAndler {
    public String getCss(String currency){
        Set<String> currencies = new HashSet<String>();
        currencies.add("AUD");
        currencies.add("AZN");
        currencies.add("AMD");
        currencies.add("BYN");
        currencies.add("BGN");
        currencies.add("BRL");
        currencies.add("HUF");
        currencies.add("KRW");
        currencies.add("HKD");
        currencies.add("DKK");
        currencies.add("USD");
        currencies.add("EUR");
        currencies.add("INR");
        currencies.add("KZT");
        currencies.add("CAD");
        currencies.add("KGS");
        currencies.add("CNY");
        currencies.add("MDL");
        currencies.add("TMT");
        currencies.add("NOK");
        currencies.add("PLN");
        currencies.add("RON");
        currencies.add("XDR");
        currencies.add("SGD");
        currencies.add("TJS");
        currencies.add("TRY");
        currencies.add("UZS");
        currencies.add("UAH");
        currencies.add("GBP");
        currencies.add("CZK");
        currencies.add("SEK");
        currencies.add("CHF");
        currencies.add("ZAR");
        currencies.add("JPY");
        if (currencies.contains(currency))
        {
            return ("#content table tr:contains("+currency +")");
        }
        return ("error1");
    }
}
