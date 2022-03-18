package mypackage;

import java.util.ArrayList;

public class Plateau {

	private Coordinate coordinate;
	private ArrayList<Rover> rovers = new ArrayList<Rover>();

	/**
	 * @param coordinate
	 */
	public Plateau(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	
	/**
	 * @return the coordinate
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}


	public void addRover(Rover rover) {
		rovers.add(rover);
	}

	/**
	 * @return the rovers
	 */
	public ArrayList<Rover> getRovers() {
		return rovers;
	}
	
}
