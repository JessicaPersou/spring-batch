package com.persou.springbatch.config;

import com.persou.springbatch.entities.Employee;
import com.persou.springbatch.processor.EmployeeProcessor;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfiguration {

    @Bean
    public Job processEmployees(JobRepository jobRepository, Step step) {
        return new JobBuilder("processEmployees", jobRepository)
            .incrementer(new RunIdIncrementer())
            .start(step)
            .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager,
                     ItemReader<Employee> itemReader, ItemProcessor<Employee, Employee> itemProcessor,
                     ItemWriter<Employee> itemWriter) {
        return new StepBuilder("step", jobRepository)
            .<Employee, Employee>chunk(20, platformTransactionManager)
            .reader(itemReader)
            .processor(itemProcessor)
            .writer(itemWriter)
            .build();
    }

    @Bean
    public ItemReader<Employee> itemReader() {
        BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Employee.class);
        return new FlatFileItemReaderBuilder<Employee>()
            .name("employeeItemReader")
            .resource(new ClassPathResource("employees.csv")).delimited()
            .names("EMPLOYEE_CODE", "DOCUMENT", "FULL_NAME", "DEPARTMENT")
            .fieldSetMapper(fieldSetMapper)
            .build();
    }

    @Bean
    public ItemWriter<Employee> itemWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Employee>()
            .dataSource(dataSource)
            .sql("INSERT INTO employees (employee_code, document, full_name, department) VALUES (:employeeCode, :document, :fullName, :department)")
            .beanMapped()
            .build();
    }

    @Bean
    public ItemProcessor<Employee, Employee> itemProcessor() {
        return new EmployeeProcessor();
    }
}
