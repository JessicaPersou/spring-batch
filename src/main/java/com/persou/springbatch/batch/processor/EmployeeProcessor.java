package com.persou.springbatch.batch.processor;

import com.persou.springbatch.dto.Employee;
import java.time.LocalDateTime;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeProcessor implements ItemProcessor<Employee, Employee> {

    @Override
    public Employee process(Employee item) {
        item.setCreatedAt(LocalDateTime.now());
        return item;
    }
}
