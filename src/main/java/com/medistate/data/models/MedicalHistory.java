package com.medistate.data.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Setter
@Getter
public class MedicalHistory {

    @Id
    private Long id;
    private String sickness;
    private String condition;
    private String diagnoses;
    private String prescription;

    private LocalDateTime dateTime;
}
