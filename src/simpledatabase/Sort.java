package simpledatabase;

import java.util.ArrayList;

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
			if(temp.isEmpty())
				return null;
			tuple = temp.get(0);
			int predicateIndex ;
			
			for(predicateIndex = 0;predicateIndex <tuple.getAttributeList().size(); predicateIndex++){
				if(tuple.getAttributeName(predicateIndex).equals(orderPredicate))
					break;
			}
			while(!temp.isEmpty()){
				int min = 0;
				for(int i = 0; i < temp.size();i++){
					if(temp.get(i).getAttributeValue(predicateIndex).toString().compareTo(temp.get(min).getAttributeValue(predicateIndex).toString())<0)
						min = i;
				}
				tuplesResult.add(temp.get(min));
				temp.remove(min);
			}
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