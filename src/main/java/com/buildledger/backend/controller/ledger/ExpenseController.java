package com.buildledger.backend.controller.ledger;


import com.buildledger.backend.dto.request.ledger.AddAmountDTO;
import com.buildledger.backend.dto.request.ledger.CreateExpenseDTO;
import com.buildledger.backend.dto.responce.ledger.ResponseExpenseDTO;
import com.buildledger.backend.service.ledger.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController()
@RequestMapping("/cooperation/all-expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }


    @PostMapping("/{id}/create")
    public ResponseEntity<String> createSell(
            @PathVariable Long id,
            @RequestBody CreateExpenseDTO createExpenseDTO)
    {
        String response = expenseService.createExpenseInCooperation(id, createExpenseDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/table")
    public ResponseEntity<List<ResponseExpenseDTO>> getExpensesByCooperationId(@PathVariable long id) {
        List<ResponseExpenseDTO> response = expenseService.getExpensesByCooperationId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<String>addIncomeAmount(@PathVariable long id,@RequestBody AddAmountDTO addAmountDTO){
        String response = expenseService.addExpenseAmount(id,addAmountDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/today/{id}")
    public ResponseEntity<List<ResponseExpenseDTO>> getTodayIncomesByProjectId(@PathVariable long id) {
        List<ResponseExpenseDTO> response = expenseService.getTodayExpenseByCooperationId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteExpenseById(@PathVariable Long id){
        return new ResponseEntity<>(expenseService.deleteExpense(id), HttpStatus.OK);
    }
}
