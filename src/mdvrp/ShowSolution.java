package mdvrp;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import mdvrp.containers.Container_Deposits;

public class ShowSolution {

	public String result;

	public static void show(Container_Deposits dep, String instance, int repeticao) throws IOException {

//		FileWriter arquivo = new FileWriter("C:\\Users\\LaIC\\Documents\\RotasMDVRPNewMutation.txt",true);
		FileWriter arquivo = new FileWriter("D:\\OneDrive\\Wagner - UFOP\\TCC\\RotasMDVRPNewMutation.txt",true);
//		FileWriter arquivo = new FileWriter( "D:\\OneDrive\\Wagner - UFOP\\TCC\\Rotas Teste 02 - ADS.txt",true);
		PrintWriter pw = new PrintWriter(arquivo);

		NumberFormat formatter = format();

		System.out.println(formatter.format(dep.fullcoast));
		pw.println(instance + ';');
		pw.println(formatter.format(dep.fullcoast));

		for (int i = 0; i < dep.Deposit_List.size(); i++) {
		
			for (int j = 0; j < dep.Deposit_List.get(i).vehicles.Vehicles_List.size(); j++) {				

				System.out.print(i + 1 + "\t" + (j + 1) + "\t"
						+ formatter.format(dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).coast) + "\t "
						+ dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).vehicleDemand + "\t");

				pw.print(i + 1 + "\t" + (j + 1) + "\t"
						+ formatter.format(dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).coast) + "\t "
						+ dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).vehicleDemand + "\t");

				System.out.print("0 ");

				pw.print("0 ");
				for (int k = 0; k < dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List
						.size(); k++) {

					System.out.print(dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List
							.get(k).clientNumber + " ");

					pw.print(dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List
							.get(k).clientNumber + " ");
				}

				System.out.print("0\n");

				pw.print("0\n");

			}

		}

		pw.flush();
		pw.close();
	}

	public static NumberFormat format() {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.ROOT);
		symbols.setDecimalSeparator('.');
		symbols.setGroupingSeparator('.');
		return new DecimalFormat("#0.00", symbols);
	}

	public static void showNoWrite(Container_Deposits dep) throws IOException {

		NumberFormat formatter = format();

		System.out.println(formatter.format(dep.fullcoast));

		for (int i = 0; i < dep.Deposit_List.size(); i++) {
			for (int j = 0; j < dep.Deposit_List.get(i).vehicles.Vehicles_List.size(); j++) {

				System.out.print(i + 1 + "\t" + (j + 1) + "\t"
						+ formatter.format(dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).coast) + "\t "
						+ dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).vehicleDemand + "\t");

				System.out.print("0 ");

				for (int k = 0; k < dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List
						.size(); k++) {

					System.out.print(dep.Deposit_List.get(i).vehicles.Vehicles_List.get(j).clients.Client_List
							.get(k).clientNumber + " ");

				}

				System.out.print("0\n");

			}

		}

	}

}
