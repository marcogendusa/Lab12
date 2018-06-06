package it.polito.tdp.rivers.model;

import it.polito.tdp.rivers.db.RiversDAO;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		RiversDAO rdao = new RiversDAO();
		River r = rdao.getRiver(1);
		
		System.out.println("Prima misura: "+model.getFirstMesure(r));
		System.out.println("Ultima misura: "+model.getLastMesure(r));
		System.out.println("Numero misurazioni: "+model.getTotMesures(r));
		System.out.println("Flusso medio: "+model.getFlowAverage(r));
		
		model.simula(r, 0.01, model.getFlowAverage(r));
		System.out.println(model.getGiorniNo());
		System.out.println(model.getQuantitaAcquaMedia());


	}

}
