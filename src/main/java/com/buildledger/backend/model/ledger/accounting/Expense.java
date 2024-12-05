package com.buildledger.backend.model.ledger.accounting;

import com.buildledger.backend.enums.ExpenseStatus;
import com.buildledger.backend.model.Project;
import com.buildledger.backend.model.building.Building;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Expense extends BaseLedger {

    private String category;
    private ExpenseStatus expenseStatus;
    @Column(nullable = true)
    private String factureNumber;


    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(category);
        sb.append(" - ");
        sb.append(expenseStatus.toString());
        if (this.factureNumber != null) {
            sb.append(" - ");
            sb.append(factureNumber);
        }

        return sb.toString();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ExpenseStatus getExpenseStatus() {
        return expenseStatus;
    }

    public void setExpenseStatus(ExpenseStatus expenseStatus) {
        this.expenseStatus = expenseStatus;
    }

    public String getFactureNumber() {
        return factureNumber;
    }

    public void setFactureNumber(String factureNumber) {
        this.factureNumber = factureNumber;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
