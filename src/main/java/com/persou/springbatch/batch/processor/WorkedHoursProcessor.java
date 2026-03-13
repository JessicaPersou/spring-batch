// java
package com.persou.springbatch.batch.processor;

import com.persou.springbatch.dto.WorkingHours;
import java.time.Duration;
import java.time.LocalTime;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkedHoursProcessor implements ItemProcessor<WorkingHours, WorkingHours> {

    @Override
    public WorkingHours process(WorkingHours workingHours) {
        Duration work = Duration.between(workingHours.getClockInTime(), workingHours.getClockOutTime());
        Duration lunch = Duration.between(workingHours.getLunchBreakStart(), workingHours.getLunchBreakEnd());
        Duration netWork = work.minus(lunch);

        LocalTime workedTime = LocalTime.MIDNIGHT.plus(netWork);
        LocalTime lunchTime = LocalTime.MIDNIGHT.plus(lunch);

        workingHours.setHoursWorked(workedTime);
        workingHours.setLunchBreakTime(lunchTime);
        return workingHours;
    }
}
