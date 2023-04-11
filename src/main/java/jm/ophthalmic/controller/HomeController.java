package jm.ophthalmic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("location")
    public String location(){
        return "location";
    }
    @GetMapping("info")
    public String info(){
        return "information";
    }
    @GetMapping("lasik")
    public String lasik(){
        return "lasik";
    }
    @GetMapping("implantable")
    public String implantable(){
        return "implantable";
    }
    @GetMapping("dryeye")
    public String dryeye(){
        return "dryeye";
    }
    @GetMapping("oldeye")
    public String oldeye(){
        return "oldeye";
    }
    @GetMapping("cataract")
    public String cataract(){
        return "cataract";
    }
}
