import java.util.Collection;
import java.util.HashMap;

/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState
{
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;
    private HashMap<Location, Waypoint> openWaypoint = new HashMap<>(); // создание полей для открытых и закрытых точек
    private HashMap<Location, Waypoint> closeWaypoint = new HashMap<>();

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
    public Waypoint getMinOpenWaypoint() // проверка открытых вершин, возвращение вершины(ссылки на нее)
    // с наименьшей стоимостью, если в открытом наборе нет вершин возврат - null
    {
        if (openWaypoint.isEmpty()) { // проверка пустотности
            return null;
        }
        Collection<Waypoint> arrayList = openWaypoint.values();// создание коллекции, для записи значений открытых точек
        double min = Double.MAX_VALUE;
        Waypoint finalWaypoint = null;
        for (Waypoint waypoint : arrayList) { // цикл для всех значений
            if (waypoint.getTotalCost() < min) { // проверка getTotalCost, чтобы был минимален,
                // если минимален то записываем объект waypoint в finalWaypoint
                min = waypoint.getTotalCost();
                finalWaypoint = waypoint;
            }
        }
        return finalWaypoint;
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
        Location location = newWP.getLocation(); // создание локации
        if (openWaypoint.containsKey(location)) { // проверка содержания локации(ключ) в открытых вершинах
            Waypoint waypoint = openWaypoint.get(location);
            if (newWP.getPreviousCost() < waypoint.getPreviousCost()) { // проверка выгодного условия
                openWaypoint.put(location, newWP); // добавление точки
                return true; // если локация есть и условия выгодное
            }
            return false; // если локация есть, но условие невыгодное
        }
        openWaypoint.put(location, newWP); // если не содержит добавляем
        return true;
    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints() // метод возвращающий размер нашего хэшмапа
    {
        // TODO:  Implement.
        return openWaypoint.size();
    }


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc)
    {
        // TODO:  Implement.
        closeWaypoint.put(loc, openWaypoint.get(loc)); // добавление вершины в закрытые из открытых
        openWaypoint.remove(loc); // удаление открытой вершины
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc) // если есть вершина в наборе закрытых вершин - true
    {
        // TODO:  Implement.
        return closeWaypoint.containsKey(loc);
    }
}