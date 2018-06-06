package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Event implements Comparable<Event> {
	
	private Flow flow;
	private LocalDate date;

	
	public Event(Flow flow) {
		this.flow = flow;
		this.date = flow.getDay();
	}

	public int compareTo(Event o) {
		return date.compareTo(o.date);
	}

	public Flow getFlow() {
		return flow;
	}

	@Override
	public String toString() {
		return "Event [flow=" + flow + ", date=" + date + "]";
	}
	
	
	

}
