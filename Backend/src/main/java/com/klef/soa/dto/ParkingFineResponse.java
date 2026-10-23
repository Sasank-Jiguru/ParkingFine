package com.klef.soa.dto;

public class ParkingFineResponse {

    private Long id;
    private String fineNumber;
    private String vehicleNumber;
    private Double amount;
    private Boolean paid;

    public ParkingFineResponse() {
    }

    public ParkingFineResponse(Long id, String fineNumber,
            String vehicleNumber, Double amount, Boolean paid) {
        this.id = id;
        this.fineNumber = fineNumber;
        this.vehicleNumber = vehicleNumber;
        this.amount = amount;
        this.paid = paid;
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
