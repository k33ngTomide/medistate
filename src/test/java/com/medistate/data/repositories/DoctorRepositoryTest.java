package com.medistate.data.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void findDoctorByEmail(){
        assertTrue(doctorRepository.findDoctorByEmail("mhaville1@amazonaws.com").isPresent());
    }

}