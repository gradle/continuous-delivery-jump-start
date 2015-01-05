# Gradle sample application

This project is a simple, MVC-based web application written in Java. Its main purpose is to demonstrate implementation
approaches for typical project automation requirements with the help of Gradle.

## Technical implementation

* Java-based web application.
* Web requests handled by Spring MVC.
* Dependency Injection with Spring Beans.
* Unit tests implemented with JUnit and Mockito.
* Integration tests use Spring Test.
* Target runtime environment: Apache Tomcat.

## Applied Gradle features

* Usage of the Gradle Wrapper.
* Deployment to an embedded [Tomcat](http://tomcat.apache.org/) container for local development.

## Running the application

You can directly run your application your local machine. There’s no need to install a Servlet container. In a terminal
window execute the following command from the root level of the project:

**Mac OS X/*nix**

    ./gradlew tomcatRun

**Windows**

    gradlew.bat tomcatRun

After a few moments, you will find the following output:

    ...
    Started Tomcat Server
    The Server is running at http://localhost:8080/sample-app

Open your browser of choice and navigate to the URL.