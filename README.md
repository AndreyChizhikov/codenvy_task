For the building, runing and testing of this project you should have Java8, Maven3, Tomcat 8.0.26 or later installed.

P.1 To build the project, you should open cmd, go to the project folder and enter in command-line:
    mvn war:war.
    
P.2 To run the project you should deploy codenvyTestTask.war in Tomcat. To do that you should execute Tomcat,
    in your browser go to the URL: http://{servername:port}/manager , find window "WAR file to deploy", 
    select project war-archive (after the project building (P.1) it will be in the folder: {project name}/target) 
    and click Deploy. After that go to URL : http://{servername:port}/codenvyTestTask/todo/app/
    
P.3 To run the tests, you should open cmd, go to the project folder and enter in command-line:
    mvn test
