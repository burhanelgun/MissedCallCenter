# Missed Call Center App
This app has been developped for handling missed calls to send notification of related users using websocket.

## How to Run Project
1. Install Docker Desktop and switch to Windows container
2. Clone this repository in to your local machine
3. Open a command prompt in the root directory of the local repository
4. Checkout to ```master``` branch if it is not
5. Run command  ```./mvnw install dockerfile:build```
6. Run command  ```docker run -p 8085:8081 -t missedcallcenter/missedcallcenter```
7. The project will up and running on http://localhost:8085/

## Enpoints
* Websocket Client: http://localhost:8085/index.html
* Swagger UI: http://localhost:8085/swagger-ui/index.html
* H2 Console: http://localhost:8085/h2-console
* Other Rest API enpoints can be seen on Swagger UI page.


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
    "name":"Mehmet",
    "password":"123456"
}
```

### Save phone
Saving a phone for a created user: Send POST request to http://localhost:8085/savePhone URL

By providing a JSON simular to this: (The id have to be same with id of respond of saveUser endpoint.)
```JSON
{
    "id": 1,
    "name": "Mehmet",
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
    "name": "Mehmet",
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
    "name": "Mehmet",
    "phone": "99999999999"
}
```
## Example use case
**1.User A Saves Itself To The System By Rest API and Saves Phone To The System**
 * ![alt text](https://user-images.githubusercontent.com/23100256/100641262-49938a80-3348-11eb-9609-7c5cba632c85.png)
 * ![alt text](https://user-images.githubusercontent.com/23100256/100641395-78116580-3348-11eb-94c1-5a1107320038.png)

**2. User A Creates Websocket Connection With Server**
 * ![alt text](https://user-images.githubusercontent.com/23100256/100642073-5a90cb80-3349-11eb-8ea9-9fed9bcc43a7.png)
 * ![alt text](https://user-images.githubusercontent.com/23100256/100642241-988def80-3349-11eb-9759-dc1b00880fc6.png)

**3. User A Calls User B three times(phone:88888888888, but has not logged in to system) With Rest API**
![alt text](https://user-images.githubusercontent.com/23100256/100642958-86608100-334a-11eb-87e8-809f2d15fee9.png)
  (Note: I sent same request for 3 times)

**4. User B Saves Itself To The System By Rest API and Saves Phone To The System and Able To Get Notification From Web Socket**
  * ![alt text](https://user-images.githubusercontent.com/23100256/100644393-51edc480-334c-11eb-8afa-5408b395a916.png)
  * ![alt text](https://user-images.githubusercontent.com/23100256/100644754-c45ea480-334c-11eb-88ce-7b23ac18c870.png)

**5. User B Gets Notification Of Missed Calls Over The Web Socket**
![alt text](https://user-images.githubusercontent.com/23100256/100644975-143d6b80-334d-11eb-8b8d-cd590114ad41.png)

**6. User B Sends Delivery Report To The Sytem**
![alt text](https://user-images.githubusercontent.com/23100256/100645392-a9406480-334d-11eb-9c1d-4b955a26320d.png)

**7. User A Gets Available Notification About the User B(since it was sends delivery report in part 6)**
![alt text](https://user-images.githubusercontent.com/23100256/100645528-d9880300-334d-11eb-8be9-4e99ee62d977.png)






