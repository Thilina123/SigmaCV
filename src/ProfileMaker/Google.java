package ProfileMaker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.NetworkManager;

import java.net.InetSocketAddress;
import java.net.Proxy;

import java.io.IOException;

public class Google {
    public String FindOnLinkedIn(String name){
        String link="";
        Document doc = null;
        try {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxyHost", 1234));
            doc = Jsoup.connect("https://www.google.lk/search?q="+name.replace(' ','+')+"+linkedin").userAgent("Chrome").timeout(0).get();


        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements;
        elements = doc.select("cite");

        int i=0;
        while(elements.size()>i) {
            link = "https://" + elements.get(i).text();
            i++;
            if (!link.contains("/dir/"))
                break;
        }
        System.out.println(link);
        return  link;
    }
    public String FindOnGoogleScholar(String name){
        String link="";
        Document doc = null;
        try {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxyHost", 1234));
            doc = Jsoup.connect("https://www.google.lk/search?q="+name.replace(' ','+')+"+google+scholar").userAgent("Chrome").timeout(0).get();


        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements;
        elements = doc.select("cite");

        int i=0;
        while(elements.size()>i) {
            link = "https://" + elements.get(i).text();
            i++;
            if (link.contains(".com/citations?user="))
                break;
        }
        System.out.println(link);
        return  link;
    }
}
