package test.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UrlTest {
    public static void main(String[] args) throws Exception {
        StringBuilder json = new StringBuilder();
        URL url = new URL("https://www.runoob.com/wp-content/themes/runoob/option/alisearch/v330/hot_hint.php?type=hint&user_id=dbfc4f27-849e-493a-acab-6c8b7a70b07a");
        URLConnection us = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(us.getInputStream(),"utf-8"));
        String input=null;
        while ((input = in.readLine())!=null){
            json.append(input);
        }
        in.close();
        System.out.println(json.toString());
    }
}
