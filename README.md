Automation Script Documentation
Project Name: FitPeo Automation Task

Objective
This automation script is designed to navigate the FitPeo website, interact with the Revenue Calculator page, and validate the specified test scenarios. The script is implemented using Java and Selenium WebDriver for reliable web automation.

Setup and Run Instructions
1. Prerequisites
Ensure the following are installed on your system:

Java Development Kit (JDK): Version 8 or above.
Apache Maven: For project dependency management.
Google Chrome: Ensure it matches the version of ChromeDriver you download.
ChromeDriver: Download the driver version compatible with your Chrome browser.
2. Environment Setup
Create the Project:

Open your IDE and create a new Maven project with:
Group ID: fitPeo
Artifact ID: automationTask.
Add the Automation Class:

Create a Java class named FitPeoAutomation.
Copy the automation script from the GitHub repository and paste it into the FitPeoAutomation class.
Add Selenium Dependency:

Open the pom.xml file in your Maven project.
Add the following dependency:
xml
Copy code
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.13.0</version>
</dependency>
Set Up ChromeDriver:

Create a folder named driver in your project directory.
Paste the downloaded chromedriver.exe file into the driver folder.
Update the DRIVER_PATH variable in the script to point to the location of chromedriver.exe.
3. Run the Script
Open the FitPeoAutomation class in your IDE.
Execute the script.
Verify the output in the console to ensure all steps are successfully validated.
Additional Note
An additional step has been added to the script:

After validating the slider value by entering 560 in the text field, the slider is adjusted back to 820.
This step ensures the Total Recurring Reimbursement for all Patients Per Month matches the value specified in the assignment.
Expected Output
The script should successfully navigate through the FitPeo website, interact with elements on the Revenue Calculator page, and validate the scenarios as defined in the task requirements.
