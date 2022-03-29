package com.softwareacademy.webapp.baroni_julien_p5.repository;

import com.softwareacademy.webapp.baroni_julien_p5.model.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FireStationRepository extends CrudRepository<FireStation, Long> {
}
