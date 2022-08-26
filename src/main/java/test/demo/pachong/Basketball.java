package test.demo.pachong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import test.demo.pachong.entity.Hoopster;
import test.demo.pachong.entity.Information;
import test.demo.pachong.pipeline.MongoPipeline;
import test.util.BeanUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;
@Component
public class Basketball implements PageProcessor {

    private static final String RUKOU_NBA = "https://data\\.qtx\\.com/basketball/lqjfb/wXn6J872B8_LNY6kaZ709";

    private static final String RUKOU_CBA = "https://data\\.qtx\\.com/basketball/lqjfb/O5zWeOj29Q_qDZjMeZjVP";

    private static final String QD = "https://data\\.qtx\\.com/basketball/lqqd/";

    private static final String QY = "https://data.qtx.com/basketball/lqqy/";

    private final Site site =Site.me().setRetryTimes(3).setSleepTime(1000);


    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        Selectable url = page.getUrl();
        if (url.regex(RUKOU_NBA).match()){
            List<String> all = html.xpath("//div[@class='whole_rank']//tr[@class='tc_score_value']//td[@class='tc_schedule_td1']/a/@href").all();
            page.addTargetRequests(all);
        }else if (url.regex(RUKOU_CBA).match()){
            List<String> all = html.xpath("//div[@class='whole_rank']//tr[@class='tc_score_value']//td[@class='tc_schedule_td2']/a/@href").all();
            page.addTargetRequests(all);
        } else if (url.regex(QD).match()){
            List<String> all = html.xpath("//div[@class='player_list']//div[@class='pos_item']/ul/li/a/@href").all();
            page.addTargetRequests(all);
        }else if (url.regex(QY).match()){
            //url
            String qyurl = url.toString();
            //姓名
            String chinaName = html.xpath("//div[@class='team_logo']/div[2]/div[1]/text()").toString();
            //英文名
            String exName = html.xpath("//div[@class='team_logo']/div[2]/div[2]/text()").toString();
            //全名
            String fullName = html.xpath("//div[@class='title_top player_page socer_play']/div[2]/div[1]/span/text()").toString();
            //球队
            String team = html.xpath("//div[@class='title_top player_page socer_play']/div[2]/div[2]/span/text()").toString();
            //位置
            String role = html.xpath("//div[@class='title_top player_page socer_play']/div[2]/div[3]/span/text()").toString();
            //球衣编号
            String number = html.xpath("//div[@class='title_top player_page socer_play']/div[2]/div[4]/span/text()").toString();
            //生日
            String birthday = html.xpath("//div[@class='title_top player_page socer_play']/div[3]/div[1]/span/text()").toString();
            //身高
            String height = html.xpath("//div[@class='title_top player_page socer_play']/div[3]/div[2]/span/text()").toString();
            //体重
            String weight = html.xpath("//div[@class='title_top player_page socer_play']/div[3]/div[3]/span/text()").toString();

            //常规赛
            //赛季
            String year = html.xpath("//div[@class='game_type'][2]/table/tbody/tr[3]/td[1]/text()").toString();
            //赛季球队
            String yearTeam = html.xpath("//div[@class='game_type'][2]/table/tbody/tr[3]/td[2]/text()").toString();
            //上场
            String appearance = html.xpath("//div[@class='game_type'][2]/table/tbody/tr[3]/td[3]/text()").toString();
            //首发
            String starter = html.xpath("//div[@class='game_type'][2]/table/tbody/tr[3]/td[4]/text()").toString();
            //时间
            String time = html.xpath("//div[@class='game_type'][2]/table/tbody/tr[3]/td[5]/text()").toString();
            //命中率
            String hitRate = html.xpath("//div[@class='game_type'][2]/table/tbody/tr[3]/td[6]/text()").toString();
            //三分
            String threePointer = html.xpath("//div[@class='game_type'][2]/table/tbody/tr[3]/td[7]/text()").toString();
            //罚球
            String penaltyKick = html.xpath("//div[@class='game_type'][2]/table/tbody/tr[3]/td[8]/text()").toString();
            //前板
            String before = html.xpath("//div[@class='game_type'][2]/table/tbody/tr[3]/td[9]/text()").toString();
            //后板
            String after = html.xpath("//div[@class='game_type'][2]/table/tbody/tr[3]/td[10]/text()").toString();
            //总篮板
            String rebound = html.xpath("//div[@class='game_type'][2]/table/tbody/tr[3]/td[11]/text()").toString();
            //助攻
            String assist = html.xpath("//div[@class='game_type'][2]/table/tbody/tr[3]/td[12]/text()").toString();
            //抢断
            String steal = html.xpath("//div[@class='game_type'][2]/table/tbody/tr[3]/td[13]/text()").toString();
            //盖帽
            String block = html.xpath("//div[@class='game_type'][2]/table/tbody/tr[3]/td[14]/text()").toString();
            //失误
            String fault = html.xpath("//div[@class='game_type'][2]/table/tbody/tr[3]/td[15]/text()").toString();
            //犯规
            String foul = html.xpath("//div[@class='game_type'][2]/table/tbody/tr[3]/td[16]/text()").toString();
            //得分
            String goal = html.xpath("//div[@class='game_type'][2]/table/tbody/tr[3]/td[17]/text()").toString();


            //季后赛
            //赛季
            String j_year = html.xpath("//div[@class='game_type'][3]/table/tbody/tr[3]/td[1]/text()").toString();
            //赛季球队
            String j_yearTeam = html.xpath("//div[@class='game_type'][3]/table/tbody/tr[3]/td[2]/text()").toString();
            //上场
            String j_appearance = html.xpath("//div[@class='game_type'][3]/table/tbody/tr[3]/td[3]/text()").toString();
            //首发
            String j_starter = html.xpath("//div[@class='game_type'][3]/table/tbody/tr[3]/td[4]/text()").toString();
            //时间
            String j_time = html.xpath("//div[@class='game_type'][3]/table/tbody/tr[3]/td[5]/text()").toString();
            //命中率
            String j_hitRate = html.xpath("//div[@class='game_type'][3]/table/tbody/tr[3]/td[6]/text()").toString();
            //三分
            String j_threePointer = html.xpath("//div[@class='game_type'][3]/table/tbody/tr[3]/td[7]/text()").toString();
            //罚球
            String j_penaltyKick = html.xpath("//div[@class='game_type'][3]/table/tbody/tr[3]/td[8]/text()").toString();
            //前板
            String j_before = html.xpath("//div[@class='game_type'][3]/table/tbody/tr[3]/td[9]/text()").toString();
            //后板
            String j_after = html.xpath("//div[@class='game_type'][3]/table/tbody/tr[3]/td[10]/text()").toString();
            //总篮板
            String j_rebound = html.xpath("//div[@class='game_type'][3]/table/tbody/tr[3]/td[11]/text()").toString();
            //助攻
            String j_assist = html.xpath("//div[@class='game_type'][3]/table/tbody/tr[3]/td[12]/text()").toString();
            //抢断
            String j_steal = html.xpath("//div[@class='game_type'][3]/table/tbody/tr[3]/td[13]/text()").toString();
            //盖帽
            String j_block = html.xpath("//div[@class='game_type'][3]/table/tbody/tr[3]/td[14]/text()").toString();
            //失误
            String j_fault = html.xpath("//div[@class='game_type'][3]/table/tbody/tr[3]/td[15]/text()").toString();
            //犯规
            String j_foul = html.xpath("//div[@class='game_type'][3]/table/tbody/tr[3]/td[16]/text()").toString();
            //得分
            String j_goal = html.xpath("//div[@class='game_type'][3]/table/tbody/tr[3]/td[17]/text()").toString();

            Information regular = new Information(year,yearTeam,appearance,starter,time,hitRate,threePointer,penaltyKick,before,after,rebound,assist,steal,block,fault,foul,goal);
            Information preseason = new Information(j_year,j_yearTeam,j_appearance,j_starter,j_time,j_hitRate,j_threePointer,j_penaltyKick,j_before,j_after,j_rebound,j_assist,j_steal,j_block,j_fault,j_foul,j_goal);
            Hoopster hoopster = new Hoopster(chinaName,exName,fullName,team,role,number,birthday,height,weight,qyurl,regular,preseason);
            page.putField("hoopster",hoopster);
//            mongoTemplate.insert(hoopster,"testc");

        }


    }

    public static void main(String[] args) {
        Spider.create(new Basketball())
                .addUrl("https://data.qtx.com/basketball/lqjfb/wXn6J872B8_LNY6kaZ709.html")
                .addUrl("https://data.qtx.com/basketball/lqjfb/O5zWeOj29Q_qDZjMeZjVP.html")
//                .addUrl("https://data.qtx.com/basketball/lqqd/gR7vwnAjNQ.html")
//                .addUrl("https://data.qtx.com/basketball/lqqy/Ew7NkVXjJ9.html")
                .addPipeline(new ConsolePipeline())
//                .addPipeline(new JsonFilePipeline("D:\\javatest"))
//                .addPipeline(new MongoPipeline())
                .thread(1)
                .run();
    }
}
