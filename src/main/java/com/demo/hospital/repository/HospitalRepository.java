package com.demo.hospital.repository;

import com.demo.hospital.model.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HospitalRepository  extends JpaRepository<HospitalEntity, Long>, JpaSpecificationExecutor<HospitalEntity> {
}
