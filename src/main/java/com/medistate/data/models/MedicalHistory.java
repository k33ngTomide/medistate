package com.medistate.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String sickness;
    private String condition;
    private String diagnoses;
    private String prescription;
    private LocalDateTime dateTime;
    @ManyToOne
    private Patient patient;
}
