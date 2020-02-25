package mdvrp.containers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import mdvrp.util.Deposit;

public class Container_Deposits implements Cloneable, Serializable {
	/**
	 * 
	 */
	public double fullcoast ;
	private static final long serialVersionUID = 1L;
	public ArrayList<Deposit> Deposit_List = new ArrayList<>();
	
	public Container_Deposits() {
		
	}
	public Container_Deposits(Deposit dep) {
		Deposit_List.add(dep);
	}
	
	public void addDeposits(Deposit dep) {
		Deposit_List.add(dep);
	}
	
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		for (Deposit c: Deposit_List) {
			s.append(c.toString());
			s.append('\n');
			
		}
		
		return  s.toString();
	}
	
//	public Container_Deposits clone() throws CloneNotSupportedException {
//		Container_Deposits novo = new Container_Deposits();
//		novo.Deposit_List = new ArrayList<Deposit>(Deposit_List);
//		/*for (int i = 0 ; i < Deposit_List.size(); i++) {
//			novo.Deposit_List.get(i).depNumber = Deposit_List.get(i).depNumber;
//			novo.Deposit_List.get(i).RouteDuration = Deposit_List.get(i).RouteDuration;
//			novo.Deposit_List.get(i).VehicleLoad = Deposit_List.get(i).VehicleLoad;
//			novo.Deposit_List.get(i).xCoordinate = Deposit_List.get(i).xCoordinate;
//			novo.Deposit_List.get(i).yCoordinate = Deposit_List.get(i).yCoordinate;
//			for (int j = 0 ; j < Deposit_List.get(i).clients.Client_List.size() ; j ++) {
//				
//				novo.Deposit_List.get(i).clients.Client_List.add(Deposit_List.get(i).clients.Client_List.get(j).clone());
//				
//			}
//			for (int k = 0 ; k < Deposit_List.get(i).vehicles.Vehicles_List.size(); k ++) {
//				novo.Deposit_List.get(i).vehicles.Vehicles_List.add(Deposit_List.get(i).vehicles.Vehicles_List.get(k).clone());
//			}
//		}*/
//	
//		return novo;
//	}
//	public Object clone()
//	{
//		Object object = null;
//		try {
//			FileOutputStream fileOutputStream = new FileOutputStream("object.dat");
//			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//			objectOutputStream.writeObject(this);
//			fileOutputStream.flush();
//			fileOutputStream.close();
//			objectOutputStream.flush();
//			objectOutputStream.close();
//			FileInputStream fileInputStream = new FileInputStream("object.dat");
//			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//			object = objectInputStream.readObject();
//	                fileInputStream.close();
//			objectInputStream.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return object;
//	}
	public Container_Deposits  clone() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Container_Deposits) ois.readObject();
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	
	
}
