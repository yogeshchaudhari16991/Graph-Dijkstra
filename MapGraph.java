/* 
* @author: Yogesh
*
*/

import java.util.PriorityQueue;
import java.util.Stack;
import java.util.Vector;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import GeographicPoint;
import util.GraphLoader;


public class MapGraph {
	public static final double INFINITY = 32656;
	
	private HashMap<GeographicPoint, MapNode> vertices;

	/** 
	 * Create a new empty MapGraph 
	 */
	
	public MapGraph()
	{
		vertices = new HashMap<GeographicPoint, MapNode>();
	}
	
	public int getNumVertices()
	{
		return vertices.size();
	}
	
	public Set<GeographicPoint> getVertices()
	{
		return vertices.keySet();
	}
	
	public int getNumEdges()
	{
		Set<GeographicPoint> verticesSet = getVertices();
		int numberOfVertices = getNumVertices();
		int numEdges = 0;
		for(GeographicPoint location : verticesSet){
			MapNode vertex = vertices.get(location);
			numEdges += vertex.getNumOfNeighbors();
		}
		return numEdges;
	}


	public boolean addVertex(GeographicPoint location)
	{
		if(location != null){
			if(! vertices.containsKey(location)){
				MapNode vertex = new MapNode(location, new ArrayList<MapEdge>());
				vertices.put(location, vertex);
				return true;
			}
		}
		return false;
	}
	
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {
			if(from != null && to !=  null && roadName != null && roadType != null
					&& vertices.containsKey(from) && vertices.containsKey(to) && length > 0){
				MapEdge edge = new MapEdge(from, to, roadName, length, roadType);
				MapNode vertex = vertices.get(from);
				vertex.addNeighbor(edge);
				vertices.put(from, vertex);
			}
			else{
				throw new IllegalArgumentException();
			}
	}
	
	public List<GeographicPoint> getPath(HashMap<GeographicPoint, GeographicPoint> parent, Vector<GeographicPoint> visited){
		List<GeographicPoint> pathToVertex = new ArrayList<GeographicPoint>();
		GeographicPoint goal = visited.get((visited.size()-1));
		pathToVertex.add(goal);
		GeographicPoint current  = goal;
		while(parent.containsKey(current)){
			pathToVertex.add(parent.get(current));
			current = parent.get(current);
		}
		List<GeographicPoint> pathToGoal = new ArrayList<GeographicPoint>();
		for(int i = pathToVertex.size()-1; i>=0; i--) {
			pathToGoal.add(pathToVertex.get(i));
		}
		return pathToGoal;
	}

	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal)
	{
		PriorityQueue<MapNode> queue = new PriorityQueue<MapNode>();
		HashMap<GeographicPoint, GeographicPoint> parent = new HashMap<GeographicPoint, GeographicPoint>();
		Vector<GeographicPoint> visited = new Vector<GeographicPoint>();
		
		//initialize all vertices with INFINITY distance
		for(GeographicPoint loc : vertices.keySet()){
			MapNode temp = new MapNode(loc, INFINITY);
			queue.add(temp);
		}
		
		//enqueue Start Vertex
		MapNode startNode = new MapNode(start, 0);
		queue.add(startNode);
		
		//run while queue is not empty
		while(! queue.isEmpty()){
			MapNode current = queue.remove();
			nodeSearched.accept(current.getLocation());
			//check if current node has been visited or not
			if(! visited.contains(current)){
				//mark current node visited
				visited.add(current.getLocation());
				//check if current node is goal node or not
				if(goal.equals(current.getLocation())){
					return getPath(parent, visited);
				}
				//iterate over all edges outgoing from current node
				for(MapEdge edge : vertices.get(current.getLocation()).getNeighbor()){
					GeographicPoint nextGeoLoc = edge.getEnd();
					MapNode next = vertices.get(nextGeoLoc);
					//check if next neighbor of current node is visited or not
					if(! visited.contains(nextGeoLoc)){
						//if not add it to queue with updated distance
						long distFromStart = (long)(current.getLength() + edge.getLength());
						if(queue.contains(next)){
							//if already in queue update distance
							//if new distance is smaller than previous distance
							if(next.getLength() > distFromStart){
								next.setLength(distFromStart);
								parent.put(next.getLocation(), current.getLocation());
								queue.add(next);
							}
						}
						else {
							//else add it to queue with distance
								next.setLength(distFromStart);
								queue.add(next);
								parent.put(next.getLocation(), current.getLocation());
							}
						}
					}
				}
			}
		return null;
	}

	public static void main(String[] args)
	{
		System.out.print("Making a new map...");
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoad the map and call dijkstra method...");
	}
	
}
