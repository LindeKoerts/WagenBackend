# WagenBackend


Inleiding: Wagen autogarage applicatie 

Met dit project kan een autogarage het proces van keuringen en reparaties rondom auto's en klanten automatiseren. De uiteindelijke applicatie biedt o.a. de mogelijkheid om klanten en auto's aan te maken. Keuringen en reparaties aan auto's te koppelen en meer. De applicatie kent Authenticatie en Autorisatie zodat data beschermd is.

Om mee te beginnen: 
1.	Het project maakt gebruik van Java version 11 
2.	Het project maakt gebruik van een PostgreSQL database 
3.	Het project maakt gebruik van het Spingboot framework en Maven 
4.	Maven wordt ingezet als dependency manager 
5.	Authorization is verplicht (Bearer (JWT) token), zie de endpoints 

Er is een export met de endpoints vanuit postman toegevoegd aan het project. Deze zijn te vinden onder main > resources > POSTMAN collecties. Deze collecties zijn importeerbaar in Postman door binnen postman te gaan naar file > import en vervolgens alle bestanden onder POSTMAN collecties te openen en te importeren. 

Installatie:
Dit project is het beste te runnen met de IDE IntelIj van Jetbrains. 
Download de community versie hier: https://www.jetbrains.com/idea/download/ 

De applicatie maakt gebruik van een PostgresSQL database. PostgreSQL kun je hier downloaden: https://www.postgresql.org/download/ 


Stappenplan 
1.	Installeer de InteliJ IDE en PostGreSQL.
Hier vind je een makkelijk en kort tutorial om PostGreSQL te installeren.
https://www.postgresqltutorial.com/install-postgresql/ 

2.	Laad het project in Intelij. Alle dependencies bevinden zich in het POM bestand wat bij het project gesloten zit. Maven zal aan de hand van dit POM bestand het synchroniseren van dependencies starten en zo worden alle dependencies die benodigd zijn automatisch voor je binnen gehaald.

Het inladen van het project kun je doen door in InteliJ via de menu bar naar Git > Clone te gaan, daar de URL van deze repository in te vullen en op clone te klikken. 

https://github.com/LindeKoerts/WagenBackend

3.	Verander de PostgreSQL credentials in resources > application-dev.properties naar jouw eigen local settings:

De huidige instellingen zijn als volgt:

spring.sql.init.platform=postgres
spring.datasource.url=jdbc:postgresql://localhost:5432/WagenGarage
spring.datasource.username=wagengarage_admin
spring.datasource.password=123456

4.	Maak een uploads directory aan in de root directory van de project folder of kijk of deze al bestaat.
v.b. ../Autogarage/uploads

5.	Run het project 
Als alle stappen zijn genomen zijn de endpoints beschikbaar en kun je deze via postman aanroepen. Je kunt de bijgeleverde export van de endpoints uit postman importeren zodat deze direct beschikbaar zijn, zie hierboven bij om te beginnen hoe dit moet. 


De endpoints 
De endpoints kunnen getest worden met een applicatie als Postman. 

De authenticatie van de applicatie werkt met een Bearer token. Deze token moet worden meegegeven aan elk request binnen Postman. Deze stel je in onder ‘Autorization’ in postman. Bij een Request endpoint (binnen postman) kies je op de tab 'Authorization' voor het Type: Bearer token, onder 'Token' vul je het 'JWT token' in welke je via het 'Authorization' endpoint kunt verkrijgen.

Deze token geeft toegang voor elk endpoint waar deze user voor geauthorizeerd is. De rechten per user vind je in de tabel bovenaan deze handleiding.

De token verkrijg je door eerst het 'Authorization' endpoint uit te voeren. De token is vervolgens 10 dagen geldig.

*Authorization 
- POST/ api/v1/authenticate
In de body geef je de username en password mee. Hiervoor kan een user uit eerdergenoemde tabel (monteur/administratief_medewerker) gebruikt worden: 

Voor de demo gebruiken we 'password' als wachtwoord, maar normaliter is dit uiteraard niet veilig en zou je dit niet zo instellen.




Authenticatie 

-	POST/authenticatie 

Cars 

-	GET /api/v1/cars
-	POST/api/v1/cars
-	DELETE /api/v1/cars/{id}
-	GET /api/v1/cars/{id}
-	GET /api/v1/cars/status 
-	PUT /api/v1/cars/{id}
-	PATCH /api/v1/cars/{id}
CarJob 
-	GET /api/v1/carjobs
-	POST/api/v1/ carjobs
-	DELETE /api/v1/carjobs/{id}
-	GET /api/v1/carjobs/{id}
-	GET /api/v1/carjobs/status 
-	PUT /api/v1/carjobs/{id}
-	PATCH /api/v1/carjobs/{id}

CarJobInvoice
-	GET /api/v1/carjobinvoices 
-	GET /api/v1/carjobinvoices/{id}
-	POST /api/v1/carjobinvoices 
-	DELETE /{carjobinvoices_id}

Customers 
-	GET /api/v1/customers 
-	GET /api/v1/customers/{id}
-	GET /api/v1/customers/{id}/name 
-	POST/api/v1/customers 
-	PUT/api/v1/customers/{id}
-	DELETE/api/v1/customers/{id}

FileUpload 
-	GET api/v1/files 
-	GET api/v1/files/ {id}
-	GET api/v1/files/ {id}/ download 
-	POST api/v1/files 
-	DELETE api/v1/files/ {id}

JobOperation 
-	POST api/v1/joboperations/{carjob_id}/ {operation_id} 
-	GET api/v1/ joboperations/{carjob_id}/ {operation_id} 
-	GET api/v1/ joboperations/{carjob_id}/ operations
-	GET api/v1/ joboperations/{operations_id}/ jobs 
-	DELETE api/v1/ joboperations/{carjob_id}/ {operation_id}
-	PUT api/v1/ joboperations/{carjob_id}/ {operation_id}

JobPart 
-	POST api/v1/jobparts/{carjob_id}/ {part_id} 
-	GET api/v1/ jobparts/{carjob_id}/ {part_id} 
-	GET api/v1/ jobparts/{part_id}/ jobs
-	GET api/v1/ jobparts/{carjob_id}/ parts
-	DELETE api/v1/ jobparts/{carjob_id}/ {part_id} 
-	PUT api/v1/ jobparts/{carjob_id}/ {part_id} 

Operation
-	GET api/v1/operations
-	GET api/v1/operations/ {id}
-	POST api/v1/operations
-	PUT api/v1/operations/ {id}
-	DELETE api/v1/operations/ {id}

Part 
-	GET api/v1/parts 
-	GET api/v1/parts/ {id}
-	POST api/v1/parts 
-	PUT api/v1/parts/ {id}
-	DELETE api/v1/parts {id} 

User 
-	GET api/v1/users 
-	GET api/v1/parts/ {username} 
-	POST api/v1/users 
-	PUT api/v1/parts/ {username} 
-	DELETE api/v1/parts/ {username}
-	GET api/v1/parts/ {username}/ authorities 
-	POST api/v1/parts/ authorities/ {username} 
-	DELETE api/v1/parts/ {username}/ authorities/ {authority} 


