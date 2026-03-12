package com.persou.springbatch.dto.mapper;

import com.persou.springbatch.dto.WorkingHours;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class WorkingHoursFieldSetMapper implements FieldSetMapper<WorkingHours> {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public WorkingHours mapFieldSet(FieldSet fieldSet) throws BindException {
        WorkingHours wh = new WorkingHours();
        wh.setEmployeeCode(fieldSet.readRawString("EMPLOYEE_CODE"));
        wh.setWorkDate(LocalDate.parse(fieldSet.readRawString("WORK_DATE").trim(), DATE_FORMATTER));
        wh.setClockInTime(LocalTime.parse(fieldSet.readRawString("CLOCK_IN_TIME")));
        wh.setClockOutTime(LocalTime.parse(fieldSet.readRawString("CLOCK_OUT_TIME")));
        wh.setLunchBreakStart(LocalTime.parse(fieldSet.readRawString("LUNCH_BREAK_START")));
        wh.setLunchBreakEnd(LocalTime.parse(fieldSet.readRawString("LUNCH_BREAK_END")));
        return wh;
    }
}
