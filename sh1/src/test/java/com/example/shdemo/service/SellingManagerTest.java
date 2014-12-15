package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Train;
import com.example.shdemo.domain.Station;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class SellingManagerTest {

	@Autowired
	SellingManager sellingManager;

	private final String NAME_1 = "Bolek";
	private final String PIN_1 = "1234";

	private final String NAME_2 = "Lolek";
	private final String PIN_2 = "4321";

	private final String MODEL_1 = "126p";
	private final String MAKE_1 = "Fiat";

	private final String MODEL_2 = "Mondeo";
	private final String MAKE_2 = "Ford";

	@Test
	public void addClientCheck() {

		List<Station> retrievedClients = sellingManager.getAllClients();

		// If there is a client with PIN_1 delete it
		for (Station client : retrievedClients) {
			if (client.getCity().equals(PIN_1)) {
				sellingManager.deleteClient(client);
			}
		}

		Station person = new Station();
		person.setName(NAME_1);
		person.setCity(PIN_1);
		// ... other properties here

		// Pin is Unique
		sellingManager.addClient(person);

		Station retrievedClient = sellingManager.findClientByPin(PIN_1);

		assertEquals(NAME_1, retrievedClient.getName());
		assertEquals(PIN_1, retrievedClient.getCity());
		// ... check other properties here
	}

	@Test
	public void addCarCheck() {

		Train car = new Train();
		car.setName(MAKE_1);
		car.setYear(MODEL_1);
		// ... other properties here

		Long carId = sellingManager.addNewCar(car);

		Train retrievedCar = sellingManager.findCarById(carId);
		assertEquals(MAKE_1, retrievedCar.getName());
		assertEquals(MODEL_1, retrievedCar.getYear());
		// ... check other properties here

	}

	@Test
	public void sellCarCheck() {

		Station person = new Station();
		person.setName(NAME_2);
		person.setCity(PIN_2);

		sellingManager.addClient(person);

		Station retrievedPerson = sellingManager.findClientByPin(PIN_2);

		Train car = new Train();
		car.setName(MAKE_2);
		car.setYear(MODEL_2);

		Long carId = sellingManager.addNewCar(car);

		sellingManager.sellCar(retrievedPerson.getId(), carId);

		List<Train> ownedCars = sellingManager.getOwnedCars(retrievedPerson);

		assertEquals(1, ownedCars.size());
		assertEquals(MAKE_2, ownedCars.get(0).getName());
		assertEquals(MODEL_2, ownedCars.get(0).getYear());
	}

	// @Test -
	public void disposeCarCheck() {
		// Do it yourself
	}

}
