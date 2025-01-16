package com.buildledger.backend.service.ledger;


import com.buildledger.backend.dto.request.ledger.AddAmountDTO;
import com.buildledger.backend.dto.request.ledger.CreateExpenseDTO;
import com.buildledger.backend.dto.responce.ResponseMainExpenseDTO;
import com.buildledger.backend.dto.responce.ResponseMainIncomeDTO;
import com.buildledger.backend.dto.responce.ledger.ResponseExpenseDTO;
import com.buildledger.backend.enums.ExpenseStatus;
import com.buildledger.backend.enums.PayStatus;
import com.buildledger.backend.model.building.Cooperation;
import com.buildledger.backend.model.ledger.accounting.Expense;
import com.buildledger.backend.model.ledger.accounting.Transaction;
import com.buildledger.backend.repository.BuildingRepository;
import com.buildledger.backend.repository.CooperationRepository;
import com.buildledger.backend.repository.ExpenseRepository;
import com.buildledger.backend.repository.ProjectRepository;
import com.buildledger.backend.service.micro.MicroCooperationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ProjectRepository projectRepository;
    private final CooperationRepository buildingRepository;
    private final TransactionService transactionService;
    private final MicroCooperationService microCooperationService;

    public ExpenseService(ExpenseRepository expenseRepository, ProjectRepository projectRepository, BuildingRepository buildingRepository, CooperationRepository buildingRepository1, TransactionService transactionService, MicroCooperationService microCooperationService) {
        this.expenseRepository = expenseRepository;
        this.projectRepository = projectRepository;

        this.buildingRepository = buildingRepository1;
        this.transactionService = transactionService;
        this.microCooperationService = microCooperationService;
    }


    public String createExpenseInCooperation(Long id, CreateExpenseDTO createExpenseDTO) {
        Expense expense = new Expense();
        Cooperation cooperation = buildingRepository.findById(id).get();
        expense.setBuilding(cooperation);
        Expense firstExpense = expenseRepository.saveAndFlush(expense);
        cooperation.getExpenses().add(firstExpense);

        if (createExpenseDTO.getFactureNumber()!=null) {
            expense.setFactureNumber(createExpenseDTO.getFactureNumber());
        }
        expense.setCategory(createExpenseDTO.getTitle());

       if(createExpenseDTO.getStatus().equals("WORK")){
           expense.setExpenseStatus(ExpenseStatus.WORK);
       }else if(createExpenseDTO.getStatus().equals("MATERIAL")){
           expense.setExpenseStatus(ExpenseStatus.MATERIAL);
       }else if(createExpenseDTO.getStatus().equals("TAX")){
           expense.setExpenseStatus(ExpenseStatus.TAX);
       }else if(createExpenseDTO.getStatus().equals("OTHER")){
           expense.setExpenseStatus(ExpenseStatus.OTHER);
       }
       expense.setProject(buildingRepository.findById(id).get().getParcel().getProject());
       expense.setAmountEuro(createExpenseDTO.getAmountEuro());
       expense.setPayedAmountEuro(createExpenseDTO.getPaid());
       double remaining = createExpenseDTO.getAmountEuro()-createExpenseDTO.getPaid();
        System.out.println(remaining);
        expense.setRemainingAmountEuro(remaining);
        expense.setPayStatus(createExpenseDTO.getPayStatus());
       expense.setDate(createExpenseDTO.getPaymentDate());

        Expense savedExpense = expenseRepository.saveAndFlush(expense);
        if(createExpenseDTO.getPaid()>0){
            Transaction transaction = transactionService.createExpenseTransaction(savedExpense.getBuilding(),
                    savedExpense, createExpenseDTO.getPaid(), savedExpense.getPayStatus());
            cooperation.getTransactions().add(transaction);
        }
        Cooperation savedCooperation = buildingRepository.saveAndFlush(cooperation);
        return "Expense created successfully";

    }



    public List<ResponseExpenseDTO> getExpensesByCooperationId(long id) {
        List<Expense> expenses = expenseRepository.findAllByBuildingId(id);
        List<ResponseExpenseDTO> response = new ArrayList<>();
        for (Expense expense : expenses) {
            ResponseExpenseDTO dto = new ResponseExpenseDTO();
            dto.setId(expense.getId());
            dto.setFactureNumber(expense.getFactureNumber());
            dto.setTitle(expense.getCategory());
            dto.setStatus(expense.getExpenseStatus().toString());
            dto.setAmountEuro(expense.getAmountEuro());
            dto.setPaymentDate(expense.getDate());
            dto.setPaid(expense.getPayedAmountEuro());
            dto.setRemain(expense.getRemainingAmountEuro());
            response.add(dto);
        }
        return response;
    }

    public List<ResponseExpenseDTO> getTodayExpenseByCooperationId(long id) {
        LocalDate currentDate = LocalDate.now();
        List<Expense> expenses = expenseRepository.findAllByBuildingIdAndDate(id, currentDate);
        List<ResponseExpenseDTO> response = new ArrayList<>();
        for (Expense expense : expenses) {
            ResponseExpenseDTO dto = new ResponseExpenseDTO();
            dto.setId(expense.getId());
            dto.setFactureNumber(expense.getFactureNumber());
            dto.setTitle(expense.getCategory());
            dto.setStatus(expense.getExpenseStatus().toString());
            dto.setAmountEuro(expense.getAmountEuro());
            dto.setPaymentDate(expense.getDate());
            dto.setPaid(expense.getPayedAmountEuro());
            dto.setRemain(expense.getRemainingAmountEuro());
            response.add(dto);
        }
        return response;

    }

    public String addExpenseAmount(long id, AddAmountDTO addAmountDTO) {
        Cooperation cooperation = microCooperationService.findById(id);
        Expense expense = expenseRepository.findById(addAmountDTO.getId()).get();
        double currentAmount = expense.getPayedAmountEuro();
        double remainingAmount = expense.getRemainingAmountEuro();
        double amountForAdd = addAmountDTO.getAddAmountInEuro();
        PayStatus currentPayStatus = null;
        expense.setPayedAmountEuro(currentAmount + amountForAdd);
        expense.setRemainingAmountEuro(remainingAmount - amountForAdd);
        if(addAmountDTO.getPayStatus().equals("CASH")) {
            currentPayStatus = PayStatus.CASH;
            expense.setPayStatus(currentPayStatus);
        }else if(addAmountDTO.getPayStatus().equals("BANK")) {
            currentPayStatus = PayStatus.BANK;
            expense.setPayStatus(currentPayStatus);
        }
        Expense savedExpense = expenseRepository.saveAndFlush(expense);

        Transaction transaction = transactionService.createExpenseTransaction(cooperation, savedExpense, amountForAdd,currentPayStatus);

        return "Create successfully";

    }

    public String deleteExpense(Long id) {
        expenseRepository.deleteById(id);
        return "Delete successfully";
    }

    public List<ResponseMainExpenseDTO> getAllExpenses() {
        List<Expense> allExpenses = expenseRepository.findAll();
        List<ResponseMainExpenseDTO> response = mapToResponse(allExpenses);
        return response;
    }

    private List<ResponseMainExpenseDTO> mapToResponse(List<Expense> allExpenses) {
        List<ResponseMainExpenseDTO> response = new ArrayList<>();
        for (Expense expense : allExpenses) {
           ResponseMainExpenseDTO responseMainExpenseDTO = new ResponseMainExpenseDTO();
            BeanUtils.copyProperties(expense,responseMainExpenseDTO );
            responseMainExpenseDTO.setCooperation(expense.getBuilding().getTitle());
            responseMainExpenseDTO.setProject(expense.getBuilding().getParcel().getProject().getTitle());
           response.add(responseMainExpenseDTO);
        }
        return response;
    }
}
