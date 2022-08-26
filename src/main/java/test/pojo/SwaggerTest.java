package test.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "对象名", description ="对象描述")
@Data
public class SwaggerTest {
    @ExcelProperty("aaa")
    private Integer i;
    private String a;
}
