package peaksoft.giftlistj7g2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SpringBootApplication
public class GiftListJ7G2Application {

    public static void main(String[] args) {
        SpringApplication.run(GiftListJ7G2Application.class, args);
        log.info("Hello from Gift-List-J7 application");
    }

    private String greet() {
        return "<h1 style=\"align-content: center; text-align: center;margin: 100px;\n" +
                " padding: 200px;  color:" +
                " #4caf50\"><font size=\"20\">Welcome to Gift List J7  Application !!! </font><h1/>";
    }

    @GetMapping("/")
    public String greetingPage() {
        return greet();
    }
}