package com.buildledger.backend.service.ledger;


import com.buildledger.backend.dto.request.ledger.AddAmountDTO;
import com.buildledger.backend.dto.responce.ResponseMainIncomeDTO;
import com.buildledger.backend.dto.responce.ledger.ResponseIncomeDTO;
import com.buildledger.backend.enums.PayStatus;
import com.buildledger.backend.enums.PaymentStatus;
import com.buildledger.backend.model.building.Cooperation;
import com.buildledger.backend.model.ledger.Installment;
import com.buildledger.backend.model.ledger.Payment;
import com.buildledger.backend.model.ledger.accounting.Income;
import com.buildledger.backend.model.ledger.accounting.Transaction;
import com.buildledger.backend.repository.InstallmentRepository;
import com.buildledger.backend.service.micro.MicroCooperationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.buildledger.backend.repository.IncomeRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncomeService {

    private final IncomeRepository incomeRepository;
    private final MicroCooperationService microCooperationService;
    private final TransactionService transactionService;
    private final InstallmentRepository installmentRepository;


    public IncomeService(IncomeRepository incomeRepository, MicroCooperationService microCooperationService, TransactionService transactionService, InstallmentRepository installmentRepository) {
        this.incomeRepository = incomeRepository;
        this.microCooperationService = microCooperationService;
        this.transactionService = transactionService;
        this.installmentRepository = installmentRepository;
    }


    public List<ResponseIncomeDTO> getIncomesByCooperationId(Long id) {
        List<Income> allIncomes = this.incomeRepository.findByCooperationId(id);

        return allIncomes.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private ResponseIncomeDTO convertToResponseDTO(Income income) {
        ResponseIncomeDTO response = new ResponseIncomeDTO();

        response.setId(income.getId());
        response.setAmountEuro(income.getAmountEuro());
        response.setDate(income.getDate());
        response.setPayStatus(income.getPayStatus());
        response.setPayedAmountEuro(income.getPayedAmountEuro());
        response.setRemainingAmountEuro(income.getRemainingAmountEuro());
        response.setLog(income.getIncomeLog());
        return response;
    }

    public String addIncomeAmount(long id, AddAmountDTO addIncomeAmountDTO) {
        Cooperation cooperation = microCooperationService.findById(id);
        Income income = incomeRepository.findById(addIncomeAmountDTO.getId()).get();

        Installment installment = income.getInstallment();

        Payment payment = income.getInstallment().getPayment();
        payment.setAmountReceived(payment.getAmountReceived() + addIncomeAmountDTO.getAddAmountInEuro());
        payment.setAmountRemaining(payment.getAmountRemaining() - addIncomeAmountDTO.getAddAmountInEuro());
        if(payment.getAmountRemaining() == 0) {payment.setPaymentStatus(PaymentStatus.PAID);};

        double currentAmount = income.getPayedAmountEuro();
        double remainingAmount = income.getRemainingAmountEuro();
        double amountForAdd = addIncomeAmountDTO.getAddAmountInEuro();

        PayStatus currentPayStatus = null;
        income.setPayedAmountEuro(currentAmount + amountForAdd);
        income.setRemainingAmountEuro(remainingAmount - amountForAdd);
        if(addIncomeAmountDTO.getPayStatus().equals("CASH")) {
            currentPayStatus = PayStatus.CASH;
            income.setPayStatus(currentPayStatus);
        }else if(addIncomeAmountDTO.getPayStatus().equals("BANK")) {
            currentPayStatus = PayStatus.BANK;
            income.setPayStatus(currentPayStatus);
        }
        Income savedIncome = incomeRepository.saveAndFlush(income);
        if(savedIncome.getRemainingAmountEuro() == 0) {
            installment.setPayStatus(true);
            Installment savedInstallment = installmentRepository.saveAndFlush(installment);
        }
        Transaction transaction = transactionService.createIncomeTransaction(cooperation, savedIncome, amountForAdd,currentPayStatus);
        Cooperation cooperation1 = microCooperationService.findById(id);
        cooperation1.getTransactions().add(transaction);
        microCooperationService.saveAndFlush(cooperation1);
         return "Create successfully";
    }

    public List<ResponseIncomeDTO> getTodayIncomesByCooperationId(long id) {
        LocalDate currentDate = LocalDate.now();
        List<Income> todayIncomes = this.incomeRepository.findByCooperationIdAndDate(id, currentDate);
        return todayIncomes.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<ResponseMainIncomeDTO> getAllIncomes() {
        List<Income> allIncomes = incomeRepository.findAll();
        List<ResponseMainIncomeDTO> response = mapToResponse(allIncomes);
        return response;
    }

    private List<ResponseMainIncomeDTO> mapToResponse(List<Income> allIncomes) {
        List<ResponseMainIncomeDTO> response = new ArrayList<>();
        for (Income income : allIncomes) {
            ResponseMainIncomeDTO responseMainIncomeDTO = new ResponseMainIncomeDTO();
            BeanUtils.copyProperties(income, responseMainIncomeDTO);
            responseMainIncomeDTO.setCooperation(income.getBuilding().getTitle());
            responseMainIncomeDTO.setProject(income.getBuilding().getParcel().getProject().getTitle());
            response.add(responseMainIncomeDTO);
        }
        return response;
    }

}