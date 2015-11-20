package simpledatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Sort extends Operator {

	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;

	public Sort(Operator child, String orderPredicate) {
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();

	}

	/**
	 * The function is used to return the sorted tuple
	 * 
	 * @return tuple
	 */
	@Override
	public Tuple next() {
		if (tuplesResult.isEmpty()) {
			Tuple tuple = child.next();
			ArrayList<Tuple> temp = new ArrayList<Tuple>();
			while (tuple != null) {
				temp.add(tuple);
				tuple = child.next();
			}
			if (temp.isEmpty())
				return null;
			tuplesResult = temp;
			Collections.sort(tuplesResult, new Comparator<Tuple>() {
				public int compare(Tuple t1, Tuple t2) {
					for (int i = 0; i < t1.getAttributeList().size(); i++) {
						if (t1.getAttributeName(i).equals(orderPredicate)) 
							return t1.getAttributeValue(i).toString().compareTo(t2.getAttributeValue(i).toString());
						
					}
					return -1;
				}

			});
		}

		return tuplesResult.remove(0);
	}

	/**
	 * The function is used to get the attribute list of the tuple
	 * 
	 * @return attribute list
	 */
	public ArrayList<Attribute> getAttributeList() {
		return child.getAttributeList();
	}

}
