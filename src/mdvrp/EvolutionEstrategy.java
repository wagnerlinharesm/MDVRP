package mdvrp;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import mdvrp.containers.Container_Client;
import mdvrp.containers.Container_Deposits;
import mdvrp.problem.Problem;
import mdvrp.util.Client;
import mdvrp.util.Vehicle;

public class EvolutionEstrategy {

	private int mu;
	private int lambda;
	private int ngen;
	private double pbm; // 0.5
	private double pbl; // 0.8

	private double pblBest;

	private double melhor = 0.0;
	private int count = 0;

	private double custo = 0.0;

	private Thread t1 = new Thread();
	private Thread t2 = new Thread();
	private Thread t3 = new Thread();

	public EvolutionEstrategy(int mu, int lambda, int ngen, double pbm, double pbl, double pblBest) {
		super();
		this.mu = mu;
		this.lambda = lambda;
		this.ngen = ngen;
		this.pbm = pbm;
		this.pbl = pbl;
		this.pblBest = pblBest;
	}

	public synchronized double method(Container_Client clients, Container_Deposits deposits)
			throws CloneNotSupportedException, IOException {
		Population.newPopulation.clear();
		Population.populationDeposits.clear();
		count = 0;
		Random rnd = new Random();
		// Gerar população inicial (mu)
		for (int j = 0; j < mu; j++) {

			Distribution.DistributionClients(clients, deposits);

		}
		

		// Calcular função objetivo, avaliar a solução
		for (int i = 0; i < Population.populationDeposits.size(); i++) {

			Distribution.funcaoObjetivo(Population.populationDeposits.get(i));

		}
//		ShowSolution.showNoWrite(Population.populationDeposits.get(0));
		// Verify.clientVerify(Population.populationDeposits.get(0));
		// Verify.numberClients(Population.populationDeposits.get(0));
		// ShowSolution.showNoWrite(Population.populationDeposits.get(0));
		// for (int i = 0; i < mu; i++) {
		// System.out.println(Population.populationDeposits.get(i).Deposit_List.get(0).vehicles.Vehicles_List.get(0).coast);
		// }
		// System.exit(0);

		int tamanho = Population.populationDeposits.size();
		long startTime = System.currentTimeMillis();
		for (int i = 0; i <= ngen; i++) { // enquanto o critério de parada não for atingido
			// System.out.println(Population.populationDeposits.get(0).Deposit_List.get(0).vehicles.Vehicles_List.get(0).coast);
			for (int j = 0; j < tamanho; j++) { // para cada solução pai
				// Criar lambda descendentes

				for (int k = 0; k < lambda / mu; k++) {
					// Mutação
					Population.newPopulation.add(mutation(Population.populationDeposits.get(j), pbm));					
					if (rnd.nextDouble() <= pbl) {

						LocalSearchUniqueRoute
								.localSearch(Population.newPopulation.get(Population.newPopulation.size() - 1));
						EvolutionEstrategy.clearVec(Population.populationDeposits.get(0));

					}
					// Avaliar cada um dos descendentes
//					Distribution.funcaoObjetivo(Population.newPopulation.get(Population.newPopulation.size() - 1));
					if (rnd.nextDouble() <= pblBest) {
						// System.out.println("Best ...");
						this.inicializaThread();
					}

				}

			}

			Population.populationDeposits.addAll(Population.newPopulation); // mu + lambda
			// System.out.println("Rankeando...");

			// LinearRanking.linearRanking(Population.populationDeposits);
			synchronized (Population.populationDeposits) {
				Collections.sort(Population.populationDeposits, new FunctionComparatorObjective()); // Ordeno a
																								// população
			Population.populationDeposits.subList(mu, Population.populationDeposits.size()).clear(); // realizo o
																										// corte
			}
			// Collections.sort(Population.populationDeposits, new
			// FunctionComparatorObjective()); // Ordeno a população

			Population.newPopulation.clear(); // limpo os demais para gerar uma nova

			System.out.println(
					"Geração " + "\t" + i + "\t Melhor custo: \t\t" + Population.populationDeposits.get(0).fullcoast);

			// FileWriter arquivo = new FileWriter( "C:\\Users\\wagne\\OneDrive\\Wagner -
			// UFOP\\Projeto\\Github\\geracoes.txt",true);
			// FileWriter arquivo = new FileWriter( "D:\\OneDrive\\Wagner -
			// UFOP\\TCC\\file.txt",true);
			// PrintWriter pw = new PrintWriter(arquivo );
			// String resultado = ("Gen" + ";" + i + ";" +
			// Population.populationDeposits.get(0).fullcoast);
			//
			// pw.println(resultado);
			// pw.flush();
			// pw.close();
			// long endTime = System.currentTimeMillis();
			// long totalTime = endTime - startTime;

			// if (melhor == Population.populationDeposits.get(0).fullcoast) {
			// count++;

			// if (count == 15) {
			// Problem.geracaoDeParada = i;
			// System.out.println("Estorou contador");
			// ShowSolution.show(Population.populationDeposits.get(0));
			// return Population.populationDeposits.get(0).fullcoast;
			// // ShowSolution.show(Population.populationDeposits.get(0));
			//
			// } else if
			// if (totalTime >= 1800000) {
			//
			// Problem.geracaoDeParada = i;
			// System.out.println("Estorou o tempo");
			// while (t1.isAlive() || t2.isAlive() || t3.isAlive()) {
			// }
			// Collections.sort(Population.populationDeposits, new
			// FunctionComparatorObjective());
			// System.out.println("Tamanho da solução: " +
			// Population.populationDeposits.size());
			// this.clearVec(Population.populationDeposits.get(0));
			// return Population.populationDeposits.get(0).fullcoast;
			// }
			// } else {
			// count = 0;
			// melhor = Population.populationDeposits.get(0).fullcoast;

			// }

		}

		Problem.geracaoDeParada = ngen;
		while (t1.isAlive() || t2.isAlive() || t3.isAlive()) {
		}
		Collections.sort(Population.populationDeposits, new FunctionComparatorObjective());
		System.out.println("Tamanho da solução: " + Population.populationDeposits.size());
		EvolutionEstrategy.clearVec(Population.populationDeposits.get(0));
		// ShowSolution.showNoWrite(Population.populationDeposits.get(0));
		return Population.populationDeposits.get(0).fullcoast;

	}

