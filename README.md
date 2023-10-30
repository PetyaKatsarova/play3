# play3
This is a booking vacation house/houses app. This is complete, with many options and functionalities website where people can offer their houses or look for houses to book for certain amount of time. Booking time, search functionalities: by price,
location etc are available. There is an option for clients offering their house to upload pictures, as well.

The app is created with java spring boot, jdk 21. You need to create application.properties file in you resources folder. The content should be something like: 

spring.datasource.url=jdbc:mysql://localhost:3306/try3
spring.datasource.username=root
spring.datasource.password=changeMe123@!
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update

#logging.level.org.springframework.web=DEBUG
spring.mail.host=smtp.gmail.com
spring.mail.port=587

#needed in my gmail account in security settings to create manage pwd to device and generate one for this app
spring.mail.username=YOUR_EMAIL
spring.mail.password=YOUR_DEVICE_PASSWORD 
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

#Trust all certificates (NOT recommended for production)
spring.mail.properties.mail.smtp.ssl.trust=*

---- end of application.properties file ------------------

!NB: open from browser with url: "localhost:8080/loginPage.html"
if you open the .html files from intelij, it opens with localhost:63342/....the complete project route....

********************** USER STORIES *********************

As a tenant, I want to be able to register on the holiday homes site so I can find a cottage.✅ user: same for tenant and landlord
As a tenant, I want to be able to log in to the holiday homes site so I can find a cottage. 
As a tenant, I want to be able to find suitable cottages through a search so I can book them.

As a landlord, I want to be able to register on the holiday homes site so I can offer cottages for rent. ✅ user: same for tenant and landlord
As a landlord, I want to be able to log in to the holiday homes site so I can offer a cottage for rent. ---- > 
As a landlord, I want to offer a cottage so others can rent it.

As a landlord, I want an overview of my own cottages so I can manage them.
As a tenant, I want an overview of my reservations so I can manage them.
As a landlord, I want to be able to cancel a reservation for a cottage so I can retain control over my cottage.
As a tenant, I want to be able to cancel my reservation so I can retain control over my reservations.
As both a tenant and a landlord, I want to send a message to another tenant or landlord so I can make contact.
As both a tenant and a landlord, I want to read my received messages so I can send replies.
As both a tenant and a landlord, I want to be able to delete my received messages so my inbox doesn't get too full.
As a landlord, I want to be able to block tenants so they cannot rent my cottage.
As a landlord, I want to be able to change my cottages so I can correct mistakes.
As both a tenant and a landlord, I want to safely reset my password if I've forgotten it so I'm not locked out.

