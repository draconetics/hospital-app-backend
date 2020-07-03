package com.demo.hospital.repository;

import com.demo.hospital.model.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
}
