import java.util.Map;

/**
 * This class contains the logic for moving a mountaineer through a
 * mountainous region. Essentially, the method startHike() will
 * simulate the mountaineer moving through the region, by always
 * having the mountaineer choose a path forward, such that the
 * absolute change in elevation is the smallest possible, given the
 * different alternatives
 * 
 * @author Kristian Nybom
 *
 */
public class MountainPath {

	private Topography topo;     // The topography of the mountainous region 
	private Mountaineer mount;   // The mountaineer moving through the mountainous region

	// directions
	public final static int DIRECTION_NOT_FOUND = -1;
	public final static Integer NE = 0;
	public final static Integer E =  1;
	public final static Integer SE = 2;

	// moves that can be made
	public final static int[] NE_MOVE = {-1,1};
	public final static int[] E_MOVE = {0,1};
	public final static int[] SE_MOVE = {1,1};

	// mapping of the directions to their corresponding moves
	static final Map<Integer, int[]> MOVES = Map.of(
			NE, NE_MOVE,
			E, E_MOVE,
			SE, SE_MOVE
	);

	public MountainPath(String filename) {
		
		/*
		 * DO NOT MODIFY THE CODE IN THIS CONSTRUCTOR
		 */
		
		topo = new Topography(filename);  // Create Topography object
		mount = new Mountaineer();        // Create the mountaineer
		
		// Set the starting location of the mountaineer
		mount.setStartingPoisition(topo.findLowestStartingPoint());
		
		// Display the current location of the mountaineer.
		System.out.println(mount);
	}

	/**
	 * TODO: Implement this method
	 * 
	 * This method should control the movement of the mountaineer
	 * through the mountainous region. When the mountaineer
	 * reaches the east end of the map, the message already
	 * implemented in this method should be displayed.
	 */
	public void startHike() {
		/*
		 * Insert your code here.
		 */



		int direction = DIRECTION_NOT_FOUND;
		do {
			direction = scanNeighbours();
			System.out.println(mount);
		} while(
				move(direction)
		);

		/*
		 * DO NOT MODIFY THE System.out.print BELOW!
		 * DO NOT ADD ANY CODE AFTER THE System.out.print BELOW!
		 */
		System.out.print("Mountaineer reached the end of the map. Total elevation change: " + mount.getElevationChange());
	}
	
	/**
	 * TODO: Implement this method
	 * 
	 * Scans neighbouring cells and returns an integer, representing the direction to move in.
	 * The direction is chosen as the smallest absolute change in elevation when moving in that
	 * direction, based on the mountaineers current location. Note: this method only determines
	 * the direction to move in, but it should not move the mountaineer anywhere.
	 * 
	 * @return An integer representing the direction to move in. Value 0 = NE, 1 = E, and 2 = SE.
	 * Should the return value be anything else, it means that the mountaineer has reached the
	 * border of the map
	 */
	public int scanNeighbours() {

		Cell location = mount.getLocation();
		int currentElevation = location.getElevation();
		int row = location.getRow();
		int column = location.getCol();

		int[] neighbouringElevations = topo.getNeighbouringElevations(row,column);

		int indexOfSmallest = DIRECTION_NOT_FOUND;
		int smallest = Integer.MAX_VALUE;

		// find index of the smallest height difference
		for (int i = 0; i < neighbouringElevations.length; i++) {
			if (neighbouringElevations[i] == Topography.OUTSIDE_BORDERS) {
				continue;
			}

			int heightDifference = Math.abs(
					currentElevation-neighbouringElevations[i]
			);

			if (heightDifference <= smallest) {
				smallest = heightDifference;
				indexOfSmallest = i;
			}
		}

		return indexOfSmallest;

	}
	
	/**
	 * TODO: Implement this method
	 * 
	 * Moves the mountaineer in the specified direction
	 * @param direction The value of the parameter corresponds to the return value of the method 
	 * scanNeighbours, i.e. value 0 = NE, 1 = E, and 2 = SE.
	 * @return true on successful movement, false at the end of map
	 */
	public boolean move(int direction) {
		if (direction == DIRECTION_NOT_FOUND) {
			return false;
		}

		int[] move = MOVES.get(direction);
		int rowMove = move[0];
		int columnMove = move[1];

		int newRow = mount.getLocation().getRow() + rowMove;
		int newColumn = mount.getLocation().getCol() + columnMove;

		Cell newCell = topo.getCell(newRow, newColumn);
		//System.out.println("Moving to " + newCell);

		// check if cell exists, if not then no move will take place
		if (newCell == null) {
			return false;
		}

		//System.out.println("Moving to cell" + newCell);
		// make actual move
		mount.moveToCell(newCell);
		return true;
	}
}
