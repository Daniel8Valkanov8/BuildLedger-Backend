package com.buildledger.backend.dto.nested;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InstallmentAndDate {
    private double sumInEuros;
    private LocalDate date;

    public double getSumInEuros() {
        return sumInEuros;
    }

    public void setSumInEuros(double sumInEuros) {
        this.sumInEuros = sumInEuros;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
