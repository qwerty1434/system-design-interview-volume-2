package com.example.business.repository;

import com.example.business.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusinessRepository extends JpaRepository<Business, Long> {

    @Query("SELECT b.businessId, b.latitude, b.longitude " +
            "FROM Business b " +
            "WHERE (b.latitude BETWEEN :my_lat - :range_lat AND :my_lat + :range_lat)" +
                "AND (b.longitude BETWEEN :my_long - :range_long AND :my_long + :range_long)"
    )
    List<Business> twoDimensionSearch(@Param("my_lat") float my_lat,
                                             @Param("my_long") float my_long,
                                             @Param("range_lat") double range_lat,
                                             @Param("range_long") double range_long);


}
