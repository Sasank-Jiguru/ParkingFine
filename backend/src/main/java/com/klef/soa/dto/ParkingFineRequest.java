package com.klef.soa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ParkingFineRequest {

    @NotBlank(message = "Please enter FIne number")
    private String fineNumber;

    @NotBlank(message = "Enter Your Vehicle number")
    private String vehicleNumber;

    @NotNull(message = "Enter the Amount To be paid")
    @Positive(message = "Enter Positive numbers only")
    private Double amount;

    @NotNull(message = "Paid status is required")
    private Boolean paid;

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
