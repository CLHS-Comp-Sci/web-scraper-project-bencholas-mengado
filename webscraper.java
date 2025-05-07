package webscrape;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class webscraper {
    public static void main(String[] args) throws Exception {
        
        final String url = "https://wiki.teamfortress.com/wiki/Weapons";

        try {
            final Document document = Jsoup.connect(url).get();

            for (Element row : document.select("table.wikitable.grid tr")){
                
                final String ticker = row.select("a").text();
                System.out.println(ticker);
                final String ammo = row.select("td").text();
                System.out.println(ammo);

                
                
                

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
