package com.buildledger.backend.service.impl;

import com.buildledger.backend.dto.responce.PurchaserDTO;
import com.buildledger.backend.model.persons.Purchaser;
import com.buildledger.backend.repository.PurchaserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class PurchaserService {

    private final PurchaserRepository purchaserRepository;

    public PurchaserService(PurchaserRepository purchaserRepository) {
        this.purchaserRepository = purchaserRepository;
    }
    public Purchaser createIfNotExist(String firstName, String lastName, String email) {
        Optional<Purchaser> purchaser = purchaserRepository.findByFirstNameAndLastNameAndEmail(firstName, lastName, email);
        if (purchaser.isEmpty()) {
            Purchaser newPurchaser = new Purchaser();
            newPurchaser.setFirstName(firstName);
            newPurchaser.setLastName(lastName);
            newPurchaser.setEmail(email);
            return purchaserRepository.saveAndFlush(newPurchaser);
        }
        return purchaser.get();
    }

    public List<PurchaserDTO> getAllPurchasers() {
        List<Purchaser> all = purchaserRepository.findAll();
        List<PurchaserDTO> responsePurchaserDTOS =
                new ArrayList<>();
        for (Purchaser purchaser : all) {
            PurchaserDTO responsePurchaserDTO = new PurchaserDTO();
            BeanUtils.copyProperties(purchaser, responsePurchaserDTO);
            responsePurchaserDTOS.add(responsePurchaserDTO);
        }
        return responsePurchaserDTOS;
    }
}
