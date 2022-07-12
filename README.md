# registration-service

## Table of content ##
- [Overview](#Overview)
- [Logic](#Logic)
  - [JWT token provider](#JWT-token-provider)
  - [Password Encoder](#Password-Encoder)




## Overview ##

- Main purpose of project is authentication system based on JWT token
- You can pass registration and succesful login after that
- Passwords are encoded with BCryptPasswordEncoder (strength 12) and saved in db

- With success login you can make GET/POST/DELETE requests to Animal controller and manipulate with Animal class in db


## Logic ##

### JWT token provider ###

  I created that class which can generate token based on secret key (you can change it in application.yaml file)
  That token let you access endpoints if you paste it in Authorization header (e.g in Postman)
 
### Password Encoder ###
  
  I create Bean with PasswordEncoder type and return built in BCryptPasswordEncoder with 12 strength
  After that i can save password in bd

## Docker ##

  You should run docker-compose file from docker dir to be able connect to postgres with pgadmin
