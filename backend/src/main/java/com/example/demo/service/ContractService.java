package com.example.demo.service;

import com.example.demo.dto.ContractDTO;
import com.example.demo.model.Contract;
import com.example.demo.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;

    public ContractDTO createContract(ContractDTO contractDTO) {
        Contract contract = Contract.builder()
                .title(contractDTO.getTitle())
                .description(contractDTO.getDescription())
                .startDate(contractDTO.getStartDate())
                .endDate(contractDTO.getEndDate())
                .amount(contractDTO.getAmount())
                .status(contractDTO.getStatus())
                .partyA(contractDTO.getPartyA())
                .partyB(contractDTO.getPartyB())
                .build();

        Contract savedContract = contractRepository.save(contract);
        return mapToDTO(savedContract);
    }

    public ContractDTO updateContract(Long id, ContractDTO contractDTO) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found with id: " + id));

        contract.setTitle(contractDTO.getTitle());
        contract.setDescription(contractDTO.getDescription());
        contract.setStartDate(contractDTO.getStartDate());
        contract.setEndDate(contractDTO.getEndDate());
        contract.setAmount(contractDTO.getAmount());
        contract.setStatus(contractDTO.getStatus());
        contract.setPartyA(contractDTO.getPartyA());
        contract.setPartyB(contractDTO.getPartyB());

        Contract updatedContract = contractRepository.save(contract);
        return mapToDTO(updatedContract);
    }

    public ContractDTO getContractById(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found with id: " + id));
        return mapToDTO(contract);
    }

    public List<ContractDTO> getAllContracts() {
        return contractRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<ContractDTO> getContractsByStatus(String status) {
        return contractRepository.findByStatus(status).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<ContractDTO> getContractsByPartyA(String partyA) {
        return contractRepository.findByPartyA(partyA).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<ContractDTO> getContractsByPartyB(String partyB) {
        return contractRepository.findByPartyB(partyB).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<ContractDTO> getContractsByDateRange(LocalDate startDate, LocalDate endDate) {
        return contractRepository.findByStartDateBetween(startDate, endDate).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void deleteContract(Long id) {
        if (!contractRepository.existsById(id)) {
            throw new RuntimeException("Contract not found with id: " + id);
        }
        contractRepository.deleteById(id);
    }

    private ContractDTO mapToDTO(Contract contract) {
        return ContractDTO.builder()
                .id(contract.getId())
                .title(contract.getTitle())
                .description(contract.getDescription())
                .startDate(contract.getStartDate())
                .endDate(contract.getEndDate())
                .amount(contract.getAmount())
                .status(contract.getStatus())
                .partyA(contract.getPartyA())
                .partyB(contract.getPartyB())
                .createdAt(contract.getCreatedAt())
                .updatedAt(contract.getUpdatedAt())
                .build();
    }
}
