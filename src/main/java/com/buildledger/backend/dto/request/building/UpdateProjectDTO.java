package com.buildledger.backend.dto.request.building;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class UpdateProjectDTO {
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String eik;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getEik() {
        return eik;
    }

    public void setEik(String eik) {
        this.eik = eik;
    }
}
