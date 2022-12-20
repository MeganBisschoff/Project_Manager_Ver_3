/* Refactored:
 * Created Architect subclass to inherit properties from the Person parent class. */

package poised;

/**
 * Subclass to create an Architect object with information about the person responsible
 * for designing the building project for Poised. 
 * Inherits all attributes and methods from the Person super class. 
 * Architect objects are set as the <code>architect</code> attribute of the Project object. 
 * 
 * @author Megan Bisschoff 
 * @version 1.0
 * @see Person.java 
 */
public class Architect extends Person {

	/**
	 * An empty constructor without parameters. 
	 * Architect objects are instantiated before attributes are set to provide a valid 
	 * and unique instance of the Architect object.
	 * 
	 * @return an Architect object of the Person data type.
	 */
	public Architect() {
	}

	/**
	 * Prints all the attributes set for an Architect. 
	 * This method is implemented in the Person class and for writing the object details to the 
	 * <code>CompletedProjects.txt</code> file.
	 * 
	 * @return output 	a string of the architect's full name, phone number, email and address					
	 */
	@Override
	public String toString() {
		String output = "\n\n--- Architect's Information ---\n";
		output += "\nFull Name: \t\t" + fullName;
		output += "\nContact Number: \t" + contactNumber;
		output += "\nEmail: \t\t\t" + email;
		output += "\nAddress: \t\t" + address;
		return output;
	}

}
