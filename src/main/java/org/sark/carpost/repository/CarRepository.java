package org.sark.carpost.repository;

import org.sark.carpost.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarEntity, Long> {
    /*@Query("SELECT CarEntity from CarEntity ce where ce.vin = :vin")
    Optional<CarEntity> findByVin(String vin);*/
}
