package nl.smallproject.www.conferencedemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("api/test")
    public String getString() {
        return "Here is the test demo";
    }
}
