package com.BioLabs.BioDesignApplication.controller;

import com.BioLabs.BioDesignApplication.entity.Patient;
import com.BioLabs.BioDesignApplication.exception.ResourceNotFoundException;
import com.BioLabs.BioDesignApplication.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        Patient newPatient = patientService.addPatient(patient);
        return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
    }

    @PostMapping("/addAll")
    public ResponseEntity<String> addPatientAll(@RequestBody List<Patient> patient) {

        for(Patient patient1:patient){
            patientService.addPatient(patient1);
        }

        return new ResponseEntity<>("Done Successfull", HttpStatus.CREATED);
    }

    @GetMapping("/view")
    public ResponseEntity<Page<Patient>> getAllPatients(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Patient> patients = patientService.getAllPatients(pageable, search);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id)
                .map(patient -> new ResponseEntity<>(patient, HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + id));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id,  @RequestBody Patient patient) {
        Patient updatedPatient = patientService.updatPatient(id, patient);
        return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
