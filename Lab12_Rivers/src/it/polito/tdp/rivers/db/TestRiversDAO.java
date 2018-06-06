package it.polito.tdp.rivers.db;

public class TestRiversDAO {

	public static void main(String[] args) {
		RiversDAO dao = new RiversDAO();
		//System.out.println(dao.getAllRivers());
		//System.out.println(dao.getRiver(2));
		System.out.println(dao.getAllFlowsFromRiver(dao.getRiver(1)));
		
	}

}
