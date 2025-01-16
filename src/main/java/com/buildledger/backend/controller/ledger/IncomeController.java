package com.buildledger.backend.controller.ledger;


import com.buildledger.backend.dto.request.ledger.AddAmountDTO;
import com.buildledger.backend.dto.request.ledger.CreateExpenseDTO;
import com.buildledger.backend.dto.responce.ledger.ResponseIncomeDTO;
import com.buildledger.backend.service.ledger.IncomeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("http://localhost:3000")
@RestController()
@RequestMapping("/cooperation/all-incomes")
public class IncomeController {
    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping("/{id}/create")
    public ResponseEntity<String> createSell(
            @PathVariable Long id,
            @RequestBody CreateExpenseDTO createIncome)
    {
        String response = incomeService.createIncomeInCooperation(id, createIncome);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<ResponseIncomeDTO>> getIncomesByProjectId(@PathVariable long id) {
        List<ResponseIncomeDTO> response = incomeService.getIncomesByCooperationId(id);
        //todo returns in dto
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<String>addIncomeAmount(@PathVariable long id,@RequestBody AddAmountDTO addIncomeAmountDTO){
        String response = incomeService.addIncomeAmount(id,addIncomeAmountDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/today/{id}")
    public ResponseEntity<List<ResponseIncomeDTO>> getTodayIncomesByProjectId(@PathVariable long id) {
        List<ResponseIncomeDTO> response = incomeService.getTodayIncomesByCooperationId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteIncomeById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(incomeService.deleteIncome(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
