package org.sark.carpost.repository;

import org.sark.carpost.entity.CarBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarBrandRepository extends JpaRepository<CarBrandEntity, Long> {
}
