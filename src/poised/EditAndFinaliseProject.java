/* Refactored: Changed the method of validating user input to match default Project attributes.
 * Moved all project editing methods to a seperate class that extends the Project class. */

package poised;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/** 
 * Subclass that edits and sets new information attributed to a Project object from the 
 * <code>projectList</code> selected by the user. 
 * <p>
 * It inherits attributes from the Project parent class to edit the projects total
 * fee <code>editProjFeeTotal</code>, the fee paid <code>editProjFeePaid</code>, the deadline 
 * <code>Project</code>, as well as finalises the project <code>markProjFinalisation</code> and 
 * generates an invoice <code>generateInvoice</code>.
 * 
 * @author Megan Bisschoff
 * @Version 1.00
 * @see Project.java
 */
public abstract class EditAndFinaliseProject extends Project {
		
	// Open scanner object to read user input
	static Scanner userInput  = new Scanner(System.in); 
	
	// Empty constructor.
	private EditAndFinaliseProject() {
	}
	
	/**
	 * Captures, validates and sets the <code>projFeeTotal</code> of the 
	 * <code>selectedProject</code> object pulled from the <code>projectList</code>.
	 * Input is requested until the user input is validated with a regex pattern and parsed to a 
	 * double type. 
	 * 
	 * @param selectedProject	a project object selected by the user.
	 * @param projectList		a list of the current projects.
	 */
	// ----- Method to EDIT FEE TOTAL ----- //
	public static void editProjFeeTotal(Project selectedProject, List<Project> projectList) { 
		
		while (true) {
			System.out.println("\nEnter the project fee paid: ");
			String inputFeeTotal = userInput.nextLine();
		
			// Validate that input matches any number of digits then parse to a double type. 
			if (inputFeeTotal.matches("\\d+")) {
				Double newFeeTotal = Double.parseDouble(inputFeeTotal);
				selectedProject.setFeeTotal(newFeeTotal);
				System.out.println("\nFee paid successfully updated." + selectedProject);
				break;
			}
			else {
				System.out.println("Invalid fee, enter digits only with no spaces, dots or commas");
			}
		}
	}
	
	/**
	 * Captures, validates and sets the <code>projFeePaid</code> of the 
	 * <code>selectedProject</code> object pulled from the <code>projectList</code>.
	 * Input is requested until the user input is validated with a regex pattern and parsed to a 
	 * double type.
	 * 
	 * @param selectedProject	a project object selected by the user.
	 * @param projectList		a list of the current projects.
	 */
	// ----- Method to EDIT FEE PAID ----- //
	public static void editProjFeePaid(Project selectedProject, List<Project> projectList) { 
			
		while (true) {
			System.out.println("\nEnter the project fee paid: ");
			String inputFeePaid = userInput.nextLine();
		
			// Validate that input matches any number of digits then parse to a double type. 
			if (inputFeePaid.matches("\\d+")) {
				Double newFeePaid = Double.parseDouble(inputFeePaid);
				selectedProject.setFeePaid(newFeePaid);
				System.out.println("\nFee paid successfully updated." + selectedProject);
				break;
			}
			else {
				System.out.println("Invalid fee, enter digits only with no spaces, "
						+ "dots or commas");
			}
		}
	}
		
	/**
	 * Captures, validates and sets the <code>projDeadline</code> of the 
	 * <code>selectedProject</code> object pulled from the <code>projectList</code>.
	 * Input is requested until the user input is validated by being parsed to a 
	 * <code>LocalDate</code> type and formatted with the <code>DateTimeFormatter</code> class.
	 * 
	 * @param selectedProject	a project object selected by the user.
	 * @param projectList		a list of the current projects.
	 */	
	// ----- Method to EDIT PROJECT DEADLINE ----- //
	public static void editProjDeadline(Project selectedProject, List<Project> projectList) {
	
		// Set pattern for date format(carried forward from Project.)  
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern(DATE_FORMAT);
		
		//...
		while (true) {
			System.out.println("Enter projects deadline as yyyy-mm-dd with dashes: ");
			String strDate = userInput.nextLine();
			try {
				// Format string deadline to date format, and set new due date.
				LocalDate formatStrDate = LocalDate.parse(strDate, formatDate);
				selectedProject.setProjDeadline(formatStrDate);
				System.out.println("\nDeadline successfully updated." + selectedProject);
				break;
			} 
			catch (Exception e) {
				System.out.println(strDate + " is not a valid date, please try again.");
			}
		}
	}
	
	/**
	 * Captures, validates and sets the <code>projCompletionDate</code> and the 
	 * <code>projFinalised</code> status to true of the <code>selectedProject</code> object 
	 * pulled from the <code>projectList</code>. 
	 * 
	 * Input for the completion date is requested until the user input is validated and parsed to a 
	 * <code>LocalDate</code> type and formatted with the <code>DateTimeFormatter</code> class.  
	 * 
	 * @param selectedProject	a project object selected by the user.
	 * @param projectList		a list of the current projects.
	 */
	// ----- Method to MARK PROJECT FINALISED ----- //
	public static void markProjFinalisation(Project selectedProject, List<Project> projectList) {

		// Set pattern for date format(carried forward from Project.)  
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern(DATE_FORMAT);
				
		// ...
		while (true) {
			System.out.println("Enter project completion date as yyyy-mm-dd with dashes: ");
			String strDate = userInput.nextLine();
			try {
				// Format string final date to date format, and set completion date.
				LocalDate formatStrDate = LocalDate.parse(strDate, formatDate);
				selectedProject.setProjCompletionDate(formatStrDate);
				
				// Set project as finalised.
				selectedProject.setProjFinalised(true);
				System.out.println("Project completion date and finalisation successfully updated."
						+ selectedProject);
				break;
			} 
			catch (Exception e) {
				System.out.println(strDate + " is not a valid date");
			}
		}		
	}
	
	/**
	 * Generates an invoice of the <code>selectedProject</code> object pulled from the 
	 * <code>projectList</code>. 
	 * 
	 * The outstanding fee is calculated by subtracting the projects <code>feeTotal</code> 
	 * from the <code>feePaid</code>. Once calculated, a string of the customers details from
	 * the Customer subclasses <code>toString</code> as well as the Projects <code>toString</code> 
	 * method is printed out with oustanding project fees.
	 * 
	 * @param selectedProject	a project object selected by the user.
	 * @param projectList		a list of the current projects.
	 */
	// Method to generate an invoice once the project is finalised
	public static void generateInvoice(Project selectedProject, List<Project> projectList) {

		// Calculate outstanding project fee.
		double outstandingFee = selectedProject.getFeeTotal() - selectedProject.getFeePaid();
		
		// If there is an oustanding fee, print an invoice with the projects balance.
		if (outstandingFee > 0) {
			System.out.println("\n--- INVOICE ---" 
					+ selectedProject.getCustomer()
					+ selectedProject.toString()  
					+ "\n\n--- Project Fees ---" 
					+ "\nTotal fee: \t\tR" + selectedProject.getFeeTotal()
					+ "\nTotal paid: \t\tR" + selectedProject.getFeePaid()
					+ "\nOutstanding balance: \tR " + outstandingFee);
		}
	}

}