	public synchronized void inicializaThread() {

		if (!t1.isAlive()) {
			System.out.println("criou");
			LocalSearchBestImprovementUniqueRoute localSearchBestImprovement1 = new LocalSearchBestImprovementUniqueRoute(
					Population.newPopulation.get(Population.newPopulation.size() - 1));
			t1 = new Thread(localSearchBestImprovement1);
			t1.start();
		}
		if (!t2.isAlive()) {
			System.out.println("criou t2");
			LocalSearchBestImprovementUniqueRoute localSearchBestImprovement2 = new LocalSearchBestImprovementUniqueRoute(
					Population.populationDeposits.get(0));
			t2 = new Thread(localSearchBestImprovement2);
			t2.start();
		}
		if (!t3.isAlive()) {
			System.out.println("criou t3");
			LocalSearchBestImprovementUniqueRoute localSearchBestImprovement3 = new LocalSearchBestImprovementUniqueRoute(
					Population.populationDeposits.get(1));
			t3 = new Thread(localSearchBestImprovement3);
			t3.start();
		}

	}

	public static void clearVec(Container_Deposits dep) {
		for (int i = 0; i < dep.Deposit_List.size(); i++) {
			for (int j = dep.Deposit_List.get(i).vehicles.Vehicles_List.size() - 1; j >= 0; j--) {

				if (dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.size() == 0) {
					dep.Deposit_List.get(i).vehicles.Vehicles_List.remove(j);

				}
			}
		}

	}

	public static Container_Deposits mutation(Container_Deposits dep, double pbm) throws CloneNotSupportedException {
		Random rnd = new Random();
		int rndDep1, rndDep2, u, v, rndVec1, rndVec2;
		Container_Deposits novo = dep.clone();
		Container_Deposits anterior = dep.clone();
		rndDep1 = rnd.nextInt(novo.Deposit_List.size());
		rndDep2 = rnd.nextInt(novo.Deposit_List.size());

		rndVec1 = rnd.nextInt(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.size());

		rndVec2 = rnd.nextInt(novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.size());

		int N = 0;
		int M = 0;
		if (rndDep1 == rndDep2 && rndVec1 == rndVec2) {
			N = M = 2;
		}
	
		if ((novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size() >= N
				&& novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
						.size() >= M)) {

			for (u = 0; u < novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
					.size(); u++) {

				for (v = 0; v < novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
						.size(); v++) {
	
					if (rnd.nextDouble() <= pbm) {
					
						// Verificar demanda
						if (!(u == v && rndDep1 == rndDep2 && rndVec1 == rndVec2)) {

							Client aux = novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
									.get(rndVec1).clients.Client_List.get(u).clone();
							Client aux2 = novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
									.get(rndVec2).clients.Client_List.get(v).clone();

							novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
									.get(rndVec2).vehicleDemand = novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
											.get(rndVec2).vehicleDemand
											- novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
													.get(rndVec2).clients.Client_List.get(v).demand
											+ novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
													.get(rndVec1).clients.Client_List.get(u).demand;

							novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
									.get(rndVec1).vehicleDemand = novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
											.get(rndVec1).vehicleDemand
											- novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
													.get(rndVec1).clients.Client_List.get(u).demand
											+ novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
													.get(rndVec2).clients.Client_List.get(v).demand;
							if (novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
									.get(rndVec2).vehicleDemand <= novo.Deposit_List.get(rndDep2).VehicleLoad
									&& novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
											.get(rndVec1).vehicleDemand <= novo.Deposit_List.get(rndDep1).VehicleLoad
									&& novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
											.get(rndVec1).vehicleDemand >= 0
									&& novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
											.get(rndVec2).vehicleDemand >= 0) {

								novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
										.remove(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
												.get(rndVec1).clients.Client_List.get(u));

								novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
										.add(u, aux2.clone());

								novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
										.remove(novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
												.get(rndVec2).clients.Client_List.get(v));
								novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
										.add(v, aux.clone());

							} else {
								novo = anterior.clone();
							}
																					
								anterior = novo.clone();							
						}
					}
				}
			}
			if (!calcularNovoCusto(novo)) {

				novo = dep.clone();
				return novo;
			} else {
				Distribution.funcaoObjetivo(novo);
				return novo;
			}

			// Container_Deposits depClone = dep.clone();
			// Distribution.funcaoObjetivo(depClone);
			// return depClone;
		}
		return novo;
	}
	
