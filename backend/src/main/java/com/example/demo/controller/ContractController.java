package com.example.demo.controller;

import com.example.demo.dto.ContractDTO;
import com.example.demo.service.ContractService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/contracts")
@CrossOrigin(originPatterns = {"http://localhost:4200", "https://*.app.github.dev"})
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @PostMapping
    public ResponseEntity<ContractDTO> createContract(@Valid @RequestBody ContractDTO contractDTO) {
        ContractDTO createdContract = contractService.createContract(contractDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContract);
    }

    @GetMapping
    public ResponseEntity<List<ContractDTO>> getAllContracts() {
        List<ContractDTO> contracts = contractService.getAllContracts();
        return ResponseEntity.ok(contracts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractDTO> getContractById(@PathVariable Long id) {
        ContractDTO contract = contractService.getContractById(id);
        return ResponseEntity.ok(contract);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContractDTO> updateContract(@PathVariable Long id, @Valid @RequestBody ContractDTO contractDTO) {
        ContractDTO updatedContract = contractService.updateContract(id, contractDTO);
        return ResponseEntity.ok(updatedContract);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable Long id) {
        contractService.deleteContract(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ContractDTO>> getContractsByStatus(@PathVariable String status) {
        List<ContractDTO> contracts = contractService.getContractsByStatus(status);
        return ResponseEntity.ok(contracts);
    }

    @GetMapping("/party-a/{partyA}")
    public ResponseEntity<List<ContractDTO>> getContractsByPartyA(@PathVariable String partyA) {
        List<ContractDTO> contracts = contractService.getContractsByPartyA(partyA);
        return ResponseEntity.ok(contracts);
    }

    @GetMapping("/party-b/{partyB}")
    public ResponseEntity<List<ContractDTO>> getContractsByPartyB(@PathVariable String partyB) {
        List<ContractDTO> contracts = contractService.getContractsByPartyB(partyB);
        return ResponseEntity.ok(contracts);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<ContractDTO>> getContractsByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<ContractDTO> contracts = contractService.getContractsByDateRange(startDate, endDate);
        return ResponseEntity.ok(contracts);
    }
}
