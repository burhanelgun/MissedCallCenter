# Missed Call Center App
This app has been developped for handling missed calls to send notification of related users using websocket.

## How to Run Project
1. Clone this repository in to your local machine
2. Open a command prompt in the root directory of the local repository
3. Run command  ```./mvnw install dockerfile:build```
4. Run command  ```docker run -p 8085:8081 -t missedcallcenter/missedcallcenter```
5. The project will up and running on http://localhost:8085/

## Enpoints
* Websocket Client: http://localhost:8085/index.html
* Swagger UI: http://localhost:8085/swagger-ui/index.html
* Other enpoints can be seen on Swagger UI page.


## Usage
A user can do 5 things with using this project.
* Creating a user (Explicitly) - http://localhost:8085/saveUser - POST request
* Saving a phone number (Explicitly) - http://localhost:8085/savePhone - POST request
* Callling a phone number (Explicitly) - http://localhost:8085/call - POST request
* Sending delivery report (Explicitly) - http://localhost:8085/deliveryReport - POST request
* Connecting websocket and getting notifications (Implicitly)

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

