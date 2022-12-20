package poised;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class that reads and writes project object information from and to an external database.
 * <p>
 * A list of Project attribute information is read from <code>PoiseProjects.txt</code> then 
 * populated into new Project objects and added to the <code>projectList</code>.
 * <p>
 * When a new project object is captured, the formatted string object is appended to the text file.
 * Finally, when the program closes the list of project objects is written to the text file and 
 * overwrites all data with the newly updated project data.
 * <p>
 * When a project object is marked as finalised, the string details are appended to 
 * <code>CompletedProjects.txt</code>.
 * 
 * @author Megan Bisschoff
 * @version 1.0
 */
public abstract class ReadAndWriteToFile {

	// Constant to format the dates read from file.
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static DateTimeFormatter formatDate = DateTimeFormatter.ofPattern(DATE_FORMAT);
	
	private ReadAndWriteToFile() {
	}
	
	/**
	 * Method reads each line form the text file and splits up the comma seperated values into 
	 * associated Project fields. New <code>Project</code> and <code>Person</code> objects are 
	 * initialised and the values are set into the respective Project and Person attributes. 
	 * <p>
	 * An array list is inititalised which returns each <code>poiseProjects</code> added to the 
	 * list.
	 * <li>Project number
	 * <li>Project name
	 * <li>Project type
	 * <li>Project addres
	 * <li>Project ERF number
	 * <li>Fee total
	 * <li>Fee paid
	 * <li>Project deadline
	 * <li>Project finalisation status
	 * <li>Customers full name
	 * <li>Customers contact number
	 * <li>Customers email
	 * <li>Customers address
	 * <li>Architects full name
	 * <li>Architects contact number
	 * <li>Architects email
	 * <li>Architects address
	 * <li>Contractors full name
	 * <li>Contractors contact number
	 * <li>Contractors email
	 * <li>Contractors address
	 * 
	 * @param projectsFile	the text file containing the object to be read.
	 * @return 				an arraylist of project objects.
	 * @throws IOException 	if the file can't be located and read from
	 */
	public static List<Project> readProjectsFromFile(File projectsFile) throws IOException {
		
		// Initiliase arraylist to store projects read from file.
		List<Project> projListFromFile = new ArrayList<Project>();

		// Read each line in file and split up elements into a string list.
		try (BufferedReader readFile = new BufferedReader(new FileReader(projectsFile))) {
			
			while (readFile.ready()) {
				String line = readFile.readLine();
				String[] attribute = line.split(",");
				
				// Assign attributes from string list to their respective Project values.
				String projNumber = attribute[0];
				String projName = attribute[1];
				String projType = attribute[2];
				String projAddress = attribute[3];
				int projErf = Integer.parseInt(attribute[4]);
				double feeTotal = Double.parseDouble(attribute[5]);
				double feePaid = Double.parseDouble(attribute[6]);
				LocalDate projDeadline = LocalDate.parse(attribute[7], formatDate);
				boolean projFinalised = Boolean.parseBoolean(attribute[8]);
				
				// Assign attributes from string list their respective Person values.
				String custFullName = attribute[9];
				String custContactNumber = attribute[10];
				String custEmail = attribute[11];
				String custAddress = attribute[12];
				String archiFullName = attribute[13];
				String archiContactNumber = attribute[14];
				String archiEmail = attribute[15];
				String archiAddress = attribute[16];
				String contrFullName = attribute[17];
				String contrContactNumber = attribute[18];
				String contrEmail = attribute[19];
				String contrAddress = attribute[20];
				
				// Create new Project and Person objects to assign attributes to. 
				Project poiseProjects = new Project();
				Person projectCustomer = new Customer();
				Person projectArchitect = new Architect();
				Person projectContractor = new Contractor();
				
				// Set attributes to the Project object.
				poiseProjects.setProjNumber(projNumber);
				poiseProjects.setProjName(projName);
				poiseProjects.setProjType(projType);
				poiseProjects.setProjAddress(projAddress);
				poiseProjects.setProjErf(projErf);
				poiseProjects.setFeeTotal(feeTotal);
				poiseProjects.setFeePaid(feePaid);
				poiseProjects.setProjDeadline(projDeadline);
				if (Boolean.TRUE.equals(projFinalised)) {
					poiseProjects.setProjFinalised(true);			
				} 
				poiseProjects.setCustomer(projectCustomer);
				poiseProjects.setArchitect(projectArchitect);
				poiseProjects.setContractor(projectContractor);
				
				// Set attributes to the Person objects.
				projectCustomer.setFullName(custFullName);
				projectCustomer.setContactNumber(custContactNumber);
				projectCustomer.setEmail(custEmail);
				projectCustomer.setAddress(custAddress);
				projectArchitect.setFullName(archiFullName);
				projectArchitect.setContactNumber(archiContactNumber);
				projectArchitect.setEmail(archiEmail);
				projectArchitect.setAddress(archiAddress);
				projectContractor.setFullName(contrFullName);
				projectContractor.setContactNumber(contrContactNumber);
				projectContractor.setEmail(contrEmail);
				projectContractor.setAddress(contrAddress);
				
				// Add projects from file to list to return to main menu for further operations.
				projListFromFile.add(poiseProjects);
			}
		}
		catch (IOException e) {
			System.err.println("Error reading projects from file." + e.getStackTrace());
		}
		return projListFromFile;
	}	

