
package com.klef.soa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.klef.soa.dto.ParkingFineRequest;
import com.klef.soa.dto.ParkingFineResponse;
import com.klef.soa.entity.ParkingFine;
import com.klef.soa.exception.ResourceNotFoundException;
import com.klef.soa.repository.ParkingFineRepository;

@Service
public class ParkingFineServiceImpl implements ParkingFineService {

    private final ParkingFineRepository repository;

    public ParkingFineServiceImpl(ParkingFineRepository repository) {
        this.repository = repository;
    }

    @Override
    public ParkingFineResponse saveParkingFine(ParkingFineRequest request) {
        ParkingFine fine = new ParkingFine();

        fine.setFineNumber(request.getFineNumber());
        fine.setVehicleNumber(request.getVehicleNumber());
        fine.setAmount(request.getAmount());
        fine.setPaid(request.getPaid());

        ParkingFine saved = repository.save(fine);

        return convertToResponse(saved);
    }

    @Override
    public List<ParkingFineResponse> getAllParkingFines() {
        return repository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteParkingFine(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Parking fine not found with ID: " + id);
        }

        repository.deleteById(id);
    }

    private ParkingFineResponse convertToResponse(ParkingFine fine) {
        return new ParkingFineResponse(
                fine.getId(),
                fine.getFineNumber(),
                fine.getVehicleNumber(),
                fine.getAmount(),
                fine.getPaid()
        );
    }
}
