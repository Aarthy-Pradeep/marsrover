/**
 * 
 */
package mypackage;

import java.util.Scanner;

/**
 * @author Aarthy
 *
 */
public class MarsRoboticRovers {

	private static Plateau plateau = null;

	/**
	 * Method to receive user inputs for plateau and rovers and process the rover
	 * instructions to display the final rover position
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to Mars Robotic Rovers");
		System.out.println("Enter the Plateau Upper right coordinates(separated by space):");

		Scanner in = new Scanner(System.in);

		// Scanning plateau coordinates
		String plateauCoordinatesInput = in.nextLine();
		String[] plateauCoordinatesArr = plateauCoordinatesInput.split(MarsRoverConstants.BLANK_SPACE);
		Coordinate plateauCoordinate = MarsRoverHelper.validateCoordinates(plateauCoordinatesArr);
		if (plateauCoordinate != null) {
			plateau = new Plateau(plateauCoordinate);
			String addAnotherRover = MarsRoverConstants.ADD_ANOTHER_ROVER_N;

			// Scanning Rover position, orientation and instructions
			do {
				System.out.println("Enter the Rover position and orientation(separated by space):");
				String roverInput = in.nextLine();
				Rover rover = MarsRoverHelper.validateInputAndCreateRover(plateau, roverInput);

				// Scanning Rover Instructions
				if (rover != null && rover.getOrientation() != null) {
					System.out.println("Enter the instructions to the Rover:");
					String roverInstructions = in.nextLine();
					MarsRoverHelper.validateAndSetRoverInstructions(rover, roverInstructions);
				}
				System.out.println("Do you want to add another Rover(Y/N):");
				addAnotherRover = in.nextLine();
			} while (addAnotherRover.equalsIgnoreCase(MarsRoverConstants.ADD_ANOTHER_ROVER_Y));

			// Process rover instructions
			MarsRoverHelper.processInstructions(plateau);
		}

		in.close();
	}

}
