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

