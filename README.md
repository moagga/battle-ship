# battle-ship

This is the submission for ThoughtWorks coding assignment.

How to start?

The project requires following softwares to build:

1. JDK8
2. Maven 3.1+

Depedencies required by the application
1. JUnit 4.10 for running unit tests.

Steps to build 
1. Explode the zip file. This will create a 'battle-ship' directory.
1. Go to project root directory ('battle-ship').
2. Run mvn clean install to build along with unit tests (assuming java & maven are defined in the System Path).
3. Once the command completes, battle-ship-1.0-SNAPSHOT.jar will be generated in the target directory.

After successfully building the application, the application can be run using java -jar command. The application takes input the absolute path of the input file.

Steps to Run
1. In the project root directory, run the command java -jar target\battle-ship-1.0-SNAPSHOT.jar <input_file_path>
2. Incase file path is not provided, the application picks up a default file which is bundled inside the application. This default file contains the test case which is given in the programming assignment.

Browse the Code
To browse the code, use the maven eclipse plugin to generate eclipse files. Steps are as follow:
1. Go to project root directory.
2. Run mvn eclipse:clean eclipse:eclipse
3. Import the project file in Eclipse.
