# Spring_Thymeleaf.Security
 Spring app using Thymeleaf templates implementing user registration and login.

The database stores information about the users and their roles. Allows for the login of current users and registration of new users.
Some request mappings are locked depending on user's role

Please edit the "spring.datasource.username" and "spring.datasource.password" fields in the application.properties file in src/main/resources with the required information to access your local instance of MySQL.

Run the SQL script found inside to create the MySQL database schema. Password for every default user is "fun123".



![image](https://user-images.githubusercontent.com/61985975/80984728-25a9ae00-8e26-11ea-8611-03c1369fdf5f.png)
