package com.persou.springbatch.batch.writer;

import com.persou.springbatch.dto.Employee;
import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeDatabaseWriterConfig {

    @Bean
    public ItemWriter<Employee> employeeDatabaseWrite(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Employee>().dataSource(dataSource).sql(
                "INSERT INTO employees (employee_code, document, full_name, department, created_at) " +
                    "VALUES (:employeeCode, :document, :fullName, :department, :createdAt)")
            .beanMapped().build();
    }

//    @Bean
//    public ItemWriter<Employee> employeeJpaWrite(EntityManagerFactory emf) {
//        return new JpaItemWriterBuilder<Employee>()
//            .entityManagerFactory(emf)
//            .build();
//    }
}
