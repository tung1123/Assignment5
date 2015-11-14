package simpledatabase;

import java.util.ArrayList;

public class Projection extends Operator {

	ArrayList<Attribute> newAttributeList;
	private String attributePredicate;

	public Projection(Operator child, String attributePredicate) {

		this.attributePredicate = attributePredicate;
		this.child = child;
		newAttributeList = new ArrayList<Attribute>();
	}

	/**
	 * Return the data of the selected attribute as tuple format
	 * 
	 * @return tuple
	 */
	@Override
	public Tuple next() {
		// Delete the lines below and add your code here
		Tuple tuple = child.next();

		if( tuple == null)
			return null;
		
		newAttributeList = new ArrayList<Attribute>();
		for(int i = 0;i < tuple.getAttributeList().size();i++){
			if(tuple.getAttributeList().get(i).getAttributeName().equals(attributePredicate)){
				newAttributeList.add(tuple.getAttributeList().get(i));
			}
		}
		return new Tuple(newAttributeList);
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