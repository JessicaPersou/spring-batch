package com.persou.springbatch.batch.writer;

import com.persou.springbatch.dto.WorkingHours;
import javax.sql.DataSource;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkedHoursDatabaseWriterConfig {

    @Bean
    public ItemWriter<WorkingHours> workingHoursDatabaseWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<WorkingHours>().dataSource(dataSource).sql(
                "INSERT INTO working_hours (employee_code, work_date, clock_in_time, clock_out_time, hours_worked) " +
                    "VALUES (:employeeCode, :workDate, :clockInTime, :clockOutTime, :hoursWorked)")
            .beanMapped().build();
    }
}
