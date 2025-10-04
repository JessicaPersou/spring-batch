package com.persou.springbatch.batch.writer;

import com.persou.springbatch.dto.Employee;
import javax.sql.DataSource;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WriterConfig {

    @Bean
    public ItemWriter<Employee> itemWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Employee>().dataSource(dataSource).sql(
                "INSERT INTO employees (employee_code, document, full_name, department, created_at) VALUES (:employeeCode, :document, :fullName, :department, :createdAt)")
            .beanMapped().build();
    }
}
