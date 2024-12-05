package com.buildledger.backend.controller.sell;


import com.buildledger.backend.dto.responce.sell.ResponsePaymentDTO;
import com.buildledger.backend.service.sells.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/cooperation")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/{id}/all-payments")
    public ResponseEntity<List<ResponsePaymentDTO>> getAllPayments(@PathVariable Long id) {
           List<ResponsePaymentDTO> response = paymentService.getAllPaymentsByCooperationId(id);
            return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