	public static Container_Deposits newMutation(Container_Deposits dep, double pbm) throws CloneNotSupportedException, IOException {
		Random rnd = new Random();
		int rndDep1, rndDep2;
		Container_Deposits novo = dep.clone();


		routeToList(novo);
		
		for (int i = 0 ; i < Problem.numClients ; i ++) {
			rndDep1 = rnd.nextInt(novo.Deposit_List.size());
			
			rndDep2 = rnd.nextInt(novo.Deposit_List.size());	
	
			
//			int N = 0;
//			int M = 0;
//			
			if (rndDep1 == rndDep2) {
			
				if ( rnd.nextDouble() <= pbm && novo.Deposit_List.get(rndDep1).clients.Client_List.size() > 1) {
					int client = rnd.nextInt(novo.Deposit_List.get(rndDep1).clients.Client_List.size());
					int client2 = rnd.nextInt(novo.Deposit_List.get(rndDep1).clients.Client_List.size());
					
					while (client2 == client) {
						client2 = rnd.nextInt(novo.Deposit_List.get(rndDep1).clients.Client_List.size());
					}
					
					Collections.swap(novo.Deposit_List.get(rndDep1).clients.Client_List,client,client2);
				}
			} else if (rnd.nextDouble() <= pbm && novo.Deposit_List.get(rndDep1).clients.Client_List.size() > 1 && novo.Deposit_List.get(rndDep2).clients.Client_List.size() > 1) {
			
				int client = rnd.nextInt(novo.Deposit_List.get(rndDep1).clients.Client_List.size());
				
				int client2 = rnd.nextInt(novo.Deposit_List.get(rndDep2).clients.Client_List.size());
				
				Client aux = novo.Deposit_List.get(rndDep1).clients.Client_List.get(client);
				
				Client aux2 = novo.Deposit_List.get(rndDep2).clients.Client_List.get(client2);
				
				novo.Deposit_List.get(rndDep1).clients.Client_List.remove(novo.Deposit_List.get(rndDep1).clients.Client_List.get(client));

				novo.Deposit_List.get(rndDep1).clients.Client_List.add(client, aux2);

				novo.Deposit_List.get(rndDep2).clients.Client_List.remove(novo.Deposit_List.get(rndDep2).clients.Client_List.get(client2));

				novo.Deposit_List.get(rndDep2).clients.Client_List.add(client2, aux);
								
				
			}
			
		}

		Container_Deposits mutado = Distribution.DistributionMutation(novo);
		Distribution.funcaoObjetivo(mutado);
//		
//		System.out.println(mutado.fullcoast);
//		System.out.println("anterior");
//		ShowSolution.showNoWrite(dep);
//		System.out.println("novo");
//		System.out.println(mutado.Deposit_List.get(0).vehicles.Vehicles_List.size());

		return mutado;
	}
	
	public static Container_Deposits routeToList(Container_Deposits dep)
	{
		for (int i = 0 ; i < dep.Deposit_List.size(); i ++) {
			for (int j = 0 ; j < dep.Deposit_List.get(i).vehicles.Vehicles_List.size(); j++) {
				for (int k = 0 ; k < dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.size(); k++) {
					dep.Deposit_List.get(i).clients.Client_List.add(dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.get(k).clone());
					
				}
				dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.clear();
			}			
		}
		return dep;
	}

