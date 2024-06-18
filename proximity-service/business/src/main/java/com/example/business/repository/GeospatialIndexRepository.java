package com.example.business.repository;


import com.example.business.entity.GeospatialIndex;
import com.example.business.entity.GeospatialIndexId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeospatialIndexRepository extends JpaRepository<GeospatialIndex, GeospatialIndexId> {

}
