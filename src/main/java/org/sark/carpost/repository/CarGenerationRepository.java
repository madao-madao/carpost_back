package org.sark.carpost.repository;

import org.sark.carpost.entity.CarGenerationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarGenerationRepository extends JpaRepository<CarGenerationEntity, Long> {
}
