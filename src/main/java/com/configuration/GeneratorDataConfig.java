package com.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Configuration
@PropertySource("application.yml")
@Setter
@Getter
@ToString
public class GeneratorDataConfig {

    @Value("#{'${hero}'.split(',')}")
    private List<String> heros;

    @Value("#{'${jhinLvl}'.split(',')}")
    private List<Double> jhinLvl;

    @Value("#{'${levels}'.split(',')}")
    private List<String> levels;

    @Bean(name="config")
    public GeneratorDataConfig generatorDataConfig() {
        return new GeneratorDataConfig();
    }
}