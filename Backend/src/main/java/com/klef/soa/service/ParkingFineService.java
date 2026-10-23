package com.klef.soa.service;

import java.util.List;

import com.klef.soa.dto.ParkingFineRequest;
import com.klef.soa.dto.ParkingFineResponse;

public interface ParkingFineService {

    ParkingFineResponse saveParkingFine(ParkingFineRequest request);

    List<ParkingFineResponse> getAllParkingFines();

    void deleteParkingFine(Long id);
}