	public static boolean calcularNovoCusto(Container_Deposits dep) {

		for (int i = 0; i < dep.Deposit_List.size(); i++) {
			for (int j = 0; j < dep.Deposit_List.get(i).vehicles.Vehicles_List.size(); j++) {

				dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).coast = 0;
				for (int k = 0; k < dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.size()
						- 1; k++) {

					dep.Deposit_List.get(i).vehicles.Vehicles_List.get(
							j).coast += Distribution.matrixClienttoClient[dep.Deposit_List.get(i).vehicles.Vehicles_List
									.get(j).clients.Client_List.get(k).clientNumber
									- 1][dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List
											.get(k + 1).clientNumber - 1];

				}

				if (dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.isEmpty() == false) {

					dep.Deposit_List.get(i).vehicles.Vehicles_List.get(
							j).coast += Distribution.matrixClientsDep[dep.Deposit_List.get(i).vehicles.Vehicles_List
									.get(j).clients.Client_List.get(0).clientNumber - 1][i];
					dep.Deposit_List.get(i).vehicles.Vehicles_List.get(
							j).coast += Distribution.matrixClientsDep[dep.Deposit_List.get(i).vehicles.Vehicles_List
									.get(j).clients.Client_List.get(
											dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List
													.size() - 1).clientNumber
									- 1][i];

				}
				if (dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).coast > dep.Deposit_List
						.get(i).RouteDuration) {
					return false;
				}
			}
		}
		return true;
	}

	// if (rnd.nextDouble() <= pbm) {
	//
	// for (int i = 0 ; i < depClone.Deposit_List.size(); i++) {
	// for (int j = 0 ; j <
	// depClone.Deposit_List.get(i).vehicles.Vehicles_List.size(); j++) {
	// for (int k = 0 ; k <
	// depClone.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.size();
	// k++) {
	// depClone.Deposit_List.get(i).clients.Client_List.add(
	// dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.get(k).clone());
	// //
	// auxClients.Client_List.add(dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.get(k).clone());
	// }
	//
	// }
	// depClone.Deposit_List.get(i).vehicles.Vehicles_List.clear();
	// } // Crio um depósito clone com apenas os clientes alocados, sem veículos
	//
	// for (int i = 0 ; i < depClone.Deposit_List.size(); i++) {
	// for (int j = 0 ; j < depClone.Deposit_List.get(i).clients.Client_List.size();
	// j++) {
	// containerAux.Client_List.clear();
	// int indDep2 = i+1;
	// if (indDep2 > dep.Deposit_List.size()-1) {
	// indDep2 = 0;
	// }
	// //int indDep2 = rnd.nextInt(depClone.Deposit_List.size());
	//
	// do {// Se os índices forem iguais, apenas faço um swap no mesmo depósito
	// // Caso forem diferentes, faço um Giant Tour com as duas listas, faço a troca
	// e retorno elas ao deposito.
	// ind2 =
	// rnd.nextInt(depClone.Deposit_List.get(indDep2).clients.Client_List.size());
	// }while ( (i == indDep2) && (j == ind2));
	// if (indDep2 != i) {
	// int size = (depClone.Deposit_List.get(i).clients.Client_List.size());
	//
	//
	// containerAux.Client_List.addAll(new
	// ArrayList<Client>(depClone.Deposit_List.get(i).clients.Client_List));
	//
	//
	// containerAux.Client_List.addAll(new
	// ArrayList<Client>(depClone.Deposit_List.get(indDep2).clients.Client_List));
	//
	//
	// depClone.Deposit_List.get(i).clients.Client_List.clear();
	// depClone.Deposit_List.get(indDep2).clients.Client_List.clear();
	//
	// Collections.swap(containerAux.Client_List, j, ind2);
	// //System.out.println("trocou");
	// depClone.Deposit_List.get(i).clients.Client_List.addAll(containerAux.Client_List.subList(0,
	// size));
	// depClone.Deposit_List.get(indDep2).clients.Client_List.addAll(containerAux.Client_List.subList(size,
	// containerAux.Client_List.size()));
	//
	//
	// }// Se os índices forem iguais, apenas faço um swap no mesmo depósito
	// // Caso forem diferentes, faço um Giant Tour com as duas listas, faço a troca
	// e retorno elas ao deposito.
	// else {
	// Collections.swap(depClone.Deposit_List.get(i).clients.Client_List, j, ind2);
	// }
	//
	// }
	//
	// }
	//
	// }
	// depClone = Distribution.DistributionMutation(depClone);
	// Distribution.funcaoObjetivo(depClone);
	// // System.out.println("depois " + depClone.fullcoast);

}
