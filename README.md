For the building, running and testing of this project you should have Java8, Maven3, Tomcat 8.0.26 or later installed.

P.1 To build the project, you should open cmd, go to the project folder and enter in command-line:
    mvn clean install
    
P.2 To run the project you should deploy codenvyTestTask.war in Tomcat. To do that you should execute Tomcat,
    in your browser go to the URL: http://localhost:8080/manager , find window "WAR file to deploy", 
    select project war-archive (after the project building (P.1) it will be in the folder: {project name}/target) and click Deploy
    or add codenvyTestTask.war in Tomcat/webapps. Then configure the context root in Tomcat/conf/server.xml:
   
    <Context path="" docBase="codenvyTestTask" debug="0" reloadable="true"></Context>
    
    After that go to URL : http://localhost:8080/todo/app/
    
P.3 To run the tests, you should open the cmd, go to the project folder and enter in the command-line:
    mvn test
