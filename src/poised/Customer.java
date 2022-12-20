/* Refactored:
 * Created Customer subclass to inherit properties from the Person parent class. */

package poised;

/**
 * Subclass to create an Customer object with information about the person for which the Poise
 * building project is being designed and constructed for. 
 * Inherits all attributes and methods from the Person super class. 
 * Customer objects are set as the <code>customer</code> attribute of the Project object. 
 * 
 * @author Megan Bisschoff 
 * @version 1.0
 * @see Person.java 
 */
public class Customer extends Person {

	/**
	 * An empty constructor without parameters. 
	 * Customer objects are instantiated before attributes are set to provide a valid 
	 * and unique instance of the Customer object.
	 * 
	 * @return a Customer object of the Person data type.
	 */
	public Customer() {
	}
	
	/**
	 * Prints all the attributes set for a Customer. 
	 * This method is implemented in the Person class and for writing the object details to the 
	 * <code>CompletedProjects.txt</code> file.
	 * 
	 * @return output 	a string of the customer's full name, phone number, email and address					
	 */
	@Override
	public String toString() {
		String output = "\n\n--- Customer's Information ---\n";
		output += "\nFull Name: \t\t" + fullName;
		output += "\nContact Number: \t" + contactNumber;
		output += "\nEmail: \t\t\t" + email;
		output += "\nAddress: \t\t" + address;
		return output;
	}

}
