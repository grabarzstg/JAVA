package com.example.shdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "train.unsold", query = "Select c from Train c where c.sold = false")
})
public class Train {

	private Long id;
	private String name;
	private String year;
	private Boolean sold = false;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String make) {
		this.name = make;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String model) {
		this.year = model;
	}

	public Boolean getSold() {
		return sold;
	}

	public void setSold(Boolean sold) {
		this.sold = sold;
	}
}
