package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

/**
 * Created by Thilina on 8/22/2014.
 */
public class NetworkManager {
    public String Get(String urlString){

        String info="";
        URL url = null;
//        String test="https://api.github.com/search/users?q=thilina premasiri&order=desc&access_token=69e07dde89a8a0a6713f810cfd4c461f04f47e85";
        try {
            url = new URL(urlString);
            System.out.println("request-----" + urlString);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
                    "cache.mrt.ac.lk", 3128));
            HttpURLConnection uc = (HttpURLConnection) url.openConnection(proxy);
//        HttpURLConnection uc = (HttpURLConnection) url.openConnection(); /*With out proxy */

            uc.connect();
            String line = null;
            StringBuffer tmp = new StringBuffer();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    uc.getInputStream()));
            while ((line = in.readLine()) != null) {
                tmp.append(line);
            }
//            Document doc = Jsoup.parse(String.valueOf(tmp));
            System.out.println("result "+tmp.toString());
            info=tmp.toString();
//            ShowInfo(tmp.toString());

        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }
//        System.out.println();
        return info;
    }
}
