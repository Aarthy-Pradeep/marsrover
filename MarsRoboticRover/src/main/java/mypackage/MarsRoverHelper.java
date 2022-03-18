package mypackage;


/**
 * @author Aarthy
 *
 */
public class MarsRoverHelper {
	
	/**
	 * Method to validate the Rover inputs, create rover and add to rover list in plateau
	 * @param plateau
	 * @param roverInput
	 * @return Rover
	 */
	public static Rover validateInputAndCreateRover(Plateau plateau, String roverInput) {
		Rover rover = null;
		String[] roverInputArr = roverInput.split(MarsRoverConstants.BLANK_SPACE);
		Coordinate roverCoordinate = validateCoordinates(roverInputArr);
		try {
			if(roverCoordinate != null) {
				if(checkRoverInPlateauRange(roverCoordinate,plateau.getCoordinate()) 
						&& roverInputArr.length >=3) {
					rover = new Rover(roverCoordinate, roverInputArr[2].charAt(0));
					plateau.addRover(rover);
				}else {
					throw new MarsRoverException("The Rover orientation is not defined.");
				}
			}
		}catch(MarsRoverException roverException) {
			System.out.println("MarsRoverException:"+roverException.getMessage());
		}
		return rover;
	}

	/**
	 * Method to validate the coordinate input if it is a valid number and is complete
	 * @param coordinatesInput
	 * @return Coordinate
	 */
	public static Coordinate validateCoordinates(String[] coordinatesInput) {
		Coordinate coordinate = null;
		try {
			if(coordinatesInput.length >=2 && 
					!coordinatesInput[0].isBlank() && !coordinatesInput[1].isBlank()) {
				coordinate = new Coordinate(Integer.parseInt(coordinatesInput[0]), 
						Integer.parseInt(coordinatesInput[1]));
			}else {
				throw new MarsRoverException("The coordinates are not complete. Its not a valid number.");
			}
		}catch (NumberFormatException nfe) {
			System.out.println("The coordinates are not a valid number.");
		}catch(MarsRoverException roverException) {
			System.out.println("MarsRoverException:"+roverException.getMessage());
		}
		return coordinate;
	}
	
	/**
	 * Method to check if the rover is within plateau range based on its coordinates
	 * @param roverCoordinate
	 * @param plateauCoordinate
	 * @return boolean
	 */
	private static boolean checkRoverInPlateauRange(Coordinate roverCoordinate,Coordinate plateauCoordinate) {
		boolean isPositionInRange = false;
		if(roverCoordinate.getX() <= plateauCoordinate.getX() &&
				roverCoordinate.getY() <= plateauCoordinate.getY()) {
			isPositionInRange = true;
		} else {
			System.out.println("Rover position is beyond the plateau dimensions.");
		}
		return isPositionInRange; 
	}
	
	
	/**
	 * Method to process all the rover instructions in rover list
	 * @param plateau
	 */
	public static void processInstructions(Plateau plateau) {
		for(Rover rover : plateau.getRovers()) {
			if(rover != null && rover.getInstructions()!= null) { 
				for(char instruction : rover.getInstructions()) {
					switch (instruction) {
						case 'l':
						case 'L' : rover.turnLeft();
								   break;
						case 'r' :
						case 'R' : rover.turnRight();
								   break;
						case 'm' :
						case 'M' : rover.move(plateau.getCoordinate());
						   		   break;
						default :  System.out.println("Invalid Instruction :"+instruction+". Processing next instruction.");
								   break;
						
					}
				}
				System.out.println("Final Rover position and orientation:"+rover.getCoordinate().toString()+" "+rover.getOrientation());
				
			}
		}
	}

	/**
	 * Method to validate and set user input for rover instructions 
	 * @param rover
	 * @param roverInstructions
	 */
	public static void validateAndSetRoverInstructions(Rover rover, String roverInstructions) {
		if(!roverInstructions.isBlank()) {
			rover.setInstructions(roverInstructions.toCharArray());
		}
	}
}
