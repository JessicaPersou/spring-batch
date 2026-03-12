// java
package com.persou.springbatch.batch.processor;

import com.persou.springbatch.dto.WorkingHours;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalTime;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkedHoursProcessor implements ItemProcessor<WorkingHours, WorkingHours> {
    @Override
    public WorkingHours process(WorkingHours workingHours) {
        LocalTime in = workingHours.getClockInTime();//entrada
        LocalTime out = workingHours.getClockOutTime();//saida

        Duration work = Duration.between(in, out);//turno total de trabalho

        LocalTime lunchStart = workingHours.getLunchBreakStart();//inicio almoço
        LocalTime lunchEnd = workingHours.getLunchBreakEnd();//fim almoço
        Duration lunch = Duration.between(lunchStart, lunchEnd); // duração almoço

        Duration netWork = work.minus(lunch);//turno sem almoço

        long minutesWork = netWork.toMinutes();
        BigDecimal hours = BigDecimal.valueOf(minutesWork).divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);

        BigDecimal lunchHours = BigDecimal.valueOf(lunch.toMinutes()).divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);
        // assegurar que WorkingHours possui setHoursWorked(BigDecimal) e getHoursWorked()
        workingHours.setHoursWorked(hours);

        // se o DTO espera Duration ou outro tipo, ajuste conforme necessário
        workingHours.setLunchBreakTime(lunchHours);

        return workingHours;
    }
}
