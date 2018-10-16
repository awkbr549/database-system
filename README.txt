PLEASE MAKE SURE YOU HAVE AN INTERNET CONNECTION, AS MAVEN NEEDS INTERNET TO USE
SPRING BOOT AND ITS DEPENDENCIES



To run this project with Ubuntu:

1: Ensure that Maven is installed on your machine

2: Ensure that MySQL is installed on your machine

3: Run mysql with the following:
	$ sudo mysql --password

4: After loggin into MySQL, do the following:
	mysql> create database db_example;
	mysql> create user 'springuser'@'localhost' identified by 'ThePassword';
	mysql> grant all on db_example.* to 'springuser'@'localhost';

5: To compile the project, run `make compile' in the root directory, or 
alternatively, run the following:
	$ mvn install
	$ mvn package

6: To run the project, run `make run' in the root directory, or alternatively, run the
following:
	$ java -jar target/UniversityRegistration-2.0.jar

7: Connect to the running application by navigating to `localhost:8080' in your
Internet browser.




You can also just run `make' to both compile and run the project.
