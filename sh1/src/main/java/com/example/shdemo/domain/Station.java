package com.example.shdemo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "station.all", query = "Select p from Station p"),
	@NamedQuery(name = "station.byCity", query = "Select p from Station p where p.city = :city")
})
public class Station {

	private Long id;

	private String Name = "unknown";
	private String city = "";
	private Date buildDate = new Date();

	private List<Train> cars = new ArrayList<Train>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String firstName) {
		this.Name = firstName;
	}

	@Column(unique = true, nullable = false)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	@Temporal(TemporalType.DATE)
	public Date getBuildDate() {
		return buildDate;
	}
	public void setBuildDate(Date buildDate) {
		this.buildDate = buildDate;
	}

	// Be careful here, both with lazy and eager fetch type
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Train> getCars() {
		return cars;
	}
	public void setCars(List<Train> cars) {
		this.cars = cars;
	}
}
