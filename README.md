
For the building, running and testing of this project you should have Java8, Maven3, Tomcat 8.0.26 or later installed.

P.1 To build the project, you should open cmd, go to the project folder and enter in command-line: mvn clean install

P.2 You need go to {project name}/target and rename codenvyTestTask.war to ROOT.war. 
    After you should go to Tomcat/webapps and delete folder ROOT.

P.3 To run the project you should deploy ROOT.war in Tomcat. To do that you should execute Tomcat, 
in your browser go to the URL: http://localhost:8080/manager , find window "WAR file to deploy", 
select project war-archive (after rename (P.2) it will be in the folder: {project name}/target) 
and click Deploy or add ROOT.war in Tomcat/webapps. After that go to URL : http://localhost:8080/todo/app/

P.4 To run the tests, you should open the cmd, go to the project folder and enter in the command-line: mvn test
