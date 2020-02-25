package mdvrp.problem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import mdvrp.Distribution;
import mdvrp.EvolutionEstrategy;
import mdvrp.containers.Container_Client;
import mdvrp.containers.Container_Deposits;
import mdvrp.containers.Container_Vehicles;
import mdvrp.util.Client;
import mdvrp.util.Deposit;
import mdvrp.util.Vehicle;

public class Problem {
	 
	public String parameterization;
	public Container_Client clients = new Container_Client();
	public  Container_Deposits deposits = new Container_Deposits();
	public Container_Vehicles vehicles = new Container_Vehicles();
	public File file;
	
	
	public  String instance ;
	public  int mu;
	public  int lambda;
	public double pbl;
	public double pblBest;
	public  int ngen;
	public  double pbm;
	public double result;
	
	public static int geracaoDeParada;
	
	
public Problem(String instance,int ngen, int mu, int lambda, double pbm, double pbl, double pblBest) {
		this.instance = instance;
		this.mu = mu;
		this.lambda = lambda;
		this.pbl = pbl;
		this.ngen = ngen;
		this.pbm = pbm;
		this.pblBest = pblBest;
	
	}


//	public Problem(String p_path) {
//		m_path = p_path;
//	}
	
	public static int numClients;
	public  int numDeps;
//	public Problem(String p_path) {
//		parameterization = p_path;
//	}
	

	
	public void readParameterization() throws IOException, CloneNotSupportedException {
		// Open the file
		this.file = new File(parameterization);
		FileReader read = new FileReader(file);
		BufferedReader store = new BufferedReader(read);

		// Read the read of the file
		String line = null;
		String[]  linha;
		while ((line = store.readLine()) != null) {
			linha = store.readLine().split(";");
			this.instance = linha[0];
			this.mu = Integer.parseInt(linha[1]);
			this.lambda = Integer.parseInt(linha[3]);
			this.pbl = Integer.parseInt(linha[4]);
			this.ngen = Integer.parseInt(linha[5]);
			this. pbm = Integer.parseInt(linha[6]);
		readFile();	
		Distribution.DistributionClients(clients, deposits);
		}
		store.close();
	
	}
	public void readFile() throws IOException, CloneNotSupportedException {
		// Open the file
		System.out.println("Instance File: " + instance);
		this.file = new File(instance);
		FileReader read = new FileReader(file);
		BufferedReader store = new BufferedReader(read);

		// Read the read of the file

		String[] linha;
		linha = store.readLine().split(" ");
		// int type = Integer.parseInt(linha[0]);
		int numVehicles = Integer.parseInt(linha[1]);
		numClients = Integer.parseInt(linha[2]);
		int numDeposits = Integer.parseInt(linha[3]);
		this.numDeps = numDeposits;

		System.out.println("Number of Vehicles:" + numVehicles + " Number of Clients: " + numClients
				+ " Number of Deposits:" + numDeposits);
		// Initializing the data structure

		for (int i = 0; i < numDeposits; i++) {

			String[] infoDeposit = store.readLine().split(" ");
			int RouteDuration = Integer.parseInt(infoDeposit[0]);
			if (RouteDuration == 0) {
				RouteDuration = 999999;
			}
			int VehicleLoad = Integer.parseInt(infoDeposit[1]);
			Deposit dep = new Deposit(RouteDuration, VehicleLoad);
			deposits.addDeposits(dep);
		}

		for (int i = 0; i < numClients; i++) {
			String infoClient = store.readLine();
			StringTokenizer st = new StringTokenizer(infoClient);
			String[] infoClient1 = new String[st.countTokens()];
			int j = 0;
			while (st.hasMoreElements()) {
				infoClient1[j] = st.nextToken();
				j++;
			}
			int clientNumber = Integer.parseInt(infoClient1[0]);

			int xCoordinate = Integer.parseInt(infoClient1[1]);

			int yCoordinate = Integer.parseInt(infoClient1[2]);

			int serviceDuration = Integer.parseInt(infoClient1[3]);

			int demand = Integer.parseInt(infoClient1[4]);

			Client clientAux = new Client(clientNumber, demand, xCoordinate, yCoordinate, serviceDuration);
			clients.addClients(clientAux);

		}

		for (int i = 0; i < numDeposits; i++) {
			String infoDeposit = store.readLine();
			StringTokenizer st = new StringTokenizer(infoDeposit);
			String[] infoDeposit1 = new String[st.countTokens()];
			int j = 0;
			while (st.hasMoreElements()) {
				infoDeposit1[j] = st.nextToken();
				j++;
			}

			int depNumber = Integer.parseInt(infoDeposit1[0]);
			int xCoordinate = Integer.parseInt(infoDeposit1[1]);
			int yCoordinate = Integer.parseInt(infoDeposit1[2]);
			deposits.Deposit_List.get(i).setLocal(depNumber, xCoordinate, yCoordinate);
		}
		int count = 0;
		for (int j = 0; j < numDeposits; j++) {
			for (int i = 0; i < numVehicles*2; i++) {

				vehicles.addVehicles(new Vehicle());
				deposits.Deposit_List.get(j).vehicles.addVehicles(vehicles.Vehicles_List.get(count));
				count++;
			}
		}

		store.close();
//		ngen=500;
//		mu = 2;
//		lambda = 2*mu;
//		 mu = numDeposits * numVehicles;
//		lambda = mu*2;
//		double pblBest = 0.6;
//		ngen = Math.round((numDeposits *  lambda + 10000));
		System.out.println("Numero de gerações " + ngen + " pbm " + pbm + " pbl " + pbl + " MU: " + mu + " lambda " + lambda + " pblBest " + pblBest);
		Distribution.euclidianDistanceClients(clients);
		Distribution.euclidianClientToDeposit(clients, deposits);
		Distribution.numVec = numVehicles;
		EvolutionEstrategy evo = new EvolutionEstrategy(mu, lambda, ngen, pbm, pbl, pblBest);
		//result = evo.method(clients, deposits);
		result = evo.method(clients, deposits);
		

	}
 
 
	public void addVehicleInDeposit(int dep, Vehicle vec, int amountVehicles) {
		
		deposits.Deposit_List.get(dep).vehicles.addVehicles(vec);
	}
	
	
	public void vehicleDemand(Vehicle vehicle) {
	
		for (int i = 0 ; i < vehicle.clients.Client_List.size() ; i ++ ) {
			vehicle.vehicleDemand += vehicle.clients.Client_List.get(i).demand;
		}
		System.out.println("Demanda total do Veículo:" + vehicle.vehicleDemand);
	
		
	}
	
	
	

}
