package mdvrp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Random;

import mdvrp.containers.Container_Client;
import mdvrp.containers.Container_Deposits;
import mdvrp.util.Client;

public class LocalSearchBestImprovementUniqueRoute implements Runnable {
	public Container_Deposits anterior;
	public Random rnd = new Random();
	public Container_Deposits melhor;
	public Container_Deposits dep;

	public LocalSearchBestImprovementUniqueRoute(Container_Deposits dep) {
		this.dep = dep.clone();
	}

	@Override
	public void run() {
		try {
			this.localSearchWithBestSolution(this.dep);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void localSearchWithBestSolution(Container_Deposits dep) throws CloneNotSupportedException {
		// a partir de um número de tentativas, tentar cada estrategia da busca local,
		// por exemplo, se
		// na primeira tentativa já melhorar a solução, partir para outra tentativa para
		// tentar melhorar mais,
		// caso não melhore, tentar novamente, até que a variável tentativas seja
		// atendida, caso não haja melhora, partir para a proximo
		// Se houver melhora, deletar solução anterior e usar a nova solução.
		// Randomizar a ordem de estrategias

		Container_Deposits novo = dep.clone();
		Distribution.funcaoObjetivo(novo);
		// Distribution.funcaoObjetivo(dep);
		//// System.out.println("Custo inicial do dep " + dep.fullcoast);
		ArrayList<Integer> strategy = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)); // 1,7,8,9
		Collections.shuffle(strategy);
		/*
		 * 1. Mover um cliente u e realocá-lo após outro cliente v; 2. Mover os clientes
		 * u e x para depois do cliente v, nessa ordem; 3. Mover os clientes u e x para
		 * depois do cliente v, em ordem inversa; 4. Trocar as posições dos clientes u e
		 * v; Estrategia 1 5. Trocar as posições dos clientes u e x pela de v; 6. Trocar
		 * as posições dos clientes u e x pelas de v e y; 7. Se u e v pertencem a mesma
		 * rota, trocar os arcos (u, x) e (v, y) por (u, v) e (x, y); 8. Se u e v
		 * pertencem a rotas distintas; trocar os arcos (u, x) e (v, y) por (u, v) e (x,
		 * y); 9. Se u e v pertencem a rotas distintas; trocar os arcos (u, x) e (v, y)
		 * por (u, y) e (x, v).
		 */
		int rndDep1, rndDep2, rndVec1, rndVec2;
		rndDep1 = rnd.nextInt(novo.Deposit_List.size());
		rndDep2 = rnd.nextInt(novo.Deposit_List.size());

		rndVec1 = rnd.nextInt(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.size());

		rndVec2 = rnd.nextInt(novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.size());

		for (int i = 1; i <= strategy.size(); i++) {
			int test = strategy.get(i - 1);
			switch (test) {
			// Estratégia 1. Trocar as posições dos clientes u e v;

			case 1:
				// System.out.println(novo.fullcoast + " Custo do novo antes do 1");

				novo = swapUV(novo, rndDep1, rndVec1, rndDep2, rndVec2);
				// Verify.clientVerify(novo);

				// System.out.println(novo.fullcoast + " Custo do novo apos do 1");
				break;
			case 2:
				// System.out.println(novo.fullcoast + " Custo do novo antes do 2");
				novo = realocarUV(novo, rndDep1, rndVec1, rndDep2, rndVec2);;
				// Verify.clientVerify(novo);

				// System.out.println(novo.fullcoast + " Custo do novo apos do 2");
				break;
			case 3:
				// System.out.println(novo.fullcoast + " Custo do novo antes do 3");
				novo = UXaposV(novo, rndDep1, rndVec1, rndDep2, rndVec2);

				// System.out.println(novo.fullcoast + " Custo do novo apos do 3");
				break;
			case 4:
				// System.out.println(novo.fullcoast + " Custo do novo antes do 4");
				novo = XUaposV(novo, rndDep1, rndVec1, rndDep2, rndVec2);

				// System.out.println(novo.fullcoast + " Custo do novo apos do 4");
				break;

			case 5:
				// System.out.println(novo.fullcoast + " Custo do novo antes do 5");
				novo = swapUXtoV(novo, rndDep1, rndVec1, rndDep2, rndVec2);

				// System.out.println(novo.fullcoast + " Custo do novo apos do 5");
				break;

			case 6:
				// System.out.println(novo.fullcoast + " Custo do novo antes do 6");
				novo = swapUXtoVY(novo, rndDep1, rndVec1, rndDep2, rndVec2);

				// System.out.println(novo.fullcoast + " Custo do novo apos do 6");
				break;

			case 7:

				novo = trocarArcosUXtoVY(novo, rndDep1, rndVec1);
				// System.out.println(novo.fullcoast + " Custo do novo apos do 7");
				break;
			case 8:

				novo = trocarUXeVYporUVeXY(novo, rndDep1, rndVec1, rndDep2, rndVec2);;

				// System.out.println(novo.fullcoast + " Custo do novo apos do 8");
				break;
			case 9:

				novo = trocarUXeVYporUYeXV(novo, rndDep1, rndVec1, rndDep2, rndVec2);;

				break;

			} // fim do switch
		} // fim das estrategias

		// for (int i = 0 ; i < novo.Deposit_List.size(); i++) {
		// for (int j = 0 ; j < novo.Deposit_List.get(i).vehicles.Vehicles_List.size();
		// j++) {
		// if
		// (novo.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.isEmpty()
		// == true) {
		// novo.Deposit_List.get(i).vehicles.Vehicles_List.remove(j);
		// }
		// }
		// }
		//
		// Distribution.funcaoObjetivo(novo);

		if (novo.fullcoast >= dep.fullcoast) {
			System.out.println("Sem melhora");
			return;

		} else {

			System.out.println("novo : " + novo.fullcoast);
			// Population.populationDeposits.remove(dep);
			synchronized (Population.populationDeposits) {
				Population.populationDeposits.add(novo.clone());
			
				novo = null;
			}
			

			// Population.newPopulation.add(novo.clone());
			anterior = null;

		}
	}

