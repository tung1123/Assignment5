package simpledatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Table extends Operator {
	private BufferedReader br = null;
	private boolean getAttribute=false;
	private Tuple tuple;

	
	public Table(String from){
		this.from = from;
		
		//Create buffer reader
		try{
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+from+".csv")));
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	
	/**
     * Create a new tuple and return the tuple to its parent.
     * Set the attribute list if you have not prepare the attribute list
     * @return the tuple
     */
	@Override
	public Tuple next(){
		//Delete the lines below and add your code here
		if(getAttribute == false){
			try {
				String attributeLine = br.readLine();
				String dataTypeLine = br.readLine();
				String tupleLine = br.readLine();
				tuple = new Tuple(attributeLine,dataTypeLine,tupleLine);
				tuple.setAttributeName();
				tuple.setAttributeType();
				tuple.setAttributeValue();
				getAttribute = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			try {
				String tupleLine = br.readLine();
				if (tupleLine != null) {
					ArrayList<Attribute> temp = new ArrayList<Attribute>();
					for(int i = 0;i < tuple.getAttributeList().size();i++){
						Attribute attribute = new Attribute();
						attribute.attributeName = tuple.getAttributeName(i);
						attribute.attributeType = tuple.getAttributeType(i);
						attribute.attributeValue = tuple.getAttributeValue(i);
						temp.add(attribute);
						
					}
							tuple.getAttributeList();
					String col[] = tupleLine.split(",");
					
					for(int i = 0;i<temp.size();i++){
						temp.get(i).setAttributeValue(temp.get(i).attributeType, col[i]);
						tuple = new Tuple(temp);
					}
				}
				else{
					return null;
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return tuple;
	}
	

	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return tuple.getAttributeList();
	}
	/*
	public static void main(String[] args){
		Table table = new Table("Student");
		while(table.next() != null){
			 ArrayList<Attribute> list = table.getAttributeList();
			 for(int i = 0;i<list.size();i++){
					System.out.print(list.get(i).getAttributeName());
					System.out.print(" "+list.get(i).getAttributeType().type.toString());
					System.out.print(" "+list.get(i).getAttributeValue());
					System.out.println();
					
				}
			
		}
	}*/
	
}