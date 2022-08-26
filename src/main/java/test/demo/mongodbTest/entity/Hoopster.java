package test.demo.mongodbTest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hoopster {
    private String id;
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

    private SeasonData regular;
    private SeasonData preseason;

}
