package ProfileMaker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.NetworkManager;

public class GitHubExtractor {
    private String token;
    private NetworkManager networkManager;
    public GitHubExtractor(String token) {
        this.token = token;
        networkManager=new NetworkManager();
    }

    public static void  main(String[] args) {
        new GitHubExtractor("69e07dde89a8a0a6713f810cfd4c461f04f47e85").GetInfo("https://api.github.com/users/wadsashika");
//        new GitHubExtractor("69e07dde89a8a0a6713f810cfd4c461f04f47e85").Find("thilina premasiri");
    }
    public void Find(String name){
        String url="https://api.github.com/search/users?q="+name+"&order=desc&access_token="+token;
        String result=networkManager.Get(url);
        System.out.println(result);
        try {
            JSONObject json=new JSONObject(result);
            if (json.getInt("total_count")==0) {
                GetInfo(json.getJSONArray("items").getJSONObject(0).getString("url"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String GetInfo(String profileUrl)  {
        String urlString=profileUrl+"/repos?access_token="+token;
        String info=networkManager.Get(urlString);
            ShowInfo(info);

//        System.out.println();
        return info;
    }

    public void ShowInfo(String jsonString){
        try {
//            JSONObject json=new JSONObject(jsonString);
            JSONArray ary=new JSONArray(jsonString);
            JSONObject json;
            System.out.println("==========GitHub Projects=========");
            for (int i = 0; i < ary.length(); i++) {
                json= ary.getJSONObject(i);
                System.out.println(json.get("name") + "-----------");
                System.out.println("Description : "+json.get("description"));
                System.out.println("Technology : "+json.get("language")+"\n");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    }
