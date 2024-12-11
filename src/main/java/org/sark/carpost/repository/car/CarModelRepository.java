package org.sark.carpost.repository.car;

import org.sark.carpost.entity.car.CarModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarModelRepository extends JpaRepository<CarModelEntity, Long> {
}
