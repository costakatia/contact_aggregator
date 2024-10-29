Contact Management API
This project is a Spring Boot application that provides a simple API to manage contacts. It connects to an external API to fetch contact data and exposes it through a local endpoint.

Here is a README.md template for your Spring Boot application with instructions on how to set it up and test using cURL.

Contact Management API
This project is a Spring Boot application that provides a simple API to manage contacts. It connects to an external API to fetch contact data and exposes it through a local endpoint.

Prerequisites
Make sure you have the following installed:

Java 17+
Maven
cURL
Setup Instructions
Build the Project
Run the following command to download dependencies and build the application:
mvn clean install
Run the Application
Start the Spring Boot application with

mvn spring-boot:run

By default, the application runs on http://localhost:8080.

Environment Variables (Optional)
If necessary, you can configure the port or token via environment variables:

export SERVER_PORT=8080

export API_TOKEN=J7ybt6jv6pdJ4gyQP9gNonsY

API Endpoints
GET /contacts
Fetches all contacts from the external API.

Request Example (cURL)

curl -X GET http://localhost:8080/contacts \
-H "Authorization: Bearer J7ybt6jv6pdJ4gyQP9gNonsY" \
-H "Content-Type: application/json"

Testing the API with cURL
Get All Contacts

curl -X GET http://localhost:8080/contacts \
-H "Authorization: Bearer J7ybt6jv6pdJ4gyQP9gNonsY" \
-H "Content-Type: application/json"
