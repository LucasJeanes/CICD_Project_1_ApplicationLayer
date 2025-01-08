Main user input application.

(Microservice 1 in Final Year CICD Project)

Service layer app can be found here:
https://github.com/LucasJeanes/CICD_Project_2_ServiceLayer

Notification layer app can be found here: https://github.com/LucasJeanes/CICD_Project_3_NotificationLayer

Reference the image below for the folder structure of this project.

Each project has its own dedicated dockerfile, but as they are individual projects, the docker-compose (which is included in AplpicationLayer) must be in the parent folder where these projects are located!

![image](https://github.com/user-attachments/assets/e3a1c56b-27f7-4110-a866-4b604061f0a7)


PROJECT SETUP:

1. Run "mvn clean package" to generate necessary JAR files in every project.
2. Run all projects and use "localhost:8081/" as url base. All endpoints can be found in "PortalServiceClient" in ApplicationLayer.
3. For testing on Docker, run Dockerfile and expose ports 8081:8081, 8082:8082, and 8083:8083 for ApplicationLayer, ServiceLayer, and NotificationLayer respectively.
4. Open CMD and navigate to parent directory as seen in above image. Run "docker compose up --build" to build the container for the respective project images.
5. Docker url is also "localhost:8081/", application-docker.properties automatically re-routes urls based on Spring profile.
6. For testing with Google Cloud Kubernetes cluster, use following url instead: "http://34.142.40.153:8081/"


Tests can be run with "mvn test" or by navigating the Maven tab in your IDE.
