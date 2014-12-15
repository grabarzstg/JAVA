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
	public void addClient(Station person) {
		person.setId(null);
		sessionFactory.getCurrentSession().persist(person);
	}
	
	@Override
	public void deleteClient(Station person) {
		person = (Station) sessionFactory.getCurrentSession().get(Station.class,
				person.getId());
		
		// lazy loading here
		for (Train car : person.getCars()) {
			car.setSold(false);
			sessionFactory.getCurrentSession().update(car);
		}
		sessionFactory.getCurrentSession().delete(person);
	}

	@Override
	public List<Train> getOwnedCars(Station person) {
		person = (Station) sessionFactory.getCurrentSession().get(Station.class,
				person.getId());
		// lazy loading here - try this code without (shallow) copying
		List<Train> cars = new ArrayList<Train>(person.getCars());
		return cars;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Station> getAllClients() {
		return sessionFactory.getCurrentSession().getNamedQuery("station.all")
				.list();
	}

	@Override
	public Station findClientByPin(String pin) {
		return (Station) sessionFactory.getCurrentSession().getNamedQuery("station.byCity").setString("city", pin).uniqueResult();
	}


	@Override
	public Long addNewCar(Train car) {
		car.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(car);
	}

	@Override
	public void sellCar(Long personId, Long carId) {
		Station person = (Station) sessionFactory.getCurrentSession().get(
				Station.class, personId);
		Train car = (Train) sessionFactory.getCurrentSession()
				.get(Train.class, carId);
		car.setSold(true);
		person.getCars().add(car);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Train> getAvailableCars() {
		return sessionFactory.getCurrentSession().getNamedQuery("train.unsold")
				.list();
	}
	@Override
	public void disposeCar(Station person, Train car) {

		person = (Station) sessionFactory.getCurrentSession().get(Station.class,
				person.getId());
		car = (Train) sessionFactory.getCurrentSession().get(Train.class,
				car.getId());

		Train toRemove = null;
		// lazy loading here (person.getCars)
		for (Train aCar : person.getCars())
			if (aCar.getId().compareTo(car.getId()) == 0) {
				toRemove = aCar;
				break;
			}

		if (toRemove != null)
			person.getCars().remove(toRemove);

		car.setSold(false);
	}

	@Override
	public Train findCarById(Long id) {
		return (Train) sessionFactory.getCurrentSession().get(Train.class, id);
	}

}
