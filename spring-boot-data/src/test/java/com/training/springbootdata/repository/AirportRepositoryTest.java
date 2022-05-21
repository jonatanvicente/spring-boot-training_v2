package com.training.springbootdata.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.springbootdata.config.DBTestConfiguration;
import com.training.springbootdata.entity.Airport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { DBTestConfiguration.class })
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
//CAUTION: For uses with real database
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class AirportRepositoryTest {
	@Autowired
	private IAirportRepository airportRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private DataSource dataSource;

	@Test
	@DisplayName("Test Find By Id")
	@Order(1)
	void findByIdTest() {
		String iata = "00M";
		Optional<Airport> airport = airportRepository.findById(iata);
		assertThat(airport.get().getIata()).isNotEmpty();
		assertEquals(iata, airport.get().getIata());
	}

	@Test
	@DisplayName("Test exists by ID")
	@Order(2)
	void existsById() {
		String iata = "05U";
		assertTrue(airportRepository.existsById(iata));
	}

	//TODO - Testing methods delete / save

}
