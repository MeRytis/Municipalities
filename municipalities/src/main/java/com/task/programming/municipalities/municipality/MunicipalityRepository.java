package com.task.programming.municipalities.municipality;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, Long> {

    Optional<Municipality> findMunicipalityByNameAndStartDateBeforeAndEndDateAfter(String name, LocalDate startDate, LocalDate endDate);

}
