package com.persou.springbatch.entities;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class WorkingHours {
    private Long id;

    private Employee employee;

    private LocalDate workDate;

    private LocalTime clockInTime;

    private LocalTime clockOutTime;

    private Duration hoursWorked;

    public WorkingHours() {
    }

    public WorkingHours(Long id, Employee employee, LocalDate workDate, LocalTime clockInTime, LocalTime clockOutTime,
                        Duration hoursWorked) {
        this.id = id;
        this.employee = employee;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public Duration getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(Duration hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkingHours that = (WorkingHours) o;
        return Objects.equals(id, that.id) && Objects.equals(employee, that.employee) &&
            Objects.equals(workDate, that.workDate) && Objects.equals(clockInTime, that.clockInTime) &&
            Objects.equals(clockOutTime, that.clockOutTime) &&
            Objects.equals(hoursWorked, that.hoursWorked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee, workDate, clockInTime, clockOutTime, hoursWorked);
    }

    @Override
    public String toString() {
        return "WorkingHours{" +
            "id=" + id +
            ", employee=" + employee +
            ", workDate=" + workDate +
            ", clockInTime=" + clockInTime +
            ", clockOutTime=" + clockOutTime +
            ", hoursWorked=" + hoursWorked +
            '}';
    }
}