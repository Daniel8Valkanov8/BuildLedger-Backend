package com.buildledger.backend.service.impl.building;

import com.buildledger.backend.dto.request.building.CreateIntermediateDTO;
import com.buildledger.backend.dto.request.sos.ResponseApartmentTableRowDTO;
import com.buildledger.backend.dto.request.sos.UpdateApartmentDTO;
import com.buildledger.backend.dto.responce.objects.ResponseApartmentDTO;
import com.buildledger.backend.dto.responce.objects.ResponseApartmentInformationDTO;
import com.buildledger.backend.model.building.Cooperation;
import com.buildledger.backend.model.ledger.Installment;
import com.buildledger.backend.model.ledger.Payment;
import com.buildledger.backend.model.ledger.Sell;
import com.buildledger.backend.model.ledger.accounting.Income;
import com.buildledger.backend.model.sos.Apartment;
import com.buildledger.backend.model.sos.Floor;
import com.buildledger.backend.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ApartmentService {
    private final CooperationRepository cooperationRepository;
    private final ApartmentRepository apartmentRepository;
    private final FloorService floorService;
    private final FloorRepository floorRepository;
    private final SellRepository sellRepository;
    private final IncomeRepository incomeRepository;
    private final InstallmentRepository installmentRepository;
    private final PaymentRepository paymentRepository;


    public ApartmentService(CooperationRepository cooperationRepository, ApartmentRepository apartmentRepository, FloorService floorService, FloorRepository floorRepository, SellRepository sellRepository, IncomeRepository incomeRepository, InstallmentRepository installmentRepository, PaymentRepository paymentRepository) {
        this.cooperationRepository = cooperationRepository;
        this.apartmentRepository = apartmentRepository;
        this.floorService = floorService;
        this.floorRepository = floorRepository;
        this.sellRepository = sellRepository;
        this.incomeRepository = incomeRepository;
        this.installmentRepository = installmentRepository;
        this.paymentRepository = paymentRepository;
    }

    public void createApartmentByCount(CreateIntermediateDTO createIntermediateDTO) {

        if(createIntermediateDTO.getApartment() == 0) return;
        Optional<Cooperation> cooperation = cooperationRepository.findById(createIntermediateDTO.getId());
        if (cooperation.isPresent()){
            for (int i = 1; i <=createIntermediateDTO.getApartment(); i++) {
                Apartment apartment = new Apartment();
                apartment.setNumber("Apartment " + i);
                apartment.setCooperation(cooperation.get());
                cooperation.get().getApartments().add(apartment);
                apartmentRepository.save(apartment);
            }
            cooperationRepository.save(cooperation.get());
        }else {
            throw new IllegalArgumentException("Cooperation with ID " + createIntermediateDTO.getId() + " not found.");
        }
    }

    public List<ResponseApartmentDTO> getAllApartmentsByCooperationID(long cooperationID) {
    List<Apartment> response = apartmentRepository.getAllApartmentsByCooperationID(cooperationID);
    List<ResponseApartmentDTO> responseDTO = new ArrayList<>();

        for (Apartment apartment : response) {
        ResponseApartmentDTO dto = new ResponseApartmentDTO();
        BeanUtils.copyProperties(apartment, dto);
            setFloorIdCatchNull(dto, apartment);
            responseDTO.add(dto);
        }
    return responseDTO;
    }
    public ResponseApartmentDTO getApartmentByID(long id) {
        Optional<Apartment> apartment = apartmentRepository.findById(id);
        if (apartment.isPresent()) {
            ResponseApartmentDTO responseApartmentDTO = new ResponseApartmentDTO();
            BeanUtils.copyProperties(apartment.get(), responseApartmentDTO);
            setFloorIdCatchNull(responseApartmentDTO, apartment.get());
            return responseApartmentDTO;
        } else {
            throw new IllegalArgumentException("Apartment with ID " + id + " not found.");
        }
    }
    public ResponseApartmentDTO updateApartment(UpdateApartmentDTO updateApartmentDTO) {
        Optional<Apartment> apartment = apartmentRepository.findById(updateApartmentDTO.getId());
        ResponseApartmentDTO responseApartmentDTO = new ResponseApartmentDTO();
        if (apartment.isPresent()) {
           Apartment apartment1 = apartment.get();
           convertUpdateToEntity(updateApartmentDTO, apartment1);

           Apartment savedApartment = apartmentRepository.saveAndFlush(apartment1);
           BeanUtils.copyProperties(savedApartment, responseApartmentDTO);
           setFloorIdCatchNull(responseApartmentDTO, savedApartment);

        }
        return responseApartmentDTO;
    }

    private void convertUpdateToEntity(UpdateApartmentDTO updateApartmentDTO, Apartment apartment1) {
        if (updateApartmentDTO.getArea() != null) {
            apartment1.setArea(updateApartmentDTO.getArea());
        }
        if (updateApartmentDTO.getFloorId() != null) {
            floorRepository.findById(updateApartmentDTO.getFloorId())
                    .ifPresent(apartment1::setFloor);
        }
        if (updateApartmentDTO.getPriceEur() != null) {
            apartment1.setPriceEur(updateApartmentDTO.getPriceEur());
        }
        if (updateApartmentDTO.getBedroomCount() != null) {
            apartment1.setBedroomCount(updateApartmentDTO.getBedroomCount());
        }
        if (updateApartmentDTO.getBathroomCount() != null) {
            apartment1.setBathroomCount(updateApartmentDTO.getBathroomCount());
        }
        if (updateApartmentDTO.getCleanArea() != null) {
            apartment1.setCleanArea(updateApartmentDTO.getCleanArea());
        }
        if (updateApartmentDTO.getCommonPartsPercentage() != null) {
            apartment1.setCommonPartsPercentage(updateApartmentDTO.getCommonPartsPercentage());
        }
        if (updateApartmentDTO.getCommonParts() != null) {
            apartment1.setCommonParts(updateApartmentDTO.getCommonParts());
        }
        if (updateApartmentDTO.getAdjoiningTerrace() != null) {
            apartment1.setAdjoiningTerrace(updateApartmentDTO.getAdjoiningTerrace());
        }
        if (updateApartmentDTO.getAdjoiningYardRoof() != null) {
            apartment1.setAdjoiningYardRoof(updateApartmentDTO.getAdjoiningYardRoof());
        }
        if (updateApartmentDTO.getCompensation() != null) {
            apartment1.setCompensation(updateApartmentDTO.getCompensation());
        }
        if (updateApartmentDTO.getPricePerSquareMeter() != null) {
            apartment1.setPricePerSquareMeter(updateApartmentDTO.getPricePerSquareMeter());
        }
        if (updateApartmentDTO.getPriceYard() != null) {
            apartment1.setPriceYard(updateApartmentDTO.getPriceYard());
        }
    }



    private static void setFloorIdCatchNull(ResponseApartmentDTO responseApartmentDTO, Apartment savedApartment) {
        if(savedApartment.getFloor()!=null) {
            responseApartmentDTO.setFloorId(savedApartment.getFloor().getId());
        }

    }

    private static void setFloorIdCatchNull(ResponseApartmentInformationDTO responseApartmentDTO, Apartment savedApartment) {
        if(savedApartment.getFloor()!=null) {
            responseApartmentDTO.setFloorId(savedApartment.getFloor().getNumber());
        }

    }

    public List<ResponseApartmentDTO> getAllFreeApartmentsByCooperationID(long id) {
        List<Apartment> response = getApartmentsByCooperationId(id);
        List<ResponseApartmentDTO> responseDTO = new ArrayList<>();
        for (Apartment apartment : response) {
            ResponseApartmentDTO dto = new ResponseApartmentDTO();
            BeanUtils.copyProperties(apartment, dto);
            setFloorIdCatchNull(dto, apartment);
            responseDTO.add(dto);
        }
        return responseDTO;
    }

    private List<Apartment> getApartmentsByCooperationId(long id) {
        List<Apartment> response = apartmentRepository.getAllFreeApartmentsByCooperationID(id);
        return response;
    }

    public ResponseApartmentInformationDTO getApartmentByCooperationIdAndApartmentId(long cooperationId, long apartmentId) {
        Optional<Apartment> apartment = apartmentRepository.getApartmentByCooperationIdAndApartmentId(cooperationId, apartmentId);
        if (apartment.isPresent()) {
            ResponseApartmentInformationDTO responseApartmentDTO = new ResponseApartmentInformationDTO();

            if (apartment.get().isSold()){
                mappingApartmentInformation(responseApartmentDTO, apartment.get());
            }
            BeanUtils.copyProperties(apartment.get(), responseApartmentDTO);
            setFloorIdCatchNull(responseApartmentDTO, apartment.get());
            return responseApartmentDTO;
        } else {
            throw new IllegalArgumentException("Apartment with ID " + apartmentId + " not found.");
        }

    }

    private void mappingApartmentInformation(ResponseApartmentInformationDTO responseApartmentDTO, Apartment apartment) {
        responseApartmentDTO.setId(apartment.getId());
        responseApartmentDTO.setArea(apartment.getArea());
        responseApartmentDTO.setPriceEur(apartment.getPriceEur());
        responseApartmentDTO.setBedroomCount(apartment.getBedroomCount());
        responseApartmentDTO.setBathroomCount(apartment.getBathroomCount());
        responseApartmentDTO.setFloorId(apartment.getFloor().getNumber());
        responseApartmentDTO.setSold(apartment.isSold());
        responseApartmentDTO.setDescription(apartment.getDescription());

        responseApartmentDTO.setDiscountInEuro(apartment.getSell().getDiscountInEuro());
        responseApartmentDTO.setTotalPriceInEuro(apartment.getSell().getTotalPriceInEuro());
        responseApartmentDTO.setBrokerProfitInEuro(apartment.getSell().getBrokerProfitInEuro());
        responseApartmentDTO.setBrokerProfitInPercentage(apartment.getSell().getBrokerProfitInPercentage());

        String broker = apartment.getSell().getBroker().getFirstName() + " " + apartment.getSell().getBroker().getLastName() + " " + apartment.getSell().getBroker().getEmail();
        String purchaser = apartment.getSell().getPurchaser().getFirstName() + " " + apartment.getSell().getPurchaser().getLastName() + " " + apartment.getSell().getPurchaser().getEmail();
        responseApartmentDTO.setBroker(broker);
        responseApartmentDTO.setPurchaser(purchaser);
        responseApartmentDTO.setContractDate(apartment.getSell().getContractDate());
        responseApartmentDTO.setInstallmentCount(apartment.getSell().getPayment().getInstallments().size());

    }

    public void addDescription(long id, String description) {
        Optional<Apartment> apartment = apartmentRepository.findById(id);
        ResponseApartmentInformationDTO responseApartmentInformationDTO = new ResponseApartmentInformationDTO();

        if (apartment.isPresent()) {
            apartment.get().setDescription(description);
            Apartment savedApartment = apartmentRepository.saveAndFlush(apartment.get());

        } else {
            throw new IllegalArgumentException("Apartment with ID " + id + " not found.");
        }
    }

    public void deleteAllApartmentsByCooperationId(Long id) {
        List<Apartment> apartments = apartmentRepository.getAllApartmentsByCooperationID(id);
        Cooperation coo = cooperationRepository.findById(id).orElseThrow(() -> new RuntimeException("Cooperation not found"));

        for (Apartment apartment : apartments) {
            if (apartment.getSell() != null) {
                Sell apartmentSell = apartment.getSell();
                Payment payment = apartmentSell.getPayment();

                // Delete installments and incomes
                if (payment != null) {
                    Set<Installment> installments = payment.getInstallments();
                    for (Installment installment : installments) {
                        if (installment.getIncome() != null) {
                            incomeRepository.delete(installment.getIncome()); // Remove Income
                        }
                        installmentRepository.delete(installment); // Remove Installment
                    }
                    paymentRepository.delete(payment); // Remove Payment
                }
                sellRepository.delete(apartmentSell); // Remove Sell
            }
            apartmentRepository.delete(apartment); // Remove Apartment
        }

    }

    //todo
    public List<ResponseApartmentTableRowDTO> getApartmentByCooperationIdInTable(long id) {
        List<Apartment> apartments = apartmentRepository.getAllApartmentsByCooperationID(id);
        List<ResponseApartmentTableRowDTO> response = convertFromApartments(apartments);
        return response;
    }

    private List<ResponseApartmentTableRowDTO> convertFromApartments(List<Apartment> apartments) {
        List<ResponseApartmentTableRowDTO> response = new ArrayList<>();
        for (Apartment apartment : apartments) {
            ResponseApartmentTableRowDTO row = new ResponseApartmentTableRowDTO();
        BeanUtils.copyProperties(apartment,row);
        if(apartment.isSold()){
            Sell sell = apartment.getSell();
            BeanUtils.copyProperties(sell,row);
            Payment payment = sell.getPayment();
            BeanUtils.copyProperties(payment,row);
            row.setInstallmentsCount(payment.getInstallments().size());
            for (Installment installment : payment.getInstallments()) {
                row.getInstallments().add(installment.getInstallmentAmount());
                }
            row.setPurchaser(sell.getPurchaser().getFirstName()
                    + " " + sell.getPurchaser().getLastName());
            }

        response.add(row);
        }
        return response;
    }
}
