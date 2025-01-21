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

    // Controller Layer
    @DeleteMapping("/transactions/{transactionId}/delete")
    public ResponseEntity<String> deleteTransactionById(@PathVariable long id, @PathVariable long transactionId) {
        try {
            String response = transactionService.deleteTransactionById(id, transactionId);

            // Определяме правилния статус според отговора
            if (response.equals("Successfully deleted transaction")) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else if (response.equals("Transaction not found") || response.equals("Transaction does not belong to the specified building")) {
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            // В случай на неочаквана грешка
            e.printStackTrace();
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/create-indipendense-transaction")
    public ResponseEntity<ResponseTransactionDTO> createIndipendenseTransaction(@PathVariable long id, @RequestBody CreateTransactionDTO transactionDTO) {

        ResponseTransactionDTO response = transactionService.createIndependenceTransaction(id, transactionDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
