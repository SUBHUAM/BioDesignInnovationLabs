package com.BioLabs.BioDesignApplication.respository;

import com.BioLabs.BioDesignApplication.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    Page<Patient> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName, Pageable pageable);

}
