package com.buildledger.backend.controller.sos;

import com.buildledger.backend.dto.request.sos.ResponseApartmentTableRowDTO;
import com.buildledger.backend.dto.request.sos.UpdateApartmentDTO;
import com.buildledger.backend.dto.responce.objects.ResponseApartmentDTO;
import com.buildledger.backend.dto.responce.objects.ResponseApartmentInformationDTO;
import com.buildledger.backend.service.impl.building.ApartmentService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/apartments") // Основният път
public class ApartmentController {
    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @PutMapping("/{id}/add-description")
    public ResponseEntity<String>
    addApartmentDescription(@PathVariable(value = "id") long id,@RequestParam String description) {
        apartmentService.addDescription(id, description);
        return new ResponseEntity<>( "Added description", HttpStatus.OK);
    }

    // Метод за получаване на апартаментите по ID на кооперацията с GET заявка
    @GetMapping({"/{id}"})
    public ResponseEntity<List<ResponseApartmentDTO>> getAllApartmentsByCooperationID(@PathVariable long id) {
        System.out.println("Fetching apartments for cooperation ID: " + id);
        List<ResponseApartmentDTO> response = apartmentService.getAllApartmentsByCooperationID(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/is-free/{id}")
    public ResponseEntity<List<ResponseApartmentDTO>> getAllFreeApartmentsByCooperationID(@PathVariable long id) {
        System.out.println("Fetching apartments for cooperation ID: " + id);
        List<ResponseApartmentDTO> response = apartmentService.getAllFreeApartmentsByCooperationID(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/cooperation/{cooperationId}/apartment/{apartmentId}")
    public ResponseEntity<ResponseApartmentInformationDTO> getApartmentByCooperationIdAndApartmentId(@PathVariable long cooperationId, @PathVariable long apartmentId) {
        ResponseApartmentInformationDTO response = apartmentService.getApartmentByCooperationIdAndApartmentId(cooperationId, apartmentId);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/apartment-get-contract/{apartmentId}/file")
    public ResponseEntity<Resource> getContractOfApartment(@PathVariable long apartmentId) {
        try {
            Resource resource = apartmentService.getContract(apartmentId);
            if (resource != null && resource.exists()) {
                // Получаваме оригиналното име на файла от базата данни
                String fileName = apartmentService.getFileName(apartmentId);

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                        .contentType(MediaType.APPLICATION_OCTET_STREAM) // Универсален MIME тип
                        .body(resource);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    




    @GetMapping("/{id}/table")
    public ResponseEntity<List<ResponseApartmentTableRowDTO>> getAllApartmentByCooperationIdInTable(@PathVariable long id) {
        List<ResponseApartmentTableRowDTO> response = apartmentService.getApartmentByCooperationIdInTable(id);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping("/update")
    public ResponseEntity<ResponseApartmentDTO> updateApartment(@RequestBody UpdateApartmentDTO updateApartmentDTO) {
        ResponseApartmentDTO response = apartmentService.updateApartment(updateApartmentDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
