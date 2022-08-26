package test.demo.mongodbTest.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.demo.mongodbTest.entity.Hoopster;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
@Api(value = "mongodb" , tags = "mongo")
public class MongoDbTestController {

    @Resource
    private MongoTemplate mongoTemplate;

    @ApiOperation(value = "ceshitemp" , notes = "这是提示")
    @PostMapping(value = "/ceshitemp")
    public void ceshitemp(){
//        Criteria criteria = new Criteria().orOperator(Criteria.where("chinaName").is(name),Criteria.where("exName").is(name),Criteria.where("fullName").is(name));
//        Query query = Query.query(criteria);
        //修改
//            Update update = Update.update("ceshixiugai","");
//            mongoTemplate.updateFirst(query,update,"hoopster");
        //新增
//        Map<String,String> map =new HashMap();
//        JSONObject jsonObject = new JSONObject();
//        List<Map> a = new ArrayList<>();
//        Map map1 = new HashMap();
//        map1.put("ttt","tttt");
//        map1.put("yyy","yyy");
//        Map map2 = new HashMap();
//        map2.put("bb","bbb");
//        map2.put("ee","eee");
//        a.add(map1);
////        a.add(map2);
//        map.put("rrrr","rrrrrrr");
//        jsonObject.put("cao",map);
//        jsonObject.put("exName","gc");
//        jsonObject.put("fullName","gc");
//        jsonObject.put("chinaName","隔窗");
//        jsonObject.put("@id",a);
//
//        mongoTemplate.insert(jsonObject,"hoopster");
        //查询
//        Criteria criteriaa = Criteria.where("cao.rrrr").is("rrrrrrr");
        Criteria criteriaa = new Criteria().orOperator(Criteria.where("@id").elemMatch(Criteria.where("ttt").is("tttt")));
        Query querya = new Query(criteriaa);
        List<JSONObject> jsonObjects = mongoTemplate.find(querya, JSONObject.class,"hoopster");
        String jsonStr = jsonObjects.get(0).getJSONObject("@id").toJSONString();
        System.out.println(jsonStr);
//        jsonStr = jsonStr.replace("=", "\"=\"");
//        jsonStr = jsonStr.replace("{", "{\"");
//        jsonStr = jsonStr.replace(":", "\":\"");
//        jsonStr = jsonStr.replace(", ", "\",\"");
//        jsonStr = jsonStr.replace("}", "\"}");
        System.out.println(jsonStr);
        JSONArray objects = JSONArray.parseArray(jsonStr);
        Object[] objects1 = objects.toArray();
        System.out.println(objects1);
    }


    @ApiOperation(value = "查询mongo数据" , notes = "这是提示")
    @PostMapping(value = "/queryMongo")
    public List<Hoopster> queryMongo(@RequestParam(name = "name" ,required = false) String name){
        List<Hoopster> hoopster = new ArrayList<>();
        if (name==null){
            Criteria criteria = new Criteria();
            Query query = Query.query(criteria);
            long count = mongoTemplate.count(query, Hoopster.class);
            long size = count / 10;
            long l = count % 10;
            if (l>0){
                size += 1;
            }
            System.out.println(count);
            System.out.println(size);
            System.out.println(l);
            Pageable pageable = PageRequest.of(1, 10, Sort.by(Sort.Order.desc("date")));
            query = new Query().with(pageable);
            hoopster = mongoTemplate.find(query, Hoopster.class, "hoopster");
            System.out.println(hoopster);
            return hoopster;
        }else{
            Criteria criteria = new Criteria().orOperator(Criteria.where("chinaName").is(name),Criteria.where("exName").is(name),Criteria.where("fullName").is(name));
            Query query = Query.query(criteria);
            //修改
//            Update update = Update.update("ceshixiugai","");
//            mongoTemplate.updateFirst(query,update,"hoopster");
            //新增
            Map<String,String> map =new HashMap();
            JSONObject jsonObject = new JSONObject();
            List<String> a = new ArrayList<>();
            a.add("aaaaa");
            a.add("bbbbb");
            jsonObject.put("exName","tianminzhou");
            jsonObject.put("fullName","zhoutianming");
            jsonObject.put("chinaName","周天民");
            jsonObject.put("@id",a);
            map.put("exName","tianminzhou");
            map.put("fullName","周天民");
            map.put("chinaName","周天民");
            map.put("hahah","");
////            mongoTemplate.save(map,"hoopster");
            mongoTemplate.insert(jsonObject,"hoopster");
            //查询
            Criteria criteriaa = Criteria.where("@id").elemMatch(Criteria.where("aa").is("aaa"));
            Query querya = new Query(criteria);
            List<JSONObject> jsonObjects = mongoTemplate.find(query, JSONObject.class,"hoopster");
            System.out.println(jsonObjects.get(0).get("birthday"));
            long count = mongoTemplate.count(query, Hoopster.class);
            long size = count / 10;
            long l = count % 10;
            if (l>0){
                size += 1;
            }
            //设置分页
            Pageable pageable = PageRequest.of(1, 2, Sort.by(Sort.Order.desc("date")));
            query = new Query().with(pageable);
            query.addCriteria(criteria);
            //查询
            hoopster = mongoTemplate.find(query, Hoopster.class, "hoopster");
            return hoopster;
        }
    }

