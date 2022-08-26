package test.demo.wenjian;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;

public class Excel {
    public static void main(String[] args) {
        File file = new File("");
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        String s = workbook.getSheetAt(1).getRow(1).getCell(1).toString();
        System.out.println(s);
    }
}
