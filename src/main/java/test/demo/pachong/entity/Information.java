package test.demo.pachong.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Information {
    private String year;
    private String yearTeam;
    private String appearance;
    private String starter;
    private String time;
    private String hitRate;
    private String threePointer;
    private String penaltyKick;
    private String before;
    private String after;
    private String rebound;
    private String assist;
    private String steal;
    private String block;
    private String fault;
    private String foul;
    private String goal;

}