	/**
	 * Method gets Project and Person attributes and concatenates the details into a long string,
	 * with comma seperated values, used by the </code>writeListToFile<code> and 
	 * </code>writeProjectToFile<code> methods for writing to <code>CurrentProjects.txt</code>.
	 * 
	 * @param projectObjects	a Project object with captured fields 
	 * @return 					a string of Project details.
	 */
	private static String projectString(Project projectObjects) {
		return projectObjects.getProjNumber() + ","
				+ projectObjects.getProjName() + ","
				+ projectObjects.getProjType() + ","
				+ projectObjects.getProjAddress() + ","
				+ projectObjects.getProjErf() + ","
				+ projectObjects.getFeeTotal() + ","
				+ projectObjects.getFeePaid() + ","
				+ projectObjects.getProjDeadline() + ","
				+ projectObjects.getProjFinalised() + ","
				// Get and format customers details.
				+ projectObjects.getCustomer().getFullName() + ","
				+ projectObjects.getCustomer().getContactNumber() + ","
				+ projectObjects.getCustomer().getEmail() + ","
				+ projectObjects.getCustomer().getAddress() + ","
				// Get and format architects details.
				+ projectObjects.getArchitect().getFullName() + ","
				+ projectObjects.getArchitect().getContactNumber() + ","
				+ projectObjects.getArchitect().getEmail() + ","
				+ projectObjects.getArchitect().getAddress() + ","
				// Get and format contractors details.
				+ projectObjects.getContractor().getFullName() + ","
				+ projectObjects.getContractor().getContactNumber() + ","
				+ projectObjects.getContractor().getEmail() + ","
				+ projectObjects.getContractor().getAddress() + ",\r\n";
	}
		
	/**
	 * Calls the <code>projectString</code> method to obtain the string details of all the projects
	 * in the <code>projectList</code> then writes each object to <code>PoiseProjects.txt</code>.
	 * 
	 * @param projectsFile	the text file to be written to.
	 * @param projectList	the list of project objects to be written to file.
	 * @throws IOException 	if the file could not be located and written.
	 */
	// Method to write the updated project list to file.
	public static void writeListToFile(File projectsFile, List<Project> projectList) {

		String allProjects = "";
		// 
		try (FileWriter writeList = new FileWriter(projectsFile)) {
			for (Project projectObjects : projectList) {
				allProjects += projectString(projectObjects);
			}
			writeList.write(allProjects);
		}
	    catch (IOException e) {
	        System.err.println("Error writing list to file." + e.getStackTrace());
	    }
	}
	
	/**
	 * Calls the <code>projectString</code> method to obtain the string details of a single newly
	 * captured project then writes the object details to <code>PoiseProjects.txt</code>. 
	 * 
	 * @param projectsFile		the text file to be written to.
	 * @param newProject		the new project object created to be appended to file.
	 * @throws IOException 		if the file could not be located and written.
	 */
	// Method to write the newly captured project to file.
	public static void writeProjectToFile(File projectsFile, Project newProject) {
		
		// Open FileWriter and append string of project details to the file
		try (FileWriter writeProject = new FileWriter(projectsFile, true)) {
			writeProject.write(projectString(newProject));
		} 
		catch (IOException e) {
			System.err.println("Error writing projects to file." + e.getStackTrace());
		}
	}
	
	/**
	 * Calls the <code>toString</code> method of the specified completed project to obtain the 
	 * string details then writes the object details to <code>CompletedProjects.txt</code>. 
	 * 
	 * @param completedProjectsFile		the text file to be written to.
	 * @param completedProject			the project object set as finalised.
	 * @throws IOException 				if the file could not be located and written.
	 */
	// Method to write the completed projects details, with completion date, to file.
	public static void writeCompletedProjectToFile
					(File completedProjectsFile, Project completedProject) { 
		
		// Open FileWriter and append projects toString with completion date to the file.
		try (FileWriter writeProject = new FileWriter(completedProjectsFile, true)) {
			writeProject.write(completedProject.toString());   
		} 
		catch (IOException e) {
			System.err.println("Error writing project to file." + e.getStackTrace());
		}
	}

}
