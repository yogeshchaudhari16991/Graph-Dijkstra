/* 
* @author: Yogesh
*
*/

public class GeographicPoint {
	
	public GeographicPoint(double latitude, double longitude)
	{
		super(latitude, longitude);
	}
	
    public String toString()
    {
    	return "Lat: " + getX() + ", Lon: " + getY();
    }

}
