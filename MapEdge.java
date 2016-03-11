/* 
* @author: Yogesh
*
*/

import GeographicPoint;

public class MapEdge {
	
	private GeographicPoint start;
	private GeographicPoint end;
	private String streetName;
	private double length;
	private String streetType;
	
	public MapEdge()
	{
		//create new empty MapEdge
	}
	
	public MapEdge(GeographicPoint start, GeographicPoint end, String streetName, 
			double length, String streetType){
		this.start = start;
		this.end = end;
		this.streetName = streetName;
		this.length = length;
		this.streetType = streetType;
	}
	
	public GeographicPoint getStart(){
		return start;
	}
	
	public GeographicPoint getEnd(){
		return end;
	}
	
	public String getStreetName(){
		return streetName;
	}
	
	public String getStreetType(){
		return streetType;
	}
	
	public double getLength(){
		return length;
	}
}
