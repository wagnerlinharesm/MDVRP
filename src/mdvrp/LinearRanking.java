package mdvrp;

import java.util.ArrayList;


import mdvrp.containers.Container_Deposits;

public class LinearRanking {

	public static void linearRanking(ArrayList<Container_Deposits> solucao) {
	//	System.out.println("tamanho antes " + solucao.size());
		for (int i = 0; i < solucao.size(); i++) {
			double a = solucao.get(i).fullcoast;
			
			for (int j = i+1; j < solucao.size(); j++) {
				double b = solucao.get(j).fullcoast;
				if (a == b) {
					solucao.remove(j);
					j--;
				}
			}
		}
		//System.out.println("tamanho depois " + solucao.size());
	}
}
