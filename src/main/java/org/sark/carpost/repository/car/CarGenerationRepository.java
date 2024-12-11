package org.sark.carpost.repository.car;

import org.sark.carpost.entity.car.CarGenerationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarGenerationRepository extends JpaRepository<CarGenerationEntity, Long> {
}
