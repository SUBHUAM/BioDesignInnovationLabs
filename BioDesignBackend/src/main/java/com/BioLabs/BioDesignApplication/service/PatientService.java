package com.BioLabs.BioDesignApplication.service;


import com.BioLabs.BioDesignApplication.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PatientService {

    Patient addPatient(Patient patient);

    Page<Patient> getAllPatients(Pageable pageable,String search);

    Optional<Patient> getPatientById(Long id);

    Patient updatPatient(Long id,Patient updatePatient);

    void deletePatient(Long id);


}
