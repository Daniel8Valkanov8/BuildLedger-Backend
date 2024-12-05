package com.buildledger.backend.controller.ledger;


import com.buildledger.backend.dto.request.CreateTransactionDTO;
import com.buildledger.backend.dto.responce.ledger.ResponseTransactionDTO;
import com.buildledger.backend.service.ledger.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cooperation/{id}")
public class TransactionController {

    private  final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<ResponseTransactionDTO>> getTransactionsByCooperation(@PathVariable long id) {

            List<ResponseTransactionDTO> response = transactionService.getTransactionsByCooperation(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create-indipendense-transaction")
    public ResponseEntity<ResponseTransactionDTO> createIndipendenseTransaction(@PathVariable long id, @RequestBody CreateTransactionDTO transactionDTO) {

        ResponseTransactionDTO response = transactionService.createIndependenceTransaction(id, transactionDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
