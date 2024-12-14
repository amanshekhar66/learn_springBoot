package week2New.week2New.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfigs {
    @Bean
    public ModelMapper getModeMapper(){
        return new ModelMapper();
    }
}
