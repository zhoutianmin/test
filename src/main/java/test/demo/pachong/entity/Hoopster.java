package test.demo.pachong.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hoopster {
    private String chinaName;
    private String exName;
    private String fullName;
    private String team;
    private String role;
    private String number;
    private String birthday;
    private String height;
    private String weight;
    private String url;

    private Information regular;
    private Information preseason;

}
