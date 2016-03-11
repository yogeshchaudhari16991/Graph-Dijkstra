
import java.util.List;

import GeographicPoint;

/**
 * @author Yogesh
 *
 */
public class MapNode implements Comparable {
	private GeographicPoint location;
	private List<MapEdge> neighbors;
	private double length;
	
	public MapNode(){
		//construct new empty MapNode
	}
	
	public MapNode(GeographicPoint location, List<MapEdge> neighbors){
		this.location = location;
		this.neighbors = neighbors;
	}
	
	public MapNode(GeographicPoint location, double length){
		this.location = location;
		this.length = length;
	}
	
	public GeographicPoint getLocation(){
		return location;
	}
	
	public List<MapEdge> getNeighbor(){
		return neighbors;
	}
	
	public double getLength(){
		return length;
	}
	
	public void setLength(double length){
		this.length = length;
	}
	
	public boolean addNeighbor(MapEdge edge){
		if(! neighbors.contains(edge))
			if(neighbors.add(edge))
				return true;
		return false;
	}
	
	public int getNumOfNeighbors(){
		return neighbors.size();
	}
	
	@Override
	public int compareTo(Object o1){
		MapNode obj1 = (MapNode)o1;
		MapNode obj2 = this;
		if(obj2.getLength() >= obj1.getLength()){
			return 1;
		}else {
				return -1;
		}
	}
}
