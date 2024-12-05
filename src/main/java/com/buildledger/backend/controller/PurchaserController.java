package com.buildledger.backend.controller;

import com.buildledger.backend.dto.responce.PurchaserDTO;
import com.buildledger.backend.service.impl.PurchaserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") // Заменете с адреса на вашето React приложение
@RestController
@RequestMapping("/purchasers")
public class PurchaserController {

    private final PurchaserService  purchaserService;
    //todo
    public PurchaserController(PurchaserService purchaserService) {
        this.purchaserService = purchaserService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PurchaserDTO>> getAllPurchasers(){
        return new ResponseEntity<>(purchaserService.getAllPurchasers(), HttpStatus.OK );
    }


}
