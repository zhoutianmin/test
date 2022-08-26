package test.demo.pachong;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

//import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*class SimpleOne {
    Document getDoc(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc;
    }
    Elements getElementAs(Document doc) {//cssQuery syntax:https://jsoup.org/apidocs/org/jsoup/select/Selector.html
        Elements a = doc.select("a[href]");//finds links (a tags with href attributes)
        return a;
    }
}
    public class Pachong {
        public static void main(String[] args) throws IOException {
            SimpleOne s = new SimpleOne();
//            Document doc = s.getDoc("https://www.baidu.com/");
            Document doc = s.getDoc("https://data.qtx.com/saicheng/QmGj0JrWNR_KljDPBDjeD.html");
//            System.out.println(s.getElementAs(doc));

// 通过元素id值来获取对应的节点
//            Element aSet = doc.getElementById("wrapper");
//            System.out.println(aSet);
// 通过标签名来获取
//            Elements aSet = doc.getElementsByTag("div");
//            System.out.println(aSet);
// 通过类名来获取
//            Elements aSet = doc.getElementsByClass("wrapper_new");
//            System.out.println(aSet);
            Elements aSet = doc.getElementsByClass("tc_center_value");
            System.out.println(aSet);
// 通过属性名来获取
//            Elements aSet = doc.getElementsByAttribute("href");
//            System.out.println(aSet);
// 通过指定属性名称和属性值来获取节点对象
//            Elements aSet = doc.getElementsByAttributeValue("href","http://news.baidu.com");
//            System.out.println(aSet);
// 获取所有节点元素
//            Elements aSet = doc.getAllElements();
//            System.out.println(aSet);
//带有href属性的a元素
//            Elements aSet = doc.select("a[href]");
//            System.out.println(aSet);
// 扩展名为.png的图片
//            Elements aSet = doc.select("img[src$=.png]");
//            System.out.println(aSet);
// class等于masthead的div标签
//            Element aSet = doc.select("div.wrapper_new").first();
//            System.out.println(aSet);
//在h3元素之后的a元素
//            Elements aSet = doc.select("h3.r > a");
//            System.out.println(aSet);
            System.out.println("end");
        }
    }*/

public class Pachong {
    public static void main(String[] args) throws IOException {
        //要爬取的网站
        String url = "https://data.qtx.com/saicheng/QmGj0JrWNR_KljDPBDjeD.html";
        //获得一个和网站的链接，注意是Jsoup的connect
        Connection connect = Jsoup.connect(url);
        //获得该网站的Document对象
        Document document = connect.get();
        int cnt = 1;
        //我们可以通过对Document对象的select方法获得具体的文本内容
        //下面的意思是获得.bool-img-text这个类下的 ul 下的 li
        Elements rootselect = document.select(".first_part table tbody");
        for (Element element:rootselect){
            Elements select = element.select(".tc_center_value");
            select.forEach(element1 -> {
                String text = element1.select(".tc_schedule_td0").text();
                String text1 = element1.select(".tc_schedule_td1").text();
                String title = element1.getElementsByAttribute("title").eq(0).text();
                String title1 = element1.getElementsByAttribute("title").eq(1).text();
                String text2 = element1.select(".tc_schedule_td3").text();
                String text3 = element1.select(".tc_schedule_td5").text();
                String text4 = element1.select(".tc_schedule_td6").text();
                System.out.println("轮次:"+text+" 时间:"+text1+" 主队:"+title+" 比分:"+text2+" 客队:"+title1+" 胜负:"+text3+" 半场:"+text4);
            });
        }
//        for(Element ele : rootselect){
//            //然后获得a标签里面具体的内容
//            Elements novelname = ele.select(".book-mid-info h2 a");
//            String name  = novelname.text();
//
//            Elements author = ele.select(".book-mid-info p a");
//            String authorname = author.first().text();
//
//            Elements sumadvice = ele.select(".total p");
//            String sum = sumadvice.last().text();
//
//            System.out.println("书名:"+name+" 作者:"+authorname+" 推荐量:"+sum);
//        }
        //创建mongodb 客户端
//        MongoClient mongoClient = new MongoClient("localhost", 27017);
//        //连接database
//        MongoDatabase database = mongoClient.getDatabase("wanho");
    }
}