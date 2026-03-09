package com.persou.springbatch.batch.job;

import com.persou.springbatch.batch.processor.EmployeeProcessor;
import com.persou.springbatch.batch.processor.WorkedHoursProcessor;
import com.persou.springbatch.dto.Employee;
import com.persou.springbatch.dto.WorkingHours;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
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

    @Bean
    public ItemProcessor<Employee, Employee> itemProcessor() {
        return new EmployeeProcessor();
    }

    @Bean
    public ItemProcessor<WorkingHours, WorkingHours> itemProcessorWorkedHours() {
        return new WorkedHoursProcessor();
    }
}
