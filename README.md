# BackOffice-end

## Info
This is the server side implementation of the backoffice client microservice that handles the call to the database, orderflow and user information to be used by the frontend application of the backoffice.

## Enviroment
This code is written in java with the framework <b><em> Spring </em></b> to create a connection to the database and structuring the program. The website enviroment we use to deploy the API is on heruko.

## Test
Is done each time the spring program is compiled since the command <code> mvn package </code> follows pom.xml file when compiled and thus tests all the files in the test directory before creating the jar.exe.

## Structure
The code structure follows the <em> MVC </em> architecture thus allowing for better structuring and truly seperate the classes.
The structure goes as followed:

<b>Controller:</b>
Contaning the Main controller for the <em> /user </em> API calls to the backend.

<b>Model:</b>
contains the buissness logic of the server with the following files that the server uses:

<b>DB:</b>
Contains the DB-class <b>UserRepository.java</b> that extends <b>CrudRepository</b> to allow fot the db calls to the server.
It also contains the DB-class <b> Users </b> that is the "table-class" that spring @hibernate will autowire into the database.
