package com.example.demo.repository;

import com.example.demo.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    List<Contract> findByStatus(String status);

    List<Contract> findByPartyA(String partyA);

    List<Contract> findByPartyB(String partyB);

    List<Contract> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

    List<Contract> findByPartyAAndStatus(String partyA, String status);

    List<Contract> findByPartyBAndStatus(String partyB, String status);
}
