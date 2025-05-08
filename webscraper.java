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
            
            //Saves the title of each table
            final String titles = document.select("h3").text();
            //System.out.println(titles);

            //Splits titles by capital letters
            final String[] titleList = titles.split("(?=\\p{Lu})");
            // for (String s : titleList)
            // {
            //     System.out.println(s);
            // }
            int n = 0;
            System.out.println("Shots to kill a full health heavy: ");
            //Iterates through each table on the website
            for (Element table : document.select("table.wikitable.grid")){
                if (n <= 5 || n == 16){
                    //System.out.println(titleList[n]);

                    //Validates weapon type
                    if (titleList[n].strip().equals("Primary")){                        
                        for (Element row : table.select("table.wikitable.grid tr"))
                        {
                            //System.out.println(titleList[n]);
                            final String weapon = row.select("a").text();
                            //Removes annoying afterburn data
                            if (weapon.indexOf("Afterburn") >=0){
                                System.out.println(weapon.substring(0,weapon.indexOf("Afterburn")));
                            }
                            else
                            {
                                System.out.println(weapon);
                            }
                            //Pulls data 
                            final String data = row.select("td").text();
                            //System.out.println(data);
                            
                            //Splits data by spaces and dashes
                            String[] nums = data.split("[-\\s]");
                            //System.out.println(nums.length);
                            // for (int i = 0; i < nums.length; i++)
                            // {
                            //     System.out.println(i + " " + nums[i]);

                            // }

                            //Shot calculations
                            if (nums.length > 4 && !nums[1].equals("(Afterburn)") && !nums[0].equals("(Afterburn)")){
                                int damage = Integer.parseInt(nums[4]);
                                //System.out.println(damage);
                                System.out.println("Point blank: " + (int)Math.ceil(300./damage) + " shots");
                                damage = Integer.parseInt(nums[6]);
                                System.out.println("Medium range: " + (int)Math.ceil(300/damage) + " shots");
                                damage = Integer.parseInt(nums[8]);
                                System.out.println("Long range: " + (int)Math.ceil(300/damage) + " shots"); 
                                System.out.println();
                            }
                            
                            

                            
                            
                        }
                    }
                    
                }

                n++;
            }

            
            for (Element row : document.select("table.wikitable.grid tr")){
                
                // if (n==0)
                // {
                //     System.out.println(document.select("table.wikitable.grid tr"));
                // }
                // n++;

                // if (titleList[n].equals("Primary"))
                // {
                //     final String weapon = row.select("a").text();
                //     System.out.println(weapon);
                //     final String ammo = row.select("td").text();
                //     System.out.println(ammo);
                // }
                              
                
                

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
