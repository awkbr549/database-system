all: 	compile run

compile:
	clear
	mvn install
	mvn package

run:
	clear
	java -jar target/UniversityRegistration-2.0.jar

clean:
	clear
	mvn clean
