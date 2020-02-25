package mdvrp;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.Collections;
import java.util.Random;

import mdvrp.containers.Container_Client;
import mdvrp.containers.Container_Deposits;
import mdvrp.problem.Problem;
import mdvrp.util.Client;

public class LocalSearch {

	public static int tentativas = 10;
	public static int attemp = 0;
	public static Container_Deposits anterior;
	public static Random rnd = new Random();
	public static int demanda = 0;

	public static void localSearch(Container_Deposits dep) throws CloneNotSupportedException {
		// a partir de um número de tentativas, tentar cada estrategia da busca local,
		// por exemplo, se
		// na primeira tentativa já melhorar a solução, partir para outra tentativa para
		// tentar melhorar mais,
		// caso não melhore, tentar novamente, até que a variável tentativas seja
		// atendida, caso não haja melhora, partir para a proximo
		// Se houver melhora, deletar solução anterior e usar a nova solução.
		// Randomizar a ordem de estrategias

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

		Container_Deposits novo = dep.clone();
		Distribution.funcaoObjetivo(novo);
		Distribution.funcaoObjetivo(dep);
		//// System.out.println("Custo inicial do dep " + dep.fullcoast);
		 ArrayList<Integer> strategy = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)); // 2 --,5,6
//		ArrayList<Integer> strategy = new ArrayList<>(Arrays.asList(2)); // 2 --,5,6
		Collections.shuffle(strategy);

		for (int i = 1; i <= strategy.size(); i++) {
			int test = strategy.get(i - 1);
			switch (test) {
			// Estratégia 1. Trocar as posições dos clientes u e v;

			case 1:
				// System.out.println(novo.fullcoast + " Custo do novo antes do 1");

				novo = swapUV(novo);
				// Verify.clientVerify(novo);
				// demanda = 0;
				// for (int k = 0; k < novo.Deposit_List.size(); k++) {
				// for (int j = 0; j < novo.Deposit_List.get(k).vehicles.Vehicles_List.size();
				// j++) {
				// demanda = 0;
				// for (int n = 0; n <
				// novo.Deposit_List.get(k).vehicles.Vehicles_List.get(j).clients.Client_List
				// .size(); n++) {
				// demanda +=
				// novo.Deposit_List.get(k).vehicles.Vehicles_List.get(j).clients.Client_List
				// .get(n).demand;
				// if (demanda > novo.Deposit_List.get(k).VehicleLoad) {
				// System.out.println("PROBLEMA NO 1");
				// System.out.println(demanda);
				// System.exit(0);
				// } else if (demanda < 0) {
				// System.out.println("PROBLEMA NO 1");
				// System.out.println(demanda);
				// System.exit(0);
				// }
				// }
				// }
				// }

				// System.out.println(novo.fullcoast + " Custo do novo apos do 1");
				break;
			case 2:
				// System.out.println(novo.fullcoast + " Custo do novo antes do 2");
				novo = realocarUV(novo);
				// Verify.clientVerify(novo);
				// demanda = 0;
				// for (int k = 0; k < novo.Deposit_List.size(); k++) {
				// for (int j = 0; j < novo.Deposit_List.get(k).vehicles.Vehicles_List.size();
				// j++) {
				// demanda = 0;
				// for (int n = 0; n <
				// novo.Deposit_List.get(k).vehicles.Vehicles_List.get(j).clients.Client_List
				// .size(); n++) {
				// demanda +=
				// novo.Deposit_List.get(k).vehicles.Vehicles_List.get(j).clients.Client_List
				// .get(n).demand;
				// if (demanda > novo.Deposit_List.get(k).VehicleLoad) {
				// System.out.println("PROBLEMA NO 2");
				// System.out.println(demanda);
				// System.exit(0);
				// } else if (demanda < 0) {
				// System.out.println("PROBLEMA NO 2");
				// System.out.println(demanda);
				// System.exit(0);
				// }
				// }
				// }
				// }
				// System.out.println(novo.fullcoast + " Custo do novo apos do 2");
				break;
			case 3:
				// System.out.println(novo.fullcoast + " Custo do novo antes do 3");
				novo = UXaposV(novo);
				// Verify.clientVerify(novo);
				// demanda = 0;
				// for (int k = 0; k < novo.Deposit_List.size(); k++) {
				// for (int j = 0; j < novo.Deposit_List.get(k).vehicles.Vehicles_List.size();
				// j++) {
				// demanda = 0;
				// for (int n = 0; n <
				// novo.Deposit_List.get(k).vehicles.Vehicles_List.get(j).clients.Client_List
				// .size(); n++) {
				// demanda +=
				// novo.Deposit_List.get(k).vehicles.Vehicles_List.get(j).clients.Client_List
				// .get(n).demand;
				// if (demanda > novo.Deposit_List.get(k).VehicleLoad) {
				// System.out.println("PROBLEMA NO 3");
				// System.out.println(demanda);
				// System.exit(0);
				// } else if (demanda < 0) {
				// System.out.println("PROBLEMA NO 3");
				// System.out.println(demanda);
				// System.exit(0);
				// }
				// }
				// }
				// }
				// System.out.println(novo.fullcoast + " Custo do novo apos do 3");
				break;
			case 4:
				// System.out.println(novo.fullcoast + " Custo do novo antes do 4");
				novo = XUaposV(novo);
				// Verify.clientVerify(novo);
				// demanda = 0;
				// for (int k = 0; k < novo.Deposit_List.size(); k++) {
				// for (int j = 0; j < novo.Deposit_List.get(k).vehicles.Vehicles_List.size();
				// j++) {
				// demanda = 0;
				// for (int n = 0; n <
				// novo.Deposit_List.get(k).vehicles.Vehicles_List.get(j).clients.Client_List
				// .size(); n++) {
				// demanda +=
				// novo.Deposit_List.get(k).vehicles.Vehicles_List.get(j).clients.Client_List
				// .get(n).demand;
				// if (demanda > novo.Deposit_List.get(k).VehicleLoad) {
				// System.out.println("PROBLEMA NO 4");
				// System.out.println(demanda);
				// System.exit(0);
				// } else if (demanda < 0) {
				// System.out.println("PROBLEMA NO 4");
				// System.out.println(demanda);
				// System.exit(0);
				// }
				// }
				// }
				// }
				// System.out.println(novo.fullcoast + " Custo do novo apos do 4");
				break;

			case 5:
				// System.out.println(novo.fullcoast + " Custo do novo antes do 5");
				novo = swapUXtoV(novo);
				// Verify.clientVerify(novo);
				// demanda = 0;
				// for (int k = 0; k < novo.Deposit_List.size(); k++) {
				// for (int j = 0; j < novo.Deposit_List.get(k).vehicles.Vehicles_List.size();
				// j++) {
				// demanda = 0;
				// for (int n = 0; n <
				// novo.Deposit_List.get(k).vehicles.Vehicles_List.get(j).clients.Client_List
				// .size(); n++) {
				// demanda +=
				// novo.Deposit_List.get(k).vehicles.Vehicles_List.get(j).clients.Client_List
				// .get(n).demand;
				// if (demanda > novo.Deposit_List.get(k).VehicleLoad) {
				// System.out.println("PROBLEMA NO 5");
				// System.out.println(demanda);
				// System.exit(0);
				// } else if (demanda < 0) {
				// System.out.println("PROBLEMA NO 5");
				// System.out.println(demanda);
				// System.exit(0);
				// }
				// }
				// }
				// }
				// System.out.println(novo.fullcoast + " Custo do novo apos do 5");
				break;

			case 6:
				// System.out.println(novo.fullcoast + " Custo do novo antes do 6");
				novo = swapUXtoVY(novo);
				// Verify.clientVerify(novo);
				// demanda = 0;
				// for (int k = 0; k < novo.Deposit_List.size(); k++) {
				// for (int j = 0; j < novo.Deposit_List.get(k).vehicles.Vehicles_List.size();
				// j++) {
				// demanda = 0;
				// for (int n = 0; n <
				// novo.Deposit_List.get(k).vehicles.Vehicles_List.get(j).clients.Client_List
				// .size(); n++) {
				// demanda +=
				// novo.Deposit_List.get(k).vehicles.Vehicles_List.get(j).clients.Client_List
				// .get(n).demand;
				// if (demanda > novo.Deposit_List.get(k).VehicleLoad) {
				// System.out.println("PROBLEMA NO 6");
				// System.out.println(demanda);
				// System.exit(0);
				// } else if (demanda < 0) {
				// System.out.println("PROBLEMA NO 6");
				// System.out.println(demanda);
				// System.exit(0);
				// }
				// }
				// }
				// }
				// System.out.println(novo.fullcoast + " Custo do novo apos do 6");
				break;

			case 7:

				// System.out.println(novo.fullcoast + " Custo do novo antes do 7");
				// Se u e v pertencem a mesma rota, trocar os arcos (u, x) e (v, y) por (u, v) e
				// (x, y);
				novo = trocarArcosUXtoVY(novo);
				// Verify.clientVerify(novo);
				// demanda = 0;
				// for (int k = 0; k < novo.Deposit_List.size(); k++) {
				// for (int j = 0; j < novo.Deposit_List.get(k).vehicles.Vehicles_List.size();
				// j++) {
				// demanda = 0;
				// for (int n = 0; n <
				// novo.Deposit_List.get(k).vehicles.Vehicles_List.get(j).clients.Client_List
				// .size(); n++) {
				// demanda +=
				// novo.Deposit_List.get(k).vehicles.Vehicles_List.get(j).clients.Client_List
				// .get(n).demand;
				// if (demanda > novo.Deposit_List.get(k).VehicleLoad) {
				// System.out.println("PROBLEMA NO 7");
				// System.out.println(demanda);
				// System.exit(0);
				// } else if (demanda < 0) {
				// System.out.println("PROBLEMA NO 7");
				// System.out.println(demanda);
				// System.exit(0);
				// }
				// }
				// }
				// }
				// System.out.println(novo.fullcoast + " Custo do novo apos do 7");
				break;
			case 8:

				// System.out.println(novo.fullcoast + " Custo do novo antes do 8");
				// Se u e v pertencem a rotas distintas; trocar os arcos (u, x) e (v, y) por (u,
				// v) e (x, y)
				novo = trocarUXeVYporUVeXY(novo);
				// Verify.clientVerify(novo);
				// demanda = 0;
				// for (int k = 0; k < novo.Deposit_List.size(); k++) {
				// for (int j = 0; j < novo.Deposit_List.get(k).vehicles.Vehicles_List.size();
				// j++) {
				// demanda = 0;
				// for (int n = 0; n <
				// novo.Deposit_List.get(k).vehicles.Vehicles_List.get(j).clients.Client_List
				// .size(); n++) {
				// demanda +=
				// novo.Deposit_List.get(k).vehicles.Vehicles_List.get(j).clients.Client_List
				// .get(n).demand;
				// if (demanda > novo.Deposit_List.get(k).VehicleLoad) {
				// System.out.println("PROBLEMA NO 8");
				// System.out.println(demanda);
				// System.exit(0);
				// } else if (demanda < 0) {
				// System.out.println("PROBLEMA NO 8");
				// System.out.println(demanda);
				// System.exit(0);
				// }
				// }
				// }
				// }
				// System.out.println(novo.fullcoast + " Custo do novo apos do 8");
				break;
			case 9:

				// System.out.println(novo.fullcoast + " Custo do novo antes do 9");
				// Se u e v pertencem a rotas distintas; trocar os arcos (u, x) e (v, y) por (u,
				// v) e (x, y)
				novo = trocarUXeVYporUYeXV(novo);
				// Verify.clientVerify(novo);
				// demanda = 0;
				// for (int k = 0; k < novo.Deposit_List.size(); k++) {
				// for (int j = 0; j < novo.Deposit_List.get(k).vehicles.Vehicles_List.size();
				// j++) {
				// demanda = 0;
				// for (int n = 0; n <
				// novo.Deposit_List.get(k).vehicles.Vehicles_List.get(j).clients.Client_List
				// .size(); n++) {
				// demanda +=
				// novo.Deposit_List.get(k).vehicles.Vehicles_List.get(j).clients.Client_List
				// .get(n).demand;
				// if (demanda > novo.Deposit_List.get(k).VehicleLoad) {
				// System.out.println("PROBLEMA NO 9");
				// System.out.println(demanda);
				// System.exit(0);
				// } else if (demanda < 0) {
				// System.out.println("PROBLEMA NO 9");
				// System.out.println(demanda);
				// System.exit(0);
				// }
				// }
				// }
				// }
				// System.out.println(novo.fullcoast + " Custo do novo apos do 9");
				break;

			} // fim do switch
		} // fim das estrategias

		// for(int i = 0 ; i < novo.Deposit_List.size(); i++) {
		// for(int j=0; j < novo.Deposit_List.get(i).vehicles.Vehicles_List.size(); j++)
		// {
		//
		// if(novo.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.isEmpty()
		// == true) {
		// novo.Deposit_List.get(i).vehicles.Vehicles_List.remove(j);
		// //System.out.println("Estorou demanda");
		// //System.out.println(novo.Deposit_List.get(i).vehicles.Vehicles_List.get(j).vehicleDemand);
		// }
		//
		// }
		// }

		if (novo.fullcoast > dep.fullcoast) {
			// System.out.println("Custo do deposito " + dep.fullcoast);
			// System.out.println("Custo do novo " + novo.fullcoast);
			// System.out.println("não houve melhora");
			return;
		} else {

			//// System.out.println("novo : " + novo.fullcoast);
			Population.newPopulation.remove(dep);
			Population.newPopulation.add(novo.clone());

			anterior = null;
		}
	}

	public static Container_Deposits swapUV(Container_Deposits novo) {
		// anterior = null;

		anterior = novo.clone();
		// while (attemp != tentativas) {

		int rndDep1, rndDep2, u, v, rndVec1, rndVec2;

		rndDep1 = rnd.nextInt(novo.Deposit_List.size());
		rndDep2 = rnd.nextInt(novo.Deposit_List.size());

		rndVec1 = rnd.nextInt(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.size());

		rndVec2 = rnd.nextInt(novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.size());

		if (!(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size() == 0
				&& novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
						.size() == 0)) {
			
			for (u = 0; u < novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
					.size(); u++) {
				// x = u + 1;
				// //System.out.println("U : " + u);
				for (v = 0; v < novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
						.size(); v++) {
					// Verificar demanda
					if (u == v && rndDep1 == rndDep2 && rndVec1 == rndVec2) {

					} else {

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
									.remove(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
											.get(rndVec1).clients.Client_List.get(u));

							novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
									.add(u, aux2.clone());

							novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
									.remove(novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
											.get(rndVec2).clients.Client_List.get(v));
							novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
									.add(v, aux.clone());

							Distribution.funcaoObjetivo(anterior);

							Distribution.funcaoObjetivo(novo);

							if (novo.fullcoast < anterior.fullcoast) {
								//
								// attemp = tentativas;
								// //System.out.println("Custo do anterior " + anterior.fullcoast);
								// //System.out.println("Custo do novo " + novo.fullcoast);
								// //System.out.println("tentativas para melhora " + attemp);
								// break;
								return novo;

							} else {
								//// System.out.println("Sem melhora");
								novo = anterior.clone();
								// attemp++;
							}

						}

						else {
							novo = anterior.clone();
							//// System.out.println("Estorou demanda");
						}
					}
				}
			}
		}

		return novo;

	}

	public static Container_Deposits realocarUV(Container_Deposits novo) {

		anterior = null;
		attemp = 0;
		// Mover um cliente u e realocá-lo após outro cliente v;

		int rndDep1, rndDep2, u, v, rndVec1, rndVec2;

		rndDep1 = rnd.nextInt(novo.Deposit_List.size());
		rndDep2 = rnd.nextInt(novo.Deposit_List.size());

		rndVec1 = rnd.nextInt(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.size());

		rndVec2 = rnd.nextInt(novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.size());

		if ((novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size() > 0
				&& novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List.size() > 0)) {
			
			for (u = 0; u < novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
					.size(); u++) {
				for (v = 0; v < novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
						.size(); v++) {

					// Verificar demanda
					// int demand2=0;
					if (!(u == v && rndDep1 == rndDep2 && rndVec1 == rndVec2)) {

						// demand2 =
						// novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).vehicleDemand
						// +
						// novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
						// .get(u).demand;

						anterior = novo.clone();

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

							// if
							// (novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.isEmpty()
							// == true) {
							// novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.remove(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1));
							// }
							Distribution.funcaoObjetivo(anterior);

							Distribution.funcaoObjetivo(novo);

							if (novo.fullcoast < anterior.fullcoast) {
								//// System.out.println("melhorou");
								return novo;

							} else {
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

		return novo;
	}

	public static Container_Deposits UXaposV(Container_Deposits novo) {
		anterior = null;
		attemp = 0;
		// Mover os clientes u e x para depois do cliente v;

		int rndDep1, rndDep3, u, x, v, rndVec1, rndVec3;

		rndDep1 = rnd.nextInt(novo.Deposit_List.size());

		rndDep3 = rnd.nextInt(novo.Deposit_List.size());

		rndVec1 = rnd.nextInt(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.size());

		rndVec3 = rnd.nextInt(novo.Deposit_List.get(rndDep3).vehicles.Vehicles_List.size());
		if ((novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size() >= 2)
				&& (novo.Deposit_List.get(rndDep3).vehicles.Vehicles_List.get(rndVec3).clients.Client_List
						.size() >= 1)) {
			
			for (u = 0; u < novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
					.size() - 1; u++) {
				x = u + 1;
				// //System.out.println("U : " + u);
				for (v = 0; v < novo.Deposit_List.get(rndDep3).vehicles.Vehicles_List.get(rndVec3).clients.Client_List
						.size(); v++) {
					// Verificar demanda
					// int demand1=0, demand2=0;
					// Verificar demanda
					// int demand3 = 0;
					if (!((u == v || x == v) && rndDep1 == rndDep3 && rndVec1 == rndVec3)) {

						//// System.out.println("RndDep1 " + rndDep1 + " RndDep2 " + rndDep2 + " rndVec1
						//// " + rndVec1 + " rndVec2 " + rndVec2 + " u " + u + " x " + x );

						// demand3 =
						// novo.Deposit_List.get(rndDep3).vehicles.Vehicles_List.get(rndVec3).vehicleDemand
						// +
						// novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.get(u).demand
						// +
						// novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List.get(x).demand;

						// if (demand3 <= novo.Deposit_List.get(rndDep3).VehicleLoad && demand3 > 0 ) {

						anterior = novo.clone();
						novo.Deposit_List.get(rndDep3).vehicles.Vehicles_List
								.get(rndVec3).vehicleDemand += novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
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

						if (novo.Deposit_List.get(rndDep3).vehicles.Vehicles_List
								.get(rndVec3).vehicleDemand <= novo.Deposit_List.get(rndDep3).VehicleLoad
								&& novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
										.get(rndVec1).vehicleDemand <= novo.Deposit_List.get(rndDep1).VehicleLoad
								&& novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).vehicleDemand >= 0
								&& novo.Deposit_List.get(rndDep3).vehicles.Vehicles_List
										.get(rndVec3).vehicleDemand >= 0) {

							Client aux = novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
									.get(rndVec1).clients.Client_List.get(u);

							Client aux2 = novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
									.get(rndVec1).clients.Client_List.get(x);

							// Inserir U e X após V
							int posV = novo.Deposit_List.get(rndDep3).vehicles.Vehicles_List
									.get(rndVec3).clients.Client_List
											.indexOf(novo.Deposit_List.get(rndDep3).vehicles.Vehicles_List
													.get(rndVec3).clients.Client_List.get(v));
							novo.Deposit_List.get(rndDep3).vehicles.Vehicles_List.get(rndVec3).clients.Client_List
									.add(posV + 1, aux.clone());
							novo.Deposit_List.get(rndDep3).vehicles.Vehicles_List.get(rndVec3).clients.Client_List
									.add(posV + 2, aux2.clone());
							// Remover U e X
							novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
									.remove(aux);
							novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
									.remove(aux2);

							// if
							// (novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.isEmpty()
							// == true) {
							// novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.remove(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1));
							// }
							// else {
							// if
							// (novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.isEmpty()
							// == true) {
							// novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.remove(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1));
							// }
							//
							// }

							Distribution.funcaoObjetivo(anterior);
							Distribution.funcaoObjetivo(novo);

							if (novo.fullcoast < anterior.fullcoast) {
								//// System.out.println("melhorou no 3");
								// //System.out.println(" Antes terceiro caso " + anterior.fullcoast);
								// //System.out.println("Após a troca terceiro caso" + novo.fullcoast);
								return novo;
							} else {
								novo = anterior.clone();
								// attemp++;
								// //System.out.println("attemp " + attemp);
							}

						} else {
							novo = anterior.clone();
							//// System.out.println("Estorou demanda no 3");

						}
					}

				}
			}
		}

		return novo;

	}

	public static Container_Deposits XUaposV(Container_Deposits novo) {
		anterior = null;
		attemp = 0;
		// Mover os clientes x e u para depois do cliente v;
		// while (attemp != tentativas) {

		int rndDep1, rndDep3, u, x, v, rndVec1, rndVec3;

		rndDep1 = rnd.nextInt(novo.Deposit_List.size());

		rndDep3 = rnd.nextInt(novo.Deposit_List.size());

		rndVec1 = rnd.nextInt(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.size());

		rndVec3 = rnd.nextInt(novo.Deposit_List.get(rndDep3).vehicles.Vehicles_List.size());

		// while
		// (novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size()
		// < 3) {
		// //// System.out.println("while 1");
		// rndDep1 = rnd.nextInt(novo.Deposit_List.size());
		// rndVec1 =
		// rnd.nextInt(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.size());
		// }
		// while
		// (novo.Deposit_List.get(rndDep3).vehicles.Vehicles_List.get(rndVec3).clients.Client_List.size()
		// < 2) {
		// //// System.out.println("while 2");
		// rndDep3 = rnd.nextInt(novo.Deposit_List.size());
		// rndVec3 =
		// rnd.nextInt(novo.Deposit_List.get(rndDep3).vehicles.Vehicles_List.size());
		// }
		if ((novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size() >= 2)
				&& (novo.Deposit_List.get(rndDep3).vehicles.Vehicles_List.get(rndVec3).clients.Client_List
						.size() >= 1)) {
			
			for (u = 0; u < novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
					.size() - 1; u++) {
				x = u + 1;
				// //System.out.println("U : " + u);
				for (v = 0; v < novo.Deposit_List.get(rndDep3).vehicles.Vehicles_List.get(rndVec3).clients.Client_List
						.size(); v++) {
					// Verificar demanda
					// int demand1=0, demand2=0;
					// Verificar demanda
					// int demand3 = 0;
					if (!((u == v || x == v) && rndDep1 == rndDep3 && rndVec1 == rndVec3)
							&& (novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
									.size() >= 2)
							&& (novo.Deposit_List.get(rndDep3).vehicles.Vehicles_List.get(rndVec3).clients.Client_List
									.size() >= 1)) {

						//// System.out.println("RndDep1 " + rndDep1 + " RndDep2 " + rndDep2 + " rndVec1
						//// " + rndVec1 + " rndVec2 " + rndVec2 + " u " + u + " x " + x );
						//
						// demand3 =
						// novo.Deposit_List.get(rndDep3).vehicles.Vehicles_List.get(rndVec3).vehicleDemand
						// +
						// novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.get(u).demand
						// +
						// novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List.get(x).demand;
						//
						// if (demand3 <= novo.Deposit_List.get(rndDep3).VehicleLoad && demand3 > 0 ) {

						anterior = novo.clone();
						novo.Deposit_List.get(rndDep3).vehicles.Vehicles_List
								.get(rndVec3).vehicleDemand += novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
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

						if (novo.Deposit_List.get(rndDep3).vehicles.Vehicles_List
								.get(rndVec3).vehicleDemand <= novo.Deposit_List.get(rndDep3).VehicleLoad
								&& novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
										.get(rndVec1).vehicleDemand <= novo.Deposit_List.get(rndDep1).VehicleLoad
								&& novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).vehicleDemand >= 0
								&& novo.Deposit_List.get(rndDep3).vehicles.Vehicles_List
										.get(rndVec3).vehicleDemand >= 0) {

							Client aux = novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
									.get(rndVec1).clients.Client_List.get(u);

							Client aux2 = novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
									.get(rndVec1).clients.Client_List.get(x);

							// Inserir U e X após V
							int posV = novo.Deposit_List.get(rndDep3).vehicles.Vehicles_List
									.get(rndVec3).clients.Client_List
											.indexOf(novo.Deposit_List.get(rndDep3).vehicles.Vehicles_List
													.get(rndVec3).clients.Client_List.get(v));

							novo.Deposit_List.get(rndDep3).vehicles.Vehicles_List.get(rndVec3).clients.Client_List
									.add(posV + 1, aux2.clone());
							novo.Deposit_List.get(rndDep3).vehicles.Vehicles_List.get(rndVec3).clients.Client_List
									.add(posV + 2, aux.clone());
							// Remover U e X
							novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
									.remove(aux2);
							novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
									.remove(aux);

							// if
							// (novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.isEmpty()
							// == true) {
							// novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.remove(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1));
							//
							// }
							// else {
							// if
							// (novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.isEmpty()
							// == true) {
							// novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.remove(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1));
							// }
							// }

							Distribution.funcaoObjetivo(anterior);
							Distribution.funcaoObjetivo(novo);

							if (novo.fullcoast < anterior.fullcoast) {
								//// System.out.println("melhorou no 3");
								// //System.out.println(" Antes terceiro caso " + anterior.fullcoast);
								// //System.out.println("Após a troca terceiro caso" + novo.fullcoast);
								return novo;
							} else {
								novo = anterior.clone();
								// attemp++;
								// //System.out.println("attemp " + attemp);
							}

						} else {
							novo = anterior.clone();
							//// System.out.println("Estorou demanda no 3");

						}
					}

				}
			}
		}
		return novo;

	}

	public static Container_Deposits swapUXtoV(Container_Deposits novo) {
		anterior = novo.clone();
		// Trocar as posições dos clientes u e x pela de v;
		attemp = 0;

		int rndDep1, rndDep2, u, x, v, rndVec1, rndVec2;

		rndDep1 = rnd.nextInt(novo.Deposit_List.size());

		rndDep2 = rnd.nextInt(novo.Deposit_List.size());

		rndVec1 = rnd.nextInt(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.size());

		rndVec2 = rnd.nextInt(novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.size());
//System.out.println(novo.Deposit_List.get(rndVec2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List.size());

		// aq
		if ((novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size() >= 2)
				&& (novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List.size() >= 1)) {
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

							// int posV
							// =novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List.indexOf(novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List.get(v));
							// int posU
							// =novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.indexOf(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.get(u));
							//

							novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
									.add(v, aux.clone());
							novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
									.add(v + 1, aux2.clone());
							novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
									.add(u, aux3.clone());

							novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
									.remove(aux);
							novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
									.remove(aux2);
							novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
									.remove(aux3);

							// if
							// (novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.isEmpty()
							// == true) {
							// novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.remove(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1));
							// }
							// if
							// (novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List.isEmpty()
							// == true) {
							// novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.remove(novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2));
							// }

							Distribution.funcaoObjetivo(anterior);
							Distribution.funcaoObjetivo(novo);

							if (novo.fullcoast < anterior.fullcoast) {
								//// System.out.println("melhorou no 5");
								//// System.out.println("Custo do anterior " + anterior.fullcoast);
								//// System.out.println("Custo do novo " + novo.fullcoast);
								//// System.out.println("tentativas para melhora " + attemp);

								return novo;
							} else {
								//// System.out.println("else");
								novo = anterior.clone();
								// attemp++;
								//// System.out.println("Attemp " + attemp);
							}
						} else {
							novo = anterior.clone();
							//// System.out.println("Estorou demanda");
						}

					}
				}
			}
		}

		return novo;
	}

	public static Container_Deposits swapUXtoVY(Container_Deposits novo) {
		anterior = null;
		// Trocar as posições dos clientes u e x pela de v e y;
		attemp = 0;
		// aq

		int rndDep1, rndDep2, u, x, v, y, rndVec1, rndVec2;

		rndDep1 = rnd.nextInt(novo.Deposit_List.size());

		rndDep2 = rnd.nextInt(novo.Deposit_List.size());

		rndVec1 = rnd.nextInt(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.size());

		rndVec2 = rnd.nextInt(novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.size());

		// while
		// (novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size()
		// < 4) {
		//
		// //// System.out.println("while 6");
		// rndDep1 = rnd.nextInt(novo.Deposit_List.size());
		// rndVec1 =
		// rnd.nextInt(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.size());
		//
		// }
		//
		// while
		// (novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List.size()
		// < 4) {
		// //// System.out.println("while 6.2");
		// rndDep2 = rnd.nextInt(novo.Deposit_List.size());
		// rndVec2 =
		// rnd.nextInt(novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.size());
		//
		// }

		// aq
		if ((novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size() >= 4)
				&& (novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
						.size() >= 4)) {
			
			for (u = 0; u < novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
					.size() - 1; u++) {
				x = u + 1;
				// //System.out.println("U : " + u);
				for (v = 0; v < novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
						.size() - 1; v++) {
					y = v + 1;
					// int demand3=0, demand1=0;
					if (!(((u == v || x == v) || (u == y || x == y)) && rndDep1 == rndDep2 && rndVec1 == rndVec2)) {

						anterior = novo.clone();
						novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
								.get(rndVec2).vehicleDemand += novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
										.get(rndVec1).clients.Client_List.get(u).demand
										+ novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
												.get(rndVec1).clients.Client_List.get(x).demand
										- novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
												.get(rndVec2).clients.Client_List.get(v).demand
										- novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
												.get(rndVec2).clients.Client_List.get(y).demand;

						novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List
								.get(rndVec1).vehicleDemand += +novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List
										.get(rndVec2).clients.Client_List.get(y).demand
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

							// //System.out.println( "Demanda1 após " +
							// novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).vehicleDemand
							// + " Demand 2 após " +
							// +
							// novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).vehicleDemand);

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

							novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
									.add(posV, aux2.clone());
							novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List
									.add(posV + 1, aux.clone());
							novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
									.add(posU, aux3.clone());
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

							// if
							// (novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.isEmpty()
							// == true) {
							// novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.remove(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1));
							// }
							// if
							// (novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List.isEmpty()
							// == true) {
							// novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.remove(novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2));
							// }

							Distribution.funcaoObjetivo(anterior);
							Distribution.funcaoObjetivo(novo);

							if (novo.fullcoast < anterior.fullcoast) {
								//// System.out.println("melhorou no 6");
								//// System.out.println("Custo do anterior " + anterior.fullcoast);
								//// System.out.println("Custo do novo " + novo.fullcoast);
								//// System.out.println("tentativas para melhora " + attemp);

								return novo;
							} else {
								//// System.out.println("else");
								novo = anterior.clone();
								// attemp++;
								//// System.out.println("Attemp " + attemp);
							}
						} else {
							novo = anterior.clone();
							//// System.out.println("Estorou demanda");
						}

					}
				}
			}
		}

		return novo;
	}

	public static Container_Deposits trocarArcosUXtoVY(Container_Deposits novo) {
		// //System.out.println("caso 7");
		anterior = null;
		attemp = 0;

		anterior = novo.clone();

		int rndDep1, rndVec1;

		rndDep1 = rnd.nextInt(novo.Deposit_List.size());

		rndVec1 = rnd.nextInt(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.size());

		//// System.out.println("tamanho depois do while " +
		//// novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size());
		if (novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size() >= 4) {
			
			for (int u = 0; u < novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
					.size() - 3; u++) {
				int x = u + 1;
				// //System.out.println("U : " + u);
				for (int v = x
						+ 1; v < novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
								.size() - 1; v++) {
					int y = v + 1;

					// //System.out.println("tamanho " +
					// novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size()
					// + " V " + v + " Y " + y);
					// //System.out.println("Antes do reverse");
					// for (int k = 0 ; k
					// <novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size();
					// k ++ ) {
					// //System.out.println(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.get(k).clientNumber);
					// }

					Collections.reverse(
							novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List
									.subList(x, y));

					// //System.out.println("Depois do reverse");
					// for (int k = 0 ; k
					// <novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size();
					// k ++ ) {
					// //System.out.println(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.get(k).clientNumber);
					// }
					Distribution.funcaoObjetivo(anterior);
					Distribution.funcaoObjetivo(novo);

					if (novo.fullcoast < anterior.fullcoast) {
						//// System.out.println("melhorou no 7");
						//// System.out.println("Custo do anterior " + anterior.fullcoast);
						//// System.out.println("Custo do novo " + novo.fullcoast);
						//// System.out.println("tentativas para melhora " + attemp);

						return novo;
					} else {
						// //System.out.println("else");
						novo = anterior.clone();
						// attemp++;
						//// System.out.println("Attemp " + attemp);
					}
				}
			}
		}

		return novo;

	}

	public static Container_Deposits trocarUXeVYporUVeXY(Container_Deposits novo) {
		anterior = null;
		attemp = 0;
		//// System.out.println(attemp);
		int demandU = 0, demandV = 0;
		anterior = novo.clone();

		int rndDep1, rndVec1, rndDep2, rndVec2;

		Container_Client novoU = new Container_Client();

		Container_Client novoV = new Container_Client();

		rndDep1 = rnd.nextInt(novo.Deposit_List.size());
		rndDep2 = rnd.nextInt(novo.Deposit_List.size());

		rndVec1 = rnd.nextInt(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.size());
		rndVec2 = rnd.nextInt(novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.size());

		if (novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size() >= 4
				&& novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List.size() >= 4) {
			

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

					// Collections.reverse(novoV.Client_List);

					for (int i = 0; i < novoU.Client_List.size(); i++) {

						demandU += novoU.Client_List.get(i).demand;

					}

					for (int i = 0; i < novoV.Client_List.size(); i++) {

						demandV += novoV.Client_List.get(i).demand;

					}

					if (demandU <= novo.Deposit_List.get(rndDep1).VehicleLoad
							&& demandV <= novo.Deposit_List.get(rndDep2).VehicleLoad && demandU > 0 && demandV > 0) {
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

						if (novo.fullcoast < anterior.fullcoast) {
							//// System.out.println("melhorou no 9");
							//// System.out.println("Custo do anterior " + anterior.fullcoast);
							//// System.out.println("Custo do novo " + novo.fullcoast);
							//// System.out.println("tentativas para melhora " + attemp);

							return novo;
						} else {
							//// System.out.println("else");
							novo = anterior.clone();
							// attemp++;
							////// System.out.println("Attemp " + attemp);
						}
					} else {
						//// System.out.println("Estorou demanda");
					}
				}
			}
		}
		//// System.out.println("Não houve melhora");
		return novo;
	}

	public static Container_Deposits trocarUXeVYporUYeXV(Container_Deposits novo) {
		anterior = null;
		attemp = 0;
		//// System.out.println(attemp);
		int demandU = 0, demandV = 0;
		anterior = novo.clone();

		int rndDep1, rndVec1, rndDep2, rndVec2;

		Container_Client novoU = new Container_Client();

		Container_Client novoV = new Container_Client();

		rndDep1 = rnd.nextInt(novo.Deposit_List.size());
		rndDep2 = rnd.nextInt(novo.Deposit_List.size());

		// while(rndDep1 == rndDep2) {
		// rndDep2 = rnd.nextInt(novo.Deposit_List.size());
		// }
		//
		rndVec1 = rnd.nextInt(novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.size());
		rndVec2 = rnd.nextInt(novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.size());

		if (novo.Deposit_List.get(rndDep1).vehicles.Vehicles_List.get(rndVec1).clients.Client_List.size() >= 4
				&& novo.Deposit_List.get(rndDep2).vehicles.Vehicles_List.get(rndVec2).clients.Client_List.size() >= 4) {
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

					// Collections.reverse(novoV.Client_List);

					for (int i = 0; i < novoU.Client_List.size(); i++) {

						demandU += novoU.Client_List.get(i).demand;

					}

					for (int i = 0; i < novoV.Client_List.size(); i++) {

						demandV += novoV.Client_List.get(i).demand;

					}

					if (demandU <= novo.Deposit_List.get(rndDep1).VehicleLoad
							&& demandV <= novo.Deposit_List.get(rndDep2).VehicleLoad && demandU > 0 && demandV > 0) {
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

						if (novo.fullcoast < anterior.fullcoast) {
							//// System.out.println("melhorou no 9");
							//// System.out.println("Custo do anterior " + anterior.fullcoast);
							//// System.out.println("Custo do novo " + novo.fullcoast);
							//// System.out.println("tentativas para melhora " + attemp);

							return novo;
						} else {
							//// System.out.println("else");
							novo = anterior.clone();
							// attemp++;
							//// System.out.println("Attemp " + attemp);
						}
					} else {
						//// System.out.println("Estorou demanda");
					}
				}
			}
		}

		//// System.out.println("Não houve melhora");
		return novo;
	}

}