package com.buildledger.backend.controller.sell;


import com.buildledger.backend.dto.responce.sell.ResponseInstallmentDTO;
import com.buildledger.backend.service.sells.InstallmentService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/cooperation")
@RestController
public class InstallmentController {

    private final InstallmentService installmentService;

    public InstallmentController(InstallmentService installmentService) {
        this.installmentService = installmentService;
    }

    @GetMapping("/{id}/all-installments")
    public ResponseEntity<List<ResponseInstallmentDTO>> getAllInstallmentsByCooperationId
            (@PathVariable Long id) {
    List<ResponseInstallmentDTO> response = installmentService.
            getAllInstallmentsByCooperationId(id);
    return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
