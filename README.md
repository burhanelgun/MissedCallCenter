# Missed Call Center App
This app was developped for handling missed calls to send notification of related users using websocket.

## How to Run Project
Install the repo and checkout to master branch
Open a command line editor in the root directory of the project
Run command  ./mvnw install dockerfile:build
Run command  docker run -p 8085:8081 -t missedcallcenter/missedcallcenter
The project will up and running on http://localhost:8085/

## Enpoints
Websocket Client: http://localhost:8085/index.html
Swagger UI: http://localhost:8085/swagger-ui/index.html
Other enpoints can be seen on Swagger UI page.


## Usage
A user can do 5 things with using this project.
Create a user - Over Rest API - http://localhost:8085/saveUser - POST request
Save phone number (Explicitly) - http://localhost:8085/savePhone - POST request
Call a phone number (Explicitly) - http://localhost:8085/call - POST request
Send delivery report (Explicitly) - http://localhost:8085/deliveryReport - POST request
Connect websocket and get messages (Implicitly)

### Create a user
For creating a new user: Send POST request to http://localhost:8085/saveUser URL
By providing a JSON simular to this:
```JSON
{
    "name":"mehmet",
    "password":"123456"
}
```

### Save phone
Saving a phone for a created user: Send POST request to http://localhost:8085/savePhone URL
By providing a JSON simular to this: (The id have to be same with id of respond of saveUser endpoint.)
```JSON
{
    "id": 1,
    "name": "mehmet",
    "phone": "99999999999"
}
```

### Call phone
Calling a phone number: Send POST request to http://localhost:8085/call URL
By providing a JSON simular to this: (The id have to be same with id of respond of saveUser endpoint.)
```JSON
{
  "calledPhone": "88888888888",
  "callerUserDto": {
    "id": 1,
    "name": "mehmet",
    "phone": "99999999999"
   }
}
```
Note: calledPhone => Aranan Numara, callerUserDto => Arayan User

### Send Delivery Report 
Calling a phone number: Send POST request to http://localhost:8085/call URL
By providing a JSON simular to this: (The id have to be same with id of respond of saveUser endpoint.)
```JSON
{
    "id": 1,
    "name": "mehmet",
    "phone": "99999999999"
}
```

