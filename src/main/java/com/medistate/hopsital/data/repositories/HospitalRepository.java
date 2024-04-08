package com.medistate.hopsital.data.repositories;

import com.medistate.doctor.data.models.Doctor;
import com.medistate.hopsital.data.model.Hospital;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {


    Optional<Hospital> findHospitalByHospitalEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Hospital h SET h.doctorList = :doctorList WHERE h.hospitalEmail = :hospitalEmail")
    void updateHospitalDoctorList(@Param("doctorList") List<Doctor> doctorList,
                                  @Param("hospitalEmail") String hospitalEmail);

    Optional<Hospital> findHospitalByHospitalName(String hospitalName);

    Hospital findByHospitalName(String hospitalName);
}
