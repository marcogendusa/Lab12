package it.polito.tdp.rivers.model;

import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.rivers.db.RiversDAO;

public class Simulator {
	
	// Tipi di eventi / coda degli eventi
	PriorityQueue<Event> queue;
	RiversDAO riversdao;
	
	
	// Modello del mondo
	private double capienzaTotale;
	private double quantitaAcqua;
	List<Flow> flow;
	
	// Parametri di simulazione
	private double flussoOutMin;
	
	// Valori in output
	private int giorniNo;
	private double quantitaAcquaMedia;
	
	public void init(River r, double k, double flussoMedio) {
		riversdao = new RiversDAO();
		flow = riversdao.getAllFlowsFromRiver(r);
		this.capienzaTotale = k*flussoMedio*86400*30;
		this.quantitaAcqua = capienzaTotale/2;
		this.flussoOutMin = flussoMedio*86400*0.8;
		this.giorniNo = 0;
		
		this.queue = new PriorityQueue<Event>();
		for(Flow f: flow) {
			queue.add(new Event(f));
		}
		
	}
	
	public void run() {
		Event e ;
		while((e=queue.poll())!=null) {
			// aggiungo acqua che entra
			double flussoIn = e.getFlow().getFlow()*86400;
			quantitaAcqua += flussoIn;
			// controllo che non tracimi
			double flussoOut = 0.0;
			if(quantitaAcqua-capienzaTotale>0) {
				flussoOut = quantitaAcqua-capienzaTotale; // esce ciò che tracima
				quantitaAcqua = capienzaTotale;
			}
			// controllo di soddisfare la domanda giornaliera
			double prob = Math.random();
			double domanda = 0.0;
			if(prob<0.05) { 
				domanda = 10*flussoOutMin;
			} else {
				domanda = flussoOutMin;
			}
			
			if(domanda-flussoOut>0) { // tolgo acqua
				quantitaAcqua -= domanda-flussoOut;
			}
			
			// controllo se soddisfo la domanda
			if(quantitaAcqua<0) {
				giorniNo++;
				quantitaAcqua = 0;
			}
			quantitaAcquaMedia += quantitaAcqua;
		}
		
		quantitaAcquaMedia = quantitaAcquaMedia/flow.size();

	}
	
	public int getGiorniNo() {
		return giorniNo;
	}
	
	public double getQuantitaAcquaMedia() {
		return quantitaAcquaMedia;
	}
	

}
