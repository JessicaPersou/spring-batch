package com.persou.springbatch.batch.reader;

import com.persou.springbatch.dto.WorkingHours;
import com.persou.springbatch.dto.WorkingHoursFieldSetMapper;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class WorkedHoursReaderConfig {

    @Bean
    public ItemReader<WorkingHours> workingHoursCsvReader() {
        BeanWrapperFieldSetMapper<WorkingHours> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(WorkingHours.class);
        return new FlatFileItemReaderBuilder<WorkingHours>()
            .name("workedHourCsv")
            .resource(new ClassPathResource("working_hours.csv"))
            .linesToSkip(1)
            .delimited()
            .names("EMPLOYEE_CODE", "WORK_DATE", "CLOCK_IN_TIME", "CLOCK_OUT_TIME")
            .fieldSetMapper(new WorkingHoursFieldSetMapper())
            .build();
    }
}
