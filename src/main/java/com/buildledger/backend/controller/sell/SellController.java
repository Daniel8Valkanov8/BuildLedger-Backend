package com.buildledger.backend.controller.sell;

import com.buildledger.backend.dto.request.sell.CreateSellDTO;
import com.buildledger.backend.dto.request.sell.ResponseSellForEditDTO;
import com.buildledger.backend.dto.request.sell.UpdateInstallmentsSellDTO;
import com.buildledger.backend.dto.responce.PurchaserDTO;
import com.buildledger.backend.dto.responce.sell.ResponseSellDTO;
import com.buildledger.backend.service.micro.FileMicroService;
import com.buildledger.backend.service.sells.SellService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/cooperation")
public class SellController {

    private final SellService sellService;
    private final FileMicroService fileMicroService;
    public SellController(SellService sellService, FileMicroService fileMicroService) {
        this.sellService = sellService;
        this.fileMicroService = fileMicroService;
    }

    @PostMapping("/{id}/create-sell")
    public ResponseEntity<String> createSell(
            @PathVariable Long id,
            @RequestPart("file") MultipartFile file,
            @RequestPart("payload") String payload)

    {  // Добавяме JSON данните като String
        String successSavedFile = this.fileMicroService.saveFile(file);
        CreateSellDTO createSellDTO = this.mapperToCreateSellDTO(payload);
        String response = sellService.createSell(id, createSellDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    private CreateSellDTO mapperToCreateSellDTO(String payload){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            return mapper.readValue(payload, CreateSellDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}/all-sells")
    public ResponseEntity<List<ResponseSellDTO>> getAllSells(@PathVariable Long id) {
        List<ResponseSellDTO> response = sellService.getAllSells(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/{id}/delete-sell")
    public ResponseEntity<String> deleteSellbyId(@PathVariable("id") Long sellId) {
        if (sellId == null) {
            return ResponseEntity.badRequest().body("Sell ID cannot be null");
        }
        try {
            String response = sellService.deleteSell(sellId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>("Sell not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}/all-sells/edit")
    public ResponseEntity<List<ResponseSellForEditDTO>> getAllSellsForEdit(@PathVariable Long id) {
        List<ResponseSellForEditDTO> response = sellService.getAllSellsForEdit(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/edit-sell/change-purchaser")
    public ResponseEntity<ResponseSellDTO> changePurchaser(@PathVariable Long id,
                                                           @RequestBody PurchaserDTO newPurchaser){


        ResponseSellDTO responseSellDTO = sellService.updatePurchaser(id,newPurchaser);
    return new ResponseEntity<>(responseSellDTO, HttpStatus.OK);
    }

    @PutMapping("{cooperationId}/payment/{id}/edit-sell/installments")
    public ResponseEntity<ResponseSellDTO> changeInstallments(@PathVariable Long cooperationId,
                                                              @PathVariable Long id,
                                                               @RequestBody UpdateInstallmentsSellDTO updateInstallmentsSellDTO){


        ResponseSellDTO responseSellDTO = sellService.updateInstallments(cooperationId,id,updateInstallmentsSellDTO);
        System.out.println("Success mapping");
        return new ResponseEntity<>(responseSellDTO, HttpStatus.OK);
    }


}
