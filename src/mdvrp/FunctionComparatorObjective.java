package mdvrp;

import java.util.Comparator;

import mdvrp.containers.Container_Deposits;

public class FunctionComparatorObjective implements Comparator<Container_Deposits> {

	@Override
	public int compare(Container_Deposits o1, Container_Deposits o2) {
		if (o1.fullcoast < o2.fullcoast) {
			return -1;
		}
		else if (o1.fullcoast > o2.fullcoast) {
			return +1;
		}
		return 0;
	}

}
