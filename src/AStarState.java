/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
import java.util.HashMap;
public class AStarState
{
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;


    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    private HashMap<Location, Waypoint> openWP = new HashMap<Location, Waypoint>();
    private HashMap<Location, Waypoint> closeWP = new HashMap<Location, Waypoint>();

    public Waypoint getMinOpenWaypoint()
    {
        if (openWP.isEmpty())
            return null;
        else {
            float min = Float.MAX_VALUE;
            Location loc = new Location();
            for (Location key : openWP.keySet()) {
                if (openWP.get(key).getTotalCost()<min){
                    min = openWP.get(key).getTotalCost();
                    loc = openWP.get(key).getLocation();
                }

            }
            return openWP.get(loc);
        }
    }

    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        if(openWP.containsKey(newWP.getLocation()) && openWP.get(newWP.getLocation()).getPreviousCost()>newWP.getPreviousCost()){
            openWP.put(newWP.getLocation(),newWP);
            return true;
        }else
        if(!openWP.containsKey(newWP.getLocation())){
            openWP.put(newWP.getLocation(),newWP);
            return true;
        }else return false;
    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints()
    {
        if (openWP.isEmpty())
            return 0;
        else
            return openWP.size();
    }


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc)
    {
        Waypoint WP = openWP.get(loc);
        openWP.remove(loc);
        closeWP.put(loc, WP);
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc)
    {
        if (closeWP.containsKey(loc))
            return true;
        else return false;
    }
}
