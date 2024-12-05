package com.buildledger.backend.service.sells;

import com.buildledger.backend.dto.responce.sell.ResponsePaymentDTO;
import com.buildledger.backend.model.ledger.Payment;
import com.buildledger.backend.model.ledger.Sell;
import com.buildledger.backend.model.sos.Apartment;
import com.buildledger.backend.model.sos.Garage;
import com.buildledger.backend.model.sos.ParkingPlace;
import com.buildledger.backend.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<ResponsePaymentDTO> getAllPaymentsByCooperationId(Long id) {

        List<Payment> payments = paymentRepository.getAllPaymentsByCooperationId(id);
        List<ResponsePaymentDTO> response = myMapToResponse(payments);
        return response;
    }

    private List<ResponsePaymentDTO> myMapToResponse(List<Payment> payments) {
        List<ResponsePaymentDTO> response = new ArrayList<>();

        for (Payment payment : payments) {
        ResponsePaymentDTO responsePaymentDTO = new ResponsePaymentDTO();
        responsePaymentDTO.setId(payment.getId());
        responsePaymentDTO.setSell(convertObjectsToString(payment.getSell()));
        responsePaymentDTO.setPaymentStatus(payment.getPaymentStatus().toString());
        responsePaymentDTO.setAmountReceived(payment.getAmountReceived());
        responsePaymentDTO.setAmountRemaining(payment.getAmountRemaining());
        responsePaymentDTO.setTotalPrice(payment.getSell().getTotalPriceInEuro());
        responsePaymentDTO.setInstallments(payment.getInstallmentCount());
        response.add(responsePaymentDTO);
        }
        return response;
    }
    private String convertObjectsToString(Sell sell) {
        StringBuilder sb = new StringBuilder();
        if (!sell.getApartments().isEmpty()) {
            for (Apartment apartment : sell.getApartments()) {
                sb.append(apartment.getNumber());
                sb.append(" ");
            }
        }
        if (!sell.getGarages().isEmpty()) {
            for (Garage garage : sell.getGarages()) {
                sb.append(garage.getNumber());
                sb.append(" ");
            }
        }
        if (!sell.getParkingPlaces().isEmpty()) {
            for (ParkingPlace parkingPlace : sell.getParkingPlaces()) {
                sb.append(parkingPlace.getNumber());
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
