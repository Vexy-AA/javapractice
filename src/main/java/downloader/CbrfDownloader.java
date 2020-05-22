package downloader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.plaf.IconUIResource;
import java.io.IOException;
import java.net.URL;

public class CbrfDownloader {

    public String get(URL url, String cssSelector) throws IOException {

        Document parse = Jsoup.parse(url, 10000);
        Elements gg = parse.select(cssSelector);
        String[] table = gg.first().text().split(" ");
        String currency = table[table.length-1];
        return currency;
    }
}
