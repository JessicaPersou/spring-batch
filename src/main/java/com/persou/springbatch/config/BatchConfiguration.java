package com.persou.springbatch.config;

import com.persou.springbatch.entities.Employee;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfiguration {

    @Bean
    public Job processEmployees(JobRepository jobRepository, Flow splitFlow) {
        return new JobBuilder("processEmployees", jobRepository)
            .incrementer(new RunIdIncrementer())
            .start(splitFlow)
            .end().build();
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
            .taskExecutor(new SimpleAsyncTaskExecutor())
            .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager, Tasklet tasklet) {
        return new StepBuilder("step2", jobRepository)
            .tasklet(tasklet, platformTransactionManager)
            .build();
    }

    @Bean
    public Tasklet tasklet() {
        return ((contribution, chunkContext) -> {
            System.out.println("------------- Tasklet Started -------------");
            Thread.sleep(10000);
            System.out.println("------------- Tasklet End --------------");
            return RepeatStatus.FINISHED;
        });
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
            .sql("INSERT INTO employees (employee_code, document, full_name, department, created_at) VALUES (:employeeCode, :document, :fullName, :department, :createdAt)")
            .beanMapped()
            .build();
    }

    @Bean
    public ItemProcessor<Employee, Employee> itemProcessor() {
        return new EmployeeProcessor();
    }

    @Bean
    public Flow splitFlow(Step step, Step step2){
        return new FlowBuilder<SimpleFlow>("simpleFlow")
            .split(new SimpleAsyncTaskExecutor())
            .add(flow(step), flow(step2))
            .build();
    }

    @Bean
    public SimpleFlow flow(Step step) {
        return new FlowBuilder<SimpleFlow>("simpleFlow")
            .start(step)
            .build();
    }
}
