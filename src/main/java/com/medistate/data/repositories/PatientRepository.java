package com.medistate.data.repositories;

import com.medistate.data.models.Hospital;
import com.medistate.data.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {


}
