package CurrencyHandler;

public class CurrencyHAndler {
    public String getCss(String currency)
    {
        return ("#content table tr:contains("+currency +")");
    }
}
