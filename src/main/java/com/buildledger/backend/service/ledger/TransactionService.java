package com.buildledger.backend.service.ledger;

import com.buildledger.backend.dto.request.CreateTransactionDTO;
import com.buildledger.backend.dto.responce.ledger.ResponseTransactionDTO;
import com.buildledger.backend.enums.MyTransactionStatus;
import com.buildledger.backend.enums.PayStatus;
import com.buildledger.backend.model.building.Building;
import com.buildledger.backend.model.building.Cooperation;
import com.buildledger.backend.model.ledger.Sell;
import com.buildledger.backend.model.ledger.accounting.Expense;
import com.buildledger.backend.model.ledger.accounting.Income;
import com.buildledger.backend.model.ledger.accounting.Transaction;
import com.buildledger.backend.model.sos.Apartment;
import com.buildledger.backend.model.sos.Garage;
import com.buildledger.backend.model.sos.ParkingPlace;
import com.buildledger.backend.repository.CooperationRepository;
import com.buildledger.backend.repository.TransactionRepository;
import org.springframework.data.repository.core.support.FragmentNotImplementedException;
import org.springframework.stereotype.Service;
import org.springframework.data.repository.core.support.FragmentNotImplementedException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CooperationRepository cooperationRepository;

    public TransactionService(com.buildledger.backend.repository.TransactionRepository transactionRepository, CooperationRepository cooperationRepository) {
        this.transactionRepository = transactionRepository;
        this.cooperationRepository = cooperationRepository;
    }


    public Transaction createIncomeTransaction(Cooperation cooperation, Income income, double amountForAdd, PayStatus payStatus) {
        LocalDate currentDate = LocalDate.now();
        Transaction transaction = initTransactionByIncome(cooperation, income, amountForAdd, payStatus, currentDate);

        Sell sell = income.getInstallment().getPayment().getSell();
        StringBuilder sellLog = initIncomeSellLog(sell);

        transaction.setTransactionStatus(MyTransactionStatus.INCOME);
        transaction.setTransactionLog("Income transaction by installment: " + income.getIncomeLog() + " Sell: " + sellLog.toString());
        Transaction savedTransaction = transactionRepository.saveAndFlush(transaction);
        return savedTransaction;
    }


    public Transaction createExpenseTransaction(Building cooperation, Expense expense,
                                                double amountForAdd,
                                                PayStatus payStatus) {
        LocalDate currentDate = LocalDate.now();
        Transaction transaction = new Transaction();

        transaction.setBuilding(cooperation);
        transaction.setPayedAmountEuro(amountForAdd);
        transaction.setAmountEuro(expense.getAmountEuro());
        transaction.setRemainingAmountEuro(expense.getRemainingAmountEuro());
        transaction.setPayStatus(expense.getPayStatus());
        transaction.setDate(currentDate);


        //todo sell log
        transaction.setTransactionStatus(MyTransactionStatus.EXPENSE);
        transaction.setTransactionLog("Expense: " + expense.toString());
        Transaction savedTransaction = transactionRepository.saveAndFlush(transaction);
        return savedTransaction;
    }

    //todo
    public List<ResponseTransactionDTO> getTransactionsByCooperation(long id) {
        List<Transaction> transactions = transactionRepository.findByBuildingId(id);
        List<ResponseTransactionDTO> response = new ArrayList<>();
        for (Transaction transaction : transactions) {
            ResponseTransactionDTO dto = new ResponseTransactionDTO();
            dto.setId(transaction.getId());
            dto.setAmountEuro(transaction.getAmountEuro());
            dto.setDate(transaction.getDate());
            System.out.println(transaction.getPayStatus());
            dto.setPayStatus(transaction.getPayStatus().toString());
            dto.setPayedAmountEuro(transaction.getPayedAmountEuro());
            dto.setRemainingAmountEuro(transaction.getRemainingAmountEuro());
            dto.setMyTransactionStatus(transaction.getTransactionStatus());
            dto.setTransactionLog(transaction.getTransactionLog());
            response.add(dto);
        }
        return response;
    }


    private static Transaction initTransactionByIncome(Cooperation cooperation, Income income, double amountForAdd, PayStatus payStatus, LocalDate currentDate) {
        Transaction transaction = new Transaction();

        transaction.setBuilding(cooperation);
        transaction.setPayedAmountEuro(amountForAdd);
        transaction.setAmountEuro(income.getAmountEuro());
        transaction.setRemainingAmountEuro(income.getRemainingAmountEuro());
        transaction.setPayStatus(payStatus);
        transaction.setDate(currentDate);
        return transaction;
    }

    private static StringBuilder initIncomeSellLog(Sell sell) {
        StringBuilder sellLog = new StringBuilder();
        if (!sell.getApartments().isEmpty()) {
            int index = 0;
            for (Apartment apartment : sell.getApartments()) {
                if (index == sell.getApartments().size()) sellLog.append(apartment.getNumber()).append(". ");
                sellLog.append(apartment.getNumber()).append(", ");
            }
        }
        if (!sell.getGarages().isEmpty()) {
            int index = 0;
            for (Garage garage : sell.getGarages()) {
                if (index == sell.getGarages().size()) sellLog.append(garage.getNumber()).append(". ");
                sellLog.append(garage.getNumber()).append(", ");
            }
        }
        if (!sell.getParkingPlaces().isEmpty()) {
            int index = 0;
            for (ParkingPlace parkingPlace : sell.getParkingPlaces()) {
                if (index == sell.getParkingPlaces().size()) sellLog.append(parkingPlace.getNumber()).append(". ");
                sellLog.append(parkingPlace.getNumber()).append(", ");
                index++;
            }
        }
        return sellLog;
    }

    public ResponseTransactionDTO createIndependenceTransaction(long id, CreateTransactionDTO transactionDTO) throws FragmentNotImplementedException {
        Cooperation cooperation = cooperationRepository.findById(id).get();
        //TODO: Should be implemented!
        return null;
    }
}