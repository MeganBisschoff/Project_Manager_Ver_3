# Project Manager Ver. 3

A Java program that tracks various projects and project members.

## Description

This program is for a fictitious structural engineering firm, called Poised, that allows them to capture project information, search for and edit project information, as well ass update the personal details of the persons involved in the project.

This is the third version of the Project Manager program ([Ver 1](https://github.com/MeganBisschoff/Project_Manager),[Ver 2](https://github.com/MeganBisschoff/Project_Manager_Ver_2)) which has now been extended to allow for the creation of mutilple projects; the ability to store data by reading and writing object information to text files; and has been adequately documented with docstrings and a Javadoc. 

**The program has also been expanded on to meet the following client specifications:**

* Reads details about existing projects from a text file and uses this information to create a list of project objects.
* Allows a user to add new objects to this list.
* Allows a user to select and update or finalise any project on the list.
* Allows a user to see a list of projects that still need to be completed.
* Allows a user to see a list of projects that are past the due date.
* Writes the updated details about the projects to the text file when the program ends.

**Updates:**

* The main ``programMenu`` was organised into the two submenus ``searchAndViewMenu`` and ``editAndFinaliseMenu``.
* The ``Main()`` class, ``Project()`` class and ``Person()`` classes and subclasses have carried over thier original structure from version 2.
* While the ``Project()`` class continues to hold the main method for instantiating and capturing a new project object, all methods relating to editing or finalising a project has been grouped into their own ``EditAndFinaliseProject()`` subclass.
* As the program now features a list of all the projects, a new abstract ``SearchAndViewProjects()`` class has been introduced to handle all queries related to searching for and viewing projects in the list.
* Another abstract class that has been introduced is the ``ReadAndWriteToFile()`` class which reads and writes project object information from and to an external database.

## Program Recap

The ``Project()`` class includes the attributes, along with getters and setters for:

* projNumber
* projName
* projType
* projAddress
* projErf
* feeTotal
* feePaid
* projDeadline
* projFinalised
* projCompletionDate
* projectCaptured
* customer
* architect
* contractor

The Person class includes the attributes, along with getters and setters for:

* fullName
* contactNumber
* email
* address

A new architect, contractor and customer (objects) is captured in their respective subclasses which extends the ``Person()`` class.

**The program includes the following classes and subclasses:**

* Main()
* Project()
	* EditAndFinaliseProject()
* Person()
	* Architect()
	* Contractor()
	* Customer()
* SearchAndViewProjects()
* ReadAndWriteToFile()
* Package()

The ``Package()`` class contains a brief overveiw of the program.

## The Project Manager Program

At startup, a list of Project attribute information is read from the *PoiseProjects.txt* database and populated into new Project objects. The project objects are added to a global *projectList* for further operation as selected by the user from the Poised program menu.

Once the program is running, the main ``programMenu`` is presented to the user:

```java
String programMenu = "\n--- Poised Program Menu ---\n" 
	+ "\nEnter 1 to capture a new Poise project"
	+ "\nEnter 2 to search and view projects"
	+ "\nEnter 3 to edit and finalise a project"
	+ "\nEnter 4 to edit the contractors, architects and customers details"
	+ "\nEnter 5 to update file and exit the program"
	+ "\nEnter selection: ";
```
**If user selects 1:**

A new project object is captured in the ``Project()`` class, and the customer, architect and contractor objects are captured in the ``Person()`` class. After a project is captured, the details are added to the project list as well as the external file.

The ``Project()`` class includes the functions:
* captureProject()
* createProjectName()

The ``Person()`` class includes the functions:
* capturePersonData()
* editPersonData()

**If user selects 2:**

The following ``searchAndViewMenu`` submenu is presented to the user.

```java
String searchAndViewMenu = "\n--- Search and View Projects ---\n"
	+ "\nEnter 1 to search for a project"
	+ "\nEnter 2 to view all projects"
	+ "\nEnter 3 to view incomplete projects"
	+ "\nEnter 4 to view overdue projects"
	+ "\nEnter your selection: ";
```
The ``SearchAndViewProjects()`` class returns the information of the project the user wishes to view from the list.	

The above operations are carried out in the ```SearchAndViewProjects()`` class which includes the functions:
* searchProj()
* viewAllProj()
* viewIncompleteProj()
* viewOverdueProj()

**If user selects 3:**

The following ``editAndFinaliseMenu`` submenu is presented to the user.

```java
String editAndFinaliseMenu = "\n--- Edit Project Details ---\n"
	+ "\nEnter 1 to edit the projects total fee"
	+ "\nEnter 2 to edit the projects fee paid"
	+ "\nEnter 3 to edit the projects deadline"
	+ "\nEnter 4 to finalise the project"
	+ "\nEnter your selection: ";
```

The ``SearchAndViewProjects()`` class returns the information of the project the user wishes to edit from the list.
	
The menu operations are carried out in the ```EditAndFinaliseProject()`` class which includes the functions:
* editProjFeeTotal()
* editProjFeePaid()
* editProjDeadline()
* markProjFinalisation()
* generateInvoice()

**If user selects 4:**

The ``editPersonData()`` method in the ``Person`` class is called to get and edit the attributes of the Architect, Contractor or Customer, which is then updated in the list.

**If user selects 5:**

The ``ReadAndWriteToFile()`` class is called which writes the updated projects in the list to file before exiting the Program.

The ```ReadAndWriteToFile()`` class also includes functions to:

* readProjectsFromFile()
* projectString()
* writeListToFile()
* writeProjectToFile()
* writeCompletedProjectToFile()

## Functionality summary:

* Capture information about new projects.
* Edit the projects deadline, total fee and fee paid.
* Finalise a project and generate an invoice.
* See a list of projects that still need to be completed or are past the due date.
* Find and select a project by entering either the project number or project name.
* Update the contractors, customers and architects contact details.

## Programming principles

This program employs the programming concepts of Java OOP including classes, accessor and mutator methods, dot notation and functions. Furthermore it employs fundamental programming techniques that include external IO databases, comparison operators, conditional logic, loops, indexing, date formatting and regex expressions. Lastly, it also employs the built in matches(), equals(), compareTo() and isBlank() functions.

## Dependencies

* import java.util.Locale;
* import java.io.FileWriter;
* import java.io.IOException;
* import java.text.ParseException;
* import java.text.SimpleDateFormat;
* import java.util.Date;
* import java.util.Scanner;

## Running the program

Open all the files in the poised package in any Java IDE and run the Main.java file.

## Code preview

```java
// ----- Method to CAPTURE PROJECT information ----- //
public void captureProject(Project projectName) {
	
	// --- Capture, validate and set the project NUMBER --- 
	while (true) {
		System.out.println("Enter the project number: ");
		
		// Validate that the input has an integer present.
		if (input.hasNextInt()) {
			int inputProjNumber = input.nextInt();
			projectName.setProjNumber(inputProjNumber);
			System.out.println("\nProject number captured as " + projectName.getProjNumber() +"\n");
			break;
		} else {
			System.out.println("Invalid entry, enter project number without spaces or letters.");
			input.nextLine();
		}
	}
	input.nextLine();
	
	// --- Capture, validate and set the project building TYPE --- 
	while (true) {
		System.out.println("Enter the building type (house, apartment, etc.): ");
		String userProjType = input.nextLine();
		
		// Validate that the input is not blank and that it has letters in the word.
		if (userProjType.isBlank() || userProjType.matches("[^a-zA-Z]+")) {
			System.out.println("Invalid building type, please try again."); 
		} else {
			projectName.setProjType(userProjType);
			System.out.println("\nBuilding type captured as " + projectName.getProjType() +"\n");
			break;
		}
	}	
	
	// --- Capture and set the project NAME ---
	// No validation check required as project name is created afterwards if empty.
	System.out.println("Enter the projects name: ");	
	String inputProjName = input.nextLine() ;
	projectName.setProjName(inputProjName);		
	
	// --- Capture, validate and set the projects physical ADDRESS --- 
	while (true) {
		System.out.println("Enter projects physical address: ");
		String inputProjAddress = input.nextLine();
		
		// Validate that the input is not blank and that it has letters in the word.
		if (inputProjAddress.isBlank() || inputProjAddress.matches("[^a-zA-Z]+")) {
			System.out.println("Invalid address entry, please try again."); 
		} else {
			projectName.setProjAddress(inputProjAddress);
			System.out.println("\nProject address captured as " + projectName.getProjAddress() +"\n");
			break;
		}
	}
	
	// --- Capture, validate and set the project ERF number  --- 
	while (true) {
		System.out.println("Enter projects ERF number: ");
		
		// Validate that the input has an integer present.
		if (input.hasNextInt()) {
			int inputProjErf = input.nextInt();
			projectName.setProjErf(inputProjErf);
			System.out.println("\nERF number captured as " + projectName.getProjErf() +"\n");
			break;
		} else {
			System.out.println("Invalid entry, enter ERF number without spaces or letters."); 
			input.nextLine();
		}
	}
	input.nextLine();

	// --- Capture, validate and set the projects FEE TOTAL --- 
	while (true) {
		System.out.println("Enter projects total fee: R");
		String inputFeeTotal = input.nextLine();
		
		// Validate that the input matches any number of digits, then parse to a double type.
		if (inputFeeTotal.matches("\\d+")) {
			Double projectFeePaid = Double.parseDouble(inputFeeTotal);
			projectName.setFeeTotal(projectFeePaid);
			System.out.println("\nFee total captured as " + projectName.getFeeTotal() +"\n");
			break;
		} else {
			System.out.println("Invalid fee, enter digits only with no spaces, dots or commas"); 
		}
	}
	
	// --- Capture, validate and set the projects FEE PAID --- 
	while (true) {
		System.out.println("Enter projects fee paid: R");
		String inputFeePaid = input.nextLine();
		
		// Validate that the input matches any number of digits, then parse to a double type.
		if (inputFeePaid.matches("\\d+")) {
			Double projectFeePaid = Double.parseDouble(inputFeePaid);
			projectName.setFeePaid(projectFeePaid);
			System.out.println("\nFee paid captured as " + projectName.getFeePaid() +"\n");
			break;
		} else {
			System.out.println("Invalid fee, enter digits only with no spaces, dots or commas"); 
		}
	}
	
	// --- Capture, validate and set the projects DUE DATE --- 
	while (true) {
		System.out.println("Enter projects deadline as dd/mm/yyyy: ");
		String strDate = input.nextLine();
		
		// Validate that the input is the correct length, has a "/" and doesn't contain letters.
		if (strDate.length() == 10 && strDate.contains("/") && strDate.matches("[^a-zA-Z]+")) {
			try {
				// Format string deadline to date format, and set deadline.
				Date inputProjDeadline = new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
				projectName.setProjDeadline(inputProjDeadline);
				System.out.println("\nDeadline captured as " + projectName.getProjDeadline() +"\n");
				break;
			} catch (ParseException e) {
				System.out.println(strDate + " is not a valid date, please try again."); 
			}
		}
		else {
			System.out.println(strDate + " is not a valid date, please try again.");
		}
	}
}
```

## Program output preview

```
--- Project Information ---

Project Number: 1
Project Name: House Deer
Building Type: House
Physical address: 11 Acacia drive, Franschhoek, Western Cape
ERF Number: 10111
Total fee.: 1000000.0
Total paid: 10000.0
Deadline: Fri Oct 01 00:00:00 SAST 2021
Finalised: false
Completion date: null

--- Customer's Information ---

Name: John Deer
Phone number: 0820001111
Email address: john@email.com
Work address: 11 Veld street, Swellendam, Western Cape

--- Architect's Information ---

Name: Alistair Archi
Phone number: 0720001111
Email address: alistair@email.com
Work address: 11 Main road, Cape Town CBD, Western Cape

--- Contractor's Information ---

Name: Calvin Contra
Phone number: 0620001111
Email address: calvin@email.com
Work address: 11 Industrial drive, Paarl, Western Cape
```

&nbsp;
***  
_A project is complete when it starts working for you, rather than you working for it._ ~ Scott Allen
***
&nbsp;

## Author

**Megan Bisschoff** 2022

Project submitted for Software Engineering learnership Level 2 Task 21 at [HyperionDev](https://www.hyperiondev.com/)

[View](https://www.hyperiondev.com/portfolio/86596/) submission results.
