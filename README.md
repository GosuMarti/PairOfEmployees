# Pair of employees
A simple spring boot application

### Application purpose
The main purpose of the application is to receive employee data from a CSV file, succsessfully parse it and find the pair of employees that has worked the most days together on a project.

### How does it work
* Before the application starts the csv reader handles the file then then sends it to a service where the data from the file gets added to the databasa in this case PostgreSQL.
* When oppened in the browser the home page sends a GetMapping request to the controller, which handles it and returns a html file with a table containing all the employees and the data for their projects.
* After clicking a button we send another GetMapping request to the controller, which calls some services that search through the database to find pair of employees that has worked the most days together on a project and then returns a view for it.

