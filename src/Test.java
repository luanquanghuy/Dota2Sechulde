import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        String link = "https://www.gosugamers.net/dota2/teams/8954-team-secret";
        try {
            URL url = new URL(link);

            Document document = Jsoup.connect(link)
                    .userAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:59.0) Gecko/20100101 Firefox/59.0")
                    .header("Host", "www.gosugamers.net")
                    .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Accept-Language", "en-US,en;q=0.5")
                    .header("Cache-Control", "max-age=0")
                    .header("Connection", "keep-alive")
                    .header("Upgrade-Insecure-Requests", "1")
                    .followRedirects(false)
                    .ignoreContentType(true)
                    .timeout(10000)
                    .get();
            //System.out.println(document.toString());
            Elements elements = document.getElementById("gb-matches").getElementsByTag("tr");
            System.out.println();
            for (Element e : elements) {
                //System.out.println(e);
                String time = Jsoup.parse(e.select("a[title]").attr("title"))
                        .getElementsByTag("span")
                        .get(0).text();
                System.out.println(time);
                System.out.print(e.select("span[class]").get(0).select("span").get(2).text() + " vs ");
                System.out.println(e.select("span[class]").get(3).select("span").get(2).text());
                System.out.println(e.select("span.live-in").text());
                String[] arrTime = time.split("[,\\s]|at");
                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy HH:mm");
                sdf.setTimeZone(TimeZone.getTimeZone("GMT+2"));
                Date date = sdf.parse(arrTime[0] + " " + arrTime[1] + " " + arrTime[3] + " " + arrTime[6]);
                SimpleDateFormat mySdf = new SimpleDateFormat("HH:mm d/M/yyyy");
                System.out.println(mySdf.format(date));
                System.out.println(date);
                System.out.println("https://www.gosugamers.net" + e.select("a[href]").attr("href"));
            }
            String urlLogo = "https://www.gosugamers.net" + document.getElementById("col1").select("div.teamImage").toString().split("\'")[1];
            System.out.println(urlLogo);
        }catch (SocketTimeoutException e) {
            System.out.println("Timed out");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
