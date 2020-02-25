package mdvrp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import mdvrp.problem.Problem;

public class Main {

	public static String parametrizacao;
	public static String instance;
	public static int mu;
	public static int lambda;
	public static int ngen = 0;
	public static double pbl;
	public static double pbm;
	public static double pblBest;

	public static void main(String[] args) throws IOException, CloneNotSupportedException {
	
		
		// Problem problem = new Problem(instance);

		// problem.readParameterization();
		// problem.readFile();
		// Distribution.euclidianDistanceClients(problem.clients);
		// //Distribution.showDistanceCLientstoClients();
		// Distribution.euclidianClientToDeposit(problem.clients, problem.deposits);
		// EvolutionEstrategy.method(problem.clients, problem.deposits);

		int repeticoes = 10;

		// Casos de teste
		// p01 até a p23
		ArrayList<String> nomes = new ArrayList<>(Arrays.asList("P01", "P02", "P03", "P04", "P05", "P06", "P07", "P08","P09",
				"P10", "P11", "P12", "P13", "P14", "P15", "P16", "P17", "P18", "P19", "P20", "P21", "P22", "P23"));
		for (int i = 1; i <= repeticoes; i++) {
			ArrayList<Integer> casos = new ArrayList<>(Arrays.asList(1));
//			ArrayList<Integer> casos = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,12,15,18,21));
//			ArrayList<Integer> casos = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23));
			Collections.shuffle(casos);

			for (int c = 1; c <= casos.size(); c++) {

				Double result = 0.0;
				long startTime = System.currentTimeMillis();

				int teste = casos.get(c - 1);

				switch (teste) {

				case 1: 
					mu = 5; // Tamanho da populacao
					lambda = 20; // numero de descendentes a
					pbm = 0.7; // mutacao - aplicacao ao descendente - variacao/pertubacao
					pbl = 0.1; 
					pblBest = 0.0;
					ngen = 1500;
					instance = "p01.txt";
					
					break;

				case 2: 
					mu = 2; // Tamanho da populacao
					lambda = 10; // numero de descendentes a
					pbm =  0.1; // mutacao - aplicacao ao descendente - variacao/pertubacao
					pbl = 0.7;
					pblBest = 0.5;
					ngen = 3018;
					instance = "p02.txt";

					break;

				case 3: // Mutação por Variaveis
					mu = 2; // Tamanho da populacao
					lambda = 10; // numero de descendentes a
					pbm = 0.1; // mutacao - aplicacao ao descendente - variacao/pertubacao
					pbl = 0.7;
					pblBest = 0.5;
					ngen = 3018;
					instance = "p03.txt";

					break;
				case 4: // Mutação por Variaveis
					mu = 2; // Tamanho da populacao
					lambda = 10; // numero de descendentes a
					pbm = 0.1; // mutacao - aplicacao ao descendente - variacao/pertubacao
					pbl = 0.7;
					pblBest = 0.5;
					ngen = 3018;
					instance = "p04.txt";

					break;
				case 5: // Mutação por Variaveis
					mu = 2; // Tamanho da populacao
					lambda = 10; // numero de descendentes a
					pbm = 0.1; // mutacao - aplicacao ao descendente - variacao/pertubacao
					pbl = 0.7; 
					pblBest = 0.5;
					ngen = 1500;
					instance = "p05.txt";

					break;
				case 6: // Mutação por Variaveis
					mu = 2; // Tamanho da populacao
					lambda = 10; // numero de descendentes a
					pbm = 0.1; // mutacao - aplicacao ao descendente - variacao/pertubacao
					pbl = 0.7;
					pblBest = 0.5;
					ngen = 3018;
					instance = "p06.txt";

					break;
				case 7: // Mutação por Variaveis
					mu = 2; // Tamanho da populacao
					lambda = 10; // numero de descendentes a
					pbm = 0.1; // mutacao - aplicacao ao descendente - variacao/pertubacao
					pbl = 0.7;
					pblBest = 0.5;
					ngen = 3018;
					instance = "p07.txt";

					break;
				case 8: // Mutação por Variaveis
					mu = 2; // Tamanho da populacao
					lambda = 10; // numero de descendentes a
					pbm = 0.1; // mutacao - aplicacao ao descendente - variacao/pertubacao
					pbl = 0.7;
					pblBest = 0.6;
					ngen = 3018;
					instance = "p08.txt";

					break;
				case 9: // Mutação por Variaveis
					mu = 2; // Tamanho da populacao
					lambda = 10; // numero de descendentes a
					pbm = 0.1; // mutacao - aplicacao ao descendente - variacao/pertubacao
					pbl = 0.7;
					pblBest = 0.6;
					ngen = 3018;
					instance = "p09.txt";

					break;
				case 10: // Mutação por Variaveis
					mu = 2; // Tamanho da populacao
					lambda =100; // numero de descendentes a
					pbm = 0.1; // mutacao - aplicacao ao descendente - variacao/pertubacao
					pbl = 0.7;
					pblBest = 0.5;
					ngen = 3018;
					instance = "p10.txt";

					break;
				case 11: // Mutação por Variaveis
					mu = 2; // Tamanho da populacao
					lambda = 10; // numero de descendentes a
					pbm = 0.1; // mutacao - aplicacao ao descendente - variacao/pertubacao
					pbl = 0.7;
					pblBest = 0.5;
					ngen = 3018;
					instance = "p11.txt";

					break;
				case 12: // Mutação por Variaveis
					mu = 2; // Tamanho da populacao
					lambda = 10; // numero de descendentes a
					pbm = 0.1; // mutacao - aplicacao ao descendente - variacao/pertubacao
					pbl = 0.7;
					pblBest = 0.5;
					ngen = 3018;
					instance = "p12.txt";

					break;
				case 13: // Mutação por Variaveis
					mu = 2; // Tamanho da populacao
					lambda =100; // numero de descendentes a
					pbm = 0.1; // mutacao - aplicacao ao descendente - variacao/pertubacao
					pbl = 0.7;
					pblBest = 0.5;
					ngen = 3018;
					instance = "p13.txt";

					break;
				case 14: // Mutação por Variaveis
					mu = 5; // Tamanho da populacao
					lambda = 10; // numero de descendentes a
					pbm = 0.1; // mutacao - aplicacao ao descendente - variacao/pertubacao
					pbl = 0.7;
					pblBest = 0.5;
					ngen = 3018;
					instance = "p14.txt";

					break;
				case 15: // Mutação por Variaveis
					mu = 2; // Tamanho da populacao
					lambda = 10; // numero de descendentes a
					pbm = 0.1; // mutacao - aplicacao ao descendente - variacao/pertubacao
					pbl = 0.7;
					pblBest = 0.5;
					ngen = 3018;
					instance = "p15.txt";

					break;
				case 16: // Mutação por Variaveis
					mu = 2; // Tamanho da populacao
					lambda = 10; // numero de descendentes a
					pbm = 0.1; // mutacao - aplicacao ao descendente - variacao/pertubacao
					pbl = 0.7;
					pblBest = 0.5;
					ngen = 3018;
					instance = "p16.txt";

					break;
				case 17: // Mutação por Variaveis
					mu = 2; // Tamanho da populacao
					lambda = 10; // numero de descendentes a
					pbm = 0.1; // mutacao - aplicacao ao descendente - variacao/pertubacao
					pbl = 0.8;
					pblBest = 0.5;
					ngen = 3018;
					instance = "p17.txt";

					break;
				case 18: // Mutação por Variaveis
					mu = 2; // Tamanho da populacao
					lambda = 10; // numero de descendentes a
					pbm = 0.1; // mutacao - aplicacao ao descendente - variacao/pertubacao
					pbl = 0.7;
					pblBest = 0.5;
					ngen = 3018;
					instance = "p18.txt";

					break;
				case 19: // Mutação por Variaveis
					mu = 2; // Tamanho da populacao
					lambda = 10; // numero de descendentes a
					pbm = 0.1; // mutacao - aplicacao ao descendente - variacao/pertubacao
					pbl = 0.7;
					pblBest = 0.5;
					ngen = 3018;
					instance = "p19.txt";

					break;
				case 20: // Mutação por Variaveis
					mu = 2; // Tamanho da populacao
					lambda = 10; // numero de descendentes a
					pbm = 0.1; // mutacao - aplicacao ao descendente - variacao/pertubacao
					pbl = 0.7;
					pblBest = 0.6;
					ngen = 3018;
					instance = "p20.txt";

					break;
				case 21: // Mutação por Variaveis
					mu = 2; // Tamanho da populacao
					lambda = 10; // numero de descendentes a
					pbm = 0.1; // mutacao - aplicacao ao descendente - variacao/pertubacao
					pbl = 0.7; 
					pblBest = 0.5;
					ngen = 1500;
					instance = "p21.txt";

					break;
				case 22: // Mutação por Variaveis
					mu = 2; // Tamanho da populacao
					lambda = 10; // numero de descendentes a
					pbm = 0.1; // mutacao - aplicacao ao descendente - variacao/pertubacao
					pbl = 0.7;
					pblBest = 0.6;
					ngen = 3018;
					instance = "p22.txt";

					break;
				case 23: // Mutação por Variaveis
					mu = 2; // Tamanho da populacao
					lambda = 10; // numero de descendentes a
					pbm = 0.1; // mutacao - aplicacao ao descendente - variacao/pertubacao
					pbl = 0.7;
					pblBest = 0.6;
					ngen = 3018;
					instance = "p23.txt";

					break;

				}

				Problem problem = new Problem(instance, ngen, mu, lambda, pbm, pbl, pblBest);
				problem.readFile();

				result = problem.result;

				// result = esReal.executar().getFuncaoObjetivo();
				// }

				long endTime = System.currentTimeMillis();
				long totalTime = endTime - startTime;
				// aq
//				FileWriter arquivo = new FileWriter( "C:\\Users\\wagne\\OneDrive\\Wagner - UFOP\\Projeto\\Github\\17NoTime.txt",true);
//				FileWriter arquivo = new FileWriter( "D:\\OneDrive\\Wagner - UFOP\\TCC\\MDVRP.txt",true);
//				FileWriter arquivo = new FileWriter( "C:\\Users\\LaIC\\Documents\\MDVRPOneHour.txt",true);
//				FileWriter arquivo = new FileWriter( "D:\\OneDrive\\Wagner - UFOP\\TCC\\MDVRP-NewMutation.txt",true);
				FileWriter arquivo = new FileWriter( "D:\\OneDrive\\Wagner - UFOP\\11º Período\\Avaliação de Desempenho de Sistemas\\TP\\Teste 1.txt",true);
				
				PrintWriter pw =  new PrintWriter(arquivo );
				int k = i;
				String resultado = (nomes.get(teste - 1) + ";" + k + ";" + result  + ";" + totalTime +";" + problem.geracaoDeParada);
				System.out.println(resultado);			
				pw.println(resultado);
				pw.flush();
				pw.close();
//				ShowSolution.show(Population.populationDeposits.get(0), (nomes.get(teste - 1)) , k );
			}
		 
			 

		}
		System.exit(0);
	}

