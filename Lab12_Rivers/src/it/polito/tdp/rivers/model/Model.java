package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	private RiversDAO riversdao;
	private Simulator sim;

	public Model() {
		this.riversdao = new RiversDAO();
	}
	
	public LocalDate getFirstMesure(River r) {
		List<Flow> flows = riversdao.getAllFlowsFromRiver(r);
		LocalDate date = flows.get(0).getDay();
		for(Flow f: flows) {
			if(f.getDay().isBefore(date))
				date = f.getDay();
		}
		return date;
	}
	
	public LocalDate getLastMesure(River r) {
		List<Flow> flows = riversdao.getAllFlowsFromRiver(r);
		LocalDate date = flows.get(0).getDay();
		for(Flow f: flows) {
			if(f.getDay().isAfter(date))
				date = f.getDay();
		}
		return date;
	}
	
	public int getTotMesures(River r) {
		return riversdao.getAllFlowsFromRiver(r).size();
	}
	
	public double getFlowAverage(River r) {
		Double sum = 0.0;
		List<Flow> l = riversdao.getAllFlowsFromRiver(r);
		for(Flow f: l) {
			sum += f.getFlow();
		}
		return sum/l.size();
	}
	
	public List<River> getAllRivers() {
		return riversdao.getAllRivers();
	}
	
	public void simula(River r, double k, double flussoMedio) {
		this.sim = new Simulator();
		this.sim.init(r, k, flussoMedio);
		this.sim.run();
	}
	
	public int getGiorniNo() {
		return sim.getGiorniNo();
	}
	
	public double getQuantitaAcquaMedia() {
		return sim.getQuantitaAcquaMedia();
	}
	
	

}
