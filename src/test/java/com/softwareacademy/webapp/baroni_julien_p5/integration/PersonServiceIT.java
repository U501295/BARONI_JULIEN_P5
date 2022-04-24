package com.softwareacademy.webapp.baroni_julien_p5.integration;

import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import com.softwareacademy.webapp.baroni_julien_p5.model.JsonSerializer.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.service.FireStationService;
import com.softwareacademy.webapp.baroni_julien_p5.service.PersonService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author : JULIEN BARONI
 *
 * <p>
 * Tests d'intégrations permettant de dérouler les services CRUDS les uns à la suites des autres
 * dans une logique de test fonctionnel. Afin de valider la non permanence des modifications en base.
 * On compare nos modifications avec une version vierge de la base de donnée afin de s'assurer de leur validité.
 * <p>
 */


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class PersonServiceIT {

    @Autowired
    PersonService personService;

    static List<Person> pJsonData = InputData.getJsonData().getPersonsData();
    static List<Person> pRefCompare = InputData.getJsonData().getPersonsData();

    @Test
    void parcoursUtilisateurPassant() {
        List<Person> result = personService.removePerson(pJsonData,
                "John",
                "Boyd"
        );
        Assertions.assertThat(result.size()).isEqualTo(pRefCompare.size() - 1);
        result = result = personService.addPerson(pJsonData,
                "Test",
                "Integration",
                "Toc Street",
                "Test city",
                2533,
                "0525654",
                "test@test.fr"
        );
        Assertions.assertThat(result.size()).isEqualTo(pRefCompare.size());
        result = personService.modifyPerson(pJsonData,
                "Test",
                "Integration",
                "Toc Street",
                "Test city",
                1000,
                "0525654",
                "test@test.fr");
        Assertions.assertThat(result.get(22).getZip()).isEqualTo(1000);
    }

}