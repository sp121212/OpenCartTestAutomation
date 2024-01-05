pipeline{
	agent any

		stages{

			stage("build"){
			steps{
			echo "test build"
			}
			}
				
			stage("Regression Automation Tests"){
				steps{
				catchError(buildResult:'SUCCESS',stageResult: 'FAILURE'){
					git 'https://github.com/sp121212/OpenCartTestAutomation.git'
					bat "mvn clean test -Dsurefire.suiteXmlfiles=src/test/resources/testrunners/testng_regression.xml"
					 }
				}
			}	

			}

}