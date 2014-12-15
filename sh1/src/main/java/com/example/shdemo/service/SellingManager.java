package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Train;
import com.example.shdemo.domain.Station;

public interface SellingManager {
	
	void addClient(Station person);
	List<Station> getAllClients();
	void deleteClient(Station person);
	Station findClientByPin(String pin);
	
	Long addNewCar(Train car);
	List<Train> getAvailableCars();
	void disposeCar(Station person, Train car);
	Train findCarById(Long id);

	List<Train> getOwnedCars(Station person);
	void sellCar(Long personId, Long carId);

}
