package com.task.programming.municipalities.municipality;

import com.task.programming.municipalities.schedule.Schedule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class MunicipalityConfiguration {

    //    Loading fake database data
    @Bean
    CommandLineRunner commandLineRunner(MunicipalityRepository municipalityRepository) {
        return args -> {
            List<Municipality> municipalities = new ArrayList<>();
            municipalities.add(new Municipality("Copenhagen", Schedule.YEARLY, LocalDate.of(2020, Month.JANUARY, 01), LocalDate.of(2020, Month.DECEMBER, 31), new BigDecimal(0.1)));
            municipalities.add(new Municipality("Vilnius", Schedule.MONTHLY, LocalDate.of(2020, Month.OCTOBER, 01), LocalDate.of(2020, Month.OCTOBER, 31), new BigDecimal(0.2)));
            municipalityRepository.saveAll(municipalities);
        };
    }
}