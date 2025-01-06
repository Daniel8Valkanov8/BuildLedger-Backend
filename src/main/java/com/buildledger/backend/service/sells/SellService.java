package com.buildledger.backend.service.sells;


import com.buildledger.backend.dto.nested.InstallmentAndDate;
import com.buildledger.backend.dto.nested.SelfContainedUnits;
import com.buildledger.backend.dto.request.sell.CreateSellDTO;
import com.buildledger.backend.dto.request.sell.ResponseSellForEditDTO;
import com.buildledger.backend.dto.request.sell.UpdateInstallmentsSellDTO;
import com.buildledger.backend.dto.responce.PurchaserDTO;
import com.buildledger.backend.dto.responce.sell.ResponseSellDTO;
import com.buildledger.backend.enums.ExpenseStatus;
import com.buildledger.backend.enums.PaymentStatus;
import com.buildledger.backend.model.Project;
import com.buildledger.backend.model.building.Cooperation;
import com.buildledger.backend.enums.PayStatus;
import com.buildledger.backend.model.ledger.accounting.Expense;
import com.buildledger.backend.model.ledger.accounting.Income;
import com.buildledger.backend.model.ledger.Installment;
import com.buildledger.backend.model.ledger.Payment;
import com.buildledger.backend.model.ledger.Sell;
import com.buildledger.backend.model.persons.Broker;
import com.buildledger.backend.model.persons.Purchaser;
import com.buildledger.backend.model.sos.Apartment;
import com.buildledger.backend.model.sos.Garage;
import com.buildledger.backend.model.sos.ParkingPlace;
import com.buildledger.backend.repository.*;
import com.buildledger.backend.service.impl.BrokerService;
import com.buildledger.backend.service.impl.PurchaserService;
import com.buildledger.backend.service.micro.FileMicroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SellService {

    private final SellRepository sellRepository;
    private final BrokerService brokerService;
    private final PurchaserService purchaserService;
    private final CooperationRepository cooperationRepository;
    private final ApartmentRepository apartmentRepository;
    private final GarageRepository garageRepository;
    private final ParkingPlaceRepository parkingPlaceRepository;
    private final PaymentRepository paymentRepository;
    private final InstallmentRepository installmentRepository;
    private final ProjectRepository projectRepository;
    private final IncomeRepository incomeRepository;
    private final PurchaserRepository purchaserRepository;
    private final BrokerRepository brokerRepository;
    private final ExpenseRepository expenseRepository;

    public SellService(SellRepository sellRepository, BrokerService brokerService, PurchaserService purchaserService, CooperationRepository cooperationRepository, ApartmentRepository apartmentRepository, GarageRepository garageRepository, ParkingPlaceRepository parkingPlaceRepository, PaymentRepository paymentRepository, InstallmentRepository installmentRepository, ProjectRepository projectRepository, IncomeRepository incomeRepository, PurchaserRepository purchaserRepository, BrokerRepository brokerRepository, FileMicroService microService, ExpenseRepository expenseRepository) {
        this.sellRepository = sellRepository;
        this.brokerService = brokerService;
        this.purchaserService = purchaserService;
        this.cooperationRepository = cooperationRepository;
        this.apartmentRepository = apartmentRepository;
        this.garageRepository = garageRepository;
        this.parkingPlaceRepository = parkingPlaceRepository;
        this.paymentRepository = paymentRepository;
        this.installmentRepository = installmentRepository;
        this.projectRepository = projectRepository;
        this.incomeRepository = incomeRepository;
        this.purchaserRepository = purchaserRepository;
        this.brokerRepository = brokerRepository;

        this.expenseRepository = expenseRepository;
    }
    @Transactional
    public String createSell(Long id, CreateSellDTO createSellDTO,String filePath) {
        Sell sell = new Sell();
        sell.setFilePath(filePath);
        sell.setCooperationId(id);
        Payment payment = new Payment();
        sell.setPayment(payment);
        payment.setSell(sell);

        contractSetData( createSellDTO, sell);
        Sell firstSAvedSell = sellRepository.saveAndFlush(sell);
        addObjectsInSell(createSellDTO, firstSAvedSell);






        Sell savedSell = sellRepository.saveAndFlush(firstSAvedSell);

        payment.setAmountRemaining(createSellDTO.getTotalPriceInEuro());
        payment.setAmountReceived(0);
        payment.setInstallmentCount(createSellDTO.getInstallmentAndDates().length);
        payment.setPaymentStatus(PaymentStatus.IN_PROGRESS);

        Payment savedPayment = paymentRepository.saveAndFlush(payment);
        int logStart = 1;
        int logEnd = createSellDTO.getInstallmentAndDates().length;
        for (InstallmentAndDate installmentAndDate : createSellDTO.getInstallmentAndDates()) {

            String log = logStart + "/" + logEnd;
            logStart++;
            Installment installment = new Installment();
            installment.setInstallmentLog(log);
            installment.setPayment(savedPayment);
            installment.setInstallmentAmount(installmentAndDate.getSumInEuros());
            installment.setInstallmentDate(installmentAndDate.getDate());
            installment.setPayStatus(false);
            Installment savedInstallment = installmentRepository.saveAndFlush(installment);
            savedPayment.getInstallments().add(savedInstallment);
            paymentRepository.saveAndFlush(savedPayment);


            createIncomes(id, installmentAndDate, log, savedSell, installment, savedInstallment);

        }
        expenseCreate(createSellDTO);
        savedSell.setPayment(savedPayment);
        sellRepository.saveAndFlush(savedSell);
        return "Success";
    }

    // todo @Async
    private void expenseCreate(CreateSellDTO createSellDTO) {
        if(createSellDTO.getBrokerFirstName()!=null&& createSellDTO.getBrokerLastName()!=null&& createSellDTO.getBrokerEmail()!=null){
            Expense expense = new Expense();
            expense.setCategory("Broker-Commission");
            expense.setDate(createSellDTO.getContractDate());
            expense.setAmountEuro(createSellDTO.getBrokerProfitInEuro());
            expense.setPayedAmountEuro(0);
            expense.setRemainingAmountEuro(createSellDTO.getBrokerProfitInEuro());
            expense.setExpenseStatus(ExpenseStatus.WORK);
            expenseRepository.saveAndFlush(expense);

        }
    }

    private void createIncomes(Long id, InstallmentAndDate installmentAndDate, String log, Sell savedSell, Installment installment, Installment savedInstallment) {
        Income income = new Income();
        String incomeLog =  log + " " + StaticService.convertObjectsToString(savedSell);
        income.setIncomeLog(incomeLog);
        income.setDate(installmentAndDate.getDate());
        income.setAmountEuro(installmentAndDate.getSumInEuros());
        income.setPayStatus(PayStatus.NO);
        income.setInstallment(installment);
        Cooperation cooperation = cooperationRepository.findById(id).get();
        income.setBuilding(cooperation);
        Project project = projectRepository.findByBuildingId(id);
        income.setProject(project);
        income.setPayedAmountEuro(0);
        income.setRemainingAmountEuro(installmentAndDate.getSumInEuros());

        Cooperation cooperationForAddSell = cooperationRepository.findById(id).get();
        cooperationForAddSell.getSells().add(savedSell);
        Income savedIncome = incomeRepository.saveAndFlush(income);
        cooperationForAddSell.getIncomes().add(savedIncome);
        savedInstallment.setIncome(savedIncome);
        installmentRepository.saveAndFlush(savedInstallment);
        cooperationRepository.saveAndFlush(cooperationForAddSell);
        projectRepository.saveAndFlush(project);

    }

    private void addObjectsInSell(CreateSellDTO createSellDTO, Sell sell) {
        Set<Apartment> apartments = new HashSet<>();
        Set<Garage> garages = new HashSet<>();
        Set<ParkingPlace> parkingPlaces = new HashSet<>();
        for (SelfContainedUnits object : createSellDTO.getSelfContainedUnits()) {
            System.out.println("the number is: " + object.getNumber());
            if (object.getNumber().contains("Apartment")) {
                Apartment apartment = apartmentRepository.findById(object.getId()).get();
                apartment.setSold(true);
                apartment.setSell(sell);
                Apartment savedApartment = apartmentRepository.saveAndFlush(apartment);
                apartments.add(savedApartment);
            }else if (object.getNumber().contains("Garage")) {
                Garage garage = garageRepository.findById(object.getId()).get();
                garage.setSold(true);
                garage.setSell(sell);
                Garage savedGarage = garageRepository.saveAndFlush(garage);
                garages.add(savedGarage);
            }else if (object.getNumber().contains("Parking-Place")) {
                ParkingPlace parkingPlace = parkingPlaceRepository.findById(object.getId()).get();
                parkingPlace.setSold(true);
                parkingPlace.setSell(sell);
                ParkingPlace savedParkingPlace = parkingPlaceRepository.saveAndFlush(parkingPlace);
                parkingPlaces.add(savedParkingPlace);
            }
        }

        sell.setApartments(apartments);
        sell.setGarages(garages);
        sell.setParkingPlaces(parkingPlaces);
    }

    private void contractSetData( CreateSellDTO createSellDTO, Sell sell) {
        //String filePath = saveFile(contract);
        sell.setContractDate(createSellDTO.getContractDate());
        Broker broker = brokerService.createIfNotExist(createSellDTO.getBrokerFirstName(), createSellDTO.getBrokerLastName(), createSellDTO.getBrokerEmail());
        Purchaser purchaser = purchaserService.createIfNotExist(createSellDTO.getPurchaserFirstName(), createSellDTO.getPurchaserLastName(), createSellDTO.getPurchaserEmail());
        sell.setBroker(broker);
        sell.setPurchaser(purchaser);
        //sell.setFilePath(filePath);
        sell.setTotalPriceInEuro(createSellDTO.getTotalPriceInEuro());
        sell.setContractPriceInEuro(createSellDTO.getContractPriceInEuro());
        sell.setDiscountInEuro(createSellDTO.getDiscountInEuro());
        sell.setBrokerProfitInEuro(createSellDTO.getBrokerProfitInEuro());
        sell.setBrokerProfitInPercentage(createSellDTO.getBrokerProfitInPercentage());
        sell.setDescription(createSellDTO.getDescription());
    }


    public List<ResponseSellDTO> getAllSells(Long id) {
        List<Sell> sells = sellRepository.findAllByCooperationId(id);
        List<ResponseSellDTO> response = mapToResponseSellDTO(sells);
        for (Sell sell : sells) {
            ResponseSellDTO responseSellDTO = convertSellToDto(sell);
            response.add(responseSellDTO);
        }
        return response;
    }

    private ResponseSellDTO convertSellToDto(Sell sell) {
        ResponseSellDTO responseSellDTO = new ResponseSellDTO();
        responseSellDTO.setId(sell.getId());
        responseSellDTO.setContractDate(sell.getContractDate());
        responseSellDTO.setObjects(convertObjectsToString(sell));
        responseSellDTO.setPurchaserName(sell.getPurchaser().getFirstName() + " " + sell.getPurchaser().getLastName());
        responseSellDTO.setBrokerName(sell.getBroker().getFirstName() + " " + sell.getBroker().getLastName());
        responseSellDTO.setTotalPriceInEuro(sell.getTotalPriceInEuro());
        responseSellDTO.setDiscountInEuro(sell.getDiscountInEuro());
        responseSellDTO.setInstallmentsCount(sell.getPayment().getInstallments().size());
        return responseSellDTO;
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

    private List<ResponseSellDTO> mapToResponseSellDTO(List<Sell> sells) {
    List<ResponseSellDTO> response = new ArrayList<>();


    return response;
    }

    public String deleteSell(Long sellId) {
        Sell sell = sellRepository.findById(sellId)
                .orElseThrow(() -> new EntityNotFoundException("Sell not found with ID: " + sellId));
        makeObjectsFree(sell);
        // Ръчно изтриване на Installments и Incomes
        Payment payment = sell.getPayment();
        if (payment != null) {
            for (Installment installment : payment.getInstallments()) {
                Income income = installment.getIncome();
                if (income != null) {
                    incomeRepository.delete(income); // Изтрий Income първо
                }
                installmentRepository.delete(installment); // След това Installment
            }
            paymentRepository.delete(payment); // Накрая Payment
        }

        // Премахване на зависимостите от Purchaser и Broker
        Purchaser purchaser = sell.getPurchaser();
        if (purchaser != null) {
            purchaser.getSells().remove(sell);
            purchaserRepository.saveAndFlush(purchaser);
        }

        Broker broker = sell.getBroker();
        if (broker != null) {
            broker.getSells().remove(sell);
            brokerRepository.saveAndFlush(broker);
        }

        sellRepository.delete(sell);
        return "Success";
    }



    private void makeObjectsFree(Sell sell) {
        //todo
        Set<Apartment> apartments = sell.getApartments();
        Set<Garage> garages = sell.getGarages();
        Set<ParkingPlace> parkingPlaces = sell.getParkingPlaces();
        for (Apartment apartment : apartments) {
            apartment.setSold(false);
            apartment.setSell(null);
            apartmentRepository.saveAndFlush(apartment);
        }
        for (Garage garage : garages) {
            garage.setSold(false);
            garage.setSell(null);
            garageRepository.saveAndFlush(garage);
        }
        for (ParkingPlace parkingPlace : parkingPlaces) {
            parkingPlace.setSold(false);
            parkingPlace.setSell(null);
            parkingPlaceRepository.saveAndFlush(parkingPlace);
        }
    }

    public List<ResponseSellForEditDTO> getAllSellsForEdit(Long id) {
        List<Sell> sells = sellRepository.findAllByCooperationId(id);
        List<ResponseSellForEditDTO> response = mapToResponseSellForEditDTO(sells);
        for (Sell sell : sells) {
            ResponseSellForEditDTO responseSellDTO = new ResponseSellForEditDTO();
            responseSellDTO.setId(sell.getId());
            responseSellDTO.setContractDate(sell.getContractDate());
            responseSellDTO.setObjects(convertObjectsToString(sell));
            responseSellDTO.setPurchaserName(sell.getPurchaser().getFirstName() + " " + sell.getPurchaser().getLastName());
            responseSellDTO.setBrokerName(sell.getBroker().getFirstName() + " " + sell.getBroker().getLastName());
            responseSellDTO.setTotalPriceInEuro(sell.getTotalPriceInEuro());
            responseSellDTO.setDiscountInEuro(sell.getDiscountInEuro());
            responseSellDTO.setInstallmentsCount(sell.getPayment().getInstallments().size());
            response.add(responseSellDTO);
        }
        return response;
    }

    private List<ResponseSellForEditDTO> mapToResponseSellForEditDTO(List<Sell> sells) {
    return null;
    }

    public ResponseSellDTO updatePurchaser(Long id, PurchaserDTO newPurchaserDTO) {
        // Намери Sell записа
        Sell sell = sellRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sell not found"));

        // Вземи текущия собственик
        Purchaser oldPurchaser = sell.getPurchaser();
        if (sellRepository.countByPurchaser(oldPurchaser) == 0) {
            purchaserRepository.delete(oldPurchaser);
        }

        // Премахни асоциацията със стария собственик
        sell.setPurchaser(null);
        sellRepository.saveAndFlush(sell); // Запази Sell без асоциация

        // Ако старият собственик няма други продажби, го изтрий
        if (oldPurchaser != null && oldPurchaser.getSells().isEmpty()) {
            purchaserRepository.delete(oldPurchaser);
        }

        // Създай или намери нов Purchaser
        Purchaser newPurchaser = purchaserService.createIfNotExist(
                newPurchaserDTO.getFirstName(),
                newPurchaserDTO.getLastName(),
                newPurchaserDTO.getEmail()
        );

        // Свържи Sell с новия Purchaser
        sell.setPurchaser(newPurchaser);
        Sell savedSell = sellRepository.saveAndFlush(sell);

        // Връщане на DTO резултат
        return convertSellToDto(savedSell);
    }
    @Transactional
    public ResponseSellDTO updateInstallments(Long cooperationId, Long id, UpdateInstallmentsSellDTO updateInstallmentsSellDTO) {

        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Payment not found"));

        Set<Installment> allInstalmentInPayment = payment.getInstallments();
        Set<Installment> paidInstalmentInPayment = new HashSet<>();
        Set<Installment> unpaidInstalmentInPayment = new HashSet<>();
        validateInstallmentsPaidUnpaid(allInstalmentInPayment, paidInstalmentInPayment, unpaidInstalmentInPayment);
        payment.setInstallmentCount(paidInstalmentInPayment.size() + updateInstallmentsSellDTO.getInstallmentsCount());
        Payment savedPayment = paymentRepository.saveAndFlush(payment);
        for (Installment installment : unpaidInstalmentInPayment) {
            System.out.println( "Unpaid installment: ");
            System.out.println( installment.getInstallmentLog());

        }

        for (Installment installment : unpaidInstalmentInPayment) {
            savedPayment.getInstallments().remove(installment);
            installmentRepository.delete(installment);
        }
        installmentRepository.flush();
        int index = 1;
        for (Installment installment : paidInstalmentInPayment) {
            installment.setInstallmentLog(index + "/" + savedPayment.getInstallmentCount());
            installmentRepository.saveAndFlush(installment);
            index++;
        }
        for (int i = 0; i < updateInstallmentsSellDTO.getInstallmentsCount(); i++) {
            Installment installment = new Installment();
            String newLog = index + "/" + savedPayment.getInstallmentCount();
            installment.setInstallmentLog(newLog);
            installment.setPayment(savedPayment);
            double newAmountOfInstallment = updateInstallmentsSellDTO.getInstallmentAndDates().get(i).getSumInEuros();
            LocalDate newDate = updateInstallmentsSellDTO.getInstallmentAndDates().get(i).getDate();

            installment.setInstallmentAmount(newAmountOfInstallment);
            installment.setInstallmentDate(newDate);
            installment.setPayStatus(false);
            Installment firstSave = installmentRepository.saveAndFlush(installment); // firstSave
            installmentRepository.saveAndFlush(firstSave);

            payment.getInstallments().add(firstSave);
            paymentRepository.saveAndFlush(payment);
            index++;
        }
        Sell sell = savedPayment.getSell();

        for (Installment installment : sell.getPayment().getInstallments()) {
            if (installment.getIncome() == null) {
                // Намиране на кооперацията
                Cooperation cooperation = cooperationRepository.findById(cooperationId).orElseThrow(() ->
                        new EntityNotFoundException("Cooperation not found"));

                // Създаване на нов обект Income
                Income income = new Income();
                income.setAmountEuro(installment.getInstallmentAmount());
                income.setPayedAmountEuro(0);
                income.setRemainingAmountEuro(installment.getInstallmentAmount());
                income.setDate(installment.getInstallmentDate());
                income.setPayStatus(PayStatus.NO);

                // Настройка на асоциациите
                income.setBuilding(cooperation); // Важно: Увери се, че building е кооперацията
                income.setProject(cooperation.getParcel().getProject());
                income.setIncomeLog(generateIncomeLog(installment));
                income.setInstallment(installment);

                // Запазване на Income
                Income savedIncome = incomeRepository.saveAndFlush(income);

                // Актуализиране на кооперацията
                cooperation.getIncomes().add(savedIncome);
                cooperationRepository.saveAndFlush(cooperation);

                // Актуализиране на Installment
                installment.setIncome(savedIncome);
                installmentRepository.saveAndFlush(installment);

            } else {
                // Актуализиране на Income Log, ако вече има Income
                installment.getIncome().setIncomeLog(generateIncomeLog(installment));
            }
        }

        ResponseSellDTO responseSellDTO = convertSellToDto(sell);
        return responseSellDTO;
    }

    private static String generateIncomeLog(Installment installment) {
        return installment.getInstallmentLog()
                + " "
                + StaticService.convertObjectsToString
                (installment.getPayment().getSell());
    }

    private static void validateInstallmentsPaidUnpaid(Set<Installment> allInstalmentInPayment, Set<Installment> paidInstalmentInPayment, Set<Installment> unpaidInstalmentInPayment) {
        for (Installment installment : allInstalmentInPayment) {
            if(installment.getIncome().getRemainingAmountEuro() == 0){
                paidInstalmentInPayment.add(installment);
            }else {
                unpaidInstalmentInPayment.add(installment);
            }
        }
    }


    //private void changePurchaserInSos(Sell sell ,Purchaser purchaser) {
    //    Set<Apartment> apartments = sell.getApartments();
    //    Set<Garage> garages = sell.getGarages();
    //    Set<ParkingPlace> parkingPlaces = sell.getParkingPlaces();
    //    //todo but not need for now
    //}
}
