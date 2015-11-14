package simpledatabase;

import java.util.ArrayList;

public class Join extends Operator {

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;

	// Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate) {
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();

	}

	/**
	 * It is used to return a new tuple which is already joined by the common
	 * attribute
	 * 
	 * @return the new joined tuple
	 */
	// The record after join with two tables
	@Override
	public Tuple next() {
		Tuple tuple1 = leftChild.next() , tuple2= rightChild.next();
		
		while(tuple1 != null){
			tuples1.add(tuple1);	
			tuple1 = leftChild.next();
		}
		
		while(tuple2 != null){
			int row = 0;
			while(row < tuples1.size()){
				tuple1 = tuples1.get(row);
				
				for(int i = 0;i < tuple2.getAttributeList().size();i++){
					for(int j = 0;j < tuple1.getAttributeList().size();j++){
						if(tuple2.getAttributeName(i).equals(tuple1.getAttributeName(j))){
							if(tuple2.getAttributeValue(i).equals(tuple1.getAttributeValue(j))){
								newAttributeList = new ArrayList<Attribute>();
								for(int k = 0; k <tuple1.getAttributeList().size() ;k++){
									newAttributeList.add(tuple1.getAttributeList().get(k));
								}
								for(int l = 0; l <tuple2.getAttributeList().size() ;l++){
									if(l != i)
										newAttributeList.add(tuple2.getAttributeList().get(l));
								}
								return new Tuple(newAttributeList);
							}
						}
					}
				}
				row++;
			}
			tuple2= rightChild.next();
		}
		// Delete the lines below and add your code here
		return null;
	}

	/**
	 * The function is used to get the attribute list of the tuple
	 * 
	 * @return attribute list
	 */
	public ArrayList<Attribute> getAttributeList() {
		if (joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return (newAttributeList);
	}

}