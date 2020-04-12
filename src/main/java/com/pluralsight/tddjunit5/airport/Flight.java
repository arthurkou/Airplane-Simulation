package com.pluralsight.tddjunit5.airport;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Flight {

	private String id;
	Set<Passenger> passengersSet = new HashSet<>(); // set � uma lista que n�o tem valores repetido
//	List<Passenger> passengersList = new ArrayList<Passenger>();

	public Flight(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	
	public Set<Passenger> getPassengersSet() {
		return Collections.unmodifiableSet(passengersSet);
	}

	public abstract boolean addPassenger(Passenger passenger);

	public abstract boolean removePassenger(Passenger passenger);

}
