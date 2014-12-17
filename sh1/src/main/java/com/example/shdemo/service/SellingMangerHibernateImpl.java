package com.example.shdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Train;
import com.example.shdemo.domain.Station;

@Component
@Transactional
public class SellingMangerHibernateImpl implements SellingManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Long addStation(Station station) {
		station.setId(null);
		sessionFactory.getCurrentSession().persist(station);
		return (Long) sessionFactory.getCurrentSession().save(station);
	}
	
	@Override
	public void deleteStation(Station station) {
		station = (Station) sessionFactory.getCurrentSession().get(Station.class,
				station.getId());
		
		// lazy loading here
		for (Train train : station.getTrains()) {
			train.setSold(false);
			sessionFactory.getCurrentSession().update(train);
		}
		sessionFactory.getCurrentSession().delete(station);
	}

	@Override
	public void updateStationById(Long id, Station newStation) {
		Station oldStation = (Station) sessionFactory.getCurrentSession().get(Station.class, id);
		oldStation.setName(newStation.getName());
		oldStation.setCity(newStation.getCity());
		sessionFactory.getCurrentSession().update(oldStation);
	}
	
	@Override
	public void deleteStationById(Long id) {
		Station station = (Station) sessionFactory.getCurrentSession().get(Station.class, id);
		sessionFactory.getCurrentSession().delete(station);
	}
	
	@Override
	public List<Train> getOwnedTrains(Station station) {
		station = (Station) sessionFactory.getCurrentSession().get(Station.class,
				station.getId());
		// lazy loading here - try this code without (shallow) copying
		List<Train> trains = new ArrayList<Train>(station.getTrains());
		return trains;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Station> getAllStations() {
		return sessionFactory.getCurrentSession().getNamedQuery("station.all")
				.list();
	}

	@Override
	public Station findStationByCity(String city) {
		return (Station) sessionFactory.getCurrentSession().getNamedQuery("station.byCity").setString("city", city).uniqueResult();
	}
	
	@Override
	public Station findStationById(Long id) {
		//return (Station) sessionFactory.getCurrentSession().getNamedQuery("station.byCity").setLong("id", id).uniqueResult();
		return (Station) sessionFactory.getCurrentSession().get(Station.class, id);
	}


	@Override
	public Long addNewTrain(Train train) {
		train.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(train);
	}

	@Override
	public void sellTrain(Long stationId, Long trainId) {
		Station station = (Station) sessionFactory.getCurrentSession().get(
				Station.class, stationId);
		Train train = (Train) sessionFactory.getCurrentSession()
				.get(Train.class, trainId);
		train.setSold(true);
		station.getTrains().add(train);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Train> getAvailableTrains() {
		return sessionFactory.getCurrentSession().getNamedQuery("train.unsold")
				.list();
	}
	@Override
	public void disposeTrain(Station station, Train train) {

		station = (Station) sessionFactory.getCurrentSession().get(Station.class,
				station.getId());
		train = (Train) sessionFactory.getCurrentSession().get(Train.class,
				train.getId());

		Train toRemove = null;
		// lazy loading here (person.getCars)
		for (Train aTrain : station.getTrains())
			if (aTrain.getId().compareTo(train.getId()) == 0) {
				toRemove = aTrain;
				break;
			}

		if (toRemove != null)
			station.getTrains().remove(toRemove);

		train.setSold(false);
	}

	@Override
	public Train findTrainById(Long id) {
		return (Train) sessionFactory.getCurrentSession().get(Train.class, id);
	}

	@Override
	public void deleteTrainById(Long id) {
		Train train = (Train) sessionFactory.getCurrentSession().get(Train.class, id);
		sessionFactory.getCurrentSession().delete(train);
	}
	
	@Override
	public void updateTrainById(Long id, Train newTrain) {
		Train oldTrain = (Train) sessionFactory.getCurrentSession().get(Train.class, id);
		oldTrain.setName(newTrain.getName());
		oldTrain.setYear(newTrain.getYear());
		sessionFactory.getCurrentSession().update(oldTrain);
	}
	
}
