package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Train;
import com.example.shdemo.domain.Station;

public interface SellingManager {
	
	Long addStation(Station station);
	List<Station> getAllStations();
	void deleteStation(Station station);
	Station findStationByCity(String city);
	Station findStationById(Long id);
	
	Long addNewTrain(Train car);
	List<Train> getAvailableTrains();
	void disposeTrain(Station station, Train train);
	Train findTrainById(Long id);

	List<Train> getOwnedTrains(Station station);
	void sellTrain(Long stationId, Long trainId);
	void deleteTrainById(Long id);
	void deleteStationById(Long id);
	void updateStationById(Long id, Station newStation);
	void updateTrainById(Long id, Train newTrain);

}
