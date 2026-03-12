package com.persou.springbatch.dto;


import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class WorkingHours {
    private Long id;

    private String employeeCode;

    private LocalDate workDate;

    private LocalTime clockInTime;

    private LocalTime clockOutTime;

    private BigDecimal hoursWorked;

    private LocalTime lunchBreakStart;

    private LocalTime lunchBreakEnd;

    private BigDecimal lunchBreakTime;

    public WorkingHours() {
    }

    public WorkingHours(Long id, String employeeCode, LocalDate workDate, LocalTime clockInTime, LocalTime clockOutTime,
                        BigDecimal hoursWorked, LocalTime lunchBreakStart, LocalTime lunchBreakEnd,
                        BigDecimal lunchBreakTime) {
        this.id = id;
        this.employeeCode = employeeCode;
        this.workDate = workDate;
        this.clockInTime = clockInTime;
        this.clockOutTime = clockOutTime;
        this.hoursWorked = hoursWorked;
        this.lunchBreakStart = lunchBreakStart;
        this.lunchBreakEnd = lunchBreakEnd;
        this.lunchBreakTime = lunchBreakTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employee) {
        this.employeeCode = employee;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
    }

    public LocalTime getClockInTime() {
        return clockInTime;
    }

    public void setClockInTime(LocalTime clockInTime) {
        this.clockInTime = clockInTime;
    }

    public LocalTime getClockOutTime() {
        return clockOutTime;
    }

    public void setClockOutTime(LocalTime clockOutTime) {
        this.clockOutTime = clockOutTime;
    }

    public BigDecimal getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(BigDecimal hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public LocalTime getLunchBreakStart() {
        return lunchBreakStart;
    }

    public void setLunchBreakStart(LocalTime lunchBreakStart) {
        this.lunchBreakStart = lunchBreakStart;
    }

    public LocalTime getLunchBreakEnd() {
        return lunchBreakEnd;
    }

    public void setLunchBreakEnd(LocalTime lunchBreakEnd) {
        this.lunchBreakEnd = lunchBreakEnd;
    }

    public void setLunchBreakTime(BigDecimal lunchBreakTime) {
        this.lunchBreakTime = lunchBreakTime;
    }

    public BigDecimal getLunchBreakTime() {
        return lunchBreakTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkingHours that = (WorkingHours) o;
        return Objects.equals(id, that.id) && Objects.equals(employeeCode, that.employeeCode) &&
            Objects.equals(workDate, that.workDate) && Objects.equals(clockInTime, that.clockInTime) &&
            Objects.equals(clockOutTime, that.clockOutTime) &&
            Objects.equals(hoursWorked, that.hoursWorked) &&
            Objects.equals(lunchBreakStart, that.lunchBreakStart) &&
            Objects.equals(lunchBreakEnd, that.lunchBreakEnd) &&
            Objects.equals(lunchBreakTime, that.lunchBreakTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeCode, workDate, clockInTime, clockOutTime, hoursWorked, lunchBreakStart,
            lunchBreakEnd, lunchBreakTime);
    }

    @Override
    public String toString() {
        return "WorkingHours{" +
            "id=" + id +
            ", employee=" + employeeCode +
            ", workDate=" + workDate +
            ", clockInTime=" + clockInTime +
            ", clockOutTime=" + clockOutTime +
            ", hoursWorked=" + hoursWorked +
            ", lunchBreakTime=" + lunchBreakStart +
            ", lunchBreakEnd=" + lunchBreakEnd +
            ", LunchBreakTime=" + lunchBreakTime +
            '}';
    }
}