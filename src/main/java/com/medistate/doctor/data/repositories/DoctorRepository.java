package com.medistate.doctor.data.repositories;

import com.medistate.doctor.data.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {



    Optional<Doctor> findDoctorByEmail(String email);

    Optional<Doctor> findDoctorByName(String name);
}
