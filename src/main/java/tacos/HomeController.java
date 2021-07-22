package tacos;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;

@Configuration
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "home";
    }
}
