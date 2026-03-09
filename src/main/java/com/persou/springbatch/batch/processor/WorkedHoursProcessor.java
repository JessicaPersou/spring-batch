package com.persou.springbatch.batch.processor;

import com.persou.springbatch.dto.WorkingHours;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;

public class WorkedHoursProcessor implements ItemProcessor<WorkingHours, WorkingHours> {
    @Override
    public WorkingHours process(WorkingHours workingHours) throws Exception {
        LocalDate date = workingHours.getWorkDate();
        LocalTime in = workingHours.getClockInTime();
        LocalTime out = workingHours.getClockOutTime();

        Duration duration = Duration.between(in, out);
        BigDecimal hours = BigDecimal.valueOf(duration.toMinutes()).divide(BigDecimal.valueOf(60),2,BigDecimal.ROUND_HALF_UP);
        WorkingHours wh = new WorkingHours();
        wh.setEmployeeCode(workingHours.getEmployeeCode());
        wh.setWorkDate(date);
        wh.setClockInTime(in);
        wh.setClockOutTime(out);
        wh.setHoursWorked(hours);
        return wh;
    }

    @Bean
    public ItemProcessor<WorkingHours, WorkingHours> itemProcessor() {
        return new WorkedHoursProcessor();
    }
}

