package ConsoleInput;

import CurrencyHandler.CurrencyHAndler;
import DateHandler.DateHandler;
import downloader.CbrfDownloader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Scanner;

import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConsoleInput {
    public static void main(String[] args) throws ParseException, URISyntaxException, IOException {
        Scanner sc = new Scanner(System.in);
        int repeat = 0;
        do {

            System.out.println("Введите желаемую валюту");
            String currency = sc.next();

            System.out.println("Введите дату в формате dd.MM.YYYY");

            String stringDate = sc.next();
            // System.out.println("вы ввели "+ stringDate);

            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("dd.MM.yyyy");
            GregorianCalendar date = new GregorianCalendar();
            date.setTime(format.parse(stringDate));

            DateHandler dateHandler = new DateHandler();
            CurrencyHAndler currencyHAndler = new CurrencyHAndler();
            CbrfDownloader downloader = new CbrfDownloader();

    //https://www.cbr.ru/currency_base/daily/?date_req=02.01.2020
            URL url = new URIBuilder()
                    .setScheme("https")
                    .setHost("www.cbr.ru")
                    // .setPort(8080)
                    .setPath("/currency_base/daily/")
                    .addParameter("date_req", dateHandler.getDate(date))
                    .build()
                    .toURL();
            System.out.println(url);

            String actual = downloader.get(url, currencyHAndler.getCss(currency));
            DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
            System.out.println("Курс " + currency + " на " + dateFormat.format(date.getTime()) + " " + actual);
            System.out.println("Еще разок? 1- да");
            repeat = sc.nextInt();
        } while (repeat == 1);
    }
}
