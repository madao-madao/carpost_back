package org.sark.carpost.repository;

import org.sark.carpost.entity.CarModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarModelRepository extends JpaRepository<CarModelEntity, Long> {
}
