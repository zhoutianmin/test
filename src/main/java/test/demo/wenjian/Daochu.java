package test.demo.wenjian;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import test.pojo.SwaggerTest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@Api(value = "daochuexcel" , tags = "excel")
public class Daochu {

    @ApiOperation(value = "导出excel" , notes = "这是提示")
    @PostMapping(value = "/exportexcel")

    public void exportExcel(HttpServletResponse resp)  {
        //设置文件生成路径
        //数据库查询视图
        List<SwaggerTest> excel =new ArrayList<>() ;
        for (int i = 0; i < 3; i++) {
            SwaggerTest swaggerTest =new SwaggerTest();
            swaggerTest.setA("aaa"+i);
            swaggerTest.setI(0+i);
            excel.add(swaggerTest);
        }
        resp.reset();
        resp.setCharacterEncoding("UTF-8");
        //响应内容格式
        resp.setContentType("application/vnd.ms-excel");
        //设置文件名
        String fileName =System.currentTimeMillis() + ".xlsx";

        try {
            //设置前端下载文件名
            resp.setHeader("Content-disposition", "attachment;filename=" +new String(fileName.getBytes("UTF-8"), "GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
//            return BaseResponse.fail("导出失败");
        }

        try {
            //向前端写入文件流流
            EasyExcel.write(resp.getOutputStream(), SwaggerTest.class)
                    .sheet().doWrite(excel);
        } catch (IOException e) {
            e.printStackTrace();
//            return BaseResponse.fail("导出失败");
        }
//        return BaseResponse.Ok("导出成功");
    }


}
