package org.sark.carpost.repository.car;

import org.sark.carpost.entity.car.CarBrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarBrandRepository extends JpaRepository<CarBrandEntity, Long> {
    Optional<CarBrandEntity> findByName(String name);
}
