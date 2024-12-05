package com.buildledger.backend.dto.responce.sell;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponsePaymentDTO {

        private long id;
        private String sell;
        private String paymentStatus;
        private double amountReceived;
        private double totalPrice;
        private double amountRemaining;
        private int installments;

        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public String getSell() {
                return sell;
        }

        public void setSell(String sell) {
                this.sell = sell;
        }

        public String getPaymentStatus() {
                return paymentStatus;
        }

        public void setPaymentStatus(String paymentStatus) {
                this.paymentStatus = paymentStatus;
        }

        public double getAmountReceived() {
                return amountReceived;
        }

        public void setAmountReceived(double amountReceived) {
                this.amountReceived = amountReceived;
        }

        public double getTotalPrice() {
                return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
                this.totalPrice = totalPrice;
        }

        public double getAmountRemaining() {
                return amountRemaining;
        }

        public void setAmountRemaining(double amountRemaining) {
                this.amountRemaining = amountRemaining;
        }

        public int getInstallments() {
                return installments;
        }

        public void setInstallments(int installments) {
                this.installments = installments;
        }
}
