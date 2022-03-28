package com.softwareacademy.webapp.baroni_julien_p5.repository;

import com.softwareacademy.webapp.baroni_julien_p5.model.Persons;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FireStationRepository extends CrudRepository<Persons, Long> {
}
