package com.demo.hospital.repository;

import com.demo.hospital.model.SpecialityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialityRepository extends JpaRepository<SpecialityEntity, Long> {
}
