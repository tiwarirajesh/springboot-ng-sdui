package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractDTO {

    private Long id;

    private String contractId;

    private String customerName;

    @NotBlank(message = "Contract title is required")
    @Size(min = 3, max = 255, message = "Contract title must be between 3 and 255 characters")
    private String title;

    @Size(max = 1000, message = "Contract description must not exceed 1000 characters")
    private String description;

    @NotNull(message = "Contract start date is required")
    private LocalDate startDate;

    @NotNull(message = "Contract end date is required")
    private LocalDate endDate;

    @NotNull(message = "Contract amount is required")
    @DecimalMin(value = "0.01", message = "Contract amount must be greater than 0")
    private BigDecimal amount;

    @NotBlank(message = "Contract status is required")
    @Pattern(regexp = "DRAFT|ACTIVE|COMPLETED|CANCELLED", message = "Status must be one of: DRAFT, ACTIVE, COMPLETED, CANCELLED")
    private String status;

    @NotBlank(message = "Party A name is required")
    @Size(min = 2, max = 255, message = "Party A name must be between 2 and 255 characters")
    private String partyA;

    @NotBlank(message = "Party B name is required")
    @Size(min = 2, max = 255, message = "Party B name must be between 2 and 255 characters")
    private String partyB;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
