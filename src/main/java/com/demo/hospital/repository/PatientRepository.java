package com.demo.hospital.repository;

import com.demo.hospital.model.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
}
