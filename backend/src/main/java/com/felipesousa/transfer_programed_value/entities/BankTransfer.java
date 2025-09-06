package com.felipesousa.transfer_programed_value.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_bank_transfer")
public class BankTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountOrigin;
    private String accountDestiny;
    private BigDecimal valueTransfer;
    private BigDecimal payTax;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant scheduleTransferDate;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant transferDate;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updateAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updateAt = Instant.now();
    }

}
