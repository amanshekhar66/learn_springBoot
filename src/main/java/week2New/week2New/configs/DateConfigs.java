package week2New.week2New.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class DateConfigs {
    @Bean
    public DateTimeFormatter getDateTimeFormatter(){
        return DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }
}