	public Container_Deposits swapUV(Container_Deposits novo, int rndDep1, int rndVec1, int rndDep2, int rndVec2) {
		// anterior = null;

		anterior = novo.clone();
		melhor = novo.clone();

		int u, v;
		 
		int N = 0;
		int M = 0;
		if (rndDep1 == rndDep2 && rndVec1 == rndVec2) {
			N = M = 2;		
		}

		if ((novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size() >= N
				&& novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List.size() >= M)) {
			
			for (u = 0; u < novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
					.size(); u++) {
				// x = u + 1;
				// //System.out.println("U : " + u);
				for (v = 0; v < novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
						.size(); v++) {
					// Verificar demanda
					if (!(u == v && rndDep1 == rndDep2 && rndVec1 == rndVec2)) {
						// //System.out.println("u " + u + " v " + v + " dep1 " + rndDep1 + " dep2 " +
						// rndDep2 + " vec1 " + rndVec1 + " vec 2 " + rndVec2);

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
								&& novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).vehicleDemand >= 0
								&& novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
										.get(rndVec2).vehicleDemand >= 0) {

							novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
									.add(u, aux2.clone());
							novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
									.remove(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
											.get(rndVec1).clients.Client_List.get(u));

							novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
									.add(v, aux.clone());
							novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
									.remove(novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
											.get(rndVec2).clients.Client_List.get(v));

							Distribution.funcaoObjetivo(anterior);

							Distribution.funcaoObjetivo(novo);

							if (novo.fullcoast < melhor.fullcoast && EvolutionEstrategy.calcularNovoCusto(novo)) {
								// System.out.println("Custo do melhor " + melhor.fullcoast);
								melhor = novo.clone();
								novo = anterior.clone();

							} else {
								novo = anterior.clone();
							}

						} else {
							novo = anterior.clone();
						}

					}
				}
			}
		}

		novo = melhor.clone();
		return novo;

	}

	public Container_Deposits realocarUV(Container_Deposits novo, int rndDep1, int rndVec1, int rndDep2, int rndVec2) {
		melhor = novo.clone();
		anterior = novo.clone();

		// Mover um cliente u e realocá-lo após outro cliente v;

		int u, v;
		 
		int N = 0;
		int M = 0;
		if (rndDep1 == rndDep2 && rndVec1 == rndVec2) {
			N = M = 2;		
		}

		if ((novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size() >= N
				&& novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List.size() >= M)) {
			
			for (u = 0; u < novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
					.size(); u++) {
				for (v = 0; v < novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
						.size(); v++) {

					// Verificar demanda
					// int demand2=0;
					if (!(u == v && rndDep1 == rndDep2 && rndVec1 == rndVec2)) {

						novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
								.get(rndVec2).vehicleDemand = novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
										.get(rndVec2).vehicleDemand
										+ novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
												.get(rndVec1).clients.Client_List.get(u).demand;

						novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
								.get(rndVec1).vehicleDemand = novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
										.get(rndVec1).vehicleDemand
										- novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
												.get(rndVec1).clients.Client_List.get(u).demand;

						if (novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
								.get(rndVec2).vehicleDemand <= novo.Deposit_List.get(rndDep2).VehicleLoad
								&& novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
										.get(rndVec1).vehicleDemand <= novo.Deposit_List.get(rndDep1).VehicleLoad
								&& novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).vehicleDemand >= 0
								&& novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
										.get(rndVec2).vehicleDemand >= 0) {

							Client aux = novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
									.get(rndVec1).clients.Client_List.get(u);
							int posV = novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
									.get(rndVec2).clients.Client_List
											.indexOf(novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
													.get(rndVec2).clients.Client_List.get(v));

							novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
									.add(posV + 1, aux.clone());
							novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
									.remove(aux);

							Distribution.funcaoObjetivo(anterior);

							Distribution.funcaoObjetivo(novo);

							if (novo.fullcoast < melhor.fullcoast && EvolutionEstrategy.calcularNovoCusto(novo)) {
								melhor = novo.clone();
								novo = anterior.clone();

							} else {
								novo = anterior.clone();
							}

						} else {
							novo = anterior.clone();
						}
					}
				}
			}

		}

		novo = melhor.clone();
		return novo;
	}

	public Container_Deposits UXaposV(Container_Deposits novo, int rndDep1, int rndVec1, int rndDep2, int rndVec2) {
		anterior = novo.clone();

		melhor = novo.clone();
		// Mover os clientes u e x para depois do cliente v;
		int u, x, v;
		int N = 1;
		int M = 2;
		if (rndDep1 == rndDep2 && rndVec1 == rndVec2) {
			N = M = 3;
		}
		if (novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List.size() >= N
				&& novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size() >= M) {

			for (u = 0; u < novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
					.size() - 1; u++) {
				x = u + 1;
				// //System.out.println("U : " + u);
				for (v = 0; v < novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
						.size(); v++) {
					// Verificar demanda
					// int demand1=0, demand2=0;
					// Verificar demanda
					// int demand3 = 0;
					if (!((u == v || x == v) && rndDep1 == rndDep2 && rndVec1 == rndVec2)) {

						novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
								.get(rndVec2).vehicleDemand += novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
										.get(rndVec1).clients.Client_List.get(u).demand
										+ novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
												.get(rndVec1).clients.Client_List.get(x).demand;

						novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
								.get(rndVec1).vehicleDemand = novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
										.get(rndVec1).vehicleDemand
										- novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
												.get(rndVec1).clients.Client_List.get(u).demand;
						novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
								.get(rndVec1).vehicleDemand = novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
										.get(rndVec1).vehicleDemand
										- novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
												.get(rndVec1).clients.Client_List.get(x).demand;
						if (novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
								.get(rndVec2).vehicleDemand <= novo.Deposit_List.get(rndDep2).VehicleLoad
								&& novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
										.get(rndVec1).vehicleDemand <= novo.Deposit_List.get(rndDep1).VehicleLoad
								&& novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).vehicleDemand >= 0
								&& novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
										.get(rndVec2).vehicleDemand >= 0) {

							Client aux = novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
									.get(rndVec1).clients.Client_List.get(u);

							Client aux2 = novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
									.get(rndVec1).clients.Client_List.get(x);

							// Inserir U e X após V
							int posV = novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
									.get(rndVec2).clients.Client_List
											.indexOf(novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
													.get(rndVec2).clients.Client_List.get(v));
							novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
									.add(posV + 1, aux.clone());
							novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
									.add(posV + 2, aux2.clone());
							// Remover U e X
							novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
									.remove(aux);
							novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
									.remove(aux2);

							Distribution.funcaoObjetivo(anterior);
							Distribution.funcaoObjetivo(novo);

							if (novo.fullcoast < melhor.fullcoast && EvolutionEstrategy.calcularNovoCusto(novo)) {
								melhor = novo.clone();
								novo = anterior.clone();

							} else {
								novo = anterior.clone();

							}

						} else {
							novo = anterior.clone();
						}

					}

				}
			}
		}

		novo = melhor.clone();
		return novo;

	}

	public Container_Deposits XUaposV(Container_Deposits novo, int rndDep1, int rndVec1, int rndDep2, int rndVec2) {
		anterior = novo.clone();

		melhor = novo.clone();
		// Mover os clientes x e u para depois do cliente v;

		int u, x, v;
		int N = 1;
		int M = 2;
		if (rndDep1 == rndDep2 && rndVec1 == rndVec2) {
			N = M = 3;
		}

		for (rndVec1 = 0; rndVec1 < novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.size(); rndVec1++) {
			for (rndVec2 = 0; rndVec2 < novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.size(); rndVec2++) {
				if (novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List.size() >= N 
						&& novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
								.size() >= M) {

					for (u = 0; u < novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
							.get(rndVec1).clients.Client_List.size() - 1; u++) {
						x = u + 1;
						// //System.out.println("U : " + u);
						for (v = 0; v < novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
								.get(rndVec2).clients.Client_List.size(); v++) {
							// Verificar demanda
							// int demand1=0, demand2=0;
							// Verificar demanda
							// int demand3 = 0;
							if (!((u == v || x == v) && rndDep1 == rndDep2 && rndVec1 == rndVec2)) {

								novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(
										rndVec2).vehicleDemand += novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
												.get(rndVec1).clients.Client_List.get(u).demand
												+ novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
														.get(rndVec1).clients.Client_List.get(x).demand;

								novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(
										rndVec1).vehicleDemand = novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
												.get(rndVec1).vehicleDemand
												- novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
														.get(rndVec1).clients.Client_List.get(u).demand;
								novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(
										rndVec1).vehicleDemand = novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
												.get(rndVec1).vehicleDemand
												- novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
														.get(rndVec1).clients.Client_List.get(x).demand;
								if (novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
										.get(rndVec2).vehicleDemand <= novo.Deposit_List.get(rndDep2).VehicleLoad
										&& novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(
												rndVec1).vehicleDemand <= novo.Deposit_List.get(rndDep1).VehicleLoad
										&& novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
												.get(rndVec1).vehicleDemand >= 0
										&& novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
												.get(rndVec2).vehicleDemand >= 0) {

									Client aux = novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
											.get(rndVec1).clients.Client_List.get(u);

									Client aux2 = novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
											.get(rndVec1).clients.Client_List.get(x);

									// Inserir U e X após V
									int posV = novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
											.get(rndVec2).clients.Client_List
													.indexOf(novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
															.get(rndVec2).clients.Client_List.get(v));

									novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
											.get(rndVec2).clients.Client_List.add(posV + 1, aux2.clone());
									novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
											.get(rndVec2).clients.Client_List.add(posV + 2, aux.clone());
									// Remover U e X
									novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
											.get(rndVec1).clients.Client_List.remove(aux2);
									novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
											.get(rndVec1).clients.Client_List.remove(aux);

									Distribution.funcaoObjetivo(anterior);
									Distribution.funcaoObjetivo(novo);

									if (novo.fullcoast < melhor.fullcoast
											&& EvolutionEstrategy.calcularNovoCusto(novo)) {
										melhor = novo.clone();
										novo = anterior.clone();

									} else {
										novo = anterior.clone();

									}

								} else {
									novo = anterior.clone();

								}
							}

						}
					}
				}
			}
		}

		novo = melhor.clone();
		return novo;

	}

	public Container_Deposits swapUXtoV(Container_Deposits novo, int rndDep1, int rndVec1, int rndDep2, int rndVec2) {
		anterior = novo.clone();
		melhor = novo.clone();
		// Trocar as posições dos clientes u e x pela de v;
		int u, x, v;

		int N = 2;
		int M = 1;
		if (rndDep1 == rndDep2 && rndVec1 == rndVec2) {
			N = M = 3;
		}
		if (novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List.size() >= M
				&& novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size() >= N) {

			// aq
			for (u = 0; u < novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
					.size() - 1; u++) {
				x = u + 1;
				// //System.out.println("U : " + u);
				for (v = 0; v < novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
						.size(); v++) {

					// int demand3, demand1;
					if (!((u == v || x == v) && rndDep1 == rndDep2 && rndVec1 == rndVec2)) {

						novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
								.get(rndVec2).vehicleDemand += novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
										.get(rndVec1).clients.Client_List.get(u).demand
										+ novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
												.get(rndVec1).clients.Client_List.get(x).demand
										- novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
												.get(rndVec2).clients.Client_List.get(v).demand;

						novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
								.get(rndVec1).vehicleDemand = novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
										.get(rndVec1).vehicleDemand
										+ novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
												.get(rndVec2).clients.Client_List.get(v).demand
										- novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
												.get(rndVec1).clients.Client_List.get(u).demand
										- novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
												.get(rndVec1).clients.Client_List.get(x).demand;
						if (novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
								.get(rndVec2).vehicleDemand <= novo.Deposit_List.get(rndDep2).VehicleLoad
								&& novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
										.get(rndVec1).vehicleDemand <= novo.Deposit_List.get(rndDep1).VehicleLoad
								&& novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).vehicleDemand >= 0
								&& novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
										.get(rndVec2).vehicleDemand >= 0) {

							Client aux = novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
									.get(rndVec1).clients.Client_List.get(u);
							Client aux2 = novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
									.get(rndVec1).clients.Client_List.get(x);
							Client aux3 = novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
									.get(rndVec2).clients.Client_List.get(v);

							int posV = novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
									.get(rndVec2).clients.Client_List
											.indexOf(novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
													.get(rndVec2).clients.Client_List.get(v));
							int posU = novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
									.get(rndVec1).clients.Client_List
											.indexOf(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
													.get(rndVec1).clients.Client_List.get(u));

							novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
									.add(posV, aux2.clone());
							novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
									.add(posV + 1, aux.clone());
							novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
									.add(posU, aux3.clone());

							novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
									.remove(aux);
							novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
									.remove(aux2);
							novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
									.remove(aux3);

							Distribution.funcaoObjetivo(anterior);
							Distribution.funcaoObjetivo(novo);

							if (novo.fullcoast < melhor.fullcoast && EvolutionEstrategy.calcularNovoCusto(novo)) {

								melhor = novo.clone();
								novo = anterior.clone();

							} else {
								//// System.out.println("else");
								novo = anterior.clone();

							}
						} else {
							novo = anterior.clone();
							//// System.out.println("Estorou demanda");
						}

					}
				}
			}
		}

		// for (int i = 0 ; i < novo.Deposit_List.size(); i++) {
		// for (int j = 0 ; j < novo.Deposit_List.get(i).vehicles.Vehicles_List.size();
		// j++) {
		// if
		// (novo.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.isEmpty()
		// == true) {
		// novo.Deposit_List.get(i).vehicles.Vehicles_List.remove(j);
		// }
		// }
		// }
		novo = melhor.clone();
		return novo;
	}

	public Container_Deposits swapUXtoVY(Container_Deposits novo, int rndDep1, int rndVec1, int rndDep2, int rndVec2) {
		anterior = novo.clone();
		melhor = novo.clone();
		// Trocar as posições dos clientes u e x pela de v e y;

		// aq

		int u, x, v, y;

		rndDep1 = rnd.nextInt(novo.Deposit_List.size());
		rndDep2 = rnd.nextInt(novo.Deposit_List.size());

		rndVec1 = rnd.nextInt(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.size());

		rndVec2 = rnd.nextInt(novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.size());
		
		int N = 2;
		int M = 2;
		if (rndDep1 == rndDep2 && rndVec1 == rndVec2) {
			N = M = 4;
		}
		if (novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List.size() >= N
				&& novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size() >= M) {

		}

		// aq
		for (u = 0; u < novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size()
				- 1; u++) {
			x = u + 1;
			// //System.out.println("U : " + u);
			for (v = 0; v < novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
					.size() - 1; v++) {
				y = v + 1;
				// int demand3, demand1;
				if (!(((u == v || x == v) || (u == y || x == y)) && rndDep1 == rndDep2 && rndVec1 == rndVec2)) {

					novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
							.get(rndVec2).vehicleDemand += novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
									.get(rndVec1).clients.Client_List.get(u).demand
									+ novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
											.get(rndVec1).clients.Client_List.get(x).demand
									- novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
											.get(rndVec2).clients.Client_List.get(v).demand
									- novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
											.get(rndVec2).clients.Client_List.get(y).demand;

					novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).vehicleDemand = novo.Deposit_List
							.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).vehicleDemand
							+ novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
									.get(y).demand
							+ novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
									.get(v).demand
							- novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
									.get(u).demand
							- novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
									.get(x).demand;

					if (novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
							.get(rndVec2).vehicleDemand <= novo.Deposit_List.get(rndDep2).VehicleLoad
							&& novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
									.get(rndVec1).vehicleDemand <= novo.Deposit_List.get(rndDep1).VehicleLoad
							&& novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).vehicleDemand >= 0
							&& novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).vehicleDemand >= 0) {

						Client aux = novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
								.get(rndVec1).clients.Client_List.get(u);
						Client aux2 = novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
								.get(rndVec1).clients.Client_List.get(x);

						Client aux3 = novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
								.get(rndVec2).clients.Client_List.get(v);
						Client aux4 = novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
								.get(rndVec2).clients.Client_List.get(y);

						int posV = novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
								.get(rndVec2).clients.Client_List
										.indexOf(novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
												.get(rndVec2).clients.Client_List.get(v));
						int posU = novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
								.get(rndVec1).clients.Client_List
										.indexOf(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
												.get(rndVec1).clients.Client_List.get(u));

						novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List.add(posV,
								aux2.clone());
						novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
								.add(posV + 1, aux.clone());
						novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.add(posU,
								aux3.clone());
						novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
								.add(posU + 1, aux4.clone());

						novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
								.remove(aux);
						novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
								.remove(aux2);
						novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
								.remove(aux3);
						novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
								.remove(aux4);

						Distribution.funcaoObjetivo(anterior);
						Distribution.funcaoObjetivo(novo);

						if (novo.fullcoast < melhor.fullcoast && EvolutionEstrategy.calcularNovoCusto(novo)) {

							melhor = novo.clone();
							novo = anterior.clone();

						} else {
							//// System.out.println("else");
							novo = anterior.clone();

						}
					} else {
						novo = anterior.clone();
						//// System.out.println("Estorou demanda");
					}

				}
			}
		}

		novo = melhor.clone();

		return novo;
	}

	public Container_Deposits trocarArcosUXtoVY(Container_Deposits novo, int rndDep1, int rndVec1) {
		// //System.out.println("caso 7");

		melhor = novo.clone();

		anterior = novo.clone();

		if (novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size() >= 4) {

			for (int u = 0; u < novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
					.size() - 4; u++) {
				int x = u + 1;
				// //System.out.println("U : " + u);
				for (int v = x+ 1; v < novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size() - 2; v++) {
					int y = v + 1;

					Collections.reverse(
							novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
									.subList(x, y));
					Distribution.funcaoObjetivo(anterior);
					Distribution.funcaoObjetivo(novo);

					if (novo.fullcoast < melhor.fullcoast && EvolutionEstrategy.calcularNovoCusto(novo)) {

						melhor = novo.clone();
						novo = anterior.clone();

					} else {
						// //System.out.println("else");
						novo = anterior.clone();

					}
				}
			}
		}

		novo = melhor.clone();
		return novo;

	}

	public Container_Deposits trocarUXeVYporUVeXY(Container_Deposits novo, int rndDep1, int rndVec1, int rndDep2,
			int rndVec2) {

		melhor = novo.clone();

		int demandU = 0, demandV = 0;
		anterior = novo.clone();

		Container_Client novoU = new Container_Client();

		Container_Client novoV = new Container_Client();

		if (novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List.size() >= 4
				&& novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size() >= 4
				&& rndDep1 != rndDep2 && rndVec1 != rndVec2) {

			for (int u = 0; u < novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
					.size() - 1; u++) {
				int x = u + 1;

				for (int v = 0; v < novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
						.get(rndVec2).clients.Client_List.size() - 1; v++) {
					int y = v + 1;
					// //System.out.println("u " + u + " v " + v);
					novoU.Client_List.clear();
					novoV.Client_List.clear();
					demandU = 0;
					demandV = 0;

					for (int i = 0; i <= u; i++) {
						novoU.Client_List.add(
								novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
										.get(i).clone());

					}

					for (int i = v; i >= 0; i--) {
						novoU.Client_List.add(
								novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
										.get(i).clone());
					}

					for (int i = novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
							.size() - 1; i >= x; i--) {
						novoV.Client_List.add(
								novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
										.get(i).clone());
					}

					for (int i = y; i < novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
							.get(rndVec2).clients.Client_List.size(); i++) {
						novoV.Client_List.add(
								novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
										.get(i).clone());
					}
					//// System.out.println("Quantidade de clientes V : " +
					//// novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List.size()
					//// + " novoV : " + novoV.Client_List.size() + " Quantidade U " +
					//// novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size()
					//// + " NovoU " + novoU.Client_List.size());

					for (int i = 0; i < novoU.Client_List.size(); i++) {

						demandU += novoU.Client_List.get(i).demand;

					}

					for (int i = 0; i < novoV.Client_List.size(); i++) {

						demandV += novoV.Client_List.get(i).demand;

					}

					if (demandU <= novo.Deposit_List.get(rndDep1).VehicleLoad
							&& demandV <= novo.Deposit_List.get(rndDep2).VehicleLoad) {
						// //System.out.println("demand < VehicleLoad");
						// anterior = novo.clone();
						novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.clear();
						novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).vehicleDemand = 0;

						novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List.clear();
						novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).vehicleDemand = 0;
						// //System.out.println("tamanho do novoU " + novoU.Client_List.size());
						for (int i = 0; i < novoU.Client_List.size(); i++) {
							// //System.out.println("ERNTREI AUQI DISTRIBUINDO CLIENTES");
							novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
									.add(novoU.Client_List.get(i).clone());
						}

						for (int i = 0; i < novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
								.get(rndVec1).clients.Client_List.size(); i++) {
							// //System.out.println("ERNTREI AQUI DEMANDA");
							novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
									.get(rndVec1).vehicleDemand += novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
											.get(rndVec1).clients.Client_List.get(i).demand;
							//// System.out.println("Demanda " +
							//// novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).vehicleDemand);
						}

						for (int i = 0; i < novoV.Client_List.size(); i++) {
							novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
									.add(novoV.Client_List.get(i).clone());
						}

						for (int i = 0; i < novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
								.get(rndVec2).clients.Client_List.size(); i++) {
							novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
									.get(rndVec2).vehicleDemand += novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
											.get(rndVec2).clients.Client_List.get(i).demand;
						}

						Distribution.funcaoObjetivo(anterior);
						Distribution.funcaoObjetivo(novo);

						if (novo.fullcoast < melhor.fullcoast && EvolutionEstrategy.calcularNovoCusto(novo)) {

							melhor = novo.clone();
							novo = anterior.clone();

						} else {
							//// System.out.println("else");
							novo = anterior.clone();

						}
					}

				}
			}
		}

		//// System.out.println("Não houve melhora");
		novo = melhor.clone();
		return novo;
	}

	public Container_Deposits trocarUXeVYporUYeXV(Container_Deposits novo, int rndDep1, int rndVec1, int rndDep2,
			int rndVec2) {

		melhor = novo.clone();

		int demandU = 0, demandV = 0;
		anterior = novo.clone();

		Container_Client novoU = new Container_Client();

		Container_Client novoV = new Container_Client();

		if (novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List.size() >= 4
				&& novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size() >= 4
				&& rndDep1 != rndDep2 && rndVec1 != rndVec2) {

			// //System.out.println("Quantidade de clientes " +
			// novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size());

			for (int u = 0; u < novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
					.size() - 1; u++) {
				int x = u + 1;

				for (int v = u
						+ 1; v < novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
								.size() - 1; v++) {
					int y = v + 1;
					// //System.out.println("u " + u + " v " + v);
					novoU.Client_List.clear();
					novoV.Client_List.clear();
					demandU = 0;
					demandV = 0;

					for (int i = 0; i <= u; i++) {
						novoU.Client_List.add(
								novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
										.get(i).clone());

					}
					for (int i = y; i < novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
							.get(rndVec2).clients.Client_List.size(); i++) {
						novoU.Client_List.add(
								novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
										.get(i).clone());
					}

					for (int i = novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
							.size() - 1; i >= x; i--) {
						novoV.Client_List.add(
								novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
										.get(i).clone());
					}
					for (int i = v; i >= 0; i--) {
						novoV.Client_List.add(
								novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
										.get(i).clone());
					}

					for (int i = 0; i < novoU.Client_List.size(); i++) {

						demandU += novoU.Client_List.get(i).demand;

					}

					for (int i = 0; i < novoV.Client_List.size(); i++) {

						demandV += novoV.Client_List.get(i).demand;

					}

					if (demandU <= novo.Deposit_List.get(rndDep1).VehicleLoad
							&& demandV <= novo.Deposit_List.get(rndDep2).VehicleLoad) {
						// //System.out.println("demand < VehicleLoad");
						// anterior = novo.clone();
						novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.clear();
						novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).vehicleDemand = 0;

						novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List.clear();
						novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).vehicleDemand = 0;
						// //System.out.println("tamanho do novoU " + novoU.Client_List.size());
						for (int i = 0; i < novoU.Client_List.size(); i++) {
							// //System.out.println("ERNTREI AUQI DISTRIBUINDO CLIENTES");
							novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
									.add(novoU.Client_List.get(i).clone());
						}

						for (int i = 0; i < novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
								.get(rndVec1).clients.Client_List.size(); i++) {
							// //System.out.println("ERNTREI AQUI DEMANDA");
							novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
									.get(rndVec1).vehicleDemand += novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
											.get(rndVec1).clients.Client_List.get(i).demand;
							// //System.out.println("Demanda " +
							// novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).vehicleDemand);
						}

						for (int i = 0; i < novoV.Client_List.size(); i++) {
							novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
									.add(novoV.Client_List.get(i).clone());
						}

						for (int i = 0; i < novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
								.get(rndVec2).clients.Client_List.size(); i++) {
							novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
									.get(rndVec2).vehicleDemand += novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
											.get(rndVec2).clients.Client_List.get(i).demand;
						}

						Distribution.funcaoObjetivo(anterior);
						Distribution.funcaoObjetivo(novo);

						if (novo.fullcoast < melhor.fullcoast && EvolutionEstrategy.calcularNovoCusto(novo)) {

							melhor = novo.clone();
							novo = anterior.clone();

						} else {
							//// System.out.println("else");
							novo = anterior.clone();

						}
					}

				}
			}
		}

		novo = melhor.clone();
		return novo;
	}

}