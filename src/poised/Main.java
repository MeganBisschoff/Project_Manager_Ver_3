/* Refactored:
 * Moved all methods from main class to the associated Persona nd Project classes.
 * Reduced nesting of if/else logic and improved efficiency with switch statements and 
 * arrow case labels to eliminate the need for break statements and prevent fall through.
 * Validated all inputs when selecting a menu choice. */

package poised;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * The main code for the Poised Project Management System that displays various program menu 
 * options and executes operations according to the user's input.
 *
 * @author Megan Bisschoff
 * @version 1.00
 */
public class Main {

	/**
	 * Initiliases a <code>PoiseProjects.txt</code> file to store details about current projects,
	 * and a <code>CompletedProjects.txt</code> file to store details about completed projects.
	 * Creates a list of current projects. 
	 * <p>
	 * The main prgram menu is displayed and a <code>switch</code> block handles the menu options 
	 * and executes operations on the objects in list selected by the user. 
	 * Users can choose to capture, search, view, edit and finalise specific projects from the main
	 * program menu and additional submenus. Users can edit the details of the customer, architect 
	 * or contractor of a specific project.
	 * 
	 * @param args
	 * @throws IOException
	 * @see Project.java
	 * @see Person.java
	 */
	public static void main(String[] args) throws IOException {
	
		// Open scanner to read user input.
		Scanner input = new Scanner(System.in).useLocale(Locale.ENGLISH);
		
		// Initiliase file to store details about current projects.
		File projectsFile = new File("PoiseProjects.txt");
		
		// Initiliase file to store details about completed projects.
		File completedProjectsFile = new File("CompletedProjects.txt");
		 
		// Call method to read text file and return a list of projects
		List<Project> projectList = ReadAndWriteToFile.readProjectsFromFile(projectsFile);
		
		// Print projects in list.
		for (Project projObject : projectList) {
				System.out.println(projObject);
		}
		
		String programMenu = "\n--- Poised Program Menu ---\n" 
				+ "\nEnter 1 to capture a new Poise project"
				+ "\nEnter 2 to search and view projects"
				+ "\nEnter 3 to edit and finalise a project"
				+ "\nEnter 4 to edit the contractors, architects and customers details"
				+ "\nEnter 5 to update file and exit the program"
				+ "\nEnter selection: ";
		
		String searchAndViewMenu = "\n--- Search and View Projects ---\n"
				+ "\nEnter 1 to search for a project"
				+ "\nEnter 2 to view all projects"
				+ "\nEnter 3 to view incomplete projects"
				+ "\nEnter 4 to view overdue projects"
				+ "\nEnter your selection: ";
		
		String editAndFinaliseMenu = "\n--- Edit Project Details ---\n"
				+ "\nEnter 1 to edit the projects total fee"
				+ "\nEnter 2 to edit the projects fee paid"
				+ "\nEnter 3 to edit the projects deadline"
				+ "\nEnter 4 to finalise the project"
				+ "\nEnter your selection: ";
		
		// Display program menu then get and validate user input before running program.
		while (true) {
			
			// Print main program menu and take user input for operation choice.
			System.out.println(programMenu);
			String inputChoice = input.nextLine();
		
			// Check if the inputted choice only has integers with no letters, 
			// then parse to validate menu choice.
			if (inputChoice.matches("[0-9]") && inputChoice.matches("[^a-zA-Z]+")) {
				int menuChoice = Integer.parseInt(inputChoice);
				
				// Switch through each case of the menu and perform selected program operation.
				switch (menuChoice) {
				
					// Capture new Project and Person object information
					case 1 -> {
						// Create and capture new Project object.
						System.out.println("\n--- Capture Project data ---");
						Project newPoiseProject = new Project();
						newPoiseProject.captureProject(newPoiseProject);
			
						System.out.println("\n--- Capture Customer Details ---");
						Person projectCustomer = new Customer();
						newPoiseProject.setCustomer(projectCustomer);
						projectCustomer.capturePersonData(projectCustomer);
						
						System.out.println("\n--- Capture Architect Details ---");
						Person projectArchitect = new Architect();
						newPoiseProject.setArchitect(projectArchitect);
						projectArchitect.capturePersonData(projectArchitect);
			
						System.out.println("\n--- Capture Contractor Details ---");
						Person projectContractor = new Contractor();
						newPoiseProject.setContractor(projectContractor);
						projectContractor.capturePersonData(projectContractor);
						
						// Set project name if it was left empty.
						newPoiseProject.createProjectName(newPoiseProject, projectCustomer);
						
						// Display object information captured.
						System.out.println("\n--- Information captured for Project " 
								+ newPoiseProject.getProjNumber() + " ---\n" + newPoiseProject.toString());
						
						// Update project arraylist then write contenets to file
						projectList.add(newPoiseProject);
						System.out.println("print after add to list: " + projectList);
						ReadAndWriteToFile.writeProjectToFile(projectsFile, newPoiseProject);
					}
					// Search and View Projects.
					case 2 -> {
						System.out.println(searchAndViewMenu);
						String case2Choice = input.nextLine();
						int searchOrViewChoice = Integer.parseInt(case2Choice);
						
						switch (searchOrViewChoice) {
							case 1 -> System.out.println
									(SearchAndViewProjects.searchProj(projectList));
							case 2 -> SearchAndViewProjects.viewAllProj(projectList);
							case 3 -> SearchAndViewProjects.viewIncompleteProj(projectList);
							case 4 -> SearchAndViewProjects.viewOverdueProj(projectList);
							default -> System.out.println("Invalid entry. Please try again");
						}
					}
					// Edit and Finalise Project.
					case 3 -> {
						Project selectedProject = SearchAndViewProjects.searchProj(projectList);
						
						System.out.println(editAndFinaliseMenu);
						String case3Choice = input.nextLine();
						int editAndFinaliseChoice = Integer.parseInt(case3Choice);
						
						switch (editAndFinaliseChoice) {
							case 1 -> EditAndFinaliseProject.editProjFeeTotal
								(selectedProject, projectList);
							case 2 -> EditAndFinaliseProject.editProjFeePaid
								(selectedProject, projectList);
							case 3 -> EditAndFinaliseProject.editProjDeadline
								(selectedProject, projectList);
							case 4 -> { 
								EditAndFinaliseProject.markProjFinalisation
										(selectedProject, projectList);
								EditAndFinaliseProject.generateInvoice
										(selectedProject, projectList);
								ReadAndWriteToFile.writeCompletedProjectToFile
										(completedProjectsFile, selectedProject);
								projectList.remove(selectedProject);
							}
							default -> System.out.println("Invalid entry. Please try again");
						}
					}
					// Edit the Customer, Architects and Contractors details.
					case 4 -> {
						System.out.println("--- Edit Contractors, Architects "
								+ "and Customers Details ---");
						
						// Call method to search for the project of the person to be edited.
						Project projectPerson = SearchAndViewProjects.searchProj(projectList);
						
						// Ask user to input which person they want to edit.
						System.out.println("\nWhich persons details would you like to edit?"
								+ "\nEnter 1 to edit the customer"
								+ "\nEnter 2 to edit the architect"
								+ "\nEnter 3 to edit the contractor"
								+ "\nEnter selection: ");
						int editPerson = input.nextInt();	

						// Call method to edit the persons data of the selected project.
						switch (editPerson) {
							case 1 -> projectPerson.getCustomer()
								.editPersonData(projectPerson.getCustomer());
							case 2 -> projectPerson.getArchitect()
								.editPersonData(projectPerson.getArchitect());
							case 3 -> projectPerson.getContractor()
								.editPersonData(projectPerson.getContractor());
							default -> System.out.println("Invalid entry. Please try again");
						}
					}
					// Write the updated projects in the list to file when the Program is exited.
					case 5 -> {
						ReadAndWriteToFile.writeListToFile(projectsFile, projectList);
						System.out.println("\nPoised projects updated in file."
							+ "\n--- Thank you for using the Poise project manager ---"); 
						System.exit(0);
					}
					// Default case set to throw exception and notify user of invalid input.
					default -> {
						System.out.println("Invalid menu choice, please try again.");
						throw new IllegalArgumentException();
					}
				} 
			}
			// Else if menu input choice contains letters and no numbers, notify of invalid input.
			else if (inputChoice.matches("[^0-9]") && inputChoice.matches("[a-zA-Z]+")) {
				System.out.println("Invalid entry, ensure input choice contains numbers only.");
			}
			else {
				break;
			}
		} 
		// Close scanner object when while loop breaks.
		input.close();	
	}

}

