package test.demo;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//import test.demo.pachong.User;
//import test.util.BeanUtil;
import test.demo.mongodbTest.entity.Hoopster;
import test.demo.mongodbTest.entity.SeasonData;
import test.demo.mongodbTest.entity.Statistics;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;

import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

//    User user = new User("aa","aavv");
    public void test(){
//        MongoTemplate bean = BeanUtil.getBean(MongoTemplate.class);
//        bean.insert(user,"testc");
    }

    ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor(

    );
    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\ztm\\Desktop\\实体\\实体\\football_personbak.xlsx");
            FileInputStream fileInputStream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            List<Map<String,String>> mapList = new ArrayList<>();
            //读取excel统计数据
            System.out.println(lastRowNum);

            FileWriter fw = new FileWriter("C:\\Users\\ztm\\Desktop\\shuju.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 1; i <= lastRowNum; i++) {
                StringBuffer sb=new StringBuffer();
                Row row = sheet.getRow(i);
                sb.append(row.getCell(0));
                sb.append("\t");
                for (int j = 1; j <=10; j++) {
                    if (row.getCell(j)!=null&&!"".equals(row.getCell(j))){
                        if (row.getCell(j).toString().indexOf(".") == -1){
                            sb.append(row.getCell(j)+",");
                        }
                    }
                }
                bw.write(sb.toString());
                bw.newLine();
            }


//            bw.write("\t");
//            bw.write("aaa");
//            bw.newLine();
//            bw.write("!hello world !");
//            bw.write("!hello world !");
            //使用缓冲区中的方法，将数据刷新到目的地文件中去。
            bw.flush();
            //关闭缓冲区,同时关闭了fw流对象
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static List<String> quchon(List<String> al) {
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

}
