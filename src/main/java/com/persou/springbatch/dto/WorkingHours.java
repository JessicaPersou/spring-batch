package com.persou.springbatch.dto;


import java.math.BigDecimal;
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

    public WorkingHours() {
    }

    public WorkingHours(Long id, String employeeCode, LocalDate workDate, LocalTime clockInTime, LocalTime clockOutTime,
                        BigDecimal hoursWorked) {
        this.id = id;
        this.employeeCode = employeeCode;
        this.workDate = workDate;
        this.clockInTime = clockInTime;
        this.clockOutTime = clockOutTime;
        this.hoursWorked = hoursWorked;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkingHours that = (WorkingHours) o;
        return Objects.equals(id, that.id) && Objects.equals(employeeCode, that.employeeCode) &&
            Objects.equals(workDate, that.workDate) && Objects.equals(clockInTime, that.clockInTime) &&
            Objects.equals(clockOutTime, that.clockOutTime) &&
            Objects.equals(hoursWorked, that.hoursWorked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeCode, workDate, clockInTime, clockOutTime, hoursWorked);
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
            '}';
    }
}