package test.demo;

import com.google.common.io.Files;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

public class FileDemo {
    public static void main(String[] args) throws IOException {
        //绝对路径
//        File file1 = new File("C:/Users/ztm/Desktop/zl/新建文本文档.txt");
//        InputStreamReader read = new InputStreamReader(new FileInputStream(file1),"GBK");
//        BufferedReader reader=new BufferedReader(read);
//        System.out.println(reader.readLine());
         //相对路径
        File file2 = new File("src\\main\\java\\test\\demo\\新建文本文档.txt");
        List<String> strings = Files.readLines(file2, Charset.forName("utf-8"));
        strings.forEach(s -> {
            System.out.println(s);
        });
//        InputStreamReader read = new InputStreamReader(new FileInputStream(file2),"GBK");
//        BufferedReader reader=new BufferedReader(read);
//        System.out.println(reader.readLine());
    }


}
