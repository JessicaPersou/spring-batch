package com.persou.springbatch.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeProcessingJob {

    @Bean
    public Job processEmployees(JobRepository jobRepository,
                                Step importEmployeeCsvStep,
                                Step calculationWorkedHoursStep) {
        return new JobBuilder("processEmployees", jobRepository)
            .incrementer(new RunIdIncrementer())
            .start(importEmployeeCsvStep)
            .next(calculationWorkedHoursStep)
            .build();
    }
}
