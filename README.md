District Court Automation
------------
This project contains automation for the below websites/search types:

[Districts eCourt](https://districts.ecourts.gov.in/)
- Case Number
- Advocate Name
- Party Name
- Cause List

[Services eCourt](https://services.ecourts.gov.in/ecourtindia_v6/)
- CNR Number

All JSON Input formats, as well as an updated Postman Collection containing all Local and Public POST Requests can be found in the project files at: [src/main/resources/jsonBodyTemplates]

Available API Requests
------------
**/caseNumber**\
Executes Case Number search query

**/caseNumberUpdate**\
Executes Case Number search query and additionally records information from all additional links from the Case Page (i.e. Business links from the Case History table)

**/partyName**\
Executes Party Name Search Query

**/partyNameUpdate**\
Executes Party Name search query and additionally records information from all additional links from the Case Page (i.e. Business links from the Case History table)

**/advocateName**\
Executes Advocate Name search query

**/advocateNameUpdate**\
Executes Advocate Name search query and additionally records information from all additional links from the Case Page (i.e. Business links from the Case History table)

**/advocateNameBasic**\
Executes Advocate Name search query, but instead of capturing the Case Page, instead will return all results from the search query in Case Number input format to be used with caseNumber or caseNumberUpdate requests

**/causeList**\
Executes Advocate Name search query

**/causeListUpdate**\
Executes Cause List search query and additionally records information from all additional links from the Case Page (i.e. Business links from the Case History table)

**/cnrNumber**\
Executes CNR Number search query

**/cnrNumberUpdate**\
Executes CNR Number search query and additionally records information from all additional links from the Case Page (i.e. Business links from the Case History table)

**/cnrNumberBasic**\
Executes CNR Number search query, but instead of capturing the Case Page, instead will return all results from the search query in Case Number input format to be used with caseNumber or caseNumberUpdate requests

Public Server Access, Setup, and Maintenance
------------
The remote Ubuntu server can be accessed at: [31.220.108.229:8080]\
Running the project locally will allow access at: [localhost:8080]

To set up the public server for remote usage, follow the below steps:
1. **Log into the server using SSH**\
Credentials can be obtained from Gaurav Purwar or Samuel Nezic. IP Address for connection is: [31.220.108.229]
2. **cd bootsel**\
Project directory can be located in the [bootsel] folder from root directory
3. **git pull**\
Pull the latest version of the build. If there are merge errors, run **git stash** beforehand, then attempt to pull again
4. **mvn clean install**\
Compile the project as a maven project, creating a .jar output that can be executed
5. **screen -s json java -jar /root/bootsel/target/eCourtDemo-1.0.jar**\
This will create a virtual terminal (screen) that will execute the program remotely without constant access being needed (otherwise closing the session will terminate execution). File being executed will be the .jar from the Step 4
6. **(Ctrl + A, D)**\
Using the keyboard, hold the above keys. This will detach the newly created screen allowing it to run independently of the current SSH session

For updates and maintenance of an existing server, follow the below steps:
1. **Log into the server using SSH**\
   Credentials can be obtained from Gaurav Purwar or Samuel Nezic. IP Address for connection is: [31.220.108.229]
2. **screen -ls**\
   List all the current screen sessions present on the server. There should only ever be one running at once. If there are more, please contact a project maintainer
3. **screen -r (screen ID)**\
   The screen ID obtained from the previous step will allow the screen to be resumed
4. **(Ctrl + C)**\
   Cancel the current execution. This will disable remote access using the server temporarily
5. **git pull**\
   Pull the latest version of the build. If there are merge errors, run **git stash** beforehand, then attempt to pull again
6. **mvn clean install**\
   Compile the project as a maven project, creating a .jar output that cna be executed
7. **java -jar /root/bootsel/target/eCourtDemo-1.0.jar**\
   Re-executes the project. File being executed will be the .jar from the Step 6
8. **(Ctrl + A, D)**\
   Using the keyboard, hold the above keys. This will detach the newly created screen allowing it to run independently of the current SSH session