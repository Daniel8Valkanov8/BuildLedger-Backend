package com.buildledger.backend.model.ledger.accounting;

import com.buildledger.backend.enums.ExpenseStatus;
import com.buildledger.backend.model.Project;
import com.buildledger.backend.model.building.Building;
import com.buildledger.backend.model.ledger.Installment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Income extends BaseLedger {

        private String incomeLog;
        private String incomeStatus;

        @Column(nullable = true)
        private String factureNumber;

        @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
        @JoinColumn(name = "installment_id")
        private Installment installment;

        @ManyToOne
        @JoinColumn(name = "building_id")
        private Building building;

        @ManyToOne
        @JoinColumn(name = "project_id")
        private Project project;

        public String getIncomeStatus() {
                return incomeStatus;
        }

        public void setIncomeStatus(String incomeStatus) {
                this.incomeStatus = incomeStatus;
        }

        public String getFactureNumber() {
                return factureNumber;
        }

        public void setFactureNumber(String factureNumber) {
                this.factureNumber = factureNumber;
        }

        public String getIncomeLog() {
                return incomeLog;
        }

        public void setIncomeLog(String incomeLog) {
                this.incomeLog = incomeLog;
        }

        public Installment getInstallment() {
                return installment;
        }

        public void setInstallment(Installment installment) {
                this.installment = installment;
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

        @Override
        public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append(incomeLog);
                sb.append(" - ");
                sb.append(incomeStatus.toString());
                if (this.factureNumber != null) {
                        sb.append(" - ");
                        sb.append(factureNumber);
                }

                return sb.toString();
        }
}
