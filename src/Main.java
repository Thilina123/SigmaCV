
import NER.NER_Extractor;
import PDF.PDF_Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    public static void main(String[] args) {

//        Pattern findUrl = Pattern.compile("\\bhttp.*?\\.pdf\\b");
//        Matcher matcher = findUrl.matcher("\n" +
//                " Organizer: <PERSON>Dayata Kirula</PERSON> 2011 <ORGANIZATION>CSE</ORGANIZATION>’s <ORGANIZATION>Exhibition Stall</ORGANIZATION>. ");
//        while (matcher.find()) {
//            System.out.println(matcher.group());
//        }

//        String input = "one Organizer: <PERSON>Dayata Kirula</PERSON> 2011 <ORGANIZATION>CSE</ORGANIZATION>’s <ORGANIZATION>Exhibition Stall</ORGANIZATION>.  two;";
//        Pattern p = Pattern.compile("(?<=\\bPERSON>\\b).*?(?=\\b</PERSON\\b)");
//        Matcher m = p.matcher(input);
//        List<String> matches = new ArrayList<String>();
//        while (m.find()) {
//            matches.add(m.group());
//        }
//        System.out.println(matches.toString());
//        PDF_Reader rd=new PDF_Reader();
//        rd.SaveTxt("Res/CV.pdf","Res/CV.txt");
//
        NER_Extractor ner=new NER_Extractor();
        ner.ExtractNames();
        //============== extracting linkedIn profile info

//        Google g=new Google();
//        LinkedInExtractor linkedIn=new LinkedInExtractor();
//        String link=g.FindOnLinkedIn("malaka walpola");             // insert name here
//        System.out.println(link);
//        linkedIn.Extract(link);
    }
}
