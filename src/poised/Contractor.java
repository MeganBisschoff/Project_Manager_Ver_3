/* Refactored:
 * Created Contractor subclass to inherit properties from the Person parent class. */

package poised;

/**
 * Subclass to create an Contractor object with information about the person responsible 
 * for constructing the building project for Poised. 
 * Inherits all attributes and methods from the Person super class. 
 * Contractor objects are set as the <code>contractor</code> attribute of the Project object. 
 * 
 * @author Megan Bisschoff 
 * @version 1.0
 * @see Person.java 
 */
public class Contractor extends Person {

	/**
	 * An empty constructor without parameters. 
	 * Contractor objects are instantiated before attributes are set to provide a valid 
	 * and unique instance of the Contractor object.
	 * 
	 * @return an Contractor object of the Person data type.
	 */
	public Contractor() {
	}
	
	/**
	 * Prints all the attributes set for a Contractor. 
	 * This method is implemented in the Person class and for writing the object details to the 
	 * <code>CompletedProjects.txt</code> file.
	 * 
	 * @return output 	a string of the contractor's full name, phone number, email and address					
	 */
	@Override
	public String toString() {
		String output = "\n\n--- Contractor's Information ---\n";
		output += "\nFull Name: \t\t" + fullName;
		output += "\nContact Number: \t" + contactNumber;
		output += "\nEmail: \t\t\t" + email;
		output += "\nAddress: \t\t" + address;
		return output;
	}

}
