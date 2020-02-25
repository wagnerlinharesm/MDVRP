package mdvrp.util;

import java.io.Serializable;

import mdvrp.containers.Container_Client;

public class Vehicle implements Cloneable, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7054134420583929243L;
	public float Autonomy;
	public double Charge;
	public Container_Client clients = new Container_Client();
	public int vehicleDemand;
	public float coast;

	public Vehicle() {

	}

	public Vehicle(float Autonomy, double Charge) {
		this.Autonomy = Autonomy;
		this.Charge = Charge;

	}

	public String toString() {

		return ("Charge " + Charge + " Vehicle demand total " + vehicleDemand + clients.toString());
	}

 
public Vehicle clone() throws CloneNotSupportedException {
	Vehicle novo = new Vehicle(Autonomy, Charge);
	novo.vehicleDemand = vehicleDemand;
	novo.coast = coast;
	
	for (int i = 0 ; i < clients.Client_List.size();  i++) {
		novo.clients.Client_List.add(clients.Client_List.get(i).clone());
	}
	
	return novo;
}
}