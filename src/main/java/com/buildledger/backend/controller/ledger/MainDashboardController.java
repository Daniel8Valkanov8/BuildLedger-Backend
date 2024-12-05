package com.buildledger.backend.controller.ledger;

import com.buildledger.backend.dto.responce.ResponseMainExpenseDTO;
import com.buildledger.backend.dto.responce.ResponseMainIncomeDTO;
import com.buildledger.backend.service.ledger.ExpenseService;
import com.buildledger.backend.service.ledger.IncomeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController()
@RequestMapping("/dashboard")
public class MainDashboardController {

    private final IncomeService incomeService;

    private final ExpenseService expenseService;

    public MainDashboardController(IncomeService incomeService, ExpenseService expenseService) {
        this.incomeService = incomeService;
        this.expenseService = expenseService;
    }

        @GetMapping("/all-incomes")
        public ResponseEntity<List<ResponseMainIncomeDTO>> getAllIncomes(){
            List<ResponseMainIncomeDTO> response = incomeService.getAllIncomes();
        return new ResponseEntity<>(response, HttpStatus.OK);
        }
        @GetMapping("/all-expenses")
        public ResponseEntity<List<ResponseMainExpenseDTO>> getAllExpenses(){
            List<ResponseMainExpenseDTO> response = expenseService.getAllExpenses();
        return new ResponseEntity<>(response, HttpStatus.OK);
        }
}
