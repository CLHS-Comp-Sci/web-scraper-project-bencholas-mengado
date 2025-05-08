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
            
            final String titles = document.select("h3").text();
            //System.out.println(titles);
            final String[] titleList = titles.split("(?=\\p{Lu})");
            // for (String s : titleList)
            // {
            //     System.out.println(s);
            // }
            int n = 0;

            for (Element table : document.select("table.wikitable.grid")){
                if (n <= 5){
                    System.out.println(titleList[n]);
                    if (titleList[n].equals("Primary ")){
                        for (Element row : table.select("table.wikitable.grid tr"))
                        {
                            //System.out.println(titleList[n]);
                            System.out.println(row.select("a").text());
                            
                        }
                    }
                    
                }

                n++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
