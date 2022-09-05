# Book Sharing Application

This is an application to manage the books sharing.
<br>
<b>Prerequisites:</b> java 17, node v16.15.1, mvn 3+  


# Getting Started

To install the application run the following command: 
<br>
`git clone https://github.com/MihaelaaLazar/book-sharing-application.git` 
<br>
<hr>
To start the server run the following commands in the root path: 
<br>

`cd .\server\ `
<br>
`mvn clean install` 
<br>
`mvn spring-boot:run`
<br>
In the server directory there is a file named `application.properties`. It should be configured with your own data. 
```xml
#database config
spring.datasource.url=YOUR_HOST

#the same with the installation
spring.datasource.username=USERNAME
spring.datasource.password=PASSWORD

#config for swagger ui
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.operationsSorter=alpha
springdoc.swagger-ui.tagsSorter=alpha

#smtp config
spring.mail.host=YOUR_HOST
spring.mail.port=587
spring.mail.username=YOUR_EMAIL
spring.mail.password=YOUR_PASSWORD

#cloudinary config
cloudinary.cloud_name=YOUR_CLOUD_NAME
cloudinary.api_key=YOUR_API_KEY
cloudinary.api_secret=YOUR_API_SECRET
```

To start the client in the root path run this:
<br>
`cd .\client\`
<br>
`npm install` and` npm start`
<br>
The client will be available at: `localhost:3000`
<br>
<br>
First of all you have to create an account on route `http://localhost:3000/register`, confirm the account by clicking on the link in the e-mail you should receive, if you did the above configuration correctly. :) 
<br>
<br>
Swagger will be available at: `http://localhost:8080/swagger-ui/index.html`. Don't forget to login first, and then pass the Authorization Token.
<br>
<br>
Now you are free to share your books with anybody.
<br>
<br>
All suggestions and improvements are welcomed. 



