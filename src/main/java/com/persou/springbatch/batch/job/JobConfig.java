package com.persou.springbatch.batch.job;

import com.persou.springbatch.batch.processor.EmployeeProcessor;
import com.persou.springbatch.dto.Employee;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

    @Bean
    public Job processEmployees(JobRepository jobRepository, Step step) {
        return new JobBuilder("processEmployees", jobRepository)
            .incrementer(new RunIdIncrementer())
            .start(step)
            .build();
    }

    @Bean
    public ItemProcessor<Employee, Employee> itemProcessor() {
        return new EmployeeProcessor();
    }
}
