package com.persou.springbatch.config;

import com.persou.springbatch.entities.Employee;
import java.time.LocalDateTime;
import org.springframework.batch.item.ItemProcessor;

public class EmployeeProcessor implements ItemProcessor<Employee, Employee> {
    @Override
    public Employee process(Employee item) throws Exception {
        item.setCreatedAt(LocalDateTime.now());
        return item;
    }
}
