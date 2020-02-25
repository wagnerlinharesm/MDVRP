package mdvrp.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import mdvrp.containers.Container_Client;
import mdvrp.containers.Container_Vehicles;

public class Deposit implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5534922510592023856L;
 
	public int RouteDuration;
	public int VehicleLoad;
	public float xCoordinate;
	public float yCoordinate;
	public int depNumber;
	public Container_Vehicles vehicles = new Container_Vehicles();
	public Container_Client clients = new Container_Client();
	public Deposit(int routeDuration2, int vehicleLoad2) {
		this.RouteDuration = routeDuration2;
		this.VehicleLoad = vehicleLoad2;
		
	}
	
 

	public void setLocal(int depNumber, int xCoordinate, int yCoordinate) {
		this.depNumber = depNumber;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		
	}
	
	
	public String toString() {
	
		return ("Route Duration " + RouteDuration + " Vehicle Load " + VehicleLoad + " Xcoordinate "
		+ xCoordinate + " yCoordinate "
		+ yCoordinate + " Dep Number " + depNumber + " Vehicles " + vehicles.toString()  );
	}
	
	public Deposit clone() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Deposit) ois.readObject();
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	
}
