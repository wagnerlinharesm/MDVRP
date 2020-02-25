package mdvrp;

import mdvrp.containers.Container_Deposits;

public class Verify {

	public static void clientVerify(Container_Deposits dep) {
		for (int i = 0 ; i < dep.Deposit_List.size(); i++) {
			for (int j = 0 ; j < dep.Deposit_List.get(i).vehicles.Vehicles_List.size();  j++) {
				for (int k = 0 ; k < dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.size()-1; k++) {
					if (dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.get(k).clientNumber == dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List.get(k+1).clientNumber){
						System.exit(0);
					}
				}
			}
		}
	}
}
