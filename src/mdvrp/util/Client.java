package mdvrp.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Client implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7095760295332445336L;
	public int clientNumber;
	public float xCoordinate;
	public float yCoordinate;
	public int serviceDuration;
	public int demand;

	public Client(int clientNumber, int demand, int xCoordinate, int yCoordinate,
			int serviceDuration ) {
		this.clientNumber = clientNumber;
		this.demand = demand;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.serviceDuration = serviceDuration;
		
		

	}
	public Client() {
		 
	}
	public String toString() {
		return "ClientNumber " + clientNumber + " xCoordinate " + xCoordinate + " yCoordinate " + yCoordinate +
				 " Service Duration " + serviceDuration + " Demand " + demand;
	}
	
	public Client clone() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Client) ois.readObject();
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}


}
