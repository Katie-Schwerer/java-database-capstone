# Schema Architecture

## Section 1: Architecture Summary
This Spring Boot Application uses Spring MVC (Model-View-Controller) with Thymeleaf to create the admin/doctor dashboards. The REST APIs will serve as communication between the client, server, and other modules. This application interacts with two databases: MySQL and MongoDB. The MySQL database stores data about patients, doctors, appointments, and administrative information using JPA entities. The MongoDB database contains data about prescriptions using document models. Every controller route requests a common service layer, which delegates to its dedicated repositories.

## Section 2: Numbered Flow of Data and Control
1. User interacts with either AdminDashboard or DoctorDashboard.
2. The action is routed to either Thymeleaf or REST controller.
3. The controller reports to the service layer
4. The service layer talks with the repository layer to do data access operations
5. Each repository interfaces with database engine
6. Data is recieved and mapped into Java model classes
7. The model is used in the response layer
