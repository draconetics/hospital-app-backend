# TOOLS

- IntelliJ (version 2018.3.2 - 64 bits)
- Browser Firefox(version 77.0.1 - 64 bits)
- Operating System (windows 10 - 64 bits)

# SOFTWARE PRE-INSTALLED

- Java 1.8+
- Maven
- Mysql 5.2 (easy installation with XAMP)

# HospitalApp

This project is a simple Hospital App, it was generated and developed with framework [Angular CLI](https://github.com/angular/angular-cli) version 10.0.0.

## Installation

- Clone this repository to your favorite folder, recommended on root disk (eg. c://hospital-app-backend)
- Open the main folder(hospital-app-backend) with IntelliJ
- IntelliJ automatically recognize all dependencies and will show a windows with a request : "Do you want to import dependencies?", then you click Yes
- Open XAMP and run the Mysql server and apache (Control panel is XAMP will show you if the services are running)
- open http://localhost/phpmyadmin
- enter en create a database called : hospital (when spring boot run for the first time, it will create all the tables and data for you)
- Enter to http://localhost/phpmyadminChange and change user root's password to root or change the file 'hospital-app-backend/src/mamin/resource/application.properties' the next line 'spring.datasource.password=root' to 'spring.datasource.password='
- Finally just run spring boot app

## Author

Mario Flores - marioflorescondori@gmail.com