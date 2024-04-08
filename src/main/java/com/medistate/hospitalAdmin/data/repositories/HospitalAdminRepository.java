package com.medistate.hospitalAdmin.data.repositories;

import com.medistate.hospitalAdmin.data.models.HospitalAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalAdminRepository  extends JpaRepository<HospitalAdmin, Long> {
    Optional <HospitalAdmin> findByEmail(String hospitalAdminEmail);

}
