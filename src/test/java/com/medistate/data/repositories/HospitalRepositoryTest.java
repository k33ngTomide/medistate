package com.medistate.data.repositories;

import com.medistate.hopsital.data.repositories.HospitalRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalRepositoryTest {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void testHospitalRepository(){
        assertTrue(hospitalRepository.findHospitalByHospitalEmail("gpietasch0@linkedin.com").isPresent());
    }

}