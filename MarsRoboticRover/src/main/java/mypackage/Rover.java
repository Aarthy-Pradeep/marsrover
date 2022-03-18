/**
 * 
 */
package mypackage;

/**
 * @author Aarthy
 *
 */
public class Rover {

	private Coordinate coordinate;
	private Orientation orientation;
	private char[] instructions;

	/**
	 * @param coordinate
	 * @param orientationInput
	 */
	public Rover(Coordinate coordinate, char orientationInput) {
		super();
		this.coordinate = coordinate;
		checkAndSetIfOrientationValid(orientationInput);
	}

	/**
	 * @return the coordinate
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * @param coordinate the coordinate to set
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * @return the orientation
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * @param orientation the orientation to set
	 */
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	/**
	 * @return the instructions
	 */
	public char[] getInstructions() {
		return instructions;
	}

	/**
	 * @param instructions the instructions to set
	 */
	public void setInstructions(char[] instructions) {
		this.instructions = instructions;
	}

	@Override
	public String toString() {
		return "Rover [coordinate=" + coordinate + ", orientation=" + orientation + "]";
	}

	/**
	 * Method to set the corresponding rover orientation whenever it turns left
	 */
	public void turnLeft() {
		switch (this.orientation) {
		case N:
			setOrientation(Orientation.W);
			break;
		case E:
			setOrientation(Orientation.N);
			break;
		case S:
			setOrientation(Orientation.E);
			break;
		case W:
			setOrientation(Orientation.S);
			break;
		}
	}

	/**
	 * Method to set the corresponding rover orientation whenever it turns right
	 */
	public void turnRight() {
		switch (this.orientation) {
		case N:
			setOrientation(Orientation.E);
			break;
		case E:
			setOrientation(Orientation.S);
			break;
		case S:
			setOrientation(Orientation.W);
			break;
		case W:
			setOrientation(Orientation.N);
			break;
		}
	}

	/**
	 * Method to move the rover in the direction of its orientation only when it is
	 * within the plateau range after its move
	 * 
	 * @param plateauCoordinate
	 */
	public void move(Coordinate plateauCoordinate) {
		Coordinate coordinate = this.coordinate;
		boolean isDimInPlateauRange = false;
		switch (this.orientation) {
		case N:
			if (isCoordinateInRange(coordinate.getY() + 1, plateauCoordinate.getY())) {
				coordinate.setY(coordinate.getY() + 1);
				isDimInPlateauRange = true;
			}
			break;
		case E:
			if (isCoordinateInRange(coordinate.getX() + 1, plateauCoordinate.getX())) {
				coordinate.setX(coordinate.getX() + 1);
				isDimInPlateauRange = true;
			}
			break;
		case S:
			if (isCoordinateInRange(coordinate.getY() - 1, plateauCoordinate.getY())) {
				coordinate.setY(coordinate.getY() - 1);
				isDimInPlateauRange = true;
			}
			break;
		case W:
			if (isCoordinateInRange(coordinate.getX() - 1, plateauCoordinate.getX())) {
				coordinate.setX(coordinate.getX() - 1);
				isDimInPlateauRange = true;
			}
			break;
		}
		if (!isDimInPlateauRange) {
			System.out.println("Rover position will be beyond Plateau range. So skipping this move. ");
		}

	}

	/**
	 * Method to check if the given rover dimension is within the plateau dimension
	 * 
	 * @param roverDim
	 * @param plateauDim
	 * @return isInRange
	 */
	private boolean isCoordinateInRange(int roverDim, int plateauDim) {
		boolean isInRange = false;
		if (roverDim <= plateauDim && roverDim >= 0)
			isInRange = true;
		return isInRange;
	}

	/**
	 * Method to set the rover orientation based on user input
	 * 
	 * @param roverOrientation
	 */
	private void checkAndSetIfOrientationValid(char roverOrientation) {
		try {
			switch (roverOrientation) {
			case 'N':
			case 'n':
				setOrientation(Orientation.N);
				break;

			case 'E':
			case 'e':
				setOrientation(Orientation.E);
				break;

			case 'W':
			case 'w':
				setOrientation(Orientation.W);
				break;

			case 'S':
			case 's':
				setOrientation(Orientation.S);
				break;

			default:
				throw new MarsRoverException("Rover orientation is not valid.");
			}
		} catch (MarsRoverException roverException) {
			System.out.println("MarsRoverException : " + roverException.getMessage());
		}
	}

}
