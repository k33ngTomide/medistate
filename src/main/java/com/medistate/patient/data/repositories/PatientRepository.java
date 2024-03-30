package com.medistate.patient.data.repositories;

import com.medistate.patient.data.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {


}
