package web;

import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Instant;

import CurrencyHandler.CurrencyHAndler;
import DateHandler.DateHandler;
import downloader.CbrfDownloader;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public HelloController(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @GetMapping("/hello/{name1}/{date1}")
    public String helloName(
            @PathVariable(name = "name1") String name,
            @PathVariable String date1){
        String return_string = "";
        String request = name + "/" + date1;
        int error = 0;

        CurrencyHAndler currencyHAndler = new CurrencyHAndler();
        String currency = currencyHAndler.getCss(name);
        if (currency == "error1"){
            return_string = "Вы ввели валюту '" + name +
                    "', формат валюты неверный, " +
                    "введите трехзначное название валюты с заглавной буквы например USD\n";
            error ++;
        }
        DateHandler dateHandler = new DateHandler();
        String date_web = "";
        try {
            date_web = dateHandler.getDate(date1);
        }
        catch (Error e){
            String error2 = e.toString();
            if (error2.equals("java.lang.Error: 1")){
                return_string += "Проверьте существует ли такая дата " + date1;
            }
            else if (error2.equals("java.lang.Error: 2")){
                return_string += "Вы ввели дату '" + date1 +
                        "', формат неверный, введите дату в формате dd.MM.YYYY например 01.10.1999";
            }
            else{
                return_string +=
                        "Заглядываю в магический шар... будущий курс валюты туманен";
            }
            error++;
        }
        if (error >0) {

            namedParameterJdbcTemplate.update(
                    " insert into requested_names(name , process_time, return_string) " +
                            " values (:name_ph, :process_time_ph, :return_ph)",
                    new MapSqlParameterSource()
                            .addValue("name_ph", request)
                            .addValue("process_time_ph", Timestamp.from(Instant.now()))
                            .addValue("return_ph",return_string)
            );
            return return_string;
        }
        CbrfDownloader downloader = new CbrfDownloader();
        try {
            URL url = new URIBuilder()
                    .setScheme("https")
                    .setHost("www.cbr.ru")
                    // .setPort(8080)
                    .setPath("/currency_base/daily/")
                    .addParameter("UniDbQuery.Posted", "True")
                    .addParameter("UniDbQuery.To", date_web)
                    .build()
                    .toURL();
            try {
                String actual = downloader.get(url, currency);
                return_string += ("Курс " + name + " на " + date_web + " " + actual);

            }
            catch (Exception e)            {
                return_string += "\n downloader fail " + url.toString() + e.toString();
            }
        }
        catch (Exception e)        {
            return_string += "\n url fail" + e.toString();
        }

        namedParameterJdbcTemplate.update(
                " insert into requested_names(name , process_time, return_string) " +
                        " values (:name_ph, :process_time_ph, :return_ph)",
                new MapSqlParameterSource()
                        .addValue("name_ph", request)
                        .addValue("process_time_ph", Timestamp.from(Instant.now()))
                        .addValue("return_ph",return_string)
        );

        return return_string;
    }


}