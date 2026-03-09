package com.persou.springbatch.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class WorkingHoursFieldSetMapper implements FieldSetMapper<WorkingHours> {
    @Override
    public WorkingHours mapFieldSet(FieldSet fieldSet) throws BindException {
        WorkingHours wh = new WorkingHours();
        wh.setEmployeeCode(fieldSet.readRawString("EMPLOYEE_CODE"));
        wh.setWorkDate(LocalDate.parse(fieldSet.readRawString("WORK_DATE")));
        wh.setClockInTime(LocalTime.parse(fieldSet.readRawString("CLOCK_IN_TIME")));
        wh.setClockOutTime(LocalTime.parse(fieldSet.readRawString("CLOCK_OUT_TIME")));
        return wh;
    }
}
