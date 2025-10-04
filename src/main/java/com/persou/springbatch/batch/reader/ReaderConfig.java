package com.persou.springbatch.batch.reader;

import com.persou.springbatch.dto.Employee;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class ReaderConfig {

    @Bean
    public ItemReader<Employee> itemReader() {
        BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Employee.class);
        return new FlatFileItemReaderBuilder<Employee>().name("employeeItemReader")
            .resource(new ClassPathResource("employees.csv")).delimited()
            .names("EMPLOYEE_CODE", "DOCUMENT", "FULL_NAME", "DEPARTMENT").fieldSetMapper(fieldSetMapper).build();
    }
}
