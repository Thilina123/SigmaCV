package ProfileMaker;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;

public class LinkedInExtractor {
    public void Extract(String searchName) {

        ProfileMaker.Google g=new ProfileMaker.Google();
        String link=g.FindOnLinkedIn(searchName);

        Document doc = null;
        String picUrl,name,title;
        String[] publications;
        try {
            doc = Jsoup.connect(link).timeout(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
//      profile picture
        Element profile_picture = doc != null ? doc.select("div[id=profile-picture] > img").first() : null;
//        System.out.println(profile_picture);
//        System.out.println(profile_picture.attr("src"));

//        name
        Element nameDiv = doc != null ? doc.select("span.full-name").first() : null;
        System.out.println(nameDiv.text());
        name=nameDiv.text();

//        title
        Element titleP = doc != null ? doc.select("p").first() : null;
        System.out.println(titleP.text());
        title=titleP.text();

//        publications
        Elements pub = doc != null ? doc.select("ul[class=publications documents] > li >h3 ") : null;
        publications=new String[pub.size()];
        for (int i = 0; i < pub.size() ; i++) {
            System.out.println(pub.get(i).text());
            publications[i]=pub.get(i).text();
        }

    }
}
