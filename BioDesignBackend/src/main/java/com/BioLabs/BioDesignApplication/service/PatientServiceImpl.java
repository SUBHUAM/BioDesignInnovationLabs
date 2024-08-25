package com.BioLabs.BioDesignApplication.service;

import com.BioLabs.BioDesignApplication.entity.Patient;
import com.BioLabs.BioDesignApplication.exception.ResourceNotFoundException;
import com.BioLabs.BioDesignApplication.respository.PatientRepository;
import com.BioLabs.BioDesignApplication.utils.EncryptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PatientServiceImpl implements PatientService{

    PatientRepository patientRepository;
    @Autowired
    public PatientServiceImpl (PatientRepository patientRepository){
        this.patientRepository=patientRepository;
    }

    @Override
    public Patient addPatient(Patient patient) {
        try {
            log.info("Adding new patient: {}", patient.getFirstName() + " " + patient.getLastName());
            patient.setFirstName(patient.getFirstName());
            patient.setLastName(patient.getLastName());
            patient.setDateOfBirth(patient.getDateOfBirth());
            patient.setEmail(EncryptionUtils.encrypt(patient.getEmail()));

        } catch (Exception e) {
            log.error("Error encrypting patient email: {}", e.getMessage());
            throw new RuntimeException("Error encrypting patient data", e);
        }
        Patient savedPatient = patientRepository.save(patient);
        log.info("Patient added successfully with ID: {}", savedPatient.getId());

        return savedPatient;
    }


    @Cacheable("patients")
    @Override
    public Page<Patient> getAllPatients(Pageable pageable, String search) {

        Page<Patient> patients;
        log.info("Retrieving all patients, search term: {}", search);

        if (search == null || search.isEmpty()) {
            patients= patientRepository.findAll(pageable);
        }
        else{
            patients=patientRepository.findByFirstNameContainingOrLastNameContaining(search, search, pageable);
        }

        patients.forEach(patient -> {
            try {
                patient.setEmail(EncryptionUtils.decrypt(patient.getEmail()));
            } catch (Exception e) {
                log.error("Error decrypting patient email for patient ID: {}", patient.getId());
                throw new RuntimeException("Error decrypting patient data", e);
            }
        });
        log.info("Retrieved {} patients", patients.getTotalElements());
        return patients;
    }

    @Override
    public Optional<Patient> getPatientById(Long id) {
        log.info("Retrieving patient with ID: {}", id);

        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isEmpty()) {
            log.warn("Patient with ID: {} not found", id);
            return patient;
        }

        try {
            patient.get().setEmail(EncryptionUtils.decrypt(patient.get().getEmail()));
            log.info("Successfully decrypted email for patient ID: {}", id);
        } catch (Exception e) {
            log.error("Error decrypting email for patient ID: {}", id);
            throw new RuntimeException("Error decrypting patient data", e);
        }

        return patient;
    }

    @Override
    public Patient updatPatient(Long id, Patient updatedPatient) {
        log.info("Updating patient with ID: {}", id);

        return patientRepository.findById(id).map(patient -> {
            try {
                patient.setFirstName(updatedPatient.getFirstName());
                patient.setLastName(updatedPatient.getLastName());
                patient.setDateOfBirth(updatedPatient.getDateOfBirth());
                patient.setEmail(EncryptionUtils.encrypt(updatedPatient.getEmail()));
                log.info("Patient with ID: {} successfully updated", id);
                return patientRepository.save(patient);
            } catch (Exception e) {
                log.error("Error encrypting email for patient with ID: {}", id);
                throw new RuntimeException("Error encrypting patient data", e);
            }
        }).orElseThrow(() -> {
            log.error("Patient with ID: {} not found", id);
            return new ResourceNotFoundException("Patient not found with id " + id);
        });
    }

    @Override
    public void deletePatient(Long id) {
        log.info("Deleting patient with ID: {}", id);
        patientRepository.deleteById(id);
        log.info("Patient with ID: {} successfully deleted", id);
    }
}
