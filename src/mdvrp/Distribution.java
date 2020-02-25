package mdvrp;

 
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import mdvrp.containers.Container_Client;
import mdvrp.containers.Container_Deposits;
import mdvrp.containers.Container_Vehicles;
import mdvrp.problem.Problem;
import mdvrp.util.Client;
import mdvrp.util.Deposit;
import mdvrp.util.Vehicle;

public class Distribution {

	public static float[][] matrixClientsDep;
	public static float[][] matrixClienttoClient;
	public static int numVec = 0;



	public static void euclidianDistanceClients(Container_Client clients) { // calculo da distancia euclidiana de
																			// cliente x cliente
		
		matrixClienttoClient = new float[clients.Client_List.size()][clients.Client_List.size()]; // Matrix NxN de
																									// distancia dos
																									// clientes
		for (int i = 0; i < clients.Client_List.size(); i++) {
			for (int j = 0; j < clients.Client_List.size(); j++) {
				if (i != j) {
				float aux1 = (float) Math.pow(clients.Client_List.get(j).xCoordinate - clients.Client_List.get(i).xCoordinate, 2);
				float aux2 = (float) Math.pow(clients.Client_List.get(j).yCoordinate - clients.Client_List.get(i).yCoordinate, 2);
				
				matrixClienttoClient[i][j] = (float) Math.sqrt((aux1 + aux2));
				}
				else {
					matrixClienttoClient[i][j]= 999;
				}
			}

		}

	}

	public static void euclidianClientToDeposit(Container_Client clients, Container_Deposits deposits) { // Calculo da
																											// Distancia
																											// euclidiana
																											// de
																											// Clients
																											// para
																											// depósitos

		matrixClientsDep = new float[clients.Client_List.size()][deposits.Deposit_List.size()];
		for (int i = 0; i < clients.Client_List.size(); i++) {
			for (int j = 0; j < deposits.Deposit_List.size(); j++) {
				float aux1 = (float) Math.pow(deposits.Deposit_List.get(j).xCoordinate - clients.Client_List.get(i).xCoordinate, 2);
				float aux2 = (float) Math.pow(deposits.Deposit_List.get(j).yCoordinate - clients.Client_List.get(i).yCoordinate, 2);
			 
				matrixClientsDep[i][j] =(float) Math.sqrt((aux1 + aux2));
			}

		}

	}

	public static void showDistanceCLientstoClients() {
		for (int i = 0; i < matrixClienttoClient.length; i++) {
			for (int j = 0; j < matrixClienttoClient.length; j++) {
				System.out.print(matrixClienttoClient[i][j] + " ");
			}
			System.out.println(" ");
		}
	}

	public static void showDistanceCLientstoDeposits() {
		for (int i = 0; i < matrixClientsDep.length; i++) {
			for (int j = 0; j < matrixClientsDep[0].length; j++) {
				System.out.print(matrixClientsDep[i][j] + " ");

			}
			System.out.println(" ");

		}
	}

