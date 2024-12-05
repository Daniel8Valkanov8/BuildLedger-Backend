package com.buildledger.backend.controller.ledger;


import com.buildledger.backend.dto.request.ledger.AddAmountDTO;
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

    @GetMapping("/{id}")
    public ResponseEntity<List<ResponseIncomeDTO>> getIncomesByProjectId(@PathVariable long id) {
        List<ResponseIncomeDTO> response = incomeService.getIncomesByCooperationId(id);
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
}
