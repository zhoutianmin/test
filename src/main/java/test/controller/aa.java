package test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/a")
public class aa {
    @GetMapping("/aa")
    public String aa(){
        System.out.println("aa");
        return "aaa";
    }
}
