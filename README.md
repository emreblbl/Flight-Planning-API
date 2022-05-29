# Flight-Planning-API

Flight Project API is a REST service which enables making CRUD operations with data described below.

## Flight-Controller endpoints

### HTTP Post Call Flowchart
![Flowchart](./image/PostFlowchart.png)
### Create flight (Post method) 
 - Request (Application/JSON)
```
{
  "airlineCode": "TA",
  "destinationAirportCode": "TIH",
  "flightDate": "2022-05-19",
  "flightTime": "13:30:00",
  "sourceAirportCode": "AHL"
}
```
 -  Post Response on Swagger :
![Swagger POST](./image/postResponse.png)

- Bad Request Response :
![Swagger POST](./image/badRequest.png)

- Response Code :404,EntityNotFoundException (application/json)
![Swagger POST](./image/404status.png)

- Response Code :400, HttpMessageNotReadableException(application/json)<br />
  - When we send request object with wrong LocalDate input
  - Request :
  ```
   {
  "airlineCode": "TB",
  "destinationAirportCode": "TIH",
  "flightDate": "2022-0a-19",
  "flightTime": "13:30:00",
  "sourceAirportCode": "AHL"
   }
   ```
  -  Response : 
 ![Swagger POST](./image/404NotReadable.png)
 - Response Code 200, 
 

