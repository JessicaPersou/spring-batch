package com.persou.springbatch.batch.step;

import com.persou.springbatch.dto.Employee;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class StepConfig {
    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager,
                     ItemReader<Employee> itemReader, ItemProcessor<Employee, Employee> itemProcessor,
                     ItemWriter<Employee> itemWriter) {
        return new StepBuilder("step", jobRepository).<Employee, Employee>chunk(20, platformTransactionManager)
            .reader(itemReader).processor(itemProcessor).writer(itemWriter).build();
    }
}
