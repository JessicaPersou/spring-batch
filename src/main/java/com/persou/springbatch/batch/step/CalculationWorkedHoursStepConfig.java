package com.persou.springbatch.batch.step;

import com.persou.springbatch.dto.WorkingHours;
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
public class CalculationWorkedHoursStepConfig {

    @Bean
    public Step calculationWorkedHoursStep(JobRepository jobRepository,
                                                    PlatformTransactionManager platformTransactionManager,
                                                    ItemReader<WorkingHours> itemReader,
                                                    ItemProcessor<WorkingHours, WorkingHours> itemProcessor,
                                                    ItemWriter<WorkingHours> itemWriter) {

        return new StepBuilder("calculationWorkedHours", jobRepository)
            .<WorkingHours, WorkingHours>chunk(2, platformTransactionManager)
            .reader(itemReader)
            .processor(itemProcessor)
            .writer(itemWriter)
            .build();
    }
}
