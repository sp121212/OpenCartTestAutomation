pipeline{
	agent any

		stages{

			stage("Build"){
				steps{
					git 'https://github.com/Jaipinder/https-github.com-jglick-simple-maven-project-with-tests.git'
					sh "mvn -Dmaven.test.failure.ignore=true clean package"
					 }
				}
				
			stage("Regression Automation Tests"){
				steps{
					git 'https://github.com/sp121212/OpenCartTestAutomation.git'
					sh "mvn clean test -Dsurefire.suitexmlfiles=/src/test/resources/testrunners/testng_regression.xml"
					 }
				}


			}

}