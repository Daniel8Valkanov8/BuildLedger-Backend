package com.buildledger.backend.service.sells;

import com.buildledger.backend.dto.responce.sell.ResponseInstallmentDTO;
import com.buildledger.backend.model.ledger.Installment;
import com.buildledger.backend.model.ledger.Sell;
import com.buildledger.backend.model.sos.Apartment;
import com.buildledger.backend.model.sos.Garage;
import com.buildledger.backend.model.sos.ParkingPlace;
import com.buildledger.backend.repository.InstallmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class InstallmentService {

    private final InstallmentRepository repository;

    public InstallmentService(InstallmentRepository repository) {
        this.repository = repository;
    }

    public List<ResponseInstallmentDTO> getAllInstallmentsByCooperationId(Long id) {
        List<Installment> installments = repository.getAllInstallmentsByCooperationId(id);
        List<ResponseInstallmentDTO> response = myMapToResponse(installments);
        return response;
    }

    private List<ResponseInstallmentDTO> myMapToResponse(List<Installment> installments) {
        List<ResponseInstallmentDTO> response = new ArrayList<>();
        for (Installment installment : installments) {
            ResponseInstallmentDTO responseInstallmentDTO = new ResponseInstallmentDTO();
            responseInstallmentDTO.setId(installment.getId());
            responseInstallmentDTO.setPayStatus(installment.isPayStatus());
            responseInstallmentDTO.setSell(StaticService.convertObjectsToString(installment.getPayment().getSell()));
            responseInstallmentDTO.setAmount(installment.getInstallmentAmount());
            responseInstallmentDTO.setInstallmentDate(installment.getInstallmentDate());
            responseInstallmentDTO.setInstallmentLog(installment.getInstallmentLog());
            response.add(responseInstallmentDTO);
        }

        return response;
    }
    //private String convertObjectsToString(Sell sell) {
    //    StringBuilder sb = new StringBuilder();
    //    if (!sell.getApartments().isEmpty()) {
    //        for (Apartment apartment : sell.getApartments()) {
    //            sb.append(apartment.getNumber());
    //            sb.append(" ");
    //        }
    //    }
    //    if (!sell.getGarages().isEmpty()) {
    //        for (Garage garage : sell.getGarages()) {
    //            sb.append(garage.getNumber());
    //            sb.append(" ");
    //        }
    //    }
    //    if (!sell.getParkingPlaces().isEmpty()) {
    //        for (ParkingPlace parkingPlace : sell.getParkingPlaces()) {
    //            sb.append(parkingPlace.getNumber());
    //            sb.append(" ");
    //        }
    //    }
    //    return sb.toString();
    //}
}
