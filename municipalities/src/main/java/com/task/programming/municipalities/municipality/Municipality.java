package com.task.programming.municipalities.municipality;

import com.task.programming.municipalities.schedule.Schedule;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table
public class Municipality {
    @Id
    @SequenceGenerator(name = "municipality_sequence", sequenceName = "municipality_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "municipality_sequence")
    private Long id;
    private String name;
    private Schedule schedule;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal rate;

    public Municipality() {
    }

    public Municipality(Long id, String name, Schedule schedule, LocalDate startDate, LocalDate endDate, BigDecimal rate) {
        this.id = id;
        this.name = name;
        this.schedule = schedule;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rate = rate;
    }

    public Municipality(String name, Schedule schedule, LocalDate startDate, LocalDate endDate, BigDecimal rate) {
        this.name = name;
        this.schedule = schedule;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}