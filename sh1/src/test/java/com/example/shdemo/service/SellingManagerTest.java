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

	private final String S_NAME_1 = "Dworzec Glowny";
	private final String CITY_1 = "Gdańsk";

	private final String S_NAME_2 = "Stacja PKP";
	private final String CITY_2 = "Starogard Gd.";

	private final String T_NAME_1 = "126p";
	private final String YEAR_1 = "1999";

	private final String T_NAME_2 = "Pendolino";
	private final String YEAR_2 = "2014";
	
	
	@Test
	public void createTrainCheck() {

		Train train = new Train();
		train.setName(YEAR_1);
		train.setYear(T_NAME_1);
		// ... other properties here

		Long trainId = sellingManager.addNewTrain(train);

		Train retrievedTrain = sellingManager.findTrainById(trainId);
		assertEquals(YEAR_1, retrievedTrain.getName());
		assertEquals(T_NAME_1, retrievedTrain.getYear());
		// ... check other properties here

	}

	@Test
	public void sellTrainCheck() {

		Station station = new Station();
		station.setName(S_NAME_2);
		station.setCity(CITY_2);

		sellingManager.addStation(station);

		Station retrievedStation = sellingManager.findStationByCity(CITY_2);

		Train train = new Train();
		train.setName(YEAR_2);
		train.setYear(T_NAME_2);

		Long trainId = sellingManager.addNewTrain(train);

		sellingManager.sellTrain(retrievedStation.getId(), trainId);

		List<Train> ownedTrains = sellingManager.getOwnedTrains(retrievedStation);

		assertEquals(1, ownedTrains.size());
		assertEquals(YEAR_2, ownedTrains.get(0).getName());
		assertEquals(T_NAME_2, ownedTrains.get(0).getYear());
	}

	@Test
	public void disposeTrainCheck() {
		
		Station station = new Station();
		station.setName(S_NAME_2);
		station.setCity(CITY_2);

		sellingManager.addStation(station);

		Station retrievedStation = sellingManager.findStationByCity(CITY_2);

		Train train = new Train();
		train.setName(YEAR_2);
		train.setYear(T_NAME_2);
		
		sellingManager.addNewTrain(train);
		sellingManager.disposeTrain(retrievedStation, train);
		
		List<Train> ownedTrains = sellingManager.getOwnedTrains(retrievedStation);
		assertEquals(0, ownedTrains.size());
	}
	
	@Test
	public void readAllTrainCheck() {
		
		Train train = new Train();
		train.setName(YEAR_1);
		train.setYear(T_NAME_1);
		
		Train train2 = new Train();
		train2.setName(YEAR_2);
		train2.setYear(T_NAME_2);
		
		sellingManager.addNewTrain(train);
		sellingManager.addNewTrain(train2);
		
		List<Train> allTrains = sellingManager.getAvailableTrains();
		assertEquals(2, allTrains.size());
		
		assertEquals(YEAR_1, allTrains.get(0).getName());
		assertEquals(T_NAME_1, allTrains.get(0).getYear());
		assertEquals(YEAR_2, allTrains.get(1).getName());
		assertEquals(T_NAME_2, allTrains.get(1).getYear());
	}

	@Test
	public void readTrainByIdCheck(){
		
		Train train = new Train();
		train.setName(YEAR_1);
		train.setYear(T_NAME_1);
		
		Long trainId = sellingManager.addNewTrain(train);
		Train retrievedTrain = sellingManager.findTrainById(trainId);
		assertEquals(YEAR_1, retrievedTrain.getName());
		assertEquals(T_NAME_1, retrievedTrain.getYear());
	}

	@Test
	public void deleteTrainByIdCheck(){
				
		Train train = new Train();
		train.setName(YEAR_1);
		train.setYear(T_NAME_1);
		Long trainId = sellingManager.addNewTrain(train);
		
		Train train2 = new Train();
		train2.setName(YEAR_2);
		train2.setYear(T_NAME_2);
		sellingManager.addNewTrain(train2);
		
		sellingManager.deleteTrainById(trainId);
		
		List<Train> allTrains = sellingManager.getAvailableTrains();
		assertEquals(1, allTrains.size());
		assertEquals(YEAR_2, allTrains.get(0).getName());
		assertEquals(T_NAME_2, allTrains.get(0).getYear());
		
		Train retrievedTrain = sellingManager.findTrainById(trainId);
		assertEquals(null, retrievedTrain);
	}
	
	@Test
	public void updateTrainByIdCheck(){
		Train oldtrain = new Train();
		oldtrain.setName(YEAR_1);
		oldtrain.setYear(T_NAME_1);
		Long oldtrainId = sellingManager.addNewTrain(oldtrain);
		
		Train newtrain = new Train();
		newtrain.setName(YEAR_2);
		newtrain.setYear(T_NAME_2);
		
		Train anotherTrain = new Train();
		anotherTrain.setName(YEAR_2);
		anotherTrain.setYear(T_NAME_1);
		Long anotherTrainId = sellingManager.addNewTrain(anotherTrain);
		
		sellingManager.updateTrainById(oldtrainId, newtrain);
		Train retrievedTrain = sellingManager.findTrainById(oldtrainId);
		assertEquals(newtrain.getName(), retrievedTrain.getName());
		assertEquals(newtrain.getYear(), retrievedTrain.getYear());
		
		Train anotherRetrievedTrain = sellingManager.findTrainById(anotherTrainId);
		assertEquals(anotherTrain.getName(), anotherRetrievedTrain.getName());
		assertEquals(anotherTrain.getYear(), anotherRetrievedTrain.getYear());
		
		
	}

	@Test
	public void createStationCheck() {

		List<Station> retrievedStations = sellingManager.getAllStations();

		// If there is a station with CITY_1 delete it
		for (Station station : retrievedStations) {
			if (station.getCity().equals(CITY_1)) {
				sellingManager.deleteStation(station);
			}
		}

		Station station = new Station();
		station.setName(S_NAME_1);
		station.setCity(CITY_1);
		// ... other properties here

		// City is Unique
		sellingManager.addStation(station);

		Station retrievedStation = sellingManager.findStationByCity(CITY_1);

		assertEquals(S_NAME_1, retrievedStation.getName());
		assertEquals(CITY_1, retrievedStation.getCity());
		// ... check other properties here
	}
	
	@Test
	public void deleteStationCheck() {
		
		List<Station> retrievedStations = sellingManager.getAllStations();
		int initData = retrievedStations.size();
		// If there is a station with CITY_1 or CITY_2 delete it
		for (Station station : retrievedStations) {
			if (station.getCity().equals(CITY_1)) {
				sellingManager.deleteStation(station);
			}
			if (station.getCity().equals(CITY_2)) {
				sellingManager.deleteStation(station);
			}
		}
		
		Station station = new Station();
		station.setName(S_NAME_1);
		station.setCity(CITY_1);
		
		Station station2 = new Station();
		station2.setName(S_NAME_2);
		station2.setCity(CITY_2);
		
		sellingManager.addStation(station);
		sellingManager.addStation(station2);
		
		sellingManager.deleteStation(station);
		
		List<Station> allStations = sellingManager.getAllStations();
		Station deletedStation = sellingManager.findStationByCity(CITY_1);
		assertEquals(initData+1, allStations.size());
		assertEquals(null, deletedStation);
		assertEquals(S_NAME_2, allStations.get(initData).getName());
		assertEquals(CITY_2, allStations.get(initData).getCity());
	}
	
	@Test
	public void readAllStationCheck(){
		
		List<Station> retrievedStations = sellingManager.getAllStations();
		int initData = retrievedStations.size();
		// If there is a station with CITY_1 or CITY_2 delete it
		for (Station station : retrievedStations) {
			if (station.getCity().equals(CITY_1)) {
				sellingManager.deleteStation(station);
			}
			if (station.getCity().equals(CITY_2)) {
				sellingManager.deleteStation(station);
			}
		}
		
		Station station = new Station();
		station.setName(S_NAME_1);
		station.setCity(CITY_1);
		
		Station station2 = new Station();
		station2.setName(S_NAME_2);
		station2.setCity(CITY_2);
		
		sellingManager.addStation(station);
		sellingManager.addStation(station2);
		
		List<Station> allStations = sellingManager.getAllStations();
		assertEquals(initData+2, allStations.size());
	}
	
	@Test
	public void readStationByIdCheck(){
		List<Station> retrievedStations = sellingManager.getAllStations();
		
		// If there is a station with CITY_1 delete it
		for (Station station : retrievedStations) {
			if (station.getCity().equals(CITY_1)) {
				sellingManager.deleteStation(station);
			}
		}
		
		Station station = new Station();
		station.setName(S_NAME_1);
		station.setCity(CITY_1);
		Long stationId = sellingManager.addStation(station);
		Station retrievedStation = sellingManager.findStationById(stationId);
		assertEquals(S_NAME_1, retrievedStation.getName());	
	}

	@Test
	public void deleteStationByIdCheck(){
		List<Station> retrievedStations = sellingManager.getAllStations();
		int initData = retrievedStations.size();
		// If there is a station with CITY_1 or CITY_2 delete it
		for (Station station : retrievedStations) {
			if (station.getCity().equals(CITY_1)) {
				sellingManager.deleteStation(station);
			}
			if (station.getCity().equals(CITY_2)) {
				sellingManager.deleteStation(station);
			}
		}
		
		Station station = new Station();
		station.setName(S_NAME_1);
		station.setCity(CITY_1);
		
		Station station2 = new Station();
		station2.setName(S_NAME_2);
		station2.setCity(CITY_2);
		
		Long stationId = sellingManager.addStation(station);
		sellingManager.addStation(station2);
		
		sellingManager.deleteStationById(stationId);
		
		List<Station> allStations = sellingManager.getAllStations();
		assertEquals(initData+1, allStations.size());
		assertEquals(S_NAME_2, allStations.get(initData).getName());
		assertEquals(CITY_2, allStations.get(initData).getCity());
		
		Station retrievedStation = sellingManager.findStationById(stationId);
		assertEquals(null, retrievedStation);		
	}

	@Test
	public void updateStationByIdCheck(){
		Station oldstation = new Station();
		oldstation.setName(S_NAME_1);
		oldstation.setCity(CITY_1);
		Long oldstationId = sellingManager.addStation(oldstation);
		
		Station newstation = new Station();
		newstation.setName(S_NAME_2);
		newstation.setCity(CITY_2);
		sellingManager.updateStationById(oldstationId, newstation);
		
		Station anotherStation = new Station();
		anotherStation.setName(S_NAME_2);
		anotherStation.setCity(CITY_2);
		Long anotherstationId = sellingManager.addStation(anotherStation);
		
		Station retrievedStation = sellingManager.findStationById(oldstationId);
		assertEquals(newstation.getName(), retrievedStation.getName());
		assertEquals(newstation.getCity(), retrievedStation.getCity());
		
		Station anotherRetrievedStation = sellingManager.findStationById(anotherstationId);
		assertEquals(anotherStation.getName(), anotherRetrievedStation.getName());
		assertEquals(anotherStation.getCity(), anotherRetrievedStation.getCity());	
	}
}
