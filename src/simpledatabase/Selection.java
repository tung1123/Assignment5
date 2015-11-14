package simpledatabase;

import java.util.ArrayList;
import java.util.Arrays;

public class Selection extends Operator {

	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;

	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate,
			String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();

	}

	/**
	 * Get the tuple which match to the where condition
	 * 
	 * @return the tuple
	 */
	@Override
	public Tuple next() {
		// Delete the lines below and add your code here
		if (!child.from.equals(whereTablePredicate))
			return child.next();

		Tuple tuple = child.next();
		while (tuple != null){
			for (int i = 0; i < child.getAttributeList().size(); i++) {
				if (child.getAttributeList().get(i).getAttributeName().equals(whereAttributePredicate)
						&& child.getAttributeList().get(i).getAttributeValue().equals(whereValuePredicate)) {
					attributeList = child.getAttributeList();
					return tuple;
				}
				
			}
			tuple = child.next();
		}
		return null;
	}

	/**
	 * The function is used to get the attribute list of the tuple
	 * 
	 * @return the attribute list
	 */
	public ArrayList<Attribute> getAttributeList() {

		return (child.getAttributeList());
	}
	
	public static void main(String[] args){
		 String selectText;
	     String fromText;
	     String joinText;
	     String whereText;
	     String orderText;
	     selectText = "Name";
	        fromText = "Student";
	        joinText = "CourseEnroll";
	        whereText = "CourseEnroll.courseID=\"COMP2021\"";
	        orderText = "";
	        Operator table = new Table("CourseEnroll");
	    	Operator selection = new Selection(table,"CourseEnroll","courseID","\"COMP2021\"");
	    	Tuple tuple = selection.next();
	    	while(tuple != null){
	    		for(int i = 0;i <tuple.getAttributeList().size();i++ ){
		    		System.out.print(tuple.getAttributeList().get(i).getAttributeName());
					System.out.print(" "+tuple.getAttributeList().get(i).getAttributeType().type.toString());
					System.out.print(" "+tuple.getAttributeList().get(i).getAttributeValue());
					System.out.println();
		    	}
	    		tuple = selection.next();
	    	}
	    	
	    	
	}

}