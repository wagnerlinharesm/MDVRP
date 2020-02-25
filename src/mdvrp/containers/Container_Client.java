package mdvrp.containers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import mdvrp.util.Client;


public class Container_Client implements Cloneable, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -864074808997065113L;
	public ArrayList<Client> Client_List = new ArrayList<>();
	
	
	public Container_Client() {
		
	}
	
	public void addClients(Client client) {
		Client_List.add(client);
	}
	
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		for (Client c: Client_List) {
			s.append(c.toString());
			s.append('\n');
			
		}
		
		return  s.toString();
	}
	
	public Container_Client clone() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Container_Client) ois.readObject();
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

}
