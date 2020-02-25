package mdvrp.containers;

import java.io.Serializable;
import java.util.ArrayList;

import mdvrp.util.Vehicle;

public class Container_Vehicles implements Cloneable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6176698287623142351L;
	public ArrayList<Vehicle> Vehicles_List = new ArrayList<>();
	
	public Container_Vehicles() {

	}

	public Container_Vehicles(Vehicle vehicle) {
		Vehicles_List.add(vehicle);
	}

	public void addVehicles(Vehicle vehicle) {
		Vehicles_List.add(vehicle);
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Vehicle c : Vehicles_List) {
			s.append(c.toString());
			s.append('\n');

		}

		return s.toString();
	}
}
