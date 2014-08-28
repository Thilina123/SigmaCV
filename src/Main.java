
import NER.NER_Extractor;
import PDF.PDF_Reader;
import ProfileMaker.GitHubExtractor;
import ProfileMaker.GoogleScholarExtractor;
import ProfileMaker.LinkedInExtractor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
//        NER_Extractor ner=new NER_Extractor();
//        ner.ExtractNames();
        //============== extracting linkedIn profile info

//        ProfileMaker.Google g=new ProfileMaker.Google();
//        ProfileMaker.LinkedInExtractor linkedIn=new ProfileMaker.LinkedInExtractor();
//        Scanner sc=new Scanner(System.in);
//        String name =sc.nextLine();
////        String link=g.FindOnLinkedIn(name);             // insert name here
//        new GitHubExtractor("69e07dde89a8a0a6713f810cfd4c461f04f47e85").Find(name);
//        System.out.println(link);
//        linkedIn.Extract(link);
          new Main().CallName();
    }

    public void CallName(){
        System.out.print("Enter name: ");
        Scanner sc=new Scanner(System.in);
        String name =sc.nextLine();

        LinkedInExtractor linkedIn=new LinkedInExtractor();
        GoogleScholarExtractor gscholar=new GoogleScholarExtractor();
//        linkedIn.Extract(name);
          gscholar.Extract(name);

//        String link=g.FindOnLinkedIn(name);             // insert name here
//        new GitHubExtractor("69e07dde89a8a0a6713f810cfd4c461f04f47e85").Find(name);

        CallName();

    }
}
