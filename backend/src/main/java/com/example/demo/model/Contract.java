package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contracts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Contract title is required")
    @Size(min = 3, max = 255, message = "Contract title must be between 3 and 255 characters")
    @Column(nullable = false, length = 255)
    private String title;

    @NotBlank(message = "Contract description is required")
    @Size(max = 1000, message = "Contract description must not exceed 1000 characters")
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Contract start date is required")
    @Column(nullable = false)
    private LocalDate startDate;

    @NotNull(message = "Contract end date is required")
    @Column(nullable = false)
    private LocalDate endDate;

    @NotNull(message = "Contract amount is required")
    @DecimalMin(value = "0.01", message = "Contract amount must be greater than 0")
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @NotBlank(message = "Contract status is required")
    @Pattern(regexp = "DRAFT|ACTIVE|COMPLETED|CANCELLED", message = "Status must be one of: DRAFT, ACTIVE, COMPLETED, CANCELLED")
    @Column(nullable = false, length = 50)
    private String status;

    @NotBlank(message = "Party A name is required")
    @Size(min = 2, max = 255, message = "Party A name must be between 2 and 255 characters")
    @Column(nullable = false, length = 255)
    private String partyA;

    @NotBlank(message = "Party B name is required")
    @Size(min = 2, max = 255, message = "Party B name must be between 2 and 255 characters")
    @Column(nullable = false, length = 255)
    private String partyB;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
        updatedAt = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDate.now();
    }
}