	public static void fail(Container_Client clients, Container_Deposits deposits) throws CloneNotSupportedException {
		Container_Deposits auxContainerDep = new Container_Deposits();
		Container_Client auxContainerClients = new Container_Client();
		Container_Client randomClients = new Container_Client();
		 Client ant = new Client();
		auxContainerDep.Deposit_List.clear();	
		auxContainerClients.Client_List.clear();
		randomClients.Client_List.clear();
		Container_Client listaClientes = clients.clone();
		
		// Copio os depósitos e clientes
		int k = 0;
		int j = 0;

		for (int i = 0; i < deposits.Deposit_List.size(); i++) {
			
			auxContainerDep.addDeposits(deposits.Deposit_List.get(i).clone());
		}	

		while (!listaClientes.Client_List.isEmpty()) {
			// Distribuo os clientes para cada veículo, levando em conta a demanda.	
			// Encontro o depósito mais próximo ao cliente
			Collections.shuffle(listaClientes.Client_List);
			for (int i = 0; i < auxContainerDep.Deposit_List.size(); i++) {
				
				if (k >= auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.size()) {
					auxContainerDep.Deposit_List.get(i).vehicles.addVehicles(new Vehicle());
					k = 0;
				} else if (listaClientes.Client_List.size() == 0) {
					break;
				}
				
				System.out.println("Tamanho antes " + listaClientes.Client_List.size());
				System.out.println(k);
				if (auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k).clients.Client_List.size() == 0) {
					
					auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k).clients.Client_List.add(listaClientes.Client_List.get(j));
					auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k).vehicleDemand += listaClientes.Client_List.get(j).demand;
					setCustoRotaVeiculo(auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k), i);	
					System.out.println("tamanho da lista de clientes empty" + listaClientes.Client_List.size());
					listaClientes.Client_List.remove(j);					
				} else if ((auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k).vehicleDemand
							+ listaClientes.Client_List.get(j).demand <= auxContainerDep.Deposit_List.get(i).VehicleLoad) 
							&& (calculaCustoRotaVeiculo(auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k), i, listaClientes.Client_List.get(j))
							<= auxContainerDep.Deposit_List.get(i).RouteDuration)) {
						
						System.out.println((auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k).vehicleDemand
							+ listaClientes.Client_List.get(j).demand <= auxContainerDep.Deposit_List.get(i).VehicleLoad) 
							&& (calculaCustoRotaVeiculo(auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k), i, listaClientes.Client_List.get(j))
							<= auxContainerDep.Deposit_List.get(i).RouteDuration));
						
						System.out.println("if");
						auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k).clients.Client_List.add(listaClientes.Client_List.get(j));
						auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k).vehicleDemand += listaClientes.Client_List.get(j).demand;
						setCustoRotaVeiculo(auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k), i);					
						listaClientes.Client_List.remove(j);
					} else {
						k++;
					}
				}
			}				
		try {
			ShowSolution.showNoWrite(auxContainerDep);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Population.populationDeposits.add((Container_Deposits) auxContainerDep.clone());
		System.exit(0);

		auxContainerDep = null;

	}
	
	public static void DistributionClients(Container_Client clients, Container_Deposits deposits) throws CloneNotSupportedException, IOException {
		Container_Deposits auxContainerDep = new Container_Deposits();
		Container_Client auxContainerClients = new Container_Client();
		Container_Client randomClients = new Container_Client();
		Client ant = new Client();
		auxContainerDep.Deposit_List.clear();	
		auxContainerClients.Client_List.clear();
		randomClients.Client_List.clear();
		Random rnd = new Random();
		// Copio os depósitos e clientes

		for (int i = 0; i < deposits.Deposit_List.size(); i++) {
	
			auxContainerDep.addDeposits(deposits.Deposit_List.get(i).clone());
		}

		for (int i = 0; i < clients.Client_List.size(); i++) {

			auxContainerClients.addClients(clients.Client_List.get(i).clone());
		} 
		
		double depSmaller; // Pega a distancia do menor deposito
		int dep; // variável auxiliar para salvar a posição do depósito de menor distância
		// Encontro o depósito mais próximo ao cliente
		for (int i = 0; i < clients.Client_List.size(); i++) {
			depSmaller = matrixClientsDep[clients.Client_List.get(i).clientNumber-1][0];
			dep = 0;
			for (int j = 0; j < deposits.Deposit_List.size(); j++) {
				if (depSmaller > matrixClientsDep[clients.Client_List.get(i).clientNumber-1][j]) {
					depSmaller = matrixClientsDep[clients.Client_List.get(i).clientNumber-1][j]; 
					dep = j;
				}
			}
			
			auxContainerDep.Deposit_List.get(dep).clients.Client_List.add(auxContainerClients.Client_List.get(i)); // Adiciona os clientes para os depósitos próximos a ele.
      
		}
		// Distribuo os clientes para cada veículo, levando em conta a demanda.
		int k = 0;
		for (int i = 0; i < auxContainerDep.Deposit_List.size(); i++) {
			Collections.shuffle(auxContainerDep.Deposit_List.get(i).clients.Client_List);			
//			auxContainerDep.Deposit_List.get(i).vehicles.addVehicles(new Vehicle());		
			k = 0; // posição do veículo a receber clientes			
			int client = 0;
			while(!auxContainerDep.Deposit_List.get(i).clients.Client_List.isEmpty()) {			

			//	System.out.println("Verificando ausencia de clients no veículo : " + auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k).clients.Client_List.isEmpty());
			
//				if (ant != null) {
//					client = clienteMaisProximo(auxContainerDep.Deposit_List.get(i).clients, ant);
//				}
				int c, newDemand;
				float custoRota;
				 client = 0;
				for (c = 0 ; c < auxContainerDep.Deposit_List.get(i).clients.Client_List.size(); c++) {
					
					newDemand = auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k).vehicleDemand
					+ auxContainerDep.Deposit_List.get(i).clients.Client_List.get(c).demand;
					
					custoRota = calculaCustoRotaVeiculo(auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k), i, auxContainerDep.Deposit_List.get(i).clients.Client_List.get(c));
					
					if( (newDemand <= auxContainerDep.Deposit_List.get(i).VehicleLoad)
							&& ( custoRota
							 <= auxContainerDep.Deposit_List.get(0).RouteDuration) ) {
						client = c;
						break;
					}
				}

				newDemand = auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k).vehicleDemand
				+ auxContainerDep.Deposit_List.get(i).clients.Client_List.get(client).demand;
				
				custoRota = calculaCustoRotaVeiculo(auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k), i, auxContainerDep.Deposit_List.get(i).clients.Client_List.get(client));
				
				if( newDemand > auxContainerDep.Deposit_List.get(i).VehicleLoad
						|| ( custoRota
						 > auxContainerDep.Deposit_List.get(0).RouteDuration) ) {
					k++;
					if (k == auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.size()) {
						auxContainerDep.Deposit_List.get(i).vehicles.addVehicles(new Vehicle());
					}
				} else  {
					auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k).clients.Client_List.add(auxContainerDep.Deposit_List.get(i).clients.Client_List.get(client));
					auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k).vehicleDemand += auxContainerDep.Deposit_List.get(i).clients.Client_List.get(client).demand;
					ant = auxContainerDep.Deposit_List.get(i).clients.Client_List.get(client);
					auxContainerDep.Deposit_List.get(i).clients.Client_List.remove(client);
					setCustoRotaVeiculo(auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k), i);	
				}
			}
 		}	
		Population.populationDeposits.add((Container_Deposits) auxContainerDep.clone());
		auxContainerDep = null;
	}

	public static  Container_Client random(Container_Deposits containerDep, int dep) throws CloneNotSupportedException {
		Random generateClients = new Random();
		Container_Client randomClients = new Container_Client();
		for (int i = 0; i < containerDep.Deposit_List.get(dep).clients.Client_List.size(); i++) {
			randomClients.Client_List.add(containerDep.Deposit_List.get(dep).clients.Client_List.get(generateClients.nextInt(containerDep.Deposit_List.get(dep).clients.Client_List.size())));
		}
		return randomClients;
	}
	public static  Container_Client randomMutation(Container_Deposits containerDep, int dep) throws CloneNotSupportedException {
		 
		Container_Client randomClients = new Container_Client();
		for (int i = 0; i < containerDep.Deposit_List.get(dep).clients.Client_List.size(); i++) {
			randomClients.Client_List.add(containerDep.Deposit_List.get(dep).clients.Client_List.get(i));
		}
		return randomClients;
	}

	public static int clienteMaisProximo(Container_Client clients, Client clientAnt) {

		int clientNumber = 0;
		if (clients.Client_List.size() == 3) {
			if (matrixClienttoClient[clientAnt.clientNumber - 1][clients.Client_List.get(0).clientNumber
					- 1] < matrixClienttoClient[clientAnt.clientNumber - 1][clients.Client_List.get(1).clientNumber - 1]
					&& matrixClienttoClient[clientAnt.clientNumber - 1][clients.Client_List.get(0).clientNumber
							- 1] < matrixClienttoClient[clientAnt.clientNumber
									- 1][clients.Client_List.get(2).clientNumber - 1]) {
				clientNumber = 0;
			} else if (matrixClienttoClient[clientAnt.clientNumber - 1][clients.Client_List.get(1).clientNumber
					- 1] < matrixClienttoClient[clientAnt.clientNumber - 1][clients.Client_List.get(2).clientNumber
							- 1]) {

				clientNumber = 1;
			} else {

				clientNumber = 2;
			}
		} else if (clients.Client_List.size() == 2) {
			if (matrixClienttoClient[clientAnt.clientNumber - 1][clients.Client_List.get(0).clientNumber
					- 1] < matrixClienttoClient[clientAnt.clientNumber - 1][clients.Client_List.get(1).clientNumber
							- 1]) {
				clientNumber = 0;
			} else {
				clientNumber = 1;
			}
		}

		return clientNumber;
	}
	
	public static float calculaCustoRotaVeiculo(Vehicle vec, int depNumber, Client client) {
	
			float custo = 0;

			if (vec.clients.Client_List.size() == 0) {
				custo += matrixClientsDep[client.clientNumber-1][depNumber];
				custo += matrixClientsDep[client.clientNumber-1][depNumber];

			} else {
				
				for (int k = 0; k < vec.clients.Client_List.size()-1; k++) {					
					
					custo += matrixClienttoClient[vec.clients.Client_List.get(k).clientNumber-1][vec.clients.Client_List.get(k+1).clientNumber-1];			
					
				}

					custo += matrixClientsDep[vec.clients.Client_List.get(0).clientNumber-1][depNumber];
					

//				System.out.println(vec.clients.Client_List.get(vec.clients.Client_List.size() - 1));
				custo += matrixClienttoClient[vec.clients.Client_List.get(vec.clients.Client_List.size() - 1).clientNumber-1][client.clientNumber-1];
				custo += matrixClientsDep[client.clientNumber-1][depNumber];
			}

		return custo;
		
	}
	public static void setCustoRotaVeiculo(Vehicle vec, int depNumber) {
		vec.coast = 0;
		for (int k = 0; k < vec.clients.Client_List.size()-1; k++) {					
									
		vec.coast += matrixClienttoClient[vec.clients.Client_List.get(k).clientNumber-1][vec.clients.Client_List.get(k+1).clientNumber-1];			
		
		}
		
		if (vec.clients.Client_List.isEmpty() == false) {				
 
		vec.coast += matrixClientsDep[vec.clients.Client_List.get(0).clientNumber-1][depNumber];
		vec.coast += matrixClientsDep[vec.clients.Client_List.get(vec.clients.Client_List.size() - 1).clientNumber-1][depNumber];

		}

	
}
	
	
	public static void funcaoObjetivo(Container_Deposits dep) {
		dep.fullcoast = 0;

		for ( int i = 0 ; i <= dep.Deposit_List.size()-1; i ++) {
			

			for (int j = 0 ; j <= dep.Deposit_List.get(i).vehicles.Vehicles_List.size()-1; j ++) {	
				dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).coast = 0;
//				if ((j > numVec && dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.size() >= 0)|| dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).coast > dep.Deposit_List.get(i).RouteDuration ) {
////					System.out.println(j);
////					System.out.println(numVec);
//					dep.fullcoast += 1000;
//				}
				for (int k = 0; k < dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.size()-1; k++) {
				
				
				dep.fullcoast += matrixClienttoClient[dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.get(k).clientNumber-1][dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.get(k+1).clientNumber-1];							
//				dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).coast += matrixClienttoClient[dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.get(k).clientNumber-1][dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.get(k+1).clientNumber-1];
				
			}
				
				if (dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.isEmpty() == false) {				
					
				dep.fullcoast += matrixClientsDep[dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.get(0).clientNumber-1][i]; // Do deposito ao primeiro cliente
				dep.fullcoast += matrixClientsDep[dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.get(dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.size() - 1 ).clientNumber-1][i]; // Do ultimo cliente ao depósito	
//				dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).coast += matrixClientsDep[dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.get(0).clientNumber-1][i];
//				dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).coast += matrixClientsDep[dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.get(dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.size() - 1).clientNumber-1][i];

				}

			}
		}
		
	}
	
	public static Container_Deposits DistributionMutation(Container_Deposits deposits  ) throws CloneNotSupportedException, IOException {

		Container_Client auxContainerClients = new Container_Client();
		Container_Client randomClients = new Container_Client();
		Client ant = new Client();
		auxContainerClients.Client_List.clear();
		randomClients.Client_List.clear();
		
////		for (int i = 0; i < deposits.Deposit_List.size(); i++) {
//			for (int j = 0 ; j < deposits.Deposit_List.get(0).clients.Client_List.size(); j++) {
//				System.out.println(deposits.Deposit_List.get(0).clients.Client_List.get(j).toString());
//			}
//			System.out.println("Deposito 2");
//			for (int j = 0 ; j < deposits.Deposit_List.get(1).clients.Client_List.size(); j++) {
//				System.out.println(deposits.Deposit_List.get(1).clients.Client_List.get(j).toString());
//			}
////		}
//		System.exit(0);
		
		
		Container_Deposits auxContainerDep = new Container_Deposits();
		auxContainerDep = deposits.clone();
		
//		for (int i = 0; i < deposits.Deposit_List.size(); i++) {
//			auxContainerDep.Deposit_List.add(Problem.deposits.Deposit_List.get(i).clone());
//			auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.clear();
//		}
//		
//
//		for (int i = 0; i < deposits.Deposit_List.size(); i++) {
//			for (int j = 0; j < Distribution.numVec; j++) {
//				auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.add(new Vehicle());
//			}
//		}
//	
//		for (int i = 0; i < deposits.Deposit_List.size(); i++) {
//			for (int j = 0 ; j < deposits.Deposit_List.get(i).clients.Client_List.size(); j++) {
//				auxContainerDep.Deposit_List.get(i).clients.Client_List.add(deposits.Deposit_List.get(i).clients.Client_List.get(j).clone());
//			}
//		}
		

		// Distribuo os clientes para cada veículo, levando em conta a demanda.
		int k = 0;
		for (int i = 0; i < auxContainerDep.Deposit_List.size(); i++) {
//			auxContainerDep.Deposit_List.get(i).vehicles.addVehicles(new Vehicle());		
			k = 0; // posição do veículo a receber clientes			
			int client = 0;
			while(!auxContainerDep.Deposit_List.get(i).clients.Client_List.isEmpty()) {			

			//	System.out.println("Verificando ausencia de clients no veículo : " + auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k).clients.Client_List.isEmpty());
			
//				if (ant != null) {
//					client = clienteMaisProximo(auxContainerDep.Deposit_List.get(i).clients, ant);
//				}
				int c, newDemand;
				float custoRota;
				 client = 0;
				for (c = 0 ; c < auxContainerDep.Deposit_List.get(i).clients.Client_List.size(); c++) {
					
					newDemand = auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k).vehicleDemand
					+ auxContainerDep.Deposit_List.get(i).clients.Client_List.get(c).demand;
					
					custoRota = calculaCustoRotaVeiculo(auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k), i, auxContainerDep.Deposit_List.get(i).clients.Client_List.get(c));
					
					if( newDemand <= auxContainerDep.Deposit_List.get(i).VehicleLoad
							&& ( custoRota
							 <= auxContainerDep.Deposit_List.get(0).RouteDuration*2) ) {
						client = c;
						break;
					}
				}

				newDemand = auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k).vehicleDemand
				+ auxContainerDep.Deposit_List.get(i).clients.Client_List.get(client).demand;
				
				custoRota = calculaCustoRotaVeiculo(auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k), i, auxContainerDep.Deposit_List.get(i).clients.Client_List.get(client));
				
				if( newDemand > auxContainerDep.Deposit_List.get(i).VehicleLoad
						|| ( custoRota
						 > auxContainerDep.Deposit_List.get(0).RouteDuration*2) ) {
					k++;
					if (k == auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.size()) {
						auxContainerDep.Deposit_List.get(i).vehicles.addVehicles(new Vehicle());
					}
				} else  {
					auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k).clients.Client_List.add(auxContainerDep.Deposit_List.get(i).clients.Client_List.get(client));
					auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k).vehicleDemand += auxContainerDep.Deposit_List.get(i).clients.Client_List.get(client).demand;
					ant = auxContainerDep.Deposit_List.get(i).clients.Client_List.get(client);
					auxContainerDep.Deposit_List.get(i).clients.Client_List.remove(client);
					setCustoRotaVeiculo(auxContainerDep.Deposit_List.get(i).vehicles.Vehicles_List.get(k), i);	
				}
			}
 		}
		return (Container_Deposits) auxContainerDep;
	}
	
	public static boolean verificaCustoDemanda (int vehicleDemand, int clientDemand, int vehicleLoad, double custoRota, double routeDuration) {
		System.out.println("vehicleDemand " + vehicleDemand + " clientDemand " + clientDemand + " vehicleLoad " + vehicleLoad + " custoRota " + custoRota + " routeDurationn " + routeDuration);
		if ((vehicleDemand + clientDemand) > vehicleLoad ) {
			System.out.println("demanda");
			return false;
		} else if (custoRota > routeDuration) {
			System.out.println("custorota");
			return false;
		} else {
			return false;
		}
	}
	

	
	public static Container_Deposits teste(Container_Deposits deposits, Container_Client clients) {
	 Container_Deposits teste = new Container_Deposits();

		// Copio os depósitos e clientes

				for (int i = 0; i < deposits.Deposit_List.size(); i++) {
					//auxContainerDep.addDeposits(deposits.Deposit_List.get(i));
					teste.addDeposits(deposits.Deposit_List.get(i).clone());
				}
				for (int i =0 ; i < 4 ; i++) {
					for (int j = 0 ; j < 4 ; j ++) {
					teste.Deposit_List.get(i).vehicles.Vehicles_List.add(new Vehicle());
				}
				}

				teste.Deposit_List.get(0).vehicles.Vehicles_List.get(0).clients.Client_List
				.add( clients.Client_List.get(3));
				
				teste.Deposit_List.get(0).vehicles.Vehicles_List.get(1).clients.Client_List
				.add( clients.Client_List.get(41));
				teste.Deposit_List.get(0).vehicles.Vehicles_List.get(1).clients.Client_List
				.add( clients.Client_List.get(43));
				teste.Deposit_List.get(0).vehicles.Vehicles_List.get(1).clients.Client_List
				.add( clients.Client_List.get(44));
				teste.Deposit_List.get(0).vehicles.Vehicles_List.get(1).clients.Client_List
				.add( clients.Client_List.get(14));
				teste.Deposit_List.get(0).vehicles.Vehicles_List.get(1).clients.Client_List
				.add( clients.Client_List.get(36));
				teste.Deposit_List.get(0).vehicles.Vehicles_List.get(1).clients.Client_List
				.add( clients.Client_List.get(16));

				teste.Deposit_List.get(0).vehicles.Vehicles_List.get(2).clients.Client_List
				.add( clients.Client_List.get(18));
				teste.Deposit_List.get(0).vehicles.Vehicles_List.get(2).clients.Client_List
				.add( clients.Client_List.get(39));
				teste.Deposit_List.get(0).vehicles.Vehicles_List.get(2).clients.Client_List
				.add( clients.Client_List.get(40));
				teste.Deposit_List.get(0).vehicles.Vehicles_List.get(2).clients.Client_List
				.add( clients.Client_List.get(12));
				teste.Deposit_List.get(0).vehicles.Vehicles_List.get(2).clients.Client_List
				.add( clients.Client_List.get(17));


				teste.Deposit_List.get(1).vehicles.Vehicles_List.get(0).clients.Client_List
				.add( clients.Client_List.get(11));
				teste.Deposit_List.get(1).vehicles.Vehicles_List.get(0).clients.Client_List
				.add( clients.Client_List.get(46));
			

				teste.Deposit_List.get(1).vehicles.Vehicles_List.get(1).clients.Client_List
				.add( clients.Client_List.get(26));
				teste.Deposit_List.get(1).vehicles.Vehicles_List.get(1).clients.Client_List
				.add( clients.Client_List.get(0));
				teste.Deposit_List.get(1).vehicles.Vehicles_List.get(1).clients.Client_List
				.add( clients.Client_List.get(31));
				teste.Deposit_List.get(1).vehicles.Vehicles_List.get(1).clients.Client_List
				.add( clients.Client_List.get(10));
		
				
				teste.Deposit_List.get(1).vehicles.Vehicles_List.get(2).clients.Client_List
				.add( clients.Client_List.get(45));
				
		

//14	25	24	43	23	7	26	8	48	6
				teste.Deposit_List.get(1).vehicles.Vehicles_List.get(3).clients.Client_List
				.add( clients.Client_List.get(13));
				teste.Deposit_List.get(1).vehicles.Vehicles_List.get(3).clients.Client_List
				.add( clients.Client_List.get(24));
				teste.Deposit_List.get(1).vehicles.Vehicles_List.get(3).clients.Client_List
				.add( clients.Client_List.get(23));
				teste.Deposit_List.get(1).vehicles.Vehicles_List.get(3).clients.Client_List
				.add(clients.Client_List.get(42));
				teste.Deposit_List.get(1).vehicles.Vehicles_List.get(3).clients.Client_List
				.add( clients.Client_List.get(22));
				teste.Deposit_List.get(1).vehicles.Vehicles_List.get(3).clients.Client_List
				.add( clients.Client_List.get(6));
				teste.Deposit_List.get(1).vehicles.Vehicles_List.get(3).clients.Client_List
				.add( clients.Client_List.get(25));
				teste.Deposit_List.get(1).vehicles.Vehicles_List.get(3).clients.Client_List
				.add( clients.Client_List.get(7));
				teste.Deposit_List.get(1).vehicles.Vehicles_List.get(3).clients.Client_List
				.add( clients.Client_List.get(47));
				teste.Deposit_List.get(1).vehicles.Vehicles_List.get(3).clients.Client_List
				.add( clients.Client_List.get(5));
				

				teste.Deposit_List.get(2).vehicles.Vehicles_List.get(0).clients.Client_List
				.add( clients.Client_List.get(33));
				teste.Deposit_List.get(2).vehicles.Vehicles_List.get(0).clients.Client_List
				.add( clients.Client_List.get(20));
				teste.Deposit_List.get(2).vehicles.Vehicles_List.get(0).clients.Client_List
				.add( clients.Client_List.get(15));
				teste.Deposit_List.get(2).vehicles.Vehicles_List.get(0).clients.Client_List
				.add( clients.Client_List.get(49));
				teste.Deposit_List.get(2).vehicles.Vehicles_List.get(0).clients.Client_List
				.add( clients.Client_List.get(8));
				
				teste.Deposit_List.get(2).vehicles.Vehicles_List.get(1).clients.Client_List
				.add( clients.Client_List.get(29));
				teste.Deposit_List.get(2).vehicles.Vehicles_List.get(1).clients.Client_List
				.add( clients.Client_List.get(38));
				teste.Deposit_List.get(2).vehicles.Vehicles_List.get(1).clients.Client_List
				.add( clients.Client_List.get(32));
				teste.Deposit_List.get(2).vehicles.Vehicles_List.get(1).clients.Client_List
				.add( clients.Client_List.get(9));
				teste.Deposit_List.get(2).vehicles.Vehicles_List.get(1).clients.Client_List
				.add( clients.Client_List.get(48));
//30	39	33	10	49

				teste.Deposit_List.get(2).vehicles.Vehicles_List.get(2).clients.Client_List
				.add( clients.Client_List.get(4));
				teste.Deposit_List.get(2).vehicles.Vehicles_List.get(2).clients.Client_List
				.add( clients.Client_List.get(37));


				teste.Deposit_List.get(3).vehicles.Vehicles_List.get(0).clients.Client_List
				.add( clients.Client_List.get(19));
				
				teste.Deposit_List.get(3).vehicles.Vehicles_List.get(0).clients.Client_List
				.add( clients.Client_List.get(1));
				teste.Deposit_List.get(3).vehicles.Vehicles_List.get(0).clients.Client_List
				.add( clients.Client_List.get(28));

				teste.Deposit_List.get(3).vehicles.Vehicles_List.get(1).clients.Client_List
				.add( clients.Client_List.get(34));
				teste.Deposit_List.get(3).vehicles.Vehicles_List.get(1).clients.Client_List
				.add( clients.Client_List.get(35));
				teste.Deposit_List.get(3).vehicles.Vehicles_List.get(1).clients.Client_List
				.add( clients.Client_List.get(2));
				teste.Deposit_List.get(3).vehicles.Vehicles_List.get(1).clients.Client_List
				.add( clients.Client_List.get(27));
				teste.Deposit_List.get(3).vehicles.Vehicles_List.get(1).clients.Client_List
				.add( clients.Client_List.get(30));			
				teste.Deposit_List.get(3).vehicles.Vehicles_List.get(1).clients.Client_List
				.add( clients.Client_List.get(21));
//		
//		teste.Deposit_List.get(0).vehicles.Vehicles_List.get(0).clients.Client_List
//		.add( clients.Client_List.get(43));
//		teste.Deposit_List.get(0).vehicles.Vehicles_List.get(0).clients.Client_List
//		.add( clients.Client_List.get(44));
//		teste.Deposit_List.get(0).vehicles.Vehicles_List.get(0).clients.Client_List
//		.add( clients.Client_List.get(32));
//		teste.Deposit_List.get(0).vehicles.Vehicles_List.get(0).clients.Client_List
//		.add( clients.Client_List.get(14));
//		teste.Deposit_List.get(0).vehicles.Vehicles_List.get(0).clients.Client_List
//		.add( clients.Client_List.get(36));
//		teste.Deposit_List.get(0).vehicles.Vehicles_List.get(0).clients.Client_List
//		.add( clients.Client_List.get(16));
//		System.out.println("aqui");
//		teste.Deposit_List.get(0).vehicles.Vehicles_List.get(1).clients.Client_List
//		.add( clients.Client_List.get(41));
//		teste.Deposit_List.get(0).vehicles.Vehicles_List.get(1).clients.Client_List
//		.add( clients.Client_List.get(18));
//		teste.Deposit_List.get(0).vehicles.Vehicles_List.get(1).clients.Client_List
//		.add( clients.Client_List.get(39));
//		teste.Deposit_List.get(0).vehicles.Vehicles_List.get(1).clients.Client_List
//		.add( clients.Client_List.get(40));
//		teste.Deposit_List.get(0).vehicles.Vehicles_List.get(1).clients.Client_List
//		.add( clients.Client_List.get(12));
//
//		teste.Deposit_List.get(0).vehicles.Vehicles_List.get(2).clients.Client_List
//		.add( clients.Client_List.get(24));
//		teste.Deposit_List.get(0).vehicles.Vehicles_List.get(2).clients.Client_List
//		.add( clients.Client_List.get(17));
//		teste.Deposit_List.get(0).vehicles.Vehicles_List.get(2).clients.Client_List
//		.add( clients.Client_List.get(3));
//
////problem.vehicleEuclideanDistance(problem.deposits.Deposit_List.get(0),
////		problem.deposits.Deposit_List.get(0).vehicles.Vehicles_List.get(0));
////problem.vehicleEuclideanDistance(problem.deposits.Deposit_List.get(0),
////		problem.deposits.Deposit_List.get(0).vehicles.Vehicles_List.get(1));
////problem.vehicleEuclideanDistance(problem.deposits.Deposit_List.get(0),
////		problem.deposits.Deposit_List.get(0).vehicles.Vehicles_List.get(2));
//
//		teste.Deposit_List.get(1).vehicles.Vehicles_List.get(0).clients.Client_List
//		.add( clients.Client_List.get(5));
//		teste.Deposit_List.get(1).vehicles.Vehicles_List.get(0).clients.Client_List
//		.add( clients.Client_List.get(26));
//		teste.Deposit_List.get(1).vehicles.Vehicles_List.get(0).clients.Client_List
//		.add( clients.Client_List.get(0));
//		teste.Deposit_List.get(1).vehicles.Vehicles_List.get(0).clients.Client_List
//		.add( clients.Client_List.get(31));
//		teste.Deposit_List.get(1).vehicles.Vehicles_List.get(0).clients.Client_List
//		.add( clients.Client_List.get(10));
//		teste.Deposit_List.get(1).vehicles.Vehicles_List.get(0).clients.Client_List
//		.add( clients.Client_List.get(45));
//
//		teste.Deposit_List.get(1).vehicles.Vehicles_List.get(1).clients.Client_List
//		.add( clients.Client_List.get(47));
//		teste.Deposit_List.get(1).vehicles.Vehicles_List.get(1).clients.Client_List
//		.add( clients.Client_List.get(7));
//		teste.Deposit_List.get(1).vehicles.Vehicles_List.get(1).clients.Client_List
//		.add( clients.Client_List.get(25));
//		teste.Deposit_List.get(1).vehicles.Vehicles_List.get(1).clients.Client_List
//		.add( clients.Client_List.get(30));
//		teste.Deposit_List.get(1).vehicles.Vehicles_List.get(1).clients.Client_List
//		.add( clients.Client_List.get(27));
//		teste.Deposit_List.get(1).vehicles.Vehicles_List.get(1).clients.Client_List
//		.add( clients.Client_List.get(21));
//
//		teste.Deposit_List.get(1).vehicles.Vehicles_List.get(2).clients.Client_List
//		.add( clients.Client_List.get(22));
//		teste.Deposit_List.get(1).vehicles.Vehicles_List.get(2).clients.Client_List
//		.add( clients.Client_List.get(6));
//		teste.Deposit_List.get(1).vehicles.Vehicles_List.get(2).clients.Client_List
//		.add( clients.Client_List.get(42));
//		teste.Deposit_List.get(1).vehicles.Vehicles_List.get(2).clients.Client_List
//		.add( clients.Client_List.get(23));
//		teste.Deposit_List.get(1).vehicles.Vehicles_List.get(2).clients.Client_List
//		.add(clients.Client_List.get(13));
//
//		teste.Deposit_List.get(1).vehicles.Vehicles_List.get(3).clients.Client_List
//		.add( clients.Client_List.get(11));
//		teste.Deposit_List.get(1).vehicles.Vehicles_List.get(3).clients.Client_List
//		.add( clients.Client_List.get(46));
//
//
//		teste.Deposit_List.get(2).vehicles.Vehicles_List.get(0).clients.Client_List
//		.add( clients.Client_List.get(8));
//		teste.Deposit_List.get(2).vehicles.Vehicles_List.get(0).clients.Client_List
//		.add( clients.Client_List.get(33));
//		teste.Deposit_List.get(2).vehicles.Vehicles_List.get(0).clients.Client_List
//		.add( clients.Client_List.get(29));
//		teste.Deposit_List.get(2).vehicles.Vehicles_List.get(0).clients.Client_List
//		.add(clients.Client_List.get(38));
//		teste.Deposit_List.get(2).vehicles.Vehicles_List.get(0).clients.Client_List
//		.add( clients.Client_List.get(9));
//
//		teste.Deposit_List.get(2).vehicles.Vehicles_List.get(1).clients.Client_List
//		.add( clients.Client_List.get(48));
//		teste.Deposit_List.get(2).vehicles.Vehicles_List.get(1).clients.Client_List
//		.add( clients.Client_List.get(4));
//		teste.Deposit_List.get(2).vehicles.Vehicles_List.get(1).clients.Client_List
//		.add( clients.Client_List.get(37));
//
//
//
//		teste.Deposit_List.get(3).vehicles.Vehicles_List.get(0).clients.Client_List
//		.add( clients.Client_List.get(34));
//		teste.Deposit_List.get(3).vehicles.Vehicles_List.get(0).clients.Client_List
//		.add(  clients.Client_List.get(35));
//		teste.Deposit_List.get(3).vehicles.Vehicles_List.get(0).clients.Client_List
//		.add( clients.Client_List.get(2));
//		teste.Deposit_List.get(3).vehicles.Vehicles_List.get(0).clients.Client_List
//		.add( clients.Client_List.get(19));
//
//		teste.Deposit_List.get(3).vehicles.Vehicles_List.get(1).clients.Client_List
//		.add( clients.Client_List.get(20));
//		teste.Deposit_List.get(3).vehicles.Vehicles_List.get(1).clients.Client_List
//		.add( clients.Client_List.get(49));
//		teste.Deposit_List.get(3).vehicles.Vehicles_List.get(1).clients.Client_List
//		.add( clients.Client_List.get(15));
//		teste.Deposit_List.get(3).vehicles.Vehicles_List.get(1).clients.Client_List
//		.add( clients.Client_List.get(1));
//		teste.Deposit_List.get(3).vehicles.Vehicles_List.get(1).clients.Client_List
//		.add( clients.Client_List.get(28));
		System.out.println("Distribuindo..........");
		Distribution.funcaoObjetivo(teste);
		return teste;

	}

}