	public static void escritor(String resultado) throws IOException {
		String path = "D:\\OneDrive\\Wagner - UFOP\\Projeto\\Github\\file.txt";
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		buffWrite.append(resultado + "\n");
		buffWrite.close();
	}

	// Container_Deposits novo = Distribution.teste(problem.deposits,
	// problem.clients);
	// System.out.println(novo.fullcoast);
	// for (int j = 0; j < novo.Deposit_List.size(); j++) {
	//
	// System.out.println("Depósito " + j);
	// for (int z = 0; z < novo.Deposit_List.get(j).vehicles.Vehicles_List.size();
	// z++) {
	//
	// System.out.println("Veículo " + z + " Custo: " +
	// novo.Deposit_List.get(j).vehicles.Vehicles_List.get(z).coast);
	// System.out.println("Demanda " +
	// novo.Deposit_List.get(j).vehicles.Vehicles_List.get(z).vehicleDemand);
	//
	// System.out.println(
	// novo.Deposit_List.get(j).vehicles.Vehicles_List.get(z).clients.toString());
	// }
	// }
	// ShowSolution.show(novo);

	// Distribution.showDistanceCLientstoDeposits();
	// Distribution.DistributionClients(problem.clients, problem.deposits);
	//
	// Distribution.DistributionClients(problem.clients, problem.deposits);
	//
	// System.out.println("Primeira Solução");
	// System.out.println(" Depósito 1");
	// for (int i = 0; i <
	// Population.populationDeposits.get(0).Deposit_List.get(0).vehicles.Vehicles_List.size();
	// i++) {
	//
	// System.out.println("Demanda " +
	// Population.populationDeposits.get(0).Deposit_List.get(0).vehicles.Vehicles_List.get(i).vehicleDemand);
	// System.out.println(Population.populationDeposits.get(0).Deposit_List.get(0).vehicles.Vehicles_List.get(i).clients.toString());
	// }
	// System.out.println(" Depósito 2");
	//
	// for (int i = 0; i <
	// Population.populationDeposits.get(0).Deposit_List.get(1).vehicles.Vehicles_List.size();
	// i++) {
	//
	// System.out.println("Demanda " +
	// Population.populationDeposits.get(0).Deposit_List.get(1).vehicles.Vehicles_List.get(i).vehicleDemand);
	// System.out.println(Population.populationDeposits.get(0).Deposit_List.get(1).vehicles.Vehicles_List.get(i).clients.toString());
	// }
	// System.out.println(" Depósito 3");
	//
	// for (int i = 0; i <
	// Population.populationDeposits.get(0).Deposit_List.get(2).vehicles.Vehicles_List.size();
	// i++) {
	//
	// System.out.println("Demanda " +
	// Population.populationDeposits.get(0).Deposit_List.get(2).vehicles.Vehicles_List.get(i).vehicleDemand);
	// System.out.println(Population.populationDeposits.get(0).Deposit_List.get(2).vehicles.Vehicles_List.get(i).clients.toString());
	// }
	//
	// System.out.println(" Depósito 4");
	// for (int i = 0; i <
	// Population.populationDeposits.get(0).Deposit_List.get(3).vehicles.Vehicles_List.size();
	// i++) {
	//
	// System.out.println("Demanda " +
	// Population.populationDeposits.get(0).Deposit_List.get(3).vehicles.Vehicles_List.get(i).vehicleDemand);
	// System.out.println(Population.populationDeposits.get(0).Deposit_List.get(3).vehicles.Vehicles_List.get(i).clients.toString());
	// }
	//
	//
	//
	// System.out.println("Solução 2");
	// System.out.println(" Depósito 1");
	// for (int i = 0; i <
	// Population.populationDeposits.get(1).Deposit_List.get(0).vehicles.Vehicles_List.size();
	// i++) {
	//
	// System.out.println("Demanda " +
	// Population.populationDeposits.get(1).Deposit_List.get(0).vehicles.Vehicles_List.get(i).vehicleDemand);
	// System.out.println(Population.populationDeposits.get(1).Deposit_List.get(0).vehicles.Vehicles_List.get(i).clients.toString());
	// }
	//
	// System.out.println(" Depósito 2");
	// for (int i = 0; i <
	// Population.populationDeposits.get(1).Deposit_List.get(1).vehicles.Vehicles_List.size();
	// i++) {
	//
	// System.out.println("Demanda " +
	// Population.populationDeposits.get(1).Deposit_List.get(1).vehicles.Vehicles_List.get(i).vehicleDemand);
	// System.out.println(Population.populationDeposits.get(1).Deposit_List.get(1).vehicles.Vehicles_List.get(i).clients.toString());
	// }
	// System.out.println(" Depósito 3");
	// for (int i = 0; i <
	// Population.populationDeposits.get(1).Deposit_List.get(2).vehicles.Vehicles_List.size();
	// i++) {
	//
	// System.out.println("Demanda " +
	// Population.populationDeposits.get(1).Deposit_List.get(2).vehicles.Vehicles_List.get(i).vehicleDemand);
	// System.out.println(Population.populationDeposits.get(1).Deposit_List.get(2).vehicles.Vehicles_List.get(i).clients.toString());
	// }
	// System.out.println(" Depósito 4");
	// for (int i = 0; i <
	// Population.populationDeposits.get(1).Deposit_List.get(3).vehicles.Vehicles_List.size();
	// i++) {
	//
	// System.out.println("Demanda " +
	// Population.populationDeposits.get(1).Deposit_List.get(3).vehicles.Vehicles_List.get(i).vehicleDemand);
	// System.out.println(Population.populationDeposits.get(1).Deposit_List.get(3).vehicles.Vehicles_List.get(i).clients.toString());
	// }
	//

