
package com.klef.soa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class ParkingFine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Add Fine number")
    private String fineNumber;

    @NotBlank(message = "Enter Your Vehicle number")
    private String vehicleNumber;

    @NotNull(message = "Please Enter the amound need to be paid")
    @Positive(message = "Enter positive numbers only")
    private Double amount;

    @NotNull(message = "Paid status is required")
    private Boolean paid;

    public ParkingFine() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFineNumber() {
        return fineNumber;
    }

    public void setFineNumber(String fineNumber) {
        this.fineNumber = fineNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }
}
