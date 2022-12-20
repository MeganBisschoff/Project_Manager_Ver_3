/**
 * The <code>poise</code> package contains the source code of the project management program 
 * crreated for Poised structural engineering firm.
 * <p>
 * Details about a project and its customer, contractor and architect can be created, viewed and 
 * edited. Information on current projects are read from and written to 
 * <code>PoiseProjects.txt</code> in the poised package's parent folder, while details of the 
 * completed projects are written to <code>CompletedProjects.txt</code>.
 * <p>
 * The <code>Person</code> is the abstract parent class of <code>Customer</code>, 
 * <code>Contractor</code> and <code>Architect</code>. 
 * <p>
 * <code>Project</code> is the parent class of the abstract <code>EditAndFinaliseProject</code> 
 * class.
 * <p>
 * The abstract classes <code>ReadAndWriteToFile</code> and <code>SearchAndViewProjects</code> are
 * dependent on object information from the <code>Project</code> and <code>Person</code> classes.
 * <p>
 * The program is run from <code>Mainu</code> where the user is displayed various program operations
 * from the main menu and it's sub menus.
 * Main menu operations:<br>
 * <li> (1) capture a new Poise project
 * <li> (2) search and view projects
 * <li> (3) edit and finalise a project
 * <li> (4) edit the contractors, architects and customers details
 * <li> (5) update file and exit the program 
 * </ul>
 * Sub menu operations from option (2):<br>
 * <li> search for a project
 * <li> view all projects
 * <li> view incomplete projects
 * <li> view overdue projects
 * </ul>
 * Sub menu operations from option (3):<br>
 * <li> edit the projects total fee
 * <li> edit the projects fee paid
 * <li> edit the projects deadline
 * <li> finalise the project
 * 
 * @author Megan Bisschoff
 * @version 1.00, 21 August 2022
 * @see Project.java
 * @see EditAndFinaliseProjects.java
 * @see Person.java
 * @see Architect.java
 * @see Customer.java
 * @see Contractor.java
 * @see ReadAndWriteToFile.java
 * @see SearchAndViewProjects.java
 
 */
package poised;