	// problem.FuncaoObjetivo(Solutions.solutionsDeposits.get(0).Deposit_List.get(0),Solutions.solutionsDeposits.get(0).Deposit_List.get(0).vehicles.Vehicles_List.get(0));

	/*
	 * problem.deposits.Deposit_List.get(0).vehicles.Vehicles_List.get(0).clients.
	 * Client_List .add(problem.clients.Client_List.get(43));
	 * problem.deposits.Deposit_List.get(0).vehicles.Vehicles_List.get(0).clients.
	 * Client_List .add(problem.clients.Client_List.get(44));
	 * problem.deposits.Deposit_List.get(0).vehicles.Vehicles_List.get(0).clients.
	 * Client_List .add(problem.clients.Client_List.get(32));
	 * problem.deposits.Deposit_List.get(0).vehicles.Vehicles_List.get(0).clients.
	 * Client_List .add(problem.clients.Client_List.get(14));
	 * problem.deposits.Deposit_List.get(0).vehicles.Vehicles_List.get(0).clients.
	 * Client_List .add(problem.clients.Client_List.get(36));
	 * problem.deposits.Deposit_List.get(0).vehicles.Vehicles_List.get(0).clients.
	 * Client_List .add(problem.clients.Client_List.get(16));
	 * 
	 * problem.deposits.Deposit_List.get(0).vehicles.Vehicles_List.get(1).clients.
	 * Client_List .add(problem.clients.Client_List.get(41));
	 * problem.deposits.Deposit_List.get(0).vehicles.Vehicles_List.get(1).clients.
	 * Client_List .add(problem.clients.Client_List.get(18));
	 * problem.deposits.Deposit_List.get(0).vehicles.Vehicles_List.get(1).clients.
	 * Client_List .add(problem.clients.Client_List.get(39));
	 * problem.deposits.Deposit_List.get(0).vehicles.Vehicles_List.get(1).clients.
	 * Client_List .add(problem.clients.Client_List.get(40));
	 * problem.deposits.Deposit_List.get(0).vehicles.Vehicles_List.get(1).clients.
	 * Client_List .add(problem.clients.Client_List.get(12));
	 * 
	 * problem.deposits.Deposit_List.get(0).vehicles.Vehicles_List.get(2).clients.
	 * Client_List .add(problem.clients.Client_List.get(24));
	 * problem.deposits.Deposit_List.get(0).vehicles.Vehicles_List.get(2).clients.
	 * Client_List .add(problem.clients.Client_List.get(17));
	 * problem.deposits.Deposit_List.get(0).vehicles.Vehicles_List.get(2).clients.
	 * Client_List .add(problem.clients.Client_List.get(3));
	 * 
	 * problem.vehicleEuclideanDistance(problem.deposits.Deposit_List.get(0),
	 * problem.deposits.Deposit_List.get(0).vehicles.Vehicles_List.get(0));
	 * problem.vehicleEuclideanDistance(problem.deposits.Deposit_List.get(0),
	 * problem.deposits.Deposit_List.get(0).vehicles.Vehicles_List.get(1));
	 * problem.vehicleEuclideanDistance(problem.deposits.Deposit_List.get(0),
	 * problem.deposits.Deposit_List.get(0).vehicles.Vehicles_List.get(2));
	 * 
	 * problem.deposits.Deposit_List.get(1).vehicles.Vehicles_List.get(0).clients.
	 * Client_List .add(problem.clients.Client_List.get(5));
	 * problem.deposits.Deposit_List.get(1).vehicles.Vehicles_List.get(0).clients.
	 * Client_List .add(problem.clients.Client_List.get(26));
	 * problem.deposits.Deposit_List.get(1).vehicles.Vehicles_List.get(0).clients.
	 * Client_List .add(problem.clients.Client_List.get(0));
	 * problem.deposits.Deposit_List.get(1).vehicles.Vehicles_List.get(0).clients.
	 * Client_List .add(problem.clients.Client_List.get(31));
	 * problem.deposits.Deposit_List.get(1).vehicles.Vehicles_List.get(0).clients.
	 * Client_List .add(problem.clients.Client_List.get(10));
	 * problem.deposits.Deposit_List.get(1).vehicles.Vehicles_List.get(0).clients.
	 * Client_List .add(problem.clients.Client_List.get(45));
	 * 
	 * problem.deposits.Deposit_List.get(1).vehicles.Vehicles_List.get(1).clients.
	 * Client_List .add(problem.clients.Client_List.get(47));
	 * problem.deposits.Deposit_List.get(1).vehicles.Vehicles_List.get(1).clients.
	 * Client_List .add(problem.clients.Client_List.get(7));
	 * problem.deposits.Deposit_List.get(1).vehicles.Vehicles_List.get(1).clients.
	 * Client_List .add(problem.clients.Client_List.get(25));
	 * problem.deposits.Deposit_List.get(1).vehicles.Vehicles_List.get(1).clients.
	 * Client_List .add(problem.clients.Client_List.get(30));
	 * problem.deposits.Deposit_List.get(1).vehicles.Vehicles_List.get(1).clients.
	 * Client_List .add(problem.clients.Client_List.get(27));
	 * problem.deposits.Deposit_List.get(1).vehicles.Vehicles_List.get(1).clients.
	 * Client_List .add(problem.clients.Client_List.get(21));
	 * 
	 * problem.deposits.Deposit_List.get(1).vehicles.Vehicles_List.get(2).clients.
	 * Client_List .add(problem.clients.Client_List.get(22));
	 * problem.deposits.Deposit_List.get(1).vehicles.Vehicles_List.get(2).clients.
	 * Client_List .add(problem.clients.Client_List.get(6));
	 * problem.deposits.Deposit_List.get(1).vehicles.Vehicles_List.get(2).clients.
	 * Client_List .add(problem.clients.Client_List.get(42));
	 * problem.deposits.Deposit_List.get(1).vehicles.Vehicles_List.get(2).clients.
	 * Client_List .add(problem.clients.Client_List.get(23));
	 * problem.deposits.Deposit_List.get(1).vehicles.Vehicles_List.get(2).clients.
	 * Client_List .add(problem.clients.Client_List.get(13));
	 * 
	 * problem.deposits.Deposit_List.get(1).vehicles.Vehicles_List.get(3).clients.
	 * Client_List .add(problem.clients.Client_List.get(11));
	 * problem.deposits.Deposit_List.get(1).vehicles.Vehicles_List.get(3).clients.
	 * Client_List .add(problem.clients.Client_List.get(46));
	 * 
	 * problem.vehicleEuclideanDistance(problem.deposits.Deposit_List.get(1),
	 * problem.deposits.Deposit_List.get(1).vehicles.Vehicles_List.get(0));
	 * problem.vehicleEuclideanDistance(problem.deposits.Deposit_List.get(1),
	 * problem.deposits.Deposit_List.get(1).vehicles.Vehicles_List.get(1));
	 * problem.vehicleEuclideanDistance(problem.deposits.Deposit_List.get(1),
	 * problem.deposits.Deposit_List.get(1).vehicles.Vehicles_List.get(2));
	 * problem.vehicleEuclideanDistance(problem.deposits.Deposit_List.get(1),
	 * problem.deposits.Deposit_List.get(1).vehicles.Vehicles_List.get(3));
	 * 
	 * problem.deposits.Deposit_List.get(2).vehicles.Vehicles_List.get(0).clients.
	 * Client_List .add(problem.clients.Client_List.get(8));
	 * problem.deposits.Deposit_List.get(2).vehicles.Vehicles_List.get(0).clients.
	 * Client_List .add(problem.clients.Client_List.get(33));
	 * problem.deposits.Deposit_List.get(2).vehicles.Vehicles_List.get(0).clients.
	 * Client_List .add(problem.clients.Client_List.get(29));
	 * problem.deposits.Deposit_List.get(2).vehicles.Vehicles_List.get(0).clients.
	 * Client_List .add(problem.clients.Client_List.get(38));
	 * problem.deposits.Deposit_List.get(2).vehicles.Vehicles_List.get(0).clients.
	 * Client_List .add(problem.clients.Client_List.get(9));
	 * 
	 * problem.deposits.Deposit_List.get(2).vehicles.Vehicles_List.get(1).clients.
	 * Client_List .add(problem.clients.Client_List.get(48));
	 * problem.deposits.Deposit_List.get(2).vehicles.Vehicles_List.get(1).clients.
	 * Client_List .add(problem.clients.Client_List.get(4));
	 * problem.deposits.Deposit_List.get(2).vehicles.Vehicles_List.get(1).clients.
	 * Client_List .add(problem.clients.Client_List.get(37));
	 * 
	 * problem.vehicleEuclideanDistance(problem.deposits.Deposit_List.get(2),
	 * problem.deposits.Deposit_List.get(2).vehicles.Vehicles_List.get(0));
	 * problem.vehicleEuclideanDistance(problem.deposits.Deposit_List.get(2),
	 * problem.deposits.Deposit_List.get(2).vehicles.Vehicles_List.get(1));
	 * 
	 * problem.deposits.Deposit_List.get(3).vehicles.Vehicles_List.get(0).clients.
	 * Client_List .add(problem.clients.Client_List.get(34));
	 * problem.deposits.Deposit_List.get(3).vehicles.Vehicles_List.get(0).clients.
	 * Client_List .add(problem.clients.Client_List.get(35));
	 * problem.deposits.Deposit_List.get(3).vehicles.Vehicles_List.get(0).clients.
	 * Client_List .add(problem.clients.Client_List.get(2));
	 * problem.deposits.Deposit_List.get(3).vehicles.Vehicles_List.get(0).clients.
	 * Client_List .add(problem.clients.Client_List.get(19));
	 * 
	 * problem.deposits.Deposit_List.get(3).vehicles.Vehicles_List.get(1).clients.
	 * Client_List .add(problem.clients.Client_List.get(20));
	 * problem.deposits.Deposit_List.get(3).vehicles.Vehicles_List.get(1).clients.
	 * Client_List .add(problem.clients.Client_List.get(49));
	 * problem.deposits.Deposit_List.get(3).vehicles.Vehicles_List.get(1).clients.
	 * Client_List .add(problem.clients.Client_List.get(15));
	 * problem.deposits.Deposit_List.get(3).vehicles.Vehicles_List.get(1).clients.
	 * Client_List .add(problem.clients.Client_List.get(1));
	 * problem.deposits.Deposit_List.get(3).vehicles.Vehicles_List.get(1).clients.
	 * Client_List .add(problem.clients.Client_List.get(28));
	 * 
	 * problem.vehicleEuclideanDistance(problem.deposits.Deposit_List.get(3),
	 * problem.deposits.Deposit_List.get(3).vehicles.Vehicles_List.get(0));
	 * problem.vehicleEuclideanDistance(problem.deposits.Deposit_List.get(3),
	 * problem.deposits.Deposit_List.get(3).vehicles.Vehicles_List.get(1));
	 * 
	 * 
	 * for ( int i = 0 ; i < problem.vehicles.Vehicles_List.size() ; i++) {
	 * problem.vehicleDemand(problem.vehicles.Vehicles_List.get(i)); }
	 */

}