    @ApiOperation(value = "合并" , notes = "这是提示")
    @PostMapping(value = "/hebing")
    public List<Hoopster> hebing(){
        try {
            File file = new File("C:\\Users\\ztm\\Desktop\\cba\\人物统计2021-2022.xlsx");
            FileInputStream fileInputStream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            List<Map<String,String>> mapList = new ArrayList<>();
            //读取excel人物统计数据
            for (int i = 1; i <= lastRowNum; i++) {
                Map<String,String> renwu = new HashMap<>();
                if (sheet.getRow(i).getCell(0)!=null){
                    renwu.put("id",new BigDecimal(sheet.getRow(i).getCell(0)+"").toString());
                }
                if (sheet.getRow(i).getCell(1)!=null){
                    renwu.put("assistRate",new BigDecimal(sheet.getRow(i).getCell(1)+"").toString());
                }
                if (sheet.getRow(i).getCell(2)!=null){
                    renwu.put("assistToTurnoverRatio",new BigDecimal(sheet.getRow(i).getCell(2)+"").toString());
                }
                if (sheet.getRow(i).getCell(3)!=null){
                    renwu.put("assists",new BigDecimal(sheet.getRow(i).getCell(3)+"").toString());
                }
                if (sheet.getRow(i).getCell(4)!=null){
                    renwu.put("assistsAverage",new BigDecimal(sheet.getRow(i).getCell(4)+"").toString());
                }
                if (sheet.getRow(i).getCell(5)!=null){
                    renwu.put("blocked",new BigDecimal(sheet.getRow(i).getCell(5)+"").toString());
                }
                if (sheet.getRow(i).getCell(6)!=null){
                    renwu.put("blockedAverage",new BigDecimal(sheet.getRow(i).getCell(6)+"").toString());
                }
                if (sheet.getRow(i).getCell(7)!=null){
                    renwu.put("blockedRate",new BigDecimal(sheet.getRow(i).getCell(7)+"").toString());
                }
                if (sheet.getRow(i).getCell(8)!=null){
                    renwu.put("blocksReceived",new BigDecimal(sheet.getRow(i).getCell(8)+"").toString());
                }
                if (sheet.getRow(i).getCell(9)!=null){
                    renwu.put("competitionId",new BigDecimal(sheet.getRow(i).getCell(9)+"").toString());
                }
                if (sheet.getRow(i).getCell(10)!=null){
                    renwu.put("disqualifications",new BigDecimal(sheet.getRow(i).getCell(10)+"").toString());
                }
                if (sheet.getRow(i).getCell(11)!=null){
                    renwu.put("doubleDoubles",new BigDecimal(sheet.getRow(i).getCell(11)+"").toString());
                }
                if (sheet.getRow(i).getCell(12)!=null){
                    renwu.put("dunks",new BigDecimal(sheet.getRow(i).getCell(12)+"").toString());
                }
                if (sheet.getRow(i).getCell(13)!=null){
                    renwu.put("effectiveFieldGoalPercentage",new BigDecimal(sheet.getRow(i).getCell(13)+"").toString());
                }
                if (sheet.getRow(i).getCell(14)!=null){
                    renwu.put("ejections",new BigDecimal(sheet.getRow(i).getCell(14)+"").toString());
                }
                if (sheet.getRow(i).getCell(15)!=null){
                    renwu.put("fastBreakAttempted",new BigDecimal(sheet.getRow(i).getCell(15)+"").toString());
                }
                if (sheet.getRow(i).getCell(16)!=null){
                    renwu.put("fastBreakPoints",new BigDecimal(sheet.getRow(i).getCell(16)+"").toString());
                }
                if (sheet.getRow(i).getCell(17)!=null){
                    renwu.put("fastBreaks",new BigDecimal(sheet.getRow(i).getCell(17)+"").toString());
                }
                if (sheet.getRow(i).getCell(18)!=null){
                    renwu.put("fieldGoals",new BigDecimal(sheet.getRow(i).getCell(18)+"").toString());
                }
                if (sheet.getRow(i).getCell(19)!=null){
                    renwu.put("fieldGoalsAtRimAttempted",new BigDecimal(sheet.getRow(i).getCell(19)+"").toString());
                }
                if (sheet.getRow(i).getCell(20)!=null){
                    renwu.put("fieldGoalsAtRimAttemptedAverage",new BigDecimal(sheet.getRow(i).getCell(20)+"").toString());
                }
                if (sheet.getRow(i).getCell(21)!=null){
                    renwu.put("fieldGoalsAtRimMade",new BigDecimal(sheet.getRow(i).getCell(21)+"").toString());
                }
                if (sheet.getRow(i).getCell(22)!=null){
                    renwu.put("fieldGoalsAtRimMadeAverage",new BigDecimal(sheet.getRow(i).getCell(22)+"").toString());
                }
                if (sheet.getRow(i).getCell(23)!=null){
                    renwu.put("fieldGoalsAtRimPercentage",new BigDecimal(sheet.getRow(i).getCell(23)+"").toString());
                }
                if (sheet.getRow(i).getCell(24)!=null){
                    renwu.put("fieldGoalsAtRimPercentageAverage",new BigDecimal(sheet.getRow(i).getCell(24)+"").toString());
                }
                if (sheet.getRow(i).getCell(25)!=null){
                    renwu.put("fieldGoalsAttempted",new BigDecimal(sheet.getRow(i).getCell(25)+"").toString());
                }
                if (sheet.getRow(i).getCell(26)!=null){
                    renwu.put("fieldGoalsAttemptedAverage",new BigDecimal(sheet.getRow(i).getCell(26)+"").toString());
                }
                if (sheet.getRow(i).getCell(27)!=null){
                    renwu.put("fieldGoalsAverage",new BigDecimal(sheet.getRow(i).getCell(27)+"").toString());
                }
                if (sheet.getRow(i).getCell(28)!=null){
                    renwu.put("fieldGoalsMidRangeAttempted",new BigDecimal(sheet.getRow(i).getCell(28)+"").toString());
                }
                if (sheet.getRow(i).getCell(29)!=null){
                    renwu.put("fieldGoalsMidRangeAttemptedAverage",new BigDecimal(sheet.getRow(i).getCell(29)+"").toString());
                }
                if (sheet.getRow(i).getCell(30)!=null){
                    renwu.put("fieldGoalsMidRangeMade",new BigDecimal(sheet.getRow(i).getCell(30)+"").toString());
                }
                if (sheet.getRow(i).getCell(31)!=null){
                    renwu.put("fieldGoalsMidRangeMadeAverage",new BigDecimal(sheet.getRow(i).getCell(31)+"").toString());
                }
                if (sheet.getRow(i).getCell(32)!=null){
                    renwu.put("fieldGoalsMidRangePercentage",new BigDecimal(sheet.getRow(i).getCell(32)+"").toString());
                }
                if (sheet.getRow(i).getCell(33)!=null){
                    renwu.put("fieldGoalsMidRangePercentageAverage",new BigDecimal(sheet.getRow(i).getCell(33)+"").toString());
                }
                if (sheet.getRow(i).getCell(34)!=null){
                    renwu.put("fieldGoalsPercentage",new BigDecimal(sheet.getRow(i).getCell(34)+"").toString());
                }
                if (sheet.getRow(i).getCell(35)!=null){
                    renwu.put("fieldGoalsPercentageAverage",new BigDecimal(sheet.getRow(i).getCell(35)+"").toString());
                }
                if (sheet.getRow(i).getCell(36)!=null){
                    renwu.put("figureId",new BigDecimal(sheet.getRow(i).getCell(36)+"").toString());
                }
                if (sheet.getRow(i).getCell(37)!=null){
                    renwu.put("figureName",sheet.getRow(i).getCell(37).toString());
                }
                if (sheet.getRow(i).getCell(38)!=null){
                    renwu.put("flagrantFouls",new BigDecimal(sheet.getRow(i).getCell(38)+"").toString());
                }
                if (sheet.getRow(i).getCell(39)!=null){
                    renwu.put("foreignAided",new BigDecimal(sheet.getRow(i).getCell(39)+"").toString());
                }
                if (sheet.getRow(i).getCell(40)!=null){
                    renwu.put("foulsReceived",new BigDecimal(sheet.getRow(i).getCell(40)+"").toString());
                }
                if (sheet.getRow(i).getCell(41)!=null){
                    renwu.put("freeThrows",new BigDecimal(sheet.getRow(i).getCell(41)+"").toString());
                }
                if (sheet.getRow(i).getCell(42)!=null){
                    renwu.put("freeThrowsAttempted",new BigDecimal(sheet.getRow(i).getCell(42)+"").toString());
                }
                if (sheet.getRow(i).getCell(43)!=null){
                    renwu.put("freeThrowsAttemptedAverage",new BigDecimal(sheet.getRow(i).getCell(43)+"").toString());
                }
                if (sheet.getRow(i).getCell(44)!=null){
                    renwu.put("freeThrowsAverage",new BigDecimal(sheet.getRow(i).getCell(44)+"").toString());
                }
                if (sheet.getRow(i).getCell(45)!=null){
                    renwu.put("freeThrowsPercentage",new BigDecimal(sheet.getRow(i).getCell(45)+"").toString());
                }
                if (sheet.getRow(i).getCell(46)!=null){
                    renwu.put("freeThrowsPercentageAverage",new BigDecimal(sheet.getRow(i).getCell(46)+"").toString());
                }
                if (sheet.getRow(i).getCell(47)!=null){
                    renwu.put("games",new BigDecimal(sheet.getRow(i).getCell(47)+"").toString());
                }
                if (sheet.getRow(i).getCell(48)!=null){
                    renwu.put("gamesStarted",new BigDecimal(sheet.getRow(i).getCell(48)+"").toString());
                }
                if (sheet.getRow(i).getCell(49)!=null){
                    renwu.put("highGamePoints",new BigDecimal(sheet.getRow(i).getCell(49)+"").toString());
                }
                if (sheet.getRow(i).getCell(50)!=null){
                    renwu.put("itemId",new BigDecimal(sheet.getRow(i).getCell(50)+"").toString());
                }
                if (sheet.getRow(i).getCell(51)!=null){
                    renwu.put("matchType",new BigDecimal(sheet.getRow(i).getCell(51)+"").toString());
                }
                if (sheet.getRow(i).getCell(52)!=null){
                    renwu.put("minutes",new BigDecimal(sheet.getRow(i).getCell(52)+"").toString());
                }
                if (sheet.getRow(i).getCell(53)!=null){
                    renwu.put("minutesAverage",new BigDecimal(sheet.getRow(i).getCell(53)+"").toString());
                }
                if (sheet.getRow(i).getCell(54)!=null){
                    renwu.put("number",new BigDecimal(sheet.getRow(i).getCell(54)+"").toString());
                }
                if (sheet.getRow(i).getCell(55)!=null){
                    renwu.put("personalFouls",new BigDecimal(sheet.getRow(i).getCell(55)+"").toString());
                }
                if (sheet.getRow(i).getCell(56)!=null){
                    renwu.put("personalFoulsAverage",new BigDecimal(sheet.getRow(i).getCell(56)+"").toString());
                }
                if (sheet.getRow(i).getCell(57)!=null){
                    renwu.put("playerEfficiencyRating",new BigDecimal(sheet.getRow(i).getCell(57)+"").toString());
                }
                if (sheet.getRow(i).getCell(58)!=null){
                    renwu.put("plusMinus",new BigDecimal(sheet.getRow(i).getCell(58)+"").toString());
                }
                if (sheet.getRow(i).getCell(59)!=null){
                    renwu.put("points",new BigDecimal(sheet.getRow(i).getCell(59)+"").toString());
                }
                if (sheet.getRow(i).getCell(60)!=null){
                    renwu.put("pointsAverage",new BigDecimal(sheet.getRow(i).getCell(60)+"").toString());
                }
                if (sheet.getRow(i).getCell(61)!=null){
                    renwu.put("positionDescription",sheet.getRow(i).getCell(61).toString());
                }
                if (sheet.getRow(i).getCell(62)!=null){
                    renwu.put("ratingsDefensive",new BigDecimal(sheet.getRow(i).getCell(62)+"").toString());
                }
                if (sheet.getRow(i).getCell(63)!=null){
                    renwu.put("ratingsOffensive",new BigDecimal(sheet.getRow(i).getCell(63)+"").toString());
                }
                if (sheet.getRow(i).getCell(64)!=null){
                    renwu.put("reboundPercentageDefensive",new BigDecimal(sheet.getRow(i).getCell(64)+"").toString());
                }
                if (sheet.getRow(i).getCell(65)!=null){
                    renwu.put("reboundPercentageOffensive",new BigDecimal(sheet.getRow(i).getCell(65)+"").toString());
                }
                if (sheet.getRow(i).getCell(66)!=null){
                    renwu.put("reboundPercentageTotal",new BigDecimal(sheet.getRow(i).getCell(66)+"").toString());
                }
                if (sheet.getRow(i).getCell(67)!=null){
                    renwu.put("rebounds",new BigDecimal(sheet.getRow(i).getCell(67)+"").toString());
                }
                if (sheet.getRow(i).getCell(68)!=null){
                    renwu.put("reboundsAverage",new BigDecimal(sheet.getRow(i).getCell(68)+"").toString());
                }
                if (sheet.getRow(i).getCell(69)!=null){
                    renwu.put("reboundsDefensive",new BigDecimal(sheet.getRow(i).getCell(69)+"").toString());
                }
                if (sheet.getRow(i).getCell(70)!=null){
                    renwu.put("reboundsDefensiveAverage",new BigDecimal(sheet.getRow(i).getCell(70)+"").toString());
                }
                if (sheet.getRow(i).getCell(71)!=null){
                    renwu.put("reboundsOffensive",new BigDecimal(sheet.getRow(i).getCell(71)+"").toString());
                }
                if (sheet.getRow(i).getCell(72)!=null){
                    renwu.put("reboundsOffensiveAverage",new BigDecimal(sheet.getRow(i).getCell(72)+"").toString());
                }
                if (sheet.getRow(i).getCell(73)!=null){
                    renwu.put("seasonId",new BigDecimal(sheet.getRow(i).getCell(73)+"").toString());
                }
                if (sheet.getRow(i).getCell(74)!=null){
                    renwu.put("seconds",new BigDecimal(sheet.getRow(i).getCell(74)+"").toString());
                }
                if (sheet.getRow(i).getCell(75)!=null){
                    renwu.put("stealRate",new BigDecimal(sheet.getRow(i).getCell(75)+"").toString());
                }
                if (sheet.getRow(i).getCell(76)!=null){
                    renwu.put("steals",new BigDecimal(sheet.getRow(i).getCell(76)+"").toString());
                }
                if (sheet.getRow(i).getCell(77)!=null){
                    renwu.put("stealsAverage",new BigDecimal(sheet.getRow(i).getCell(77)+"").toString());
                }
                if (sheet.getRow(i).getCell(78)!=null){
                    renwu.put("technicalFouls",new BigDecimal(sheet.getRow(i).getCell(78)+"").toString());
                }
                if (sheet.getRow(i).getCell(79)!=null){
                    renwu.put("technicalFoulsCoach",new BigDecimal(sheet.getRow(i).getCell(79)+"").toString());
                }
                if (sheet.getRow(i).getCell(80)!=null){
                    renwu.put("threePointAttempted",new BigDecimal(sheet.getRow(i).getCell(80)+"").toString());
                }
                if (sheet.getRow(i).getCell(81)!=null){
                    renwu.put("threePointAttemptedAverage",new BigDecimal(sheet.getRow(i).getCell(81)+"").toString());
                }
                if (sheet.getRow(i).getCell(82)!=null){
                    renwu.put("threePointGoals",new BigDecimal(sheet.getRow(i).getCell(82)+"").toString());
                }
                if (sheet.getRow(i).getCell(83)!=null){
                    renwu.put("threePointGoalsAverage",new BigDecimal(sheet.getRow(i).getCell(83)+"").toString());
                }
                if (sheet.getRow(i).getCell(84)!=null){
                    renwu.put("threePointPercentage",new BigDecimal(sheet.getRow(i).getCell(84)+"").toString());
                }
                if (sheet.getRow(i).getCell(85)!=null){
                    renwu.put("threePointPercentageAverage",new BigDecimal(sheet.getRow(i).getCell(85)+"").toString());
                }
                if (sheet.getRow(i).getCell(86)!=null){
                    renwu.put("tripleDoubles",new BigDecimal(sheet.getRow(i).getCell(86)+"").toString());
                }
                if (sheet.getRow(i).getCell(87)!=null){
                    renwu.put("trueShootingPercentage",new BigDecimal(sheet.getRow(i).getCell(87)+"").toString());
                }
                if (sheet.getRow(i).getCell(88)!=null){
                    renwu.put("turnoverRate",new BigDecimal(sheet.getRow(i).getCell(88)+"").toString());
                }
                if (sheet.getRow(i).getCell(89)!=null){
                    renwu.put("turnovers",new BigDecimal(sheet.getRow(i).getCell(89)+"").toString());
                }
                if (sheet.getRow(i).getCell(90)!=null){
                    renwu.put("turnoversAverage",new BigDecimal(sheet.getRow(i).getCell(90)+"").toString());
                }
                if (sheet.getRow(i).getCell(91)!=null){
                    renwu.put("twoPointAttempted",new BigDecimal(sheet.getRow(i).getCell(91)+"").toString());
                }
                if (sheet.getRow(i).getCell(92)!=null){
                    renwu.put("twoPointAttemptedAverage",new BigDecimal(sheet.getRow(i).getCell(92)+"").toString());
                }
                if (sheet.getRow(i).getCell(93)!=null){
                    renwu.put("twoPointGoals",new BigDecimal(sheet.getRow(i).getCell(93)+"").toString());
                }
                if (sheet.getRow(i).getCell(94)!=null){
                    renwu.put("twoPointGoalsAverage",new BigDecimal(sheet.getRow(i).getCell(94)+"").toString());
                }
                if (sheet.getRow(i).getCell(95)!=null){
                    renwu.put("twoPointPercentage",new BigDecimal(sheet.getRow(i).getCell(95)+"").toString());
                }
                if (sheet.getRow(i).getCell(96)!=null){
                    renwu.put("twoPointPercentageAverage",new BigDecimal(sheet.getRow(i).getCell(96)+"").toString());
                }
                if (sheet.getRow(i).getCell(97)!=null){
                    renwu.put("usagePercentage",new BigDecimal(sheet.getRow(i).getCell(97)+"").toString());
                }
                if (sheet.getRow(i).getCell(98)!=null){
                    renwu.put("winSharesDefensive",new BigDecimal(sheet.getRow(i).getCell(98)+"").toString());
                }
                if (sheet.getRow(i).getCell(99)!=null){
                    renwu.put("winSharesOffensive",new BigDecimal(sheet.getRow(i).getCell(99)+"").toString());
                }
                if (sheet.getRow(i).getCell(100)!=null){
                    renwu.put("winSharesTotal",new BigDecimal(sheet.getRow(i).getCell(100)+"").toString());
                }
                if (sheet.getRow(i).getCell(101)!=null){
                    renwu.put("createTime",sheet.getRow(i).getCell(101).toString());
                }
                if (sheet.getRow(i).getCell(102)!=null){
                    renwu.put("editTime",sheet.getRow(i).getCell(102).toString());
                }
                mapList.add(renwu);
            }
            //分名字去重后集合
            //        人名       statics     类型        属性
            List<Map<String,Map<String,Map<String,Map<String,String>>>>> namelsit = new ArrayList<>();
            //循环将球员数据去重按类型归类
            for (Map<String, String> next : mapList) {
                //球员名称
                String figureName = next.get("figureName");
                //判断集合中有没有该球员数据变量
                int a = 0;
                //判断集合中有没有该球员数据,如果有
                for (Map<String, Map<String, Map<String, Map<String, String>>>> qiuy : namelsit) {
                    //如果该球员数据下面有数据,直接修改
                    if (qiuy.get(figureName) != null) {
                        a = 1;
                        //该球员数据  statistics   类型       数据
                        Map<String, Map<String, Map<String, String>>> qiuyMap = qiuy.get(figureName);
                        //类型
                        Map<String, Map<String, String>> statistics = qiuyMap.get("statistics");
                        //常规赛
                        if ("1".equals(next.get("matchType"))) {
                            statistics.put("regular", next);
                        }//季后赛
                        else if ("2".equals(next.get("matchType"))) {
                            statistics.put("playoff", next);
                        }//季前赛
                        else if ("3".equals(next.get("matchType"))) {
                            statistics.put("Preseason", next);
                        }
                        break;
                    }
                }
                //当集合中没有该球员数据
                if(a==0){
                    Map<String,Map<String,Map<String,Map<String,String>>>> qiuyMap =new HashMap<>();
                    Map<String, Map<String, Map<String, String>>> statisticsMap=new HashMap<>();
                    //类型
                    Map<String, Map<String, String>> leixin = new HashMap<>();
                    //常规赛
                    if ("1".equals(next.get("matchType"))) {
                        leixin.put("regular", next);
                    }//季后赛
                    else if ("2".equals(next.get("matchType"))) {
                        leixin.put("playoff", next);
                    }//季前赛
                    else if ("3".equals(next.get("matchType"))) {
                        leixin.put("Preseason", next);
                    }
                    statisticsMap.put("statistics",leixin);
                    qiuyMap.put(next.get("figureName"),statisticsMap);
                    //新增该球员数据
                    namelsit.add(qiuyMap);
                }
            }
            //查询库里已有球员
//            List<Hoopster> hoopster = mongoTemplate.findAll(Hoopster.class, "hoopster");
            //库中没有球员id集合
            List<String> nolist = new ArrayList<>();
            //循环excel球员判断球员是否在人物表中存在,不存在就记录下来
            for (Map<String, Map<String, Map<String, Map<String, String>>>> stringMapMap : namelsit) {
                String name = stringMapMap.keySet().toArray()[0].toString();
                Criteria criteria = new Criteria().orOperator(Criteria.where("chinaName").is(name),Criteria.where("exName").is(name),Criteria.where("fullName").is(name));
                Query query = Query.query(criteria);
                List<Hoopster> hoopster = mongoTemplate.find(query, Hoopster.class, "hoopster");
                if (hoopster.size()==0){
                    if (stringMapMap.get(name).get("statistics").get("regular")!=null){
                        //取到人物id添加到集合中
                        String noName = stringMapMap.get(name).get("statistics").get("regular").get("figureId");
                        nolist.add(noName);
                    }else if (stringMapMap.get(name).get("statistics").get("playoff")!=null){
                        String noName = stringMapMap.get(name).get("statistics").get("playoff").get("figureId");
                        nolist.add(noName);
                    }else if (stringMapMap.get(name).get("statistics").get("Preseason")!=null){
                        String noName = stringMapMap.get(name).get("statistics").get("Preseason").get("figureId");
                        nolist.add(noName);
                    }
                }
            }
            System.out.println("quchonqian"+namelsit.size());
            System.out.println("meiyd"+nolist.size());
            //删除掉没有的人物
//            Iterator<Map<String, Map<String, Map<String, Map<String, String>>>>> iterator = namelsit.iterator();
//            while (iterator.hasNext()){
//                String name = iterator.next().keySet().toArray()[0].toString();
//                Criteria criteria = new Criteria().orOperator(Criteria.where("chinaName").is(name),Criteria.where("exName").is(name),Criteria.where("fullName").is(name));
//                Query query = Query.query(criteria);
//                List<Hoopster> hoopster = mongoTemplate.find(query, Hoopster.class, "hoopster");
//                if (hoopster.size()==0){
//                    iterator.remove();
//                }
//            }
//            System.out.println("quchong"+namelsit.size());
//            for (Map<String, Map<String, Map<String, Map<String, String>>>> stringMapMap : namelsit) {
//                Criteria criteria = new Criteria();
//                criteria.and("@id").is("aaaaa");
//                Query query = new Query(criteria);
//                Map<String,String> adsda = new HashMap<>();
//                adsda.put("aaa","sads");
//                adsda.put("fds","fdas");
//                Update update = Update.update("statistics",adsda);
//                mongoTemplate.updateFirst(query,update,"hoopster");
//
//            }
            //读取人物excel
            File renwufile = new File("C:\\Users\\ztm\\Desktop\\cba\\人物.xlsx");
            FileInputStream renwufileInputStream = new FileInputStream(renwufile);
            XSSFWorkbook renwuworkbook = new XSSFWorkbook(renwufileInputStream);
            Sheet renwusheet = renwuworkbook.getSheetAt(0);
            int renwuRowNum = renwusheet.getLastRowNum();
            //读取队伍人物excel
            File duiwurenwufile = new File("C:\\Users\\ztm\\Desktop\\cba\\队伍人物.xlsx");
            FileInputStream duiwurenwufileInputStream = new FileInputStream(duiwurenwufile);
            XSSFWorkbook duiwurenwuworkbook = new XSSFWorkbook(duiwurenwufileInputStream);
            Sheet duiwurenwusheet = duiwurenwuworkbook.getSheetAt(0);
            int duiwurenwuRowNum = duiwurenwusheet.getLastRowNum();
            //需要人员所属球队id map<球员id,球队id>   //Todo 还要排除掉不管的球队
            Map<String,String> duiwuidmap = new HashMap<>();
            for (int i = 2; i <= duiwurenwuRowNum; i++) {
                if (nolist.contains(duiwurenwusheet.getRow(i).getCell(7).toString())){
                    duiwuidmap.put(duiwurenwusheet.getRow(i).getCell(7).toString(),duiwurenwusheet.getRow(i).getCell(8).toString());
                }
            }
            //读取队伍excel
            File duiwufile = new File("C:\\Users\\ztm\\Desktop\\cba\\队伍人物.xlsx");
            FileInputStream duiwufileInputStream = new FileInputStream(duiwufile);
            XSSFWorkbook duiwuworkbook = new XSSFWorkbook(duiwufileInputStream);
            Sheet duiwusheet = duiwuworkbook.getSheetAt(0);
            int duiwuRowNum = duiwusheet.getLastRowNum();
            for (int i = 2; i <= duiwuRowNum; i++) {

            }
            //新增人物集合
            List<Map<String,String>> renwulist = new ArrayList<>();
            for (int i = 2; i <= renwuRowNum; i++) {
                //判断该球员是否需要新增
                if (nolist.contains(renwusheet.getRow(i).getCell(0).toString())){
                    Map<String,Object> renwu = new HashMap<>();
                    renwu.put("image","");
                    renwu.put("role",renwusheet.getRow(i).getCell(12).toString());
                    renwu.put("@type","Person");
                    renwu.put("show","");
                    renwu.put("weight",renwusheet.getRow(i).getCell(31)+" kg");
                    renwu.put("updateTime","2022-07-17");
                    List<String> alternateName = new ArrayList<>();
                    if (renwusheet.getRow(i).getCell(20)!=null){
                        alternateName.add(renwusheet.getRow(i).getCell(20).toString());
                    }
                    if (renwusheet.getRow(i).getCell(24)!=null){
                        alternateName.add(renwusheet.getRow(i).getCell(24).toString());
                    }
                    List<String> quchon = quchon(alternateName);
                    renwu.put("alternateName",quchon);
                    renwu.put("NbaYear","");
                    renwu.put("salary","");
                    renwu.put("@context","");
                    if (renwusheet.getRow(i).getCell(6)!=null){
                        renwu.put("birthDate",renwusheet.getRow(i).getCell(6).toString());
                    }else {
                        renwu.put("birthDate","");
                    }
                    renwu.put("numberedPosition",renwusheet.getRow(i).getCell(30).toString());
                    renwu.put("url","");
                    renwu.put("cnName",renwusheet.getRow(i).getCell(24).toString());
                    if (renwusheet.getRow(i).getCell(19)!=null){
                        renwu.put("nationality",renwusheet.getRow(i).getCell(19).toString());
                    }else {
                        renwu.put("nationality","");
                    }
                    renwu.put("homeLocation","");
                    renwu.put("name","");
                    renwu.put("@id",renwusheet.getRow(i).getCell(0).toString());
                    renwu.put("sportsDiscipline","篮球");
                    renwu.put("height",renwusheet.getRow(i).getCell(15).toString());
                    renwu.put("memberOf","");
                }
            }





            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> quchon(List<String> al) {
        for (int i = 0; i < al.size(); i++) {
            for (int j = i + 1; j < al.size(); j++) {
                if (al.get(i) == al.get(j)) {
                    al.remove(j);
                    continue;
                } else {
                }
            }
        }
        return al;
    }



    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\ztm\\Desktop\\cba\\人物统计2021-2022.xlsx");
            FileInputStream fileInputStream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            List<Map<String,String>> mapList = new ArrayList<>();
            //读取excel统计数据

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
