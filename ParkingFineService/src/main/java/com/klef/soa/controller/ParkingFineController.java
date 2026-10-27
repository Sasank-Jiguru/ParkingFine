
package com.klef.soa.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.soa.dto.ParkingFineRequest;
import com.klef.soa.dto.ParkingFineResponse;
import com.klef.soa.service.ParkingFineService;

@CrossOrigin(origins = {
    "http://localhost:3000",
    "http://localhost:5173"
})
@RestController
@RequestMapping("/parkingfines")
public class ParkingFineController {

    @Autowired
    private ParkingFineService parkingFineService;

    @PostMapping("/add")
    public ParkingFineResponse addParkingFine(
            @Valid
            @RequestBody
            ParkingFineRequest request) {

        return parkingFineService.saveParkingFine(request);
    }

    @GetMapping("/displayall")
    public List<ParkingFineResponse> displayAllParkingFines() {

        return parkingFineService.getAllParkingFines();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteParkingFine(@PathVariable Long id) {

        parkingFineService.deleteParkingFine(id);

        return "Parking fine deleted successfully";
    }
}
