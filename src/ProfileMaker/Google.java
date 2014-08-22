package ProfileMaker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by MALMI on 7/26/2014.
 */
public class Google {
    public String FindOnLinkedIn(String name){
        String link="";
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.google.lk/search?q="+name.replace(' ','+')+"+linkedin").userAgent("Chrome").timeout(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements;
//        elements = doc.select("a[href*=linkedin.com]>div");
        elements = doc.select("cite");

//        for (int i = 0; i < elements.size(); i++) {
//            System.out.println(elements.get(i).text());
//        }
        link="https://"+elements.get(0).text();
        return  link;
    }
}
