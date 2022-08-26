package test.demo.pachong;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

public class WebmagicDome implements PageProcessor {

    private final Site site =Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        Selectable xpath1 = html.xpath("//div[@class='whole_rank']" +
                "//tr[@class='tc_score_value']");
        List<String> all = xpath1.xpath("//td[@class='tc_schedule_td1']/allText()").all();
        for (int i = 0; i < all.size(); i++) {
            System.out.println(all.get(i));
        }

//        List<String> keys = html.xpath("//div[@class='basic-info J-basic-info cmn-clearfix']"+
//                "//dt[@class='basicInfo-item name']/text()").all();
//        List<String> values = html.xpath("//div[@class='basic-info J-basic-info cmn-clearfix']"+
//                "//dd[@class='basicInfo-item value']/allText()").all();
//        for (int i = 0; i < keys.size(); i++) {
//            page.putField(keys.get(i),values.get(i));
//        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new WebmagicDome())
                .addUrl("https://baike.baidu.com/item/"+"苏炳添")
//                .addUrl("https://data.qtx.com/basketball/lqjfb/wXn6J872B8_LNY6kaZ709.html")
                .addPipeline(new ConsolePipeline())
                .addPipeline(new JsonFilePipeline("D:\\javatest"))
                .thread(2)
                .run();
    }
}
